package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class A1FirstNarrativeMicroUnitTest {

    @Test
    fun supportedLanguagesExposeNarrativeLinkedOnlyToExistingStarterTargets() {
        listOf("en", "pt", "es", "fr").forEach { languageCode ->
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
    fun koreanNarrativeRemainsUnavailableUnderCurrentReviewPolicy() {
        assertNull(a1FirstNarrativeMicroUnitFor("ko"))
    }

    @Test
    fun starterNarrativeSelectorExposesOnlyExistingA1Narratives() {
        listOf("en", "pt", "es", "fr").forEach { languageCode ->
            val narrative = starterNarrativeMicroUnitFor(languageCode, CefrLevel.A1)
            assertNotNull(narrative)
            assertEquals(languageCode, narrative!!.languageCode)
            assertEquals(CefrLevel.A1, narrative.level)
        }

        assertNull(starterNarrativeMicroUnitFor("ko", CefrLevel.A1))
        assertNull(starterNarrativeMicroUnitFor("en", CefrLevel.A2))
        assertNull(starterNarrativeMicroUnitFor("en", CefrLevel.B1))
        assertNull(starterNarrativeMicroUnitFor("en", CefrLevel.B2))
        assertNull(starterNarrativeMicroUnitFor("en", CefrLevel.C1))
        assertNull(starterNarrativeMicroUnitFor("en", CefrLevel.C2))
    }
}
