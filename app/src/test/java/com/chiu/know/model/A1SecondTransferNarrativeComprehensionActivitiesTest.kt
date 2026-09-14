package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1SecondTransferNarrativeComprehensionActivitiesTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun providesOneClosedA1ReadingComprehensionPerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val activities = a1SecondTransferNarrativeComprehensionActivitiesFor(languageCode)
            assertEquals(1, activities.size)

            val activity = activities.single()
            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.READING, activity.primarySkill)
            assertEquals(ResponseType.MULTIPLE_CHOICE, activity.responseType)
            assertEquals(1, activity.acceptedAnswers.size)
            assertTrue(activity.acceptedAnswers.single() in activity.responseOptions)
            assertEquals(2, activity.responseOptions.distinct().size)
        }
    }

    @Test
    fun comprehensionRemainsOutsideStarterReviewQueue() {
        supportedLanguages.forEach { languageCode ->
            val starterKeys = starterLearningActivitiesFor(languageCode).map { it.reviewKey }.toSet()
            val transferKeys = a1SecondTransferNarrativeComprehensionActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()
            assertTrue(transferKeys.intersect(starterKeys).isEmpty())
        }
    }

    @Test
    fun comprehensionIsGroundedInChiusPreferenceBeat() {
        val expected = mapOf(
            "en" to ("coffee" to "I like coffee."),
            "pt" to ("café" to "Eu gosto de café."),
            "es" to ("café" to "Me gusta el café."),
            "fr" to ("café" to "J’aime le café."),
            "ko" to ("커피" to "커피를 좋아해요.")
        )

        supportedLanguages.forEach { languageCode ->
            val (answer, fullSentence) = expected.getValue(languageCode)
            val narrative = a1SecondTransferNarrativeMicroUnitFor(languageCode)!!
            val activity = a1SecondTransferNarrativeComprehensionActivitiesFor(languageCode).single()

            assertEquals(listOf(answer), activity.acceptedAnswers)
            assertTrue(narrative.beats.any { it.speaker == "Chiu" && it.text == fullSentence })
            assertTrue(activity.feedback.contains(fullSentence))
        }
    }

    @Test
    fun koreanComprehensionUsesReviewedPreferenceForm() {
        val activity = a1SecondTransferNarrativeComprehensionActivitiesFor("ko").single()
        assertTrue(activity.prompt.contains("무엇을 좋아"))
        assertEquals(listOf("커피"), activity.acceptedAnswers)
        assertEquals(listOf("커피", "책"), activity.responseOptions)
        assertTrue(activity.feedback.contains("커피를 좋아해요."))
    }
}
