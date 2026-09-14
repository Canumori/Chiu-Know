package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class LearningActivityQueueTest {

    private val languages = listOf("en", "pt", "es", "fr", "ko")
    private val now = 1_000L
    private val preferences = LearnerPreferences(
        goal = LearningGoal.CONVERSATION,
        priority = LearningPriority.SPEAKING,
        dailyMinutes = 15
    )

    @Test
    fun a1ReadySecondTransferNewWorkMapsToNewTarget() {
        languages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))

            val selection = learningActivityQueueSelection(
                languageCode = languageCode,
                level = CefrLevel.A1,
                evidence = prerequisiteEvidence(unit),
                schedules = emptyList(),
                nowEpochMillis = now,
                preferences = preferences
            )

            assertEquals(StarterQueueReason.NEW_TARGET, selection.reason)
            assertEquals(unit.comprehension.id, selection.activity?.id)
            assertNull(selection.nextDueAtEpochMillis)
        }
    }

    @Test
    fun a1NarrativeComprehensionContinuesIntoRelatedRetrieval() {
        languages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val evidence = prerequisiteEvidence(unit) + learningEvidenceFor(
                activity = unit.comprehension,
                correct = true,
                attemptedAtEpochMillis = 100L
            )

            val selection = learningActivityQueueSelection(
                languageCode = languageCode,
                level = CefrLevel.A1,
                evidence = evidence,
                schedules = emptyList(),
                nowEpochMillis = now,
                preferences = preferences
            )

            assertEquals(StarterQueueReason.NEW_TARGET, selection.reason)
            assertEquals(unit.retrievalTracks.first().activities.first().id, selection.activity?.id)
            assertEquals(ResponseType.MULTIPLE_CHOICE, selection.activity?.responseType)
        }
    }

    @Test
    fun a1SecondTransferDueReviewMapsToDueReview() {
        languages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val transferKey = a1SecondTransferReviewActivitiesFor(languageCode).first().reviewKey

            val selection = learningActivityQueueSelection(
                languageCode = languageCode,
                level = CefrLevel.A1,
                evidence = prerequisiteEvidence(unit),
                schedules = listOf(schedule(transferKey, 900L)),
                nowEpochMillis = now,
                preferences = preferences
            )

            assertEquals(StarterQueueReason.DUE_REVIEW, selection.reason)
            assertEquals(transferKey, selection.activity?.reviewKey)
            assertTrue(
                selection.activity?.responseType == ResponseType.REORDER ||
                    selection.activity?.responseType == ResponseType.FILL_IN
            )
        }
    }

    @Test
    fun a1NoneDuePreservesNextDueTimestamp() {
        languages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val evidence = completedTransferEvidence(unit)
            val starterKeys = starterLearningActivitiesFor(languageCode)
                .filter { it.level == CefrLevel.A1 }
                .map { it.reviewKey }
                .distinct()
            val schedules = starterKeys.mapIndexed { index, reviewKey ->
                schedule(reviewKey, 1_500L + index)
            }

            val selection = learningActivityQueueSelection(
                languageCode = languageCode,
                level = CefrLevel.A1,
                evidence = evidence,
                schedules = schedules,
                nowEpochMillis = now,
                preferences = preferences
            )

            assertNull(selection.activity)
            assertEquals(StarterQueueReason.NONE_DUE, selection.reason)
            assertEquals(1_500L, selection.nextDueAtEpochMillis)
        }
    }

    @Test
    fun unsupportedA1LanguagePreservesNoContent() {
        val selection = learningActivityQueueSelection(
            languageCode = "xx",
            level = CefrLevel.A1,
            evidence = emptyList(),
            schedules = emptyList(),
            nowEpochMillis = now,
            preferences = preferences
        )

        assertNull(selection.activity)
        assertEquals(StarterQueueReason.NO_CONTENT, selection.reason)
        assertNull(selection.nextDueAtEpochMillis)
    }

    @Test
    fun nonA1LevelsRemainEquivalentToStarterQueue() {
        val levels = listOf(CefrLevel.A2, CefrLevel.B1, CefrLevel.B2, CefrLevel.C1, CefrLevel.C2)

        languages.forEach { languageCode ->
            levels.forEach { level ->
                val expected = starterQueueSelection(
                    languageCode = languageCode,
                    level = level,
                    evidence = emptyList(),
                    schedules = emptyList(),
                    nowEpochMillis = now,
                    preferences = preferences
                )
                val actual = learningActivityQueueSelection(
                    languageCode = languageCode,
                    level = level,
                    evidence = emptyList(),
                    schedules = emptyList(),
                    nowEpochMillis = now,
                    preferences = preferences
                )

                assertEquals(expected, actual)
            }
        }
    }

    @Test(expected = IllegalArgumentException::class)
    fun negativeQueueTimeIsRejected() {
        learningActivityQueueSelection(
            languageCode = "en",
            level = CefrLevel.A1,
            evidence = emptyList(),
            schedules = emptyList(),
            nowEpochMillis = -1L,
            preferences = preferences
        )
    }

    private fun prerequisiteEvidence(unit: A1SecondTransferLearningUnit): List<LearningEvidence> =
        unit.narrative.linkedReviewKeys.mapIndexed { index, reviewKey ->
            LearningEvidence(
                activityId = "synthetic-${unit.narrative.languageCode}-prerequisite-$index",
                reviewKey = reviewKey,
                level = CefrLevel.A1,
                primarySkill = LearningSkill.READING,
                correct = index % 2 == 0,
                attemptedAtEpochMillis = index.toLong() + 1L
            )
        }

    private fun completedTransferEvidence(unit: A1SecondTransferLearningUnit): List<LearningEvidence> {
        val evidence = prerequisiteEvidence(unit).toMutableList()
        val sequence = listOf(unit.comprehension) + unit.retrievalTracks.flatMap { it.activities }
        sequence.forEachIndexed { index, activity ->
            evidence += LearningEvidence(
                activityId = activity.id,
                reviewKey = activity.reviewKey,
                level = activity.level,
                primarySkill = activity.primarySkill,
                correct = index % 2 == 0,
                attemptedAtEpochMillis = 100L + index
            )
        }
        return evidence
    }

    private fun schedule(reviewKey: String, dueAt: Long) = ReviewScheduleState(
        reviewKey = reviewKey,
        phase = ReviewPhase.REVIEW,
        difficulty = 5.0,
        stabilityDays = 2.0,
        dueAtEpochMillis = dueAt,
        lastReviewAtEpochMillis = 100L,
        reviewCount = 2,
        lapseCount = 0
    )
}
