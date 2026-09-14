package com.chiu.know.model

/**
 * Reduced-cue follow-up to the first A1 residence microinteraction.
 *
 * The learner first sees the question and must retrieve the core residence form
 * without visible answer choices. This remains controlled text production: it
 * is not free conversation, speaking, pronunciation assessment or mastery.
 */
private val a1ResidenceMicroInteractionRetrievalActivities = listOf(
    LearningActivity(
        id = "en-a1-interaction-residence-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Retrieve the core first-person residence form after a basic residence question",
        knowledgeTarget = "Where do you live? → I live in ...",
        responseType = ResponseType.FILL_IN,
        prompt = "Mia: ‘Where do you live?’ Chiu: ‘I ___ in Rio.’",
        feedback = "After ‘I’, use ‘live’: I live in Rio.",
        reviewKey = "en:a1:interaction:residence-question-answer",
        acceptedAnswers = listOf("live")
    ),
    LearningActivity(
        id = "pt-a1-interaction-residence-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Recuperar a forma central de residência em primeira pessoa depois de uma pergunta básica",
        knowledgeTarget = "Onde você mora? → Eu moro em ...",
        responseType = ResponseType.FILL_IN,
        prompt = "Mia: ‘Onde você mora?’ Chiu: ‘Eu ___ no Rio.’",
        feedback = "Com ‘eu’, usamos ‘moro’: Eu moro no Rio.",
        reviewKey = "pt:a1:interacao:pergunta-resposta-residencia",
        acceptedAnswers = listOf("moro")
    ),
    LearningActivity(
        id = "es-a1-interaction-residence-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Recuperar la forma central de residencia en primera persona después de una pregunta básica",
        knowledgeTarget = "¿Dónde vives? → Vivo en ...",
        responseType = ResponseType.FILL_IN,
        prompt = "Mia: ‘¿Dónde vives?’ Chiu: ‘___ en Río.’",
        feedback = "La respuesta básica usa ‘vivo’: Vivo en Río.",
        reviewKey = "es:a1:interaccion:pregunta-respuesta-residencia",
        acceptedAnswers = listOf("Vivo")
    ),
    LearningActivity(
        id = "fr-a1-interaction-residence-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Récupérer la forme centrale de résidence à la première personne après une question simple",
        knowledgeTarget = "Où est-ce que tu habites ? → J’habite à ...",
        responseType = ResponseType.FILL_IN,
        prompt = "Mia : « Où est-ce que tu habites ? » Chiu : « J’___ à Rio. »",
        feedback = "La réponse utilise ‘habite’ : J’habite à Rio.",
        reviewKey = "fr:a1:interaction:question-reponse-residence",
        acceptedAnswers = listOf("habite")
    ),
    LearningActivity(
        id = "ko-a1-interaction-residence-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "사는 곳을 묻는 기본 질문 뒤에 1인칭 거주 표현 떠올리기",
        knowledgeTarget = "어디에 살아요? → ...에 살아요",
        responseType = ResponseType.FILL_IN,
        prompt = "미아: ‘어디에 살아요?’ 치우: ‘리우에 ___.’",
        feedback = "사는 곳을 말할 때 ‘살아요’를 쓸 수 있어요: 리우에 살아요.",
        reviewKey = "ko:a1:interaction:residence-question-answer",
        acceptedAnswers = listOf("살아요")
    )
)

fun a1ResidenceMicroInteractionRetrievalActivitiesFor(languageCode: String): List<LearningActivity> =
    a1ResidenceMicroInteractionRetrievalActivities.filter { it.id.startsWith("$languageCode-") }
