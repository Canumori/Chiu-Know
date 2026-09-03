package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PlacementBankQualityTest {

    private val banks = mapOf(
        "en" to starterEnglishPlacementQuestions,
        "pt" to starterPortuguesePlacementQuestions,
        "es" to starterSpanishPlacementQuestions,
        "fr" to starterFrenchPlacementQuestions,
        "ko" to starterKoreanPlacementQuestions
    )

    @Test
    fun everyCurrentLanguageBankIsStructurallyValidAcrossA1ToC2() {
        banks.forEach { (languageCode, questions) ->
            val report = analyzePlacementBank(questions)

            assertTrue("Placement bank $languageCode is structurally invalid: $report", report.isStructurallyValid)
            CefrLevel.entries.forEach { level ->
                assertTrue(
                    "Placement bank $languageCode has no question for $level",
                    (report.questionsPerLevel[level] ?: 0) > 0
                )
            }
        }
    }

    @Test
    fun malformedQuestionIsReportedInsteadOfBeingSilentlyAccepted() {
        val malformed = PlacementQuestion(
            id = "bad",
            level = CefrLevel.B1,
            prompt = "Question",
            options = listOf("same", "same"),
            correctIndex = 4
        )

        val report = analyzePlacementBank(listOf(malformed))

        assertFalse(report.isStructurallyValid)
        assertEquals(setOf("bad"), report.malformedQuestionIds)
    }

    @Test
    fun duplicateIdsAndPromptsAreReported() {
        val questions = listOf(
            PlacementQuestion("dup", CefrLevel.A1, "Same prompt", listOf("a", "b"), 0),
            PlacementQuestion("dup", CefrLevel.A2, " same prompt ", listOf("a", "b"), 1)
        )

        val report = analyzePlacementBank(questions)

        assertEquals(setOf("dup"), report.duplicateIds)
        assertEquals(setOf("same prompt"), report.duplicatePrompts)
        assertFalse(report.isStructurallyValid)
    }
}
