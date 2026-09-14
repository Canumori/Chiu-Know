package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class KoreanPlacementCandidateSessionTest {

    @Test
    fun everyKoreanCandidateAnswerPathTerminatesWithinQualityPolicy() {
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
    fun completedKoreanCandidateSessionsHaveEnoughEvidenceAndARealDecision() {
        val policy = PlacementQualityPolicy()

        allTerminalSessions(policy)
            .filter { it.phase == PlacementSessionPhase.COMPLETE }
            .forEach { state ->
                assertTrue(state.answeredQuestions >= policy.minimumAnsweredQuestions)
                assertNotNull(state.finalDecision)
                assertNotNull(state.finalDecision?.decidedLevel)
            }
    }

    @Test
    fun activeKoreanCandidateSessionsExposeOnlyThePresentedQuestion() {
        val policy = PlacementQualityPolicy()
        val seen = mutableSetOf<PlacementSessionState>()

        fun visit(state: PlacementSessionState) {
            if (!seen.add(state)) return
            if (state.isFinished) {
                assertNull(state.current)
                assertEquals(state.answeredQuestions, state.usedQuestionIds.size)
                return
            }

            assertTrue(state.answeredQuestions < policy.maximumAnsweredQuestions)
            assertNotNull(state.current)
            assertEquals(state.answeredQuestions + 1, state.usedQuestionIds.size)
            assertTrue(state.current?.question?.id in state.usedQuestionIds)
            state.confirmationQueue.forEach { queued ->
                assertTrue(queued.question.id !in state.usedQuestionIds)
            }

            visit(advancePlacementSession(state, false, candidateKoreanPlacementQuestions, policy))
            visit(advancePlacementSession(state, true, candidateKoreanPlacementQuestions, policy))
        }

        visit(startPlacementSession(candidateKoreanPlacementQuestions))
        assertTrue(seen.isNotEmpty())
    }

    @Test
    fun completedKoreanCandidateDecisionNeverMovesMoreThanOneCefrBand() {
        val policy = PlacementQualityPolicy()
        val levels = CefrLevel.entries

        allTerminalSessions(policy)
            .filter { it.phase == PlacementSessionPhase.COMPLETE }
            .forEach { state ->
                val provisional = requireNotNull(state.provisionalLevel)
                val decided = requireNotNull(state.finalDecision?.decidedLevel)
                assertTrue(kotlin.math.abs(levels.indexOf(decided) - levels.indexOf(provisional)) <= 1)
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
                "Active Korean candidate session exceeded maximum question contract"
            }
            visit(advancePlacementSession(state, false, candidateKoreanPlacementQuestions, policy))
            visit(advancePlacementSession(state, true, candidateKoreanPlacementQuestions, policy))
        }

        visit(startPlacementSession(candidateKoreanPlacementQuestions))
        return terminal
    }
}
