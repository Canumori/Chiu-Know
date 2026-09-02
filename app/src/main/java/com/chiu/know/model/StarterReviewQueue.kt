package com.chiu.know.model

enum class StarterQueueReason {
    DUE_REVIEW,
    NEW_TARGET,
    NONE_DUE,
    NO_CONTENT
}

data class StarterQueueSelection(
    val activity: LearningActivity?,
    val reason: StarterQueueReason,
    val nextDueAtEpochMillis: Long? = null
) {
    init {
        require((activity == null) == (reason == StarterQueueReason.NONE_DUE || reason == StarterQueueReason.NO_CONTENT)) {
            "Queue reason and activity availability must agree"
        }
        require(nextDueAtEpochMillis == null || nextDueAtEpochMillis >= 0L) {
            "Next due timestamp must not be negative"
        }
    }
}

/**
 * Builds a conservative starter queue without changing CEFR progress.
 *
 * Priority:
 * 1. review targets whose explicit schedule is due;
 * 2. knowledge targets that do not have scheduler state yet;
 * 3. no activity when every known target is scheduled for the future.
 */
fun starterQueueSelection(
    languageCode: String,
    level: CefrLevel,
    evidence: List<LearningEvidence>,
    schedules: List<ReviewScheduleState>,
    nowEpochMillis: Long
): StarterQueueSelection {
    require(nowEpochMillis >= 0L) { "Queue time must not be negative" }

    val candidates = starterLearningActivitiesFor(languageCode).filter { it.level == level }
    if (candidates.isEmpty()) {
        return StarterQueueSelection(null, StarterQueueReason.NO_CONTENT)
    }

    val groups = candidates.groupBy { it.reviewKey }
    val compatibleEvidence = evidence.filter { it.level == level && it.reviewKey in groups }
    val compatibleSchedules = schedules
        .filter { it.reviewKey in groups }
        .associateBy { it.reviewKey }

    val dueTarget = groups.keys
        .mapNotNull { reviewKey -> compatibleSchedules[reviewKey] }
        .filter { it.dueAtEpochMillis <= nowEpochMillis }
        .minWithOrNull(
            compareBy<ReviewScheduleState> { it.dueAtEpochMillis }
                .thenBy { groups.keys.indexOf(it.reviewKey) }
        )

    if (dueTarget != null) {
        return StarterQueueSelection(
            activity = rotatedVariant(groups.getValue(dueTarget.reviewKey), compatibleEvidence),
            reason = StarterQueueReason.DUE_REVIEW
        )
    }

    val newTarget = groups.keys.firstOrNull { it !in compatibleSchedules }
    if (newTarget != null) {
        return StarterQueueSelection(
            activity = rotatedVariant(groups.getValue(newTarget), compatibleEvidence),
            reason = StarterQueueReason.NEW_TARGET
        )
    }

    return StarterQueueSelection(
        activity = null,
        reason = StarterQueueReason.NONE_DUE,
        nextDueAtEpochMillis = compatibleSchedules.values.minOfOrNull { it.dueAtEpochMillis }
    )
}

private fun rotatedVariant(
    candidates: List<LearningActivity>,
    evidence: List<LearningEvidence>
): LearningActivity {
    val attempts = evidence.count { it.reviewKey == candidates.first().reviewKey }
    return candidates[Math.floorMod(attempts, candidates.size)]
}
