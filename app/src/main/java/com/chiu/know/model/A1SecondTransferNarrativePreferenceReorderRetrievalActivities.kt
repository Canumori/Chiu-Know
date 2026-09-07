package com.chiu.know.model

/**
 * A1 preference retrieval with fewer cues in the second transfer narrative.
 *
 * The learner reconstructs Chiu's familiar preference response from tokens after
 * Barto's question in the square instead of choosing between complete replies.
 * This remains closed, deterministic structured production and is not free
 * writing, speaking, pronunciation, FSRS evidence, or automatic mastery.
 */
private val a1SecondTransferNarrativePreferenceReorderRetrievalActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-square-preference-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconstruct Chiu's familiar preference response from a new dialogue cue",
        knowledgeTarget = "preference question-answer transfer in the square narrative",
        responseType = ResponseType.REORDER,
        prompt = "In the square, Barto asks: ‘What do you like?’ Put the words in order for Chiu’s reply.",
        feedback = "Chiu’s reply is: I like coffee.",
        reviewKey = "en:a1:narrative:square:cued-preference",
        acceptedAnswers = listOf("I like coffee."),
        responseOptions = listOf("coffee.", "like", "I")
    ),
    LearningActivity(
        id = "pt-a1-narrative-praca-preference-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconstruir a resposta conhecida de preferência de Chiu a partir de uma nova pista de diálogo",
        knowledgeTarget = "transferência da pergunta e resposta de preferência na narrativa da praça",
        responseType = ResponseType.REORDER,
        prompt = "Na praça, Barto pergunta: ‘Do que você gosta?’ Coloque as palavras em ordem para formar a resposta de Chiu.",
        feedback = "A resposta de Chiu é: Eu gosto de café.",
        reviewKey = "pt:a1:narrativa:praca:recuperacao-preferencia",
        acceptedAnswers = listOf("Eu gosto de café."),
        responseOptions = listOf("café.", "de", "Eu", "gosto")
    ),
    LearningActivity(
        id = "es-a1-narrative-plaza-preference-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconstruir la respuesta conocida de preferencia de Chiu a partir de una nueva pista de diálogo",
        knowledgeTarget = "transferencia de pregunta y respuesta de preferencia en la narración de la plaza",
        responseType = ResponseType.REORDER,
        prompt = "En la plaza, Barto pregunta: ‘¿Qué te gusta?’ Ordena las palabras para formar la respuesta de Chiu.",
        feedback = "La respuesta de Chiu es: Me gusta el café.",
        reviewKey = "es:a1:narrativa:plaza:recuperacion-preferencia",
        acceptedAnswers = listOf("Me gusta el café."),
        responseOptions = listOf("café.", "gusta", "Me", "el")
    ),
    LearningActivity(
        id = "fr-a1-narrative-place-preference-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconstruire la réponse connue de Chiu sur ses préférences à partir d’un nouvel indice de dialogue",
        knowledgeTarget = "transfert de la question-réponse sur les préférences dans le récit de la place",
        responseType = ResponseType.REORDER,
        prompt = "Sur la place, Barto demande : « Qu’est-ce que tu aimes ? » Remets les éléments en ordre pour former la réponse de Chiu.",
        feedback = "La réponse de Chiu est : J’aime le café.",
        reviewKey = "fr:a1:narration:place:recuperation-preference",
        acceptedAnswers = listOf("J’aime le café."),
        responseOptions = listOf("café.", "J’aime", "le")
    ),
    LearningActivity(
        id = "ko-a1-narrative-square-preference-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "새로운 대화 단서에서 치우의 익숙한 선호 대답을 순서대로 재구성하기",
        knowledgeTarget = "광장 이야기에서 선호 질문과 대답의 전이",
        responseType = ResponseType.REORDER,
        prompt = "광장에서 바르토가 ‘무엇을 좋아해요?’라고 물어요. 치우의 대답이 되도록 표현을 올바른 순서로 놓으세요.",
        feedback = "치우는 ‘커피를 좋아해요.’라고 대답해요.",
        reviewKey = "ko:a1:narrative:square:cued-preference",
        acceptedAnswers = listOf("커피를 좋아해요."),
        responseOptions = listOf("좋아해요.", "커피를")
    )
)

fun a1SecondTransferNarrativePreferenceReorderRetrievalActivitiesFor(languageCode: String): List<LearningActivity> =
    a1SecondTransferNarrativePreferenceReorderRetrievalActivities.filter { it.id.startsWith("$languageCode-") }
