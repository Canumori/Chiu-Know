package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Test

class NarrativeSessionProgressTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun everyLanguageMovesFromSixCardsThroughTwoComprehensionChecks() {
        supportedLanguages.forEach { languageCode ->
            val narrative = requireNotNull(a1FirstNarrativeMicroUnitFor(languageCode))
            val comprehension = a1FirstNarrativeComprehensionActivitiesFor(languageCode)
            var progress = narrativeSessionProgressFor(narrative, comprehension)

            assertEquals(NarrativeSessionPhase.STORY, progress.phase)
            repeat(narrative.beats.size - 1) {
                progress = advanceNarrativeSessionStory(progress)
                assertEquals(NarrativeSessionPhase.STORY, progress.phase)
            }

            progress = advanceNarrativeSessionStory(progress)
            assertEquals(NarrativeSessionPhase.COMPREHENSION, progress.phase)
            assertEquals(0, progress.comprehensionIndex)

            progress = advanceNarrativeSessionComprehension(progress)
            assertEquals(NarrativeSessionPhase.COMPREHENSION, progress.phase)
            assertEquals(1, progress.comprehensionIndex)

            progress = advanceNarrativeSessionComprehension(progress)
            assertEquals(NarrativeSessionPhase.COMPLETE, progress.phase)
            assertEquals(null, progress.comprehensionIndex)
        }
    }

    @Test
    fun advancesAreIdempotentOutsideTheirOwnPhase() {
        val narrative = requireNotNull(a1FirstNarrativeMicroUnitFor("en"))
        val comprehension = a1FirstNarrativeComprehensionActivitiesFor("en")
        var progress = narrativeSessionProgressFor(narrative, comprehension)

        assertEquals(progress, advanceNarrativeSessionComprehension(progress))

        repeat(narrative.beats.size) {
            progress = advanceNarrativeSessionStory(progress)
        }
        assertEquals(progress, advanceNarrativeSessionStory(progress))

        repeat(comprehension.size) {
            progress = advanceNarrativeSessionComprehension(progress)
        }
        assertEquals(progress, advanceNarrativeSessionStory(progress))
        assertEquals(progress, advanceNarrativeSessionComprehension(progress))
    }

    @Test
    fun rejectsComprehensionFromAnotherLanguage() {
        val narrative = requireNotNull(a1FirstNarrativeMicroUnitFor("en"))
        val portugueseActivity = a1FirstNarrativeComprehensionActivitiesFor("pt").first()

        assertThrows(IllegalArgumentException::class.java) {
            narrativeSessionProgressFor(narrative, listOf(portugueseActivity))
        }
    }
}
