package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class A1SecondTransferReviewQueueTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun reviewPoolUsesOnlyStrongClosedVariantsForTwoTransferTargets() {
        supportedLanguages.forEach { languageCode ->
            val activities = a1SecondTransferReviewActivitiesFor(languageCode)

            assertEquals(4, activities.size)
            assertTrue(activities.all { it.level == CefrLevel.A1 })
            assertTrue(activities.all { it.primarySkill == LearningSkill.READING })
            assertEquals(
                setOf(ResponseType.REORDER, ResponseType.FILL_IN),
                activities.map { it.responseType }.toSet()
            )

            val groups = activities.groupBy { it.reviewKey }
            assertEquals(2, groups.size)
            groups.values.forEach { variants ->
                assertEquals(2, variants.size)
                assertEquals(
                    listOf(ResponseType.REORDER, ResponseType.FILL_IN),
                    variants.map { it.responseType }
                )
            }
        }
    }

    @Test
    fun secondTransferReviewKeysRemainOutsideStarterQueue() {
        supportedLanguages.forEach { languageCode ->
            val starterKeys = starterLearningActivitiesFor(languageCode).map { it.reviewKey }.toSet()
            val transferKeys = a1SecondTransferReviewActivitiesFor(languageCode).map { it.reviewKey }.toSet()

            assertTrue(transferKeys.intersect(starterKeys).isEmpty())
        }
    }

    @Test
    fun doesNotIntroduceTransferTargetWithoutSchedule() {
        val selection = a1SecondTransferReviewSelection(
            languageCode = "en",
            evidence = emptyList(),
            schedules = emptyList(),
            nowEpochMillis = 100L
        )

        assertEquals(A1SecondTransferReviewReason.NO_SCHEDULE, selection.reason)
        assertNull(selection.activity)
        assertNull(selection.nextDueAtEpochMillis)
    }

    @Test
    fun ignoresSchedulesFromOtherQueues() {
        val starter = starterLearningActivitiesFor("en").first()
        val selection = a1SecondTransferReviewSelection(
            languageCode = "en",
            evidence = emptyList(),
            schedules = listOf(schedule(starter.reviewKey, dueAt = 50L)),
            nowEpochMillis = 100L
        )

        assertEquals(A1SecondTransferReviewReason.NO_SCHEDULE, selection.reason)
        assertNull(selection.activity)
    }

    @Test
    fun reportsEarliestFutureDueTimeWithoutPullingReviewForward() {
        val groups = a1SecondTransferReviewActivitiesFor("en").groupBy { it.reviewKey }
        val residenceKey = groups.keys.first()
        val preferenceKey = groups.keys.last()

        val selection = a1SecondTransferReviewSelection(
            languageCode = "en",
            evidence = emptyList(),
            schedules = listOf(
                schedule(residenceKey, dueAt = 400L),
                schedule(preferenceKey, dueAt = 300L)
            ),
            nowEpochMillis = 100L
        )

        assertEquals(A1SecondTransferReviewReason.NONE_DUE, selection.reason)
        assertNull(selection.activity)
        assertEquals(300L, selection.nextDueAtEpochMillis)
    }

    @Test
    fun earliestDueTransferTargetWins() {
        val groups = a1SecondTransferReviewActivitiesFor("en").groupBy { it.reviewKey }
        val residenceKey = groups.keys.first()
        val preferenceKey = groups.keys.last()

        val selection = a1SecondTransferReviewSelection(
            languageCode = "en",
            evidence = emptyList(),
            schedules = listOf(
                schedule(residenceKey, dueAt = 90L),
                schedule(preferenceKey, dueAt = 50L)
            ),
            nowEpochMillis = 100L
        )

        assertEquals(A1SecondTransferReviewReason.DUE_REVIEW, selection.reason)
        assertEquals(preferenceKey, selection.activity?.reviewKey)
    }

    @Test
    fun equalDueTimesKeepNarrativeOrderResidenceBeforePreference() {
        val groups = a1SecondTransferReviewActivitiesFor("en").groupBy { it.reviewKey }
        val residenceKey = groups.keys.first()
        val preferenceKey = groups.keys.last()

        val selection = a1SecondTransferReviewSelection(
            languageCode = "en",
            evidence = emptyList(),
            schedules = listOf(
                schedule(preferenceKey, dueAt = 50L),
                schedule(residenceKey, dueAt = 50L)
            ),
            nowEpochMillis = 100L
        )

        assertEquals(A1SecondTransferReviewReason.DUE_REVIEW, selection.reason)
        assertEquals(residenceKey, selection.activity?.reviewKey)
    }

    @Test
    fun observedAttemptsRotateDueTargetBetweenReorderAndFillIn() {
        val residenceVariants = a1SecondTransferReviewActivitiesFor("en")
            .groupBy { it.reviewKey }
            .values
            .first()
        val reviewKey = residenceVariants.first().reviewKey
        val dueSchedule = listOf(schedule(reviewKey, dueAt = 50L))

        val firstSelection = a1SecondTransferReviewSelection(
            languageCode = "en",
            evidence = emptyList(),
            schedules = dueSchedule,
            nowEpochMillis = 100L
        )
        assertEquals(ResponseType.REORDER, firstSelection.activity?.responseType)
        assertEquals(residenceVariants[0].id, firstSelection.activity?.id)

        val secondSelection = a1SecondTransferReviewSelection(
            languageCode = "en",
            evidence = listOf(attempt(residenceVariants[0], at = 10L)),
            schedules = dueSchedule,
            nowEpochMillis = 100L
        )
        assertEquals(ResponseType.FILL_IN, secondSelection.activity?.responseType)
        assertEquals(residenceVariants[1].id, secondSelection.activity?.id)

        val thirdSelection = a1SecondTransferReviewSelection(
            languageCode = "en",
            evidence = listOf(
                attempt(residenceVariants[0], at = 10L),
                attempt(residenceVariants[1], at = 20L)
            ),
            schedules = dueSchedule,
            nowEpochMillis = 100L
        )
        assertEquals(ResponseType.REORDER, thirdSelection.activity?.responseType)
        assertEquals(residenceVariants[0].id, thirdSelection.activity?.id)
    }

    @Test
    fun unsupportedLanguageReportsNoContentWithoutInventingActivity() {
        val selection = a1SecondTransferReviewSelection(
            languageCode = "de",
            evidence = emptyList(),
            schedules = emptyList(),
            nowEpochMillis = 100L
        )

        assertEquals(A1SecondTransferReviewReason.NO_CONTENT, selection.reason)
        assertNull(selection.activity)
        assertNull(selection.nextDueAtEpochMillis)
    }

    @Test
    fun koreanReviewPoolKeepsReviewedResidenceAndPreferenceForms() {
        val activities = a1SecondTransferReviewActivitiesFor("ko")

        assertTrue(activities.any { it.feedback.contains("리우에 살아요.") })
        assertTrue(activities.any { it.feedback.contains("커피를 좋아해요.") })
        assertTrue(activities.none { it.responseType == ResponseType.FREE_TEXT })
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
