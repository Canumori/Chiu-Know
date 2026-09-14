package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class A1FamilyIdentificationActivitiesTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun exposesOneControlledA1FamilyIdentificationPerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val activities = a1FamilyIdentificationActivitiesFor(languageCode)

            assertEquals(1, activities.size)
            val activity = activities.single()
            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.VOCABULARY, activity.primarySkill)
            assertEquals(ResponseType.REORDER, activity.responseType)
            assertTrue(activity.id.startsWith("$languageCode-a1-family-identification-"))
            assertTrue(activity.reviewKey.startsWith("$languageCode:a1:vocabulary:family-identification"))
            assertTrue(activity.audioPromptId == null)
        }
    }

    @Test
    fun visibleTokensReconstructTheAcceptedFamilySentence() {
        supportedLanguages.forEach { languageCode ->
            val activity = a1FamilyIdentificationActivitiesFor(languageCode).single()
            val reconstructed = activity.responseOptions.joinToString(" ")

            assertTrue(isLearningAnswerCorrect(activity, reconstructed))
        }
    }

    @Test
    fun reversingTokensDoesNotAccidentallyPass() {
        supportedLanguages.forEach { languageCode ->
            val activity = a1FamilyIdentificationActivitiesFor(languageCode).single()
            val reversed = activity.responseOptions.reversed().joinToString(" ")

            assertFalse(isLearningAnswerCorrect(activity, reversed))
        }
    }

    @Test
    fun unsupportedLanguageDoesNotExposeFamilyContent() {
        assertTrue(a1FamilyIdentificationActivitiesFor("de").isEmpty())
    }
}
