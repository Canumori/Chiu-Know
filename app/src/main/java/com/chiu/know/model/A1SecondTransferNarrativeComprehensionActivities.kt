package com.chiu.know.model

/**
 * Closed comprehension check for the second A1 transfer context.
 *
 * It checks whether the learner can identify Chiu's preference after the
 * interlocutor and setting change. This remains deterministic recognition and
 * does not create mastery, FSRS, free-writing, speaking, or pronunciation evidence.
 */
private val a1SecondTransferNarrativeComprehensionActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-square-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Understand Chiu's familiar preference in a second transfer context",
        knowledgeTarget = "preference comprehension after interlocutor and setting transfer",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "In the square, what does Chiu say he likes?",
        feedback = "Chiu says: ‘I like coffee.’",
        reviewKey = "en:a1:narrative:square:comprehension-preference",
        acceptedAnswers = listOf("coffee"),
        responseOptions = listOf("coffee", "books")
    ),
    LearningActivity(
        id = "pt-a1-narrative-praca-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Compreender a preferência conhecida de Chiu em um segundo contexto de transferência",
        knowledgeTarget = "compreensão de preferência após mudança de interlocutor e contexto",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Na praça, do que Chiu diz que gosta?",
        feedback = "Chiu diz: ‘Eu gosto de café.’",
        reviewKey = "pt:a1:narrativa:praca:compreensao-preferencia",
        acceptedAnswers = listOf("café"),
        responseOptions = listOf("café", "livros")
    ),
    LearningActivity(
        id = "es-a1-narrative-plaza-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Comprender la preferencia conocida de Chiu en un segundo contexto de transferencia",
        knowledgeTarget = "comprensión de preferencias tras cambiar interlocutor y contexto",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "En la plaza, ¿qué dice Chiu que le gusta?",
        feedback = "Chiu dice: ‘Me gusta el café.’",
        reviewKey = "es:a1:narrativa:plaza:comprension-preferencia",
        acceptedAnswers = listOf("café"),
        responseOptions = listOf("café", "libros")
    ),
    LearningActivity(
        id = "fr-a1-narrative-place-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Comprendre la préférence connue de Chiu dans un deuxième contexte de transfert",
        knowledgeTarget = "compréhension de la préférence après changement d’interlocuteur et de contexte",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Sur la place, qu’est-ce que Chiu dit qu’il aime ?",
        feedback = "Chiu dit : « J’aime le café. »",
        reviewKey = "fr:a1:narration:place:comprehension-preference",
        acceptedAnswers = listOf("café"),
        responseOptions = listOf("café", "livres")
    ),
    LearningActivity(
        id = "ko-a1-narrative-square-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "두 번째 전이 맥락에서 치우의 익숙한 선호 표현 이해하기",
        knowledgeTarget = "화자와 장소가 바뀐 뒤 선호 표현 이해",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "광장에서 치우는 무엇을 좋아한다고 말해요?",
        feedback = "치우는 ‘커피를 좋아해요.’라고 말해요.",
        reviewKey = "ko:a1:narrative:square:comprehension-preference",
        acceptedAnswers = listOf("커피"),
        responseOptions = listOf("커피", "책")
    )
)

fun a1SecondTransferNarrativeComprehensionActivitiesFor(languageCode: String): List<LearningActivity> =
    a1SecondTransferNarrativeComprehensionActivities.filter { it.id.startsWith("$languageCode-") }
