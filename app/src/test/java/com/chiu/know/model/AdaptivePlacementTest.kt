package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class AdaptivePlacementTest {

    @Test
    fun startsAtB1AcrossFullRange() {
        val state = startAdaptivePlacement()

        assertEquals(CefrLevel.A1, state.estimatedLevel)
        assertEquals(CefrLevel.B1, state.currentLevel)
        assertFalse(state.isFinished)
        assertEquals(0, state.answeredQuestions)
    }

    @Test
    fun allCorrectAnswersCanReachC2() {
        var state = startAdaptivePlacement()
        var step = advanceAdaptivePlacement(state, answeredCorrectly = true)

        while (!step.finished) {
            state = step.state
            step = advanceAdaptivePlacement(state, answeredCorrectly = true)
        }

        assertEquals(CefrLevel.C2, step.estimatedLevel)
        assertNull(step.nextLevel)
        assertTrue(step.finished)
    }

    @Test
    fun allIncorrectAnswersCanReachA1() {
        var state = startAdaptivePlacement()
        var step = advanceAdaptivePlacement(state, answeredCorrectly = false)

        while (!step.finished) {
            state = step.state
            step = advanceAdaptivePlacement(state, answeredCorrectly = false)
        }

        assertEquals(CefrLevel.A1, step.estimatedLevel)
        assertNull(step.nextLevel)
        assertTrue(step.finished)
    }

    @Test
    fun everyPossibleAnswerPathTerminatesQuicklyAndSafely() {
        val finishedLevels = mutableSetOf<CefrLevel>()
        val visited = mutableSetOf<Pair<AdaptivePlacementState, List<Boolean>>>()

        fun explore(state: AdaptivePlacementState, answers: List<Boolean>) {
            assertTrue("Adaptive placement exceeded six answers: $answers", answers.size <= CefrLevel.entries.size)

            if (state.isFinished) {
                finishedLevels += state.estimatedLevel
                return
            }

            listOf(false, true).forEach { answer ->
                val step = advanceAdaptivePlacement(state, answer)
                val nextAnswers = answers + answer
                assertEquals(nextAnswers.size, step.state.answeredQuestions)
                assertTrue(step.state.lowerBoundIndex <= step.state.upperBoundIndex)
                assertTrue(step.state.currentLevelIndex in step.state.lowerBoundIndex..step.state.upperBoundIndex)

                if (step.finished) {
                    assertNull(step.nextLevel)
                    finishedLevels += step.estimatedLevel
                } else {
                    assertEquals(step.state.currentLevel, step.nextLevel)
                    if (visited.add(step.state to nextAnswers)) explore(step.state, nextAnswers)
                }
            }
        }

        explore(startAdaptivePlacement(), emptyList())

        assertEquals(CefrLevel.entries.toSet(), finishedLevels)
    }

    @Test
    fun finishedStateDoesNotAdvanceAgain() {
        val finished = AdaptivePlacementState(
            lowerBoundIndex = CefrLevel.entries.indexOf(CefrLevel.B2),
            upperBoundIndex = CefrLevel.entries.indexOf(CefrLevel.B2),
            currentLevelIndex = CefrLevel.entries.indexOf(CefrLevel.B2),
            answeredQuestions = 3
        )

        val step = advanceAdaptivePlacement(finished, answeredCorrectly = true)

        assertEquals(finished, step.state)
        assertEquals(CefrLevel.B2, step.estimatedLevel)
        assertNull(step.nextLevel)
        assertTrue(step.finished)
    }
}
