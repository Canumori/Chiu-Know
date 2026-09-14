package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1SecondTransferNarrativeResidenceCuedRetrievalActivitiesTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    private val expectedExchanges = mapOf(
        "en" to ("Where do you live?" to "I live in Rio."),
        "pt" to ("Onde você mora?" to "Eu moro no Rio."),
        "es" to ("¿Dónde vives?" to "Vivo en Río."),
        "fr" to ("Où est-ce que tu habites ?" to "J’habite à Rio."),
        "ko" to ("어디에 살아요?" to "리우에 살아요.")
    )

    private val expectedDistractors = mapOf(
        "en" to "I like coffee.",
        "pt" to "Eu gosto de café.",
        "es" to "Me gusta el café.",
        "fr" to "J’aime le café.",
        "ko" to "커피를 좋아해요."
    )

    @Test
    fun providesOneClosedA1ResidenceCuedRetrievalPerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val activities = a1SecondTransferNarrativeResidenceCuedRetrievalActivitiesFor(languageCode)
            assertEquals(1, activities.size)

            val activity = activities.single()
            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.READING, activity.primarySkill)
            assertEquals(ResponseType.MULTIPLE_CHOICE, activity.responseType)
            assertEquals(1, activity.acceptedAnswers.size)
            assertEquals(2, activity.responseOptions.size)
            assertEquals(2, activity.responseOptions.distinct().size)
            assertTrue(activity.acceptedAnswers.single() in activity.responseOptions)
            assertTrue(expectedDistractors.getValue(languageCode) in activity.responseOptions)
        }
    }

    @Test
    fun residenceCuedRetrievalIsGroundedInImmediateBartoChiuExchange() {
        supportedLanguages.forEach { languageCode ->
            val (question, answer) = expectedExchanges.getValue(languageCode)
            val narrative = a1SecondTransferNarrativeMicroUnitFor(languageCode)!!
            val activity = a1SecondTransferNarrativeResidenceCuedRetrievalActivitiesFor(languageCode).single()

            val questionIndex = narrative.beats.indexOfFirst {
                it.speaker == "Barto" && it.text == question
            }
            assertTrue(questionIndex >= 0)
            assertTrue(questionIndex + 1 < narrative.beats.size)
            assertEquals("Chiu", narrative.beats[questionIndex + 1].speaker)
            assertEquals(answer, narrative.beats[questionIndex + 1].text)
            assertEquals(listOf(answer), activity.acceptedAnswers)
            assertTrue(activity.prompt.contains(question))
            assertTrue(activity.feedback.contains(answer))
        }
    }

    @Test
    fun residenceCuedRetrievalRemainsOutsideStarterReviewQueue() {
        supportedLanguages.forEach { languageCode ->
            val starterKeys = starterLearningActivitiesFor(languageCode).map { it.reviewKey }.toSet()
            val transferKeys = a1SecondTransferNarrativeResidenceCuedRetrievalActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()
            assertTrue(transferKeys.intersect(starterKeys).isEmpty())
        }
    }

    @Test
    fun koreanResidenceCuedRetrievalMatchesReviewedSquareDialogue() {
        val activity = a1SecondTransferNarrativeResidenceCuedRetrievalActivitiesFor("ko").single()
        assertTrue(activity.prompt.contains("어디에 살아요?"))
        assertEquals(listOf("리우에 살아요."), activity.acceptedAnswers)
        assertEquals(listOf("리우에 살아요.", "커피를 좋아해요."), activity.responseOptions)
        assertTrue(activity.feedback.contains("리우에 살아요."))
    }
}
