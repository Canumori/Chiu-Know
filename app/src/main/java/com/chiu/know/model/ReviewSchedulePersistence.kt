package com.chiu.know.model

/**
 * Applies one observed attempt to the versioned local schedule collection.
 *
 * This pure function lets DataStore integration stay small and testable. It
 * replaces only the state for the matching reviewKey and keeps other targets.
 */
fun updateReviewScheduleStateSet(
    encoded: Set<String>,
    evidence: LearningEvidence,
    scheduler: ReviewScheduler = PrivateFsrsScheduler()
): Set<String> {
    val currentStates = decodeReviewScheduleStateSet(encoded)
    val previous = currentStates.firstOrNull { it.reviewKey == evidence.reviewKey }
    val next = scheduler.next(previous, reviewObservationFor(evidence))

    return currentStates
        .filterNot { it.reviewKey == next.reviewKey }
        .plus(next)
        .map(::encodeReviewScheduleState)
        .toSet()
}


/**
 * Replays historical evidence into scheduler state for installations that
 * recorded attempts before review schedules existed.
 *
 * Raw evidence remains authoritative. Replay is chronological and combines
 * activity variants through their shared reviewKey.
 */
fun rebuildReviewScheduleStates(
    evidence: List<LearningEvidence>,
    scheduler: ReviewScheduler = PrivateFsrsScheduler()
): List<ReviewScheduleState> {
    val states = mutableMapOf<String, ReviewScheduleState>()

    evidence.sortedBy { it.attemptedAtEpochMillis }.forEach { attempt ->
        val observation = reviewObservationFor(attempt)
        states[attempt.reviewKey] = scheduler.next(states[attempt.reviewKey], observation)
    }

    return states.values.sortedBy { it.reviewKey }
}
