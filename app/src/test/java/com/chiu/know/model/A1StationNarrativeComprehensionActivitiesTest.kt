package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1StationNarrativeComprehensionActivitiesTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun providesOneClosedReadingCheckPerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val activity = a1StationNarrativeComprehensionActivitiesFor(languageCode).single()

            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.READING, activity.primarySkill)
            assertEquals(ResponseType.MULTIPLE_CHOICE, activity.responseType)
            assertEquals(1, activity.acceptedAnswers.size)
            assertTrue(activity.acceptedAnswers.single() in activity.responseOptions)
            assertEquals(2, activity.responseOptions.distinct().size)
        }
    }

    @Test
    fun acceptedAnswerMatchesMiasRepairBeatExactly() {
        val expected = mapOf(
            "en" to "I don't understand.",
            "pt" to "Eu não entendo.",
            "es" to "No entiendo.",
            "fr" to "Je ne comprends pas.",
            "ko" to "이해가 안 돼요."
        )

        expected.forEach { (languageCode, repairPhrase) ->
            val narrative = a1StationNarrativeMicroUnitFor(languageCode)!!
            val activity = a1StationNarrativeComprehensionActivitiesFor(languageCode).single()

            assertEquals(listOf(repairPhrase), activity.acceptedAnswers)
            assertTrue(narrative.beats.any { it.speaker == "Mia" && it.text == repairPhrase })
            assertTrue(activity.feedback.contains(repairPhrase))
        }
    }

    @Test
    fun locationQuestionIsARealDistractorFromTheSameStory() {
        supportedLanguages.forEach { languageCode ->
            val narrative = a1StationNarrativeMicroUnitFor(languageCode)!!
            val activity = a1StationNarrativeComprehensionActivitiesFor(languageCode).single()
            val locationQuestion = narrative.beats.first().text

            assertTrue(locationQuestion in activity.responseOptions)
            assertTrue(locationQuestion !in activity.acceptedAnswers)
        }
    }

    @Test
    fun comprehensionRemainsOutsideStarterReviewQueue() {
        supportedLanguages.forEach { languageCode ->
            val starterKeys = starterLearningActivitiesFor(languageCode).map { it.reviewKey }.toSet()
            val stationKeys = a1StationNarrativeComprehensionActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()

            assertTrue(stationKeys.intersect(starterKeys).isEmpty())
        }
        assertTrue(a1StationNarrativeComprehensionActivitiesFor("de").isEmpty())
    }
}
