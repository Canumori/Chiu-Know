package com.chiu.know.model

/** A skill that a learning activity primarily exercises. */
enum class LearningSkill {
    GRAMMAR,
    VOCABULARY,
    LISTENING,
    READING,
    WRITING,
    SPEAKING
}

/** The kind of learner production an activity requires. */
enum class ResponseType {
    MULTIPLE_CHOICE,
    FILL_IN,
    REORDER,
    FREE_TEXT,
    LISTEN_AND_RESPOND,
    SPEAK
}

/**
 * Pedagogical contract for every real learning activity.
 *
 * The model intentionally stores learning purpose separately from UI so that
 * future progress/review logic can use evidence rather than XP or screen visits.
 */
data class LearningActivity(
    val id: String,
    val level: CefrLevel,
    val primarySkill: LearningSkill,
    val learningObjective: String,
    val knowledgeTarget: String,
    val responseType: ResponseType,
    val prompt: String,
    val feedback: String,
    val reviewKey: String
) {
    init {
        require(id.isNotBlank()) { "Activity id must not be blank" }
        require(learningObjective.isNotBlank()) { "Learning objective must not be blank" }
        require(knowledgeTarget.isNotBlank()) { "Knowledge target must not be blank" }
        require(prompt.isNotBlank()) { "Prompt must not be blank" }
        require(feedback.isNotBlank()) { "Feedback must not be blank" }
        require(reviewKey.isNotBlank()) { "Review key must not be blank" }
    }
}
