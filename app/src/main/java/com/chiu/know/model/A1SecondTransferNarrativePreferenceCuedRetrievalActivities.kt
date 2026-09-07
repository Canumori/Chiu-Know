package com.chiu.know.model

/**
 * Controlled A1 cued retrieval for Chiu's preference in the second transfer context.
 *
 * The learner selects the full familiar response after Barto asks the preference
 * question in the square. This remains closed and deterministic, creates no FSRS
 * evidence or mastery by itself, and does not assess speaking, pronunciation, or
 * free writing.
 */
private val a1SecondTransferNarrativePreferenceCuedRetrievalActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-square-preference-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrieve Chiu's familiar preference response from a new dialogue cue",
        knowledgeTarget = "preference question-answer transfer in the square narrative",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "In the square, Barto asks Chiu: ‘What do you like?’ Choose Chiu’s reply.",
        feedback = "Chiu answers the preference question with ‘I like coffee.’",
        reviewKey = "en:a1:narrative:square:cued-preference",
        acceptedAnswers = listOf("I like coffee."),
        responseOptions = listOf("I like coffee.", "I live in Rio.")
    ),
    LearningActivity(
        id = "pt-a1-narrative-praca-preference-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recuperar a resposta conhecida de preferência de Chiu a partir de uma nova pista de diálogo",
        knowledgeTarget = "transferência da pergunta e resposta de preferência na narrativa da praça",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Na praça, Barto pergunta a Chiu: ‘Do que você gosta?’ Escolha a resposta de Chiu.",
        feedback = "Chiu responde à pergunta sobre preferência com ‘Eu gosto de café.’",
        reviewKey = "pt:a1:narrativa:praca:recuperacao-preferencia",
        acceptedAnswers = listOf("Eu gosto de café."),
        responseOptions = listOf("Eu gosto de café.", "Eu moro no Rio.")
    ),
    LearningActivity(
        id = "es-a1-narrative-plaza-preference-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recuperar la respuesta conocida de preferencia de Chiu a partir de una nueva pista de diálogo",
        knowledgeTarget = "transferencia de pregunta y respuesta de preferencia en la narración de la plaza",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "En la plaza, Barto pregunta a Chiu: ‘¿Qué te gusta?’ Elige la respuesta de Chiu.",
        feedback = "Chiu responde a la pregunta sobre preferencias con ‘Me gusta el café.’",
        reviewKey = "es:a1:narrativa:plaza:recuperacion-preferencia",
        acceptedAnswers = listOf("Me gusta el café."),
        responseOptions = listOf("Me gusta el café.", "Vivo en Río.")
    ),
    LearningActivity(
        id = "fr-a1-narrative-place-preference-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrouver la réponse connue de Chiu sur ses préférences à partir d’un nouvel indice de dialogue",
        knowledgeTarget = "transfert de la question-réponse sur les préférences dans le récit de la place",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Sur la place, Barto demande à Chiu : « Qu’est-ce que tu aimes ? » Choisis la réponse de Chiu.",
        feedback = "Chiu répond à la question sur ses préférences par « J’aime le café. »",
        reviewKey = "fr:a1:narration:place:recuperation-preference",
        acceptedAnswers = listOf("J’aime le café."),
        responseOptions = listOf("J’aime le café.", "J’habite à Rio.")
    ),
    LearningActivity(
        id = "ko-a1-narrative-square-preference-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "새로운 대화 단서에서 치우의 익숙한 선호 대답 떠올리기",
        knowledgeTarget = "광장 이야기에서 선호 질문과 대답의 전이",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "광장에서 바르토가 치우에게 ‘무엇을 좋아해요?’라고 물어요. 치우의 대답을 고르세요.",
        feedback = "치우는 좋아하는 것을 묻는 질문에 ‘커피를 좋아해요.’라고 대답해요.",
        reviewKey = "ko:a1:narrative:square:cued-preference",
        acceptedAnswers = listOf("커피를 좋아해요."),
        responseOptions = listOf("커피를 좋아해요.", "리우에 살아요.")
    )
)

fun a1SecondTransferNarrativePreferenceCuedRetrievalActivitiesFor(languageCode: String): List<LearningActivity> =
    a1SecondTransferNarrativePreferenceCuedRetrievalActivities.filter { it.id.startsWith("$languageCode-") }
