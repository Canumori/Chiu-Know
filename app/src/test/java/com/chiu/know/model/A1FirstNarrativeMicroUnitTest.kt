package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class A1FirstNarrativeMicroUnitTest {

    @Test
    fun supportedLanguagesExposeNarrativeLinkedOnlyToExistingStarterTargets() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val narrative = a1FirstNarrativeMicroUnitFor(languageCode)
            assertNotNull(narrative)
            narrative!!

            assertEquals(CefrLevel.A1, narrative.level)
            assertEquals(languageCode, narrative.languageCode)
            assertTrue(narrative.beats.size >= 2)

            val starterReviewKeys = starterLearningActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()

            assertTrue(narrative.linkedReviewKeys.all { it in starterReviewKeys })
        }
    }

    @Test
    fun koreanNarrativeUsesReviewedA1Forms() {
        val narrative = a1FirstNarrativeMicroUnitFor("ko")
        assertNotNull(narrative)
        narrative!!

        assertEquals("카페에서 처음 만나요", narrative.title)
        assertEquals(
            listOf(
                "안녕하세요! 저는 미아예요. 이름이 뭐예요?",
                "제 이름은 치우예요.",
                "어디에 살아요?",
                "리우에 살아요.",
                "무엇을 좋아해요?",
                "커피를 좋아해요."
            ),
            narrative.beats.map { it.text }
        )
    }

    @Test
    fun starterNarrativeSelectorExposesOnlyExistingA1Narratives() {
        listOf("en", "pt", "es", "fr", "ko").forEach { languageCode ->
            val narrative = starterNarrativeMicroUnitFor(languageCode, CefrLevel.A1)
            assertNotNull(narrative)
            assertEquals(languageCode, narrative!!.languageCode)
            assertEquals(CefrLevel.A1, narrative.level)
        }

        assertNull(starterNarrativeMicroUnitFor("en", CefrLevel.A2))
        assertNull(starterNarrativeMicroUnitFor("en", CefrLevel.B1))
        assertNull(starterNarrativeMicroUnitFor("en", CefrLevel.B2))
        assertNull(starterNarrativeMicroUnitFor("en", CefrLevel.C1))
        assertNull(starterNarrativeMicroUnitFor("en", CefrLevel.C2))
        assertNull(starterNarrativeMicroUnitFor("ko", CefrLevel.A2))
        assertNull(starterNarrativeMicroUnitFor("ko", CefrLevel.B1))
        assertNull(starterNarrativeMicroUnitFor("ko", CefrLevel.B2))
        assertNull(starterNarrativeMicroUnitFor("ko", CefrLevel.C1))
        assertNull(starterNarrativeMicroUnitFor("ko", CefrLevel.C2))
    }
}
