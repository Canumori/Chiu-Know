package com.chiu.know.model

private val starterLearningActivities = listOf(
    LearningActivity(
        id = "en-a1-greeting-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Retrieve a basic neutral greeting in context",
        knowledgeTarget = "hello",
        responseType = ResponseType.FILL_IN,
        prompt = "Complete the greeting: ___! Nice to meet you.",
        feedback = "‘Hello’ is a common neutral greeting.",
        reviewKey = "en:a1:greeting:hello",
        acceptedAnswers = listOf("hello")
    ),
    LearningActivity(
        id = "pt-a1-greeting-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Recuperar uma saudação básica e neutra em contexto",
        knowledgeTarget = "olá",
        responseType = ResponseType.FILL_IN,
        prompt = "Complete a saudação: ___! Prazer em conhecer você.",
        feedback = "‘Olá’ é uma saudação neutra e comum.",
        reviewKey = "pt:a1:greeting:ola",
        acceptedAnswers = listOf("olá")
    ),
    LearningActivity(
        id = "es-a1-greeting-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Recuperar un saludo básico y neutro en contexto",
        knowledgeTarget = "hola",
        responseType = ResponseType.FILL_IN,
        prompt = "Completa el saludo: ___! Mucho gusto.",
        feedback = "‘Hola’ es un saludo neutro y común.",
        reviewKey = "es:a1:greeting:hola",
        acceptedAnswers = listOf("hola")
    ),
    LearningActivity(
        id = "fr-a1-greeting-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Récupérer une salutation neutre de base en contexte",
        knowledgeTarget = "bonjour",
        responseType = ResponseType.FILL_IN,
        prompt = "Complétez la salutation : ___ ! Enchanté(e).",
        feedback = "‘Bonjour’ est une salutation neutre et courante.",
        reviewKey = "fr:a1:greeting:bonjour",
        acceptedAnswers = listOf("bonjour")
    ),
    LearningActivity(
        id = "ko-a1-greeting-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "기본적인 공손한 인사말을 문맥에서 떠올리기",
        knowledgeTarget = "안녕하세요",
        responseType = ResponseType.FILL_IN,
        prompt = "인사말을 완성하세요: ___! 만나서 반가워요.",
        feedback = "‘안녕하세요’는 기본적이고 공손한 인사말입니다.",
        reviewKey = "ko:a1:greeting:annyeonghaseyo",
        acceptedAnswers = listOf("안녕하세요")
    )
)

fun starterLearningActivitiesFor(languageCode: String): List<LearningActivity> =
    starterLearningActivities.filter { it.id.startsWith("$languageCode-") }

fun starterLearningActivityFor(languageCode: String, level: CefrLevel): LearningActivity? =
    starterLearningActivitiesFor(languageCode).firstOrNull { it.level == level }
