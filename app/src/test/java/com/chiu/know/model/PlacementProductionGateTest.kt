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
    fun frenchUsesQualitySessionWithExpandedValidatedBank() {
        val selection = placementRuntimeSelection("fr")

        assertEquals(PlacementRuntimeMode.QUALITY_SESSION, selection.mode)
        assertEquals(qualityFrenchPlacementQuestions, selection.questions)
        assertEquals(24, selection.questions.size)
        CefrLevel.entries.forEach { level ->
            assertEquals(4, selection.questions.count { it.level == level })
        }
        assertTrue(isQualityPlacementEnabled("fr"))
    }

    @Test
    fun koreanStaysOnLegacyFoundationUntilEquivalentBankExists() {
        val selection = placementRuntimeSelection("ko")

        assertEquals(PlacementRuntimeMode.LEGACY_FOUNDATION, selection.mode)
        assertEquals(starterKoreanPlacementQuestions, selection.questions)
        assertFalse(isQualityPlacementEnabled("ko"))
    }

    @Test
    fun runtimeSelectionNeverCrossesLanguageBanks() {
        val englishIds = qualityEnglishPlacementQuestions.map { it.id }.toSet()
        val portugueseIds = qualityPortuguesePlacementQuestions.map { it.id }.toSet()
        val spanishIds = qualitySpanishPlacementQuestions.map { it.id }.toSet()
        val frenchIds = qualityFrenchPlacementQuestions.map { it.id }.toSet()
        val qualityIdsByLanguage = mapOf(
            "en" to englishIds,
            "pt" to portugueseIds,
            "es" to spanishIds,
            "fr" to frenchIds
        )
        val allQualityIds = qualityIdsByLanguage.values.flatten().toSet()

        supportedTargetLanguages.forEach { language ->
            val selection = placementRuntimeSelection(language.code)

            assertEquals(language.code, selection.languageCode)
            assertTrue(selection.questions.isNotEmpty())

            val foreignQualityIds = allQualityIds - qualityIdsByLanguage[language.code].orEmpty()
            assertTrue(selection.questions.none { it.id in foreignQualityIds })
        }
    }
}
