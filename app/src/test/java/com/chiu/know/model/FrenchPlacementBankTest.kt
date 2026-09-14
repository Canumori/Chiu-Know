package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FrenchPlacementBankTest {
    @Test fun hasAtLeastTwoQuestionsPerCefrLevel() { CefrLevel.entries.forEach { level -> assertTrue("Expected at least two French questions for $level", placementQuestionsForLevel(starterFrenchPlacementQuestions, level).size >= 2) } }
    @Test fun questionIdsAreUnique() { val ids = starterFrenchPlacementQuestions.map { it.id }; assertEquals(ids.size, ids.toSet().size) }
}
