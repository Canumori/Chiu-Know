package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class A1FirstNarrativeComprehensionActivitiesTest {

    @Test
    fun providesOneA1ReadingComprehensionCheckPerSupportedLanguage() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val activities = a1FirstNarrativeComprehensionActivitiesFor(languageCode)

            assertEquals(1, activities.size)
            val activity = activities.single()
            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.READING, activity.primarySkill)
            assertEquals(ResponseType.MULTIPLE_CHOICE, activity.responseType)
            assertTrue(activity.acceptedAnswers.all { it in activity.responseOptions })
            assertTrue(activity.responseOptions.distinct().size == activity.responseOptions.size)
        }
    }

    @Test
    fun comprehensionCheckDependsOnAnExistingNarrativeAndUsesNarrativeSpecificEvidence() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val narrative = a1FirstNarrativeMicroUnitFor(languageCode)
            val activity = a1FirstNarrativeComprehensionActivitiesFor(languageCode).single()

            assertNotNull(narrative)
            narrative!!
            assertTrue(activity.reviewKey.contains(":narrative:") || activity.reviewKey.contains(":narrativa:") || activity.reviewKey.contains(":narration:"))
            assertTrue(activity.acceptedAnswers.any { answer ->
                narrative.beats.any { beat -> beat.text.contains(answer, ignoreCase = true) }
            })
        }
    }

    @Test
    fun narrativeComprehensionRemainsOutsideStarterReviewQueue() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val narrativeReviewKey = a1FirstNarrativeComprehensionActivitiesFor(languageCode)
                .single()
                .reviewKey
            val starterReviewKeys = starterLearningActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()

            assertTrue(narrativeReviewKey !in starterReviewKeys)
        }
    }
}
