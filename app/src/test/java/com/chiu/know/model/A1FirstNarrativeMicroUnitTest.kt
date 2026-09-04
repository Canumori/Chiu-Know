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
}
