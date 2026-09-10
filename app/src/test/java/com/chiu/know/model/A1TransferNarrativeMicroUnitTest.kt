package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class A1TransferNarrativeMicroUnitTest {

    @Test
    fun providesOneTransferNarrativePerSupportedLanguage() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val narrative = a1TransferNarrativeMicroUnitFor(languageCode)

            assertNotNull(narrative)
            narrative!!
            assertEquals(CefrLevel.A1, narrative.level)
            assertEquals(languageCode, narrative.languageCode)
            assertEquals(6, narrative.beats.size)
            assertTrue(narrative.linkedReviewKeys.isNotEmpty())
        }
    }

    @Test
    fun transferNarrativeUsesASeparateContextAndIdentity() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val first = a1FirstNarrativeMicroUnitFor(languageCode)
            val transfer = a1TransferNarrativeMicroUnitFor(languageCode)

            assertNotNull(first)
            assertNotNull(transfer)
            first!!
            transfer!!
            assertTrue(first.id != transfer.id)
            assertTrue(first.title != transfer.title)
            assertTrue(first.setting != transfer.setting)
        }
    }

    @Test
    fun transferNarrativeRecombinesKnownReviewTargets() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val first = a1FirstNarrativeMicroUnitFor(languageCode)!!
            val transfer = a1TransferNarrativeMicroUnitFor(languageCode)!!

            assertTrue(transfer.linkedReviewKeys.all { it in first.linkedReviewKeys })
        }
    }

    @Test
    fun koreanTransferNarrativeKeepsReviewedA1FormsNaturalAndClosed() {
        val narrative = a1TransferNarrativeMicroUnitFor("ko")!!
        val text = narrative.beats.joinToString(" ") { it.text }

        assertTrue("안녕하세요" in text)
        assertTrue("어디에 살아요?" in text)
        assertTrue("리우에 살아요." in text)
        assertTrue("무엇을 좋아해요?" in text)
        assertTrue("책을 좋아해요." in text)
    }
}
