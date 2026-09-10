package com.chiu.know.model

/**
 * Selects an activity for user-requested optional practice without touching the
 * spaced-review schedule.
 *
 * A2-C2 preserve the existing starter-practice selector exactly. A1 also stays
 * starter-only until the connected second-transfer initial sequence has been
 * fully exposed. After that sequence is complete, its already validated strong
 * REORDER/FILL_IN review variants become eligible alongside starter activities.
 *
 * This is exposure balancing only: correctness does not imply mastery, and this
 * function never creates or mutates ReviewScheduleState.
 */
fun learningActivityForOptionalPractice(
    languageCode: String,
    level: CefrLevel,
    evidence: List<LearningEvidence>
): LearningActivity? {
    if (level != CefrLevel.A1) {
        return starterLearningActivityForEvidence(languageCode, level, evidence)
    }

    val transferState = a1SecondTransferNewWorkSelection(languageCode, evidence)
    if (transferState.reason != A1SecondTransferNewWorkReason.INITIAL_SEQUENCE_COMPLETE) {
        return starterLearningActivityForEvidence(languageCode, level, evidence)
    }

    val candidates = starterLearningActivitiesFor(languageCode)
        .filter { it.level == level } +
        a1SecondTransferReviewActivitiesFor(languageCode)

    return balancedOptionalPracticeActivity(candidates, level, evidence)
}

private fun balancedOptionalPracticeActivity(
    candidates: List<LearningActivity>,
    level: CefrLevel,
    evidence: List<LearningEvidence>
): LearningActivity? {
    if (candidates.isEmpty()) return null

    val groups = candidates.groupBy { it.reviewKey }
    val relevantEvidence = evidence.filter { attempt ->
        attempt.level == level && attempt.reviewKey in groups
    }

    val targetReviewKey = groups.keys.minBy { reviewKey ->
        relevantEvidence.count { it.reviewKey == reviewKey }
    }
    val targetCandidates = groups.getValue(targetReviewKey)
    val targetAttemptCount = relevantEvidence.count { it.reviewKey == targetReviewKey }

    return targetCandidates[Math.floorMod(targetAttemptCount, targetCandidates.size)]
}
