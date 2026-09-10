package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LearningClaimEvidenceSummaryTest {

    @Test
    fun oneKnownAttemptContributesOnlyToSupportedClaims() {
        val readingFillIn = activity(
            id = "reading-fill",
            skill = LearningSkill.READING,
            responseType = ResponseType.FILL_IN
        )
        val evidence = listOf(
            learningEvidenceFor(readingFillIn, correct = true, attemptedAtEpochMillis = 100L)
        )

        val summaries = summarizeLearningEvidenceByClaim(evidence, listOf(readingFillIn))

        assertEquals(
            setOf(EvidenceClaim.CUED_RETRIEVAL, EvidenceClaim.READING_COMPREHENSION),
            summaries.map { it.claim }.toSet()
        )
        summaries.forEach {
            assertEquals(1, it.totalAttempts)
            assertEquals(1, it.correctAttempts)
            assertEquals(CefrLevel.A1, it.level)
        }
        assertFalse(summaries.any { it.claim == EvidenceClaim.WRITTEN_PRODUCTION })
    }

    @Test
    fun unknownActivitiesAndUnsupportedClaimsAreNotInvented() {
        val speaking = activity(
            id = "speaking",
            skill = LearningSkill.SPEAKING,
            responseType = ResponseType.SPEAK
        )
        val evidence = listOf(
            learningEvidenceFor(speaking, correct = false, attemptedAtEpochMillis = 200L),
            LearningEvidence(
                activityId = "removed-activity",
                reviewKey = "unknown:target",
                level = CefrLevel.A1,
                primarySkill = LearningSkill.LISTENING,
                correct = true,
                attemptedAtEpochMillis = 300L
            )
        )

        val summaries = summarizeLearningEvidenceByClaim(evidence, listOf(speaking))

        assertEquals(listOf(EvidenceClaim.SPOKEN_PRODUCTION), summaries.map { it.claim })
        assertFalse(summaries.any { it.claim == EvidenceClaim.PRONUNCIATION })
        assertFalse(summaries.any { it.claim == EvidenceClaim.INTERACTION })
    }

    @Test
    fun duplicateActivityIdsAreRejectedAsAmbiguous() {
        val first = activity("duplicate", LearningSkill.GRAMMAR, ResponseType.MULTIPLE_CHOICE)
        val second = activity("duplicate", LearningSkill.READING, ResponseType.FILL_IN)

        var threw = false
        try {
            summarizeLearningEvidenceByClaim(emptyList(), listOf(first, second))
        } catch (_: IllegalArgumentException) {
            threw = true
        }

        assertTrue(threw)
    }

    private fun activity(
        id: String,
        skill: LearningSkill,
        responseType: ResponseType
    ) = LearningActivity(
        id = id,
        level = CefrLevel.A1,
        primarySkill = skill,
        learningObjective = "Observe a supported evidence claim",
        knowledgeTarget = "target",
        responseType = responseType,
        prompt = "Respond",
        feedback = "Observed attempt",
        reviewKey = "$id:review",
        acceptedAnswers = listOf("answer"),
        responseOptions = when (responseType) {
            ResponseType.MULTIPLE_CHOICE, ResponseType.REORDER -> listOf("answer", "other")
            else -> emptyList()
        }
    )
}
