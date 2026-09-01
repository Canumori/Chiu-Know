package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LearningActivityTest {

    private fun sampleActivity() = LearningActivity(
        id = "en-a1-greeting-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Recognize and retrieve a basic greeting in context",
        knowledgeTarget = "hello",
        responseType = ResponseType.FILL_IN,
        prompt = "Complete the greeting: ___! Nice to meet you.",
        feedback = "‘Hello’ is a common neutral greeting.",
        reviewKey = "en:a1:greeting:hello",
        acceptedAnswers = listOf("hello")
    )

    @Test
    fun activityKeepsPedagogicalMetadataSeparateFromPresentation() {
        val activity = sampleActivity()

        assertEquals(CefrLevel.A1, activity.level)
        assertEquals(LearningSkill.VOCABULARY, activity.primarySkill)
        assertEquals(ResponseType.FILL_IN, activity.responseType)
        assertEquals("en:a1:greeting:hello", activity.reviewKey)
    }

    @Test
    fun evaluatorIgnoresCaseAndSurroundingWhitespaceOnly() {
        val activity = sampleActivity()

        assertTrue(isLearningAnswerCorrect(activity, "  HeLLo  "))
        assertFalse(isLearningAnswerCorrect(activity, "hi"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun activityRejectsMissingLearningObjective() {
        sampleActivity().copy(learningObjective = "")
    }

    @Test(expected = IllegalArgumentException::class)
    fun activityRejectsMissingReviewLink() {
        sampleActivity().copy(reviewKey = "")
    }

    @Test(expected = IllegalArgumentException::class)
    fun activityRejectsMissingAcceptedAnswers() {
        sampleActivity().copy(acceptedAnswers = emptyList())
    }
}
