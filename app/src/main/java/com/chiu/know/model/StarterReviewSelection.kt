package com.chiu.know.model

/**
 * Selects the next starter activity from observed evidence only.
 *
 * This is deliberately not a mastery or spaced-repetition algorithm. It keeps
 * review targets balanced by choosing the reviewKey with the fewest observed
 * attempts, then rotates through that target's contextual variants. Ties keep
 * the stable curriculum order.
 */
fun starterLearningActivityForEvidence(
    languageCode: String,
    level: CefrLevel,
    evidence: List<LearningEvidence>
): LearningActivity? {
    val candidates = starterLearningActivitiesFor(languageCode).filter { it.level == level }
    if (candidates.isEmpty()) return null

    val groups = candidates.groupBy { it.reviewKey }
    val relevantEvidence = evidence.filter { attempt ->
        attempt.level == level && attempt.reviewKey in groups
    }

    val targetReviewKey = groups.keys.minBy { reviewKey ->
        relevantEvidence.count { it.reviewKey == reviewKey }
    }
    val targetCandidates = groups.getValue(targetReviewKey)
    val targetAttemptCount = relevantEvidence.count { it.reviewKey == targetReviewKey }

    return targetCandidates[Math.floorMod(targetAttemptCount, targetCandidates.size)]
}
