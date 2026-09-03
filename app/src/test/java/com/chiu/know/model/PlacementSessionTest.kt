package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class PlacementSessionTest {

    @Test
    fun englishSessionTracksExactlyPresentedQuestionsAndFinishesWithinPolicy() {
        val policy = PlacementQualityPolicy()
        val bank = placementCandidateQuestionsFor("en")
        val seen = mutableSetOf<PlacementSessionState>()

        fun visit(state: PlacementSessionState) {
            if (!seen.add(state)) return
            assertEquals(state.usedQuestionIds.size, state.usedQuestionIds.toSet().size)
            if (state.isFinished) {
                assertNull(state.current)
                assertEquals(state.answeredQuestions, state.usedQuestionIds.size)
                assertTrue(state.answeredQuestions <= policy.maximumAnsweredQuestions)
                return
            }
            assertNotNull(state.current)
            assertEquals(state.answeredQuestions + 1, state.usedQuestionIds.size)
            assertTrue(state.current!!.question.id in state.usedQuestionIds)
            visit(advancePlacementSession(state, false, bank, policy))
            visit(advancePlacementSession(state, true, bank, policy))
        }

        visit(startPlacementSession(bank))
        assertFalse(seen.isEmpty())
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
                assertNull(terminal.terminalReason)
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
        assertEquals(PlacementTerminalReason.BANK_INSUFFICIENT, terminal.terminalReason)
        assertTrue(terminal.isFinished)
        assertNull(terminal.current)
        assertNull(terminal.finalDecision?.decidedLevel)
    }

    @Test
    fun maximumEvidenceInconclusiveIsNotMisreportedAsBankExhaustion() {
        val policy = PlacementQualityPolicy(maximumAnsweredQuestions = 8)
        val bank = placementCandidateQuestionsFor("en")
        val terminal = allTerminalEnglishSessions(bank, policy)
            .firstOrNull { it.terminalReason == PlacementTerminalReason.MAX_EVIDENCE_INCONCLUSIVE }

        assertNotNull(terminal)
        assertEquals(policy.maximumAnsweredQuestions, terminal!!.answeredQuestions)
        assertEquals(PlacementDecisionStatus.NEEDS_MORE_EVIDENCE, terminal.finalDecision?.status)
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
