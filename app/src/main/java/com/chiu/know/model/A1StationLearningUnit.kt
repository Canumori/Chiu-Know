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
        require(activity.responseType == ResponseType.REORDER) {
            "Station retrieval must reconstruct the established phrase"
        }
        require(activity.acceptedAnswers.size == 1) {
            "Station retrieval must define one deterministic answer"
        }
    }
}

data class A1StationLearningUnit(
    val narrative: NarrativeMicroUnit,
    val comprehension: LearningActivity,
    val retrievals: List<A1StationRetrieval>
) {
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

    return A1StationLearningUnit(
        narrative = narrative,
        comprehension = comprehension,
        retrievals = listOf(
            A1StationRetrieval(A1StationTarget.RESTROOM_LOCATION, location),
            A1StationRetrieval(A1StationTarget.COMPREHENSION_REPAIR, repair)
        )
    )
}
