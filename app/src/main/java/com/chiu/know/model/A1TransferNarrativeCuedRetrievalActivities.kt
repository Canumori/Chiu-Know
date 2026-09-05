package com.chiu.know.model

/**
 * Controlled A1 cued-retrieval checks in the second narrative context.
 *
 * These activities reduce support from fact recognition to choosing the familiar
 * response that completes a dialogue turn in the park. They remain closed and
 * deterministic: they are not free writing, do not create FSRS evidence or
 * mastery by themselves, and do not assess speaking or pronunciation.
 */
private val a1TransferNarrativeCuedRetrievalActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-transfer-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrieve a familiar residence response from a short dialogue cue",
        knowledgeTarget = "residence question-answer transfer in the park narrative",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "In the park, Mia asks Chiu: ‘Where do you live?’ Choose Chiu’s reply.",
        feedback = "Chiu answers the residence question with ‘I live in Rio.’",
        reviewKey = "en:a1:narrative:park:cued-residence",
        acceptedAnswers = listOf("I live in Rio."),
        responseOptions = listOf("I live in Rio.", "I like books.")
    ),
    LearningActivity(
        id = "pt-a1-narrative-transfer-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recuperar uma resposta conhecida de residência a partir de uma pista curta de diálogo",
        knowledgeTarget = "transferência da pergunta e resposta de residência na narrativa do parque",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "No parque, Mia pergunta a Chiu: ‘Onde você mora?’ Escolha a resposta de Chiu.",
        feedback = "Chiu responde à pergunta sobre residência com ‘Eu moro no Rio.’",
        reviewKey = "pt:a1:narrativa:parque:recuperacao-residencia",
        acceptedAnswers = listOf("Eu moro no Rio."),
        responseOptions = listOf("Eu moro no Rio.", "Eu gosto de livros.")
    ),
    LearningActivity(
        id = "es-a1-narrative-transfer-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recuperar una respuesta conocida de residencia a partir de una pista breve de diálogo",
        knowledgeTarget = "transferencia de pregunta y respuesta de residencia en la narración del parque",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "En el parque, Mia pregunta a Chiu: ‘¿Dónde vives?’ Elige la respuesta de Chiu.",
        feedback = "Chiu responde a la pregunta sobre residencia con ‘Vivo en Río.’",
        reviewKey = "es:a1:narrativa:parque:recuperacion-residencia",
        acceptedAnswers = listOf("Vivo en Río."),
        responseOptions = listOf("Vivo en Río.", "Me gustan los libros.")
    ),
    LearningActivity(
        id = "fr-a1-narrative-transfer-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrouver une réponse connue sur le lieu de résidence à partir d’un court indice de dialogue",
        knowledgeTarget = "transfert de la question-réponse sur le lieu de résidence dans le récit du parc",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Dans le parc, Mia demande à Chiu : « Où est-ce que tu habites ? » Choisis la réponse de Chiu.",
        feedback = "Chiu répond à la question sur son lieu de résidence par « J’habite à Rio. »",
        reviewKey = "fr:a1:narration:parc:recuperation-residence",
        acceptedAnswers = listOf("J’habite à Rio."),
        responseOptions = listOf("J’habite à Rio.", "J’aime les livres.")
    ),
    LearningActivity(
        id = "ko-a1-narrative-transfer-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "짧은 대화 단서에서 익숙한 거주지 대답 떠올리기",
        knowledgeTarget = "공원 이야기에서 거주지 질문과 대답의 전이",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "공원에서 미아가 치우에게 ‘어디에 살아요?’라고 물어요. 치우의 대답을 고르세요.",
        feedback = "치우는 사는 곳을 묻는 질문에 ‘리우에 살아요.’라고 대답해요.",
        reviewKey = "ko:a1:narrative:park:cued-residence",
        acceptedAnswers = listOf("리우에 살아요."),
        responseOptions = listOf("리우에 살아요.", "책을 좋아해요.")
    )
)

fun a1TransferNarrativeCuedRetrievalActivitiesFor(languageCode: String): List<LearningActivity> =
    a1TransferNarrativeCuedRetrievalActivities.filter { it.id.startsWith("$languageCode-") }
