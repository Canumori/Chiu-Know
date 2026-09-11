package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

class NarrativeCardProgressTest {

    @Test
    fun advancesOneBeatAtATimeAndCompletesOnlyAfterFinalCard() {
        val narrative = requireNotNull(a1FirstNarrativeMicroUnitFor("en"))
        var progress = narrativeCardProgressFor(narrative)

        assertEquals(0, progress.currentBeatIndex)
        assertFalse(progress.completed)

        repeat(narrative.beats.lastIndex) {
            progress = advanceNarrativeCardProgress(progress)
            assertFalse(progress.completed)
        }

        assertEquals(narrative.beats.lastIndex, progress.currentBeatIndex)

        progress = advanceNarrativeCardProgress(progress)
        assertTrue(progress.completed)
    }

    @Test
    fun repeatedAdvanceAfterCompletionIsIdempotent() {
        val narrative = requireNotNull(a1FirstNarrativeMicroUnitFor("ko"))
        var progress = narrativeCardProgressFor(narrative)

        repeat(narrative.beats.size) {
            progress = advanceNarrativeCardProgress(progress)
        }

        assertEquals(progress, advanceNarrativeCardProgress(progress))
        assertEquals(progress, advanceNarrativeCardProgress(progress))
    }

    @Test
    fun rejectsInvalidProgressThatCouldSkipOutsideStory() {
        assertThrows(IllegalArgumentException::class.java) {
            NarrativeCardProgress(
                narrativeId = "en-a1-story",
                beatCount = 2,
                currentBeatIndex = 2
            )
        }
    }
}
