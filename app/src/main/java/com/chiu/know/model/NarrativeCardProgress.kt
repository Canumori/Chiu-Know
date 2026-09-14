package com.chiu.know.model

/**
 * Immutable progress through one narrative shown as sequential dialogue cards.
 *
 * Viewing a story is contextual exposure only. This state does not create
 * LearningEvidence, update FSRS, unlock content or imply comprehension.
 */
data class NarrativeCardProgress(
    val narrativeId: String,
    val beatCount: Int,
    val currentBeatIndex: Int = 0,
    val completed: Boolean = false
) {
    init {
        require(narrativeId.isNotBlank()) { "Narrative id must not be blank" }
        require(beatCount > 0) { "Narrative card progress requires at least one beat" }
        require(currentBeatIndex in 0 until beatCount) {
            "Current narrative beat must remain inside the story"
        }
        require(!completed || currentBeatIndex == beatCount - 1) {
            "Completed narrative progress must remain on the final beat"
        }
    }
}

fun narrativeCardProgressFor(narrative: NarrativeMicroUnit): NarrativeCardProgress =
    NarrativeCardProgress(
        narrativeId = narrative.id,
        beatCount = narrative.beats.size
    )

/**
 * Advances exactly one card. Continuing from the final card marks the story as
 * complete; subsequent calls are idempotent.
 */
fun advanceNarrativeCardProgress(progress: NarrativeCardProgress): NarrativeCardProgress {
    if (progress.completed) return progress

    return if (progress.currentBeatIndex == progress.beatCount - 1) {
        progress.copy(completed = true)
    } else {
        progress.copy(currentBeatIndex = progress.currentBeatIndex + 1)
    }
}
