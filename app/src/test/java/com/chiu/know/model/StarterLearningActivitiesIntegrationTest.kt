package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class StarterLearningActivitiesIntegrationTest {

    @Test
    fun providesIntegratedA1VocabularyGrammarAndReadingForEveryLanguage() {
        supportedTargetLanguages.forEach { language ->
            val activities = starterLearningActivitiesFor(language.code)
            assertEquals(5, activities.size)
            assertTrue(activities.all { it.level == CefrLevel.A1 })
            assertEquals(
                setOf(LearningSkill.VOCABULARY, LearningSkill.GRAMMAR, LearningSkill.READING),
                activities.map { it.primarySkill }.toSet()
            )
            assertEquals(3, activities.map { it.reviewKey }.distinct().size)
        }
    }

    @Test
    fun rotatesThroughVocabularyPairGrammarPairThenReading() {
        supportedTargetLanguages.forEach { language ->
            val first = starterLearningActivityFor(language.code, CefrLevel.A1, 0)
            val second = starterLearningActivityFor(language.code, CefrLevel.A1, 1)
            val third = starterLearningActivityFor(language.code, CefrLevel.A1, 2)
            val fourth = starterLearningActivityFor(language.code, CefrLevel.A1, 3)
            val fifth = starterLearningActivityFor(language.code, CefrLevel.A1, 4)
            val sixth = starterLearningActivityFor(language.code, CefrLevel.A1, 5)

            assertNotNull(first)
            assertNotNull(second)
            assertNotNull(third)
            assertNotNull(fourth)
            assertNotNull(fifth)
            assertNotEquals(first?.id, second?.id)
            assertEquals(first?.reviewKey, second?.reviewKey)
            assertEquals(LearningSkill.VOCABULARY, first?.primarySkill)
            assertNotEquals(third?.id, fourth?.id)
            assertEquals(third?.reviewKey, fourth?.reviewKey)
            assertEquals(LearningSkill.GRAMMAR, third?.primarySkill)
            assertNotEquals(first?.reviewKey, third?.reviewKey)
            assertEquals(LearningSkill.READING, fifth?.primarySkill)
            assertNotEquals(fourth?.reviewKey, fifth?.reviewKey)
            assertEquals(first?.id, sixth?.id)
        }
    }

    @Test
    fun stillDoesNotExposeUnimplementedHigherLevels() {
        supportedTargetLanguages.forEach { language ->
            assertNull(starterLearningActivityFor(language.code, CefrLevel.B1))
            assertNull(starterLearningActivityFor(language.code, CefrLevel.C2))
        }
    }
}
