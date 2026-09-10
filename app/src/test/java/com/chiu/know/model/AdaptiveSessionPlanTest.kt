package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AdaptiveSessionPlanTest {

    private val preferences = LearnerPreferences(
        goal = LearningGoal.CONVERSATION,
        priority = LearningPriority.SPEAKING,
        dailyMinutes = 15
    )

    @Test
    fun dueReviewsReserveSessionSlotsBeforeNewPractice() {
        val plan = adaptiveSessionPlan(preferences, dueReviewCount = 5)

        assertEquals(7, plan.targetActivities)
        assertEquals(5, plan.reservedDueReviews)
        assertEquals(2, plan.newPracticeSlots)
    }

    @Test
    fun dueBacklogNeverInflatesSessionBudgetOrDisappears() {
        val plan = adaptiveSessionPlan(preferences, dueReviewCount = 100)

        assertEquals(7, plan.targetActivities)
        assertEquals(7, plan.reservedDueReviews)
        assertEquals(0, plan.newPracticeSlots)
    }

    @Test
    fun repeatedErrorsCanFlagExtraPracticeSkill() {
        val evidence = listOf(
            LearningEvidence("l1", "l1:key", CefrLevel.A1, LearningSkill.LISTENING, false, 100L),
            LearningEvidence("l2", "l2:key", CefrLevel.A1, LearningSkill.LISTENING, true, 200L),
            LearningEvidence("l3", "l3:key", CefrLevel.A1, LearningSkill.LISTENING, false, 300L),
            LearningEvidence("l4", "l4:key", CefrLevel.A1, LearningSkill.LISTENING, false, 400L)
        )

        val plan = adaptiveSessionPlan(preferences, dueReviewCount = 0, evidence = evidence)

        assertTrue(plan.extraPracticeSkills.contains(LearningSkill.LISTENING))
    }

    @Test
    fun isolatedErrorDoesNotCreateExtraPracticeFocus() {
        val evidence = listOf(
            LearningEvidence("w1", "w1:key", CefrLevel.A1, LearningSkill.WRITING, false, 100L)
        )

        val plan = adaptiveSessionPlan(preferences, dueReviewCount = 0, evidence = evidence)

        assertFalse(plan.extraPracticeSkills.contains(LearningSkill.WRITING))
    }

    @Test
    fun strongerObservedNeedIsOrderedBeforeWeakerOne() {
        val evidence = listOf(
            LearningEvidence("g1", "g1:key", CefrLevel.A1, LearningSkill.GRAMMAR, false, 100L),
            LearningEvidence("g2", "g2:key", CefrLevel.A1, LearningSkill.GRAMMAR, false, 200L),
            LearningEvidence("g3", "g3:key", CefrLevel.A1, LearningSkill.GRAMMAR, false, 300L),
            LearningEvidence("v1", "v1:key", CefrLevel.A1, LearningSkill.VOCABULARY, false, 400L),
            LearningEvidence("v2", "v2:key", CefrLevel.A1, LearningSkill.VOCABULARY, false, 500L),
            LearningEvidence("v3", "v3:key", CefrLevel.A1, LearningSkill.VOCABULARY, true, 600L)
        )

        val plan = adaptiveSessionPlan(preferences, dueReviewCount = 0, evidence = evidence)

        assertEquals(LearningSkill.GRAMMAR, plan.extraPracticeSkills.first())
    }
}
