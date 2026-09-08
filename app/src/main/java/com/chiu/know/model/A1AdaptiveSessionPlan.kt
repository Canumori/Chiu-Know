package com.chiu.know.model

data class A1AdaptiveSessionPlanning(
    val reviewLoad: A1ReviewLoad,
    val sessionPlan: AdaptiveSessionPlan
) {
    init {
        require(sessionPlan.reservedDueReviews <= reviewLoad.dueReviewCount) {
            "Reserved reviews cannot exceed the observed A1 due-review load"
        }
    }
}

/**
 * Connects A1 review-load aggregation to the existing adaptive session planner.
 *
 * This remains planning-only. It does not select a review activity, resolve
 * precedence between review queues, expose the second-transfer unit in UI,
 * mutate schedules, create evidence, or claim mastery. Review load is derived
 * only from eligible A1 schedules, while practice-need evidence is limited to A1.
 */
fun a1AdaptiveSessionPlanning(
    languageCode: String,
    preferences: LearnerPreferences,
    schedules: List<ReviewScheduleState>,
    evidence: List<LearningEvidence>,
    nowEpochMillis: Long
): A1AdaptiveSessionPlanning {
    val reviewLoad = a1ReviewLoad(
        languageCode = languageCode,
        schedules = schedules,
        nowEpochMillis = nowEpochMillis
    )
    val sessionPlan = adaptiveSessionPlan(
        preferences = preferences,
        dueReviewCount = reviewLoad.dueReviewCount,
        evidence = evidence.filter { it.level == CefrLevel.A1 }
    )

    return A1AdaptiveSessionPlanning(
        reviewLoad = reviewLoad,
        sessionPlan = sessionPlan
    )
}
