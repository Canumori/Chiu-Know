package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1NarrativeSequenceActivitiesTest {

    @Test
    fun providesOneA1SequenceCheckPerSupportedLanguage() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val activities = a1NarrativeSequenceActivitiesFor(languageCode)

            assertEquals(1, activities.size)
            val activity = activities.single()
            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.READING, activity.primarySkill)
            assertEquals(ResponseType.MULTIPLE_CHOICE, activity.responseType)
            assertEquals(2, activity.responseOptions.size)
            assertEquals(2, activity.responseOptions.distinct().size)
            assertTrue(activity.acceptedAnswers.all { it in activity.responseOptions })
        }
    }

    @Test
    fun sequenceAnswersAreGroundedInTheFirstNarrativeTurnOrder() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val narrative = a1FirstNarrativeMicroUnitFor(languageCode)!!
            val activity = a1NarrativeSequenceActivitiesFor(languageCode).single()
            val accepted = activity.acceptedAnswers.single()

            assertTrue(narrative.beats.any { it.text == accepted })
        }
    }

    @Test
    fun sequenceChecksRemainOutsideStarterReviewQueue() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val sequenceKeys = a1NarrativeSequenceActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()
            val starterKeys = starterLearningActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()

            assertTrue(sequenceKeys.intersect(starterKeys).isEmpty())
        }
    }

    @Test
    fun koreanSequenceCheckMatchesReviewedDialogue() {
        val activity = a1NarrativeSequenceActivitiesFor("ko").single()

        assertEquals(listOf("무엇을 좋아해요?"), activity.acceptedAnswers)
        assertEquals(listOf("무엇을 좋아해요?", "이름이 뭐예요?"), activity.responseOptions)
    }
}
