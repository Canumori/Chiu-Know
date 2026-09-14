package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LearningActivityQueuePersistenceIntegrationTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")
    private val preferences = LearnerPreferences(
        goal = LearningGoal.CONVERSATION,
        priority = LearningPriority.SPEAKING,
        dailyMinutes = 15
    )

    @Test
    fun persistedIncorrectSecondTransferAttemptReturnsAsDueReviewBeforeNewWork() {
        supportedLanguages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            var encodedEvidence = prerequisiteEvidence(unit)
                .map(::encodeLearningEvidence)
                .toSet()
            var encodedSchedules = emptySet<String>()

            fun queue(at: Long): StarterQueueSelection = learningActivityQueueSelection(
                languageCode = languageCode,
                level = CefrLevel.A1,
                evidence = decodeLearningEvidenceSet(encodedEvidence),
                schedules = decodeReviewScheduleStateSet(encodedSchedules),
                nowEpochMillis = at,
                preferences = preferences
            )

            fun persist(activity: LearningActivity, correct: Boolean, at: Long) {
                val evidence = learningEvidenceFor(
                    activity = activity,
                    correct = correct,
                    attemptedAtEpochMillis = at
                )
                encodedEvidence = encodedEvidence + encodeLearningEvidence(evidence)
                encodedSchedules = updateReviewScheduleStateSet(
                    encoded = encodedSchedules,
                    evidence = evidence
                )
            }

            val comprehensionSelection = queue(100L)
            assertEquals(StarterQueueReason.NEW_TARGET, comprehensionSelection.reason)
            assertEquals(unit.comprehension.id, comprehensionSelection.activity?.id)

            persist(
                activity = requireNotNull(comprehensionSelection.activity),
                correct = false,
                at = 100L
            )

            val firstRetrievalSelection = queue(101L)
            val firstResidenceRetrieval = unit.retrievalTracks
                .first { it.target == A1SecondTransferTarget.RESIDENCE }
                .activities
                .first()

            assertEquals(StarterQueueReason.NEW_TARGET, firstRetrievalSelection.reason)
            assertEquals(firstResidenceRetrieval.id, firstRetrievalSelection.activity?.id)
            assertEquals(ResponseType.MULTIPLE_CHOICE, firstResidenceRetrieval.responseType)

            persist(
                activity = requireNotNull(firstRetrievalSelection.activity),
                correct = false,
                at = 101L
            )

            val residenceSchedule = decodeReviewScheduleStateSet(encodedSchedules)
                .single { it.reviewKey == firstResidenceRetrieval.reviewKey }
            val dueSelection = queue(residenceSchedule.dueAtEpochMillis)

            assertEquals(StarterQueueReason.DUE_REVIEW, dueSelection.reason)
            assertEquals(firstResidenceRetrieval.reviewKey, dueSelection.activity?.reviewKey)
            assertNotEquals(firstResidenceRetrieval.id, dueSelection.activity?.id)
            assertTrue(
                dueSelection.activity?.responseType == ResponseType.REORDER ||
                    dueSelection.activity?.responseType == ResponseType.FILL_IN
            )
        }
    }

    private fun prerequisiteEvidence(unit: A1SecondTransferLearningUnit): List<LearningEvidence> =
        unit.narrative.linkedReviewKeys.mapIndexed { index, reviewKey ->
            LearningEvidence(
                activityId = "synthetic-${unit.narrative.languageCode}-queue-persistence-prerequisite-$index",
                reviewKey = reviewKey,
                level = CefrLevel.A1,
                primarySkill = LearningSkill.READING,
                correct = false,
                attemptedAtEpochMillis = index.toLong() + 1L
            )
        }
}
