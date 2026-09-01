package com.chiu.know.model

/**
 * Local prototype engine for future adaptive placement.
 *
 * This is infrastructure only: it is not a validated CEFR scoring model and is
 * intentionally not wired to the current UI yet. The existing six-question
 * placement flow remains unchanged until this engine is validated separately.
 */
data class AdaptivePlacementState(
    val lowerBoundIndex: Int,
    val upperBoundIndex: Int,
    val currentLevelIndex: Int,
    val answeredQuestions: Int = 0
) {
    init {
        require(lowerBoundIndex in CEFR_LEVELS.indices)
        require(upperBoundIndex in CEFR_LEVELS.indices)
        require(lowerBoundIndex <= upperBoundIndex)
        require(currentLevelIndex in lowerBoundIndex..upperBoundIndex)
        require(answeredQuestions >= 0)
    }

    val currentLevel: CefrLevel
        get() = CEFR_LEVELS[currentLevelIndex]

    val estimatedLevel: CefrLevel
        get() = CEFR_LEVELS[lowerBoundIndex]

    val isFinished: Boolean
        get() = lowerBoundIndex == upperBoundIndex
}

data class AdaptivePlacementStep(
    val state: AdaptivePlacementState,
    val nextLevel: CefrLevel?,
    val estimatedLevel: CefrLevel,
    val finished: Boolean
)

private val CEFR_LEVELS = CefrLevel.entries

fun startAdaptivePlacement(): AdaptivePlacementState {
    val startIndex = CEFR_LEVELS.indexOf(CefrLevel.B1)
    return AdaptivePlacementState(
        lowerBoundIndex = CEFR_LEVELS.indices.first,
        upperBoundIndex = CEFR_LEVELS.lastIndex,
        currentLevelIndex = startIndex
    )
}

fun advanceAdaptivePlacement(
    state: AdaptivePlacementState,
    answeredCorrectly: Boolean
): AdaptivePlacementStep {
    if (state.isFinished) {
        return AdaptivePlacementStep(
            state = state,
            nextLevel = null,
            estimatedLevel = state.estimatedLevel,
            finished = true
        )
    }

    val newLowerBound = if (answeredCorrectly) {
        maxOf(state.lowerBoundIndex, state.currentLevelIndex)
    } else {
        state.lowerBoundIndex
    }

    val newUpperBound = if (answeredCorrectly) {
        state.upperBoundIndex
    } else {
        minOf(state.upperBoundIndex, maxOf(state.lowerBoundIndex, state.currentLevelIndex - 1))
    }

    val finished = newLowerBound == newUpperBound
    val nextIndex = if (finished) {
        newLowerBound
    } else {
        (newLowerBound + newUpperBound + 1) / 2
    }

    val nextState = AdaptivePlacementState(
        lowerBoundIndex = newLowerBound,
        upperBoundIndex = newUpperBound,
        currentLevelIndex = nextIndex,
        answeredQuestions = state.answeredQuestions + 1
    )

    return AdaptivePlacementStep(
        state = nextState,
        nextLevel = if (finished) null else nextState.currentLevel,
        estimatedLevel = nextState.estimatedLevel,
        finished = finished
    )
}
