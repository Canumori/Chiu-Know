package com.chiu.know.model

/**
 * Structural quality checks for placement banks.
 *
 * These checks protect data integrity only. Passing them does not validate CEFR
 * calibration, item difficulty or psychometric reliability.
 */
data class PlacementBankQualityReport(
    val totalQuestions: Int,
    val questionsPerLevel: Map<CefrLevel, Int>,
    val duplicateIds: Set<String>,
    val duplicatePrompts: Set<String>,
    val malformedQuestionIds: Set<String>
) {
    val isStructurallyValid: Boolean
        get() = totalQuestions > 0 &&
            CefrLevel.entries.all { (questionsPerLevel[it] ?: 0) > 0 } &&
            duplicateIds.isEmpty() &&
            duplicatePrompts.isEmpty() &&
            malformedQuestionIds.isEmpty()
}

fun analyzePlacementBank(questions: List<PlacementQuestion>): PlacementBankQualityReport {
    val ids = questions.map { it.id }
    val normalizedPrompts = questions.map { it.prompt.trim().lowercase() }

    val malformed = questions.filter { question ->
        question.id.isBlank() ||
            question.prompt.isBlank() ||
            question.options.size < 2 ||
            question.options.any { it.isBlank() } ||
            question.options.distinct().size != question.options.size ||
            question.correctIndex !in question.options.indices
    }.map { it.id.ifBlank { "<blank-id>" } }.toSet()

    return PlacementBankQualityReport(
        totalQuestions = questions.size,
        questionsPerLevel = CefrLevel.entries.associateWith { level -> questions.count { it.level == level } },
        duplicateIds = ids.groupingBy { it }.eachCount().filterValues { it > 1 }.keys,
        duplicatePrompts = normalizedPrompts.groupingBy { it }.eachCount().filterValues { it > 1 }.keys,
        malformedQuestionIds = malformed
    )
}
