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
    fun reachesReorderAsThirdGrammarVariantWithoutCreatingANewReviewTarget() {
        val language = "en"
        val all = starterLearningActivitiesFor(language)
        val vocabulary = all.first { it.primarySkill == LearningSkill.VOCABULARY }
        val reading = all.first { it.primarySkill == LearningSkill.READING }
        val grammarVariants = all.filter { it.reviewKey == "en:a1:grammar:copula:first-person" }

        assertEquals(3, grammarVariants.size)
        assertEquals(ResponseType.FILL_IN, grammarVariants[0].responseType)
        assertEquals(ResponseType.FILL_IN, grammarVariants[1].responseType)
        assertEquals(ResponseType.REORDER, grammarVariants[2].responseType)
        assertEquals("en-a1-copula-reorder-001", grammarVariants[2].id)

        val evidenceBeforeGrammar = listOf(
            attempt(vocabulary, 1L), attempt(vocabulary, 2L), attempt(vocabulary, 3L),
            attempt(reading, 4L), attempt(reading, 5L), attempt(reading, 6L)
        )

        val firstGrammar = starterLearningActivityForEvidence(language, CefrLevel.A1, evidenceBeforeGrammar)!!
        assertEquals(grammarVariants[0].id, firstGrammar.id)

        val secondGrammar = starterLearningActivityForEvidence(
            language,
            CefrLevel.A1,
            evidenceBeforeGrammar + attempt(firstGrammar, 7L)
        )!!
        assertEquals(grammarVariants[1].id, secondGrammar.id)

        val reorderGrammar = starterLearningActivityForEvidence(
            language,
            CefrLevel.A1,
            evidenceBeforeGrammar + attempt(firstGrammar, 7L) + attempt(secondGrammar, 8L)
        )!!
        assertEquals(ResponseType.REORDER, reorderGrammar.responseType)
        assertEquals("en-a1-copula-reorder-001", reorderGrammar.id)
        assertEquals(firstGrammar.reviewKey, reorderGrammar.reviewKey)
    }

    @Test
    fun correctnessDoesNotChangeExposureBalancing() {
        val language = "en"
        val first = starterLearningActivityForEvidence(language, CefrLevel.A1, emptyList())!!

        val afterCorrect = starterLearningActivityForEvidence(
            language,
            CefrLevel.A1,
            listOf(attempt(first, 1L, correct = true))
        )!!
        val afterIncorrect = starterLearningActivityForEvidence(
            language,
            CefrLevel.A1,
            listOf(attempt(first, 1L, correct = false))
        )!!

        assertEquals(afterCorrect.id, afterIncorrect.id)
        assertEquals(afterCorrect.reviewKey, afterIncorrect.reviewKey)
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

    private fun attempt(
        activity: LearningActivity,
        at: Long,
        correct: Boolean = true
    ) = learningEvidenceFor(activity, correct = correct, attemptedAtEpochMillis = at)
}
