package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class LearnerProfileCodecTest {

    @Test
    fun preferencesRoundTripWithoutProficiencyOrMasteryData() {
        val preferences = LearnerPreferences(
            goal = LearningGoal.WORK,
            priority = LearningPriority.SPEAKING,
            dailyMinutes = 35
        )

        val encoded = encodeLearnerPreferences(preferences)

        assertEquals("v1|WORK|SPEAKING|35", encoded)
        assertEquals(preferences, decodeLearnerPreferences(encoded))
    }

    @Test
    fun everyDeclaredGoalAndPriorityRoundTrips() {
        LearningGoal.entries.forEach { goal ->
            LearningPriority.entries.forEach { priority ->
                val preferences = LearnerPreferences(goal, priority, 20)
                assertEquals(preferences, decodeLearnerPreferences(encodeLearnerPreferences(preferences)))
            }
        }
    }

    @Test
    fun rejectsUnknownVersionAndUnknownEnums() {
        assertNull(decodeLearnerPreferences("v2|GENERAL|BALANCED|15"))
        assertNull(decodeLearnerPreferences("v1|UNKNOWN|BALANCED|15"))
        assertNull(decodeLearnerPreferences("v1|GENERAL|UNKNOWN|15"))
    }

    @Test
    fun rejectsMalformedAndOutOfRangeDailyTime() {
        assertNull(decodeLearnerPreferences("broken"))
        assertNull(decodeLearnerPreferences("v1|GENERAL|BALANCED|abc"))
        assertNull(decodeLearnerPreferences("v1|GENERAL|BALANCED|4"))
        assertNull(decodeLearnerPreferences("v1|GENERAL|BALANCED|181"))
    }

    @Test
    fun documentedDailyTimeBoundariesRoundTrip() {
        listOf(LearnerPreferences.MIN_DAILY_MINUTES, LearnerPreferences.MAX_DAILY_MINUTES).forEach { minutes ->
            val preferences = LearnerPreferences(dailyMinutes = minutes)
            assertEquals(preferences, decodeLearnerPreferences(encodeLearnerPreferences(preferences)))
        }
    }
}
