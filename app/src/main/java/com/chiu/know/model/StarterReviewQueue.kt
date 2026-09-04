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
 *
 * Learner preferences may reorder only unscheduled new targets. They never
 * override due review, alter CEFR level, create mastery evidence or remove a
 * skill from the queue.
 */
fun starterQueueSelection(
    languageCode: String,
    level: CefrLevel,
    evidence: List<LearningEvidence>,
    schedules: List<ReviewScheduleState>,
    nowEpochMillis: Long,
    preferences: LearnerPreferences? = null
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

    val unscheduledTargets = groups.keys.filter { it !in compatibleSchedules }
    val newTarget = if (preferences == null) {
        unscheduledTargets.firstOrNull()
    } else {
        val mix = learnerPracticeMix(preferences)
        unscheduledTargets.maxWithOrNull(
            compareBy<String> { reviewKey ->
                skillPlanningWeight(
                    groups.getValue(reviewKey).first().primarySkill,
                    mix
                )
            }.thenByDescending { reviewKey -> groups.keys.indexOf(reviewKey) }
        )
    }

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

private fun skillPlanningWeight(skill: LearningSkill, mix: LearnerPracticeMix): Int = when (skill) {
    LearningSkill.GRAMMAR -> mix.grammarWeight
    LearningSkill.VOCABULARY -> mix.vocabularyWeight
    LearningSkill.LISTENING -> mix.listeningWeight
    LearningSkill.READING -> mix.readingWeight
    LearningSkill.WRITING -> mix.writingWeight
    LearningSkill.SPEAKING -> mix.speakingWeight
}

private fun rotatedVariant(
    candidates: List<LearningActivity>,
    evidence: List<LearningEvidence>
): LearningActivity {
    val attempts = evidence.count { it.reviewKey == candidates.first().reviewKey }
    return candidates[Math.floorMod(attempts, candidates.size)]
}
