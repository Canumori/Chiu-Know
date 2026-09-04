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
            assertEquals(10, activities.size)
            assertTrue(activities.all { it.level == CefrLevel.A1 })
            assertEquals(
                setOf(LearningSkill.VOCABULARY, LearningSkill.GRAMMAR, LearningSkill.READING),
                activities.map { it.primarySkill }.toSet()
            )
            assertEquals(4, activities.map { it.reviewKey }.distinct().size)
            assertEquals(2, activities.map { it.reviewKey }.distinct().count { reviewKey ->
                activities.first { it.reviewKey == reviewKey }.primarySkill == LearningSkill.VOCABULARY
            })
            assertEquals(1, activities.count { it.responseType == ResponseType.REORDER })
            assertEquals(1, activities.count { it.responseType == ResponseType.MULTIPLE_CHOICE })
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
