package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1ReadingActivitiesTest {
    private val languages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun everyLanguageHasTwoA1ReadingTransferContexts() {
        languages.forEach { language ->
            val activities = a1ReadingActivitiesFor(language)
            assertEquals(2, activities.size)
            assertTrue(activities.all { it.level == CefrLevel.A1 })
            assertTrue(activities.all { it.primarySkill == LearningSkill.READING })
            assertTrue(activities.all { it.responseType == ResponseType.FILL_IN })
            assertEquals(1, activities.map { it.reviewKey }.distinct().size)
            assertEquals(1, activities.map { it.knowledgeTarget }.distinct().size)
            assertNotEquals(activities[0].id, activities[1].id)
            assertTrue(activities.all { it.reviewKey.startsWith("$language:a1:reading:") })
        }
    }

    @Test
    fun readingAnswersAreEvaluatedDeterministicallyAcrossContexts() {
        val expectedAnswers = mapOf(
            "en" to listOf("mia", "chiu"),
            "pt" to listOf("MIA", "CHIU"),
            "es" to listOf("Mia", "Chiu"),
            "fr" to listOf(" mia ", " chiu "),
            "ko" to listOf("미아", "치우")
        )

        expectedAnswers.forEach { (language, answers) ->
            val activities = a1ReadingActivitiesFor(language)
            activities.zip(answers).forEach { (activity, answer) ->
                assertTrue(isLearningAnswerCorrect(activity, answer))
                assertFalse(isLearningAnswerCorrect(activity, "wrong-answer"))
            }
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
