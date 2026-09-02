package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class A1ReadingActivitiesTest {
    private val languages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun everyLanguageHasOneExplicitA1ReadingActivity() {
        languages.forEach { language ->
            val activities = a1ReadingActivitiesFor(language)
            assertEquals(1, activities.size)
            val activity = activities.single()
            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.READING, activity.primarySkill)
            assertEquals(ResponseType.FILL_IN, activity.responseType)
            assertTrue(activity.reviewKey.startsWith("$language:a1:reading:"))
        }
    }

    @Test
    fun readingAnswersAreEvaluatedDeterministically() {
        val expected = mapOf(
            "en" to "mia",
            "pt" to "MIA",
            "es" to "Mia",
            "fr" to " mia ",
            "ko" to "미아"
        )

        expected.forEach { (language, answer) ->
            val activity = a1ReadingActivitiesFor(language).single()
            assertTrue(isLearningAnswerCorrect(activity, answer))
            assertFalse(isLearningAnswerCorrect(activity, "wrong-answer"))
        }
    }

    @Test
    fun integratedStarterBankNowIncludesReadingWithoutFakeHigherLevels() {
        languages.forEach { language ->
            val activities = starterLearningActivitiesFor(language)
            assertTrue(activities.any { it.primarySkill == LearningSkill.VOCABULARY })
            assertTrue(activities.any { it.primarySkill == LearningSkill.GRAMMAR })
            assertTrue(activities.any { it.primarySkill == LearningSkill.READING })
            assertTrue(activities.all { it.level == CefrLevel.A1 })
            assertEquals(activities.size, activities.map { it.id }.toSet().size)
        }
    }
}
