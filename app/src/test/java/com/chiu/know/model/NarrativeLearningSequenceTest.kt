package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertThrows
import org.junit.Assert.assertTrue
import org.junit.Test

class NarrativeLearningSequenceTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun connectsThreeDistinctA1ContextsForEverySupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val sequence = requireNotNull(a1NarrativeLearningSequenceFor(languageCode))
            val introducedTargets = sequence.units.first().linkedReviewKeys.toSet()

            assertEquals(languageCode, sequence.languageCode)
            assertEquals(CefrLevel.A1, sequence.level)
            assertEquals(3, sequence.units.size)
            assertEquals(3, sequence.units.map { it.id }.distinct().size)
            assertEquals(3, sequence.units.map { it.setting }.distinct().size)
            assertTrue(
                sequence.units.drop(1).all { later ->
                    later.linkedReviewKeys.all { it in introducedTargets }
                }
            )
        }
    }

    @Test
    fun rejectsLaterNarrativeWithTargetNotIntroducedInFirstContext() {
        val sequence = requireNotNull(a1NarrativeLearningSequenceFor("en"))
        val disconnected = sequence.units[1].copy(
            linkedReviewKeys = sequence.units[1].linkedReviewKeys + "en:a1:unknown:new-target"
        )

        assertThrows(IllegalArgumentException::class.java) {
            NarrativeLearningSequence(
                listOf(sequence.units.first(), disconnected, sequence.units.last())
            )
        }
    }

    @Test
    fun unsupportedLanguageDoesNotInventPartialSequence() {
        assertNull(a1NarrativeLearningSequenceFor("de"))
    }
}
