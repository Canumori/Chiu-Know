package com.chiu.know.model

/**
 * A1 transfer retrieval with fewer cues in the park narrative.
 *
 * The learner now reconstructs the familiar residence answer from tokens instead
 * of choosing between complete alternatives. This remains closed, deterministic
 * structured production and is not free writing, speaking, pronunciation, FSRS
 * evidence, or automatic mastery.
 */
private val a1TransferNarrativeReorderRetrievalActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-transfer-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconstruct a familiar residence response from a dialogue cue",
        knowledgeTarget = "residence question-answer transfer in the park narrative",
        responseType = ResponseType.REORDER,
        prompt = "In the park, Mia asks: ‘Where do you live?’ Put the words in order for Chiu’s reply.",
        feedback = "Chiu’s reply is: I live in Rio.",
        reviewKey = "en:a1:narrative:park:cued-residence",
        acceptedAnswers = listOf("I live in Rio."),
        responseOptions = listOf("Rio.", "live", "I", "in")
    ),
    LearningActivity(
        id = "pt-a1-narrative-transfer-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconstruir uma resposta conhecida de residência a partir de uma pista de diálogo",
        knowledgeTarget = "transferência da pergunta e resposta de residência na narrativa do parque",
        responseType = ResponseType.REORDER,
        prompt = "No parque, Mia pergunta: ‘Onde você mora?’ Coloque as palavras em ordem para formar a resposta de Chiu.",
        feedback = "A resposta de Chiu é: Eu moro no Rio.",
        reviewKey = "pt:a1:narrativa:parque:recuperacao-residencia",
        acceptedAnswers = listOf("Eu moro no Rio."),
        responseOptions = listOf("Rio.", "moro", "Eu", "no")
    ),
    LearningActivity(
        id = "es-a1-narrative-transfer-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconstruir una respuesta conocida de residencia a partir de una pista de diálogo",
        knowledgeTarget = "transferencia de pregunta y respuesta de residencia en la narración del parque",
        responseType = ResponseType.REORDER,
        prompt = "En el parque, Mia pregunta: ‘¿Dónde vives?’ Ordena las palabras para formar la respuesta de Chiu.",
        feedback = "La respuesta de Chiu es: Vivo en Río.",
        reviewKey = "es:a1:narrativa:parque:recuperacion-residencia",
        acceptedAnswers = listOf("Vivo en Río."),
        responseOptions = listOf("Río.", "en", "Vivo")
    ),
    LearningActivity(
        id = "fr-a1-narrative-transfer-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconstruire une réponse connue sur le lieu de résidence à partir d’un indice de dialogue",
        knowledgeTarget = "transfert de la question-réponse sur le lieu de résidence dans le récit du parc",
        responseType = ResponseType.REORDER,
        prompt = "Dans le parc, Mia demande : « Où est-ce que tu habites ? » Remets les éléments en ordre pour former la réponse de Chiu.",
        feedback = "La réponse de Chiu est : J’habite à Rio.",
        reviewKey = "fr:a1:narration:parc:recuperation-residence",
        acceptedAnswers = listOf("J’habite à Rio."),
        responseOptions = listOf("Rio.", "J’habite", "à")
    ),
    LearningActivity(
        id = "ko-a1-narrative-transfer-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "대화 단서에서 익숙한 거주지 대답을 순서대로 재구성하기",
        knowledgeTarget = "공원 이야기에서 거주지 질문과 대답의 전이",
        responseType = ResponseType.REORDER,
        prompt = "공원에서 미아가 ‘어디에 살아요?’라고 물어요. 치우의 대답이 되도록 표현을 올바른 순서로 놓으세요.",
        feedback = "치우의 대답은 ‘리우에 살아요.’예요.",
        reviewKey = "ko:a1:narrative:park:cued-residence",
        acceptedAnswers = listOf("리우에 살아요."),
        responseOptions = listOf("살아요.", "리우에")
    )
)

fun a1TransferNarrativeReorderRetrievalActivitiesFor(languageCode: String): List<LearningActivity> =
    a1TransferNarrativeReorderRetrievalActivities.filter { it.id.startsWith("$languageCode-") }
