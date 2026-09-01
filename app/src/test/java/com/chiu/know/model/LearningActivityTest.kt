package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Test

class LearningActivityTest {

    @Test
    fun activityKeepsPedagogicalMetadataSeparateFromPresentation() {
        val activity = LearningActivity(
            id = "en-a1-greeting-001",
            level = CefrLevel.A1,
            primarySkill = LearningSkill.VOCABULARY,
            learningObjective = "Recognize and retrieve a basic greeting in context",
            knowledgeTarget = "hello",
            responseType = ResponseType.FILL_IN,
            prompt = "Complete the greeting: ___! Nice to meet you.",
            feedback = "‘Hello’ is a common neutral greeting.",
            reviewKey = "en:a1:greeting:hello"
        )

        assertEquals(CefrLevel.A1, activity.level)
        assertEquals(LearningSkill.VOCABULARY, activity.primarySkill)
        assertEquals(ResponseType.FILL_IN, activity.responseType)
        assertEquals("en:a1:greeting:hello", activity.reviewKey)
    }

    @Test(expected = IllegalArgumentException::class)
    fun activityRejectsMissingLearningObjective() {
        LearningActivity(
            id = "invalid",
            level = CefrLevel.A1,
            primarySkill = LearningSkill.GRAMMAR,
            learningObjective = "",
            knowledgeTarget = "be",
            responseType = ResponseType.FILL_IN,
            prompt = "I ___ happy.",
            feedback = "Use ‘am’ with I.",
            reviewKey = "en:a1:be:first-person"
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun activityRejectsMissingReviewLink() {
        LearningActivity(
            id = "invalid-review",
            level = CefrLevel.A1,
            primarySkill = LearningSkill.VOCABULARY,
            learningObjective = "Retrieve a greeting",
            knowledgeTarget = "hello",
            responseType = ResponseType.FILL_IN,
            prompt = "___!",
            feedback = "Use ‘Hello’.",
            reviewKey = ""
        )
    }
}
