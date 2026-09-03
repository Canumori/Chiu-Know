package com.chiu.know.model

import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class PortuguesePlacementSessionTest {
    @Test
    fun everyPortugueseAnswerPathTerminatesWithinQualityPolicy() {
        val policy = PlacementQualityPolicy()
        val terminal = allTerminalSessions(policy)

        assertTrue(terminal.isNotEmpty())
        terminal.forEach { state ->
            assertTrue(state.isFinished)
            assertTrue(state.answeredQuestions <= policy.maximumAnsweredQuestions)
            assertNull(state.current)
        }
    }

    @Test
    fun completedPortugueseSessionsHaveEnoughEvidenceAndARealDecision() {
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
    fun activePortugueseSessionsAlwaysExposeAQuestion() {
        val policy = PlacementQualityPolicy()
        val seen = mutableSetOf<PlacementSessionState>()

        fun visit(state: PlacementSessionState) {
            if (!seen.add(state)) return
            if (state.isFinished) {
                assertNull(state.current)
                return
            }
            assertNotNull(state.current)
            assertTrue(state.answeredQuestions < policy.maximumAnsweredQuestions)
            visit(advancePlacementSession(state, false, qualityPortuguesePlacementQuestions, policy))
            visit(advancePlacementSession(state, true, qualityPortuguesePlacementQuestions, policy))
        }

        visit(startPlacementSession(qualityPortuguesePlacementQuestions))
        assertTrue(seen.isNotEmpty())
    }

    @Test
    fun completedPortugueseDecisionNeverMovesMoreThanOneCefrBand() {
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
                "Active Portuguese session exceeded maximum question contract"
            }
            visit(advancePlacementSession(state, false, qualityPortuguesePlacementQuestions, policy))
            visit(advancePlacementSession(state, true, qualityPortuguesePlacementQuestions, policy))
        }

        visit(startPlacementSession(qualityPortuguesePlacementQuestions))
        return terminal
    }
}
