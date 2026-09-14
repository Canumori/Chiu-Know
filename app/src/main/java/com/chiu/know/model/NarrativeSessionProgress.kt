package com.chiu.know.model

enum class NarrativeSessionPhase {
    STORY,
    COMPREHENSION,
    COMPLETE
}

/**
 * Immutable progress through one story and its dedicated comprehension checks.
 *
 * The state controls presentation order only. It does not persist evidence,
 * update schedules or interpret completion as mastery or transfer.
 */
data class NarrativeSessionProgress(
    val cardProgress: NarrativeCardProgress,
    val comprehensionCount: Int,
    val comprehensionIndex: Int? = null,
    val phase: NarrativeSessionPhase = NarrativeSessionPhase.STORY
) {
    init {
        require(comprehensionCount > 0) {
            "Narrative session requires at least one comprehension activity"
        }
        when (phase) {
            NarrativeSessionPhase.STORY -> require(comprehensionIndex == null) {
                "Story phase cannot expose a comprehension activity"
            }
            NarrativeSessionPhase.COMPREHENSION -> require(
                comprehensionIndex != null && comprehensionIndex in 0 until comprehensionCount
            ) {
                "Comprehension phase requires a valid activity index"
            }
            NarrativeSessionPhase.COMPLETE -> require(
                cardProgress.completed && comprehensionIndex == null
            ) {
                "Complete narrative session must finish cards and clear comprehension"
            }
        }
    }
}

fun narrativeSessionProgressFor(
    narrative: NarrativeMicroUnit,
    comprehensionActivities: List<LearningActivity>
): NarrativeSessionProgress {
    require(comprehensionActivities.isNotEmpty()) {
        "Narrative session requires comprehension activities"
    }
    require(comprehensionActivities.all { it.level == narrative.level }) {
        "Narrative and comprehension activities must share one CEFR level"
    }
    require(comprehensionActivities.all { it.id.startsWith("${narrative.languageCode}-") }) {
        "Narrative and comprehension activities must share one target language"
    }

    return NarrativeSessionProgress(
        cardProgress = narrativeCardProgressFor(narrative),
        comprehensionCount = comprehensionActivities.size
    )
}

fun advanceNarrativeSessionStory(
    progress: NarrativeSessionProgress
): NarrativeSessionProgress {
    if (progress.phase != NarrativeSessionPhase.STORY) return progress

    val advancedCards = advanceNarrativeCardProgress(progress.cardProgress)
    return if (advancedCards.completed) {
        progress.copy(
            cardProgress = advancedCards,
            comprehensionIndex = 0,
            phase = NarrativeSessionPhase.COMPREHENSION
        )
    } else {
        progress.copy(cardProgress = advancedCards)
    }
}

/**
 * Advances after a correctly completed comprehension activity.
 *
 * Incorrect answers remain on the current activity and therefore must not call
 * this function.
 */
fun advanceNarrativeSessionComprehension(
    progress: NarrativeSessionProgress
): NarrativeSessionProgress {
    if (progress.phase != NarrativeSessionPhase.COMPREHENSION) return progress

    val currentIndex = requireNotNull(progress.comprehensionIndex)
    return if (currentIndex == progress.comprehensionCount - 1) {
        progress.copy(
            comprehensionIndex = null,
            phase = NarrativeSessionPhase.COMPLETE
        )
    } else {
        progress.copy(comprehensionIndex = currentIndex + 1)
    }
}
