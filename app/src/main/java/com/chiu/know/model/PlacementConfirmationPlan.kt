package com.chiu.know.model

/**
 * Evidence-collection plan used after the locating phase of placement.
 *
 * This deliberately does not score or change the learner's CEFR level. Its job
 * is narrower: request fresh evidence around the provisional level so a later,
 * separately validated decision rule has enough information to confirm or
 * revise that estimate.
 */
data class PlacementConfirmationTarget(
    val level: CefrLevel,
    val role: PlacementConfirmationRole
)

enum class PlacementConfirmationRole {
    ESTIMATED_LEVEL,
    LOWER_BOUNDARY,
    UPPER_BOUNDARY
}

/**
 * Builds the minimum confirmation targets required by [PlacementQualityPolicy].
 *
 * Interior CEFR levels sample both adjacent boundaries when at least two
 * boundary questions are requested. Edge levels use the only available
 * adjacent boundary. The function is deterministic and contains no confidence
 * score or psychometric claim.
 */
fun placementConfirmationTargets(
    estimatedLevel: CefrLevel,
    policy: PlacementQualityPolicy = PlacementQualityPolicy()
): List<PlacementConfirmationTarget> {
    val levels = CefrLevel.entries
    val estimatedIndex = levels.indexOf(estimatedLevel)
    val targets = mutableListOf<PlacementConfirmationTarget>()

    repeat(policy.confirmationQuestionsAtEstimatedLevel) {
        targets += PlacementConfirmationTarget(
            level = estimatedLevel,
            role = PlacementConfirmationRole.ESTIMATED_LEVEL
        )
    }

    val lower = levels.getOrNull(estimatedIndex - 1)
    val upper = levels.getOrNull(estimatedIndex + 1)

    repeat(policy.adjacentBoundaryQuestions) { index ->
        val target = when {
            lower != null && upper != null -> {
                if (index % 2 == 0) {
                    PlacementConfirmationTarget(lower, PlacementConfirmationRole.LOWER_BOUNDARY)
                } else {
                    PlacementConfirmationTarget(upper, PlacementConfirmationRole.UPPER_BOUNDARY)
                }
            }
            lower != null -> PlacementConfirmationTarget(lower, PlacementConfirmationRole.LOWER_BOUNDARY)
            upper != null -> PlacementConfirmationTarget(upper, PlacementConfirmationRole.UPPER_BOUNDARY)
            else -> error("CEFR scale must contain an adjacent level")
        }
        targets += target
    }

    return targets
}

/**
 * Selects one fresh question for each confirmation target, preserving order.
 * Returns null if the bank cannot satisfy the plan without repetition. The
 * caller must treat that as insufficient evidence rather than recycling items.
 */
fun selectPlacementConfirmationQuestions(
    questions: List<PlacementQuestion>,
    targets: List<PlacementConfirmationTarget>,
    usedQuestionIds: Set<String>
): List<PlacementQuestion>? {
    val used = usedQuestionIds.toMutableSet()
    val selected = mutableListOf<PlacementQuestion>()

    for (target in targets) {
        val question = nextUnusedPlacementQuestion(
            questions = questions,
            level = target.level,
            usedQuestionIds = used
        ) ?: return null
        selected += question
        used += question.id
    }

    return selected
}
