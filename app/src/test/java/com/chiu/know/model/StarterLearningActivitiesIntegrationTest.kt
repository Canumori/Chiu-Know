package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class StarterLearningActivitiesIntegrationTest {

    @Test
    fun providesIntegratedA1TargetsAndTransferFormatsForEveryLanguage() {
        supportedTargetLanguages.forEach { language ->
            val activities = starterLearningActivitiesFor(language.code)
            val reviewTargets = activities.distinctBy { it.reviewKey }
            val vocabularyTargets = reviewTargets.count { it.primarySkill == LearningSkill.VOCABULARY }

            assertTrue(activities.size >= 10)
            assertTrue(activities.all { it.level == CefrLevel.A1 })
            assertEquals(
                setOf(LearningSkill.VOCABULARY, LearningSkill.GRAMMAR, LearningSkill.READING),
                activities.map { it.primarySkill }.toSet()
            )
            assertTrue(reviewTargets.size >= 4)
            assertTrue(vocabularyTargets >= 2)
            assertTrue(activities.any { it.responseType == ResponseType.REORDER })
            assertTrue(activities.any { it.responseType == ResponseType.MULTIPLE_CHOICE })
        }
    }

    @Test
    fun rawRotationTraversesEntireCurrentBankBeforeWrapping() {
        supportedTargetLanguages.forEach { language ->
            val activities = starterLearningActivitiesFor(language.code)
            val rotated = activities.indices.map { index ->
                starterLearningActivityFor(language.code, CefrLevel.A1, index)
            }

            rotated.forEach { assertNotNull(it) }
            assertEquals(activities.map { it.id }, rotated.map { it?.id })
            assertEquals(
                activities.first().id,
                starterLearningActivityFor(language.code, CefrLevel.A1, activities.size)?.id
            )
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
