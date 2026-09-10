package com.chiu.know.model

/**
 * Quality-first stopping contract for the future production placement test.
 *
 * Duration is deliberately not the primary target. The test should collect
 * enough evidence to make a useful placement decision, then stop; measured
 * completion time can be optimized later without weakening evidence quality.
 *
 * This policy is not wired to the current placement UI yet because the current
 * bank still has only two questions per CEFR level. Expanding and validating
 * the bank comes first so confirmation does not simply repeat items.
 */
data class PlacementQualityPolicy(
    val minimumAnsweredQuestions: Int = 8,
    val confirmationQuestionsAtEstimatedLevel: Int = 2,
    val adjacentBoundaryQuestions: Int = 2,
    val maximumAnsweredQuestions: Int = 14
) {
    init {
        require(minimumAnsweredQuestions > 0)
        require(confirmationQuestionsAtEstimatedLevel > 0)
        require(adjacentBoundaryQuestions > 0)
        require(maximumAnsweredQuestions >= minimumAnsweredQuestions)
    }
}

enum class PlacementPhase {
    LOCATE,
    CONFIRM,
    COMPLETE
}

/**
 * Determines only the broad test phase. It does not invent CEFR confidence or
 * mastery: confirmation still requires real questions and observed answers.
 */
fun placementPhase(
    adaptiveSearchFinished: Boolean,
    answeredQuestions: Int,
    policy: PlacementQualityPolicy = PlacementQualityPolicy()
): PlacementPhase {
    require(answeredQuestions >= 0)

    if (answeredQuestions >= policy.maximumAnsweredQuestions) return PlacementPhase.COMPLETE
    if (!adaptiveSearchFinished || answeredQuestions < policy.minimumAnsweredQuestions) {
        return PlacementPhase.LOCATE
    }
    return PlacementPhase.CONFIRM
}
