package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SpanishPlacementQualityBankTest {
    @Test
    fun spanishQualityBankHasExactlyFourItemsPerCefrLevel() {
        assertEquals(24, qualitySpanishPlacementQuestions.size)
        CefrLevel.entries.forEach { level ->
            assertEquals(4, qualitySpanishPlacementQuestions.count { it.level == level })
        }
    }

    @Test
    fun spanishQualityBankUsesUniqueNonBlankIdsAndPrompts() {
        val ids = qualitySpanishPlacementQuestions.map { it.id }
        val prompts = qualitySpanishPlacementQuestions.map { it.prompt }

        assertEquals(ids.size, ids.toSet().size)
        assertEquals(prompts.size, prompts.toSet().size)
        assertTrue(ids.all { it.isNotBlank() })
        assertTrue(prompts.all { it.isNotBlank() })
    }

    @Test
    fun spanishQualityBankHasFourDistinctOptionsAndValidAnswerIndexes() {
        qualitySpanishPlacementQuestions.forEach { question ->
            assertEquals(4, question.options.size)
            assertEquals(question.options.size, question.options.toSet().size)
            assertTrue(question.options.all { it.isNotBlank() })
            assertTrue(question.correctIndex in question.options.indices)
        }
    }

    @Test
    fun spanishQuestionIdsMatchDeclaredCefrLevels() {
        qualitySpanishPlacementQuestions.forEach { question ->
            assertTrue(question.id.startsWith("es-${question.level.name.lowercase()}-"))
        }
    }

    @Test
    fun spanishQualityBankPreservesStarterAndAdditionalQuestions() {
        assertTrue(qualitySpanishPlacementQuestions.containsAll(starterSpanishPlacementQuestions))
        assertTrue(qualitySpanishPlacementQuestions.containsAll(additionalSpanishPlacementQuestions))
    }

    @Test
    fun spanishBankCanSatisfyDefaultConfirmationPlanFreshAtEveryLevel() {
        CefrLevel.entries.forEach { level ->
            val selected = requireNotNull(
                selectPlacementConfirmationQuestions(
                    questions = qualitySpanishPlacementQuestions,
                    targets = placementConfirmationTargets(level),
                    usedQuestionIds = emptySet()
                )
            )
            assertEquals(4, selected.size)
        }
    }
}
