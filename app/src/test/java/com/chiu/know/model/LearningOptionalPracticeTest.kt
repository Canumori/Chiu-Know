package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class LearningOptionalPracticeTest {
    private val supportedLanguages = listOf("en", "pt", "es", "fr", "ko")

    @Test
    fun preservesStarterPracticeUntilSecondTransferInitialSequenceCompletes() {
        supportedLanguages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val evidence = prerequisiteEvidence(languageCode, unit) +
                learningEvidenceFor(
                    activity = unit.comprehension,
                    correct = false,
                    attemptedAtEpochMillis = 100L
                )

            assertEquals(
                A1SecondTransferNewWorkReason.RETRIEVAL_STEP,
                a1SecondTransferNewWorkSelection(languageCode, evidence).reason
            )
            assertEquals(
                starterLearningActivityForEvidence(languageCode, CefrLevel.A1, evidence),
                learningActivityForOptionalPractice(languageCode, CefrLevel.A1, evidence)
            )
        }
    }

    @Test
    fun completedSecondTransferMakesStrongTransferVariantsEligible() {
        supportedLanguages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val evidence = completedSecondTransferEvidenceWithStarterTargetsHeavier(languageCode, unit)

            assertEquals(
                A1SecondTransferNewWorkReason.INITIAL_SEQUENCE_COMPLETE,
                a1SecondTransferNewWorkSelection(languageCode, evidence).reason
            )

            val selected = requireNotNull(
                learningActivityForOptionalPractice(languageCode, CefrLevel.A1, evidence)
            )
            val residenceTrack = unit.retrievalTracks
                .first { it.target == A1SecondTransferTarget.RESIDENCE }
            val strongTransferIds = a1SecondTransferReviewActivitiesFor(languageCode).map { it.id }.toSet()

            assertEquals(residenceTrack.reviewKey, selected.reviewKey)
            assertTrue(selected.id in strongTransferIds)
            assertTrue(
                selected.responseType == ResponseType.REORDER ||
                    selected.responseType == ResponseType.FILL_IN
            )
        }
    }

    @Test
    fun transientSessionEvidenceAdvancesOptionalPracticeWithoutChangingPersistedEvidence() {
        supportedLanguages.forEach { languageCode ->
            val unit = requireNotNull(a1SecondTransferLearningUnitFor(languageCode))
            val persistedEvidence = completedSecondTransferEvidenceWithStarterTargetsHeavier(languageCode, unit)
            val persistedEvidenceSize = persistedEvidence.size
            val first = requireNotNull(
                learningActivityForOptionalPractice(languageCode, CefrLevel.A1, persistedEvidence)
            )
            val sessionEvidence = listOf(
                learningEvidenceFor(
                    activity = first,
                    correct = true,
                    attemptedAtEpochMillis = 1_000L
                )
            )
            val second = requireNotNull(
                learningActivityForOptionalPractice(
                    languageCode,
                    CefrLevel.A1,
                    persistedEvidence + sessionEvidence
                )
            )

            assertNotEquals(first.id, second.id)
            assertEquals(persistedEvidenceSize, persistedEvidence.size)
        }
    }

    @Test
    fun nonA1LevelsRemainExactStarterDelegates() {
        val laterLevels = listOf(
            CefrLevel.A2,
            CefrLevel.B1,
            CefrLevel.B2,
            CefrLevel.C1,
            CefrLevel.C2
        )

        supportedLanguages.forEach { languageCode ->
            laterLevels.forEach { level ->
                val evidence = emptyList<LearningEvidence>()
                assertEquals(
                    starterLearningActivityForEvidence(languageCode, level, evidence),
                    learningActivityForOptionalPractice(languageCode, level, evidence)
                )
            }
        }
    }

    @Test
    fun unsupportedA1LanguagePreservesMissingContent() {
        assertNull(starterLearningActivityForEvidence("de", CefrLevel.A1, emptyList()))
        assertNull(learningActivityForOptionalPractice("de", CefrLevel.A1, emptyList()))
    }

    private fun prerequisiteEvidence(
        languageCode: String,
        unit: A1SecondTransferLearningUnit
    ): List<LearningEvidence> = unit.narrative.linkedReviewKeys.mapIndexed { index, reviewKey ->
        LearningEvidence(
            activityId = "synthetic-$languageCode-optional-practice-prerequisite-$index",
            reviewKey = reviewKey,
            level = CefrLevel.A1,
            primarySkill = LearningSkill.READING,
            correct = false,
            attemptedAtEpochMillis = index.toLong() + 1L
        )
    }

    private fun completedSecondTransferEvidenceWithStarterTargetsHeavier(
        languageCode: String,
        unit: A1SecondTransferLearningUnit
    ): List<LearningEvidence> {
        val evidence = prerequisiteEvidence(languageCode, unit).toMutableList()
        var attemptedAt = 100L

        val secondTransferActivities = listOf(unit.comprehension) +
            unit.retrievalTracks.flatMap { it.activities }
        secondTransferActivities.forEach { activity ->
            evidence += learningEvidenceFor(
                activity = activity,
                correct = false,
                attemptedAtEpochMillis = attemptedAt++
            )
        }

        starterLearningActivitiesFor(languageCode)
            .filter { it.level == CefrLevel.A1 }
            .distinctBy { it.reviewKey }
            .forEach { activity ->
                repeat(5) {
                    evidence += learningEvidenceFor(
                        activity = activity,
                        correct = false,
                        attemptedAtEpochMillis = attemptedAt++
                    )
                }
            }

        return evidence
    }
}
