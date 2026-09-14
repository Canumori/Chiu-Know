package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1TransferNarrativeComprehensionActivitiesTest {

    @Test
    fun providesOneControlledTransferCheckPerSupportedLanguage() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val activities = a1TransferNarrativeComprehensionActivitiesFor(languageCode)

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
    fun answersAreGroundedInTheSecondNarrativeRatherThanTheFirst() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val transfer = a1TransferNarrativeMicroUnitFor(languageCode)!!
            val first = a1FirstNarrativeMicroUnitFor(languageCode)!!
            val activity = a1TransferNarrativeComprehensionActivitiesFor(languageCode).single()
            val accepted = activity.acceptedAnswers.single()

            val transferSpeakers = transfer.beats.map { it.speaker }
            assertTrue(
                accepted in transferSpeakers ||
                    transfer.beats.any { accepted in it.text }
            )
            assertTrue(transfer.id != first.id)
            assertTrue(activity.reviewKey.contains("park") || activity.reviewKey.contains("parque") || activity.reviewKey.contains("parc"))
        }
    }

    @Test
    fun transferChecksRemainOutsideStarterReviewQueue() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val starterKeys = starterLearningActivitiesFor(languageCode).map { it.reviewKey }.toSet()
            val transferKeys = a1TransferNarrativeComprehensionActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()

            assertTrue(starterKeys.intersect(transferKeys).isEmpty())
        }
    }

    @Test
    fun koreanCheckIsDeterministicAndGroundedInReviewedText() {
        val narrative = a1TransferNarrativeMicroUnitFor("ko")!!
        val activity = a1TransferNarrativeComprehensionActivitiesFor("ko").single()
        val miaBeat = narrative.beats.first { it.text == "책을 좋아해요." }

        assertEquals("Mia", miaBeat.speaker)
        assertEquals(listOf("미아"), activity.acceptedAnswers)
        assertEquals(listOf("미아", "치우"), activity.responseOptions)
    }
}
