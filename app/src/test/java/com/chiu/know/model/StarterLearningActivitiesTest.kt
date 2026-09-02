package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class StarterLearningActivitiesTest {

    @Test
    fun providesA1RetrievalActivityForEverySupportedTargetLanguage() {
        supportedTargetLanguages.forEach { language ->
            val activity = starterLearningActivityFor(language.code, CefrLevel.A1)
            assertNotNull("Missing A1 starter activity for ${language.code}", activity)
            assertEquals(CefrLevel.A1, activity?.level)
            assertEquals(LearningSkill.VOCABULARY, activity?.primarySkill)
            assertEquals(ResponseType.FILL_IN, activity?.responseType)
            assertTrue(activity?.reviewKey?.startsWith("${language.code}:a1:") == true)
        }
    }

    @Test
    fun rotatesSameKnowledgeAcrossDifferentContexts() {
        supportedTargetLanguages.forEach { language ->
            val first = starterLearningActivityFor(language.code, CefrLevel.A1, priorAttemptCount = 0)
            val second = starterLearningActivityFor(language.code, CefrLevel.A1, priorAttemptCount = 1)
            val third = starterLearningActivityFor(language.code, CefrLevel.A1, priorAttemptCount = 2)

            assertNotNull(first)
            assertNotNull(second)
            assertNotEquals(first?.id, second?.id)
            assertEquals(first?.reviewKey, second?.reviewKey)
            assertEquals(first?.knowledgeTarget, second?.knowledgeTarget)
            assertEquals(first?.id, third?.id)
        }
    }

    @Test
    fun doesNotPretendHigherLevelsHaveContentYet() {
        supportedTargetLanguages.forEach { language ->
            assertNull(starterLearningActivityFor(language.code, CefrLevel.B1))
            assertNull(starterLearningActivityFor(language.code, CefrLevel.C2))
        }
    }

    @Test
    fun starterActivityIdsAreUniqueAndReviewKeysIntentionallyRepeatForTransfer() {
        val activities = supportedTargetLanguages.flatMap { starterLearningActivitiesFor(it.code) }
        assertEquals(activities.size, activities.map { it.id }.distinct().size)
        supportedTargetLanguages.forEach { language ->
            val languageActivities = starterLearningActivitiesFor(language.code)
            assertEquals(2, languageActivities.size)
            assertEquals(1, languageActivities.map { it.reviewKey }.distinct().size)
        }
    }
}
