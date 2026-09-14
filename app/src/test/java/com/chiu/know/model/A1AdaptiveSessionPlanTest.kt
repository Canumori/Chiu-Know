package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class A1AdaptiveSessionPlanTest {

    private val languages = listOf("en", "pt", "es", "fr", "ko")
    private val preferences = LearnerPreferences(
        goal = LearningGoal.CONVERSATION,
        priority = LearningPriority.SPEAKING,
        dailyMinutes = 15
    )
    private val now = 1_000L

    @Test
    fun starterAndSecondTransferDueReviewsReserveSessionSlotsTogether() {
        languages.forEach { languageCode ->
            val starterKey = starterLearningActivitiesFor(languageCode)
                .first { it.level == CefrLevel.A1 }
                .reviewKey
            val transferKey = a1SecondTransferReviewActivitiesFor(languageCode)
                .first()
                .reviewKey
            val schedules = listOf(
                schedule(starterKey, 900L),
                schedule(transferKey, 1_000L),
                schedule("external:$languageCode", 800L)
            )

            val planning = a1AdaptiveSessionPlanning(
                languageCode = languageCode,
                preferences = preferences,
                schedules = schedules,
                evidence = emptyList(),
                nowEpochMillis = now
            )

            assertEquals(2, planning.reviewLoad.dueReviewCount)
            assertEquals(2, planning.sessionPlan.reservedDueReviews)
            assertEquals(7, planning.sessionPlan.targetActivities)
            assertEquals(5, planning.sessionPlan.newPracticeSlots)
        }
    }

    @Test
    fun largeA1DueBacklogUsesSessionBudgetWithoutInventingCompletion() {
        languages.forEach { languageCode ->
            val eligibleKeys = a1ReviewLoad(languageCode, emptyList(), now).eligibleReviewKeys
            assertTrue(eligibleKeys.size >= 7)
            val schedules = eligibleKeys.take(10).mapIndexed { index, reviewKey ->
                schedule(reviewKey, 800L + index)
            }

            val planning = a1AdaptiveSessionPlanning(
                languageCode = languageCode,
                preferences = preferences,
                schedules = schedules,
                evidence = emptyList(),
                nowEpochMillis = now
            )

            assertTrue(planning.reviewLoad.dueReviewCount >= 7)
            assertEquals(7, planning.sessionPlan.targetActivities)
            assertEquals(7, planning.sessionPlan.reservedDueReviews)
            assertEquals(0, planning.sessionPlan.newPracticeSlots)
            assertTrue(planning.reviewLoad.dueReviewCount >= planning.sessionPlan.reservedDueReviews)
        }
    }

    @Test
    fun futureAndExternalSchedulesDoNotReserveReviewSlots() {
        languages.forEach { languageCode ->
            val eligibleKey = a1ReviewLoad(languageCode, emptyList(), now).eligibleReviewKeys.first()
            val schedules = listOf(
                schedule(eligibleKey, 1_500L),
                schedule("external:$languageCode", 900L)
            )

            val planning = a1AdaptiveSessionPlanning(
                languageCode = languageCode,
                preferences = preferences,
                schedules = schedules,
                evidence = emptyList(),
                nowEpochMillis = now
            )

            assertEquals(0, planning.reviewLoad.dueReviewCount)
            assertEquals(0, planning.sessionPlan.reservedDueReviews)
            assertEquals(7, planning.sessionPlan.newPracticeSlots)
            assertEquals(1_500L, planning.reviewLoad.nextDueAtEpochMillis)
        }
    }

    @Test
    fun repeatedA1ErrorsCanCreatePracticeFocusButA2ErrorsCannotLeakIntoA1Planning() {
        val a1GrammarErrors = listOf(
            evidence("a1-g1", CefrLevel.A1, LearningSkill.GRAMMAR, false, 100L),
            evidence("a1-g2", CefrLevel.A1, LearningSkill.GRAMMAR, false, 200L),
            evidence("a1-g3", CefrLevel.A1, LearningSkill.GRAMMAR, false, 300L)
        )
        val a2ListeningErrors = listOf(
            evidence("a2-l1", CefrLevel.A2, LearningSkill.LISTENING, false, 400L),
            evidence("a2-l2", CefrLevel.A2, LearningSkill.LISTENING, false, 500L),
            evidence("a2-l3", CefrLevel.A2, LearningSkill.LISTENING, false, 600L),
            evidence("a2-l4", CefrLevel.A2, LearningSkill.LISTENING, false, 700L)
        )

        val planning = a1AdaptiveSessionPlanning(
            languageCode = "en",
            preferences = preferences,
            schedules = emptyList(),
            evidence = a1GrammarErrors + a2ListeningErrors,
            nowEpochMillis = now
        )

        assertTrue(planning.sessionPlan.extraPracticeSkills.contains(LearningSkill.GRAMMAR))
        assertFalse(planning.sessionPlan.extraPracticeSkills.contains(LearningSkill.LISTENING))
    }

    @Test(expected = IllegalArgumentException::class)
    fun negativePlanningTimeIsStillRejectedByReviewLoadBoundary() {
        a1AdaptiveSessionPlanning(
            languageCode = "en",
            preferences = preferences,
            schedules = emptyList(),
            evidence = emptyList(),
            nowEpochMillis = -1L
        )
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

    private fun evidence(
        id: String,
        level: CefrLevel,
        skill: LearningSkill,
        correct: Boolean,
        time: Long
    ) = LearningEvidence(
        activityId = id,
        reviewKey = "$id:key",
        level = level,
        primarySkill = skill,
        correct = correct,
        attemptedAtEpochMillis = time
    )
}
