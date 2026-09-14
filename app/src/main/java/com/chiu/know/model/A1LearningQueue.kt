package com.chiu.know.model

enum class A1LearningQueueReason {
    DUE_STARTER_REVIEW,
    DUE_SECOND_TRANSFER_REVIEW,
    SECOND_TRANSFER_NEW_WORK,
    STARTER_NEW_TARGET,
    NONE_DUE,
    NO_CONTENT
}

data class A1LearningQueueSelection(
    val activity: LearningActivity?,
    val reason: A1LearningQueueReason,
    val nextDueAtEpochMillis: Long? = null,
    val transferNewWorkReason: A1SecondTransferNewWorkReason? = null
) {
    init {
        require(
            (activity != null) == (
                reason == A1LearningQueueReason.DUE_STARTER_REVIEW ||
                    reason == A1LearningQueueReason.DUE_SECOND_TRANSFER_REVIEW ||
                    reason == A1LearningQueueReason.SECOND_TRANSFER_NEW_WORK ||
                    reason == A1LearningQueueReason.STARTER_NEW_TARGET
                )
        ) { "A1 learning queue reason must agree with activity availability" }
        require(nextDueAtEpochMillis == null || nextDueAtEpochMillis >= 0L) {
            "A1 learning queue next due timestamp must not be negative"
        }
        require(
            transferNewWorkReason == null || reason == A1LearningQueueReason.SECOND_TRANSFER_NEW_WORK
        ) { "Transfer reason is only valid when transfer new work is selected" }
    }
}

/**
 * Composes the already validated A1 priority lane with the existing starter queue.
 *
 * The priority lane owns due-review ordering across starter and second-transfer
 * targets and the finite connected second-transfer new-work sequence. Only when
 * that lane has no activity does this function fall back to the starter queue for
 * an unscheduled new target or its normal NONE_DUE/NO_CONTENT state.
 *
 * This remains model-only: it does not persist evidence, mutate review schedules,
 * expose optional practice, change CEFR, or claim mastery.
 */
fun a1LearningQueueSelection(
    languageCode: String,
    evidence: List<LearningEvidence>,
    schedules: List<ReviewScheduleState>,
    nowEpochMillis: Long,
    preferences: LearnerPreferences? = null
): A1LearningQueueSelection {
    require(nowEpochMillis >= 0L) { "A1 learning queue time must not be negative" }

    val priority = a1PriorityLearningSelection(
        languageCode = languageCode,
        evidence = evidence,
        schedules = schedules,
        nowEpochMillis = nowEpochMillis,
        preferences = preferences
    )

    when (priority.reason) {
        A1PriorityLearningReason.DUE_STARTER_REVIEW -> return A1LearningQueueSelection(
            activity = requireNotNull(priority.activity),
            reason = A1LearningQueueReason.DUE_STARTER_REVIEW
        )
        A1PriorityLearningReason.DUE_SECOND_TRANSFER_REVIEW -> return A1LearningQueueSelection(
            activity = requireNotNull(priority.activity),
            reason = A1LearningQueueReason.DUE_SECOND_TRANSFER_REVIEW
        )
        A1PriorityLearningReason.SECOND_TRANSFER_NEW_WORK -> return A1LearningQueueSelection(
            activity = requireNotNull(priority.activity),
            reason = A1LearningQueueReason.SECOND_TRANSFER_NEW_WORK,
            transferNewWorkReason = requireNotNull(priority.transferNewWorkReason)
        )
        A1PriorityLearningReason.NONE -> Unit
    }

    val starter = starterQueueSelection(
        languageCode = languageCode,
        level = CefrLevel.A1,
        evidence = evidence,
        schedules = schedules,
        nowEpochMillis = nowEpochMillis,
        preferences = preferences
    )

    return when (starter.reason) {
        StarterQueueReason.DUE_REVIEW -> A1LearningQueueSelection(
            activity = requireNotNull(starter.activity),
            reason = A1LearningQueueReason.DUE_STARTER_REVIEW
        )
        StarterQueueReason.NEW_TARGET -> A1LearningQueueSelection(
            activity = requireNotNull(starter.activity),
            reason = A1LearningQueueReason.STARTER_NEW_TARGET
        )
        StarterQueueReason.NONE_DUE -> A1LearningQueueSelection(
            activity = null,
            reason = A1LearningQueueReason.NONE_DUE,
            nextDueAtEpochMillis = starter.nextDueAtEpochMillis
        )
        StarterQueueReason.NO_CONTENT -> A1LearningQueueSelection(
            activity = null,
            reason = A1LearningQueueReason.NO_CONTENT
        )
    }
}
