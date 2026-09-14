package com.chiu.know.model

/**
 * Neutral preference contract for future personalization.
 *
 * These preferences may influence examples, practice mix and planning, but they
 * must never erase essential CEFR competencies or be treated as evidence of
 * proficiency/mastery. The contract is intentionally not wired to UI yet.
 */
enum class LearningGoal {
    GENERAL,
    CONVERSATION,
    TRAVEL,
    WORK,
    STUDY_OR_EXAM,
    LIVING_ABROAD,
    CULTURE_AND_MEDIA
}

enum class LearningPriority {
    BALANCED,
    LISTENING,
    SPEAKING,
    READING,
    WRITING
}

data class LearnerPreferences(
    val goal: LearningGoal = LearningGoal.GENERAL,
    val priority: LearningPriority = LearningPriority.BALANCED,
    val dailyMinutes: Int = DEFAULT_DAILY_MINUTES
) {
    init {
        require(dailyMinutes in MIN_DAILY_MINUTES..MAX_DAILY_MINUTES) {
            "dailyMinutes must be between $MIN_DAILY_MINUTES and $MAX_DAILY_MINUTES"
        }
    }

    companion object {
        const val MIN_DAILY_MINUTES = 5
        const val MAX_DAILY_MINUTES = 180
        const val DEFAULT_DAILY_MINUTES = 15
    }
}
