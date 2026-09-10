package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class FrenchPlacementQualityBankTest {

    @Test
    fun hasFourQuestionsForEveryCefrLevel() {
        CefrLevel.entries.forEach { level ->
            val questions = placementQuestionsForLevel(qualityFrenchPlacementQuestions, level)
            assertEquals("Expected four French questions for $level", 4, questions.size)
        }
    }

    @Test
    fun allQuestionIdsAndPromptsAreUnique() {
        val ids = qualityFrenchPlacementQuestions.map { it.id }
        val prompts = qualityFrenchPlacementQuestions.map { it.prompt.trim().lowercase() }

        assertEquals(ids.size, ids.toSet().size)
        assertEquals(prompts.size, prompts.toSet().size)
    }

    @Test
    fun everyQuestionHasFourDistinctNonBlankOptionsAndValidAnswer() {
        qualityFrenchPlacementQuestions.forEach { question ->
            assertFalse("Blank prompt in ${question.id}", question.prompt.isBlank())
            assertEquals("Expected four options in ${question.id}", 4, question.options.size)
            assertTrue("Invalid correctIndex in ${question.id}", question.correctIndex in question.options.indices)
            assertTrue("Blank option in ${question.id}", question.options.none { it.isBlank() })

            val normalizedOptions = question.options.map { it.trim().lowercase() }
            assertEquals(
                "Duplicate option text in ${question.id}",
                normalizedOptions.size,
                normalizedOptions.toSet().size
            )
        }
    }

    @Test
    fun idsMatchDeclaredCefrLevel() {
        qualityFrenchPlacementQuestions.forEach { question ->
            val expectedMarker = "-${question.level.name.lowercase()}-"
            assertTrue(
                "Question ${question.id} does not match declared level ${question.level}",
                question.id.contains(expectedMarker)
            )
        }
    }

    @Test
    fun expansionDoesNotReplaceStarterItems() {
        assertTrue(qualityFrenchPlacementQuestions.containsAll(starterFrenchPlacementQuestions))
        assertTrue(qualityFrenchPlacementQuestions.containsAll(additionalFrenchPlacementQuestions))
        assertEquals(
            starterFrenchPlacementQuestions.size + additionalFrenchPlacementQuestions.size,
            qualityFrenchPlacementQuestions.size
        )
    }
}
