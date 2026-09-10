package com.chiu.know.model

/**
 * Second controlled A1 text microinteraction.
 *
 * The learner reads a short preference question and chooses the contextually
 * appropriate answer. This is recognition of a question-answer exchange, not
 * free conversation, speaking, pronunciation assessment or interactive mastery.
 */
private val a1PreferenceMicroInteractionActivities = listOf(
    LearningActivity(
        id = "en-a1-interaction-preference-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recognize an appropriate answer to a basic question about preference",
        knowledgeTarget = "What do you like? → I like ...",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Mia: ‘What do you like?’ Chiu: ___",
        feedback = "‘I like coffee’ directly answers the question about what Chiu likes.",
        reviewKey = "en:a1:interaction:preference-question-answer",
        acceptedAnswers = listOf("I like coffee"),
        responseOptions = listOf("I like coffee", "I live in Rio", "My name is Chiu")
    ),
    LearningActivity(
        id = "pt-a1-interaction-preference-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconhecer uma resposta adequada a uma pergunta básica sobre preferência",
        knowledgeTarget = "Do que você gosta? → Eu gosto de ...",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Mia: ‘Do que você gosta?’ Chiu: ___",
        feedback = "‘Eu gosto de café’ responde diretamente à pergunta sobre do que o Chiu gosta.",
        reviewKey = "pt:a1:interacao:pergunta-resposta-preferencia",
        acceptedAnswers = listOf("Eu gosto de café"),
        responseOptions = listOf("Eu gosto de café", "Eu moro no Rio", "Meu nome é Chiu")
    ),
    LearningActivity(
        id = "es-a1-interaction-preference-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconocer una respuesta adecuada a una pregunta básica sobre preferencia",
        knowledgeTarget = "¿Qué te gusta? → Me gusta ...",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Mia: ‘¿Qué te gusta?’ Chiu: ___",
        feedback = "‘Me gusta el café’ responde directamente a la pregunta sobre lo que le gusta a Chiu.",
        reviewKey = "es:a1:interaccion:pregunta-respuesta-preferencia",
        acceptedAnswers = listOf("Me gusta el café"),
        responseOptions = listOf("Me gusta el café", "Vivo en Río", "Me llamo Chiu")
    ),
    LearningActivity(
        id = "fr-a1-interaction-preference-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconnaître une réponse appropriée à une question simple sur une préférence",
        knowledgeTarget = "Qu’est-ce que tu aimes ? → J’aime ...",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Mia : « Qu’est-ce que tu aimes ? » Chiu : ___",
        feedback = "« J’aime le café » répond directement à la question sur ce que Chiu aime.",
        reviewKey = "fr:a1:interaction:question-reponse-preference",
        acceptedAnswers = listOf("J’aime le café"),
        responseOptions = listOf("J’aime le café", "J’habite à Rio", "Je m’appelle Chiu")
    ),
    LearningActivity(
        id = "ko-a1-interaction-preference-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "무엇을 좋아하는지 묻는 기본 질문에 알맞은 대답 알아보기",
        knowledgeTarget = "무엇을 좋아해요? → ...을/를 좋아해요",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "미아: ‘무엇을 좋아해요?’ 치우: ___",
        feedback = "‘커피를 좋아해요’는 무엇을 좋아하는지 묻는 질문에 직접 대답해요.",
        reviewKey = "ko:a1:interaction:preference-question-answer",
        acceptedAnswers = listOf("커피를 좋아해요"),
        responseOptions = listOf("커피를 좋아해요", "리우에 살아요", "제 이름은 치우예요")
    )
)

fun a1PreferenceMicroInteractionActivitiesFor(languageCode: String): List<LearningActivity> =
    a1PreferenceMicroInteractionActivities.filter { it.id.startsWith("$languageCode-") }
