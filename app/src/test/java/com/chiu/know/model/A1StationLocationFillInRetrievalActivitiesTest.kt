package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1StationLocationFillInRetrievalActivitiesTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    private val expected = mapOf(
        "en" to Triple("restroom", "Where is the ___?", "Where is the restroom?"),
        "pt" to Triple("banheiro", "Onde fica o ___?", "Onde fica o banheiro?"),
        "es" to Triple("baño", "¿Dónde está el ___?", "¿Dónde está el baño?"),
        "fr" to Triple("toilettes", "Où sont les ___ ?", "Où sont les toilettes ?"),
        "ko" to Triple("어디예요", "화장실이 ___?", "화장실이 어디예요?")
    )

    @Test
    fun providesOneReducedCueLocationRetrievalPerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val activity = a1StationLocationFillInRetrievalActivitiesFor(languageCode).single()
            val (answer, frame, fullQuestion) = expected.getValue(languageCode)

            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.VOCABULARY, activity.primarySkill)
            assertEquals(ResponseType.FILL_IN, activity.responseType)
            assertEquals(listOf(answer), activity.acceptedAnswers)
            assertTrue(activity.responseOptions.isEmpty())
            assertTrue(activity.prompt.contains(frame))
            assertTrue(activity.feedback.contains(fullQuestion))
        }
    }

    @Test
    fun fillInUsesTheSameTargetAsTheEstablishedReorderActivity() {
        supportedLanguages.forEach { languageCode ->
            val reorder = a1BasicLocationActivitiesFor(languageCode).single()
            val fillIn = a1StationLocationFillInRetrievalActivitiesFor(languageCode).single()

            assertEquals(reorder.reviewKey, fillIn.reviewKey)
            assertEquals(reorder.knowledgeTarget, fillIn.knowledgeTarget)
            assertEquals(
                LearningCueStage.REDUCED_CUE_RETRIEVAL,
                learningCueStage(fillIn)
            )
        }
    }

    @Test
    fun completeQuestionMatchesMiasFirstStationBeat() {
        supportedLanguages.forEach { languageCode ->
            val narrative = a1StationNarrativeMicroUnitFor(languageCode)!!
            val fillIn = a1StationLocationFillInRetrievalActivitiesFor(languageCode).single()

            assertEquals("Mia", narrative.beats.first().speaker)
            assertEquals(fillIn.knowledgeTarget, narrative.beats.first().text)
        }
    }

    @Test
    fun unsupportedLanguageReceivesNoInventedActivity() {
        assertTrue(a1StationLocationFillInRetrievalActivitiesFor("de").isEmpty())
        assertTrue(a1StationLocationFillInRetrievalActivitiesFor("").isEmpty())
    }
}
