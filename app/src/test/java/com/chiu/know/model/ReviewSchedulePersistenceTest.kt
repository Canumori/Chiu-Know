package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ReviewSchedulePersistenceTest {

    @Test
    fun createsAndThenReplacesStateForSameKnowledgeTarget() {
        val firstEvidence = evidence("activity-1", "en:a1:greeting:hello", correct = true, at = 100L)
        val firstSet = updateReviewScheduleStateSet(emptySet(), firstEvidence)
        val firstState = decodeReviewScheduleStateSet(firstSet).single()

        val secondEvidence = evidence("activity-2", firstEvidence.reviewKey, correct = false, at = 200L)
        val secondSet = updateReviewScheduleStateSet(firstSet, secondEvidence)
        val secondState = decodeReviewScheduleStateSet(secondSet).single()

        assertEquals(firstEvidence.reviewKey, secondState.reviewKey)
        assertEquals(2, secondState.reviewCount)
        assertEquals(1, secondState.lapseCount)
        assertNotEquals(firstState.lastReviewAtEpochMillis, secondState.lastReviewAtEpochMillis)
    }

    @Test
    fun preservesSchedulesForOtherKnowledgeTargets() {
        val greeting = evidence("greeting", "en:a1:greeting:hello", true, 100L)
        val grammar = evidence("grammar", "en:a1:grammar:copula:first-person", true, 200L)

        val firstSet = updateReviewScheduleStateSet(emptySet(), greeting)
        val bothSet = updateReviewScheduleStateSet(firstSet, grammar)
        val states = decodeReviewScheduleStateSet(bothSet)

        assertEquals(2, states.size)
        assertEquals(setOf(greeting.reviewKey, grammar.reviewKey), states.map { it.reviewKey }.toSet())
    }

    @Test
    fun dropsMalformedStoredEntriesInsteadOfBreakingAttemptSaving() {
        val updated = updateReviewScheduleStateSet(
            encoded = setOf("broken"),
            evidence = evidence("reading", "pt:a1:reading:introduction-name", true, 300L)
        )

        assertEquals(1, decodeReviewScheduleStateSet(updated).size)
    }

    @Test
    fun rebuildsHistoricalEvidenceChronologicallyAcrossActivityVariants() {
        val history = listOf(
            evidence("choice", "en:a1:reading:introduction-name", true, 300L),
            evidence("fill-1", "en:a1:reading:introduction-name", false, 100L),
            evidence("grammar", "en:a1:grammar:copula:first-person", true, 150L),
            evidence("fill-2", "en:a1:reading:introduction-name", true, 200L)
        )

        val states = rebuildReviewScheduleStates(history)
        val reading = states.first { it.reviewKey == "en:a1:reading:introduction-name" }

        assertEquals(2, states.size)
        assertEquals(3, reading.reviewCount)
        assertEquals(1, reading.lapseCount)
        assertEquals(300L, reading.lastReviewAtEpochMillis)
    }

    @Test
    fun rebuildingEmptyHistoryCreatesNoArtificialSchedule() {
        assertEquals(emptyList<ReviewScheduleState>(), rebuildReviewScheduleStates(emptyList()))
    }

    private fun evidence(
        activityId: String,
        reviewKey: String,
        correct: Boolean,
        at: Long
    ) = LearningEvidence(
        activityId = activityId,
        reviewKey = reviewKey,
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        correct = correct,
        attemptedAtEpochMillis = at
    )
}
