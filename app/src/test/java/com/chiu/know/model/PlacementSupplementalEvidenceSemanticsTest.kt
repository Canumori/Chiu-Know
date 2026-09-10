package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Test

class PlacementSupplementalEvidenceSemanticsTest {

    @Test
    fun supplementalLowerFailureCannotCreateDownwardRevision() {
        val result = decidePlacementFromConfirmation(
            provisionalLevel = CefrLevel.B1,
            evidence = listOf(
                evidence("b1-1", CefrLevel.B1, PlacementConfirmationRole.ESTIMATED_LEVEL, false),
                evidence("b1-2", CefrLevel.B1, PlacementConfirmationRole.ESTIMATED_LEVEL, false),
                evidence("a2-required", CefrLevel.A2, PlacementConfirmationRole.LOWER_BOUNDARY, true),
                evidence("b2-required", CefrLevel.B2, PlacementConfirmationRole.UPPER_BOUNDARY, false),
                evidence("a2-supplemental", CefrLevel.A2, PlacementConfirmationRole.LOWER_BOUNDARY, false)
            )
        )

        assertEquals(PlacementDecisionStatus.CONFIRMED, result.status)
        assertEquals(CefrLevel.B1, result.decidedLevel)
    }

    @Test
    fun supplementalUpperSuccessCannotRescueFailedRequiredUpperEvidence() {
        val result = decidePlacementFromConfirmation(
            provisionalLevel = CefrLevel.B1,
            evidence = listOf(
                evidence("b1-1", CefrLevel.B1, PlacementConfirmationRole.ESTIMATED_LEVEL, true),
                evidence("b1-2", CefrLevel.B1, PlacementConfirmationRole.ESTIMATED_LEVEL, true),
                evidence("a2-required", CefrLevel.A2, PlacementConfirmationRole.LOWER_BOUNDARY, true),
                evidence("b2-required", CefrLevel.B2, PlacementConfirmationRole.UPPER_BOUNDARY, false),
                evidence("b2-supplemental", CefrLevel.B2, PlacementConfirmationRole.UPPER_BOUNDARY, true)
            )
        )

        assertEquals(PlacementDecisionStatus.CONFIRMED, result.status)
        assertEquals(CefrLevel.B1, result.decidedLevel)
    }

    private fun evidence(
        id: String,
        level: CefrLevel,
        role: PlacementConfirmationRole,
        correct: Boolean
    ) = PlacementConfirmationEvidence(
        questionId = id,
        level = level,
        role = role,
        correct = correct
    )
}
