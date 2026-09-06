package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1TransferNarrativePreferenceFillInRetrievalActivitiesTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun providesOneClosedA1PreferenceFillInRetrievalPerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val activities = a1TransferNarrativePreferenceFillInRetrievalActivitiesFor(languageCode)
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
    fun preferenceFillInRetrievalRemainsOutsideStarterReviewQueue() {
        supportedLanguages.forEach { languageCode ->
            val starterKeys = starterLearningActivitiesFor(languageCode).map { it.reviewKey }.toSet()
            val transferKeys = a1TransferNarrativePreferenceFillInRetrievalActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()
            assertTrue(transferKeys.intersect(starterKeys).isEmpty())
        }
    }

    @Test
    fun koreanPreferenceFillInMatchesReviewedParkDialogue() {
        val activity = a1TransferNarrativePreferenceFillInRetrievalActivitiesFor("ko").single()
        assertTrue(activity.prompt.contains("무엇을 좋아해요?"))
        assertTrue(activity.prompt.contains("___ 좋아해요"))
        assertEquals(listOf("책을"), activity.acceptedAnswers)
        assertTrue(activity.feedback.contains("책을 좋아해요."))
    }
}
