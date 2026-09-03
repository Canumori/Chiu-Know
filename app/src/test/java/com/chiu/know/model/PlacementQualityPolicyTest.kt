package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class PlacementQualityPolicyTest {

    @Test
    fun defaultPolicyPrioritizesEvidenceOverExtremeBrevity() {
        val policy = PlacementQualityPolicy()

        assertEquals(8, policy.minimumAnsweredQuestions)
        assertEquals(2, policy.confirmationQuestionsAtEstimatedLevel)
        assertEquals(2, policy.adjacentBoundaryQuestions)
        assertEquals(14, policy.maximumAnsweredQuestions)
    }

    @Test
    fun unfinishedAdaptiveSearchKeepsLocating() {
        val policy = PlacementQualityPolicy()

        assertEquals(PlacementPhase.LOCATE, placementPhase(false, 3, policy))
        assertEquals(PlacementPhase.LOCATE, placementPhase(false, 8, policy))
    }

    @Test
    fun earlyAdaptiveResultDoesNotEndTestBeforeMinimumEvidence() {
        val policy = PlacementQualityPolicy()

        assertEquals(PlacementPhase.LOCATE, placementPhase(true, 3, policy))
        assertEquals(PlacementPhase.LOCATE, placementPhase(true, 7, policy))
    }

    @Test
    fun estimatedLevelMustEnterConfirmationAfterMinimumEvidence() {
        val policy = PlacementQualityPolicy()

        assertEquals(PlacementPhase.CONFIRM, placementPhase(true, 8, policy))
        assertEquals(PlacementPhase.CONFIRM, placementPhase(true, 13, policy))
    }

    @Test
    fun maximumEvidenceCapCompletesInsteadOfRunningIndefinitely() {
        val policy = PlacementQualityPolicy()

        assertEquals(PlacementPhase.COMPLETE, placementPhase(false, 14, policy))
        assertEquals(PlacementPhase.COMPLETE, placementPhase(true, 18, policy))
    }

    @Test
    fun invalidPoliciesAreRejected() {
        assertThrows(IllegalArgumentException::class.java) {
            PlacementQualityPolicy(minimumAnsweredQuestions = 0)
        }
        assertThrows(IllegalArgumentException::class.java) {
            PlacementQualityPolicy(maximumAnsweredQuestions = 7)
        }
    }
}
