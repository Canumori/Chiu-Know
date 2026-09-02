package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class StarterReviewSelectionTest {

    @Test
    fun balancesReviewTargetsBeforeRepeatingContexts() {
        val language = "en"
        val vocabulary = starterLearningActivityForEvidence(language, CefrLevel.A1, emptyList())!!
        assertEquals("en-a1-greeting-001", vocabulary.id)

        val afterVocabulary = listOf(attempt(vocabulary, 1L))
        val grammar = starterLearningActivityForEvidence(language, CefrLevel.A1, afterVocabulary)!!
        assertEquals(LearningSkill.GRAMMAR, grammar.primarySkill)

        val afterGrammar = afterVocabulary + attempt(grammar, 2L)
        val reading = starterLearningActivityForEvidence(language, CefrLevel.A1, afterGrammar)!!
        assertEquals(LearningSkill.READING, reading.primarySkill)

        val afterReading = afterGrammar + attempt(reading, 3L)
        val secondVocabularyContext = starterLearningActivityForEvidence(language, CefrLevel.A1, afterReading)!!
        assertEquals("en-a1-greeting-002", secondVocabularyContext.id)
    }

    @Test
    fun rotatesVariantsWithinSameReviewTargetFromAttemptCount() {
        val language = "en"
        val all = starterLearningActivitiesFor(language)
        val vocabularyFirst = all.first { it.id == "en-a1-greeting-001" }
        val grammarFirst = all.first { it.primarySkill == LearningSkill.GRAMMAR }
        val readingFirst = all.first { it.primarySkill == LearningSkill.READING }

        val evidence = listOf(
            attempt(vocabularyFirst, 1L),
            attempt(grammarFirst, 2L),
            attempt(readingFirst, 3L),
            attempt(vocabularyFirst, 4L),
            attempt(grammarFirst, 5L),
            attempt(readingFirst, 6L)
        )

        val next = starterLearningActivityForEvidence(language, CefrLevel.A1, evidence)!!
        assertEquals("en-a1-greeting-001", next.id)
    }

    @Test
    fun ignoresEvidenceOutsideAvailableLevelAndReturnsNullForMissingContent() {
        val a1 = starterLearningActivityFor("en", CefrLevel.A1)!!
        val unrelated = LearningEvidence(
            activityId = "historic-b1",
            reviewKey = a1.reviewKey,
            level = CefrLevel.B1,
            primarySkill = LearningSkill.VOCABULARY,
            correct = true,
            attemptedAtEpochMillis = 10L
        )

        assertEquals("en-a1-greeting-001", starterLearningActivityForEvidence("en", CefrLevel.A1, listOf(unrelated))?.id)
        assertNull(starterLearningActivityForEvidence("en", CefrLevel.B1, emptyList()))
    }

    private fun attempt(activity: LearningActivity, at: Long) =
        learningEvidenceFor(activity, correct = true, attemptedAtEpochMillis = at)
}
