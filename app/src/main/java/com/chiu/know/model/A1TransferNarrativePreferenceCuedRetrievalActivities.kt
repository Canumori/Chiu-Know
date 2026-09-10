package com.chiu.know.model

/**
 * Controlled A1 cued-retrieval checks for preference language in the second narrative context.
 *
 * These activities move beyond identifying who said the preference line: the learner must choose
 * the familiar response that completes Chiu's preference question in the park. They remain closed
 * and deterministic, create no FSRS evidence or mastery by themselves, and do not assess speaking,
 * pronunciation, or free writing.
 */
private val a1TransferNarrativePreferenceCuedRetrievalActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-transfer-preference-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrieve a familiar preference response from a short dialogue cue",
        knowledgeTarget = "preference question-answer transfer in the park narrative",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "In the park, Chiu asks Mia: ‘What do you like?’ Choose Mia’s reply.",
        feedback = "Mia answers the preference question with ‘I like books.’",
        reviewKey = "en:a1:narrative:park:cued-preference",
        acceptedAnswers = listOf("I like books."),
        responseOptions = listOf("I like books.", "I live in Rio.")
    ),
    LearningActivity(
        id = "pt-a1-narrative-transfer-preference-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recuperar uma resposta conhecida de preferência a partir de uma pista curta de diálogo",
        knowledgeTarget = "transferência da pergunta e resposta de preferência na narrativa do parque",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "No parque, Chiu pergunta a Mia: ‘Do que você gosta?’ Escolha a resposta de Mia.",
        feedback = "Mia responde à pergunta sobre preferência com ‘Eu gosto de livros.’",
        reviewKey = "pt:a1:narrativa:parque:recuperacao-preferencia",
        acceptedAnswers = listOf("Eu gosto de livros."),
        responseOptions = listOf("Eu gosto de livros.", "Eu moro no Rio.")
    ),
    LearningActivity(
        id = "es-a1-narrative-transfer-preference-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recuperar una respuesta conocida de preferencia a partir de una pista breve de diálogo",
        knowledgeTarget = "transferencia de pregunta y respuesta de preferencia en la narración del parque",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "En el parque, Chiu pregunta a Mia: ‘¿Qué te gusta?’ Elige la respuesta de Mia.",
        feedback = "Mia responde a la pregunta sobre preferencias con ‘Me gustan los libros.’",
        reviewKey = "es:a1:narrativa:parque:recuperacion-preferencia",
        acceptedAnswers = listOf("Me gustan los libros."),
        responseOptions = listOf("Me gustan los libros.", "Vivo en Río.")
    ),
    LearningActivity(
        id = "fr-a1-narrative-transfer-preference-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrouver une réponse connue sur les préférences à partir d’un court indice de dialogue",
        knowledgeTarget = "transfert de la question-réponse sur les préférences dans le récit du parc",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Dans le parc, Chiu demande à Mia : « Qu’est-ce que tu aimes ? » Choisis la réponse de Mia.",
        feedback = "Mia répond à la question sur ses préférences par « J’aime les livres. »",
        reviewKey = "fr:a1:narration:parc:recuperation-preference",
        acceptedAnswers = listOf("J’aime les livres."),
        responseOptions = listOf("J’aime les livres.", "J’habite à Rio.")
    ),
    LearningActivity(
        id = "ko-a1-narrative-transfer-preference-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "짧은 대화 단서에서 익숙한 선호 대답 떠올리기",
        knowledgeTarget = "공원 이야기에서 선호 질문과 대답의 전이",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "공원에서 치우가 미아에게 ‘무엇을 좋아해요?’라고 물어요. 미아의 대답을 고르세요.",
        feedback = "미아는 좋아하는 것을 묻는 질문에 ‘책을 좋아해요.’라고 대답해요.",
        reviewKey = "ko:a1:narrative:park:cued-preference",
        acceptedAnswers = listOf("책을 좋아해요."),
        responseOptions = listOf("책을 좋아해요.", "리우에 살아요.")
    )
)

fun a1TransferNarrativePreferenceCuedRetrievalActivitiesFor(languageCode: String): List<LearningActivity> =
    a1TransferNarrativePreferenceCuedRetrievalActivities.filter { it.id.startsWith("$languageCode-") }
