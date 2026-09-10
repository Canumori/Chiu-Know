package com.chiu.know.model

/**
 * Descriptive evidence observed for one knowledge/review target across all of
 * its activity variants.
 *
 * This is deliberately not a mastery score or a schedule. Correctness, recency
 * and contextual variety remain separate facts for a future review algorithm.
 */
data class ReviewEvidenceSummary(
    val reviewKey: String,
    val level: CefrLevel,
    val totalAttempts: Int,
    val correctAttempts: Int,
    val incorrectAttempts: Int,
    val distinctActivityCount: Int,
    val firstAttemptAtEpochMillis: Long,
    val latestAttemptAtEpochMillis: Long,
    val latestWasCorrect: Boolean
) {
    init {
        require(reviewKey.isNotBlank()) { "Review key must not be blank" }
        require(totalAttempts > 0) { "Summary must contain at least one attempt" }
        require(correctAttempts >= 0) { "Correct attempts must not be negative" }
        require(incorrectAttempts >= 0) { "Incorrect attempts must not be negative" }
        require(correctAttempts + incorrectAttempts == totalAttempts) {
            "Correct and incorrect attempts must add up to total attempts"
        }
        require(distinctActivityCount in 1..totalAttempts) {
            "Distinct activity count must fit the observed attempts"
        }
        require(firstAttemptAtEpochMillis >= 0L) { "First timestamp must not be negative" }
        require(latestAttemptAtEpochMillis >= firstAttemptAtEpochMillis) {
            "Latest timestamp must not precede first timestamp"
        }
    }
}

/**
 * Groups compatible evidence by level and reviewKey, newest target first.
 *
 * Activity variants sharing a reviewKey are intentionally combined. This lets
 * later scheduling distinguish repeated exposure to one prompt from retrieval
 * across multiple contexts without claiming that either proves mastery.
 */
fun summarizeReviewEvidence(evidence: List<LearningEvidence>): List<ReviewEvidenceSummary> =
    evidence
        .groupBy { it.level to it.reviewKey }
        .map { (identity, attempts) ->
            val first = attempts.minBy { it.attemptedAtEpochMillis }
            val latest = attempts.maxBy { it.attemptedAtEpochMillis }
            val correct = attempts.count { it.correct }
            ReviewEvidenceSummary(
                reviewKey = identity.second,
                level = identity.first,
                totalAttempts = attempts.size,
                correctAttempts = correct,
                incorrectAttempts = attempts.size - correct,
                distinctActivityCount = attempts.map { it.activityId }.distinct().size,
                firstAttemptAtEpochMillis = first.attemptedAtEpochMillis,
                latestAttemptAtEpochMillis = latest.attemptedAtEpochMillis,
                latestWasCorrect = latest.correct
            )
        }
        .sortedByDescending { it.latestAttemptAtEpochMillis }
