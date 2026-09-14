package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1StationRepairFillInRetrievalActivitiesTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    private val expected = mapOf(
        "en" to Triple("understand", "I don't ___.", "I don't understand."),
        "pt" to Triple("entendo", "Eu não ___.", "Eu não entendo."),
        "es" to Triple("entiendo", "No ___.", "No entiendo."),
        "fr" to Triple("comprends", "Je ne ___ pas.", "Je ne comprends pas."),
        "ko" to Triple("돼요", "이해가 안 ___.", "이해가 안 돼요.")
    )

    @Test
    fun providesOneReducedCueRepairRetrievalPerSupportedLanguage() {
        supportedLanguages.forEach { languageCode ->
            val activity = a1StationRepairFillInRetrievalActivitiesFor(languageCode).single()
            val (answer, frame, fullPhrase) = expected.getValue(languageCode)

            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.VOCABULARY, activity.primarySkill)
            assertEquals(ResponseType.FILL_IN, activity.responseType)
            assertEquals(listOf(answer), activity.acceptedAnswers)
            assertTrue(activity.responseOptions.isEmpty())
            assertTrue(activity.prompt.contains(frame))
            assertTrue(activity.feedback.contains(fullPhrase))
        }
    }

    @Test
    fun fillInUsesTheSameTargetAsTheEstablishedReorderActivity() {
        supportedLanguages.forEach { languageCode ->
            val reorder = a1ComprehensionRepairActivitiesFor(languageCode).single()
            val fillIn = a1StationRepairFillInRetrievalActivitiesFor(languageCode).single()

            assertEquals(reorder.reviewKey, fillIn.reviewKey)
            assertEquals(reorder.knowledgeTarget, fillIn.knowledgeTarget)
            assertEquals(
                LearningCueStage.REDUCED_CUE_RETRIEVAL,
                learningCueStage(fillIn)
            )
        }
    }

    @Test
    fun completePhraseMatchesMiasRepairBeat() {
        supportedLanguages.forEach { languageCode ->
            val narrative = a1StationNarrativeMicroUnitFor(languageCode)!!
            val fillIn = a1StationRepairFillInRetrievalActivitiesFor(languageCode).single()

            assertEquals("Mia", narrative.beats[2].speaker)
            assertEquals(fillIn.knowledgeTarget, narrative.beats[2].text)
        }
    }

    @Test
    fun unsupportedLanguageReceivesNoInventedActivity() {
        assertTrue(a1StationRepairFillInRetrievalActivitiesFor("de").isEmpty())
        assertTrue(a1StationRepairFillInRetrievalActivitiesFor("").isEmpty())
    }
}
