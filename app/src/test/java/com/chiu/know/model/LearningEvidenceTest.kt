package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class LearningEvidenceTest {

    @Test
    fun evidenceCopiesPedagogicalIdentityFromActivity() {
        val activity = LearningActivity(
            id = "en-a1-greeting-001",
            level = CefrLevel.A1,
            primarySkill = LearningSkill.VOCABULARY,
            learningObjective = "Retrieve a greeting",
            knowledgeTarget = "hello",
            responseType = ResponseType.FILL_IN,
            prompt = "___!",
            feedback = "Use Hello.",
            reviewKey = "en:a1:greeting:hello",
            acceptedAnswers = listOf("hello")
        )

        val evidence = learningEvidenceFor(activity, correct = false, attemptedAtEpochMillis = 1234L)

        assertEquals(activity.id, evidence.activityId)
        assertEquals(activity.reviewKey, evidence.reviewKey)
        assertEquals(CefrLevel.A1, evidence.level)
        assertEquals(LearningSkill.VOCABULARY, evidence.primarySkill)
        assertFalse(evidence.correct)
        assertEquals(1234L, evidence.attemptedAtEpochMillis)
    }

    @Test(expected = IllegalArgumentException::class)
    fun evidenceRejectsNegativeTimestamp() {
        LearningEvidence(
            activityId = "en-a1-greeting-001",
            reviewKey = "en:a1:greeting:hello",
            level = CefrLevel.A1,
            primarySkill = LearningSkill.VOCABULARY,
            correct = true,
            attemptedAtEpochMillis = -1L
        )
    }
}
