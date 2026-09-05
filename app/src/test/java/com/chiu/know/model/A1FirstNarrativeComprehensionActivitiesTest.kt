package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class A1FirstNarrativeComprehensionActivitiesTest {

    @Test
    fun providesTwoA1ReadingComprehensionChecksPerSupportedLanguage() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val activities = a1FirstNarrativeComprehensionActivitiesFor(languageCode)

            assertEquals(2, activities.size)
            activities.forEach { activity ->
                assertEquals(CefrLevel.A1, activity.level)
                assertEquals(LearningSkill.READING, activity.primarySkill)
                assertEquals(ResponseType.MULTIPLE_CHOICE, activity.responseType)
                assertTrue(activity.acceptedAnswers.all { it in activity.responseOptions })
                assertTrue(activity.responseOptions.distinct().size == activity.responseOptions.size)
            }
            assertEquals(activities.size, activities.map { it.id }.toSet().size)
            assertEquals(activities.size, activities.map { it.reviewKey }.toSet().size)
        }
    }

    @Test
    fun comprehensionChecksDependOnAnExistingNarrativeAndUseNarrativeSpecificEvidence() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val narrative = a1FirstNarrativeMicroUnitFor(languageCode)
            val activities = a1FirstNarrativeComprehensionActivitiesFor(languageCode)

            assertNotNull(narrative)
            narrative!!
            activities.forEach { activity ->
                assertTrue(activity.reviewKey.contains(":narrative:") || activity.reviewKey.contains(":narrativa:") || activity.reviewKey.contains(":narration:"))
                assertTrue(activity.acceptedAnswers.any { answer ->
                    narrative.beats.any { beat -> beat.text.contains(answer, ignoreCase = true) }
                })
            }
        }
    }

    @Test
    fun narrativeComprehensionRemainsOutsideStarterReviewQueue() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val narrativeReviewKeys = a1FirstNarrativeComprehensionActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()
            val starterReviewKeys = starterLearningActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()

            assertTrue(narrativeReviewKeys.intersect(starterReviewKeys).isEmpty())
        }
    }
}
