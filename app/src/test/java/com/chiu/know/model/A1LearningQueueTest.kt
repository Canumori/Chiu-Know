package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class A1LearningQueueTest {

    private val languages = listOf("en", "pt", "es", "fr", "ko")
    private val now = 1_000L
    private val preferences = LearnerPreferences(
        goal = LearningGoal.CONVERSATION,
        priority = LearningPriority.SPEAKING,
        dailyMinutes = 15
    )

    @Test
    fun missingTransferPrerequisitesFallsBackToStarterNewTarget() {
        languages.forEach { languageCode ->
            val selection = a1LearningQueueSelection(
                languageCode = languageCode,
                evidence = emptyList(),
                schedules = emptyList(),
                nowEpochMillis = now,
                preferences = preferences
            )

            assertEquals(A1LearningQueueReason.STARTER_NEW_TARGET, selection.reason)
            assertTrue(selection.activity != null)
            assertNull(selection.nextDueAtEpochMillis)
            assertNull(selection.transferNewWorkReason)
            assertTrue(
                selection.activity?.reviewKey in starterLearningActivitiesFor(languageCode)
                    .filter { it.level == CefrLevel.A1 }
                    .map { it.reviewKey }
            )
        }
    }

    @Test
    fun readySecondTransferNewWorkPrecedesUnscheduledStarterNewTarget() {
        languages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val evidence = prerequisiteEvidence(unit)

            val selection = a1LearningQueueSelection(
                languageCode = languageCode,
                evidence = evidence,
                schedules = emptyList(),
                nowEpochMillis = now,
                preferences = preferences
            )

            assertEquals(A1LearningQueueReason.SECOND_TRANSFER_NEW_WORK, selection.reason)
            assertEquals(A1SecondTransferNewWorkReason.COMPREHENSION, selection.transferNewWorkReason)
            assertEquals(unit.comprehension.id, selection.activity?.id)
            assertNull(selection.nextDueAtEpochMillis)
        }
    }

    @Test
    fun starterDueReviewPrecedesTransferNewWorkAndStarterNewTargets() {
        languages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val starterKey = starterLearningActivitiesFor(languageCode)
                .first { it.level == CefrLevel.A1 }
                .reviewKey

            val selection = a1LearningQueueSelection(
                languageCode = languageCode,
                evidence = prerequisiteEvidence(unit),
                schedules = listOf(schedule(starterKey, 900L)),
                nowEpochMillis = now,
                preferences = preferences
            )

            assertEquals(A1LearningQueueReason.DUE_STARTER_REVIEW, selection.reason)
            assertEquals(starterKey, selection.activity?.reviewKey)
            assertNull(selection.transferNewWorkReason)
        }
    }

    @Test
    fun secondTransferDueReviewPrecedesAllNewWork() {
        languages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val transferKey = a1SecondTransferReviewActivitiesFor(languageCode).first().reviewKey

            val selection = a1LearningQueueSelection(
                languageCode = languageCode,
                evidence = prerequisiteEvidence(unit),
                schedules = listOf(schedule(transferKey, 900L)),
                nowEpochMillis = now,
                preferences = preferences
            )

            assertEquals(A1LearningQueueReason.DUE_SECOND_TRANSFER_REVIEW, selection.reason)
            assertEquals(transferKey, selection.activity?.reviewKey)
            assertTrue(
                selection.activity?.responseType == ResponseType.REORDER ||
                    selection.activity?.responseType == ResponseType.FILL_IN
            )
            assertNull(selection.transferNewWorkReason)
        }
    }

    @Test
    fun completedTransferAndFutureScheduledStarterTargetsPreserveNoneDueState() {
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

            val selection = a1LearningQueueSelection(
                languageCode = languageCode,
                evidence = evidence,
                schedules = schedules,
                nowEpochMillis = now,
                preferences = preferences
            )

            assertNull(selection.activity)
            assertEquals(A1LearningQueueReason.NONE_DUE, selection.reason)
            assertEquals(1_500L, selection.nextDueAtEpochMillis)
            assertNull(selection.transferNewWorkReason)
        }
    }

    @Test
    fun futureStarterScheduleDoesNotBlockReadyTransferNewWork() {
        languages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val starterKey = starterLearningActivitiesFor(languageCode)
                .first { it.level == CefrLevel.A1 }
                .reviewKey

            val selection = a1LearningQueueSelection(
                languageCode = languageCode,
                evidence = prerequisiteEvidence(unit),
                schedules = listOf(schedule(starterKey, 1_500L)),
                nowEpochMillis = now,
                preferences = preferences
            )

            assertEquals(A1LearningQueueReason.SECOND_TRANSFER_NEW_WORK, selection.reason)
            assertEquals(unit.comprehension.id, selection.activity?.id)
        }
    }

    @Test
    fun externalSchedulesDoNotDisturbStarterFallback() {
        languages.forEach { languageCode ->
            val selection = a1LearningQueueSelection(
                languageCode = languageCode,
                evidence = emptyList(),
                schedules = listOf(schedule("external:$languageCode:not-a1", 800L)),
                nowEpochMillis = now,
                preferences = preferences
            )

            assertEquals(A1LearningQueueReason.STARTER_NEW_TARGET, selection.reason)
            assertTrue(selection.activity != null)
        }
    }

    @Test
    fun unsupportedLanguagePreservesNoContentState() {
        val selection = a1LearningQueueSelection(
            languageCode = "xx",
            evidence = emptyList(),
            schedules = emptyList(),
            nowEpochMillis = now,
            preferences = preferences
        )

        assertNull(selection.activity)
        assertEquals(A1LearningQueueReason.NO_CONTENT, selection.reason)
        assertNull(selection.nextDueAtEpochMillis)
        assertNull(selection.transferNewWorkReason)
    }

    @Test(expected = IllegalArgumentException::class)
    fun negativeQueueTimeIsRejected() {
        a1LearningQueueSelection(
            languageCode = "en",
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
