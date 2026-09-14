package com.chiu.know.model

/**
 * A1 residence retrieval with reduced lexical support in the second transfer narrative.
 *
 * Unlike REORDER, the learner is no longer given every token of Chiu's response.
 * The dialogue frame stays visible, but one key element must be retrieved and
 * entered. This remains deterministic FILL_IN activity: it is not free writing,
 * speaking, pronunciation assessment, FSRS evidence, or automatic mastery.
 */
private val a1SecondTransferNarrativeResidenceFillInRetrievalActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-square-residence-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrieve a missing element from Chiu's familiar residence response in a new dialogue context",
        knowledgeTarget = "residence question-answer transfer in the square narrative",
        responseType = ResponseType.FILL_IN,
        prompt = "In the square, Barto asks ‘Where do you live?’ Complete Chiu’s reply: I ___ in Rio.",
        feedback = "The complete reply is: I live in Rio.",
        reviewKey = "en:a1:narrative:square:cued-residence",
        acceptedAnswers = listOf("live")
    ),
    LearningActivity(
        id = "pt-a1-narrative-praca-residence-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recuperar um elemento ausente da resposta conhecida de residência de Chiu em um novo contexto de diálogo",
        knowledgeTarget = "transferência da pergunta e resposta de residência na narrativa da praça",
        responseType = ResponseType.FILL_IN,
        prompt = "Na praça, Barto pergunta ‘Onde você mora?’ Complete a resposta de Chiu: Eu ___ no Rio.",
        feedback = "A resposta completa é: Eu moro no Rio.",
        reviewKey = "pt:a1:narrativa:praca:recuperacao-residencia",
        acceptedAnswers = listOf("moro")
    ),
    LearningActivity(
        id = "es-a1-narrative-plaza-residence-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recuperar un elemento ausente de la respuesta conocida de residencia de Chiu en un nuevo contexto de diálogo",
        knowledgeTarget = "transferencia de pregunta y respuesta de residencia en la narración de la plaza",
        responseType = ResponseType.FILL_IN,
        prompt = "En la plaza, Barto pregunta ‘¿Dónde vives?’ Completa la respuesta de Chiu: ___ en Río.",
        feedback = "La respuesta completa es: Vivo en Río.",
        reviewKey = "es:a1:narrativa:plaza:recuperacion-residencia",
        acceptedAnswers = listOf("Vivo")
    ),
    LearningActivity(
        id = "fr-a1-narrative-place-residence-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrouver un élément manquant de la réponse connue de Chiu sur son lieu de résidence dans un nouveau contexte de dialogue",
        knowledgeTarget = "transfert de la question-réponse sur le lieu de résidence dans le récit de la place",
        responseType = ResponseType.FILL_IN,
        prompt = "Sur la place, Barto demande « Où est-ce que tu habites ? » Complète la réponse de Chiu : J’___ à Rio.",
        feedback = "La réponse complète est : J’habite à Rio.",
        reviewKey = "fr:a1:narration:place:recuperation-residence",
        acceptedAnswers = listOf("habite")
    ),
    LearningActivity(
        id = "ko-a1-narrative-square-residence-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "새로운 대화 맥락에서 치우의 익숙한 거주지 대답에 빠진 핵심 표현 떠올리기",
        knowledgeTarget = "광장 이야기에서 거주지 질문과 대답의 전이",
        responseType = ResponseType.FILL_IN,
        prompt = "광장에서 바르토가 ‘어디에 살아요?’라고 물어요. 치우의 대답을 완성하세요: 리우에 ___.",
        feedback = "완전한 대답은 ‘리우에 살아요.’예요.",
        reviewKey = "ko:a1:narrative:square:cued-residence",
        acceptedAnswers = listOf("살아요")
    )
)

fun a1SecondTransferNarrativeResidenceFillInRetrievalActivitiesFor(languageCode: String): List<LearningActivity> =
    a1SecondTransferNarrativeResidenceFillInRetrievalActivities.filter { it.id.startsWith("$languageCode-") }
