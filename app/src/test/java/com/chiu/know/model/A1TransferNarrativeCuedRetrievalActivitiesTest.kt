package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1TransferNarrativeCuedRetrievalActivitiesTest {

    @Test
    fun providesOneClosedCuedRetrievalActivityPerSupportedLanguage() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val activities = a1TransferNarrativeCuedRetrievalActivitiesFor(languageCode)

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
    fun acceptedResidenceReplyIsGroundedInTransferNarrative() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val narrative = a1TransferNarrativeMicroUnitFor(languageCode)!!
            val activity = a1TransferNarrativeCuedRetrievalActivitiesFor(languageCode).single()
            val accepted = activity.acceptedAnswers.single()

            assertTrue(narrative.beats.any { it.speaker == "Chiu" && it.text == accepted })
        }
    }

    @Test
    fun cuedRetrievalRemainsOutsideStarterReviewQueue() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val cuedKeys = a1TransferNarrativeCuedRetrievalActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()
            val starterKeys = starterLearningActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()

            assertTrue(cuedKeys.intersect(starterKeys).isEmpty())
        }
    }

    @Test
    fun koreanCuedRetrievalMatchesReviewedParkDialogue() {
        val activity = a1TransferNarrativeCuedRetrievalActivitiesFor("ko").single()

        assertTrue(activity.prompt.contains("어디에 살아요?"))
        assertEquals(listOf("리우에 살아요."), activity.acceptedAnswers)
        assertEquals(listOf("리우에 살아요.", "책을 좋아해요."), activity.responseOptions)
        assertTrue(activity.feedback.contains("리우에 살아요."))
    }
}
