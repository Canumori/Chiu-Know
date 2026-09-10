package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ReviewEvidenceSummaryTest {

    @Test
    fun combinesResponseVariantsThatShareOneKnowledgeTarget() {
        val evidence = listOf(
            attempt("fill-1", ResponseType.FILL_IN, false, 100L),
            attempt("fill-2", ResponseType.FILL_IN, true, 200L),
            attempt("reorder", ResponseType.REORDER, true, 300L),
            attempt("choice", ResponseType.MULTIPLE_CHOICE, false, 400L)
        )

        val summary = summarizeReviewEvidence(evidence).single()

        assertEquals("en:a1:grammar:copula:first-person", summary.reviewKey)
        assertEquals(CefrLevel.A1, summary.level)
        assertEquals(4, summary.totalAttempts)
        assertEquals(2, summary.correctAttempts)
        assertEquals(2, summary.incorrectAttempts)
        assertEquals(4, summary.distinctActivityCount)
        assertEquals(100L, summary.firstAttemptAtEpochMillis)
        assertEquals(400L, summary.latestAttemptAtEpochMillis)
        assertFalse(summary.latestWasCorrect)
    }

    @Test
    fun keepsLevelsAndKnowledgeTargetsSeparateAndOrdersNewestFirst() {
        val evidence = listOf(
            LearningEvidence("a1-old", "shared:key", CefrLevel.A1, LearningSkill.GRAMMAR, true, 100L),
            LearningEvidence("a2-new", "shared:key", CefrLevel.A2, LearningSkill.GRAMMAR, true, 500L),
            LearningEvidence("other", "other:key", CefrLevel.A1, LearningSkill.READING, false, 300L)
        )

        val summaries = summarizeReviewEvidence(evidence)

        assertEquals(3, summaries.size)
        assertEquals(CefrLevel.A2, summaries[0].level)
        assertEquals("other:key", summaries[1].reviewKey)
        assertEquals(CefrLevel.A1, summaries[2].level)
        assertTrue(summaries[0].latestWasCorrect)
    }

    private fun attempt(
        id: String,
        responseType: ResponseType,
        correct: Boolean,
        at: Long
    ): LearningEvidence {
        val activity = LearningActivity(
            id = id,
            level = CefrLevel.A1,
            primarySkill = LearningSkill.GRAMMAR,
            learningObjective = "Exercise one shared grammar target",
            knowledgeTarget = "I am",
            responseType = responseType,
            prompt = "Respond",
            feedback = "Observed attempt",
            reviewKey = "en:a1:grammar:copula:first-person",
            acceptedAnswers = listOf("answer"),
            responseOptions = if (responseType == ResponseType.FILL_IN) emptyList() else listOf("answer", "other")
        )
        return learningEvidenceFor(activity, correct, at)
    }
}
