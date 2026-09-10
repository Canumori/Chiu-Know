package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class A1PriorityLearningSelectionTest {

    private val languages = listOf("en", "pt", "es", "fr", "ko")
    private val now = 1_000L
    private val preferences = LearnerPreferences(
        goal = LearningGoal.CONVERSATION,
        priority = LearningPriority.SPEAKING,
        dailyMinutes = 15
    )

    @Test
    fun olderStarterReviewWinsAcrossBothReviewSources() {
        languages.forEach { languageCode ->
            val starterKey = starterReviewKey(languageCode)
            val transferKey = transferReviewKey(languageCode)
            val schedules = listOf(
                schedule(starterKey, 800L),
                schedule(transferKey, 900L)
            )

            val selection = a1PriorityLearningSelection(
                languageCode = languageCode,
                evidence = transferPrerequisiteEvidence(languageCode),
                schedules = schedules,
                nowEpochMillis = now,
                preferences = preferences
            )

            assertEquals(A1PriorityLearningReason.DUE_STARTER_REVIEW, selection.reason)
            assertEquals(starterKey, selection.activity?.reviewKey)
            assertNull(selection.transferNewWorkReason)
        }
    }

    @Test
    fun olderSecondTransferReviewWinsAcrossBothReviewSources() {
        languages.forEach { languageCode ->
            val starterKey = starterReviewKey(languageCode)
            val transferKey = transferReviewKey(languageCode)
            val schedules = listOf(
                schedule(starterKey, 900L),
                schedule(transferKey, 800L)
            )

            val selection = a1PriorityLearningSelection(
                languageCode = languageCode,
                evidence = transferPrerequisiteEvidence(languageCode),
                schedules = schedules,
                nowEpochMillis = now,
                preferences = preferences
            )

            assertEquals(A1PriorityLearningReason.DUE_SECOND_TRANSFER_REVIEW, selection.reason)
            assertEquals(transferKey, selection.activity?.reviewKey)
            assertTrue(
                selection.activity?.responseType == ResponseType.REORDER ||
                    selection.activity?.responseType == ResponseType.FILL_IN
            )
            assertNull(selection.transferNewWorkReason)
        }
    }

    @Test
    fun dueReviewAlwaysBeatsReadySecondTransferNewWork() {
        languages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val prerequisiteEvidence = transferPrerequisiteEvidence(languageCode)
            val dueStarterKey = starterReviewKey(languageCode)

            val readyNewWork = a1SecondTransferNewWorkSelection(languageCode, prerequisiteEvidence)
            assertEquals(A1SecondTransferNewWorkReason.COMPREHENSION, readyNewWork.reason)
            assertEquals(unit.comprehension.id, readyNewWork.activity?.id)

            val selection = a1PriorityLearningSelection(
                languageCode = languageCode,
                evidence = prerequisiteEvidence,
                schedules = listOf(schedule(dueStarterKey, 900L)),
                nowEpochMillis = now,
                preferences = preferences
            )

            assertEquals(A1PriorityLearningReason.DUE_STARTER_REVIEW, selection.reason)
            assertEquals(dueStarterKey, selection.activity?.reviewKey)
        }
    }

    @Test
    fun exactDueTieUsesStableA1EligibleOrder() {
        languages.forEach { languageCode ->
            val starterKey = starterReviewKey(languageCode)
            val transferKey = transferReviewKey(languageCode)
            val schedules = listOf(
                schedule(transferKey, 900L),
                schedule(starterKey, 900L)
            )

            val selection = a1PriorityLearningSelection(
                languageCode = languageCode,
                evidence = transferPrerequisiteEvidence(languageCode),
                schedules = schedules,
                nowEpochMillis = now,
                preferences = preferences
            )

            assertEquals(A1PriorityLearningReason.DUE_STARTER_REVIEW, selection.reason)
            assertEquals(starterKey, selection.activity?.reviewKey)
        }
    }

    @Test
    fun noDueReviewAllowsConnectedTransferComprehension() {
        languages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val futureSchedules = listOf(
                schedule(starterReviewKey(languageCode), 1_500L),
                schedule(transferReviewKey(languageCode), 1_600L)
            )

            val selection = a1PriorityLearningSelection(
                languageCode = languageCode,
                evidence = transferPrerequisiteEvidence(languageCode),
                schedules = futureSchedules,
                nowEpochMillis = now,
                preferences = preferences
            )

            assertEquals(A1PriorityLearningReason.SECOND_TRANSFER_NEW_WORK, selection.reason)
            assertEquals(A1SecondTransferNewWorkReason.COMPREHENSION, selection.transferNewWorkReason)
            assertEquals(unit.comprehension.id, selection.activity?.id)
        }
    }

    @Test
    fun comprehensionAttemptAdvancesToFirstResidenceRetrievalStep() {
        languages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val residence = unit.retrievalTracks.single { it.target == A1SecondTransferTarget.RESIDENCE }
            val evidence = transferPrerequisiteEvidence(languageCode).toMutableList()
            evidence += evidenceFor(unit.comprehension, correct = false, time = 100L)

            val selection = a1PriorityLearningSelection(
                languageCode = languageCode,
                evidence = evidence,
                schedules = emptyList(),
                nowEpochMillis = now,
                preferences = preferences
            )

            assertEquals(A1PriorityLearningReason.SECOND_TRANSFER_NEW_WORK, selection.reason)
            assertEquals(A1SecondTransferNewWorkReason.RETRIEVAL_STEP, selection.transferNewWorkReason)
            assertEquals(residence.activities.first().id, selection.activity?.id)
            assertEquals(ResponseType.MULTIPLE_CHOICE, selection.activity?.responseType)
        }
    }

    @Test
    fun futureOrExternalSchedulesNeverCreateDuePriority() {
        languages.forEach { languageCode ->
            val schedules = listOf(
                schedule(starterReviewKey(languageCode), 1_500L),
                schedule("external:$languageCode:not-a1", 800L)
            )

            val selection = a1PriorityLearningSelection(
                languageCode = languageCode,
                evidence = emptyList(),
                schedules = schedules,
                nowEpochMillis = now,
                preferences = preferences
            )

            assertNull(selection.activity)
            assertEquals(A1PriorityLearningReason.NONE, selection.reason)
            assertEquals(
                A1SecondTransferNewWorkReason.PREREQUISITES_NOT_EXPOSED,
                selection.transferNewWorkReason
            )
        }
    }

    @Test
    fun completedTransferSequenceReturnsNoPriorityActivity() {
        languages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val evidence = transferPrerequisiteEvidence(languageCode).toMutableList()
            val fullSequence = listOf(unit.comprehension) + unit.retrievalTracks.flatMap { it.activities }
            fullSequence.forEachIndexed { index, activity ->
                evidence += evidenceFor(activity, correct = index % 2 == 0, time = 100L + index)
            }

            val selection = a1PriorityLearningSelection(
                languageCode = languageCode,
                evidence = evidence,
                schedules = emptyList(),
                nowEpochMillis = now,
                preferences = preferences
            )

            assertNull(selection.activity)
            assertEquals(A1PriorityLearningReason.NONE, selection.reason)
            assertEquals(
                A1SecondTransferNewWorkReason.INITIAL_SEQUENCE_COMPLETE,
                selection.transferNewWorkReason
            )
        }
    }

    @Test
    fun unsupportedLanguageReturnsNoPriorityContent() {
        val selection = a1PriorityLearningSelection(
            languageCode = "xx",
            evidence = emptyList(),
            schedules = listOf(schedule("xx:a1:external", 800L)),
            nowEpochMillis = now,
            preferences = preferences
        )

        assertNull(selection.activity)
        assertEquals(A1PriorityLearningReason.NONE, selection.reason)
        assertEquals(A1SecondTransferNewWorkReason.NO_CONTENT, selection.transferNewWorkReason)
    }

    @Test(expected = IllegalArgumentException::class)
    fun negativeSelectionTimeIsRejected() {
        a1PriorityLearningSelection(
            languageCode = "en",
            evidence = emptyList(),
            schedules = emptyList(),
            nowEpochMillis = -1L,
            preferences = preferences
        )
    }

    private fun starterReviewKey(languageCode: String): String =
        starterLearningActivitiesFor(languageCode)
            .first { it.level == CefrLevel.A1 }
            .reviewKey

    private fun transferReviewKey(languageCode: String): String =
        a1SecondTransferReviewActivitiesFor(languageCode)
            .first()
            .reviewKey

    private fun transferPrerequisiteEvidence(languageCode: String): List<LearningEvidence> {
        val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
        return unit.narrative.linkedReviewKeys.mapIndexed { index, reviewKey ->
            LearningEvidence(
                activityId = "synthetic-$languageCode-prerequisite-$index",
                reviewKey = reviewKey,
                level = CefrLevel.A1,
                primarySkill = LearningSkill.READING,
                correct = index % 2 == 0,
                attemptedAtEpochMillis = index.toLong() + 1L
            )
        }
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
