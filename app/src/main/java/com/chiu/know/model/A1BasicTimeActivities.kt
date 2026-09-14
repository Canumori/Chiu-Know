package com.chiu.know.model

/**
 * Small controlled A1 slice for asking the time.
 *
 * These activities use REORDER to require active reconstruction of a short,
 * useful everyday question without claiming open writing, speaking or broader
 * conversational mastery.
 *
 * This file remains isolated from the starter bank until content and tests are
 * green on their own.
 */
private val a1BasicTimeActivities = listOf(
    LearningActivity(
        id = "en-a1-basic-time-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Build a basic question to ask the time",
        knowledgeTarget = "What time is it?",
        responseType = ResponseType.REORDER,
        prompt = "Chiu needs to know the time. Put the words in order to ask naturally.",
        feedback = "A natural basic question is: What time is it?",
        reviewKey = "en:a1:vocabulary:basic-time",
        acceptedAnswers = listOf("What time is it?"),
        responseOptions = listOf("What", "time", "is", "it?")
    ),
    LearningActivity(
        id = "pt-a1-basic-time-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Montar uma pergunta básica para saber as horas",
        knowledgeTarget = "Que horas são?",
        responseType = ResponseType.REORDER,
        prompt = "Chiu precisa saber a hora. Coloque as palavras em ordem para perguntar naturalmente.",
        feedback = "Uma pergunta básica e natural é: Que horas são?",
        reviewKey = "pt:a1:vocabulary:basic-time",
        acceptedAnswers = listOf("Que horas são?"),
        responseOptions = listOf("Que", "horas", "são?")
    ),
    LearningActivity(
        id = "es-a1-basic-time-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Construir una pregunta básica para saber la hora",
        knowledgeTarget = "¿Qué hora es?",
        responseType = ResponseType.REORDER,
        prompt = "Chiu necesita saber la hora. Ordena las palabras para preguntar de forma natural.",
        feedback = "Una pregunta básica y natural es: ¿Qué hora es?",
        reviewKey = "es:a1:vocabulary:basic-time",
        acceptedAnswers = listOf("¿Qué hora es?"),
        responseOptions = listOf("¿Qué", "hora", "es?")
    ),
    LearningActivity(
        id = "fr-a1-basic-time-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Construire une question simple pour demander l’heure",
        knowledgeTarget = "Quelle heure est-il ?",
        responseType = ResponseType.REORDER,
        prompt = "Chiu a besoin de connaître l’heure. Remettez les mots dans l’ordre pour poser la question naturellement.",
        feedback = "Une question simple et naturelle est : Quelle heure est-il ?",
        reviewKey = "fr:a1:vocabulary:basic-time",
        acceptedAnswers = listOf("Quelle heure est-il ?"),
        responseOptions = listOf("Quelle", "heure", "est-il", "?")
    ),
    LearningActivity(
        id = "ko-a1-basic-time-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "현재 시간을 묻는 기본 표현을 만들기",
        knowledgeTarget = "지금 몇 시예요?",
        responseType = ResponseType.REORDER,
        prompt = "치우가 지금 시간을 알고 싶어 해요. 시간을 묻는 표현이 되도록 순서대로 고르세요.",
        feedback = "현재 시간을 묻는 기본적인 표현은 ‘지금 몇 시예요?’예요.",
        reviewKey = "ko:a1:vocabulary:basic-time",
        acceptedAnswers = listOf("지금 몇 시예요?"),
        responseOptions = listOf("지금", "몇", "시예요?")
    )
)

fun a1BasicTimeActivitiesFor(languageCode: String): List<LearningActivity> =
    a1BasicTimeActivities.filter { it.id.startsWith("$languageCode-") }
