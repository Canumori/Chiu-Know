package com.chiu.know.model

/**
 * Small controlled A1 slice for asking the price of an item.
 *
 * These activities use REORDER to require active reconstruction of a short,
 * useful price question without claiming open writing or conversational mastery.
 *
 * This file remains isolated from the starter bank until content and tests are
 * green on their own.
 */
private val a1BasicPriceActivities = listOf(
    LearningActivity(
        id = "en-a1-basic-price-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Build a basic question to ask the price of an item",
        knowledgeTarget = "How much is this?",
        responseType = ResponseType.REORDER,
        prompt = "Lara sees something she likes. Put the words in order to ask the price.",
        feedback = "A natural basic price question is: How much is this?",
        reviewKey = "en:a1:vocabulary:basic-price",
        acceptedAnswers = listOf("How much is this?"),
        responseOptions = listOf("How", "much", "is", "this?")
    ),
    LearningActivity(
        id = "pt-a1-basic-price-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Montar uma pergunta básica para saber o preço de um item",
        knowledgeTarget = "Quanto custa isto?",
        responseType = ResponseType.REORDER,
        prompt = "Lara viu algo de que gostou. Coloque as palavras em ordem para perguntar o preço.",
        feedback = "Uma pergunta básica e natural sobre preço é: Quanto custa isto?",
        reviewKey = "pt:a1:vocabulary:basic-price",
        acceptedAnswers = listOf("Quanto custa isto?"),
        responseOptions = listOf("Quanto", "custa", "isto?")
    ),
    LearningActivity(
        id = "es-a1-basic-price-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Construir una pregunta básica para saber el precio de un objeto",
        knowledgeTarget = "¿Cuánto cuesta esto?",
        responseType = ResponseType.REORDER,
        prompt = "Lara ve algo que le gusta. Ordena las palabras para preguntar el precio.",
        feedback = "Una pregunta básica y natural sobre el precio es: ¿Cuánto cuesta esto?",
        reviewKey = "es:a1:vocabulary:basic-price",
        acceptedAnswers = listOf("¿Cuánto cuesta esto?"),
        responseOptions = listOf("¿Cuánto", "cuesta", "esto?")
    ),
    LearningActivity(
        id = "fr-a1-basic-price-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Construire une question simple pour demander le prix d’un objet",
        knowledgeTarget = "Ça coûte combien ?",
        responseType = ResponseType.REORDER,
        prompt = "Lara voit quelque chose qui lui plaît. Remettez les mots dans l’ordre pour demander le prix.",
        feedback = "Une question simple et naturelle sur le prix est : Ça coûte combien ?",
        reviewKey = "fr:a1:vocabulary:basic-price",
        acceptedAnswers = listOf("Ça coûte combien ?"),
        responseOptions = listOf("Ça", "coûte", "combien", "?")
    ),
    LearningActivity(
        id = "ko-a1-basic-price-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "물건의 가격을 묻는 기본 표현을 만들기",
        knowledgeTarget = "이거 얼마예요?",
        responseType = ResponseType.REORDER,
        prompt = "라라가 마음에 드는 물건을 봤어요. 가격을 묻는 표현이 되도록 순서대로 고르세요.",
        feedback = "가격을 묻는 기본적인 표현은 ‘이거 얼마예요?’예요.",
        reviewKey = "ko:a1:vocabulary:basic-price",
        acceptedAnswers = listOf("이거 얼마예요?"),
        responseOptions = listOf("이거", "얼마예요?")
    )
)

fun a1BasicPriceActivitiesFor(languageCode: String): List<LearningActivity> =
    a1BasicPriceActivities.filter { it.id.startsWith("$languageCode-") }
