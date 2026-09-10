package com.chiu.know.model

/**
 * Reduced-cue retrieval after the controlled A1 preference microinteraction.
 *
 * The learner completes one already introduced preference form inside the same
 * short question-answer exchange. This is controlled textual retrieval, not
 * free conversation, speaking, pronunciation assessment or proof of mastery.
 * Sharing the interaction reviewKey lets the review queue rotate scaffolding
 * for the same functional target instead of treating this as a new target.
 */
private val a1PreferenceMicroInteractionRetrievalActivities = listOf(
    LearningActivity(
        id = "en-a1-interaction-preference-retrieval-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Retrieve the basic preference form with reduced cueing inside a short exchange",
        knowledgeTarget = "What do you like? → I like ...",
        responseType = ResponseType.FILL_IN,
        prompt = "Mia: ‘What do you like?’ Chiu: ‘I ___ coffee.’",
        feedback = "Use ‘like’ to complete the already introduced preference answer.",
        reviewKey = "en:a1:interaction:preference-question-answer",
        acceptedAnswers = listOf("like")
    ),
    LearningActivity(
        id = "pt-a1-interaction-preference-retrieval-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Recuperar a forma básica de preferência com menos pistas em uma troca curta",
        knowledgeTarget = "Do que você gosta? → Eu gosto de ...",
        responseType = ResponseType.FILL_IN,
        prompt = "Mia: ‘Do que você gosta?’ Chiu: ‘Eu ___ de café.’",
        feedback = "Use ‘gosto’ para completar a resposta de preferência já apresentada.",
        reviewKey = "pt:a1:interacao:pergunta-resposta-preferencia",
        acceptedAnswers = listOf("gosto")
    ),
    LearningActivity(
        id = "es-a1-interaction-preference-retrieval-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Recuperar la forma básica de preferencia con menos apoyo dentro de un intercambio breve",
        knowledgeTarget = "¿Qué te gusta? → Me gusta ...",
        responseType = ResponseType.FILL_IN,
        prompt = "Mia: ‘¿Qué te gusta?’ Chiu: ‘Me ___ el café.’",
        feedback = "Usa ‘gusta’ para completar la respuesta de preferencia ya presentada.",
        reviewKey = "es:a1:interaccion:pregunta-respuesta-preferencia",
        acceptedAnswers = listOf("gusta")
    ),
    LearningActivity(
        id = "fr-a1-interaction-preference-retrieval-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Récupérer la forme simple de préférence avec moins d’indices dans un bref échange",
        knowledgeTarget = "Qu’est-ce que tu aimes ? → J’aime ...",
        responseType = ResponseType.FILL_IN,
        prompt = "Mia : « Qu’est-ce que tu aimes ? » Chiu : « J’___ le café. »",
        feedback = "Utilisez ‘aime’ pour compléter la réponse de préférence déjà présentée.",
        reviewKey = "fr:a1:interaction:question-reponse-preference",
        acceptedAnswers = listOf("aime")
    ),
    LearningActivity(
        id = "ko-a1-interaction-preference-retrieval-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "짧은 문답에서 이미 제시된 기본 선호 표현을 더 적은 단서로 떠올리기",
        knowledgeTarget = "무엇을 좋아해요? → ...을/를 좋아해요",
        responseType = ResponseType.FILL_IN,
        prompt = "미아: ‘무엇을 좋아해요?’ 치우: ‘커피를 ___.’",
        feedback = "이미 제시된 선호 대답을 완성하려면 ‘좋아해요’를 사용하세요.",
        reviewKey = "ko:a1:interaction:preference-question-answer",
        acceptedAnswers = listOf("좋아해요")
    )
)

fun a1PreferenceMicroInteractionRetrievalActivitiesFor(languageCode: String): List<LearningActivity> =
    a1PreferenceMicroInteractionRetrievalActivities.filter { it.id.startsWith("$languageCode-") }
