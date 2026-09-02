package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class PrivateFsrsSchedulerTest {

    private val scheduler = PrivateFsrsScheduler()

    @Test
    fun firstCorrectObservationUsesPublishedGoodInitialState() {
        val reviewedAt = 1_000L
        val state = scheduler.next(
            previous = null,
            observation = ReviewObservation("en:a1:greeting:hello", correct = true, reviewedAt)
        )

        assertEquals(ReviewPhase.REVIEW, state.phase)
        assertEquals(2.3065, state.stabilityDays, 0.0000001)
        assertEquals(2.118103970459015, state.difficulty, 0.0000001)
        assertEquals(reviewedAt + (2.3065 * 86_400_000.0).toLong(), state.dueAtEpochMillis, 1L)
        assertEquals(1, state.reviewCount)
        assertEquals(0, state.lapseCount)
    }

    @Test
    fun firstIncorrectObservationUsesAgainWithoutInventingHardOrEasy() {
        val state = scheduler.next(
            previous = null,
            observation = ReviewObservation("pt:a1:greeting:ola", correct = false, 10L)
        )

        assertEquals(ReviewPhase.LEARNING, state.phase)
        assertEquals(0.212, state.stabilityDays, 0.0000001)
        assertEquals(6.4133, state.difficulty, 0.0000001)
        assertEquals(1, state.reviewCount)
        assertEquals(1, state.lapseCount)
    }

    @Test
    fun delayedSuccessExtendsStabilityAndKeepsFactsSeparate() {
        val first = scheduler.next(
            null,
            ReviewObservation("es:a1:greeting:hola", correct = true, 0L)
        )
        val second = scheduler.next(
            first,
            ReviewObservation(
                "es:a1:greeting:hola",
                correct = true,
                attemptedAtEpochMillis = first.dueAtEpochMillis
            )
        )

        assertEquals(ReviewPhase.REVIEW, second.phase)
        assertTrue(second.stabilityDays > first.stabilityDays)
        assertTrue(second.dueAtEpochMillis > second.lastReviewAtEpochMillis)
        assertEquals(2, second.reviewCount)
        assertEquals(0, second.lapseCount)
    }

    @Test
    fun failureAfterReviewCreatesRelearningStateAndCountsLapse() {
        val first = scheduler.next(
            null,
            ReviewObservation("fr:a1:greeting:bonjour", correct = true, 0L)
        )
        val failed = scheduler.next(
            first,
            ReviewObservation(
                "fr:a1:greeting:bonjour",
                correct = false,
                attemptedAtEpochMillis = first.dueAtEpochMillis
            )
        )

        assertEquals(ReviewPhase.RELEARNING, failed.phase)
        assertEquals(2, failed.reviewCount)
        assertEquals(1, failed.lapseCount)
        assertTrue(failed.stabilityDays > 0.0)
    }

    @Test
    fun sameDayCorrectReviewNeverReducesStability() {
        val first = scheduler.next(
            null,
            ReviewObservation("ko:a1:greeting:annyeonghaseyo", correct = true, 0L)
        )
        val sameDay = scheduler.next(
            first,
            ReviewObservation(
                "ko:a1:greeting:annyeonghaseyo",
                correct = true,
                attemptedAtEpochMillis = 60_000L
            )
        )

        assertTrue(sameDay.stabilityDays >= first.stabilityDays)
    }

    @Test(expected = IllegalArgumentException::class)
    fun rejectsObservationForDifferentKnowledgeTarget() {
        val first = scheduler.next(
            null,
            ReviewObservation("first:key", correct = true, 0L)
        )

        scheduler.next(
            first,
            ReviewObservation("other:key", correct = true, 100L)
        )
    }
}
