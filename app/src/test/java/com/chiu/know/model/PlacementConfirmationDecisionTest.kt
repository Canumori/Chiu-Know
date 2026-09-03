package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class PlacementConfirmationDecisionTest {

    private fun evidence(
        id: String,
        level: CefrLevel,
        role: PlacementConfirmationRole,
        correct: Boolean
    ) = PlacementConfirmationEvidence(id, level, role, correct)

    @Test
    fun incompleteEvidenceNeedsMoreEvidence() {
        val result = decidePlacementFromConfirmation(
            provisionalLevel = CefrLevel.B1,
            evidence = listOf(
                evidence("b1-1", CefrLevel.B1, PlacementConfirmationRole.ESTIMATED_LEVEL, true)
            )
        )

        assertEquals(PlacementDecisionStatus.NEEDS_MORE_EVIDENCE, result.status)
        assertNull(result.decidedLevel)
    }

    @Test
    fun duplicatedQuestionIdsNeverProduceDecision() {
        val result = decidePlacementFromConfirmation(
            provisionalLevel = CefrLevel.B1,
            evidence = listOf(
                evidence("same", CefrLevel.B1, PlacementConfirmationRole.ESTIMATED_LEVEL, true),
                evidence("same", CefrLevel.B1, PlacementConfirmationRole.ESTIMATED_LEVEL, true),
                evidence("a2", CefrLevel.A2, PlacementConfirmationRole.LOWER_BOUNDARY, true),
                evidence("b2", CefrLevel.B2, PlacementConfirmationRole.UPPER_BOUNDARY, false)
            )
        )

        assertEquals(PlacementDecisionStatus.NEEDS_MORE_EVIDENCE, result.status)
        assertNull(result.decidedLevel)
    }

    @Test
    fun evidenceWithWrongLevelRoleCombinationIsRejected() {
        val result = decidePlacementFromConfirmation(
            provisionalLevel = CefrLevel.B1,
            evidence = listOf(
                evidence("b1-1", CefrLevel.B1, PlacementConfirmationRole.ESTIMATED_LEVEL, true),
                evidence("b1-2", CefrLevel.B1, PlacementConfirmationRole.ESTIMATED_LEVEL, true),
                evidence("wrong-lower", CefrLevel.C1, PlacementConfirmationRole.LOWER_BOUNDARY, true),
                evidence("b2", CefrLevel.B2, PlacementConfirmationRole.UPPER_BOUNDARY, false)
            )
        )

        assertEquals(PlacementDecisionStatus.NEEDS_MORE_EVIDENCE, result.status)
        assertNull(result.decidedLevel)
    }

    @Test
    fun strongUpperEvidenceRevisesUpExactlyOneBand() {
        val result = decidePlacementFromConfirmation(
            provisionalLevel = CefrLevel.B1,
            evidence = listOf(
                evidence("b1-1", CefrLevel.B1, PlacementConfirmationRole.ESTIMATED_LEVEL, true),
                evidence("b1-2", CefrLevel.B1, PlacementConfirmationRole.ESTIMATED_LEVEL, true),
                evidence("a2", CefrLevel.A2, PlacementConfirmationRole.LOWER_BOUNDARY, true),
                evidence("b2", CefrLevel.B2, PlacementConfirmationRole.UPPER_BOUNDARY, true)
            )
        )

        assertEquals(PlacementDecisionStatus.REVISED_UP, result.status)
        assertEquals(CefrLevel.B2, result.decidedLevel)
    }

    @Test
    fun weakProvisionalPlusLowerFailureRevisesDownExactlyOneBand() {
        val result = decidePlacementFromConfirmation(
            provisionalLevel = CefrLevel.B2,
            evidence = listOf(
                evidence("b2-1", CefrLevel.B2, PlacementConfirmationRole.ESTIMATED_LEVEL, false),
                evidence("b2-2", CefrLevel.B2, PlacementConfirmationRole.ESTIMATED_LEVEL, false),
                evidence("b1", CefrLevel.B1, PlacementConfirmationRole.LOWER_BOUNDARY, false),
                evidence("c1", CefrLevel.C1, PlacementConfirmationRole.UPPER_BOUNDARY, false)
            )
        )

        assertEquals(PlacementDecisionStatus.REVISED_DOWN, result.status)
        assertEquals(CefrLevel.B1, result.decidedLevel)
    }

    @Test
    fun mixedProvisionalEvidenceNeedsMoreEvidence() {
        val result = decidePlacementFromConfirmation(
            provisionalLevel = CefrLevel.B1,
            evidence = listOf(
                evidence("b1-1", CefrLevel.B1, PlacementConfirmationRole.ESTIMATED_LEVEL, true),
                evidence("b1-2", CefrLevel.B1, PlacementConfirmationRole.ESTIMATED_LEVEL, false),
                evidence("a2", CefrLevel.A2, PlacementConfirmationRole.LOWER_BOUNDARY, true),
                evidence("b2", CefrLevel.B2, PlacementConfirmationRole.UPPER_BOUNDARY, false)
            )
        )

        assertEquals(PlacementDecisionStatus.NEEDS_MORE_EVIDENCE, result.status)
        assertNull(result.decidedLevel)
    }

    @Test
    fun a1CanNeverReviseBelowScale() {
        val result = decidePlacementFromConfirmation(
            provisionalLevel = CefrLevel.A1,
            evidence = listOf(
                evidence("a1-1", CefrLevel.A1, PlacementConfirmationRole.ESTIMATED_LEVEL, false),
                evidence("a1-2", CefrLevel.A1, PlacementConfirmationRole.ESTIMATED_LEVEL, false),
                evidence("a2-1", CefrLevel.A2, PlacementConfirmationRole.UPPER_BOUNDARY, false),
                evidence("a2-2", CefrLevel.A2, PlacementConfirmationRole.UPPER_BOUNDARY, false)
            )
        )

        assertEquals(PlacementDecisionStatus.CONFIRMED, result.status)
        assertEquals(CefrLevel.A1, result.decidedLevel)
    }

    @Test
    fun c2CanNeverReviseAboveScale() {
        val result = decidePlacementFromConfirmation(
            provisionalLevel = CefrLevel.C2,
            evidence = listOf(
                evidence("c2-1", CefrLevel.C2, PlacementConfirmationRole.ESTIMATED_LEVEL, true),
                evidence("c2-2", CefrLevel.C2, PlacementConfirmationRole.ESTIMATED_LEVEL, true),
                evidence("c1-1", CefrLevel.C1, PlacementConfirmationRole.LOWER_BOUNDARY, true),
                evidence("c1-2", CefrLevel.C1, PlacementConfirmationRole.LOWER_BOUNDARY, true)
            )
        )

        assertEquals(PlacementDecisionStatus.CONFIRMED, result.status)
        assertEquals(CefrLevel.C2, result.decidedLevel)
    }

    @Test
    fun c2CanReviseDownButOnlyOneBand() {
        val result = decidePlacementFromConfirmation(
            provisionalLevel = CefrLevel.C2,
            evidence = listOf(
                evidence("c2-1", CefrLevel.C2, PlacementConfirmationRole.ESTIMATED_LEVEL, false),
                evidence("c2-2", CefrLevel.C2, PlacementConfirmationRole.ESTIMATED_LEVEL, false),
                evidence("c1-1", CefrLevel.C1, PlacementConfirmationRole.LOWER_BOUNDARY, false),
                evidence("c1-2", CefrLevel.C1, PlacementConfirmationRole.LOWER_BOUNDARY, true)
            )
        )

        assertEquals(PlacementDecisionStatus.REVISED_DOWN, result.status)
        assertEquals(CefrLevel.C1, result.decidedLevel)
    }

    @Test
    fun a1CanReviseUpButOnlyOneBand() {
        val result = decidePlacementFromConfirmation(
            provisionalLevel = CefrLevel.A1,
            evidence = listOf(
                evidence("a1-1", CefrLevel.A1, PlacementConfirmationRole.ESTIMATED_LEVEL, true),
                evidence("a1-2", CefrLevel.A1, PlacementConfirmationRole.ESTIMATED_LEVEL, true),
                evidence("a2-1", CefrLevel.A2, PlacementConfirmationRole.UPPER_BOUNDARY, true),
                evidence("a2-2", CefrLevel.A2, PlacementConfirmationRole.UPPER_BOUNDARY, true)
            )
        )

        assertEquals(PlacementDecisionStatus.REVISED_UP, result.status)
        assertEquals(CefrLevel.A2, result.decidedLevel)
    }

    @Test
    fun bankInsufficientFailsClosedWithoutLevelDecision() {
        val result = decidePlacementFromConfirmation(
            provisionalLevel = CefrLevel.B1,
            evidence = emptyList(),
            bankInsufficient = true
        )

        assertEquals(PlacementDecisionStatus.BANK_INSUFFICIENT, result.status)
        assertNull(result.decidedLevel)
    }
}
