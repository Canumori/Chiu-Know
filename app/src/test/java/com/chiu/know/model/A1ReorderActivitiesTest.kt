package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class A1ReorderActivitiesTest {

    @Test
    fun providesOneDeterministicReorderTransferPerSupportedLanguage() {
        supportedTargetLanguages.forEach { language ->
            val activities = a1ReorderActivitiesFor(language.code)
            assertEquals(1, activities.size)

            val activity = activities.single()
            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.GRAMMAR, activity.primarySkill)
            assertEquals(ResponseType.REORDER, activity.responseType)
            assertTrue(activity.responseOptions.size >= 2)

            val grammarReviewKeys = a1IntegratedGrammarActivitiesFor(language.code)
                .map { it.reviewKey }
                .toSet()
            assertTrue(activity.reviewKey in grammarReviewKeys)

            assertTrue(isLearningAnswerCorrect(activity, activity.acceptedAnswers.single()))
            assertFalse(isLearningAnswerCorrect(activity, activity.responseOptions.joinToString(" ")))
        }
    }

    @Test
    fun responseTokensCanReconstructAcceptedAnswerExactly() {
        a1ReorderActivitiesFor("en").single().let { activity ->
            val reconstructed = listOf("I", "am", "Mia").joinToString(" ")
            assertEquals(activity.acceptedAnswers.single(), reconstructed)
            assertTrue(isLearningAnswerCorrect(activity, reconstructed))
        }

        a1ReorderActivitiesFor("ko").single().let { activity ->
            val reconstructed = listOf("저는", "미아예요").joinToString(" ")
            assertEquals(activity.acceptedAnswers.single(), reconstructed)
            assertTrue(isLearningAnswerCorrect(activity, reconstructed))
        }
    }
}
