package com.chiu.know.model

/**
 * Second controlled A1 question-forming slice: asking where someone lives.
 * This remains deterministic REORDER practice, not speaking or free dialogue.
 */
private val a1ResidenceQuestionActivities = listOf(
    LearningActivity(
        id = "en-a1-question-residence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Form a basic question to ask where someone lives",
        knowledgeTarget = "Where do you live?",
        responseType = ResponseType.REORDER,
        prompt = "Mia wants to know where Chiu lives. Put the words in order.",
        feedback = "A basic question is: Where do you live?",
        reviewKey = "en:a1:grammar:question:ask-residence",
        acceptedAnswers = listOf("Where do you live"),
        responseOptions = listOf("live", "Where", "you", "do")
    ),
    LearningActivity(
        id = "pt-a1-question-residence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Formar uma pergunta básica para saber onde alguém mora",
        knowledgeTarget = "Onde você mora?",
        responseType = ResponseType.REORDER,
        prompt = "A Mia quer saber onde o Chiu mora. Coloque as palavras em ordem.",
        feedback = "Uma pergunta básica é: Onde você mora?",
        reviewKey = "pt:a1:grammar:pergunta:residencia",
        acceptedAnswers = listOf("Onde você mora"),
        responseOptions = listOf("mora", "Onde", "você")
    ),
    LearningActivity(
        id = "es-a1-question-residence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Formar una pregunta básica para saber dónde vive alguien",
        knowledgeTarget = "¿Dónde vives?",
        responseType = ResponseType.REORDER,
        prompt = "Mia quiere saber dónde vive Chiu. Ordena las palabras.",
        feedback = "Una pregunta básica es: ¿Dónde vives?",
        reviewKey = "es:a1:grammar:pregunta:residencia",
        acceptedAnswers = listOf("Dónde vives"),
        responseOptions = listOf("vives", "Dónde")
    ),
    LearningActivity(
        id = "fr-a1-question-residence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Former une question simple pour demander où habite quelqu’un",
        knowledgeTarget = "Où est-ce que tu habites ?",
        responseType = ResponseType.REORDER,
        prompt = "Mia veut savoir où habite Chiu. Remettez les éléments dans l’ordre.",
        feedback = "Une question simple est : Où est-ce que tu habites ?",
        reviewKey = "fr:a1:grammar:question:residence",
        acceptedAnswers = listOf("Où est-ce que tu habites"),
        responseOptions = listOf("tu", "habites", "Où", "est-ce", "que")
    ),
    LearningActivity(
        id = "ko-a1-question-residence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "상대방이 어디에 사는지 묻는 기본 질문 만들기",
        knowledgeTarget = "어디에 살아요?",
        responseType = ResponseType.REORDER,
        prompt = "미아가 치우에게 어디에 사는지 물어요. 표현을 올바른 순서로 놓으세요.",
        feedback = "기본적으로 ‘어디에 살아요?’라고 물을 수 있어요.",
        reviewKey = "ko:a1:grammar:question:ask-residence",
        acceptedAnswers = listOf("어디에 살아요"),
        responseOptions = listOf("살아요", "어디에")
    )
)

fun a1ResidenceQuestionActivitiesFor(languageCode: String): List<LearningActivity> =
    a1ResidenceQuestionActivities.filter { it.id.startsWith("$languageCode-") }
