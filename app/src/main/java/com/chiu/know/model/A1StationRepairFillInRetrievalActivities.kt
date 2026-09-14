package com.chiu.know.model

/**
 * Reduced-cue retrieval for Mia's comprehension-repair phrase in the station.
 *
 * The learner retrieves the missing verbal element without a word bank. This
 * remains deterministic FILL_IN practice and does not assess free writing,
 * listening, speaking, or pronunciation.
 */
private val a1StationRepairFillInRetrievalActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-station-repair-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Retrieve the missing verb in the station comprehension-repair phrase",
        knowledgeTarget = "I don't understand.",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu’s direction is not clear. Complete Mia’s response: I don't ___.",
        feedback = "The complete response is: I don't understand.",
        reviewKey = "en:a1:vocabulary:comprehension-repair",
        acceptedAnswers = listOf("understand")
    ),
    LearningActivity(
        id = "pt-a1-narrative-estacao-reparo-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Recuperar o verbo ausente na frase de reparo de compreensão da estação",
        knowledgeTarget = "Eu não entendo.",
        responseType = ResponseType.FILL_IN,
        prompt = "A indicação de Chiu não ficou clara. Complete a resposta de Mia: Eu não ___.",
        feedback = "A resposta completa é: Eu não entendo.",
        reviewKey = "pt:a1:vocabulary:comprehension-repair",
        acceptedAnswers = listOf("entendo")
    ),
    LearningActivity(
        id = "es-a1-narrative-estacion-reparacion-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Recuperar el verbo que falta en la frase de reparación de comprensión de la estación",
        knowledgeTarget = "No entiendo.",
        responseType = ResponseType.FILL_IN,
        prompt = "La indicación de Chiu no está clara. Completa la respuesta de Mia: No ___.",
        feedback = "La respuesta completa es: No entiendo.",
        reviewKey = "es:a1:vocabulary:comprehension-repair",
        acceptedAnswers = listOf("entiendo")
    ),
    LearningActivity(
        id = "fr-a1-narrative-gare-reparation-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Retrouver le verbe manquant dans la phrase de réparation de compréhension à la gare",
        knowledgeTarget = "Je ne comprends pas.",
        responseType = ResponseType.FILL_IN,
        prompt = "L’indication de Chiu n’est pas claire. Complète la réponse de Mia : Je ne ___ pas.",
        feedback = "La réponse complète est : Je ne comprends pas.",
        reviewKey = "fr:a1:vocabulary:comprehension-repair",
        acceptedAnswers = listOf("comprends")
    ),
    LearningActivity(
        id = "ko-a1-narrative-station-repair-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "역 이야기에서 이해하지 못했음을 알리는 표현의 빠진 부분 떠올리기",
        knowledgeTarget = "이해가 안 돼요.",
        responseType = ResponseType.FILL_IN,
        prompt = "치우의 설명이 분명하지 않아요. 미아의 말을 완성하세요: 이해가 안 ___.",
        feedback = "완전한 표현은 ‘이해가 안 돼요.’예요.",
        reviewKey = "ko:a1:vocabulary:comprehension-repair",
        acceptedAnswers = listOf("돼요")
    )
)

fun a1StationRepairFillInRetrievalActivitiesFor(languageCode: String): List<LearningActivity> =
    a1StationRepairFillInRetrievalActivities.filter { it.id.startsWith("$languageCode-") }
