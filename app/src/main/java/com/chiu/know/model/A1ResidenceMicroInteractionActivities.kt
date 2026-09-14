package com.chiu.know.model

/**
 * First controlled A1 text microinteraction.
 *
 * The learner reads a short question and chooses the contextually appropriate
 * residence answer. This is recognition of a question-answer exchange, not
 * free conversation, speaking, or proof of interactive mastery.
 */
private val a1ResidenceMicroInteractionActivities = listOf(
    LearningActivity(
        id = "en-a1-interaction-residence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recognize an appropriate answer to a basic question about residence",
        knowledgeTarget = "Where do you live? → I live in ...",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Mia: ‘Where do you live?’ Chiu: ___",
        feedback = "‘I live in Rio’ directly answers the question about where Chiu lives.",
        reviewKey = "en:a1:interaction:residence-question-answer",
        acceptedAnswers = listOf("I live in Rio"),
        responseOptions = listOf("I live in Rio", "My name is Chiu", "I like coffee")
    ),
    LearningActivity(
        id = "pt-a1-interaction-residence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconhecer uma resposta adequada a uma pergunta básica sobre residência",
        knowledgeTarget = "Onde você mora? → Eu moro em ...",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Mia: ‘Onde você mora?’ Chiu: ___",
        feedback = "‘Eu moro no Rio’ responde diretamente à pergunta sobre onde o Chiu mora.",
        reviewKey = "pt:a1:interacao:pergunta-resposta-residencia",
        acceptedAnswers = listOf("Eu moro no Rio"),
        responseOptions = listOf("Eu moro no Rio", "Meu nome é Chiu", "Eu gosto de café")
    ),
    LearningActivity(
        id = "es-a1-interaction-residence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconocer una respuesta adecuada a una pregunta básica sobre residencia",
        knowledgeTarget = "¿Dónde vives? → Vivo en ...",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Mia: ‘¿Dónde vives?’ Chiu: ___",
        feedback = "‘Vivo en Río’ responde directamente a la pregunta sobre dónde vive Chiu.",
        reviewKey = "es:a1:interaccion:pregunta-respuesta-residencia",
        acceptedAnswers = listOf("Vivo en Río"),
        responseOptions = listOf("Vivo en Río", "Me llamo Chiu", "Me gusta el café")
    ),
    LearningActivity(
        id = "fr-a1-interaction-residence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconnaître une réponse appropriée à une question simple sur le lieu de résidence",
        knowledgeTarget = "Où est-ce que tu habites ? → J’habite à ...",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Mia : « Où est-ce que tu habites ? » Chiu : ___",
        feedback = "« J’habite à Rio » répond directement à la question sur le lieu où habite Chiu.",
        reviewKey = "fr:a1:interaction:question-reponse-residence",
        acceptedAnswers = listOf("J’habite à Rio"),
        responseOptions = listOf("J’habite à Rio", "Je m’appelle Chiu", "J’aime le café")
    ),
    LearningActivity(
        id = "ko-a1-interaction-residence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "사는 곳을 묻는 기본 질문에 알맞은 대답 알아보기",
        knowledgeTarget = "어디에 살아요? → ...에 살아요",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "미아: ‘어디에 살아요?’ 치우: ___",
        feedback = "‘리우에 살아요’는 어디에 사는지 묻는 질문에 직접 대답해요.",
        reviewKey = "ko:a1:interaction:residence-question-answer",
        acceptedAnswers = listOf("리우에 살아요"),
        responseOptions = listOf("리우에 살아요", "제 이름은 치우예요", "커피를 좋아해요")
    )
)

fun a1ResidenceMicroInteractionActivitiesFor(languageCode: String): List<LearningActivity> =
    a1ResidenceMicroInteractionActivities.filter { it.id.startsWith("$languageCode-") }
