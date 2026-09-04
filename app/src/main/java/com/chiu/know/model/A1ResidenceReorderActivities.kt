package com.chiu.know.model

/**
 * Transfer format for the A1 residence grammar target.
 *
 * These REORDER activities share the exact reviewKey used by the residence
 * FILL_IN pair. They therefore add a less-cued production format without
 * inventing a separate mastery target.
 */
private val a1ResidenceReorderActivities = listOf(
    LearningActivity(
        id = "en-a1-residence-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Reconstruct a basic first-person residence statement",
        knowledgeTarget = "I live",
        responseType = ResponseType.REORDER,
        prompt = "Put the words in order to say where Mia lives.",
        feedback = "A basic residence statement can be: I live in Rio.",
        reviewKey = "en:a1:grammar:live:first-person",
        acceptedAnswers = listOf("I live in Rio"),
        responseOptions = listOf("Rio", "live", "I", "in")
    ),
    LearningActivity(
        id = "pt-a1-residence-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Reconstruir uma frase básica de residência na primeira pessoa",
        knowledgeTarget = "eu moro",
        responseType = ResponseType.REORDER,
        prompt = "Coloque as palavras em ordem para dizer onde a Mia mora.",
        feedback = "Uma frase básica de residência pode ser: Eu moro no Rio.",
        reviewKey = "pt:a1:grammar:morar:first-person",
        acceptedAnswers = listOf("Eu moro no Rio"),
        responseOptions = listOf("Rio", "Eu", "no", "moro")
    ),
    LearningActivity(
        id = "es-a1-residence-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Reconstruir una frase básica de residencia en primera persona",
        knowledgeTarget = "yo vivo",
        responseType = ResponseType.REORDER,
        prompt = "Ordena las palabras para decir dónde vive Mia.",
        feedback = "Una frase básica de residencia puede ser: Yo vivo en Río.",
        reviewKey = "es:a1:grammar:vivir:first-person",
        acceptedAnswers = listOf("Yo vivo en Río", "Yo vivo en Rio"),
        responseOptions = listOf("Río", "vivo", "Yo", "en")
    ),
    LearningActivity(
        id = "fr-a1-residence-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Reconstruire une phrase simple sur le lieu de vie à la première personne",
        knowledgeTarget = "j’habite",
        responseType = ResponseType.REORDER,
        prompt = "Remettez les éléments dans l’ordre pour dire où habite Mia.",
        feedback = "Une phrase simple peut être : J’habite à Rio.",
        reviewKey = "fr:a1:grammar:habiter:first-person",
        acceptedAnswers = listOf("J’habite à Rio", "J'habite à Rio"),
        responseOptions = listOf("Rio", "J’habite", "à")
    ),
    LearningActivity(
        id = "ko-a1-residence-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "사는 곳을 말하는 기본 문장을 올바른 순서로 재구성하기",
        knowledgeTarget = "저는 살아요",
        responseType = ResponseType.REORDER,
        prompt = "미아가 사는 곳을 말하도록 표현을 올바른 순서로 놓으세요.",
        feedback = "기본적으로 ‘저는 리우에 살아요’라고 말할 수 있어요.",
        reviewKey = "ko:a1:grammar:salda:polite-present",
        acceptedAnswers = listOf("저는 리우에 살아요"),
        responseOptions = listOf("살아요", "저는", "리우에")
    )
)

fun a1ResidenceReorderActivitiesFor(languageCode: String): List<LearningActivity> =
    a1ResidenceReorderActivities.filter { it.id.startsWith("$languageCode-") }
