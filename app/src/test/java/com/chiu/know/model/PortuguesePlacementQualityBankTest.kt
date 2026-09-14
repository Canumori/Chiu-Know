package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PortuguesePlacementQualityBankTest {
    @Test
    fun qualityBankHasFourQuestionsPerCefrLevel() {
        assertEquals(24, qualityPortuguesePlacementQuestions.size)
        CefrLevel.entries.forEach { level ->
            assertEquals(4, qualityPortuguesePlacementQuestions.count { it.level == level })
        }
    }

    @Test
    fun qualityBankHasUniqueIdsAndPrompts() {
        val ids = qualityPortuguesePlacementQuestions.map { it.id }
        val prompts = qualityPortuguesePlacementQuestions.map { it.prompt }
        assertEquals(ids.size, ids.distinct().size)
        assertEquals(prompts.size, prompts.distinct().size)
        assertTrue(ids.all { it.isNotBlank() })
        assertTrue(prompts.all { it.isNotBlank() })
    }

    @Test
    fun everyQuestionHasFourDistinctNonBlankOptionsAndValidAnswer() {
        qualityPortuguesePlacementQuestions.forEach { question ->
            assertEquals(4, question.options.size)
            assertEquals(4, question.options.distinct().size)
            assertTrue(question.options.all { it.isNotBlank() })
            assertTrue(question.correctIndex in question.options.indices)
        }
    }

    @Test
    fun idsMatchDeclaredCefrLevel() {
        qualityPortuguesePlacementQuestions.forEach { question ->
            assertTrue(question.id.startsWith("pt-${question.level.name.lowercase()}-"))
        }
    }

    @Test
    fun expandedBankPreservesAllStarterQuestions() {
        assertTrue(qualityPortuguesePlacementQuestions.containsAll(starterPortuguesePlacementQuestions))
        assertTrue(qualityPortuguesePlacementQuestions.containsAll(additionalPortuguesePlacementQuestions))
    }

    @Test
    fun defaultConfirmationPlanCanBeSatisfiedFreshAtEveryLevel() {
        CefrLevel.entries.forEach { level ->
            val targets = placementConfirmationTargets(level)
            val selected = selectPlacementConfirmationQuestions(
                questions = qualityPortuguesePlacementQuestions,
                targets = targets,
                usedQuestionIds = emptySet()
            )
            assertTrue("Portuguese bank cannot satisfy confirmation at $level", selected != null)
            assertEquals(targets.size, selected!!.size)
            assertEquals(selected.size, selected.map { it.id }.distinct().size)
        }
    }
}
