package com.chiu.know.model

/**
 * Planning-only description of one learning session.
 *
 * The planner deliberately does not infer CEFR, mastery, retention or ability.
 * It only converts learner-declared time/preferences plus known due-review load
 * into a bounded workload and a relative practice mix.
 */
data class AdaptiveSessionPlan(
    val targetMinutes: Int,
    val targetActivities: Int,
    val reservedDueReviews: Int,
    val newPracticeSlots: Int,
    val practiceMix: LearnerPracticeMix
) {
    init {
        require(targetMinutes in LearnerPreferences.MIN_DAILY_MINUTES..LearnerPreferences.MAX_DAILY_MINUTES)
        require(targetActivities >= 1)
        require(reservedDueReviews in 0..targetActivities)
        require(newPracticeSlots == targetActivities - reservedDueReviews)
    }
}

/**
 * Builds a conservative workload without changing the review queue itself.
 *
 * Rules:
 * - dailyMinutes is a planning preference only;
 * - roughly one activity slot per 2 minutes, bounded to avoid huge sessions;
 * - due reviews reserve the first available slots;
 * - preferences affect only the mix of non-review practice;
 * - a large due backlog may exceed one session and is never silently marked done.
 */
fun adaptiveSessionPlan(
    preferences: LearnerPreferences,
    dueReviewCount: Int
): AdaptiveSessionPlan {
    require(dueReviewCount >= 0)

    val activityBudget = (preferences.dailyMinutes / MINUTES_PER_ACTIVITY)
        .coerceIn(MIN_ACTIVITIES, MAX_ACTIVITIES)
    val reservedReviews = dueReviewCount.coerceAtMost(activityBudget)

    return AdaptiveSessionPlan(
        targetMinutes = preferences.dailyMinutes,
        targetActivities = activityBudget,
        reservedDueReviews = reservedReviews,
        newPracticeSlots = activityBudget - reservedReviews,
        practiceMix = learnerPracticeMix(preferences)
    )
}

private const val MINUTES_PER_ACTIVITY = 2
private const val MIN_ACTIVITIES = 3
private const val MAX_ACTIVITIES = 30
