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
        languageCode = "en",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "In the square, what does Chiu say he likes?",
        feedback = "Chiu says: ‘I like coffee.’",
        reviewKey = "en:a1:narrative:square:comprehension-preference",
        acceptedAnswers = listOf("coffee"),
        responseOptions = listOf("coffee", "books")
    ),
    LearningActivity(
        id = "pt-a1-narrative-praca-comprehension-001",
        languageCode = "pt",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Na praça, do que Chiu diz que gosta?",
        feedback = "Chiu diz: ‘Eu gosto de café.’",
        reviewKey = "pt:a1:narrativa:praca:compreensao-preferencia",
        acceptedAnswers = listOf("café"),
        responseOptions = listOf("café", "livros")
    ),
    LearningActivity(
        id = "es-a1-narrative-plaza-comprehension-001",
        languageCode = "es",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "En la plaza, ¿qué dice Chiu que le gusta?",
        feedback = "Chiu dice: ‘Me gusta el café.’",
        reviewKey = "es:a1:narrativa:plaza:comprension-preferencia",
        acceptedAnswers = listOf("café"),
        responseOptions = listOf("café", "libros")
    ),
    LearningActivity(
        id = "fr-a1-narrative-place-comprehension-001",
        languageCode = "fr",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Sur la place, qu’est-ce que Chiu dit qu’il aime ?",
        feedback = "Chiu dit : « J’aime le café. »",
        reviewKey = "fr:a1:narration:place:comprehension-preference",
        acceptedAnswers = listOf("café"),
        responseOptions = listOf("café", "livres")
    ),
    LearningActivity(
        id = "ko-a1-narrative-square-comprehension-001",
        languageCode = "ko",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "광장에서 치우는 무엇을 좋아한다고 말해요?",
        feedback = "치우는 ‘커피를 좋아해요.’라고 말해요.",
        reviewKey = "ko:a1:narrative:square:comprehension-preference",
        acceptedAnswers = listOf("커피"),
        responseOptions = listOf("커피", "책")
    )
)

fun a1SecondTransferNarrativeComprehensionActivitiesFor(languageCode: String): List<LearningActivity> =
    a1SecondTransferNarrativeComprehensionActivities.filter { it.languageCode == languageCode }
