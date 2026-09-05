package com.chiu.know.model

/**
 * A1 transfer preference retrieval with reduced lexical support in the park narrative.
 *
 * Unlike REORDER, the learner is no longer given every token of Mia's response.
 * The dialogue frame stays visible, but one key element must be retrieved and
 * entered. This remains deterministic FILL_IN activity: it is not free writing,
 * speaking, pronunciation assessment, FSRS evidence, or automatic mastery.
 */
private val a1TransferNarrativePreferenceFillInRetrievalActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-transfer-preference-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrieve a missing element from a familiar preference response",
        knowledgeTarget = "preference question-answer transfer in the park narrative",
        responseType = ResponseType.FILL_IN,
        prompt = "In the park, Chiu asks ‘What do you like?’ Complete Mia’s reply: I like ___.",
        feedback = "The complete reply is: I like books.",
        reviewKey = "en:a1:narrative:park:cued-preference",
        acceptedAnswers = listOf("books")
    ),
    LearningActivity(
        id = "pt-a1-narrative-transfer-preference-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recuperar um elemento ausente de uma resposta conhecida sobre preferência",
        knowledgeTarget = "transferência da pergunta e resposta de preferência na narrativa do parque",
        responseType = ResponseType.FILL_IN,
        prompt = "No parque, Chiu pergunta ‘Do que você gosta?’ Complete a resposta de Mia: Eu gosto de ___.",
        feedback = "A resposta completa é: Eu gosto de livros.",
        reviewKey = "pt:a1:narrativa:parque:recuperacao-preferencia",
        acceptedAnswers = listOf("livros")
    ),
    LearningActivity(
        id = "es-a1-narrative-transfer-preference-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recuperar un elemento ausente de una respuesta conocida sobre preferencias",
        knowledgeTarget = "transferencia de pregunta y respuesta de preferencia en la narración del parque",
        responseType = ResponseType.FILL_IN,
        prompt = "En el parque, Chiu pregunta ‘¿Qué te gusta?’ Completa la respuesta de Mia: Me gustan los ___.",
        feedback = "La respuesta completa es: Me gustan los libros.",
        reviewKey = "es:a1:narrativa:parque:recuperacion-preferencia",
        acceptedAnswers = listOf("libros")
    ),
    LearningActivity(
        id = "fr-a1-narrative-transfer-preference-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrouver un élément manquant d’une réponse connue sur les préférences",
        knowledgeTarget = "transfert de la question-réponse sur les préférences dans le récit du parc",
        responseType = ResponseType.FILL_IN,
        prompt = "Dans le parc, Chiu demande « Qu’est-ce que tu aimes ? » Complète la réponse de Mia : J’aime les ___.",
        feedback = "La réponse complète est : J’aime les livres.",
        reviewKey = "fr:a1:narration:parc:recuperation-preference",
        acceptedAnswers = listOf("livres")
    ),
    LearningActivity(
        id = "ko-a1-narrative-transfer-preference-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "익숙한 선호 대답에서 빠진 핵심 표현 떠올리기",
        knowledgeTarget = "공원 이야기에서 선호 질문과 대답의 전이",
        responseType = ResponseType.FILL_IN,
        prompt = "공원에서 치우가 ‘무엇을 좋아해요?’라고 물어요. 미아의 대답을 완성하세요: ___ 좋아해요.",
        feedback = "완전한 대답은 ‘책을 좋아해요.’예요.",
        reviewKey = "ko:a1:narrative:park:cued-preference",
        acceptedAnswers = listOf("책을")
    )
)

fun a1TransferNarrativePreferenceFillInRetrievalActivitiesFor(languageCode: String): List<LearningActivity> =
    a1TransferNarrativePreferenceFillInRetrievalActivities.filter { it.id.startsWith("$languageCode-") }
