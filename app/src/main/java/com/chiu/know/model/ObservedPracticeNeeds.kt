package com.chiu.know.model

/**
 * Descriptive planning signal based only on observed recent attempts.
 *
 * This is not a proficiency score, mastery estimate, diagnosis or CEFR claim.
 * It only helps decide where to spend extra practice time inside a session.
 */
data class ObservedPracticeNeed(
    val skill: LearningSkill,
    val observedAttempts: Int,
    val incorrectAttempts: Int,
    val correctAttempts: Int,
    val latestAttemptAtEpochMillis: Long,
    val latestWasIncorrect: Boolean
) {
    init {
        require(observedAttempts > 0)
        require(incorrectAttempts >= 0)
        require(correctAttempts >= 0)
        require(incorrectAttempts + correctAttempts == observedAttempts)
        require(latestAttemptAtEpochMillis >= 0L)
    }
}

/**
 * Returns descriptive recent evidence per skill, newest skill signal first.
 * Each skill is limited to a small recent window so old history cannot dominate
 * current planning forever.
 */
fun observedPracticeNeeds(
    evidence: List<LearningEvidence>,
    recentAttemptsPerSkill: Int = DEFAULT_RECENT_ATTEMPTS_PER_SKILL
): List<ObservedPracticeNeed> {
    require(recentAttemptsPerSkill > 0)

    return evidence
        .groupBy { it.primarySkill }
        .map { (skill, attempts) ->
            val recent = attempts
                .sortedByDescending { it.attemptedAtEpochMillis }
                .take(recentAttemptsPerSkill)
            val incorrect = recent.count { !it.correct }
            val latest = recent.first()

            ObservedPracticeNeed(
                skill = skill,
                observedAttempts = recent.size,
                incorrectAttempts = incorrect,
                correctAttempts = recent.size - incorrect,
                latestAttemptAtEpochMillis = latest.attemptedAtEpochMillis,
                latestWasIncorrect = !latest.correct
            )
        }
        .sortedByDescending { it.latestAttemptAtEpochMillis }
}

/**
 * True only when there is repeated recent evidence that extra practice may be
 * useful. A single mistake is intentionally insufficient.
 */
fun ObservedPracticeNeed.warrantsExtraPractice(): Boolean =
    observedAttempts >= MIN_ATTEMPTS_FOR_EXTRA_PRACTICE &&
        incorrectAttempts >= MIN_INCORRECT_FOR_EXTRA_PRACTICE &&
        incorrectAttempts > correctAttempts

private const val DEFAULT_RECENT_ATTEMPTS_PER_SKILL = 6
private const val MIN_ATTEMPTS_FOR_EXTRA_PRACTICE = 3
private const val MIN_INCORRECT_FOR_EXTRA_PRACTICE = 2
