package com.chiu.know.model

enum class A1PriorityLearningReason {
    DUE_STARTER_REVIEW,
    DUE_SECOND_TRANSFER_REVIEW,
    SECOND_TRANSFER_NEW_WORK,
    NONE
}

data class A1PriorityLearningSelection(
    val activity: LearningActivity?,
    val reason: A1PriorityLearningReason,
    val transferNewWorkReason: A1SecondTransferNewWorkReason? = null
) {
    init {
        require((activity != null) == (reason != A1PriorityLearningReason.NONE)) {
            "A1 priority selection reason must agree with activity availability"
        }
        require(
            transferNewWorkReason == null ||
                reason == A1PriorityLearningReason.SECOND_TRANSFER_NEW_WORK ||
                reason == A1PriorityLearningReason.NONE
        ) {
            "Transfer new-work reason is only valid for the transfer lane"
        }
    }
}

/**
 * Resolves the A1 priority lane without replacing the starter new-target queue.
 *
 * Priority:
 * 1. the oldest due review across starter and second-transfer targets;
 * 2. connected second-transfer new work when its prerequisites are exposed;
 * 3. no priority activity, allowing the caller to fall back to ordinary starter
 *    new-target selection or an up-to-date state.
 *
 * Exact due-time ties use the stable eligible-review order from A1ReviewLoad.
 * This selector does not mutate schedules/evidence, claim mastery, pull future
 * reviews forward, or expose optional practice as scheduled learning.
 */
fun a1PriorityLearningSelection(
    languageCode: String,
    evidence: List<LearningEvidence>,
    schedules: List<ReviewScheduleState>,
    nowEpochMillis: Long,
    preferences: LearnerPreferences? = null
): A1PriorityLearningSelection {
    require(nowEpochMillis >= 0L) { "A1 priority selection time must not be negative" }

    val reviewLoad = a1ReviewLoad(
        languageCode = languageCode,
        schedules = schedules,
        nowEpochMillis = nowEpochMillis
    )
    val compatibleSchedules = schedules
        .filter { it.reviewKey in reviewLoad.dueReviewKeys }
        .associateBy { it.reviewKey }

    val oldestDueReviewKey = reviewLoad.dueReviewKeys.minWithOrNull(
        compareBy<String> { compatibleSchedules.getValue(it).dueAtEpochMillis }
            .thenBy { reviewLoad.eligibleReviewKeys.indexOf(it) }
    )

    if (oldestDueReviewKey != null) {
        val secondTransferReviewKeys = a1SecondTransferReviewActivitiesFor(languageCode)
            .mapTo(hashSetOf()) { it.reviewKey }

        if (oldestDueReviewKey in secondTransferReviewKeys) {
            val transferQueue = a1SecondTransferReviewQueueSelection(
                languageCode = languageCode,
                evidence = evidence,
                schedules = schedules,
                nowEpochMillis = nowEpochMillis
            )
            val activity = requireNotNull(transferQueue.activity) {
                "Due second-transfer review key must resolve to a review activity"
            }
            require(activity.reviewKey == oldestDueReviewKey) {
                "Second-transfer review queue must agree with global A1 due-review priority"
            }
            return A1PriorityLearningSelection(
                activity = activity,
                reason = A1PriorityLearningReason.DUE_SECOND_TRANSFER_REVIEW
            )
        }

        val starterQueue = starterQueueSelection(
            languageCode = languageCode,
            level = CefrLevel.A1,
            evidence = evidence,
            schedules = schedules,
            nowEpochMillis = nowEpochMillis,
            preferences = preferences
        )
        val activity = requireNotNull(starterQueue.activity) {
            "Due starter review key must resolve to a starter review activity"
        }
        require(starterQueue.reason == StarterQueueReason.DUE_REVIEW) {
            "Global A1 due-review priority must not resolve to starter new work"
        }
        require(activity.reviewKey == oldestDueReviewKey) {
            "Starter review queue must agree with global A1 due-review priority"
        }
        return A1PriorityLearningSelection(
            activity = activity,
            reason = A1PriorityLearningReason.DUE_STARTER_REVIEW
        )
    }

    val transferNewWork = a1SecondTransferNewWorkSelection(
        languageCode = languageCode,
        evidence = evidence
    )
    if (
        transferNewWork.reason == A1SecondTransferNewWorkReason.COMPREHENSION ||
        transferNewWork.reason == A1SecondTransferNewWorkReason.RETRIEVAL_STEP
    ) {
        return A1PriorityLearningSelection(
            activity = requireNotNull(transferNewWork.activity),
            reason = A1PriorityLearningReason.SECOND_TRANSFER_NEW_WORK,
            transferNewWorkReason = transferNewWork.reason
        )
    }

    return A1PriorityLearningSelection(
        activity = null,
        reason = A1PriorityLearningReason.NONE,
        transferNewWorkReason = transferNewWork.reason
    )
}
