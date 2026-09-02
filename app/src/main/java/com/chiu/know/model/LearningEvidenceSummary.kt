package com.chiu.know.model

/**
 * Descriptive aggregation of observed attempts for one review target.
 *
 * This summary is intentionally not a mastery score. It exposes only evidence
 * already observed so later review/progression logic can reason about attempts
 * and recency without turning one correct answer into durable learning.
 */
data class LearningEvidenceSummary(
    val activityId: String,
    val reviewKey: String,
    val totalAttempts: Int,
    val correctAttempts: Int,
    val incorrectAttempts: Int,
    val latestAttemptAtEpochMillis: Long,
    val latestWasCorrect: Boolean
) {
    init {
        require(activityId.isNotBlank()) { "Summary activity id must not be blank" }
        require(reviewKey.isNotBlank()) { "Summary review key must not be blank" }
        require(totalAttempts > 0) { "Summary must contain at least one attempt" }
        require(correctAttempts >= 0) { "Correct attempts must not be negative" }
        require(incorrectAttempts >= 0) { "Incorrect attempts must not be negative" }
        require(correctAttempts + incorrectAttempts == totalAttempts) {
            "Correct and incorrect attempts must add up to total attempts"
        }
        require(latestAttemptAtEpochMillis >= 0L) { "Latest timestamp must not be negative" }
    }
}

/**
 * Groups evidence by activity/review identity and returns newest summaries first.
 * No proficiency, CEFR advancement or mastery decision is inferred here.
 */
fun summarizeLearningEvidence(evidence: List<LearningEvidence>): List<LearningEvidenceSummary> =
    evidence
        .groupBy { it.activityId to it.reviewKey }
        .map { (identity, attempts) ->
            val latest = attempts.maxBy { it.attemptedAtEpochMillis }
            val correct = attempts.count { it.correct }
            LearningEvidenceSummary(
                activityId = identity.first,
                reviewKey = identity.second,
                totalAttempts = attempts.size,
                correctAttempts = correct,
                incorrectAttempts = attempts.size - correct,
                latestAttemptAtEpochMillis = latest.attemptedAtEpochMillis,
                latestWasCorrect = latest.correct
            )
        }
        .sortedByDescending { it.latestAttemptAtEpochMillis }
