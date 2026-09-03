package com.chiu.know.model

/**
 * One observed answer collected specifically for placement confirmation.
 * This is evidence, not a mastery score or calibrated psychometric estimate.
 */
data class PlacementConfirmationEvidence(
    val questionId: String,
    val level: CefrLevel,
    val role: PlacementConfirmationRole,
    val correct: Boolean
)

enum class PlacementDecisionStatus {
    CONFIRMED,
    REVISED_DOWN,
    REVISED_UP,
    NEEDS_MORE_EVIDENCE,
    BANK_INSUFFICIENT
}

data class PlacementConfirmationDecision(
    val provisionalLevel: CefrLevel,
    val decidedLevel: CefrLevel?,
    val status: PlacementDecisionStatus
)

/**
 * Conservative first decision rule for confirmation evidence.
 *
 * It deliberately moves at most one CEFR band from the provisional estimate.
 * The thresholds are engineering rules to make behavior deterministic and
 * testable; they are not calibrated probabilities, confidence percentages or
 * claims of psychometric validation.
 *
 * Required evidence must be complete before a level can be confirmed/revised.
 * If the bank cannot provide that evidence, callers should use
 * [PlacementDecisionStatus.BANK_INSUFFICIENT] rather than recycle questions.
 */
fun decidePlacementFromConfirmation(
    provisionalLevel: CefrLevel,
    evidence: List<PlacementConfirmationEvidence>,
    policy: PlacementQualityPolicy = PlacementQualityPolicy(),
    bankInsufficient: Boolean = false
): PlacementConfirmationDecision {
    if (bankInsufficient) {
        return PlacementConfirmationDecision(
            provisionalLevel = provisionalLevel,
            decidedLevel = null,
            status = PlacementDecisionStatus.BANK_INSUFFICIENT
        )
    }

    val expectedTargets = placementConfirmationTargets(provisionalLevel, policy)
    val evidenceByRole = evidence.groupBy { it.role }
    val expectedByRole = expectedTargets.groupingBy { it.role }.eachCount()

    val hasRequiredCounts = expectedByRole.all { (role, required) ->
        evidenceByRole[role].orEmpty().size >= required
    }
    val evidenceMatchesTargets = evidence.all { item ->
        expectedTargets.any { it.level == item.level && it.role == item.role }
    }
    val uniqueQuestionIds = evidence.map { it.questionId }.distinct().size == evidence.size

    if (!hasRequiredCounts || !evidenceMatchesTargets || !uniqueQuestionIds) {
        return PlacementConfirmationDecision(
            provisionalLevel = provisionalLevel,
            decidedLevel = null,
            status = PlacementDecisionStatus.NEEDS_MORE_EVIDENCE
        )
    }

    val estimatedEvidence = evidenceByRole[PlacementConfirmationRole.ESTIMATED_LEVEL].orEmpty()
    val lowerEvidence = evidenceByRole[PlacementConfirmationRole.LOWER_BOUNDARY].orEmpty()
    val upperEvidence = evidenceByRole[PlacementConfirmationRole.UPPER_BOUNDARY].orEmpty()

    val failedEstimated = estimatedEvidence.count { !it.correct }
    val passedEstimated = estimatedEvidence.count { it.correct }
    val passedUpper = upperEvidence.count { it.correct }
    val failedLower = lowerEvidence.count { !it.correct }

    val levels = CefrLevel.entries
    val index = levels.indexOf(provisionalLevel)
    val lower = levels.getOrNull(index - 1)
    val upper = levels.getOrNull(index + 1)

    // Downward revision requires weak performance at the provisional level and,
    // when a lower boundary exists, failure there as corroborating evidence.
    if (lower != null && failedEstimated == estimatedEvidence.size &&
        lowerEvidence.isNotEmpty() && failedLower > 0
    ) {
        return PlacementConfirmationDecision(
            provisionalLevel = provisionalLevel,
            decidedLevel = lower,
            status = PlacementDecisionStatus.REVISED_DOWN
        )
    }

    // Upward revision is intentionally stricter: all provisional evidence and
    // all available upper-boundary evidence must be correct.
    if (upper != null && passedEstimated == estimatedEvidence.size &&
        upperEvidence.isNotEmpty() && passedUpper == upperEvidence.size
    ) {
        return PlacementConfirmationDecision(
            provisionalLevel = provisionalLevel,
            decidedLevel = upper,
            status = PlacementDecisionStatus.REVISED_UP
        )
    }

    return PlacementConfirmationDecision(
        provisionalLevel = provisionalLevel,
        decidedLevel = provisionalLevel,
        status = PlacementDecisionStatus.CONFIRMED
    )
}
