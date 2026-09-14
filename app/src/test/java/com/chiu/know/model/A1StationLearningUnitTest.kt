package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class A1StationLearningUnitTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun connectsStoryComprehensionAndTwoEstablishedRetrievalsPerLanguage() {
        supportedLanguages.forEach { languageCode ->
            val unit = requireNotNull(a1StationLearningUnitFor(languageCode))

            assertEquals(languageCode, unit.narrative.languageCode)
            assertEquals(CefrLevel.A1, unit.narrative.level)
            assertEquals(ResponseType.MULTIPLE_CHOICE, unit.comprehension.responseType)
            assertEquals(
                listOf(
                    A1StationTarget.RESTROOM_LOCATION,
                    A1StationTarget.COMPREHENSION_REPAIR
                ),
                unit.retrievals.map { it.target }
            )
            assertTrue(unit.retrievals.all { it.activity.responseType == ResponseType.REORDER })
            assertTrue(unit.retrievals.all { it.activity.primarySkill == LearningSkill.VOCABULARY })
        }
    }

    @Test
    fun retrievalsFollowMiasPhraseOrderInTheStory() {
        supportedLanguages.forEach { languageCode ->
            val unit = requireNotNull(a1StationLearningUnitFor(languageCode))
            val miaBeats = unit.narrative.beats.filter { it.speaker == "Mia" }

            assertEquals(2, miaBeats.size)
            assertEquals(
                miaBeats.map { it.text },
                unit.retrievals.map { it.activity.acceptedAnswers.single() }
            )
            assertEquals(
                unit.narrative.linkedReviewKeys,
                unit.retrievals.map { it.activity.reviewKey }
            )
        }
    }

    @Test
    fun unitReusesExistingStarterTargetsWithoutDuplicatingThem() {
        supportedLanguages.forEach { languageCode ->
            val unit = requireNotNull(a1StationLearningUnitFor(languageCode))
            val establishedActivities =
                a1BasicLocationActivitiesFor(languageCode) +
                    a1ComprehensionRepairActivitiesFor(languageCode)

            assertEquals(
                establishedActivities.map { it.id },
                unit.retrievals.map { it.activity.id }
            )
            assertEquals(
                establishedActivities.map { it.reviewKey },
                unit.retrievals.map { it.activity.reviewKey }
            )
        }
    }

    @Test
    fun koreanUnitKeepsReviewedStationForms() {
        val unit = requireNotNull(a1StationLearningUnitFor("ko"))

        assertEquals(
            listOf("화장실이 어디예요?", "이해가 안 돼요."),
            unit.retrievals.map { it.activity.acceptedAnswers.single() }
        )
    }

    @Test
    fun connectsReducedCueRetrievalsAfterTheEstablishedTargetsPerLanguage() {
        supportedLanguages.forEach { languageCode ->
            val unit = requireNotNull(a1StationLearningUnitFor(languageCode))

            assertEquals(
                unit.retrievals.map { it.target },
                unit.reducedCueRetrievals.map { it.target }
            )
            assertEquals(
                unit.narrative.linkedReviewKeys,
                unit.reducedCueRetrievals.map { it.activity.reviewKey }
            )
            assertTrue(
                unit.reducedCueRetrievals.all {
                    it.activity.responseType == ResponseType.FILL_IN &&
                        it.activity.responseOptions.isEmpty()
                }
            )
            assertTrue(
                unit.reducedCueRetrievals.map { it.activity.id }.toSet()
                    .intersect(unit.retrievals.map { it.activity.id }.toSet())
                    .isEmpty()
            )
        }
    }

    @Test
    fun unsupportedLanguageDoesNotInventAStationUnit() {
        assertNull(a1StationLearningUnitFor("de"))
        assertNull(a1StationLearningUnitFor(""))
    }
}
