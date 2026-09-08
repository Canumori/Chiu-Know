package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class A1SecondTransferNewWorkSelectionTest {

    private val languages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun missingStarterExposureBlocksSecondTransferInEveryLanguage() {
        languages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))

            val selection = a1SecondTransferNewWorkSelection(languageCode, emptyList())

            assertNull(selection.activity)
            assertEquals(
                A1SecondTransferNewWorkReason.PREREQUISITES_NOT_EXPOSED,
                selection.reason
            )
            assertEquals(unit.narrative.linkedReviewKeys, selection.missingPrerequisiteReviewKeys)
        }
    }

    @Test
    fun incorrectA1AttemptsStillCountAsExposureWithoutClaimingMastery() {
        languages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val prerequisiteEvidence = prerequisiteEvidence(unit, correct = false)

            val selection = a1SecondTransferNewWorkSelection(languageCode, prerequisiteEvidence)

            assertEquals(A1SecondTransferNewWorkReason.COMPREHENSION, selection.reason)
            assertEquals(unit.comprehension.id, selection.activity?.id)
            assertTrue(selection.missingPrerequisiteReviewKeys.isEmpty())
        }
    }

    @Test
    fun nonA1EvidenceDoesNotUnlockA1Transfer() {
        languages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val wrongLevelEvidence = unit.narrative.linkedReviewKeys.mapIndexed { index, reviewKey ->
                LearningEvidence(
                    activityId = "synthetic-$languageCode-prerequisite-$index",
                    reviewKey = reviewKey,
                    level = CefrLevel.A2,
                    primarySkill = LearningSkill.READING,
                    correct = true,
                    attemptedAtEpochMillis = index.toLong() + 1L
                )
            }

            val selection = a1SecondTransferNewWorkSelection(languageCode, wrongLevelEvidence)

            assertEquals(
                A1SecondTransferNewWorkReason.PREREQUISITES_NOT_EXPOSED,
                selection.reason
            )
            assertEquals(unit.narrative.linkedReviewKeys, selection.missingPrerequisiteReviewKeys)
        }
    }

    @Test
    fun progressionFollowsComprehensionThenResidenceThenPreferenceCueWithdrawal() {
        languages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val expectedSequence = listOf(unit.comprehension) +
                unit.retrievalTracks.flatMap { it.activities }
            val evidence = prerequisiteEvidence(unit, correct = false).toMutableList()

            expectedSequence.forEachIndexed { index, expectedActivity ->
                val selection = a1SecondTransferNewWorkSelection(languageCode, evidence)

                val expectedReason = if (index == 0) {
                    A1SecondTransferNewWorkReason.COMPREHENSION
                } else {
                    A1SecondTransferNewWorkReason.RETRIEVAL_STEP
                }
                assertEquals(expectedReason, selection.reason)
                assertEquals(expectedActivity.id, selection.activity?.id)

                evidence += evidenceFor(expectedActivity, correct = index % 2 == 0, time = 100L + index)
            }

            val complete = a1SecondTransferNewWorkSelection(languageCode, evidence)
            assertNull(complete.activity)
            assertEquals(
                A1SecondTransferNewWorkReason.INITIAL_SEQUENCE_COMPLETE,
                complete.reason
            )
        }
    }

    @Test
    fun outOfOrderEvidenceCannotSkipAnEarlierRetrievalStep() {
        languages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val residence = unit.retrievalTracks.single { it.target == A1SecondTransferTarget.RESIDENCE }
            val evidence = prerequisiteEvidence(unit, correct = true).toMutableList()
            evidence += evidenceFor(unit.comprehension, correct = true, time = 100L)
            evidence += evidenceFor(residence.activities[1], correct = true, time = 101L)
            evidence += evidenceFor(residence.activities[2], correct = true, time = 102L)

            val selection = a1SecondTransferNewWorkSelection(languageCode, evidence)

            assertEquals(A1SecondTransferNewWorkReason.RETRIEVAL_STEP, selection.reason)
            assertEquals(residence.activities[0].id, selection.activity?.id)
            assertEquals(ResponseType.MULTIPLE_CHOICE, selection.activity?.responseType)
        }
    }

    @Test
    fun unsupportedLanguageReturnsNoContent() {
        val selection = a1SecondTransferNewWorkSelection("xx", emptyList())

        assertNull(selection.activity)
        assertEquals(A1SecondTransferNewWorkReason.NO_CONTENT, selection.reason)
        assertTrue(selection.missingPrerequisiteReviewKeys.isEmpty())
    }

    private fun prerequisiteEvidence(
        unit: A1SecondTransferLearningUnit,
        correct: Boolean
    ): List<LearningEvidence> = unit.narrative.linkedReviewKeys.mapIndexed { index, reviewKey ->
        LearningEvidence(
            activityId = "synthetic-${unit.narrative.languageCode}-prerequisite-$index",
            reviewKey = reviewKey,
            level = CefrLevel.A1,
            primarySkill = LearningSkill.READING,
            correct = correct,
            attemptedAtEpochMillis = index.toLong() + 1L
        )
    }

    private fun evidenceFor(
        activity: LearningActivity,
        correct: Boolean,
        time: Long
    ) = LearningEvidence(
        activityId = activity.id,
        reviewKey = activity.reviewKey,
        level = activity.level,
        primarySkill = activity.primarySkill,
        correct = correct,
        attemptedAtEpochMillis = time
    )
}
