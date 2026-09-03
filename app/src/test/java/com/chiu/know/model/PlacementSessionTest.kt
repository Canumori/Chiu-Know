package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class PlacementSessionTest {

    @Test
    fun englishSessionNeverRepeatsQuestionsAndFinishesWithinPolicy() {
        val policy = PlacementQualityPolicy()
        val bank = placementCandidateQuestionsFor("en")

        allTerminalEnglishSessions(bank, policy).forEach { terminal ->
            assertTrue(terminal.isFinished)
            assertTrue(terminal.answeredQuestions <= policy.maximumAnsweredQuestions)
            assertEquals(terminal.usedQuestionIds.size, terminal.usedQuestionIds.toSet().size)
            assertTrue(terminal.usedQuestionIds.size >= terminal.answeredQuestions)
        }
    }

    @Test
    fun completedEnglishSessionsNeverFinishBeforeMinimumEvidence() {
        val policy = PlacementQualityPolicy()
        val bank = placementCandidateQuestionsFor("en")

        allTerminalEnglishSessions(bank, policy)
            .filter { it.phase == PlacementSessionPhase.COMPLETE }
            .forEach { terminal ->
                assertTrue(terminal.answeredQuestions >= policy.minimumAnsweredQuestions)
                assertNotNull(terminal.finalDecision)
                assertNotNull(terminal.finalDecision?.decidedLevel)
                assertTrue(
                    terminal.finalDecision?.status in setOf(
                        PlacementDecisionStatus.CONFIRMED,
                        PlacementDecisionStatus.REVISED_DOWN,
                        PlacementDecisionStatus.REVISED_UP
                    )
                )
            }
    }

    @Test
    fun everyCompletedDecisionStaysInsideCefrAndMovesAtMostOneBand() {
        val policy = PlacementQualityPolicy()
        val bank = placementCandidateQuestionsFor("en")
        val levels = CefrLevel.entries

        allTerminalEnglishSessions(bank, policy)
            .filter { it.phase == PlacementSessionPhase.COMPLETE }
            .forEach { terminal ->
                val provisional = requireNotNull(terminal.provisionalLevel)
                val decided = requireNotNull(terminal.finalDecision?.decidedLevel)
                val distance = kotlin.math.abs(levels.indexOf(decided) - levels.indexOf(provisional))
                assertTrue(distance <= 1)
                assertTrue(decided in CefrLevel.entries)
            }
    }

    @Test
    fun bankInsufficiencyIsExplicitAndNeverInventsFinalLevel() {
        val tinyBank = listOf(
            PlacementQuestion(
                id = "only-b1",
                level = CefrLevel.B1,
                prompt = "Only B1 question",
                options = listOf("a", "b", "c", "d"),
                correctIndex = 0
            )
        )

        val start = startPlacementSession(tinyBank)
        assertEquals(PlacementSessionPhase.LOCATE, start.phase)

        val terminal = advancePlacementSession(
            state = start,
            answeredCorrectly = true,
            questions = tinyBank
        )

        assertEquals(PlacementSessionPhase.BANK_INSUFFICIENT, terminal.phase)
        assertTrue(terminal.isFinished)
        assertNull(terminal.current)
        assertNull(terminal.finalDecision?.decidedLevel)
    }

    @Test
    fun sessionCannotAdvanceAfterCompletionOrBankFailure() {
        val tinyBank = listOf(
            PlacementQuestion(
                id = "only-b1",
                level = CefrLevel.B1,
                prompt = "Only B1 question",
                options = listOf("a", "b", "c", "d"),
                correctIndex = 0
            )
        )
        val terminal = advancePlacementSession(
            state = startPlacementSession(tinyBank),
            answeredCorrectly = true,
            questions = tinyBank
        )

        var threw = false
        try {
            advancePlacementSession(terminal, true, tinyBank)
        } catch (_: IllegalArgumentException) {
            threw = true
        }
        assertTrue(threw)
    }

    @Test
    fun activeEnglishSessionAlwaysHasCurrentQuestionAndTerminalSessionNeverDoes() {
        val policy = PlacementQualityPolicy()
        val bank = placementCandidateQuestionsFor("en")

        val seen = mutableSetOf<PlacementSessionState>()
        fun visit(state: PlacementSessionState) {
            if (!seen.add(state)) return
            if (state.isFinished) {
                assertNull(state.current)
                return
            }
            assertNotNull(state.current)
            visit(advancePlacementSession(state, false, bank, policy))
            visit(advancePlacementSession(state, true, bank, policy))
        }

        visit(startPlacementSession(bank))
        assertFalse(seen.isEmpty())
    }

    private fun allTerminalEnglishSessions(
        bank: List<PlacementQuestion>,
        policy: PlacementQualityPolicy
    ): List<PlacementSessionState> {
        val terminal = mutableListOf<PlacementSessionState>()

        fun visit(state: PlacementSessionState) {
            if (state.isFinished) {
                terminal += state
                return
            }
            check(state.answeredQuestions < policy.maximumAnsweredQuestions) {
                "Active session exceeded maximum question contract"
            }
            visit(advancePlacementSession(state, false, bank, policy))
            visit(advancePlacementSession(state, true, bank, policy))
        }

        visit(startPlacementSession(bank))
        assertTrue(terminal.isNotEmpty())
        return terminal
    }
}
