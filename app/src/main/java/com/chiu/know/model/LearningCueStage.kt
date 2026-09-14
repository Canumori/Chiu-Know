package com.chiu.know.model

/**
 * Observable cue support for activity formats that the app can evaluate
 * deterministically today.
 *
 * The order describes progressively less answer support. It is a curriculum
 * planning fact, not evidence of mastery, proficiency or successful transfer.
 */
enum class LearningCueStage {
    RECOGNITION,
    STRUCTURED_RECONSTRUCTION,
    REDUCED_CUE_RETRIEVAL
}

/**
 * Returns a cue stage only for the closed evaluators currently used by the
 * learning flow.
 *
 * FREE_TEXT, listening and speaking stay unclassified until their dedicated
 * evaluation mechanisms can justify a cue-withdrawal claim.
 */
fun learningCueStage(activity: LearningActivity): LearningCueStage? =
    when (activity.responseType) {
        ResponseType.MULTIPLE_CHOICE -> LearningCueStage.RECOGNITION
        ResponseType.REORDER -> LearningCueStage.STRUCTURED_RECONSTRUCTION
        ResponseType.FILL_IN -> LearningCueStage.REDUCED_CUE_RETRIEVAL
        ResponseType.FREE_TEXT,
        ResponseType.LISTEN_AND_RESPOND,
        ResponseType.SPEAK -> null
    }
