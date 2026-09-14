package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class A1ComprehensionRepairActivitiesTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun exposesOneControlledA1ComprehensionRepairPhrasePerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val activities = a1ComprehensionRepairActivitiesFor(languageCode)

            assertEquals(1, activities.size)
            val activity = activities.single()
            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.VOCABULARY, activity.primarySkill)
            assertEquals(ResponseType.REORDER, activity.responseType)
            assertTrue(activity.id.startsWith("$languageCode-a1-comprehension-repair-"))
            assertTrue(activity.reviewKey.startsWith("$languageCode:a1:vocabulary:comprehension-repair"))
            assertTrue(activity.audioPromptId == null)
        }
    }

    @Test
    fun visibleTokensReconstructTheAcceptedRepairPhrase() {
        supportedLanguages.forEach { languageCode ->
            val activity = a1ComprehensionRepairActivitiesFor(languageCode).single()
            val reconstructed = activity.responseOptions.joinToString(" ")

            assertTrue(isLearningAnswerCorrect(activity, reconstructed))
        }
    }

    @Test
    fun reversingTokensDoesNotAccidentallyPass() {
        supportedLanguages.forEach { languageCode ->
            val activity = a1ComprehensionRepairActivitiesFor(languageCode).single()
            val reversed = activity.responseOptions.reversed().joinToString(" ")

            assertFalse(isLearningAnswerCorrect(activity, reversed))
        }
    }

    @Test
    fun starterBankIncludesComprehensionRepairForEverySupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val expectedId = a1ComprehensionRepairActivitiesFor(languageCode).single().id
            val starterIds = starterLearningActivitiesFor(languageCode).map { it.id }

            assertTrue(starterIds.contains(expectedId))
        }
    }

    @Test
    fun unsupportedLanguageDoesNotExposeRepairContent() {
        assertTrue(a1ComprehensionRepairActivitiesFor("de").isEmpty())
    }
}
