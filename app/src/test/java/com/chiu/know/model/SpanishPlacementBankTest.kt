package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SpanishPlacementBankTest {

    @Test
    fun hasAtLeastTwoQuestionsPerCefrLevel() {
        CefrLevel.entries.forEach { level ->
            assertTrue(
                "Expected at least two Spanish questions for $level",
                placementQuestionsForLevel(starterSpanishPlacementQuestions, level).size >= 2
            )
        }
    }

    @Test
    fun questionIdsAreUnique() {
        val ids = starterSpanishPlacementQuestions.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }
}
