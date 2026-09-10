package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1SecondTransferNarrativeResidenceFillInRetrievalActivitiesTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    private val expectedExchanges = mapOf(
        "en" to ("Where do you live?" to "I live in Rio."),
        "pt" to ("Onde você mora?" to "Eu moro no Rio."),
        "es" to ("¿Dónde vives?" to "Vivo en Río."),
        "fr" to ("Où est-ce que tu habites ?" to "J’habite à Rio."),
        "ko" to ("어디에 살아요?" to "리우에 살아요.")
    )

    private val expectedMissingElements = mapOf(
        "en" to "live",
        "pt" to "moro",
        "es" to "Vivo",
        "fr" to "habite",
        "ko" to "살아요"
    )

    private val expectedPromptFrames = mapOf(
        "en" to "I ___ in Rio.",
        "pt" to "Eu ___ no Rio.",
        "es" to "___ en Río.",
        "fr" to "J’___ à Rio.",
        "ko" to "리우에 ___."
    )

    @Test
    fun providesOneClosedA1ResidenceFillInRetrievalPerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val activities = a1SecondTransferNarrativeResidenceFillInRetrievalActivitiesFor(languageCode)
            assertEquals(1, activities.size)

            val activity = activities.single()
            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.READING, activity.primarySkill)
            assertEquals(ResponseType.FILL_IN, activity.responseType)
            assertEquals(1, activity.acceptedAnswers.size)
            assertEquals(expectedMissingElements.getValue(languageCode), activity.acceptedAnswers.single())
            assertTrue(activity.responseOptions.isEmpty())
            assertTrue(activity.prompt.contains(expectedPromptFrames.getValue(languageCode)))
        }
    }

    @Test
    fun residenceFillInRetrievalIsGroundedInImmediateBartoChiuExchange() {
        supportedLanguages.forEach { languageCode ->
            val (question, answer) = expectedExchanges.getValue(languageCode)
            val narrative = a1SecondTransferNarrativeMicroUnitFor(languageCode)!!
            val activity = a1SecondTransferNarrativeResidenceFillInRetrievalActivitiesFor(languageCode).single()

            val questionIndex = narrative.beats.indexOfFirst {
                it.speaker == "Barto" && it.text == question
            }
            assertTrue(questionIndex >= 0)
            assertTrue(questionIndex + 1 < narrative.beats.size)
            assertEquals("Chiu", narrative.beats[questionIndex + 1].speaker)
            assertEquals(answer, narrative.beats[questionIndex + 1].text)
            assertTrue(answer.contains(activity.acceptedAnswers.single()))
            assertTrue(activity.prompt.contains(question))
            assertTrue(activity.feedback.contains(answer))
        }
    }

    @Test
    fun residenceFillInRetrievalRemainsOutsideStarterReviewQueue() {
        supportedLanguages.forEach { languageCode ->
            val starterKeys = starterLearningActivitiesFor(languageCode).map { it.reviewKey }.toSet()
            val transferKeys = a1SecondTransferNarrativeResidenceFillInRetrievalActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()
            assertTrue(transferKeys.intersect(starterKeys).isEmpty())
        }
    }

    @Test
    fun koreanResidenceFillInMatchesReviewedSquareDialogue() {
        val activity = a1SecondTransferNarrativeResidenceFillInRetrievalActivitiesFor("ko").single()
        assertTrue(activity.prompt.contains("어디에 살아요?"))
        assertTrue(activity.prompt.contains("리우에 ___."))
        assertEquals(listOf("살아요"), activity.acceptedAnswers)
        assertTrue(activity.feedback.contains("리우에 살아요."))
    }
}
