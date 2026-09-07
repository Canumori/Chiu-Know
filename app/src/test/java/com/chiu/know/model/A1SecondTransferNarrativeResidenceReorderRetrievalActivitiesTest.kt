package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1SecondTransferNarrativeResidenceReorderRetrievalActivitiesTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    private val expectedExchanges = mapOf(
        "en" to ("Where do you live?" to "I live in Rio."),
        "pt" to ("Onde você mora?" to "Eu moro no Rio."),
        "es" to ("¿Dónde vives?" to "Vivo en Río."),
        "fr" to ("Où est-ce que tu habites ?" to "J’habite à Rio."),
        "ko" to ("어디에 살아요?" to "리우에 살아요.")
    )

    @Test
    fun providesOneClosedA1ResidenceReorderRetrievalPerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val activities = a1SecondTransferNarrativeResidenceReorderRetrievalActivitiesFor(languageCode)
            assertEquals(1, activities.size)

            val activity = activities.single()
            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.READING, activity.primarySkill)
            assertEquals(ResponseType.REORDER, activity.responseType)
            assertEquals(1, activity.acceptedAnswers.size)
            assertTrue(activity.responseOptions.size >= 2)
            assertEquals(activity.responseOptions.size, activity.responseOptions.distinct().size)
        }
    }

    @Test
    fun reorderTokensReconstructTheAcceptedResidenceAnswerExactly() {
        supportedLanguages.forEach { languageCode ->
            val activity = a1SecondTransferNarrativeResidenceReorderRetrievalActivitiesFor(languageCode).single()
            val acceptedTokens = activity.acceptedAnswers.single().split(" ")

            assertEquals(
                acceptedTokens.sorted(),
                activity.responseOptions.sorted()
            )
        }
    }

    @Test
    fun residenceReorderRetrievalIsGroundedInImmediateBartoChiuExchange() {
        supportedLanguages.forEach { languageCode ->
            val (question, answer) = expectedExchanges.getValue(languageCode)
            val narrative = a1SecondTransferNarrativeMicroUnitFor(languageCode)!!
            val activity = a1SecondTransferNarrativeResidenceReorderRetrievalActivitiesFor(languageCode).single()

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
    fun residenceReorderRetrievalRemainsOutsideStarterReviewQueue() {
        supportedLanguages.forEach { languageCode ->
            val starterKeys = starterLearningActivitiesFor(languageCode).map { it.reviewKey }.toSet()
            val transferKeys = a1SecondTransferNarrativeResidenceReorderRetrievalActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()
            assertTrue(transferKeys.intersect(starterKeys).isEmpty())
        }
    }

    @Test
    fun koreanResidenceReorderMatchesReviewedSquareDialogue() {
        val activity = a1SecondTransferNarrativeResidenceReorderRetrievalActivitiesFor("ko").single()
        assertTrue(activity.prompt.contains("어디에 살아요?"))
        assertEquals(listOf("리우에 살아요."), activity.acceptedAnswers)
        assertEquals(listOf("살아요.", "리우에"), activity.responseOptions)
        assertTrue(activity.feedback.contains("리우에 살아요."))
    }
}
