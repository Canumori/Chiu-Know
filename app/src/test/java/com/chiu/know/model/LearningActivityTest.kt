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
        assertTrue(activity.responseOptions.isEmpty())
    }

    @Test
    fun evaluatorIgnoresCaseAndSurroundingWhitespaceOnly() {
        val activity = sampleActivity()

        assertTrue(isLearningAnswerCorrect(activity, "  HeLLo  "))
        assertFalse(isLearningAnswerCorrect(activity, "hi"))
    }

    @Test
    fun reorderActivityKeepsExplicitTokensSeparateFromAcceptedAnswer() {
        val activity = sampleActivity().copy(
            id = "en-a1-reorder-001",
            responseType = ResponseType.REORDER,
            prompt = "Put the words in order.",
            acceptedAnswers = listOf("I am Mia"),
            responseOptions = listOf("Mia", "am", "I")
        )

        assertEquals(listOf("Mia", "am", "I"), activity.responseOptions)
        assertTrue(isLearningAnswerCorrect(activity, "I am Mia"))
        assertFalse(isLearningAnswerCorrect(activity, "Mia I am"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun structuredResponseRejectsMissingOptions() {
        sampleActivity().copy(responseType = ResponseType.REORDER)
    }

    @Test(expected = IllegalArgumentException::class)
    fun structuredResponseRejectsBlankOption() {
        sampleActivity().copy(
            responseType = ResponseType.MULTIPLE_CHOICE,
            responseOptions = listOf("hello", "")
        )
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
