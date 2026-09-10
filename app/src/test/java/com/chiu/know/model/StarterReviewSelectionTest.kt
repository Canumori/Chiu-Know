package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class StarterReviewSelectionTest {

    @Test
    fun balancesAllReviewTargetsBeforeRepeatingContexts() {
        val language = "en"
        val all = starterLearningActivitiesFor(language)
        val targetOrder = all.distinctBy { it.reviewKey }

        var evidence = emptyList<LearningEvidence>()
        targetOrder.forEachIndexed { index, expectedTarget ->
            val selected = starterLearningActivityForEvidence(language, CefrLevel.A1, evidence)!!
            assertEquals(expectedTarget.reviewKey, selected.reviewKey)
            evidence = evidence + attempt(selected, (index + 1).toLong())
        }

        val firstTargetSecondContext = starterLearningActivityForEvidence(language, CefrLevel.A1, evidence)!!
        assertEquals(targetOrder.first().reviewKey, firstTargetSecondContext.reviewKey)
        assertEquals("en-a1-greeting-002", firstTargetSecondContext.id)
    }

    @Test
    fun rotatesVariantsWithinSameReviewTargetFromAttemptCount() {
        val language = "en"
        val all = starterLearningActivitiesFor(language)
        val targetGroups = all.groupBy { it.reviewKey }
        val firstTarget = targetGroups.keys.first()

        val evidence = targetGroups.keys.flatMapIndexed { targetIndex, reviewKey ->
            val firstVariant = targetGroups.getValue(reviewKey).first()
            List(2) { attempt(firstVariant, (targetIndex * 10 + it + 1).toLong()) }
        }

        val next = starterLearningActivityForEvidence(language, CefrLevel.A1, evidence)!!
        assertEquals(firstTarget, next.reviewKey)
        assertEquals(targetGroups.getValue(firstTarget)[0].id, next.id)
    }

    @Test
    fun reachesReorderAsThirdGrammarVariantWithoutCreatingANewReviewTarget() {
        val language = "en"
        val all = starterLearningActivitiesFor(language)
        val grammarReviewKey = "en:a1:grammar:copula:first-person"
        val grammarVariants = all.filter { it.reviewKey == grammarReviewKey }

        assertEquals(3, grammarVariants.size)
        assertEquals(ResponseType.FILL_IN, grammarVariants[0].responseType)
        assertEquals(ResponseType.FILL_IN, grammarVariants[1].responseType)
        assertEquals(ResponseType.REORDER, grammarVariants[2].responseType)
        assertEquals("en-a1-copula-reorder-001", grammarVariants[2].id)

        val evidenceBeforeGrammar = evidenceGivingOtherTargetsThreeAttempts(all, grammarReviewKey)

        val firstGrammar = starterLearningActivityForEvidence(language, CefrLevel.A1, evidenceBeforeGrammar)!!
        assertEquals(grammarVariants[0].id, firstGrammar.id)

        val secondGrammar = starterLearningActivityForEvidence(
            language,
            CefrLevel.A1,
            evidenceBeforeGrammar + attempt(firstGrammar, 100L)
        )!!
        assertEquals(grammarVariants[1].id, secondGrammar.id)

        val reorderGrammar = starterLearningActivityForEvidence(
            language,
            CefrLevel.A1,
            evidenceBeforeGrammar + attempt(firstGrammar, 100L) + attempt(secondGrammar, 101L)
        )!!
        assertEquals(ResponseType.REORDER, reorderGrammar.responseType)
        assertEquals("en-a1-copula-reorder-001", reorderGrammar.id)
        assertEquals(firstGrammar.reviewKey, reorderGrammar.reviewKey)
    }

    @Test
    fun reachesMultipleChoiceAsThirdReadingVariantWithoutCreatingANewReviewTarget() {
        val language = "en"
        val all = starterLearningActivitiesFor(language)
        val readingReviewKey = "en:a1:reading:introduction-name"
        val readingVariants = all.filter { it.reviewKey == readingReviewKey }

        assertEquals(3, readingVariants.size)
        assertEquals(ResponseType.FILL_IN, readingVariants[0].responseType)
        assertEquals(ResponseType.FILL_IN, readingVariants[1].responseType)
        assertEquals(ResponseType.MULTIPLE_CHOICE, readingVariants[2].responseType)

        val evidenceBeforeReading = evidenceGivingOtherTargetsThreeAttempts(all, readingReviewKey)

        val firstReading = starterLearningActivityForEvidence(language, CefrLevel.A1, evidenceBeforeReading)!!
        val secondReading = starterLearningActivityForEvidence(
            language,
            CefrLevel.A1,
            evidenceBeforeReading + attempt(firstReading, 100L)
        )!!
        val multipleChoiceReading = starterLearningActivityForEvidence(
            language,
            CefrLevel.A1,
            evidenceBeforeReading + attempt(firstReading, 100L) + attempt(secondReading, 101L)
        )!!

        assertEquals(readingVariants[0].id, firstReading.id)
        assertEquals(readingVariants[1].id, secondReading.id)
        assertEquals(ResponseType.MULTIPLE_CHOICE, multipleChoiceReading.responseType)
        assertEquals(readingVariants[2].id, multipleChoiceReading.id)
        assertEquals(firstReading.reviewKey, multipleChoiceReading.reviewKey)
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

    private fun evidenceGivingOtherTargetsThreeAttempts(
        all: List<LearningActivity>,
        excludedReviewKey: String
    ): List<LearningEvidence> = all
        .distinctBy { it.reviewKey }
        .filter { it.reviewKey != excludedReviewKey }
        .flatMapIndexed { targetIndex, activity ->
            List(3) { attempt(activity, (targetIndex * 10 + it + 1).toLong()) }
        }

    private fun attempt(
        activity: LearningActivity,
        at: Long,
        correct: Boolean = true
    ) = learningEvidenceFor(activity, correct = correct, attemptedAtEpochMillis = at)
}
