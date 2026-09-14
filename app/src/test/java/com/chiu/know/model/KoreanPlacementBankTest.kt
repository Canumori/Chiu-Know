package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class KoreanPlacementBankTest {
    @Test
    fun hasAtLeastTwoQuestionsPerCefrLevel() {
        CefrLevel.entries.forEach { level ->
            assertTrue(
                "Expected at least two Korean questions for $level",
                placementQuestionsForLevel(starterKoreanPlacementQuestions, level).size >= 2
            )
        }
    }

    @Test
    fun questionIdsAreUnique() {
        val ids = starterKoreanPlacementQuestions.map { it.id }
        assertEquals(ids.size, ids.toSet().size)
    }
}
