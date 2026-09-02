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
