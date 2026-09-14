package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class A1SecondTransferNarrativeMicroUnitTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun providesOneA1SecondTransferNarrativePerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val narrative = a1SecondTransferNarrativeMicroUnitFor(languageCode)
            assertNotNull(narrative)
            narrative!!

            assertEquals(languageCode, narrative.languageCode)
            assertEquals(CefrLevel.A1, narrative.level)
            assertEquals(6, narrative.beats.size)
            assertTrue(narrative.beats.any { it.speaker == "Barto" })
            assertTrue(narrative.beats.any { it.speaker == "Chiu" })
        }
    }

    @Test
    fun secondTransferUsesANewSettingAndInterlocutor() {
        supportedLanguages.forEach { languageCode ->
            val park = a1TransferNarrativeMicroUnitFor(languageCode)!!
            val square = a1SecondTransferNarrativeMicroUnitFor(languageCode)!!

            assertNotEquals(park.id, square.id)
            assertNotEquals(park.setting, square.setting)
            assertTrue(square.beats.any { it.speaker == "Barto" })
            assertTrue(square.beats.none { it.speaker == "Mia" })
        }
    }

    @Test
    fun secondTransferReusesEstablishedReviewTargets() {
        supportedLanguages.forEach { languageCode ->
            val firstTransfer = a1TransferNarrativeMicroUnitFor(languageCode)!!
            val secondTransfer = a1SecondTransferNarrativeMicroUnitFor(languageCode)!!

            assertEquals(firstTransfer.linkedReviewKeys.toSet(), secondTransfer.linkedReviewKeys.toSet())
        }
    }

    @Test
    fun koreanSecondTransferUsesReviewedA1Forms() {
        val narrative = a1SecondTransferNarrativeMicroUnitFor("ko")!!
        assertEquals("광장에서 만나요", narrative.title)
        assertTrue(narrative.beats.any { it.speaker == "Barto" && it.text == "어디에 살아요?" })
        assertTrue(narrative.beats.any { it.speaker == "Chiu" && it.text == "리우에 살아요." })
        assertTrue(narrative.beats.any { it.speaker == "Barto" && it.text == "무엇을 좋아해요?" })
        assertTrue(narrative.beats.any { it.speaker == "Chiu" && it.text == "커피를 좋아해요." })
    }
}
