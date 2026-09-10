package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class A1SecondTransferLearningUnitTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun connectsNarrativeComprehensionAndTwoClosedRetrievalTracksPerLanguage() {
        supportedLanguages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))

            assertEquals(languageCode, unit.narrative.languageCode)
            assertEquals(CefrLevel.A1, unit.narrative.level)
            assertEquals(CefrLevel.A1, unit.comprehension.level)
            assertEquals(LearningSkill.READING, unit.comprehension.primarySkill)
            assertEquals(ResponseType.MULTIPLE_CHOICE, unit.comprehension.responseType)
            assertEquals(
                listOf(A1SecondTransferTarget.RESIDENCE, A1SecondTransferTarget.PREFERENCE),
                unit.retrievalTracks.map { it.target }
            )

            unit.retrievalTracks.forEach { track ->
                assertEquals(3, track.activities.size)
                assertEquals(
                    listOf(ResponseType.MULTIPLE_CHOICE, ResponseType.REORDER, ResponseType.FILL_IN),
                    track.activities.map { it.responseType }
                )
                assertTrue(track.activities.all { it.level == CefrLevel.A1 })
                assertTrue(track.activities.all { it.primarySkill == LearningSkill.READING })
                assertTrue(track.activities.none { it.responseType == ResponseType.FREE_TEXT })
                assertEquals(1, track.activities.map { it.reviewKey }.distinct().size)
            }

            assertEquals(2, unit.retrievalTracks.map { it.reviewKey }.distinct().size)
        }
    }

    @Test
    fun retrievalTracksStayGroundedInImmediateBartoToChiuNarrativeAnswers() {
        supportedLanguages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val beats = unit.narrative.beats
            assertEquals(6, beats.size)

            val residenceQuestion = beats[2]
            val residenceAnswer = beats[3]
            val preferenceQuestion = beats[4]
            val preferenceAnswer = beats[5]

            assertEquals("Barto", residenceQuestion.speaker)
            assertEquals("Chiu", residenceAnswer.speaker)
            assertEquals("Barto", preferenceQuestion.speaker)
            assertEquals("Chiu", preferenceAnswer.speaker)

            val residenceTrack = unit.retrievalTracks.first { it.target == A1SecondTransferTarget.RESIDENCE }
            val preferenceTrack = unit.retrievalTracks.first { it.target == A1SecondTransferTarget.PREFERENCE }

            assertEquals(residenceAnswer.text, residenceTrack.activities[0].acceptedAnswers.single())
            assertEquals(residenceAnswer.text, residenceTrack.activities[1].acceptedAnswers.single())
            assertTrue(residenceTrack.activities[2].feedback.contains(residenceAnswer.text))

            assertEquals(preferenceAnswer.text, preferenceTrack.activities[0].acceptedAnswers.single())
            assertEquals(preferenceAnswer.text, preferenceTrack.activities[1].acceptedAnswers.single())
            assertTrue(preferenceTrack.activities[2].feedback.contains(preferenceAnswer.text))
            assertTrue(unit.comprehension.feedback.contains(preferenceAnswer.text))
        }
    }

    @Test
    fun narrativeReusesIntroducedStarterTargetsWhileSquareEvidenceStaysSeparate() {
        supportedLanguages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val starterKeys = starterLearningActivitiesFor(languageCode).map { it.reviewKey }.toSet()

            assertTrue(unit.narrative.linkedReviewKeys.all { it in starterKeys })
            assertTrue(unit.comprehension.reviewKey !in starterKeys)
            assertTrue(unit.retrievalTracks.all { it.reviewKey !in starterKeys })
        }
    }

    @Test
    fun koreanUnitKeepsReviewedResidenceAndPreferenceForms() {
        val unit = requireNotNull(a1SecondTransferLearningUnitFor("ko"))
        val beats = unit.narrative.beats

        assertEquals("어디에 살아요?", beats[2].text)
        assertEquals("리우에 살아요.", beats[3].text)
        assertEquals("무엇을 좋아해요?", beats[4].text)
        assertEquals("커피를 좋아해요.", beats[5].text)

        val residenceTrack = unit.retrievalTracks.first { it.target == A1SecondTransferTarget.RESIDENCE }
        val preferenceTrack = unit.retrievalTracks.first { it.target == A1SecondTransferTarget.PREFERENCE }
        assertEquals("리우에 살아요.", residenceTrack.activities[0].acceptedAnswers.single())
        assertEquals("커피를 좋아해요.", preferenceTrack.activities[0].acceptedAnswers.single())
    }

    @Test
    fun unsupportedLanguageDoesNotInventConnectedUnit() {
        assertNull(a1SecondTransferLearningUnitFor("de"))
    }
}
