package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1SecondTransferNarrativePreferenceCuedRetrievalActivitiesTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    private val expectedExchanges = mapOf(
        "en" to ("What do you like?" to "I like coffee."),
        "pt" to ("Do que você gosta?" to "Eu gosto de café."),
        "es" to ("¿Qué te gusta?" to "Me gusta el café."),
        "fr" to ("Qu’est-ce que tu aimes ?" to "J’aime le café."),
        "ko" to ("무엇을 좋아해요?" to "커피를 좋아해요.")
    )

    @Test
    fun providesOneClosedA1PreferenceCuedRetrievalPerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val activities = a1SecondTransferNarrativePreferenceCuedRetrievalActivitiesFor(languageCode)
            assertEquals(1, activities.size)

            val activity = activities.single()
            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.READING, activity.primarySkill)
            assertEquals(ResponseType.MULTIPLE_CHOICE, activity.responseType)
            assertEquals(1, activity.acceptedAnswers.size)
            assertEquals(2, activity.responseOptions.size)
            assertEquals(2, activity.responseOptions.distinct().size)
            assertTrue(activity.acceptedAnswers.single() in activity.responseOptions)
        }
    }

    @Test
    fun cuedRetrievalIsGroundedInImmediateBartoChiuPreferenceExchange() {
        supportedLanguages.forEach { languageCode ->
            val (question, answer) = expectedExchanges.getValue(languageCode)
            val narrative = a1SecondTransferNarrativeMicroUnitFor(languageCode)!!
            val activity = a1SecondTransferNarrativePreferenceCuedRetrievalActivitiesFor(languageCode).single()

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
    fun preferenceCuedRetrievalRemainsOutsideStarterReviewQueue() {
        supportedLanguages.forEach { languageCode ->
            val starterKeys = starterLearningActivitiesFor(languageCode).map { it.reviewKey }.toSet()
            val transferKeys = a1SecondTransferNarrativePreferenceCuedRetrievalActivitiesFor(languageCode)
                .map { it.reviewKey }
                .toSet()
            assertTrue(transferKeys.intersect(starterKeys).isEmpty())
        }
    }

    @Test
    fun koreanPreferenceCuedRetrievalMatchesReviewedSquareDialogue() {
        val activity = a1SecondTransferNarrativePreferenceCuedRetrievalActivitiesFor("ko").single()
        assertTrue(activity.prompt.contains("무엇을 좋아해요?"))
        assertEquals(listOf("커피를 좋아해요."), activity.acceptedAnswers)
        assertEquals(listOf("커피를 좋아해요.", "리우에 살아요."), activity.responseOptions)
        assertTrue(activity.feedback.contains("커피를 좋아해요."))
    }
}
