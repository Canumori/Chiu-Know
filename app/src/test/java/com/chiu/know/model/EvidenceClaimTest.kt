package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class EvidenceClaimTest {

    private fun activity(
        skill: LearningSkill,
        responseType: ResponseType,
        audioPromptId: String? = null
    ) = LearningActivity(
        id = "test",
        level = CefrLevel.A1,
        primarySkill = skill,
        learningObjective = "objective",
        knowledgeTarget = "target",
        responseType = responseType,
        prompt = "prompt",
        feedback = "feedback",
        reviewKey = "review",
        acceptedAnswers = listOf("answer"),
        responseOptions = when (responseType) {
            ResponseType.MULTIPLE_CHOICE, ResponseType.REORDER -> listOf("a", "b")
            else -> emptyList()
        },
        audioPromptId = audioPromptId
    )

    @Test
    fun recognitionFormatsDoNotPretendToBeFreeProduction() {
        val claims = supportedEvidenceClaims(activity(LearningSkill.GRAMMAR, ResponseType.MULTIPLE_CHOICE))

        assertEquals(setOf(EvidenceClaim.RECOGNITION), claims)
        assertFalse(claims.contains(EvidenceClaim.WRITTEN_PRODUCTION))
    }

    @Test
    fun readingActivitiesMayContributeReadingEvidenceAlongsideTheirResponseDemand() {
        val claims = supportedEvidenceClaims(activity(LearningSkill.READING, ResponseType.FILL_IN))

        assertTrue(claims.contains(EvidenceClaim.READING_COMPREHENSION))
        assertTrue(claims.contains(EvidenceClaim.CUED_RETRIEVAL))
    }

    @Test
    fun listeningRequiresTheListeningResponseContract() {
        val claims = supportedEvidenceClaims(
            activity(
                skill = LearningSkill.LISTENING,
                responseType = ResponseType.LISTEN_AND_RESPOND,
                audioPromptId = "audio-1"
            )
        )

        assertEquals(setOf(EvidenceClaim.LISTENING_COMPREHENSION), claims)
    }

    @Test
    fun speakDoesNotClaimPronunciationOrInteractionWithoutDedicatedAnalysis() {
        val claims = supportedEvidenceClaims(activity(LearningSkill.SPEAKING, ResponseType.SPEAK))

        assertTrue(claims.contains(EvidenceClaim.SPOKEN_PRODUCTION))
        assertFalse(claims.contains(EvidenceClaim.PRONUNCIATION))
        assertFalse(claims.contains(EvidenceClaim.INTERACTION))
    }
}
