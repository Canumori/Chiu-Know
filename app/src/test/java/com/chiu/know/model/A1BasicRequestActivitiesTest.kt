package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class A1BasicRequestActivitiesTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun exposesOneControlledA1RequestPerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val activities = a1BasicRequestActivitiesFor(languageCode)

            assertEquals(1, activities.size)
            val activity = activities.single()
            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.VOCABULARY, activity.primarySkill)
            assertEquals(ResponseType.REORDER, activity.responseType)
            assertTrue(activity.id.startsWith("$languageCode-a1-basic-request-water-"))
            assertTrue(activity.reviewKey.startsWith("$languageCode:a1:vocabulary:basic-request:water"))
            assertTrue(activity.audioPromptId == null)
        }
    }

    @Test
    fun starterBankIncludesTheBasicRequestForEverySupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val expectedId = a1BasicRequestActivitiesFor(languageCode).single().id
            val starterIds = starterLearningActivitiesFor(languageCode).map { it.id }

            assertTrue(starterIds.contains(expectedId))
        }
    }

    @Test
    fun visibleTokensReconstructTheAcceptedRequest() {
        supportedLanguages.forEach { languageCode ->
            val activity = a1BasicRequestActivitiesFor(languageCode).single()
            val reconstructed = activity.responseOptions.joinToString(" ")

            assertTrue(isLearningAnswerCorrect(activity, reconstructed))
        }
    }

    @Test
    fun reversingTokensDoesNotAccidentallyPass() {
        supportedLanguages.forEach { languageCode ->
            val activity = a1BasicRequestActivitiesFor(languageCode).single()
            val reversed = activity.responseOptions.reversed().joinToString(" ")

            assertFalse(isLearningAnswerCorrect(activity, reversed))
        }
    }

    @Test
    fun unsupportedLanguageDoesNotExposeRequestContent() {
        assertTrue(a1BasicRequestActivitiesFor("de").isEmpty())
    }
}
