package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class TemporaryVoiceSampleTest {
    @Test
    fun offersThreeExplicitlyTemporaryStyles() {
        val samples = temporaryVoiceSamples()

        assertEquals(TemporaryVoiceStyle.entries.toList(), samples.map { it.style })
        assertTrue(samples.all { it.speechRate > 0f && it.pitch > 0f })
    }

    @Test
    fun definesAListeningSampleForEverySupportedTargetLanguage() {
        listOf("pt", "en", "es", "fr", "ko").forEach { languageCode ->
            assertTrue(voiceSamplePhrase(languageCode).isNotBlank())
        }
    }

    @Test
    fun unknownLanguageFallsBackDeterministically() {
        assertEquals(voiceSamplePhrase("en"), voiceSamplePhrase("unknown"))
    }
}
