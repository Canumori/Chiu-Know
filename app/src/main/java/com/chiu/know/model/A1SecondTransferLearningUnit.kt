package com.chiu.know.model

enum class A1SecondTransferTarget {
    RESIDENCE,
    PREFERENCE
}

data class A1SecondTransferRetrievalTrack(
    val target: A1SecondTransferTarget,
    val activities: List<LearningActivity>
) {
    init {
        require(activities.size == 3) { "Second-transfer retrieval track must contain exactly three activities" }
        require(activities.all { it.level == CefrLevel.A1 }) { "Second-transfer retrieval track must remain A1" }
        require(activities.all { it.primarySkill == LearningSkill.READING }) {
            "Second-transfer retrieval track must remain structured reading evidence"
        }
        require(activities.map { it.reviewKey }.distinct().size == 1) {
            "Second-transfer retrieval variants for one target must share one review key"
        }
        require(
            activities.mapNotNull(::learningCueStage) == LearningCueStage.entries
        ) {
            "Second-transfer retrieval track must withdraw cues in the validated order"
        }
    }

    val reviewKey: String
        get() = activities.first().reviewKey
}

data class A1SecondTransferLearningUnit(
    val narrative: NarrativeMicroUnit,
    val comprehension: LearningActivity,
    val retrievalTracks: List<A1SecondTransferRetrievalTrack>
) {
    init {
        require(narrative.level == CefrLevel.A1) { "Second-transfer narrative must remain A1" }
        require(comprehension.level == CefrLevel.A1) { "Second-transfer comprehension must remain A1" }
        require(comprehension.primarySkill == LearningSkill.READING) {
            "Second-transfer comprehension must remain reading evidence"
        }
        require(comprehension.responseType == ResponseType.MULTIPLE_CHOICE) {
            "Second-transfer comprehension must remain closed recognition"
        }
        require(retrievalTracks.map { it.target } == listOf(
            A1SecondTransferTarget.RESIDENCE,
            A1SecondTransferTarget.PREFERENCE
        )) {
            "Second-transfer retrieval tracks must preserve the narrative target order"
        }
        require(retrievalTracks.map { it.reviewKey }.distinct().size == retrievalTracks.size) {
            "Residence and preference transfer evidence must remain separate targets"
        }
    }
}

/**
 * Connects the already validated second A1 transfer narrative with its closed
 * comprehension and retrieval tracks.
 *
 * This is a structural curriculum unit only. It does not choose when the unit is
 * shown, create LearningEvidence, mutate FSRS state, claim mastery, or turn any
 * activity into free writing, speaking, or pronunciation assessment.
 *
 * Each retrieval track preserves the validated cue-withdrawal progression:
 * MULTIPLE_CHOICE (full-response cue) -> REORDER -> FILL_IN.
 */
fun a1SecondTransferLearningUnitFor(languageCode: String): A1SecondTransferLearningUnit? {
    val narrative = a1SecondTransferNarrativeMicroUnitFor(languageCode) ?: return null
    val comprehension = a1SecondTransferNarrativeComprehensionActivitiesFor(languageCode).singleOrNull()
        ?: return null

    val residenceTrack = A1SecondTransferRetrievalTrack(
        target = A1SecondTransferTarget.RESIDENCE,
        activities = listOfNotNull(
            a1SecondTransferNarrativeResidenceCuedRetrievalActivitiesFor(languageCode).singleOrNull(),
            a1SecondTransferNarrativeResidenceReorderRetrievalActivitiesFor(languageCode).singleOrNull(),
            a1SecondTransferNarrativeResidenceFillInRetrievalActivitiesFor(languageCode).singleOrNull()
        )
    )
    val preferenceTrack = A1SecondTransferRetrievalTrack(
        target = A1SecondTransferTarget.PREFERENCE,
        activities = listOfNotNull(
            a1SecondTransferNarrativePreferenceCuedRetrievalActivitiesFor(languageCode).singleOrNull(),
            a1SecondTransferNarrativePreferenceReorderRetrievalActivitiesFor(languageCode).singleOrNull(),
            a1SecondTransferNarrativePreferenceFillInRetrievalActivitiesFor(languageCode).singleOrNull()
        )
    )

    return A1SecondTransferLearningUnit(
        narrative = narrative,
        comprehension = comprehension,
        retrievalTracks = listOf(residenceTrack, preferenceTrack)
    )
}
