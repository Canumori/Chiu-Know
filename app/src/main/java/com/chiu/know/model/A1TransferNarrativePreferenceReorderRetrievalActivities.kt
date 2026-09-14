package com.chiu.know.model

/**
 * A1 transfer preference retrieval with fewer cues in the park narrative.
 *
 * The learner reconstructs Mia's familiar preference response from tokens instead
 * of choosing between complete alternatives. This remains closed, deterministic
 * structured production and is not free writing, speaking, pronunciation, FSRS
 * evidence, or automatic mastery.
 */
private val a1TransferNarrativePreferenceReorderRetrievalActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-transfer-preference-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconstruct a familiar preference response from a dialogue cue",
        knowledgeTarget = "preference question-answer transfer in the park narrative",
        responseType = ResponseType.REORDER,
        prompt = "In the park, Chiu asks: ‘What do you like?’ Put the words in order for Mia’s reply.",
        feedback = "Mia’s reply is: I like books.",
        reviewKey = "en:a1:narrative:park:cued-preference",
        acceptedAnswers = listOf("I like books."),
        responseOptions = listOf("books.", "like", "I")
    ),
    LearningActivity(
        id = "pt-a1-narrative-transfer-preference-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconstruir uma resposta conhecida de preferência a partir de uma pista de diálogo",
        knowledgeTarget = "transferência da pergunta e resposta de preferência na narrativa do parque",
        responseType = ResponseType.REORDER,
        prompt = "No parque, Chiu pergunta: ‘Do que você gosta?’ Coloque as palavras em ordem para formar a resposta de Mia.",
        feedback = "A resposta de Mia é: Eu gosto de livros.",
        reviewKey = "pt:a1:narrativa:parque:recuperacao-preferencia",
        acceptedAnswers = listOf("Eu gosto de livros."),
        responseOptions = listOf("livros.", "de", "Eu", "gosto")
    ),
    LearningActivity(
        id = "es-a1-narrative-transfer-preference-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconstruir una respuesta conocida de preferencia a partir de una pista de diálogo",
        knowledgeTarget = "transferencia de pregunta y respuesta de preferencia en la narración del parque",
        responseType = ResponseType.REORDER,
        prompt = "En el parque, Chiu pregunta: ‘¿Qué te gusta?’ Ordena las palabras para formar la respuesta de Mia.",
        feedback = "La respuesta de Mia es: Me gustan los libros.",
        reviewKey = "es:a1:narrativa:parque:recuperacion-preferencia",
        acceptedAnswers = listOf("Me gustan los libros."),
        responseOptions = listOf("libros.", "gustan", "Me", "los")
    ),
    LearningActivity(
        id = "fr-a1-narrative-transfer-preference-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconstruire une réponse connue sur les préférences à partir d’un indice de dialogue",
        knowledgeTarget = "transfert de la question-réponse sur les préférences dans le récit du parc",
        responseType = ResponseType.REORDER,
        prompt = "Dans le parc, Chiu demande : « Qu’est-ce que tu aimes ? » Remets les éléments en ordre pour former la réponse de Mia.",
        feedback = "La réponse de Mia est : J’aime les livres.",
        reviewKey = "fr:a1:narration:parc:recuperation-preference",
        acceptedAnswers = listOf("J’aime les livres."),
        responseOptions = listOf("livres.", "J’aime", "les")
    ),
    LearningActivity(
        id = "ko-a1-narrative-transfer-preference-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "대화 단서에서 익숙한 선호 대답을 순서대로 재구성하기",
        knowledgeTarget = "공원 이야기에서 선호 질문과 대답의 전이",
        responseType = ResponseType.REORDER,
        prompt = "공원에서 치우가 ‘무엇을 좋아해요?’라고 물어요. 미아의 대답이 되도록 표현을 올바른 순서로 놓으세요.",
        feedback = "미아의 대답은 ‘책을 좋아해요.’예요.",
        reviewKey = "ko:a1:narrative:park:cued-preference",
        acceptedAnswers = listOf("책을 좋아해요."),
        responseOptions = listOf("좋아해요.", "책을")
    )
)

fun a1TransferNarrativePreferenceReorderRetrievalActivitiesFor(languageCode: String): List<LearningActivity> =
    a1TransferNarrativePreferenceReorderRetrievalActivities.filter { it.id.startsWith("$languageCode-") }
