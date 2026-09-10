package com.chiu.know.model

/**
 * Compatibility adapter for the current learning-activity UI.
 *
 * A1 uses the composed review-first queue, including connected second-transfer
 * work. Other CEFR levels preserve the existing starter queue exactly. The
 * result intentionally reuses StarterQueueSelection so the current screen can
 * keep its NO_CONTENT/NONE_DUE/activity handling and generic evidence/FSRS
 * persistence unchanged when it is wired to this function.
 */
fun learningActivityQueueSelection(
    languageCode: String,
    level: CefrLevel,
    evidence: List<LearningEvidence>,
    schedules: List<ReviewScheduleState>,
    nowEpochMillis: Long,
    preferences: LearnerPreferences? = null
): StarterQueueSelection {
    require(nowEpochMillis >= 0L) { "Learning activity queue time must not be negative" }

    if (level != CefrLevel.A1) {
        return starterQueueSelection(
            languageCode = languageCode,
            level = level,
            evidence = evidence,
            schedules = schedules,
            nowEpochMillis = nowEpochMillis,
            preferences = preferences
        )
    }

    val a1Selection = a1LearningQueueSelection(
        languageCode = languageCode,
        evidence = evidence,
        schedules = schedules,
        nowEpochMillis = nowEpochMillis,
        preferences = preferences
    )

    return when (a1Selection.reason) {
        A1LearningQueueReason.DUE_STARTER_REVIEW,
        A1LearningQueueReason.DUE_SECOND_TRANSFER_REVIEW -> StarterQueueSelection(
            activity = requireNotNull(a1Selection.activity),
            reason = StarterQueueReason.DUE_REVIEW
        )
        A1LearningQueueReason.SECOND_TRANSFER_NEW_WORK,
        A1LearningQueueReason.STARTER_NEW_TARGET -> StarterQueueSelection(
            activity = requireNotNull(a1Selection.activity),
            reason = StarterQueueReason.NEW_TARGET
        )
        A1LearningQueueReason.NONE_DUE -> StarterQueueSelection(
            activity = null,
            reason = StarterQueueReason.NONE_DUE,
            nextDueAtEpochMillis = a1Selection.nextDueAtEpochMillis
        )
        A1LearningQueueReason.NO_CONTENT -> StarterQueueSelection(
            activity = null,
            reason = StarterQueueReason.NO_CONTENT
        )
    }
}
