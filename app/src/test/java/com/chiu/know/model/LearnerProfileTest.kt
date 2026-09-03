package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class LearnerProfileTest {

    @Test
    fun defaultsAreNeutralAndConservative() {
        val preferences = LearnerPreferences()

        assertEquals(LearningGoal.GENERAL, preferences.goal)
        assertEquals(LearningPriority.BALANCED, preferences.priority)
        assertEquals(LearnerPreferences.DEFAULT_DAILY_MINUTES, preferences.dailyMinutes)
    }

    @Test
    fun allDeclaredGoalsAndPrioritiesCanBeRepresentedWithoutChangingProficiency() {
        LearningGoal.entries.forEach { goal ->
            LearningPriority.entries.forEach { priority ->
                val preferences = LearnerPreferences(goal = goal, priority = priority, dailyMinutes = 30)
                assertEquals(goal, preferences.goal)
                assertEquals(priority, preferences.priority)
                assertEquals(30, preferences.dailyMinutes)
            }
        }
    }

    @Test
    fun dailyMinutesAcceptDocumentedBoundaries() {
        assertEquals(
            LearnerPreferences.MIN_DAILY_MINUTES,
            LearnerPreferences(dailyMinutes = LearnerPreferences.MIN_DAILY_MINUTES).dailyMinutes
        )
        assertEquals(
            LearnerPreferences.MAX_DAILY_MINUTES,
            LearnerPreferences(dailyMinutes = LearnerPreferences.MAX_DAILY_MINUTES).dailyMinutes
        )
    }

    @Test
    fun dailyMinutesRejectValuesOutsideDocumentedBoundaries() {
        assertThrows(IllegalArgumentException::class.java) {
            LearnerPreferences(dailyMinutes = LearnerPreferences.MIN_DAILY_MINUTES - 1)
        }
        assertThrows(IllegalArgumentException::class.java) {
            LearnerPreferences(dailyMinutes = LearnerPreferences.MAX_DAILY_MINUTES + 1)
        }
    }
}
