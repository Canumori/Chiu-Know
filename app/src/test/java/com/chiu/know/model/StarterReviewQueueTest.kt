package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class StarterReviewQueueTest {

    private val language = "en"
    private val level = CefrLevel.A1
    private val activities = starterLearningActivitiesFor(language)
    private val vocabulary = activities.first { it.primarySkill == LearningSkill.VOCABULARY }
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
    fun dueReviewStillWinsOverLearnerPreference() {
        val preferences = LearnerPreferences(
            goal = LearningGoal.STUDY_OR_EXAM,
            priority = LearningPriority.READING,
            dailyMinutes = 35
        )
        val selection = starterQueueSelection(
            language,
            level,
            evidence = listOf(attempt(vocabulary, 10L)),
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
    fun reportsNextDueTimeWhenAllKnownTargetsAreFuture() {
        val schedules = listOf(
            schedule(vocabulary.reviewKey, dueAt = 500L),
            schedule(grammar.reviewKey, dueAt = 400L),
            schedule(reading.reviewKey, dueAt = 600L)
        )

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

    private fun attempt(activity: LearningActivity, at: Long) =
        learningEvidenceFor(activity, correct = true, attemptedAtEpochMillis = at)

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
