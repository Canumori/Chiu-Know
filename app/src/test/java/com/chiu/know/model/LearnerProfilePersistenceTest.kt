package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class LearnerProfilePersistenceTest {

    @Test
    fun persistsAndRestoresValidPreferences() {
        val preferences = LearnerPreferences(
            goal = LearningGoal.TRAVEL,
            priority = LearningPriority.LISTENING,
            dailyMinutes = 25
        )

        val encoded = persistedLearnerPreferences(preferences)

        assertEquals("v1|TRAVEL|LISTENING|25", encoded)
        assertEquals(preferences, restoredLearnerPreferences(encoded))
    }

    @Test
    fun absentPreferencesRemainAbsent() {
        assertNull(persistedLearnerPreferences(null))
        assertNull(restoredLearnerPreferences(null))
    }

    @Test
    fun corruptedPersistedPreferencesFailClosed() {
        assertNull(restoredLearnerPreferences("v1|TRAVEL|LISTENING|4"))
        assertNull(restoredLearnerPreferences("v9|TRAVEL|LISTENING|25"))
        assertNull(restoredLearnerPreferences("not-a-profile"))
    }
}
