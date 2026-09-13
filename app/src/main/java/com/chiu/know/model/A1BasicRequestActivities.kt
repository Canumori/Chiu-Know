package com.chiu.know.model

/**
 * Small controlled A1 slice for a basic polite request.
 *
 * These activities deliberately use REORDER rather than FREE_TEXT or SPEAK:
 * they exercise retrieval of a short formulaic request without claiming open
 * writing, speaking, pronunciation or conversational mastery.
 *
 * This file is intentionally isolated from the starter bank until its content
 * and tests are green on their own.
 */
private val a1BasicRequestActivities = listOf(
    LearningActivity(
        id = "en-a1-basic-request-water-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Build a short polite request for water",
        knowledgeTarget = "Water, please.",
        responseType = ResponseType.REORDER,
        prompt = "Barto is thirsty. Put the words in order to make a polite request.",
        feedback = "A short polite request is: Water, please.",
        reviewKey = "en:a1:vocabulary:basic-request:water",
        acceptedAnswers = listOf("Water, please."),
        responseOptions = listOf("Water,", "please.")
    ),
    LearningActivity(
        id = "pt-a1-basic-request-water-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Montar um pedido curto e educado de água",
        knowledgeTarget = "Água, por favor.",
        responseType = ResponseType.REORDER,
        prompt = "Barto está com sede. Coloque as palavras em ordem para fazer um pedido educado.",
        feedback = "Um pedido curto e educado é: Água, por favor.",
        reviewKey = "pt:a1:vocabulary:basic-request:water",
        acceptedAnswers = listOf("Água, por favor."),
        responseOptions = listOf("Água,", "por", "favor.")
    ),
    LearningActivity(
        id = "es-a1-basic-request-water-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Construir una petición breve y educada de agua",
        knowledgeTarget = "Agua, por favor.",
        responseType = ResponseType.REORDER,
        prompt = "Barto tiene sed. Ordena las palabras para hacer una petición educada.",
        feedback = "Una petición breve y educada es: Agua, por favor.",
        reviewKey = "es:a1:vocabulary:basic-request:water",
        acceptedAnswers = listOf("Agua, por favor."),
        responseOptions = listOf("Agua,", "por", "favor.")
    ),
    LearningActivity(
        id = "fr-a1-basic-request-water-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Construire une courte demande polie pour de l’eau",
        knowledgeTarget = "De l’eau, s’il vous plaît.",
        responseType = ResponseType.REORDER,
        prompt = "Barto a soif. Remettez les mots dans l’ordre pour faire une demande polie.",
        feedback = "Une courte demande polie est : De l’eau, s’il vous plaît.",
        reviewKey = "fr:a1:vocabulary:basic-request:water",
        acceptedAnswers = listOf("De l’eau, s’il vous plaît."),
        responseOptions = listOf("De", "l’eau,", "s’il", "vous", "plaît.")
    ),
    LearningActivity(
        id = "ko-a1-basic-request-water-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "물을 정중하게 요청하는 짧은 표현을 만들기",
        knowledgeTarget = "물 주세요.",
        responseType = ResponseType.REORDER,
        prompt = "바르토가 목이 말라요. 정중하게 물을 부탁하는 표현이 되도록 순서대로 고르세요.",
        feedback = "물을 정중하게 부탁할 때 ‘물 주세요.’라고 할 수 있어요.",
        reviewKey = "ko:a1:vocabulary:basic-request:water",
        acceptedAnswers = listOf("물 주세요."),
        responseOptions = listOf("물", "주세요.")
    )
)

fun a1BasicRequestActivitiesFor(languageCode: String): List<LearningActivity> =
    a1BasicRequestActivities.filter { it.id.startsWith("$languageCode-") }
