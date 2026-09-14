package com.chiu.know.model

/**
 * Small controlled A1 slice for signaling a comprehension problem.
 *
 * These activities use REORDER to require active reconstruction of a short,
 * useful repair phrase without claiming open conversation, speaking,
 * pronunciation or broader communicative mastery.
 *
 * This file remains isolated from the starter bank until content and tests are
 * green on their own.
 */
private val a1ComprehensionRepairActivities = listOf(
    LearningActivity(
        id = "en-a1-comprehension-repair-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Build a basic phrase to signal that something was not understood",
        knowledgeTarget = "I don't understand.",
        responseType = ResponseType.REORDER,
        prompt = "Barto did not understand an explanation. Put the words in order to say so.",
        feedback = "A direct basic repair phrase is: I don't understand.",
        reviewKey = "en:a1:vocabulary:comprehension-repair",
        acceptedAnswers = listOf("I don't understand."),
        responseOptions = listOf("I", "don't", "understand.")
    ),
    LearningActivity(
        id = "pt-a1-comprehension-repair-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Montar uma frase básica para avisar que algo não foi compreendido",
        knowledgeTarget = "Eu não entendo.",
        responseType = ResponseType.REORDER,
        prompt = "Barto não entendeu uma explicação. Coloque as palavras em ordem para avisar isso.",
        feedback = "Uma frase básica e direta é: Eu não entendo.",
        reviewKey = "pt:a1:vocabulary:comprehension-repair",
        acceptedAnswers = listOf("Eu não entendo."),
        responseOptions = listOf("Eu", "não", "entendo.")
    ),
    LearningActivity(
        id = "es-a1-comprehension-repair-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Construir una frase básica para indicar que algo no se entendió",
        knowledgeTarget = "No entiendo.",
        responseType = ResponseType.REORDER,
        prompt = "Barto no entendió una explicación. Ordena las palabras para indicarlo.",
        feedback = "Una frase básica y directa es: No entiendo.",
        reviewKey = "es:a1:vocabulary:comprehension-repair",
        acceptedAnswers = listOf("No entiendo."),
        responseOptions = listOf("No", "entiendo.")
    ),
    LearningActivity(
        id = "fr-a1-comprehension-repair-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Construire une phrase simple pour signaler qu’on n’a pas compris",
        knowledgeTarget = "Je ne comprends pas.",
        responseType = ResponseType.REORDER,
        prompt = "Barto n’a pas compris une explication. Remettez les mots dans l’ordre pour le signaler.",
        feedback = "Une phrase simple et directe est : Je ne comprends pas.",
        reviewKey = "fr:a1:vocabulary:comprehension-repair",
        acceptedAnswers = listOf("Je ne comprends pas."),
        responseOptions = listOf("Je", "ne", "comprends", "pas.")
    ),
    LearningActivity(
        id = "ko-a1-comprehension-repair-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "이해하지 못했음을 알리는 기본 표현을 만들기",
        knowledgeTarget = "이해가 안 돼요.",
        responseType = ResponseType.REORDER,
        prompt = "바르토가 설명을 이해하지 못했어요. 이해하지 못했다는 표현이 되도록 순서대로 고르세요.",
        feedback = "이해하지 못했을 때 ‘이해가 안 돼요.’라고 말할 수 있어요.",
        reviewKey = "ko:a1:vocabulary:comprehension-repair",
        acceptedAnswers = listOf("이해가 안 돼요."),
        responseOptions = listOf("이해가", "안", "돼요.")
    )
)

fun a1ComprehensionRepairActivitiesFor(languageCode: String): List<LearningActivity> =
    a1ComprehensionRepairActivities.filter { it.id.startsWith("$languageCode-") }
