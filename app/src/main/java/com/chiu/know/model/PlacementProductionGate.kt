package com.chiu.know.model

/**
 * Explicit production-readiness gate for the quality-first placement flow.
 *
 * The session engine itself is language-agnostic, but each language is enabled
 * only after its bank satisfies the project's documented review and integrity
 * requirements.
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

fun placementRuntimeSelection(languageCode: String): PlacementRuntimeSelection = when (languageCode) {
    "en" -> PlacementRuntimeSelection(
        languageCode = languageCode,
        mode = PlacementRuntimeMode.QUALITY_SESSION,
        questions = qualityEnglishPlacementQuestions
    )
    "pt" -> PlacementRuntimeSelection(
        languageCode = languageCode,
        mode = PlacementRuntimeMode.QUALITY_SESSION,
        questions = qualityPortuguesePlacementQuestions
    )
    "es" -> PlacementRuntimeSelection(
        languageCode = languageCode,
        mode = PlacementRuntimeMode.QUALITY_SESSION,
        questions = qualitySpanishPlacementQuestions
    )
    "fr" -> PlacementRuntimeSelection(
        languageCode = languageCode,
        mode = PlacementRuntimeMode.QUALITY_SESSION,
        questions = qualityFrenchPlacementQuestions
    )
    "ko" -> PlacementRuntimeSelection(
        languageCode = languageCode,
        mode = PlacementRuntimeMode.QUALITY_SESSION,
        questions = candidateKoreanPlacementQuestions
    )
    else -> PlacementRuntimeSelection(
        languageCode = languageCode,
        mode = PlacementRuntimeMode.LEGACY_FOUNDATION,
        questions = starterPlacementQuestionsFor(languageCode)
    )
}

fun isQualityPlacementEnabled(languageCode: String): Boolean =
    placementRuntimeSelection(languageCode).mode == PlacementRuntimeMode.QUALITY_SESSION
