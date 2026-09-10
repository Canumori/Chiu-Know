package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1TransferNarrativeReorderRetrievalActivitiesTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun providesOneClosedA1ReorderRetrievalPerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val activities = a1TransferNarrativeReorderRetrievalActivitiesFor(languageCode)
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
    fun reorderTokensReconstructTheAcceptedAnswerExactly() {
        supportedLanguages.forEach { languageCode ->
            val activity = a1TransferNarrativeReorderRetrievalActivitiesFor(languageCode).single()
            val accepted = activity.acceptedAnswers.single()

            val acceptedTokens = accepted.split(" ")
            assertEquals(
                acceptedTokens.sorted(),
                activity.responseOptions.sorted()
            )
        }
    }

    @Test
    fun reorderRetrievalRemainsOutsideStarterReviewQueue() {
        supportedLanguages.forEach { languageCode ->
            val starterKeys = starterLearningActivitiesFor(languageCode).map { it.reviewKey }.toSet()
            val transferKeys = a1TransferNarrativeReorderRetrievalActivitiesFor(languageCode).map { it.reviewKey }.toSet()
            assertTrue(transferKeys.intersect(starterKeys).isEmpty())
        }
    }

    @Test
    fun koreanReorderMatchesReviewedResidenceResponse() {
        val activity = a1TransferNarrativeReorderRetrievalActivitiesFor("ko").single()
        assertEquals(listOf("리우에 살아요."), activity.acceptedAnswers)
        assertEquals(listOf("살아요.", "리우에"), activity.responseOptions)
    }
}
