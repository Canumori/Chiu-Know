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
    fun otherSupportedTargetsRemainOnLegacyFoundationUntilEquivalentBankExists() {
        supportedTargetLanguages
            .map { it.code }
            .filterNot { it == "en" }
            .forEach { languageCode ->
                val selection = placementRuntimeSelection(languageCode)

                assertEquals(PlacementRuntimeMode.LEGACY_FOUNDATION, selection.mode)
                assertEquals(starterPlacementQuestionsFor(languageCode), selection.questions)
                assertFalse(isQualityPlacementEnabled(languageCode))
            }
    }

    @Test
    fun runtimeSelectionNeverCrossesLanguageBanks() {
        supportedTargetLanguages.forEach { language ->
            val selection = placementRuntimeSelection(language.code)

            assertEquals(language.code, selection.languageCode)
            assertTrue(selection.questions.isNotEmpty())
            if (language.code != "en") {
                val englishIds = qualityEnglishPlacementQuestions.map { it.id }.toSet()
                assertTrue(selection.questions.none { it.id in englishIds })
            }
        }
    }
}
