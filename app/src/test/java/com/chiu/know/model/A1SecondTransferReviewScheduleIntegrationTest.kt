package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1SecondTransferReviewScheduleIntegrationTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun everySupportedLanguageCanCreateScheduleFromSecondTransferEvidence() {
        supportedLanguages.forEach { languageCode ->
            val activity = a1SecondTransferReviewActivitiesFor(languageCode).first()
            val evidence = learningEvidenceFor(
                activity = activity,
                correct = true,
                attemptedAtEpochMillis = 100L
            )

            val stored = updateReviewScheduleStateSet(
                encoded = emptySet(),
                evidence = evidence
            )
            val state = decodeReviewScheduleStateSet(stored).single()

            assertEquals(activity.reviewKey, state.reviewKey)
            assertEquals(1, state.reviewCount)
            assertEquals(100L, state.lastReviewAtEpochMillis)
        }
    }

    @Test
    fun reorderAndFillInVariantsUpdateOneSharedSchedule() {
        supportedLanguages.forEach { languageCode ->
            val variants = a1SecondTransferReviewActivitiesFor(languageCode)
                .groupBy { it.reviewKey }
                .values
                .first()
            val reorder = variants.first { it.responseType == ResponseType.REORDER }
            val fillIn = variants.first { it.responseType == ResponseType.FILL_IN }

            val afterReorder = updateReviewScheduleStateSet(
                encoded = emptySet(),
                evidence = learningEvidenceFor(reorder, correct = true, attemptedAtEpochMillis = 100L)
            )
            val afterFillIn = updateReviewScheduleStateSet(
                encoded = afterReorder,
                evidence = learningEvidenceFor(fillIn, correct = false, attemptedAtEpochMillis = 200L)
            )
            val states = decodeReviewScheduleStateSet(afterFillIn)

            assertEquals(1, states.size)
            assertEquals(reorder.reviewKey, fillIn.reviewKey)
            assertEquals(reorder.reviewKey, states.single().reviewKey)
            assertEquals(2, states.single().reviewCount)
            assertEquals(200L, states.single().lastReviewAtEpochMillis)
        }
    }

    @Test
    fun historicalRebuildKeepsResidenceAndPreferenceAsSeparateTargets() {
        supportedLanguages.forEach { languageCode ->
            val groups = a1SecondTransferReviewActivitiesFor(languageCode)
                .groupBy { it.reviewKey }
                .values
                .toList()
            assertEquals(2, groups.size)

            val history = listOf(
                learningEvidenceFor(groups[0][0], correct = true, attemptedAtEpochMillis = 100L),
                learningEvidenceFor(groups[1][0], correct = true, attemptedAtEpochMillis = 150L),
                learningEvidenceFor(groups[0][1], correct = false, attemptedAtEpochMillis = 200L),
                learningEvidenceFor(groups[1][1], correct = true, attemptedAtEpochMillis = 250L)
            )

            val rebuilt = rebuildReviewScheduleStates(history)
            val expectedKeys = groups.map { it.first().reviewKey }.toSet()

            assertEquals(2, rebuilt.size)
            assertEquals(expectedKeys, rebuilt.map { it.reviewKey }.toSet())
            assertTrue(rebuilt.all { it.reviewCount == 2 })
            assertEquals(200L, rebuilt.first { it.reviewKey == groups[0].first().reviewKey }.lastReviewAtEpochMillis)
            assertEquals(250L, rebuilt.first { it.reviewKey == groups[1].first().reviewKey }.lastReviewAtEpochMillis)
        }
    }
}
