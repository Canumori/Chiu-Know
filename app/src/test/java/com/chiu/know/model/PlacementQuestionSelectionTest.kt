package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class PlacementQuestionSelectionTest {

    @Test
    fun selectsOnlyQuestionsFromRequestedLevel() {
        val questions = listOf(
            PlacementQuestion("a1", CefrLevel.A1, "A1", listOf("x"), 0),
            PlacementQuestion("b1-1", CefrLevel.B1, "B1 one", listOf("x"), 0),
            PlacementQuestion("b1-2", CefrLevel.B1, "B1 two", listOf("x"), 0)
        )

        val selected = placementQuestionsForLevel(questions, CefrLevel.B1)

        assertEquals(listOf("b1-1", "b1-2"), selected.map { it.id })
    }

    @Test
    fun cyclesAcrossMultipleQuestionsAtSameLevel() {
        val questions = listOf(
            PlacementQuestion("b1-1", CefrLevel.B1, "B1 one", listOf("x"), 0),
            PlacementQuestion("b1-2", CefrLevel.B1, "B1 two", listOf("x"), 0)
        )

        assertEquals("b1-1", placementQuestionForLevel(questions, CefrLevel.B1, 0).id)
        assertEquals("b1-2", placementQuestionForLevel(questions, CefrLevel.B1, 1).id)
        assertEquals("b1-1", placementQuestionForLevel(questions, CefrLevel.B1, 2).id)
    }

    @Test
    fun emptyLevelFailsExplicitlyInsteadOfCrashingLater() {
        val questions = listOf(
            PlacementQuestion("a1", CefrLevel.A1, "A1", listOf("x"), 0)
        )

        assertThrows(IllegalArgumentException::class.java) {
            placementQuestionForLevel(questions, CefrLevel.C2)
        }
    }
}
