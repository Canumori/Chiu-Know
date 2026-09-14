package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1SecondTransferNarrativePreferenceReorderRetrievalActivitiesTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    private val expectedExchanges = mapOf(
        "en" to ("What do you like?" to "I like coffee."),
        "pt" to ("Do que você gosta?" to "Eu gosto de café."),
        "es" to ("¿Qué te gusta?" to "Me gusta el café."),
        "fr" to ("Qu’est-ce que tu aimes ?" to "J’aime le café."),
        "ko" to ("무엇을 좋아해요?" to "커피를 좋아해요.")
    )

    @Test
    fun providesOneClosedA1PreferenceReorderRetrievalPerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val activities = a1SecondTransferNarrativePreferenceReorderRetrievalActivitiesFor(languageCode)
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
    fun reorderTokensReconstructTheAcceptedPreferenceAnswerExactly() {
        supportedLanguages.forEach { languageCode ->
            val activity = a1SecondTransferNarrativePreferenceReorderRetrievalActivitiesFor(languageCode).single()
            val acceptedTokens = activity.acceptedAnswers.single().split(" ")

            assertEquals(
                acceptedTokens.sorted(),
                activity.responseOptions.sorted()
            )
        }
    }

    @Test
    fun reorderRetrievalIsGroundedInImmediateBartoChiuPreferenceExchange() {
        supportedLanguages.forEach { languageCode ->
            val (question, answer) = expectedExchanges.getValue(languageCode)
            val narrative = a1SecondTransferNarrativeMicroUnitFor(languageCode)!!
            val activity = a1SecondTransferNarrativePreferenceReorderRetrievalActivitiesFor(languageCode).single()

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
    fun preferenceReorderRetrievalRemainsOutsideStarterReviewQueue() {
        supportedLanguages.forEach { languageCode ->
            val starterKeys = starterLearningActivitiesFor(languageCode).map { it.reviewKey }.toSet()
            val transferKeys = a1SecondTransferNarrativePreferenceReorderRetrievalActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()
            assertTrue(transferKeys.intersect(starterKeys).isEmpty())
        }
    }

    @Test
    fun koreanPreferenceReorderMatchesReviewedSquareDialogue() {
        val activity = a1SecondTransferNarrativePreferenceReorderRetrievalActivitiesFor("ko").single()
        assertTrue(activity.prompt.contains("무엇을 좋아해요?"))
        assertEquals(listOf("커피를 좋아해요."), activity.acceptedAnswers)
        assertEquals(listOf("좋아해요.", "커피를"), activity.responseOptions)
        assertTrue(activity.feedback.contains("커피를 좋아해요."))
    }
}
