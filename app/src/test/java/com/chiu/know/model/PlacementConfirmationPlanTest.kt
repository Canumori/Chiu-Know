package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class PlacementConfirmationPlanTest {

    @Test
    fun everyLevelGetsRequiredEstimatedAndBoundaryEvidenceTargets() {
        val policy = PlacementQualityPolicy(
            minimumAnsweredQuestions = 8,
            confirmationQuestionsAtEstimatedLevel = 2,
            adjacentBoundaryQuestions = 2,
            maximumAnsweredQuestions = 14
        )

        CefrLevel.entries.forEach { estimated ->
            val targets = placementConfirmationTargets(estimated, policy)
            assertEquals(4, targets.size)
            assertEquals(
                2,
                targets.count {
                    it.level == estimated && it.role == PlacementConfirmationRole.ESTIMATED_LEVEL
                }
            )
            assertEquals(2, targets.count { it.role != PlacementConfirmationRole.ESTIMATED_LEVEL })
        }
    }

    @Test
    fun interiorLevelsSampleBothAdjacentBoundaries() {
        listOf(CefrLevel.A2, CefrLevel.B1, CefrLevel.B2, CefrLevel.C1).forEach { estimated ->
            val levels = CefrLevel.entries
            val index = levels.indexOf(estimated)
            val targets = placementConfirmationTargets(estimated)

            assertTrue(targets.any {
                it.level == levels[index - 1] && it.role == PlacementConfirmationRole.LOWER_BOUNDARY
            })
            assertTrue(targets.any {
                it.level == levels[index + 1] && it.role == PlacementConfirmationRole.UPPER_BOUNDARY
            })
        }
    }

    @Test
    fun a1UsesOnlyItsAvailableUpperBoundary() {
        val targets = placementConfirmationTargets(CefrLevel.A1)
        val boundaries = targets.filter { it.role != PlacementConfirmationRole.ESTIMATED_LEVEL }

        assertEquals(2, boundaries.size)
        assertTrue(boundaries.all {
            it.level == CefrLevel.A2 && it.role == PlacementConfirmationRole.UPPER_BOUNDARY
        })
    }

    @Test
    fun c2UsesOnlyItsAvailableLowerBoundary() {
        val targets = placementConfirmationTargets(CefrLevel.C2)
        val boundaries = targets.filter { it.role != PlacementConfirmationRole.ESTIMATED_LEVEL }

        assertEquals(2, boundaries.size)
        assertTrue(boundaries.all {
            it.level == CefrLevel.C1 && it.role == PlacementConfirmationRole.LOWER_BOUNDARY
        })
    }

    @Test
    fun englishBankCanSatisfyDefaultConfirmationPlanForEveryLevelWhenFresh() {
        val bank = placementCandidateQuestionsFor("en")

        CefrLevel.entries.forEach { estimated ->
            val targets = placementConfirmationTargets(estimated)
            val selected = selectPlacementConfirmationQuestions(bank, targets, emptySet())

            requireNotNull(selected)
            assertEquals(targets.size, selected.size)
            assertEquals(selected.size, selected.map { it.id }.toSet().size)
            targets.zip(selected).forEach { (target, question) ->
                assertEquals(target.level, question.level)
            }
        }
    }

    @Test
    fun alreadyUsedQuestionsAreNeverRecycledAsConfirmationEvidence() {
        val bank = placementCandidateQuestionsFor("en")
        val targets = placementConfirmationTargets(CefrLevel.B1)
        val used = setOf("en-b1-001", "en-a2-001", "en-b2-001")

        val selected = selectPlacementConfirmationQuestions(bank, targets, used)

        requireNotNull(selected)
        assertTrue(selected.none { it.id in used })
        assertEquals(selected.size, selected.map { it.id }.toSet().size)
    }

    @Test
    fun insufficientFreshQuestionsFailsClosedInsteadOfRepeating() {
        val bank = placementCandidateQuestionsFor("en")
        val targets = placementConfirmationTargets(CefrLevel.B1)
        val used = bank.filter { it.level == CefrLevel.B1 }.map { it.id }.toSet()

        assertNull(selectPlacementConfirmationQuestions(bank, targets, used))
    }

    @Test
    fun confirmationTargetsRespectConfiguredEvidenceCounts() {
        val policy = PlacementQualityPolicy(
            minimumAnsweredQuestions = 8,
            confirmationQuestionsAtEstimatedLevel = 3,
            adjacentBoundaryQuestions = 4,
            maximumAnsweredQuestions = 14
        )
        val targets = placementConfirmationTargets(CefrLevel.B2, policy)

        assertEquals(7, targets.size)
        assertEquals(3, targets.count { it.role == PlacementConfirmationRole.ESTIMATED_LEVEL })
        assertEquals(2, targets.count { it.role == PlacementConfirmationRole.LOWER_BOUNDARY })
        assertEquals(2, targets.count { it.role == PlacementConfirmationRole.UPPER_BOUNDARY })
    }
}
