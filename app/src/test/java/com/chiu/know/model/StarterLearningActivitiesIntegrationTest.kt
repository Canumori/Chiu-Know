package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class StarterLearningActivitiesIntegrationTest {

    @Test
    fun providesIntegratedA1VocabularyGrammarReadingAndReorderForEveryLanguage() {
        supportedTargetLanguages.forEach { language ->
            val activities = starterLearningActivitiesFor(language.code)
            assertEquals(7, activities.size)
            assertTrue(activities.all { it.level == CefrLevel.A1 })
            assertEquals(
                setOf(LearningSkill.VOCABULARY, LearningSkill.GRAMMAR, LearningSkill.READING),
                activities.map { it.primarySkill }.toSet()
            )
            assertEquals(3, activities.map { it.reviewKey }.distinct().size)
            assertEquals(1, activities.count { it.responseType == ResponseType.REORDER })
        }
    }

    @Test
    fun rotatesThroughVocabularyGrammarReadingAndReorderTransfer() {
        supportedTargetLanguages.forEach { language ->
            val first = starterLearningActivityFor(language.code, CefrLevel.A1, 0)
            val second = starterLearningActivityFor(language.code, CefrLevel.A1, 1)
            val third = starterLearningActivityFor(language.code, CefrLevel.A1, 2)
            val fourth = starterLearningActivityFor(language.code, CefrLevel.A1, 3)
            val fifth = starterLearningActivityFor(language.code, CefrLevel.A1, 4)
            val sixth = starterLearningActivityFor(language.code, CefrLevel.A1, 5)
            val seventh = starterLearningActivityFor(language.code, CefrLevel.A1, 6)
            val eighth = starterLearningActivityFor(language.code, CefrLevel.A1, 7)

            listOf(first, second, third, fourth, fifth, sixth, seventh, eighth).forEach { assertNotNull(it) }

            assertNotEquals(first?.id, second?.id)
            assertEquals(first?.reviewKey, second?.reviewKey)
            assertEquals(LearningSkill.VOCABULARY, first?.primarySkill)

            assertNotEquals(third?.id, fourth?.id)
            assertEquals(third?.reviewKey, fourth?.reviewKey)
            assertEquals(LearningSkill.GRAMMAR, third?.primarySkill)
            assertNotEquals(first?.reviewKey, third?.reviewKey)

            assertNotEquals(fifth?.id, sixth?.id)
            assertEquals(fifth?.reviewKey, sixth?.reviewKey)
            assertEquals(LearningSkill.READING, fifth?.primarySkill)
            assertNotEquals(fourth?.reviewKey, fifth?.reviewKey)

            assertEquals(LearningSkill.GRAMMAR, seventh?.primarySkill)
            assertEquals(ResponseType.REORDER, seventh?.responseType)
            assertEquals(third?.reviewKey, seventh?.reviewKey)

            assertEquals(first?.id, eighth?.id)
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
