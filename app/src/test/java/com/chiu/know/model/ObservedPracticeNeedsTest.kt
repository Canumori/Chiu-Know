package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class ObservedPracticeNeedsTest {

    @Test
    fun singleMistakeNeverTriggersExtraPracticeByItself() {
        val needs = observedPracticeNeeds(
            listOf(
                LearningEvidence("a", "a:key", CefrLevel.A1, LearningSkill.GRAMMAR, false, 100L)
            )
        )

        assertEquals(1, needs.size)
        assertFalse(needs.single().warrantsExtraPractice())
    }

    @Test
    fun repeatedRecentErrorsCanTriggerExtraPracticeWithoutClaimingMastery() {
        val needs = observedPracticeNeeds(
            listOf(
                LearningEvidence("a1", "a1:key", CefrLevel.A1, LearningSkill.LISTENING, false, 100L),
                LearningEvidence("a2", "a2:key", CefrLevel.A1, LearningSkill.LISTENING, true, 200L),
                LearningEvidence("a3", "a3:key", CefrLevel.A1, LearningSkill.LISTENING, false, 300L),
                LearningEvidence("a4", "a4:key", CefrLevel.A1, LearningSkill.LISTENING, false, 400L)
            )
        )

        val listening = needs.single()
        assertEquals(4, listening.observedAttempts)
        assertEquals(3, listening.incorrectAttempts)
        assertEquals(1, listening.correctAttempts)
        assertTrue(listening.latestWasIncorrect)
        assertTrue(listening.warrantsExtraPractice())
    }

    @Test
    fun oldErrorsFallOutsideBoundedRecentWindow() {
        val evidence = listOf(
            LearningEvidence("old1", "old1:key", CefrLevel.A1, LearningSkill.READING, false, 10L),
            LearningEvidence("old2", "old2:key", CefrLevel.A1, LearningSkill.READING, false, 20L),
            LearningEvidence("new1", "new1:key", CefrLevel.A1, LearningSkill.READING, true, 100L),
            LearningEvidence("new2", "new2:key", CefrLevel.A1, LearningSkill.READING, true, 200L),
            LearningEvidence("new3", "new3:key", CefrLevel.A1, LearningSkill.READING, true, 300L)
        )

        val reading = observedPracticeNeeds(evidence, recentAttemptsPerSkill = 3).single()

        assertEquals(3, reading.observedAttempts)
        assertEquals(0, reading.incorrectAttempts)
        assertFalse(reading.warrantsExtraPractice())
    }

    @Test
    fun keepsSkillsSeparateAndOrdersSignalsByLatestAttempt() {
        val needs = observedPracticeNeeds(
            listOf(
                LearningEvidence("g", "g:key", CefrLevel.A1, LearningSkill.GRAMMAR, false, 200L),
                LearningEvidence("v", "v:key", CefrLevel.A1, LearningSkill.VOCABULARY, false, 500L)
            )
        )

        assertEquals(LearningSkill.VOCABULARY, needs[0].skill)
        assertEquals(LearningSkill.GRAMMAR, needs[1].skill)
    }
}
