package com.chiu.know.model

/**
 * Conservative contract describing what kind of evidence an activity can
 * legitimately contribute. These claims are deliberately narrower than the
 * broad skill labels used for curriculum organization.
 *
 * A supported claim means that the activity format can collect that kind of
 * evidence when its content/evaluator is appropriate. It never means mastery,
 * certification or a calibrated CEFR sub-score by itself.
 */
enum class EvidenceClaim {
    RECOGNITION,
    CUED_RETRIEVAL,
    STRUCTURED_RECONSTRUCTION,
    READING_COMPREHENSION,
    LISTENING_COMPREHENSION,
    WRITTEN_PRODUCTION,
    SPOKEN_PRODUCTION,
    PRONUNCIATION,
    INTERACTION
}

fun supportedEvidenceClaims(activity: LearningActivity): Set<EvidenceClaim> {
    val claims = mutableSetOf<EvidenceClaim>()

    when (activity.responseType) {
        ResponseType.MULTIPLE_CHOICE -> claims += EvidenceClaim.RECOGNITION
        ResponseType.FILL_IN -> claims += EvidenceClaim.CUED_RETRIEVAL
        ResponseType.REORDER -> claims += EvidenceClaim.STRUCTURED_RECONSTRUCTION
        ResponseType.FREE_TEXT -> claims += EvidenceClaim.WRITTEN_PRODUCTION
        ResponseType.LISTEN_AND_RESPOND -> claims += EvidenceClaim.LISTENING_COMPREHENSION
        ResponseType.SPEAK -> claims += EvidenceClaim.SPOKEN_PRODUCTION
    }

    if (activity.primarySkill == LearningSkill.READING) {
        claims += EvidenceClaim.READING_COMPREHENSION
    }

    // SPEAK alone is intentionally not enough to claim pronunciation quality,
    // and no current response type proves interaction. Those require dedicated
    // mechanisms and criteria before either claim may be emitted.
    claims -= EvidenceClaim.PRONUNCIATION
    claims -= EvidenceClaim.INTERACTION

    return claims
}
