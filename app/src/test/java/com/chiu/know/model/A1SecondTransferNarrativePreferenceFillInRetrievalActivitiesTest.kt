package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1SecondTransferNarrativePreferenceFillInRetrievalActivitiesTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    private val expectedExchanges = mapOf(
        "en" to ("What do you like?" to "I like coffee."),
        "pt" to ("Do que você gosta?" to "Eu gosto de café."),
        "es" to ("¿Qué te gusta?" to "Me gusta el café."),
        "fr" to ("Qu’est-ce que tu aimes ?" to "J’aime le café."),
        "ko" to ("무엇을 좋아해요?" to "커피를 좋아해요.")
    )

    private val expectedMissingElements = mapOf(
        "en" to "coffee",
        "pt" to "café",
        "es" to "café",
        "fr" to "café",
        "ko" to "커피를"
    )

    private val expectedPromptFrames = mapOf(
        "en" to "I like ___.",
        "pt" to "Eu gosto de ___.",
        "es" to "Me gusta el ___.",
        "fr" to "J’aime le ___.",
        "ko" to "___ 좋아해요."
    )

    @Test
    fun providesOneClosedA1PreferenceFillInRetrievalPerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val activities = a1SecondTransferNarrativePreferenceFillInRetrievalActivitiesFor(languageCode)
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
    fun fillInRetrievalIsGroundedInImmediateBartoChiuPreferenceExchange() {
        supportedLanguages.forEach { languageCode ->
            val (question, answer) = expectedExchanges.getValue(languageCode)
            val narrative = a1SecondTransferNarrativeMicroUnitFor(languageCode)!!
            val activity = a1SecondTransferNarrativePreferenceFillInRetrievalActivitiesFor(languageCode).single()

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
    fun preferenceFillInRetrievalRemainsOutsideStarterReviewQueue() {
        supportedLanguages.forEach { languageCode ->
            val starterKeys = starterLearningActivitiesFor(languageCode).map { it.reviewKey }.toSet()
            val transferKeys = a1SecondTransferNarrativePreferenceFillInRetrievalActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()
            assertTrue(transferKeys.intersect(starterKeys).isEmpty())
        }
    }

    @Test
    fun koreanPreferenceFillInMatchesReviewedSquareDialogue() {
        val activity = a1SecondTransferNarrativePreferenceFillInRetrievalActivitiesFor("ko").single()
        assertTrue(activity.prompt.contains("무엇을 좋아해요?"))
        assertTrue(activity.prompt.contains("___ 좋아해요."))
        assertEquals(listOf("커피를"), activity.acceptedAnswers)
        assertTrue(activity.feedback.contains("커피를 좋아해요."))
    }
}
