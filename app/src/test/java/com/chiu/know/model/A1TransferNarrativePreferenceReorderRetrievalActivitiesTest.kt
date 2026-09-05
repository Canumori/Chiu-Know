package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1TransferNarrativePreferenceReorderRetrievalActivitiesTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun providesOneClosedA1PreferenceReorderRetrievalPerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val activities = a1TransferNarrativePreferenceReorderRetrievalActivitiesFor(languageCode)
            assertEquals(1, activities.size)

            val activity = activities.single()
            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.READING, activity.primarySkill)
            assertEquals(ResponseType.REORDER, activity.responseType)
            assertEquals(1, activity.acceptedAnswers.size)
            assertTrue(activity.responseOptions.size >= 2)
            assertEquals(activity.responseOptions.size, activity.responseOptions.distinct().size)
        }
    }

    @Test
    fun reorderTokensReconstructTheAcceptedPreferenceAnswerExactly() {
        supportedLanguages.forEach { languageCode ->
            val activity = a1TransferNarrativePreferenceReorderRetrievalActivitiesFor(languageCode).single()
            val accepted = activity.acceptedAnswers.single()

            val acceptedTokens = accepted.split(" ")
            assertEquals(
                acceptedTokens.sorted(),
                activity.responseOptions.sorted()
            )
        }
    }

    @Test
    fun preferenceReorderRetrievalRemainsOutsideStarterReviewQueue() {
        supportedLanguages.forEach { languageCode ->
            val starterKeys = starterLearningActivitiesFor(languageCode).map { it.reviewKey }.toSet()
            val transferKeys = a1TransferNarrativePreferenceReorderRetrievalActivitiesFor(languageCode).map { it.reviewKey }.toSet()
            assertTrue(transferKeys.intersect(starterKeys).isEmpty())
        }
    }

    @Test
    fun koreanPreferenceReorderMatchesReviewedParkResponse() {
        val activity = a1TransferNarrativePreferenceReorderRetrievalActivitiesFor("ko").single()
        assertTrue(activity.prompt.contains("무엇을 좋아해요?"))
        assertEquals(listOf("책을 좋아해요."), activity.acceptedAnswers)
        assertEquals(listOf("좋아해요.", "책을"), activity.responseOptions)
        assertTrue(activity.feedback.contains("책을 좋아해요."))
    }
}
