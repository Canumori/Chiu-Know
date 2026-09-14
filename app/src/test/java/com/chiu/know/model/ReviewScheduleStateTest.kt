package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class ReviewScheduleStateTest {

    @Test
    fun preservesSchedulingFactsWithoutClaimingMasteryOrCefrProgress() {
        val state = ReviewScheduleState(
            reviewKey = "en:a1:greeting:hello",
            phase = ReviewPhase.REVIEW,
            difficulty = 5.0,
            stabilityDays = 3.5,
            dueAtEpochMillis = 400_000L,
            lastReviewAtEpochMillis = 100_000L,
            reviewCount = 2,
            lapseCount = 1
        )

        assertEquals(CURRENT_REVIEW_SCHEDULE_SCHEMA_VERSION, state.schemaVersion)
        assertEquals("en:a1:greeting:hello", state.reviewKey)
        assertEquals(ReviewPhase.REVIEW, state.phase)
        assertEquals(2, state.reviewCount)
    }

    @Test
    fun observationUsesOnlyEvidenceTheAppActuallyRecorded() {
        val evidence = LearningEvidence(
            activityId = "en-a1-greeting-001",
            reviewKey = "en:a1:greeting:hello",
            level = CefrLevel.A1,
            primarySkill = LearningSkill.VOCABULARY,
            correct = false,
            attemptedAtEpochMillis = 123L
        )

        val observation = reviewObservationFor(evidence)

        assertEquals(evidence.reviewKey, observation.reviewKey)
        assertEquals(evidence.attemptedAtEpochMillis, observation.attemptedAtEpochMillis)
        assertFalse(observation.correct)
    }

    @Test
    fun schedulerBoundaryCanBeTestedWithoutUiOrStorage() {
        val scheduler = ReviewScheduler { _, observation ->
            ReviewScheduleState(
                reviewKey = observation.reviewKey,
                phase = ReviewPhase.LEARNING,
                difficulty = 5.0,
                stabilityDays = 1.0,
                dueAtEpochMillis = observation.attemptedAtEpochMillis + 86_400_000L,
                lastReviewAtEpochMillis = observation.attemptedAtEpochMillis,
                reviewCount = 1,
                lapseCount = if (observation.correct) 0 else 1
            )
        }

        val state = scheduler.next(
            previous = null,
            observation = ReviewObservation("pt:a1:greeting:ola", correct = true, attemptedAtEpochMillis = 10L)
        )

        assertEquals("pt:a1:greeting:ola", state.reviewKey)
        assertEquals(1, state.reviewCount)
        assertEquals(0, state.lapseCount)
    }

    @Test(expected = IllegalArgumentException::class)
    fun scheduleRejectsDueDateBeforeLastReview() {
        ReviewScheduleState(
            reviewKey = "en:a1:greeting:hello",
            phase = ReviewPhase.REVIEW,
            difficulty = 5.0,
            stabilityDays = 1.0,
            dueAtEpochMillis = 99L,
            lastReviewAtEpochMillis = 100L,
            reviewCount = 1,
            lapseCount = 0
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun scheduleRejectsNonFiniteStability() {
        ReviewScheduleState(
            reviewKey = "en:a1:greeting:hello",
            phase = ReviewPhase.REVIEW,
            difficulty = 5.0,
            stabilityDays = Double.NaN,
            dueAtEpochMillis = 200L,
            lastReviewAtEpochMillis = 100L,
            reviewCount = 1,
            lapseCount = 0
        )
    }
}
