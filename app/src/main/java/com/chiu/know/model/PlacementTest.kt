package com.chiu.know.model

enum class CefrLevel { A1, A2, B1, B2, C1, C2 }

data class PlacementQuestion(
    val id: String,
    val level: CefrLevel,
    val prompt: String,
    val options: List<String>,
    val correctIndex: Int
)

val starterEnglishPlacementQuestions = listOf(
    PlacementQuestion(
        id = "en-a1-001",
        level = CefrLevel.A1,
        prompt = "Choose the correct sentence.",
        options = listOf("She are happy.", "She is happy.", "She be happy.", "She am happy."),
        correctIndex = 1
    ),
    PlacementQuestion(
        id = "en-a2-001",
        level = CefrLevel.A2,
        prompt = "I ___ here since 2024.",
        options = listOf("live", "lived", "have lived", "am living yesterday"),
        correctIndex = 2
    ),
    PlacementQuestion(
        id = "en-b1-001",
        level = CefrLevel.B1,
        prompt = "If it rains tomorrow, we ___ at home.",
        options = listOf("stay", "stayed", "will stay", "would have stayed"),
        correctIndex = 2
    ),
    PlacementQuestion(
        id = "en-b2-001",
        level = CefrLevel.B2,
        prompt = "By the time we arrived, the film ___.",
        options = listOf("already started", "had already started", "has already started", "would already start"),
        correctIndex = 1
    ),
    PlacementQuestion(
        id = "en-c1-001",
        level = CefrLevel.C1,
        prompt = "The evidence is consistent with the hypothesis, but it does not necessarily ___ it.",
        options = listOf("prove", "proving", "proved", "to prove"),
        correctIndex = 0
    ),
    PlacementQuestion(
        id = "en-c2-001",
        level = CefrLevel.C2,
        prompt = "Choose the most natural option: His apology did little to ___ the concerns raised by the report.",
        options = listOf("allay", "evade", "repeal", "dissolve"),
        correctIndex = 0
    )
)

fun estimateLevel(correctAnswers: Int, totalQuestions: Int): CefrLevel {
    if (totalQuestions <= 0) return CefrLevel.A1
    val ratio = correctAnswers.toDouble() / totalQuestions
    return when {
        ratio >= 0.90 -> CefrLevel.C2
        ratio >= 0.75 -> CefrLevel.C1
        ratio >= 0.60 -> CefrLevel.B2
        ratio >= 0.45 -> CefrLevel.B1
        ratio >= 0.30 -> CefrLevel.A2
        else -> CefrLevel.A1
    }
}
