package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PortuguesePlacementBankTest {

    @Test
    fun hasAtLeastTwoQuestionsPerCefrLevel() {
        CefrLevel.entries.forEach { level ->
            assertTrue(
                "Expected at least two Portuguese questions for $level",
                placementQuestionsForLevel(starterPortuguesePlacementQuestions, level).size >= 2
            )
        }
    }

    @Test
    fun questionIdsAreUnique() {
        val ids = starterPortuguesePlacementQuestions.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }
}
