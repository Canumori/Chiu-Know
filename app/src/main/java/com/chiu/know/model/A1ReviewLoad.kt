package com.chiu.know.model

data class A1ReviewLoad(
    val eligibleReviewKeys: List<String>,
    val dueReviewKeys: List<String>,
    val nextDueAtEpochMillis: Long?
) {
    init {
        require(eligibleReviewKeys.distinct().size == eligibleReviewKeys.size) {
            "Eligible A1 review keys must be unique"
        }
        require(dueReviewKeys.distinct().size == dueReviewKeys.size) {
            "Due A1 review keys must be unique"
        }
        require(dueReviewKeys.all { it in eligibleReviewKeys }) {
            "Due A1 review keys must be eligible review targets"
        }
        require(nextDueAtEpochMillis == null || nextDueAtEpochMillis >= 0L) {
            "Next A1 review timestamp must not be negative"
        }
    }

    val dueReviewCount: Int
        get() = dueReviewKeys.size
}

/**
 * Aggregates the scheduled A1 review load across the starter curriculum and the
 * connected second-transfer review targets.
 *
 * This is planning data only. It does not select an activity, resolve precedence
 * between review queues, create or mutate ReviewScheduleState, claim mastery, or
 * pull a future review forward. The resulting dueReviewCount is intended for
 * AdaptiveSessionPlan, which already reserves due-review slots before new work.
 */
fun a1ReviewLoad(
    languageCode: String,
    schedules: List<ReviewScheduleState>,
    nowEpochMillis: Long
): A1ReviewLoad {
    require(nowEpochMillis >= 0L) { "Review load time must not be negative" }

    val eligibleReviewKeys = (
        starterLearningActivitiesFor(languageCode)
            .filter { it.level == CefrLevel.A1 }
            .map { it.reviewKey } +
            a1SecondTransferReviewActivitiesFor(languageCode).map { it.reviewKey }
        ).distinct()

    val compatibleSchedules = schedules
        .filter { it.reviewKey in eligibleReviewKeys }
        .associateBy { it.reviewKey }

    val dueReviewKeys = eligibleReviewKeys.filter { reviewKey ->
        compatibleSchedules[reviewKey]?.dueAtEpochMillis?.let { it <= nowEpochMillis } == true
    }

    val nextDueAtEpochMillis = compatibleSchedules.values
        .asSequence()
        .map { it.dueAtEpochMillis }
        .filter { it > nowEpochMillis }
        .minOrNull()

    return A1ReviewLoad(
        eligibleReviewKeys = eligibleReviewKeys,
        dueReviewKeys = dueReviewKeys,
        nextDueAtEpochMillis = nextDueAtEpochMillis
    )
}
