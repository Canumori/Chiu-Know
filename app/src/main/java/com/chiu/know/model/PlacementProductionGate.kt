package com.chiu.know.model

/**
 * Explicit production-readiness gate for the quality-first placement flow.
 *
 * The session engine itself is language-agnostic, but we must not expose the
 * same quality claim for languages whose question banks cannot yet support the
 * validated minimum/confirmation contract without repetition.
 */
enum class PlacementRuntimeMode {
    QUALITY_SESSION,
    LEGACY_FOUNDATION
}

data class PlacementRuntimeSelection(
    val languageCode: String,
    val mode: PlacementRuntimeMode,
    val questions: List<PlacementQuestion>
)

fun placementRuntimeSelection(languageCode: String): PlacementRuntimeSelection {
    return if (languageCode == "en") {
        PlacementRuntimeSelection(
            languageCode = languageCode,
            mode = PlacementRuntimeMode.QUALITY_SESSION,
            questions = qualityEnglishPlacementQuestions
        )
    } else {
        PlacementRuntimeSelection(
            languageCode = languageCode,
            mode = PlacementRuntimeMode.LEGACY_FOUNDATION,
            questions = starterPlacementQuestionsFor(languageCode)
        )
    }
}

fun isQualityPlacementEnabled(languageCode: String): Boolean =
    placementRuntimeSelection(languageCode).mode == PlacementRuntimeMode.QUALITY_SESSION
