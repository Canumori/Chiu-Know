package com.chiu.know.model

/**
 * Persistable scheduler state for one reviewKey.
 *
 * This state describes when a knowledge target should be reviewed. It is not a
 * CEFR level, a mastery score or a replacement for raw LearningEvidence.
 */
enum class ReviewPhase {
    LEARNING,
    REVIEW,
    RELEARNING
}

data class ReviewScheduleState(
    val schemaVersion: Int = CURRENT_REVIEW_SCHEDULE_SCHEMA_VERSION,
    val reviewKey: String,
    val phase: ReviewPhase,
    val difficulty: Double,
    val stabilityDays: Double,
    val dueAtEpochMillis: Long,
    val lastReviewAtEpochMillis: Long,
    val reviewCount: Int,
    val lapseCount: Int
) {
    init {
        require(schemaVersion > 0) { "Schedule schema version must be positive" }
        require(reviewKey.isNotBlank()) { "Schedule review key must not be blank" }
        require(difficulty.isFinite() && difficulty > 0.0) {
            "Schedule difficulty must be finite and positive"
        }
        require(stabilityDays.isFinite() && stabilityDays > 0.0) {
            "Schedule stability must be finite and positive"
        }
        require(lastReviewAtEpochMillis >= 0L) { "Last review timestamp must not be negative" }
        require(dueAtEpochMillis >= lastReviewAtEpochMillis) {
            "Due timestamp must not precede last review"
        }
        require(reviewCount > 0) { "Review count must be positive" }
        require(lapseCount in 0..reviewCount) { "Lapse count must fit review count" }
    }
}

const val CURRENT_REVIEW_SCHEDULE_SCHEMA_VERSION = 1

/**
 * One scheduling input derived from an observed attempt.
 *
 * The binary result matches evidence the app can honestly observe today. A
 * future richer response flow may add learner ratings explicitly; it must not
 * fabricate EASY/HARD ratings from correctness alone.
 */
data class ReviewObservation(
    val reviewKey: String,
    val correct: Boolean,
    val attemptedAtEpochMillis: Long
) {
    init {
        require(reviewKey.isNotBlank()) { "Observation review key must not be blank" }
        require(attemptedAtEpochMillis >= 0L) { "Observation timestamp must not be negative" }
    }
}

fun reviewObservationFor(evidence: LearningEvidence): ReviewObservation =
    ReviewObservation(
        reviewKey = evidence.reviewKey,
        correct = evidence.correct,
        attemptedAtEpochMillis = evidence.attemptedAtEpochMillis
    )

/**
 * Algorithm boundary kept independent from UI and persistence.
 *
 * Implementations must be deterministic for the same previous state,
 * observation and configuration.
 */
fun interface ReviewScheduler {
    fun next(
        previous: ReviewScheduleState?,
        observation: ReviewObservation
    ): ReviewScheduleState
}
