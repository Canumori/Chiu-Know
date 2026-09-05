package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1TransferNarrativePreferenceCuedRetrievalActivitiesTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun providesOneClosedA1PreferenceCuedRetrievalPerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val activities = a1TransferNarrativePreferenceCuedRetrievalActivitiesFor(languageCode)
            assertEquals(1, activities.size)

            val activity = activities.single()
            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.READING, activity.primarySkill)
            assertEquals(ResponseType.MULTIPLE_CHOICE, activity.responseType)
            assertEquals(1, activity.acceptedAnswers.size)
            assertEquals(2, activity.responseOptions.size)
            assertEquals(2, activity.responseOptions.distinct().size)
            assertTrue(activity.acceptedAnswers.single() in activity.responseOptions)
        }
    }

    @Test
    fun acceptedPreferenceAnswerIsGroundedInTransferNarrative() {
        supportedLanguages.forEach { languageCode ->
            val narrative = a1TransferNarrativeMicroUnitFor(languageCode)!!
            val activity = a1TransferNarrativePreferenceCuedRetrievalActivitiesFor(languageCode).single()
            assertTrue(narrative.beats.any { it.text == activity.acceptedAnswers.single() })
        }
    }

    @Test
    fun preferenceCuedRetrievalRemainsOutsideStarterReviewQueue() {
        supportedLanguages.forEach { languageCode ->
            val starterKeys = starterLearningActivitiesFor(languageCode).map { it.reviewKey }.toSet()
            val transferKeys = a1TransferNarrativePreferenceCuedRetrievalActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()
            assertTrue(transferKeys.intersect(starterKeys).isEmpty())
        }
    }

    @Test
    fun koreanPreferenceCuedRetrievalMatchesReviewedParkDialogue() {
        val activity = a1TransferNarrativePreferenceCuedRetrievalActivitiesFor("ko").single()
        assertTrue(activity.prompt.contains("무엇을 좋아해요?"))
        assertEquals(listOf("책을 좋아해요."), activity.acceptedAnswers)
        assertEquals(listOf("책을 좋아해요.", "리우에 살아요."), activity.responseOptions)
    }
}
