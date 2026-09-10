package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1TransferNarrativeFillInRetrievalActivitiesTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun providesOneClosedA1FillInRetrievalPerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val activities = a1TransferNarrativeFillInRetrievalActivitiesFor(languageCode)
            assertEquals(1, activities.size)

            val activity = activities.single()
            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.READING, activity.primarySkill)
            assertEquals(ResponseType.FILL_IN, activity.responseType)
            assertEquals(1, activity.acceptedAnswers.size)
            assertTrue(activity.acceptedAnswers.single().isNotBlank())
            assertTrue(activity.responseOptions.isEmpty())
        }
    }

    @Test
    fun fillInRetrievalRemainsOutsideStarterReviewQueue() {
        supportedLanguages.forEach { languageCode ->
            val starterKeys = starterLearningActivitiesFor(languageCode).map { it.reviewKey }.toSet()
            val transferKeys = a1TransferNarrativeFillInRetrievalActivitiesFor(languageCode).map { it.reviewKey }.toSet()
            assertTrue(transferKeys.intersect(starterKeys).isEmpty())
        }
    }

    @Test
    fun koreanFillInMatchesReviewedResidenceResponse() {
        val activity = a1TransferNarrativeFillInRetrievalActivitiesFor("ko").single()
        assertTrue(activity.prompt.contains("어디에 살아요?"))
        assertTrue(activity.prompt.contains("리우에 ___"))
        assertEquals(listOf("살아요"), activity.acceptedAnswers)
        assertTrue(activity.feedback.contains("리우에 살아요."))
    }
}
