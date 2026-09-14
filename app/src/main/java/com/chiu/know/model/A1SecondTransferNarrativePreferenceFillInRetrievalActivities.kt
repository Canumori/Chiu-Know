package com.chiu.know.model

/**
 * A1 preference retrieval with reduced lexical support in the second transfer narrative.
 *
 * Unlike REORDER, the learner is no longer given every token of Chiu's response.
 * The dialogue frame stays visible, but one key element must be retrieved and
 * entered. This remains deterministic FILL_IN activity: it is not free writing,
 * speaking, pronunciation assessment, FSRS evidence, or automatic mastery.
 */
private val a1SecondTransferNarrativePreferenceFillInRetrievalActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-square-preference-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrieve a missing element from Chiu's familiar preference response",
        knowledgeTarget = "preference question-answer transfer in the square narrative",
        responseType = ResponseType.FILL_IN,
        prompt = "In the square, Barto asks ‘What do you like?’ Complete Chiu’s reply: I like ___.",
        feedback = "The complete reply is: I like coffee.",
        reviewKey = "en:a1:narrative:square:cued-preference",
        acceptedAnswers = listOf("coffee")
    ),
    LearningActivity(
        id = "pt-a1-narrative-praca-preference-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recuperar um elemento ausente da resposta conhecida de preferência de Chiu",
        knowledgeTarget = "transferência da pergunta e resposta de preferência na narrativa da praça",
        responseType = ResponseType.FILL_IN,
        prompt = "Na praça, Barto pergunta ‘Do que você gosta?’ Complete a resposta de Chiu: Eu gosto de ___.",
        feedback = "A resposta completa é: Eu gosto de café.",
        reviewKey = "pt:a1:narrativa:praca:recuperacao-preferencia",
        acceptedAnswers = listOf("café")
    ),
    LearningActivity(
        id = "es-a1-narrative-plaza-preference-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recuperar un elemento ausente de la respuesta conocida de preferencia de Chiu",
        knowledgeTarget = "transferencia de pregunta y respuesta de preferencia en la narración de la plaza",
        responseType = ResponseType.FILL_IN,
        prompt = "En la plaza, Barto pregunta ‘¿Qué te gusta?’ Completa la respuesta de Chiu: Me gusta el ___.",
        feedback = "La respuesta completa es: Me gusta el café.",
        reviewKey = "es:a1:narrativa:plaza:recuperacion-preferencia",
        acceptedAnswers = listOf("café")
    ),
    LearningActivity(
        id = "fr-a1-narrative-place-preference-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrouver un élément manquant de la réponse connue de Chiu sur ses préférences",
        knowledgeTarget = "transfert de la question-réponse sur les préférences dans le récit de la place",
        responseType = ResponseType.FILL_IN,
        prompt = "Sur la place, Barto demande « Qu’est-ce que tu aimes ? » Complète la réponse de Chiu : J’aime le ___.",
        feedback = "La réponse complète est : J’aime le café.",
        reviewKey = "fr:a1:narration:place:recuperation-preference",
        acceptedAnswers = listOf("café")
    ),
    LearningActivity(
        id = "ko-a1-narrative-square-preference-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "치우의 익숙한 선호 대답에서 빠진 핵심 표현 떠올리기",
        knowledgeTarget = "광장 이야기에서 선호 질문과 대답의 전이",
        responseType = ResponseType.FILL_IN,
        prompt = "광장에서 바르토가 ‘무엇을 좋아해요?’라고 물어요. 치우의 대답을 완성하세요: ___ 좋아해요.",
        feedback = "치우는 ‘커피를 좋아해요.’라고 대답해요.",
        reviewKey = "ko:a1:narrative:square:cued-preference",
        acceptedAnswers = listOf("커피를")
    )
)

fun a1SecondTransferNarrativePreferenceFillInRetrievalActivitiesFor(languageCode: String): List<LearningActivity> =
    a1SecondTransferNarrativePreferenceFillInRetrievalActivities.filter { it.id.startsWith("$languageCode-") }
