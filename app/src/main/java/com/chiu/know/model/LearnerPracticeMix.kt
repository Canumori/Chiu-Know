package com.chiu.know.model

/**
 * Planning-only practice emphasis derived from learner-declared preferences.
 *
 * This model may influence the relative mix of future practice activities, but it
 * must not change CEFR level, mastery, unlocks or remove essential competencies.
 */
data class LearnerPracticeMix(
    val grammarWeight: Int,
    val vocabularyWeight: Int,
    val listeningWeight: Int,
    val readingWeight: Int,
    val writingWeight: Int,
    val speakingWeight: Int
) {
    init {
        val weights = listOf(
            grammarWeight,
            vocabularyWeight,
            listeningWeight,
            readingWeight,
            writingWeight,
            speakingWeight
        )
        require(weights.all { it >= MIN_SKILL_WEIGHT }) {
            "Every essential skill must retain a positive planning weight"
        }
    }

    companion object {
        const val MIN_SKILL_WEIGHT = 1
    }
}

fun learnerPracticeMix(preferences: LearnerPreferences): LearnerPracticeMix {
    val base = mutableMapOf(
        LearningSkill.GRAMMAR to 2,
        LearningSkill.VOCABULARY to 2,
        LearningSkill.LISTENING to 2,
        LearningSkill.READING to 2,
        LearningSkill.WRITING to 2,
        LearningSkill.SPEAKING to 2
    )

    when (preferences.priority) {
        LearningPriority.BALANCED -> Unit
        LearningPriority.LISTENING -> base[LearningSkill.LISTENING] = 4
        LearningPriority.SPEAKING -> base[LearningSkill.SPEAKING] = 4
        LearningPriority.READING -> base[LearningSkill.READING] = 4
        LearningPriority.WRITING -> base[LearningSkill.WRITING] = 4
    }

    when (preferences.goal) {
        LearningGoal.CONVERSATION -> {
            base[LearningSkill.LISTENING] = base.getValue(LearningSkill.LISTENING) + 1
            base[LearningSkill.SPEAKING] = base.getValue(LearningSkill.SPEAKING) + 1
        }
        LearningGoal.TRAVEL,
        LearningGoal.LIVING_ABROAD -> {
            base[LearningSkill.LISTENING] = base.getValue(LearningSkill.LISTENING) + 1
            base[LearningSkill.SPEAKING] = base.getValue(LearningSkill.SPEAKING) + 1
            base[LearningSkill.READING] = base.getValue(LearningSkill.READING) + 1
        }
        LearningGoal.WORK,
        LearningGoal.STUDY_OR_EXAM -> {
            base[LearningSkill.READING] = base.getValue(LearningSkill.READING) + 1
            base[LearningSkill.WRITING] = base.getValue(LearningSkill.WRITING) + 1
        }
        LearningGoal.CULTURE_AND_MEDIA -> {
            base[LearningSkill.LISTENING] = base.getValue(LearningSkill.LISTENING) + 1
            base[LearningSkill.READING] = base.getValue(LearningSkill.READING) + 1
        }
        LearningGoal.GENERAL -> Unit
    }

    return LearnerPracticeMix(
        grammarWeight = base.getValue(LearningSkill.GRAMMAR),
        vocabularyWeight = base.getValue(LearningSkill.VOCABULARY),
        listeningWeight = base.getValue(LearningSkill.LISTENING),
        readingWeight = base.getValue(LearningSkill.READING),
        writingWeight = base.getValue(LearningSkill.WRITING),
        speakingWeight = base.getValue(LearningSkill.SPEAKING)
    )
}
