package com.chiu.know.model

/**
 * Selects a placement item at the requested CEFR level without repeating an
 * item already used in the same attempt.
 *
 * The caller owns [usedQuestionIds] so the selection stays deterministic and
 * testable. Returning null means that the requested level has no unused item;
 * the caller must not silently recycle an old question when collecting
 * confirmation evidence.
 */
fun nextUnusedPlacementQuestion(
    questions: List<PlacementQuestion>,
    level: CefrLevel,
    usedQuestionIds: Set<String>
): PlacementQuestion? = placementQuestionsForLevel(questions, level)
    .firstOrNull { it.id !in usedQuestionIds }

/**
 * Production candidate bank by target language.
 *
 * English uses the expanded, CI-validated bank. Other languages deliberately
 * remain on their starter banks until they receive equivalent linguistic
 * expansion and validation.
 */
fun placementCandidateQuestionsFor(languageCode: String): List<PlacementQuestion> =
    if (languageCode == "en") qualityEnglishPlacementQuestions
    else starterPlacementQuestionsFor(languageCode)
