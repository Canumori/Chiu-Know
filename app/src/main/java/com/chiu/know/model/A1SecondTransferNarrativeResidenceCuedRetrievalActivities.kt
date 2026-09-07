package com.chiu.know.model

/**
 * A1 residence retrieval from the second transfer narrative in the square.
 *
 * Barto's complete residence question acts as the cue and the learner chooses
 * Chiu's complete reply. The preference reply is the contextual distractor.
 * This remains closed, deterministic recognition-supported retrieval and does
 * not represent free writing, speaking, pronunciation, FSRS evidence, or mastery.
 */
private val a1SecondTransferNarrativeResidenceCuedRetrievalActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-square-residence-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrieve Chiu's familiar residence response from a new dialogue cue",
        knowledgeTarget = "residence question-answer transfer in the square narrative",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "In the square, Barto asks: ‘Where do you live?’ Choose Chiu’s reply.",
        feedback = "Chiu replies: I live in Rio.",
        reviewKey = "en:a1:narrative:square:cued-residence",
        acceptedAnswers = listOf("I live in Rio."),
        responseOptions = listOf("I live in Rio.", "I like coffee.")
    ),
    LearningActivity(
        id = "pt-a1-narrative-praca-residence-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recuperar a resposta conhecida de residência de Chiu a partir de uma nova pista de diálogo",
        knowledgeTarget = "transferência da pergunta e resposta de residência na narrativa da praça",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Na praça, Barto pergunta: ‘Onde você mora?’ Escolha a resposta de Chiu.",
        feedback = "Chiu responde: Eu moro no Rio.",
        reviewKey = "pt:a1:narrativa:praca:recuperacao-residencia",
        acceptedAnswers = listOf("Eu moro no Rio."),
        responseOptions = listOf("Eu moro no Rio.", "Eu gosto de café.")
    ),
    LearningActivity(
        id = "es-a1-narrative-plaza-residence-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recuperar la respuesta conocida de residencia de Chiu a partir de una nueva pista de diálogo",
        knowledgeTarget = "transferencia de pregunta y respuesta de residencia en la narración de la plaza",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "En la plaza, Barto pregunta: ‘¿Dónde vives?’ Elige la respuesta de Chiu.",
        feedback = "Chiu responde: Vivo en Río.",
        reviewKey = "es:a1:narrativa:plaza:recuperacion-residencia",
        acceptedAnswers = listOf("Vivo en Río."),
        responseOptions = listOf("Vivo en Río.", "Me gusta el café.")
    ),
    LearningActivity(
        id = "fr-a1-narrative-place-residence-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrouver la réponse connue de Chiu sur son lieu de résidence à partir d’un nouvel indice de dialogue",
        knowledgeTarget = "transfert de la question-réponse sur le lieu de résidence dans le récit de la place",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Sur la place, Barto demande : « Où est-ce que tu habites ? » Choisis la réponse de Chiu.",
        feedback = "Chiu répond : J’habite à Rio.",
        reviewKey = "fr:a1:narration:place:recuperation-residence",
        acceptedAnswers = listOf("J’habite à Rio."),
        responseOptions = listOf("J’habite à Rio.", "J’aime le café.")
    ),
    LearningActivity(
        id = "ko-a1-narrative-square-residence-cued-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "새로운 대화 단서에서 치우의 익숙한 거주지 대답 떠올리기",
        knowledgeTarget = "광장 이야기에서 거주지 질문과 대답의 전이",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "광장에서 바르토가 ‘어디에 살아요?’라고 물어요. 치우의 대답을 고르세요.",
        feedback = "치우는 ‘리우에 살아요.’라고 대답해요.",
        reviewKey = "ko:a1:narrative:square:cued-residence",
        acceptedAnswers = listOf("리우에 살아요."),
        responseOptions = listOf("리우에 살아요.", "커피를 좋아해요.")
    )
)

fun a1SecondTransferNarrativeResidenceCuedRetrievalActivitiesFor(languageCode: String): List<LearningActivity> =
    a1SecondTransferNarrativeResidenceCuedRetrievalActivities.filter { it.id.startsWith("$languageCode-") }
