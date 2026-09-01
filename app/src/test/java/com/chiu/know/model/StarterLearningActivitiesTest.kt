package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class StarterLearningActivitiesTest {

    @Test
    fun providesOneA1RetrievalActivityForEverySupportedTargetLanguage() {
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
    fun doesNotPretendHigherLevelsHaveContentYet() {
        supportedTargetLanguages.forEach { language ->
            assertNull(starterLearningActivityFor(language.code, CefrLevel.B1))
            assertNull(starterLearningActivityFor(language.code, CefrLevel.C2))
        }
    }

    @Test
    fun starterActivityIdsAndReviewKeysAreUnique() {
        val activities = supportedTargetLanguages.flatMap { starterLearningActivitiesFor(it.code) }
        assertEquals(activities.size, activities.map { it.id }.distinct().size)
        assertEquals(activities.size, activities.map { it.reviewKey }.distinct().size)
    }
}
