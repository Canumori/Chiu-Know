package com.chiu.know.model

import kotlin.math.exp
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.roundToLong

private const val MILLIS_PER_DAY = 86_400_000.0

/**
 * Private FSRS-6 scheduler implemented from the published mathematical model.
 *
 * The app currently observes only incorrect/correct, so ratings map honestly to
 * AGAIN (1) and GOOD (3). It must not fabricate HARD/EASY from correctness.
 *
 * Formula reference:
 * https://github.com/open-spaced-repetition/awesome-fsrs/wiki/The-Algorithm
 */
class PrivateFsrsScheduler(
    private val desiredRetention: Double = 0.9,
    private val maximumIntervalDays: Double = 36_500.0,
    private val parameters: List<Double> = DEFAULT_FSRS_6_PARAMETERS
) : ReviewScheduler {

    init {
        require(desiredRetention in 0.01..0.99) { "Desired retention must be between 0.01 and 0.99" }
        require(maximumIntervalDays.isFinite() && maximumIntervalDays >= 1.0) {
            "Maximum interval must be finite and at least one day"
        }
        require(parameters.size == 21 && parameters.all { it.isFinite() }) {
            "FSRS-6 requires 21 finite parameters"
        }
    }

    override fun next(
        previous: ReviewScheduleState?,
        observation: ReviewObservation
    ): ReviewScheduleState {
        require(previous == null || previous.reviewKey == observation.reviewKey) {
            "Previous state and observation must refer to the same review key"
        }
        require(previous == null || observation.attemptedAtEpochMillis >= previous.lastReviewAtEpochMillis) {
            "Observation must not precede the previous review"
        }

        val rating = if (observation.correct) GOOD_RATING else AGAIN_RATING
        val memory = if (previous == null) {
            MemoryState(
                difficulty = initialDifficulty(rating),
                stability = initialStability(rating)
            )
        } else {
            updatedMemory(previous, observation.attemptedAtEpochMillis, rating)
        }

        val intervalDays = nextIntervalDays(memory.stability)
        val dueAt = safeDueTimestamp(observation.attemptedAtEpochMillis, intervalDays)
        val lapse = !observation.correct

        return ReviewScheduleState(
            reviewKey = observation.reviewKey,
            phase = when {
                observation.correct -> ReviewPhase.REVIEW
                previous == null -> ReviewPhase.LEARNING
                else -> ReviewPhase.RELEARNING
            },
            difficulty = memory.difficulty,
            stabilityDays = memory.stability,
            dueAtEpochMillis = dueAt,
            lastReviewAtEpochMillis = observation.attemptedAtEpochMillis,
            reviewCount = (previous?.reviewCount ?: 0) + 1,
            lapseCount = (previous?.lapseCount ?: 0) + if (lapse) 1 else 0
        )
    }

    private fun updatedMemory(
        previous: ReviewScheduleState,
        reviewedAtEpochMillis: Long,
        rating: Int
    ): MemoryState {
        val elapsedDays = (reviewedAtEpochMillis - previous.lastReviewAtEpochMillis) / MILLIS_PER_DAY
        val difficulty = nextDifficulty(previous.difficulty, rating)
        val stability = if (elapsedDays < 1.0) {
            sameDayStability(previous.stabilityDays, rating)
        } else {
            val retrievability = retrievability(elapsedDays, previous.stabilityDays)
            if (rating == AGAIN_RATING) {
                forgettingStability(difficulty, previous.stabilityDays, retrievability)
            } else {
                recallStability(difficulty, previous.stabilityDays, retrievability)
            }
        }
        return MemoryState(difficulty, stability.coerceAtLeast(MINIMUM_STABILITY_DAYS))
    }

    private fun initialStability(rating: Int): Double =
        parameters[rating - 1].coerceAtLeast(MINIMUM_STABILITY_DAYS)

    private fun initialDifficulty(rating: Int): Double =
        (parameters[4] - exp(parameters[5] * (rating - 1)) + 1.0).coerceIn(1.0, 10.0)

    private fun nextDifficulty(current: Double, rating: Int): Double {
        val delta = -parameters[6] * (rating - 3)
        val damped = current + delta * (10.0 - current) / 9.0
        val meanReversionTarget = initialDifficulty(EASY_RATING)
        return (parameters[7] * meanReversionTarget + (1.0 - parameters[7]) * damped)
            .coerceIn(1.0, 10.0)
    }

    private fun retrievability(elapsedDays: Double, stability: Double): Double {
        val decay = parameters[20]
        val factor = 0.9.pow(-1.0 / decay) - 1.0
        return (1.0 + factor * elapsedDays / stability).pow(-decay).coerceIn(0.0, 1.0)
    }

    private fun sameDayStability(stability: Double, rating: Int): Double {
        val increase = exp(parameters[17] * (rating - 3 + parameters[18])) *
            stability.pow(-parameters[19])
        val boundedIncrease = if (rating >= HARD_RATING) max(1.0, increase) else increase
        return stability * boundedIncrease
    }

    private fun recallStability(
        difficulty: Double,
        stability: Double,
        retrievability: Double
    ): Double {
        val increase = exp(parameters[8]) *
            (11.0 - difficulty) *
            stability.pow(-parameters[9]) *
            (exp(parameters[10] * (1.0 - retrievability)) - 1.0)
        return stability * (1.0 + increase)
    }

    private fun forgettingStability(
        difficulty: Double,
        stability: Double,
        retrievability: Double
    ): Double =
        parameters[11] *
            difficulty.pow(-parameters[12]) *
            ((stability + 1.0).pow(parameters[13]) - 1.0) *
            exp(parameters[14] * (1.0 - retrievability))

    private fun nextIntervalDays(stability: Double): Double {
        val decay = parameters[20]
        val factor = 0.9.pow(-1.0 / decay) - 1.0
        return (stability / factor * (desiredRetention.pow(-1.0 / decay) - 1.0))
            .coerceIn(MINIMUM_INTERVAL_DAYS, maximumIntervalDays)
    }

    private fun safeDueTimestamp(reviewedAtEpochMillis: Long, intervalDays: Double): Long {
        val intervalMillis = (intervalDays * MILLIS_PER_DAY).roundToLong()
        return if (Long.MAX_VALUE - reviewedAtEpochMillis < intervalMillis) {
            Long.MAX_VALUE
        } else {
            reviewedAtEpochMillis + intervalMillis
        }
    }

    private data class MemoryState(
        val difficulty: Double,
        val stability: Double
    )

    companion object {
        private const val AGAIN_RATING = 1
        private const val HARD_RATING = 2
        private const val GOOD_RATING = 3
        private const val EASY_RATING = 4
        private const val MINIMUM_STABILITY_DAYS = 0.01
        private const val MINIMUM_INTERVAL_DAYS = 0.01

        val DEFAULT_FSRS_6_PARAMETERS = listOf(
            0.212, 1.2931, 2.3065, 8.2956, 6.4133, 0.8334, 3.0194,
            0.001, 1.8722, 0.1666, 0.796, 1.4835, 0.0614, 0.2629,
            1.6483, 0.6014, 1.8729, 0.5425, 0.0912, 0.0658, 0.1542
        )
    }
}
