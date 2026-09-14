package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class A1NarrativeSpeakerTrackingActivitiesTest {

    @Test
    fun providesOneA1SpeakerTrackingCheckPerSupportedLanguage() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val activities = a1NarrativeSpeakerTrackingActivitiesFor(languageCode)

            assertEquals(1, activities.size)
            val activity = activities.single()
            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.READING, activity.primarySkill)
            assertEquals(ResponseType.MULTIPLE_CHOICE, activity.responseType)
            assertEquals(2, activity.responseOptions.size)
            assertEquals(activity.responseOptions.distinct().size, activity.responseOptions.size)
            assertTrue(activity.acceptedAnswers.all { it in activity.responseOptions })
        }
    }

    @Test
    fun speakerTrackingAnswersAreGroundedInTheExistingNarrative() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val narrative = a1FirstNarrativeMicroUnitFor(languageCode)
            val activity = a1NarrativeSpeakerTrackingActivitiesFor(languageCode).single()

            assertNotNull(narrative)
            narrative!!
            val acceptedSpeaker = activity.acceptedAnswers.single()
            assertTrue(narrative.beats.any { beat ->
                beat.speaker.equals(acceptedSpeaker, ignoreCase = true) ||
                    beat.text.contains(acceptedSpeaker, ignoreCase = true)
            })
        }
    }

    @Test
    fun speakerTrackingRemainsOutsideStarterReviewQueue() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val narrativeKeys = a1NarrativeSpeakerTrackingActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()
            val starterKeys = starterLearningActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()

            assertTrue(narrativeKeys.intersect(starterKeys).isEmpty())
        }
    }
}
