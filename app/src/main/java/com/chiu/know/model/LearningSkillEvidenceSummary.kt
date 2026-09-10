package com.chiu.know.model

/**
 * Descriptive evidence observed for one curriculum skill at one CEFR level.
 *
 * This is not a proficiency score, mastery state or CEFR sub-level. It keeps
 * attempts, correctness, target variety and recency as separate observed facts
 * so future product work can avoid manufacturing precision from sparse data.
 */
data class LearningSkillEvidenceSummary(
    val skill: LearningSkill,
    val level: CefrLevel,
    val totalAttempts: Int,
    val correctAttempts: Int,
    val incorrectAttempts: Int,
    val distinctReviewTargetCount: Int,
    val firstAttemptAtEpochMillis: Long,
    val latestAttemptAtEpochMillis: Long
) {
    init {
        require(totalAttempts > 0) { "Skill summary must contain at least one attempt" }
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
 * Aggregates only observed attempts by declared primary skill and CEFR level.
 *
 * Empty skills are omitted. The result is ordered by most recent observation
 * and deliberately does not calculate percentages, grades or mastery.
 */
fun summarizeLearningEvidenceBySkill(
    evidence: List<LearningEvidence>
): List<LearningSkillEvidenceSummary> =
    evidence
        .groupBy { it.level to it.primarySkill }
        .map { (identity, attempts) ->
            val correct = attempts.count { it.correct }
            LearningSkillEvidenceSummary(
                skill = identity.second,
                level = identity.first,
                totalAttempts = attempts.size,
                correctAttempts = correct,
                incorrectAttempts = attempts.size - correct,
                distinctReviewTargetCount = attempts.map { it.reviewKey }.distinct().size,
                firstAttemptAtEpochMillis = attempts.minOf { it.attemptedAtEpochMillis },
                latestAttemptAtEpochMillis = attempts.maxOf { it.attemptedAtEpochMillis }
            )
        }
        .sortedByDescending { it.latestAttemptAtEpochMillis }
