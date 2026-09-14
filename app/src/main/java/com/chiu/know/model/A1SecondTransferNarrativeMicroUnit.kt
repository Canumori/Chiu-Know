package com.chiu.know.model

/**
 * Third A1 narrative micro-unit and second controlled transfer context.
 *
 * It reuses previously introduced residence and preference language with a
 * different interlocutor and setting. This is contextual transfer only: it
 * does not create mastery evidence, alter FSRS state, or represent free
 * writing, conversation, speaking, or pronunciation assessment.
 */
private val a1SecondTransferNarrativeMicroUnits = listOf(
    NarrativeMicroUnit(
        id = "en-a1-narrative-square-003",
        languageCode = "en",
        level = CefrLevel.A1,
        title = "A meeting in the square",
        setting = "Barto meets Chiu in a small square.",
        beats = listOf(
            NarrativeBeat("Barto", "Hello, Chiu!"),
            NarrativeBeat("Chiu", "Hello, Barto!"),
            NarrativeBeat("Barto", "Where do you live?"),
            NarrativeBeat("Chiu", "I live in Rio."),
            NarrativeBeat("Barto", "What do you like?"),
            NarrativeBeat("Chiu", "I like coffee.")
        ),
        linkedReviewKeys = listOf(
            "en:a1:greeting:hello",
            "en:a1:interaction:residence-question-answer",
            "en:a1:interaction:preference-question-answer"
        )
    ),
    NarrativeMicroUnit(
        id = "pt-a1-narrative-praca-003",
        languageCode = "pt",
        level = CefrLevel.A1,
        title = "Um encontro na praça",
        setting = "Barto encontra Chiu em uma pequena praça.",
        beats = listOf(
            NarrativeBeat("Barto", "Olá, Chiu!"),
            NarrativeBeat("Chiu", "Olá, Barto!"),
            NarrativeBeat("Barto", "Onde você mora?"),
            NarrativeBeat("Chiu", "Eu moro no Rio."),
            NarrativeBeat("Barto", "Do que você gosta?"),
            NarrativeBeat("Chiu", "Eu gosto de café.")
        ),
        linkedReviewKeys = listOf(
            "pt:a1:greeting:ola",
            "pt:a1:interacao:pergunta-resposta-residencia",
            "pt:a1:interacao:pergunta-resposta-preferencia"
        )
    ),
    NarrativeMicroUnit(
        id = "es-a1-narrative-plaza-003",
        languageCode = "es",
        level = CefrLevel.A1,
        title = "Un encuentro en la plaza",
        setting = "Barto se encuentra con Chiu en una pequeña plaza.",
        beats = listOf(
            NarrativeBeat("Barto", "¡Hola, Chiu!"),
            NarrativeBeat("Chiu", "¡Hola, Barto!"),
            NarrativeBeat("Barto", "¿Dónde vives?"),
            NarrativeBeat("Chiu", "Vivo en Río."),
            NarrativeBeat("Barto", "¿Qué te gusta?"),
            NarrativeBeat("Chiu", "Me gusta el café.")
        ),
        linkedReviewKeys = listOf(
            "es:a1:greeting:hola",
            "es:a1:interaccion:pregunta-respuesta-residencia",
            "es:a1:interaccion:pregunta-respuesta-preferencia"
        )
    ),
    NarrativeMicroUnit(
        id = "fr-a1-narrative-place-003",
        languageCode = "fr",
        level = CefrLevel.A1,
        title = "Une rencontre sur la place",
        setting = "Barto rencontre Chiu sur une petite place.",
        beats = listOf(
            NarrativeBeat("Barto", "Bonjour, Chiu !"),
            NarrativeBeat("Chiu", "Bonjour, Barto !"),
            NarrativeBeat("Barto", "Où est-ce que tu habites ?"),
            NarrativeBeat("Chiu", "J’habite à Rio."),
            NarrativeBeat("Barto", "Qu’est-ce que tu aimes ?"),
            NarrativeBeat("Chiu", "J’aime le café.")
        ),
        linkedReviewKeys = listOf(
            "fr:a1:greeting:bonjour",
            "fr:a1:interaction:question-reponse-residence",
            "fr:a1:interaction:question-reponse-preference"
        )
    ),
    NarrativeMicroUnit(
        id = "ko-a1-narrative-square-003",
        languageCode = "ko",
        level = CefrLevel.A1,
        title = "광장에서 만나요",
        setting = "바르토가 작은 광장에서 치우를 만나요.",
        beats = listOf(
            NarrativeBeat("Barto", "안녕하세요, 치우!"),
            NarrativeBeat("Chiu", "안녕하세요, 바르토!"),
            NarrativeBeat("Barto", "어디에 살아요?"),
            NarrativeBeat("Chiu", "리우에 살아요."),
            NarrativeBeat("Barto", "무엇을 좋아해요?"),
            NarrativeBeat("Chiu", "커피를 좋아해요.")
        ),
        linkedReviewKeys = listOf(
            "ko:a1:greeting:annyeonghaseyo",
            "ko:a1:interaction:residence-question-answer",
            "ko:a1:interaction:preference-question-answer"
        )
    )
)

fun a1SecondTransferNarrativeMicroUnitFor(languageCode: String): NarrativeMicroUnit? =
    a1SecondTransferNarrativeMicroUnits.firstOrNull { it.languageCode == languageCode }
