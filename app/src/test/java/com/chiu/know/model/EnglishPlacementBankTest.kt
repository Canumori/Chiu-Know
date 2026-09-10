package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class EnglishPlacementBankTest {

    @Test
    fun hasAtLeastTwoQuestionsForEveryCefrLevel() {
        CefrLevel.entries.forEach { level ->
            val questions = placementQuestionsForLevel(starterEnglishPlacementQuestions, level)
            assertTrue("Expected at least two English questions for $level", questions.size >= 2)
        }
    }

    @Test
    fun questionIdsAreUnique() {
        val ids = starterEnglishPlacementQuestions.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }
}
