package com.chiu.know.model

/**
 * A1 residence retrieval with fewer cues in the second transfer narrative.
 *
 * The learner reconstructs Chiu's familiar residence response from tokens after
 * Barto's question in the square instead of choosing between complete replies.
 * This remains closed, deterministic structured production and is not free
 * writing, speaking, pronunciation, FSRS evidence, or automatic mastery.
 */
private val a1SecondTransferNarrativeResidenceReorderRetrievalActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-square-residence-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconstruct Chiu's familiar residence response from a new dialogue cue",
        knowledgeTarget = "residence question-answer transfer in the square narrative",
        responseType = ResponseType.REORDER,
        prompt = "In the square, Barto asks: ‘Where do you live?’ Put the words in order for Chiu’s reply.",
        feedback = "Chiu’s reply is: I live in Rio.",
        reviewKey = "en:a1:narrative:square:cued-residence",
        acceptedAnswers = listOf("I live in Rio."),
        responseOptions = listOf("Rio.", "live", "I", "in")
    ),
    LearningActivity(
        id = "pt-a1-narrative-praca-residence-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconstruir a resposta conhecida de residência de Chiu a partir de uma nova pista de diálogo",
        knowledgeTarget = "transferência da pergunta e resposta de residência na narrativa da praça",
        responseType = ResponseType.REORDER,
        prompt = "Na praça, Barto pergunta: ‘Onde você mora?’ Coloque as palavras em ordem para formar a resposta de Chiu.",
        feedback = "A resposta de Chiu é: Eu moro no Rio.",
        reviewKey = "pt:a1:narrativa:praca:recuperacao-residencia",
        acceptedAnswers = listOf("Eu moro no Rio."),
        responseOptions = listOf("Rio.", "moro", "Eu", "no")
    ),
    LearningActivity(
        id = "es-a1-narrative-plaza-residence-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconstruir la respuesta conocida de residencia de Chiu a partir de una nueva pista de diálogo",
        knowledgeTarget = "transferencia de pregunta y respuesta de residencia en la narración de la plaza",
        responseType = ResponseType.REORDER,
        prompt = "En la plaza, Barto pregunta: ‘¿Dónde vives?’ Ordena las palabras para formar la respuesta de Chiu.",
        feedback = "La respuesta de Chiu es: Vivo en Río.",
        reviewKey = "es:a1:narrativa:plaza:recuperacion-residencia",
        acceptedAnswers = listOf("Vivo en Río."),
        responseOptions = listOf("Río.", "en", "Vivo")
    ),
    LearningActivity(
        id = "fr-a1-narrative-place-residence-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconstruire la réponse connue de Chiu sur son lieu de résidence à partir d’un nouvel indice de dialogue",
        knowledgeTarget = "transfert de la question-réponse sur le lieu de résidence dans le récit de la place",
        responseType = ResponseType.REORDER,
        prompt = "Sur la place, Barto demande : « Où est-ce que tu habites ? » Remets les éléments en ordre pour former la réponse de Chiu.",
        feedback = "La réponse de Chiu est : J’habite à Rio.",
        reviewKey = "fr:a1:narration:place:recuperation-residence",
        acceptedAnswers = listOf("J’habite à Rio."),
        responseOptions = listOf("Rio.", "J’habite", "à")
    ),
    LearningActivity(
        id = "ko-a1-narrative-square-residence-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "새로운 대화 단서에서 치우의 익숙한 거주지 대답을 순서대로 재구성하기",
        knowledgeTarget = "광장 이야기에서 거주지 질문과 대답의 전이",
        responseType = ResponseType.REORDER,
        prompt = "광장에서 바르토가 ‘어디에 살아요?’라고 물어요. 치우의 대답이 되도록 표현을 올바른 순서로 놓으세요.",
        feedback = "치우의 대답은 ‘리우에 살아요.’예요.",
        reviewKey = "ko:a1:narrative:square:cued-residence",
        acceptedAnswers = listOf("리우에 살아요."),
        responseOptions = listOf("살아요.", "리우에")
    )
)

fun a1SecondTransferNarrativeResidenceReorderRetrievalActivitiesFor(languageCode: String): List<LearningActivity> =
    a1SecondTransferNarrativeResidenceReorderRetrievalActivities.filter { it.id.startsWith("$languageCode-") }
