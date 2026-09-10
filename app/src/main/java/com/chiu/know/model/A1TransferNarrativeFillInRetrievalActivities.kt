package com.chiu.know.model

/**
 * A1 transfer retrieval with reduced lexical support in the park narrative.
 *
 * Unlike REORDER, the learner is no longer given every token of the response.
 * The dialogue frame stays visible, but one key element must be retrieved and
 * entered. This remains deterministic FILL_IN activity: it is not free writing,
 * speaking, pronunciation assessment, FSRS evidence, or automatic mastery.
 */
private val a1TransferNarrativeFillInRetrievalActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-transfer-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrieve a missing element from a familiar residence response",
        knowledgeTarget = "residence question-answer transfer in the park narrative",
        responseType = ResponseType.FILL_IN,
        prompt = "In the park, Mia asks ‘Where do you live?’ Complete Chiu’s reply: I ___ in Rio.",
        feedback = "The complete reply is: I live in Rio.",
        reviewKey = "en:a1:narrative:park:cued-residence",
        acceptedAnswers = listOf("live")
    ),
    LearningActivity(
        id = "pt-a1-narrative-transfer-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recuperar um elemento ausente de uma resposta conhecida sobre residência",
        knowledgeTarget = "transferência da pergunta e resposta de residência na narrativa do parque",
        responseType = ResponseType.FILL_IN,
        prompt = "No parque, Mia pergunta ‘Onde você mora?’ Complete a resposta de Chiu: Eu ___ no Rio.",
        feedback = "A resposta completa é: Eu moro no Rio.",
        reviewKey = "pt:a1:narrativa:parque:recuperacao-residencia",
        acceptedAnswers = listOf("moro")
    ),
    LearningActivity(
        id = "es-a1-narrative-transfer-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recuperar un elemento ausente de una respuesta conocida sobre residencia",
        knowledgeTarget = "transferencia de pregunta y respuesta de residencia en la narración del parque",
        responseType = ResponseType.FILL_IN,
        prompt = "En el parque, Mia pregunta ‘¿Dónde vives?’ Completa la respuesta de Chiu: ___ en Río.",
        feedback = "La respuesta completa es: Vivo en Río.",
        reviewKey = "es:a1:narrativa:parque:recuperacion-residencia",
        acceptedAnswers = listOf("Vivo")
    ),
    LearningActivity(
        id = "fr-a1-narrative-transfer-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrouver un élément manquant d’une réponse connue sur le lieu de résidence",
        knowledgeTarget = "transfert de la question-réponse sur le lieu de résidence dans le récit du parc",
        responseType = ResponseType.FILL_IN,
        prompt = "Dans le parc, Mia demande « Où est-ce que tu habites ? » Complète la réponse de Chiu : J’___ à Rio.",
        feedback = "La réponse complète est : J’habite à Rio.",
        reviewKey = "fr:a1:narration:parc:recuperation-residence",
        acceptedAnswers = listOf("habite")
    ),
    LearningActivity(
        id = "ko-a1-narrative-transfer-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "익숙한 거주지 대답에서 빠진 핵심 표현 떠올리기",
        knowledgeTarget = "공원 이야기에서 거주지 질문과 대답의 전이",
        responseType = ResponseType.FILL_IN,
        prompt = "공원에서 미아가 ‘어디에 살아요?’라고 물어요. 치우의 대답을 완성하세요: 리우에 ___.",
        feedback = "완전한 대답은 ‘리우에 살아요.’예요.",
        reviewKey = "ko:a1:narrative:park:cued-residence",
        acceptedAnswers = listOf("살아요")
    )
)

fun a1TransferNarrativeFillInRetrievalActivitiesFor(languageCode: String): List<LearningActivity> =
    a1TransferNarrativeFillInRetrievalActivities.filter { it.id.startsWith("$languageCode-") }
