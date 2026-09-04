package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class StarterReviewQueueTest {

    private val language = "en"
    private val level = CefrLevel.A1
    private val activities = starterLearningActivitiesFor(language)
    private val vocabulary = activities.first { it.id == "en-a1-greeting-001" }
    private val gratitude = activities.first { it.id == "en-a1-gratitude-001" }
    private val grammar = activities.first { it.primarySkill == LearningSkill.GRAMMAR }
    private val reading = activities.first { it.primarySkill == LearningSkill.READING }

    @Test
    fun selectsFirstNewKnowledgeTargetWhenNothingHasSchedule() {
        val selection = starterQueueSelection(language, level, emptyList(), emptyList(), nowEpochMillis = 100L)

        assertEquals(StarterQueueReason.NEW_TARGET, selection.reason)
        assertEquals(vocabulary.id, selection.activity?.id)
    }

    @Test
    fun learnerPreferencesMayReorderOnlyNewTargets() {
        val preferences = LearnerPreferences(
            goal = LearningGoal.STUDY_OR_EXAM,
            priority = LearningPriority.READING,
            dailyMinutes = 35
        )

        val selection = starterQueueSelection(
            language,
            level,
            evidence = emptyList(),
            schedules = emptyList(),
            nowEpochMillis = 100L,
            preferences = preferences
        )

        assertEquals(StarterQueueReason.NEW_TARGET, selection.reason)
        assertEquals(LearningSkill.READING, selection.activity?.primarySkill)
    }

    @Test
    fun repeatedObservedNeedCanGuideAnotherUnscheduledTargetInSameSkill() {
        val preferences = LearnerPreferences(
            goal = LearningGoal.STUDY_OR_EXAM,
            priority = LearningPriority.READING,
            dailyMinutes = 35
        )
        val evidence = listOf(
            attempt(vocabulary, 10L, correct = false),
            attempt(vocabulary, 20L, correct = false),
            attempt(vocabulary, 30L, correct = true)
        )

        val selection = starterQueueSelection(
            language,
            level,
            evidence = evidence,
            schedules = listOf(schedule(vocabulary.reviewKey, dueAt = 500L)),
            nowEpochMillis = 100L,
            preferences = preferences
        )

        assertEquals(StarterQueueReason.NEW_TARGET, selection.reason)
        assertEquals(gratitude.reviewKey, selection.activity?.reviewKey)
        assertEquals(LearningSkill.VOCABULARY, selection.activity?.primarySkill)
    }

    @Test
    fun dueReviewStillWinsOverLearnerPreferenceAndObservedNeed() {
        val preferences = LearnerPreferences(
            goal = LearningGoal.STUDY_OR_EXAM,
            priority = LearningPriority.READING,
            dailyMinutes = 35
        )
        val evidence = listOf(
            attempt(vocabulary, 10L, correct = false),
            attempt(vocabulary, 20L, correct = false),
            attempt(vocabulary, 30L, correct = true)
        )
        val selection = starterQueueSelection(
            language,
            level,
            evidence = evidence,
            schedules = listOf(schedule(vocabulary.reviewKey, dueAt = 90L)),
            nowEpochMillis = 100L,
            preferences = preferences
        )

        assertEquals(StarterQueueReason.DUE_REVIEW, selection.reason)
        assertEquals(vocabulary.reviewKey, selection.activity?.reviewKey)
    }

    @Test
    fun selectsDueReviewBeforeIntroducingAnotherTarget() {
        val selection = starterQueueSelection(
            language,
            level,
            evidence = listOf(attempt(vocabulary, 10L)),
            schedules = listOf(schedule(vocabulary.reviewKey, dueAt = 90L)),
            nowEpochMillis = 100L
        )

        assertEquals(StarterQueueReason.DUE_REVIEW, selection.reason)
        assertEquals(vocabulary.reviewKey, selection.activity?.reviewKey)
    }

    @Test
    fun introducesNextUnscheduledTargetWhenExistingReviewIsFuture() {
        val selection = starterQueueSelection(
            language,
            level,
            evidence = listOf(attempt(vocabulary, 10L)),
            schedules = listOf(schedule(vocabulary.reviewKey, dueAt = 500L)),
            nowEpochMillis = 100L
        )

        assertEquals(StarterQueueReason.NEW_TARGET, selection.reason)
        assertEquals(grammar.reviewKey, selection.activity?.reviewKey)
    }

    @Test
    fun rotatesVariantsInsideDueKnowledgeTargetFromObservedAttempts() {
        val variants = activities.filter { it.reviewKey == reading.reviewKey }
        val evidence = listOf(attempt(variants[0], 10L), attempt(variants[1], 20L))
        val selection = starterQueueSelection(
            language,
            level,
            evidence,
            schedules = listOf(schedule(reading.reviewKey, dueAt = 50L)),
            nowEpochMillis = 100L
        )

        assertEquals(StarterQueueReason.DUE_REVIEW, selection.reason)
        assertEquals(ResponseType.MULTIPLE_CHOICE, selection.activity?.responseType)
        assertEquals(variants[2].id, selection.activity?.id)
    }

    @Test
    fun residenceInteractionRotatesFromRecognitionToReducedCueRetrieval() {
        val recognition = activities.first { it.id == "en-a1-interaction-residence-001" }
        val retrieval = activities.first { it.id == "en-a1-interaction-residence-002" }

        assertEquals(recognition.reviewKey, retrieval.reviewKey)
        assertEquals(ResponseType.MULTIPLE_CHOICE, recognition.responseType)
        assertEquals(ResponseType.FILL_IN, retrieval.responseType)

        val selection = starterQueueSelection(
            language,
            level,
            evidence = listOf(attempt(recognition, 10L)),
            schedules = listOf(schedule(recognition.reviewKey, dueAt = 50L)),
            nowEpochMillis = 100L
        )

        assertEquals(StarterQueueReason.DUE_REVIEW, selection.reason)
        assertEquals(retrieval.id, selection.activity?.id)
    }

    @Test
    fun preferenceInteractionRotatesFromRecognitionToReducedCueRetrieval() {
        val recognition = activities.first { it.id == "en-a1-interaction-preference-001" }
        val retrieval = activities.first { it.id == "en-a1-interaction-preference-retrieval-001" }

        assertEquals(recognition.reviewKey, retrieval.reviewKey)
        assertEquals(ResponseType.MULTIPLE_CHOICE, recognition.responseType)
        assertEquals(ResponseType.FILL_IN, retrieval.responseType)

        val selection = starterQueueSelection(
            language,
            level,
            evidence = listOf(attempt(recognition, 10L)),
            schedules = listOf(schedule(recognition.reviewKey, dueAt = 50L)),
            nowEpochMillis = 100L
        )

        assertEquals(StarterQueueReason.DUE_REVIEW, selection.reason)
        assertEquals(retrieval.id, selection.activity?.id)
    }

    @Test
    fun reportsNextDueTimeWhenAllKnownTargetsAreFuture() {
        val schedules = activities
            .distinctBy { it.reviewKey }
            .mapIndexed { index, activity -> schedule(activity.reviewKey, dueAt = 400L + index * 100L) }

        val selection = starterQueueSelection(
            language,
            level,
            evidence = emptyList(),
            schedules = schedules,
            nowEpochMillis = 100L
        )

        assertEquals(StarterQueueReason.NONE_DUE, selection.reason)
        assertNull(selection.activity)
        assertEquals(400L, selection.nextDueAtEpochMillis)
    }

    @Test
    fun reportsNoContentWithoutInventingHigherLevelActivities() {
        val selection = starterQueueSelection(language, CefrLevel.C2, emptyList(), emptyList(), 100L)

        assertEquals(StarterQueueReason.NO_CONTENT, selection.reason)
        assertNull(selection.activity)
    }

    private fun attempt(activity: LearningActivity, at: Long, correct: Boolean = true) =
        learningEvidenceFor(activity, correct = correct, attemptedAtEpochMillis = at)

    private fun schedule(reviewKey: String, dueAt: Long) = ReviewScheduleState(
        reviewKey = reviewKey,
        phase = ReviewPhase.REVIEW,
        difficulty = 5.0,
        stabilityDays = 1.0,
        dueAtEpochMillis = dueAt,
        lastReviewAtEpochMillis = 0L,
        reviewCount = 1,
        lapseCount = 0
    )
}
