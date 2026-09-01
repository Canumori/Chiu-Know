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
 * Learning purpose, expected evidence and future review identity live in the
 * model instead of being inferred from UI visits or XP.
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
    val reviewKey: String,
    val acceptedAnswers: List<String>
) {
    init {
        require(id.isNotBlank()) { "Activity id must not be blank" }
        require(learningObjective.isNotBlank()) { "Learning objective must not be blank" }
        require(knowledgeTarget.isNotBlank()) { "Knowledge target must not be blank" }
        require(prompt.isNotBlank()) { "Prompt must not be blank" }
        require(feedback.isNotBlank()) { "Feedback must not be blank" }
        require(reviewKey.isNotBlank()) { "Review key must not be blank" }
        require(acceptedAnswers.isNotEmpty()) { "Activity must define accepted answers" }
        require(acceptedAnswers.none { it.isBlank() }) { "Accepted answers must not be blank" }
    }
}

/**
 * Conservative deterministic evaluator for the first text-retrieval activities.
 * It ignores surrounding whitespace and letter case, but intentionally does not
 * erase accents or rewrite learner input. More complex response types will use
 * dedicated evaluators rather than weakening this rule globally.
 */
fun isLearningAnswerCorrect(activity: LearningActivity, learnerAnswer: String): Boolean {
    val normalized = learnerAnswer.trim().lowercase()
    return activity.acceptedAnswers.any { it.trim().lowercase() == normalized }
}
