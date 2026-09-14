package com.chiu.know.model

enum class A1StationTarget {
    RESTROOM_LOCATION,
    COMPREHENSION_REPAIR
}

data class A1StationRetrieval(
    val target: A1StationTarget,
    val activity: LearningActivity
) {
    init {
        require(activity.level == CefrLevel.A1) { "Station retrieval must remain A1" }
        require(activity.primarySkill == LearningSkill.VOCABULARY) {
            "Station retrieval must remain controlled vocabulary production"
        }
        require(
            activity.responseType == ResponseType.REORDER ||
                activity.responseType == ResponseType.FILL_IN
        ) {
            "Station retrieval must reconstruct the established phrase or retrieve its missing element"
        }
        require(activity.acceptedAnswers.size == 1) {
            "Station retrieval must define one deterministic answer"
        }
    }
}

data class A1StationLearningUnit(
    val narrative: NarrativeMicroUnit,
    val comprehension: LearningActivity,
    val retrievals: List<A1StationRetrieval>,
    val reducedCueRetrievals: List<A1StationRetrieval>
) {
    val practiceSequence: List<A1StationRetrieval>
        get() = retrievals + reducedCueRetrievals

    init {
        require(narrative.level == CefrLevel.A1) { "Station narrative must remain A1" }
        require(comprehension.level == CefrLevel.A1) { "Station comprehension must remain A1" }
        require(comprehension.primarySkill == LearningSkill.READING) {
            "Station comprehension must remain reading evidence"
        }
        require(comprehension.responseType == ResponseType.MULTIPLE_CHOICE) {
            "Station comprehension must remain closed recognition"
        }
        require(retrievals.map { it.target } == listOf(
            A1StationTarget.RESTROOM_LOCATION,
            A1StationTarget.COMPREHENSION_REPAIR
        )) {
            "Station retrievals must preserve the narrative target order"
        }
        require(retrievals.map { it.activity.reviewKey } == narrative.linkedReviewKeys) {
            "Station retrievals must reuse the narrative's established targets"
        }
        require(reducedCueRetrievals.map { it.target } == retrievals.map { it.target }) {
            "Reduced-cue retrievals must preserve the established target order"
        }
        require(reducedCueRetrievals.all { it.activity.responseType == ResponseType.FILL_IN }) {
            "Reduced-cue retrievals must remove the word bank"
        }
        require(reducedCueRetrievals.map { it.activity.reviewKey } == narrative.linkedReviewKeys) {
            "Reduced-cue retrievals must reuse the narrative's established targets"
        }
    }
}

/**
 * Connects the approved station story to its closed comprehension and the two
 * already validated active-reconstruction activities.
 *
 * This structural unit does not choose when content is shown, create evidence,
 * mutate FSRS state, claim mastery, or add listening/speaking assessment.
 */
fun a1StationLearningUnitFor(languageCode: String): A1StationLearningUnit? {
    val narrative = a1StationNarrativeMicroUnitFor(languageCode) ?: return null
    val comprehension = a1StationNarrativeComprehensionActivitiesFor(languageCode).singleOrNull()
        ?: return null
    val location = a1BasicLocationActivitiesFor(languageCode).singleOrNull()
        ?: return null
    val repair = a1ComprehensionRepairActivitiesFor(languageCode).singleOrNull()
        ?: return null
    val reducedCueLocation =
        a1StationLocationFillInRetrievalActivitiesFor(languageCode).singleOrNull()
            ?: return null
    val reducedCueRepair =
        a1StationRepairFillInRetrievalActivitiesFor(languageCode).singleOrNull()
            ?: return null

    return A1StationLearningUnit(
        narrative = narrative,
        comprehension = comprehension,
        retrievals = listOf(
            A1StationRetrieval(A1StationTarget.RESTROOM_LOCATION, location),
            A1StationRetrieval(A1StationTarget.COMPREHENSION_REPAIR, repair)
        ),
        reducedCueRetrievals = listOf(
            A1StationRetrieval(A1StationTarget.RESTROOM_LOCATION, reducedCueLocation),
            A1StationRetrieval(A1StationTarget.COMPREHENSION_REPAIR, reducedCueRepair)
        )
    )
}
