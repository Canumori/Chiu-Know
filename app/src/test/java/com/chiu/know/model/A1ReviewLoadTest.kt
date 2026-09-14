package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class A1ReviewLoadTest {

    private val languages = listOf("en", "pt", "es", "fr", "ko")
    private val now = 1_000L

    @Test
    fun eligibleKeysAreUniqueAndCoverStarterAndSecondTransferInEveryLanguage() {
        languages.forEach { languageCode ->
            val load = a1ReviewLoad(languageCode, emptyList(), now)
            val starterKeys = starterLearningActivitiesFor(languageCode)
                .filter { it.level == CefrLevel.A1 }
                .map { it.reviewKey }
                .distinct()
            val transferKeys = a1SecondTransferReviewActivitiesFor(languageCode)
                .map { it.reviewKey }
                .distinct()

            assertEquals(load.eligibleReviewKeys.distinct(), load.eligibleReviewKeys)
            assertTrue(starterKeys.isNotEmpty())
            assertTrue(transferKeys.isNotEmpty())
            assertTrue(load.eligibleReviewKeys.containsAll(starterKeys))
            assertTrue(load.eligibleReviewKeys.containsAll(transferKeys))
            assertEquals((starterKeys + transferKeys).distinct(), load.eligibleReviewKeys)
            assertEquals(0, load.dueReviewCount)
            assertNull(load.nextDueAtEpochMillis)
        }
    }

    @Test
    fun dueSchedulesFromStarterAndSecondTransferAreCountedTogether() {
        languages.forEach { languageCode ->
            val starterKey = starterLearningActivitiesFor(languageCode)
                .first { it.level == CefrLevel.A1 }
                .reviewKey
            val transferKey = a1SecondTransferReviewActivitiesFor(languageCode)
                .first()
                .reviewKey
            val schedules = listOf(
                schedule(starterKey, dueAt = 900L),
                schedule(transferKey, dueAt = 1_000L)
            )

            val load = a1ReviewLoad(languageCode, schedules, now)

            assertEquals(2, load.dueReviewCount)
            assertEquals(listOf(starterKey, transferKey).toSet(), load.dueReviewKeys.toSet())
            assertNull(load.nextDueAtEpochMillis)
        }
    }

    @Test
    fun futureAndExternalSchedulesDoNotInflateDueCountAndEarliestFutureIsReported() {
        languages.forEach { languageCode ->
            val starterKeys = starterLearningActivitiesFor(languageCode)
                .filter { it.level == CefrLevel.A1 }
                .map { it.reviewKey }
                .distinct()
            val transferKey = a1SecondTransferReviewActivitiesFor(languageCode)
                .first()
                .reviewKey
            val firstStarterKey = starterKeys.first()
            val secondStarterKey = starterKeys.drop(1).firstOrNull() ?: firstStarterKey
            val schedules = listOf(
                schedule(firstStarterKey, dueAt = 1_500L),
                schedule(secondStarterKey, dueAt = 1_200L),
                schedule(transferKey, dueAt = 900L),
                schedule("external:$languageCode:not-a1-review", dueAt = 800L)
            )

            val load = a1ReviewLoad(languageCode, schedules, now)

            assertEquals(1, load.dueReviewCount)
            assertEquals(listOf(transferKey), load.dueReviewKeys)
            assertEquals(1_200L, load.nextDueAtEpochMillis)
            assertFalse(load.eligibleReviewKeys.contains("external:$languageCode:not-a1-review"))
        }
    }

    @Test
    fun duplicateSchedulesForSameReviewKeyNeverDoubleCount() {
        languages.forEach { languageCode ->
            val reviewKey = a1SecondTransferReviewActivitiesFor(languageCode)
                .first()
                .reviewKey
            val schedules = listOf(
                schedule(reviewKey, dueAt = 800L),
                schedule(reviewKey, dueAt = 900L)
            )

            val load = a1ReviewLoad(languageCode, schedules, now)

            assertEquals(1, load.dueReviewCount)
            assertEquals(listOf(reviewKey), load.dueReviewKeys)
        }
    }

    @Test
    fun unsupportedLanguageHasNoA1ReviewLoad() {
        val load = a1ReviewLoad("xx", listOf(schedule("xx:a1:foreign", dueAt = 900L)), now)

        assertTrue(load.eligibleReviewKeys.isEmpty())
        assertTrue(load.dueReviewKeys.isEmpty())
        assertEquals(0, load.dueReviewCount)
        assertNull(load.nextDueAtEpochMillis)
    }

    @Test(expected = IllegalArgumentException::class)
    fun negativePlanningTimeIsRejected() {
        a1ReviewLoad("en", emptyList(), -1L)
    }

    private fun schedule(
        reviewKey: String,
        dueAt: Long
    ) = ReviewScheduleState(
        reviewKey = reviewKey,
        phase = ReviewPhase.REVIEW,
        difficulty = 5.0,
        stabilityDays = 2.0,
        dueAtEpochMillis = dueAt,
        lastReviewAtEpochMillis = 100L,
        reviewCount = 2,
        lapseCount = 0
    )
}
