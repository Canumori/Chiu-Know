package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class LearningCueStageTest {

    @Test
    fun closedEvaluatorsExposeProgressivelyReducedCueStages() {
        assertEquals(
            LearningCueStage.RECOGNITION,
            learningCueStage(activity(ResponseType.MULTIPLE_CHOICE))
        )
        assertEquals(
            LearningCueStage.STRUCTURED_RECONSTRUCTION,
            learningCueStage(activity(ResponseType.REORDER))
        )
        assertEquals(
            LearningCueStage.REDUCED_CUE_RETRIEVAL,
            learningCueStage(activity(ResponseType.FILL_IN))
        )
    }

    @Test
    fun formatsWithoutDedicatedEvaluatorsStayUnclassified() {
        listOf(
            ResponseType.FREE_TEXT,
            ResponseType.LISTEN_AND_RESPOND,
            ResponseType.SPEAK
        ).forEach { responseType ->
            assertNull(learningCueStage(activity(responseType)))
        }
    }

    private fun activity(responseType: ResponseType): LearningActivity =
        LearningActivity(
            id = "en-a1-cue-${responseType.name.lowercase()}",
            level = CefrLevel.A1,
            primarySkill = LearningSkill.VOCABULARY,
            learningObjective = "Retrieve a known expression with controlled support",
            knowledgeTarget = "greeting",
            responseType = responseType,
            prompt = "Complete the greeting",
            feedback = "Use the familiar greeting.",
            reviewKey = "en-a1-greeting",
            acceptedAnswers = listOf("Hello"),
            responseOptions = when (responseType) {
                ResponseType.MULTIPLE_CHOICE -> listOf("Hello", "Goodbye")
                ResponseType.REORDER -> listOf("Hel", "lo")
                else -> emptyList()
            },
            audioPromptId = if (responseType == ResponseType.LISTEN_AND_RESPOND) {
                "en-a1-greeting-audio"
            } else {
                null
            }
        )
}
