package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LearningEvidenceSummaryTest {

    @Test
    fun summarizesAttemptsWithoutInferringMastery() {
        val evidence = listOf(
            LearningEvidence(
                activityId = "en-a1-greeting-001",
                reviewKey = "en:a1:greeting:hello",
                level = CefrLevel.A1,
                primarySkill = LearningSkill.VOCABULARY,
                correct = false,
                attemptedAtEpochMillis = 100L
            ),
            LearningEvidence(
                activityId = "en-a1-greeting-001",
                reviewKey = "en:a1:greeting:hello",
                level = CefrLevel.A1,
                primarySkill = LearningSkill.VOCABULARY,
                correct = true,
                attemptedAtEpochMillis = 200L
            )
        )

        val summary = summarizeLearningEvidence(evidence).single()

        assertEquals(2, summary.totalAttempts)
        assertEquals(1, summary.correctAttempts)
        assertEquals(1, summary.incorrectAttempts)
        assertEquals(200L, summary.latestAttemptAtEpochMillis)
        assertTrue(summary.latestWasCorrect)
    }

    @Test
    fun keepsReviewTargetsSeparateAndOrdersNewestFirst() {
        val evidence = listOf(
            LearningEvidence("first", "first:key", CefrLevel.A1, LearningSkill.VOCABULARY, true, 100L),
            LearningEvidence("second", "second:key", CefrLevel.A1, LearningSkill.VOCABULARY, false, 300L),
            LearningEvidence("first", "first:key", CefrLevel.A1, LearningSkill.VOCABULARY, false, 200L)
        )

        val summaries = summarizeLearningEvidence(evidence)

        assertEquals(2, summaries.size)
        assertEquals("second:key", summaries[0].reviewKey)
        assertEquals("first:key", summaries[1].reviewKey)
        assertFalse(summaries[0].latestWasCorrect)
        assertEquals(2, summaries[1].totalAttempts)
    }
}
