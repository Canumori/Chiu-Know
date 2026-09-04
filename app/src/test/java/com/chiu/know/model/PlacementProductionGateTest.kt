package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PlacementProductionGateTest {

    @Test
    fun englishUsesQualitySessionWithExpandedValidatedBank() {
        val selection = placementRuntimeSelection("en")
        assertEquals(PlacementRuntimeMode.QUALITY_SESSION, selection.mode)
        assertEquals(qualityEnglishPlacementQuestions, selection.questions)
        assertEquals(24, selection.questions.size)
        CefrLevel.entries.forEach { level -> assertEquals(4, selection.questions.count { it.level == level }) }
        assertTrue(isQualityPlacementEnabled("en"))
    }

    @Test
    fun portugueseUsesQualitySessionWithExpandedValidatedBank() {
        val selection = placementRuntimeSelection("pt")
        assertEquals(PlacementRuntimeMode.QUALITY_SESSION, selection.mode)
        assertEquals(qualityPortuguesePlacementQuestions, selection.questions)
        assertEquals(24, selection.questions.size)
        CefrLevel.entries.forEach { level -> assertEquals(4, selection.questions.count { it.level == level }) }
        assertTrue(isQualityPlacementEnabled("pt"))
    }

    @Test
    fun spanishUsesQualitySessionWithExpandedValidatedBank() {
        val selection = placementRuntimeSelection("es")
        assertEquals(PlacementRuntimeMode.QUALITY_SESSION, selection.mode)
        assertEquals(qualitySpanishPlacementQuestions, selection.questions)
        assertEquals(24, selection.questions.size)
        CefrLevel.entries.forEach { level -> assertEquals(4, selection.questions.count { it.level == level }) }
        assertTrue(isQualityPlacementEnabled("es"))
    }

    @Test
    fun frenchUsesQualitySessionWithExpandedValidatedBank() {
        val selection = placementRuntimeSelection("fr")
        assertEquals(PlacementRuntimeMode.QUALITY_SESSION, selection.mode)
        assertEquals(qualityFrenchPlacementQuestions, selection.questions)
        assertEquals(24, selection.questions.size)
        CefrLevel.entries.forEach { level -> assertEquals(4, selection.questions.count { it.level == level }) }
        assertTrue(isQualityPlacementEnabled("fr"))
    }

    @Test
    fun koreanUsesQualitySessionWithReviewedCandidateBank() {
        val selection = placementRuntimeSelection("ko")
        assertEquals(PlacementRuntimeMode.QUALITY_SESSION, selection.mode)
        assertEquals(candidateKoreanPlacementQuestions, selection.questions)
        assertEquals(24, selection.questions.size)
        CefrLevel.entries.forEach { level -> assertEquals(4, selection.questions.count { it.level == level }) }
        assertTrue(isQualityPlacementEnabled("ko"))
    }

    @Test
    fun runtimeSelectionNeverCrossesLanguageBanks() {
        val idsByLanguage = mapOf(
            "en" to qualityEnglishPlacementQuestions.map { it.id }.toSet(),
            "pt" to qualityPortuguesePlacementQuestions.map { it.id }.toSet(),
            "es" to qualitySpanishPlacementQuestions.map { it.id }.toSet(),
            "fr" to qualityFrenchPlacementQuestions.map { it.id }.toSet(),
            "ko" to candidateKoreanPlacementQuestions.map { it.id }.toSet()
        )
        val allQualityIds = idsByLanguage.values.flatten().toSet()

        supportedTargetLanguages.forEach { language ->
            val selection = placementRuntimeSelection(language.code)
            assertEquals(language.code, selection.languageCode)
            assertTrue(selection.questions.isNotEmpty())
            val foreignQualityIds = allQualityIds - idsByLanguage[language.code].orEmpty()
            assertTrue(selection.questions.none { it.id in foreignQualityIds })
        }
    }
}
