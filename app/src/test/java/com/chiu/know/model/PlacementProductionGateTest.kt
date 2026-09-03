package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PlacementProductionGateTest {

    @Test
    fun englishUsesQualitySessionWithExpandedValidatedBank() {
        val selection = placementRuntimeSelection("en")

        assertEquals(PlacementRuntimeMode.QUALITY_SESSION, selection.mode)
        assertEquals(qualityEnglishPlacementQuestions, selection.questions)
        assertEquals(24, selection.questions.size)
        CefrLevel.entries.forEach { level ->
            assertEquals(4, selection.questions.count { it.level == level })
        }
        assertTrue(isQualityPlacementEnabled("en"))
    }

    @Test
    fun portugueseUsesQualitySessionWithExpandedValidatedBank() {
        val selection = placementRuntimeSelection("pt")

        assertEquals(PlacementRuntimeMode.QUALITY_SESSION, selection.mode)
        assertEquals(qualityPortuguesePlacementQuestions, selection.questions)
        assertEquals(24, selection.questions.size)
        CefrLevel.entries.forEach { level ->
            assertEquals(4, selection.questions.count { it.level == level })
        }
        assertTrue(isQualityPlacementEnabled("pt"))
    }

    @Test
    fun spanishUsesQualitySessionWithExpandedValidatedBank() {
        val selection = placementRuntimeSelection("es")

        assertEquals(PlacementRuntimeMode.QUALITY_SESSION, selection.mode)
        assertEquals(qualitySpanishPlacementQuestions, selection.questions)
        assertEquals(24, selection.questions.size)
        CefrLevel.entries.forEach { level ->
            assertEquals(4, selection.questions.count { it.level == level })
        }
        assertTrue(isQualityPlacementEnabled("es"))
    }

    @Test
    fun remainingSupportedTargetsStayOnLegacyFoundationUntilEquivalentBankExists() {
        supportedTargetLanguages
            .map { it.code }
            .filterNot { it in setOf("en", "pt", "es") }
            .forEach { languageCode ->
                val selection = placementRuntimeSelection(languageCode)

                assertEquals(PlacementRuntimeMode.LEGACY_FOUNDATION, selection.mode)
                assertEquals(starterPlacementQuestionsFor(languageCode), selection.questions)
                assertFalse(isQualityPlacementEnabled(languageCode))
            }
    }

    @Test
    fun runtimeSelectionNeverCrossesLanguageBanks() {
        val englishIds = qualityEnglishPlacementQuestions.map { it.id }.toSet()
        val portugueseIds = qualityPortuguesePlacementQuestions.map { it.id }.toSet()
        val spanishIds = qualitySpanishPlacementQuestions.map { it.id }.toSet()

        supportedTargetLanguages.forEach { language ->
            val selection = placementRuntimeSelection(language.code)

            assertEquals(language.code, selection.languageCode)
            assertTrue(selection.questions.isNotEmpty())

            when (language.code) {
                "en" -> assertTrue(selection.questions.none { it.id in portugueseIds || it.id in spanishIds })
                "pt" -> assertTrue(selection.questions.none { it.id in englishIds || it.id in spanishIds })
                "es" -> assertTrue(selection.questions.none { it.id in englishIds || it.id in portugueseIds })
                else -> assertTrue(selection.questions.none { it.id in englishIds || it.id in portugueseIds || it.id in spanishIds })
            }
        }
    }
}
