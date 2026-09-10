package com.chiu.know.model

/**
 * Descriptive observations for one evidence claim at one CEFR level.
 *
 * A claim summary is not mastery, a proficiency score or certification. One
 * attempt may legitimately contribute to more than one claim when the known
 * activity contract supports each of them.
 */
data class LearningClaimEvidenceSummary(
    val claim: EvidenceClaim,
    val level: CefrLevel,
    val totalAttempts: Int,
    val correctAttempts: Int,
    val incorrectAttempts: Int,
    val distinctReviewTargetCount: Int,
    val firstAttemptAtEpochMillis: Long,
    val latestAttemptAtEpochMillis: Long
) {
    init {
        require(totalAttempts > 0) { "Claim summary must contain at least one attempt" }
        require(correctAttempts >= 0) { "Correct attempts must not be negative" }
        require(incorrectAttempts >= 0) { "Incorrect attempts must not be negative" }
        require(correctAttempts + incorrectAttempts == totalAttempts) {
            "Correct and incorrect attempts must add up to total attempts"
        }
        require(distinctReviewTargetCount in 1..totalAttempts) {
            "Distinct review target count must fit the observed attempts"
        }
        require(firstAttemptAtEpochMillis >= 0L) { "First timestamp must not be negative" }
        require(latestAttemptAtEpochMillis >= firstAttemptAtEpochMillis) {
            "Latest timestamp must not precede first timestamp"
        }
    }
}

/**
 * Joins persisted observations to known activities and aggregates only claims
 * supported by those activity contracts.
 *
 * Evidence for an unknown activity is omitted rather than guessed. Claims that
 * still require dedicated evaluation (free writing, speaking, pronunciation and
 * interaction) are also omitted even when their format could support them in
 * the future. Activity IDs must be unique so historical evidence can never be
 * assigned ambiguously.
 */
private val claimsAwaitingDedicatedEvaluation = setOf(
    EvidenceClaim.WRITTEN_PRODUCTION,
    EvidenceClaim.SPOKEN_PRODUCTION,
    EvidenceClaim.PRONUNCIATION,
    EvidenceClaim.INTERACTION
)

private fun currentlyObservableEvidenceClaims(
    activity: LearningActivity
): Set<EvidenceClaim> = supportedEvidenceClaims(activity) - claimsAwaitingDedicatedEvaluation

fun summarizeLearningEvidenceByClaim(
    evidence: List<LearningEvidence>,
    activities: List<LearningActivity>
): List<LearningClaimEvidenceSummary> {
    val activityIds = activities.map { it.id }
    require(activityIds.distinct().size == activityIds.size) {
        "Activity IDs must be unique when summarizing evidence claims"
    }
    val activitiesById = activities.associateBy { it.id }

    return evidence
        .flatMap { attempt ->
            val activity = activitiesById[attempt.activityId] ?: return@flatMap emptyList()
            currentlyObservableEvidenceClaims(activity).map { claim -> (claim to attempt.level) to attempt }
        }
        .groupBy(keySelector = { it.first }, valueTransform = { it.second })
        .map { (identity, attempts) ->
            val correct = attempts.count { it.correct }
            LearningClaimEvidenceSummary(
                claim = identity.first,
                level = identity.second,
                totalAttempts = attempts.size,
                correctAttempts = correct,
                incorrectAttempts = attempts.size - correct,
                distinctReviewTargetCount = attempts.map { it.reviewKey }.distinct().size,
                firstAttemptAtEpochMillis = attempts.minOf { it.attemptedAtEpochMillis },
                latestAttemptAtEpochMillis = attempts.maxOf { it.attemptedAtEpochMillis }
            )
        }
        .sortedByDescending { it.latestAttemptAtEpochMillis }
}
