package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class LearningSkillEvidenceSummaryTest {

    @Test
    fun aggregatesObservedFactsWithoutCreatingScoresOrMastery() {
        val evidence = listOf(
            attempt("grammar-1", "grammar:copula", CefrLevel.A1, LearningSkill.GRAMMAR, false, 100L),
            attempt("grammar-2", "grammar:copula", CefrLevel.A1, LearningSkill.GRAMMAR, true, 200L),
            attempt("grammar-3", "grammar:articles", CefrLevel.A1, LearningSkill.GRAMMAR, true, 300L),
            attempt("reading-1", "reading:introduction", CefrLevel.A1, LearningSkill.READING, true, 400L),
            attempt("grammar-a2", "grammar:past", CefrLevel.A2, LearningSkill.GRAMMAR, false, 500L)
        )

        val summaries = summarizeLearningEvidenceBySkill(evidence)

        assertEquals(3, summaries.size)

        val a1Grammar = summaries.single {
            it.level == CefrLevel.A1 && it.skill == LearningSkill.GRAMMAR
        }
        assertEquals(3, a1Grammar.totalAttempts)
        assertEquals(2, a1Grammar.correctAttempts)
        assertEquals(1, a1Grammar.incorrectAttempts)
        assertEquals(2, a1Grammar.distinctReviewTargetCount)
        assertEquals(100L, a1Grammar.firstAttemptAtEpochMillis)
        assertEquals(300L, a1Grammar.latestAttemptAtEpochMillis)

        assertEquals(CefrLevel.A2, summaries.first().level)
        assertEquals(LearningSkill.GRAMMAR, summaries.first().skill)
        assertTrue(summaries.none { it.skill == LearningSkill.SPEAKING })
    }

    @Test
    fun emptyEvidenceProducesNoArtificialSkillState() {
        assertTrue(summarizeLearningEvidenceBySkill(emptyList()).isEmpty())
    }

    private fun attempt(
        id: String,
        reviewKey: String,
        level: CefrLevel,
        skill: LearningSkill,
        correct: Boolean,
        at: Long
    ) = LearningEvidence(
        activityId = id,
        reviewKey = reviewKey,
        level = level,
        primarySkill = skill,
        correct = correct,
        attemptedAtEpochMillis = at
    )
}
