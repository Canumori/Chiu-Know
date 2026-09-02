package com.chiu.know.model

private const val reviewScheduleFieldSeparator = "|"

/**
 * Compact, versioned local representation for one review target schedule.
 */
fun encodeReviewScheduleState(state: ReviewScheduleState): String =
    listOf(
        state.schemaVersion,
        state.reviewKey,
        state.phase.name,
        state.difficulty,
        state.stabilityDays,
        state.dueAtEpochMillis,
        state.lastReviewAtEpochMillis,
        state.reviewCount,
        state.lapseCount
    ).joinToString(reviewScheduleFieldSeparator)

/**
 * Invalid, unsupported or obsolete entries are ignored instead of breaking the
 * learning flow. Explicit migration can be added when schemaVersion advances.
 */
fun decodeReviewScheduleState(encoded: String): ReviewScheduleState? {
    val fields = encoded.split(reviewScheduleFieldSeparator)
    if (fields.size != 9) return null

    val schemaVersion = fields[0].toIntOrNull() ?: return null
    if (schemaVersion != CURRENT_REVIEW_SCHEDULE_SCHEMA_VERSION) return null
    val phase = ReviewPhase.entries.firstOrNull { it.name == fields[2] } ?: return null
    val difficulty = fields[3].toDoubleOrNull() ?: return null
    val stability = fields[4].toDoubleOrNull() ?: return null
    val dueAt = fields[5].toLongOrNull() ?: return null
    val lastReviewAt = fields[6].toLongOrNull() ?: return null
    val reviewCount = fields[7].toIntOrNull() ?: return null
    val lapseCount = fields[8].toIntOrNull() ?: return null

    return runCatching {
        ReviewScheduleState(
            schemaVersion = schemaVersion,
            reviewKey = fields[1],
            phase = phase,
            difficulty = difficulty,
            stabilityDays = stability,
            dueAtEpochMillis = dueAt,
            lastReviewAtEpochMillis = lastReviewAt,
            reviewCount = reviewCount,
            lapseCount = lapseCount
        )
    }.getOrNull()
}

/**
 * Keeps at most the newest valid state for each reviewKey.
 */
fun decodeReviewScheduleStateSet(encoded: Set<String>): List<ReviewScheduleState> =
    encoded
        .mapNotNull(::decodeReviewScheduleState)
        .groupBy { it.reviewKey }
        .values
        .map { states -> states.maxBy { it.lastReviewAtEpochMillis } }
        .sortedBy { it.reviewKey }
