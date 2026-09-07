package com.chiu.know.model

enum class A1SecondTransferReviewReason {
    DUE_REVIEW,
    NONE_DUE,
    NO_SCHEDULE,
    NO_CONTENT
}

data class A1SecondTransferReviewSelection(
    val activity: LearningActivity?,
    val reason: A1SecondTransferReviewReason,
    val nextDueAtEpochMillis: Long? = null
) {
    init {
        require((activity != null) == (reason == A1SecondTransferReviewReason.DUE_REVIEW)) {
            "Second-transfer review reason and activity availability must agree"
        }
        require(nextDueAtEpochMillis == null || nextDueAtEpochMillis >= 0L) {
            "Next due timestamp must not be negative"
        }
    }
}

/**
 * Closed retrieval variants eligible for spaced review after the second A1
 * transfer narrative.
 *
 * The easier full-answer multiple-choice cue is deliberately excluded here.
 * Review alternates only between the already validated REORDER and FILL_IN
 * variants for residence and preference. These activities remain structured
 * READING evidence: they are not free writing, speaking, pronunciation evidence
 * or automatic mastery.
 */
fun a1SecondTransferReviewActivitiesFor(languageCode: String): List<LearningActivity> =
    a1SecondTransferNarrativeResidenceReorderRetrievalActivitiesFor(languageCode) +
        a1SecondTransferNarrativeResidenceFillInRetrievalActivitiesFor(languageCode) +
        a1SecondTransferNarrativePreferenceReorderRetrievalActivitiesFor(languageCode) +
        a1SecondTransferNarrativePreferenceFillInRetrievalActivitiesFor(languageCode)

/**
 * Selects only already scheduled, due second-transfer review targets.
 *
 * This queue never introduces an unscheduled target and never creates or mutates
 * scheduler state. ReviewScheduleState must come from observed attempts through
 * the existing review scheduler. When a target is due, observed attempts rotate
 * its two strong closed variants (REORDER/FILL_IN). Due time always wins; ties
 * preserve the stable narrative order (residence before preference).
 */
fun a1SecondTransferReviewSelection(
    languageCode: String,
    evidence: List<LearningEvidence>,
    schedules: List<ReviewScheduleState>,
    nowEpochMillis: Long
): A1SecondTransferReviewSelection {
    require(nowEpochMillis >= 0L) { "Queue time must not be negative" }

    val candidates = a1SecondTransferReviewActivitiesFor(languageCode)
    if (candidates.isEmpty()) {
        return A1SecondTransferReviewSelection(
            activity = null,
            reason = A1SecondTransferReviewReason.NO_CONTENT
        )
    }

    val groups = candidates.groupBy { it.reviewKey }
    val compatibleSchedules = schedules
        .filter { it.reviewKey in groups }
        .associateBy { it.reviewKey }

    if (compatibleSchedules.isEmpty()) {
        return A1SecondTransferReviewSelection(
            activity = null,
            reason = A1SecondTransferReviewReason.NO_SCHEDULE
        )
    }

    val dueTarget = compatibleSchedules.values
        .filter { it.dueAtEpochMillis <= nowEpochMillis }
        .minWithOrNull(
            compareBy<ReviewScheduleState> { it.dueAtEpochMillis }
                .thenBy { groups.keys.indexOf(it.reviewKey) }
        )

    if (dueTarget != null) {
        val variants = groups.getValue(dueTarget.reviewKey)
        val relevantAttempts = evidence.count { attempt ->
            attempt.level == CefrLevel.A1 && attempt.reviewKey == dueTarget.reviewKey
        }
        return A1SecondTransferReviewSelection(
            activity = variants[Math.floorMod(relevantAttempts, variants.size)],
            reason = A1SecondTransferReviewReason.DUE_REVIEW
        )
    }

    return A1SecondTransferReviewSelection(
        activity = null,
        reason = A1SecondTransferReviewReason.NONE_DUE,
        nextDueAtEpochMillis = compatibleSchedules.values.minOf { it.dueAtEpochMillis }
    )
}
