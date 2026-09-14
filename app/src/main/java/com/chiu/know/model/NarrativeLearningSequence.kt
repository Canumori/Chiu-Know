package com.chiu.know.model

/**
 * Structural sequence of connected narratives for curriculum planning.
 *
 * Connection means that later contexts reuse targets introduced by the first
 * narrative. It does not prove learning transfer, create evidence, unlock
 * content or change review scheduling.
 */
data class NarrativeLearningSequence(
    val units: List<NarrativeMicroUnit>
) {
    init {
        require(units.size >= 2) { "Narrative sequence must contain at least two contexts" }
        require(units.map { it.languageCode }.distinct().size == 1) {
            "Narrative sequence must remain in one target language"
        }
        require(units.map { it.level }.distinct().size == 1) {
            "Narrative sequence must remain at one CEFR level"
        }
        require(units.map { it.id }.distinct().size == units.size) {
            "Narrative sequence must contain distinct units"
        }
        require(units.map { it.setting }.distinct().size == units.size) {
            "Narrative transfer contexts must use distinct settings"
        }

        val introducedTargets = units.first().linkedReviewKeys.toSet()
        require(units.drop(1).all { later ->
            later.linkedReviewKeys.isNotEmpty() &&
                later.linkedReviewKeys.all { it in introducedTargets }
        }) {
            "Later narratives may reuse only targets introduced by the first context"
        }
    }

    val languageCode: String
        get() = units.first().languageCode

    val level: CefrLevel
        get() = units.first().level
}

/**
 * Returns the currently validated three-context A1 narrative sequence.
 *
 * Missing content fails closed instead of inventing or partially exposing a
 * sequence.
 */
fun a1NarrativeLearningSequenceFor(languageCode: String): NarrativeLearningSequence? {
    val units = listOfNotNull(
        a1FirstNarrativeMicroUnitFor(languageCode),
        a1TransferNarrativeMicroUnitFor(languageCode),
        a1SecondTransferNarrativeMicroUnitFor(languageCode)
    )
    if (units.size != 3) return null

    return NarrativeLearningSequence(units)
}
