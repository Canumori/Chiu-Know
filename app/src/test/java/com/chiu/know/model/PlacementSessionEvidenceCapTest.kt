package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class PlacementSessionEvidenceCapTest {

    @Test
    fun locatingStopsAtMaximumEvidenceInsteadOfPresentingAnotherQuestion() {
        val policy = PlacementQualityPolicy(
            minimumAnsweredQuestions = 1,
            maximumAnsweredQuestions = 1
        )
        val bank = placementCandidateQuestionsFor("en")
        val start = startPlacementSession(bank)

        val terminal = advancePlacementSession(
            state = start,
            answeredCorrectly = true,
            questions = bank,
            policy = policy
        )

        assertTrue(terminal.isFinished)
        assertEquals(policy.maximumAnsweredQuestions, terminal.answeredQuestions)
        assertEquals(PlacementTerminalReason.MAX_EVIDENCE_INCONCLUSIVE, terminal.terminalReason)
        assertNull(terminal.current)
        assertNull(terminal.finalDecision?.decidedLevel)
        assertEquals(terminal.answeredQuestions, terminal.usedQuestionIds.size)
    }

    @Test
    fun incompleteMandatoryConfirmationAtCapNeverProducesLevelDecision() {
        val policy = PlacementQualityPolicy(
            minimumAnsweredQuestions = 1,
            maximumAnsweredQuestions = 1
        )
        val currentQuestion = question("b1-current", CefrLevel.B1)
        val queuedQuestion = question("b1-required", CefrLevel.B1)
        val state = PlacementSessionState(
            adaptiveState = startAdaptivePlacement(),
            phase = PlacementSessionPhase.CONFIRM,
            current = PlacementSessionQuestion(
                currentQuestion,
                PlacementConfirmationRole.ESTIMATED_LEVEL
            ),
            usedQuestionIds = setOf(currentQuestion.id),
            confirmationQueue = listOf(
                PlacementSessionQuestion(
                    queuedQuestion,
                    PlacementConfirmationRole.ESTIMATED_LEVEL
                )
            ),
            provisionalLevel = CefrLevel.B1
        )

        val terminal = advancePlacementSession(
            state = state,
            answeredCorrectly = true,
            questions = listOf(currentQuestion, queuedQuestion),
            policy = policy
        )

        assertTrue(terminal.isFinished)
        assertEquals(PlacementTerminalReason.MAX_EVIDENCE_INCONCLUSIVE, terminal.terminalReason)
        assertEquals(PlacementDecisionStatus.NEEDS_MORE_EVIDENCE, terminal.finalDecision?.status)
        assertNull(terminal.finalDecision?.decidedLevel)
        assertNull(terminal.current)
    }

    private fun question(id: String, level: CefrLevel) = PlacementQuestion(
        id = id,
        level = level,
        prompt = "Evidence cap test $id",
        options = listOf("a", "b"),
        correctIndex = 0
    )
}
