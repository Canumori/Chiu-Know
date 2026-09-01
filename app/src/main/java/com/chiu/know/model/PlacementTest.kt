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
    PlacementQuestion("en-a1-001", CefrLevel.A1, "Choose the correct sentence.", listOf("She are happy.", "She is happy.", "She be happy.", "She am happy."), 1),
    PlacementQuestion("en-a2-001", CefrLevel.A2, "I ___ here since 2024.", listOf("live", "lived", "have lived", "am living yesterday"), 2),
    PlacementQuestion("en-b1-001", CefrLevel.B1, "If it rains tomorrow, we ___ at home.", listOf("stay", "stayed", "will stay", "would have stayed"), 2),
    PlacementQuestion("en-b2-001", CefrLevel.B2, "By the time we arrived, the film ___.", listOf("already started", "had already started", "has already started", "would already start"), 1),
    PlacementQuestion("en-c1-001", CefrLevel.C1, "The evidence is consistent with the hypothesis, but it does not necessarily ___ it.", listOf("prove", "proving", "proved", "to prove"), 0),
    PlacementQuestion("en-c2-001", CefrLevel.C2, "Choose the most natural option: His apology did little to ___ the concerns raised by the report.", listOf("allay", "evade", "repeal", "dissolve"), 0)
)

val starterPortuguesePlacementQuestions = listOf(
    PlacementQuestion("pt-a1-001", CefrLevel.A1, "Eu ___ estudante.", listOf("sou", "é", "somos", "ser"), 0),
    PlacementQuestion("pt-a2-001", CefrLevel.A2, "Ontem nós ___ ao cinema.", listOf("vamos", "fomos", "iremos", "iríamos"), 1),
    PlacementQuestion("pt-b1-001", CefrLevel.B1, "Se eu tiver tempo, ___ com você amanhã.", listOf("falei", "falarei", "falava", "teria falado"), 1),
    PlacementQuestion("pt-b2-001", CefrLevel.B2, "Quando cheguei, eles já ___.", listOf("saem", "saíram", "tinham saído", "sairão"), 2),
    PlacementQuestion("pt-c1-001", CefrLevel.C1, "Embora ___ cansado, continuou trabalhando.", listOf("estava", "esteja", "estivesse", "estará"), 2),
    PlacementQuestion("pt-c2-001", CefrLevel.C2, "A medida foi adotada para ___ os efeitos da crise.", listOf("mitigar", "revogar", "dissipar-se", "prescindir"), 0)
)

val starterSpanishPlacementQuestions = listOf(
    PlacementQuestion("es-a1-001", CefrLevel.A1, "Yo ___ estudiante.", listOf("soy", "eres", "somos", "ser"), 0),
    PlacementQuestion("es-a2-001", CefrLevel.A2, "Ayer nosotros ___ al cine.", listOf("vamos", "fuimos", "iremos", "iríamos"), 1),
    PlacementQuestion("es-b1-001", CefrLevel.B1, "Si tengo tiempo, ___ contigo mañana.", listOf("hablé", "hablaré", "hablaba", "habría hablado"), 1),
    PlacementQuestion("es-b2-001", CefrLevel.B2, "Cuando llegué, ellos ya ___.", listOf("salen", "salieron", "habían salido", "saldrán"), 2),
    PlacementQuestion("es-c1-001", CefrLevel.C1, "Aunque estaba cansado, ___ trabajando.", listOf("siguió", "seguirá", "seguiría", "haya seguido"), 0),
    PlacementQuestion("es-c2-001", CefrLevel.C2, "La medida se adoptó para ___ los efectos de la crisis.", listOf("mitigar", "derogar", "eludir", "prescindir"), 0)
)

val starterFrenchPlacementQuestions = listOf(
    PlacementQuestion("fr-a1-001", CefrLevel.A1, "Je ___ étudiant.", listOf("suis", "es", "sommes", "être"), 0),
    PlacementQuestion("fr-a2-001", CefrLevel.A2, "Hier, nous ___ au cinéma.", listOf("allons", "sommes allés", "irons", "irions"), 1),
    PlacementQuestion("fr-b1-001", CefrLevel.B1, "Si j'ai le temps, je ___ avec toi demain.", listOf("parlais", "parlerai", "parlerais", "avais parlé"), 1),
    PlacementQuestion("fr-b2-001", CefrLevel.B2, "Quand je suis arrivé, ils ___.", listOf("partent", "sont partis", "étaient déjà partis", "partiront"), 2),
    PlacementQuestion("fr-c1-001", CefrLevel.C1, "Bien qu'il ___ fatigué, il a continué à travailler.", listOf("est", "soit", "sera", "était"), 1),
    PlacementQuestion("fr-c2-001", CefrLevel.C2, "La mesure a été adoptée pour ___ les effets de la crise.", listOf("atténuer", "abroger", "éluder", "omettre"), 0)
)

val starterKoreanPlacementQuestions = listOf(
    PlacementQuestion("ko-a1-001", CefrLevel.A1, "저는 학생___.", listOf("입니다", "있습니다", "합니다", "갑니다"), 0),
    PlacementQuestion("ko-a2-001", CefrLevel.A2, "어제 친구를 ___.", listOf("만나요", "만났어요", "만날 거예요", "만나고 있어요"), 1),
    PlacementQuestion("ko-b1-001", CefrLevel.B1, "시간이 있으면 같이 영화를 ___.", listOf("봤어요", "봐요", "봤었어요", "보지 않았어요"), 1),
    PlacementQuestion("ko-b2-001", CefrLevel.B2, "집에 도착했을 때 동생은 이미 ___.", listOf("자요", "잠들어 있었어요", "잘 거예요", "자고 싶어요"), 1),
    PlacementQuestion("ko-c1-001", CefrLevel.C1, "비가 많이 ___ 경기는 계속되었다.", listOf("왔지만", "오면", "와서", "오려고"), 0),
    PlacementQuestion("ko-c2-001", CefrLevel.C2, "그의 설명은 보고서가 제기한 의혹을 완전히 ___ 못했다.", listOf("해소하지", "폐지하지", "회피하지", "분산하지"), 0)
)

fun starterPlacementQuestionsFor(languageCode: String): List<PlacementQuestion> = when (languageCode) {
    "pt" -> starterPortuguesePlacementQuestions
    "es" -> starterSpanishPlacementQuestions
    "fr" -> starterFrenchPlacementQuestions
    "ko" -> starterKoreanPlacementQuestions
    else -> starterEnglishPlacementQuestions
}

fun placementQuestionsForLevel(
    questions: List<PlacementQuestion>,
    level: CefrLevel
): List<PlacementQuestion> = questions.filter { it.level == level }

fun placementQuestionForLevel(
    questions: List<PlacementQuestion>,
    level: CefrLevel,
    attemptIndex: Int = 0
): PlacementQuestion {
    val candidates = placementQuestionsForLevel(questions, level)
    require(candidates.isNotEmpty()) { "No placement questions available for level $level" }
    return candidates[Math.floorMod(attemptIndex, candidates.size)]
}

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
