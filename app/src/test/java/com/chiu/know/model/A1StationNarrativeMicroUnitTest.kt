package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class A1StationNarrativeMicroUnitTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun providesOneStationNarrativePerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val narrative = a1StationNarrativeMicroUnitFor(languageCode)
            assertNotNull(narrative)
            narrative!!

            assertEquals(languageCode, narrative.languageCode)
            assertEquals(CefrLevel.A1, narrative.level)
            assertEquals(4, narrative.beats.size)
            assertEquals(listOf("Mia", "Chiu", "Mia", "Chiu"), narrative.beats.map { it.speaker })
            assertTrue(narrative.id.endsWith("-004"))
        }
    }

    @Test
    fun storyPlacesLocationBeforeComprehensionRepair() {
        val expectedPairs = mapOf(
            "en" to ("Where is the restroom?" to "I don't understand."),
            "pt" to ("Onde fica o banheiro?" to "Eu não entendo."),
            "es" to ("¿Dónde está el baño?" to "No entiendo."),
            "fr" to ("Où sont les toilettes ?" to "Je ne comprends pas."),
            "ko" to ("화장실이 어디예요?" to "이해가 안 돼요.")
        )

        expectedPairs.forEach { (languageCode, phrases) ->
            val narrative = a1StationNarrativeMicroUnitFor(languageCode)!!
            assertEquals(phrases.first, narrative.beats[0].text)
            assertEquals(phrases.second, narrative.beats[2].text)
        }
    }

    @Test
    fun storyLinksOnlyToEstablishedLocationAndRepairTargets() {
        supportedLanguages.forEach { languageCode ->
            val narrative = a1StationNarrativeMicroUnitFor(languageCode)!!
            val establishedKeys = (
                a1BasicLocationActivitiesFor(languageCode) +
                    a1ComprehensionRepairActivitiesFor(languageCode)
                ).map { it.reviewKey }.toSet()

            assertEquals(establishedKeys, narrative.linkedReviewKeys.toSet())
            assertEquals(2, narrative.linkedReviewKeys.size)
        }
    }

    @Test
    fun unsupportedLanguageDoesNotReceiveStationNarrative() {
        assertNull(a1StationNarrativeMicroUnitFor("de"))
        assertNull(a1StationNarrativeMicroUnitFor(""))
    }
}
