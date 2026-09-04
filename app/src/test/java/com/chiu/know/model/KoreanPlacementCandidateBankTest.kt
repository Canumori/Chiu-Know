package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class KoreanPlacementCandidateBankTest {

    @Test
    fun hasFourQuestionsForEveryCefrLabel() {
        CefrLevel.entries.forEach { level ->
            assertEquals(
                "Expected four candidate Korean questions for $level",
                4,
                placementQuestionsForLevel(candidateKoreanPlacementQuestions, level).size
            )
        }
    }

    @Test
    fun candidateQuestionIdsAndPromptsAreUnique() {
        val ids = candidateKoreanPlacementQuestions.map { it.id }
        val prompts = candidateKoreanPlacementQuestions.map { it.prompt.trim() }

        assertEquals(ids.size, ids.toSet().size)
        assertEquals(prompts.size, prompts.toSet().size)
    }

    @Test
    fun everyCandidateQuestionIsStructurallyValid() {
        candidateKoreanPlacementQuestions.forEach { question ->
            assertFalse("Blank prompt in ${question.id}", question.prompt.isBlank())
            assertEquals("Expected four options in ${question.id}", 4, question.options.size)
            assertTrue("Invalid correctIndex in ${question.id}", question.correctIndex in question.options.indices)
            assertTrue("Blank option in ${question.id}", question.options.none { it.isBlank() })
            assertEquals(
                "Duplicate option text in ${question.id}",
                question.options.size,
                question.options.map { it.trim() }.toSet().size
            )
            assertTrue(
                "Question ${question.id} does not match declared level ${question.level}",
                question.id.contains("-${question.level.name.lowercase()}-")
            )
        }
    }

    @Test
    fun candidateExpansionPreservesStarterItemsWithoutEnablingProduction() {
        assertTrue(candidateKoreanPlacementQuestions.containsAll(starterKoreanPlacementQuestions))
        assertTrue(candidateKoreanPlacementQuestions.containsAll(additionalKoreanPlacementQuestions))
        assertEquals(24, candidateKoreanPlacementQuestions.size)
        assertFalse(isQualityPlacementEnabled("ko"))
        assertEquals(starterKoreanPlacementQuestions, placementRuntimeSelection("ko").questions)
    }
}
