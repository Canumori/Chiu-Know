package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LearnerPracticeMixTest {

    @Test
    fun balancedGeneralProfileKeepsAllSkillsEqual() {
        val mix = learnerPracticeMix(LearnerPreferences())

        assertEquals(2, mix.grammarWeight)
        assertEquals(2, mix.vocabularyWeight)
        assertEquals(2, mix.listeningWeight)
        assertEquals(2, mix.readingWeight)
        assertEquals(2, mix.writingWeight)
        assertEquals(2, mix.speakingWeight)
    }

    @Test
    fun declaredPriorityRaisesEmphasisWithoutRemovingOtherSkills() {
        val mix = learnerPracticeMix(
            LearnerPreferences(priority = LearningPriority.SPEAKING)
        )

        assertEquals(4, mix.speakingWeight)
        allWeights(mix).forEach { weight ->
            assertTrue(weight >= LearnerPracticeMix.MIN_SKILL_WEIGHT)
        }
    }

    @Test
    fun conversationGoalEmphasizesListeningAndSpeaking() {
        val mix = learnerPracticeMix(
            LearnerPreferences(goal = LearningGoal.CONVERSATION)
        )

        assertEquals(3, mix.listeningWeight)
        assertEquals(3, mix.speakingWeight)
        assertEquals(2, mix.readingWeight)
        assertEquals(2, mix.writingWeight)
    }

    @Test
    fun priorityAndGoalCombineOnlyAsPlanningWeights() {
        val mix = learnerPracticeMix(
            LearnerPreferences(
                goal = LearningGoal.WORK,
                priority = LearningPriority.WRITING,
                dailyMinutes = 45
            )
        )

        assertEquals(5, mix.writingWeight)
        assertEquals(3, mix.readingWeight)
        assertEquals(2, mix.grammarWeight)
        assertEquals(2, mix.vocabularyWeight)
        assertEquals(2, mix.listeningWeight)
        assertEquals(2, mix.speakingWeight)
    }

    @Test
    fun everySupportedPreferenceCombinationRetainsEveryEssentialSkill() {
        LearningGoal.entries.forEach { goal ->
            LearningPriority.entries.forEach { priority ->
                val mix = learnerPracticeMix(
                    LearnerPreferences(goal = goal, priority = priority)
                )
                allWeights(mix).forEach { weight ->
                    assertTrue(weight >= LearnerPracticeMix.MIN_SKILL_WEIGHT)
                }
            }
        }
    }

    private fun allWeights(mix: LearnerPracticeMix) = listOf(
        mix.grammarWeight,
        mix.vocabularyWeight,
        mix.listeningWeight,
        mix.readingWeight,
        mix.writingWeight,
        mix.speakingWeight
    )
}
