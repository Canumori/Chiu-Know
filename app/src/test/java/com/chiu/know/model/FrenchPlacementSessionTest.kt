package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class FrenchPlacementSessionTest {
    @Test
    fun everyFrenchAnswerPathTerminatesWithinQualityPolicy() {
        val policy = PlacementQualityPolicy()
        val terminal = allTerminalSessions(policy)

        assertTrue(terminal.isNotEmpty())
        terminal.forEach { state ->
            assertTrue(state.isFinished)
            assertTrue(state.answeredQuestions <= policy.maximumAnsweredQuestions)
            assertNull(state.current)
            assertEquals(state.answeredQuestions, state.usedQuestionIds.size)
        }
    }

    @Test
    fun completedFrenchSessionsHaveEnoughEvidenceAndARealDecision() {
        val policy = PlacementQualityPolicy()

        allTerminalSessions(policy)
            .filter { it.phase == PlacementSessionPhase.COMPLETE }
            .forEach { state ->
                assertTrue(state.answeredQuestions >= policy.minimumAnsweredQuestions)
                assertNotNull(state.finalDecision)
                assertNotNull(state.finalDecision?.decidedLevel)
                assertTrue(
                    state.finalDecision?.status in setOf(
                        PlacementDecisionStatus.CONFIRMED,
                        PlacementDecisionStatus.REVISED_DOWN,
                        PlacementDecisionStatus.REVISED_UP
                    )
                )
            }
    }

    @Test
    fun activeFrenchSessionsAlwaysExposeExactlyOneUnansweredPresentedQuestion() {
        val policy = PlacementQualityPolicy()
        val seen = mutableSetOf<PlacementSessionState>()

        fun visit(state: PlacementSessionState) {
            if (!seen.add(state)) return
            if (state.isFinished) {
                assertNull(state.current)
                assertEquals(state.answeredQuestions, state.usedQuestionIds.size)
                return
            }
            assertNotNull(state.current)
            assertTrue(state.answeredQuestions < policy.maximumAnsweredQuestions)
            assertEquals(state.answeredQuestions + 1, state.usedQuestionIds.size)
            assertTrue(state.current?.question?.id in state.usedQuestionIds)
            state.confirmationQueue.forEach { queued ->
                assertTrue(queued.question.id !in state.usedQuestionIds)
            }
            visit(advancePlacementSession(state, false, qualityFrenchPlacementQuestions, policy))
            visit(advancePlacementSession(state, true, qualityFrenchPlacementQuestions, policy))
        }

        visit(startPlacementSession(qualityFrenchPlacementQuestions))
        assertTrue(seen.isNotEmpty())
    }

    @Test
    fun completedFrenchDecisionNeverMovesMoreThanOneCefrBand() {
        val policy = PlacementQualityPolicy()
        val levels = CefrLevel.entries

        allTerminalSessions(policy)
            .filter { it.phase == PlacementSessionPhase.COMPLETE }
            .forEach { state ->
                val provisional = requireNotNull(state.provisionalLevel)
                val decided = requireNotNull(state.finalDecision?.decidedLevel)
                val distance = kotlin.math.abs(levels.indexOf(decided) - levels.indexOf(provisional))
                assertTrue(distance <= 1)
            }
    }

    private fun allTerminalSessions(policy: PlacementQualityPolicy): List<PlacementSessionState> {
        val terminal = mutableListOf<PlacementSessionState>()

        fun visit(state: PlacementSessionState) {
            if (state.isFinished) {
                terminal += state
                return
            }
            check(state.answeredQuestions < policy.maximumAnsweredQuestions) {
                "Active French session exceeded maximum question contract"
            }
            visit(advancePlacementSession(state, false, qualityFrenchPlacementQuestions, policy))
            visit(advancePlacementSession(state, true, qualityFrenchPlacementQuestions, policy))
        }

        visit(startPlacementSession(qualityFrenchPlacementQuestions))
        return terminal
    }
}
