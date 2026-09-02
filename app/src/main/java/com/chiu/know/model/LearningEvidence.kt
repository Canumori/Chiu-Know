package com.chiu.know.model

/**
 * One observed attempt at a learning activity.
 *
 * This is evidence of a specific retrieval attempt, not proof of mastery and
 * not an XP/progression event. Later review logic can aggregate attempts over
 * time without confusing a single correct answer with durable learning.
 */
data class LearningEvidence(
    val activityId: String,
    val reviewKey: String,
    val level: CefrLevel,
    val primarySkill: LearningSkill,
    val correct: Boolean,
    val attemptedAtEpochMillis: Long
) {
    init {
        require(activityId.isNotBlank()) { "Evidence activity id must not be blank" }
        require(reviewKey.isNotBlank()) { "Evidence review key must not be blank" }
        require(attemptedAtEpochMillis >= 0L) { "Evidence timestamp must not be negative" }
    }
}

fun learningEvidenceFor(
    activity: LearningActivity,
    correct: Boolean,
    attemptedAtEpochMillis: Long
): LearningEvidence = LearningEvidence(
    activityId = activity.id,
    reviewKey = activity.reviewKey,
    level = activity.level,
    primarySkill = activity.primarySkill,
    correct = correct,
    attemptedAtEpochMillis = attemptedAtEpochMillis
)
