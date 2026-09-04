package com.chiu.know.model

import org.junit.Assert.assertTrue
import org.junit.Test

class PlacementSessionBankIdentityTest {

    @Test
    fun blankQuestionIdIsRejectedBeforeSessionStarts() {
        val invalidBank = listOf(
            question(id = " ", level = CefrLevel.B1)
        )

        var threw = false
        try {
            startPlacementSession(invalidBank)
        } catch (_: IllegalArgumentException) {
            threw = true
        }

        assertTrue(threw)
    }

    @Test
    fun duplicateQuestionIdsAreRejectedGloballyBeforeSessionStarts() {
        val invalidBank = listOf(
            question(id = "duplicate-id", level = CefrLevel.B1),
            question(id = "duplicate-id", level = CefrLevel.B2)
        )

        var threw = false
        try {
            startPlacementSession(invalidBank)
        } catch (_: IllegalArgumentException) {
            threw = true
        }

        assertTrue(threw)
    }

    private fun question(id: String, level: CefrLevel) = PlacementQuestion(
        id = id,
        level = level,
        prompt = "Structural identity test",
        options = listOf("a", "b"),
        correctIndex = 0
    )
}
