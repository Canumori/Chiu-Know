package com.chiu.know.model

/**
 * Second A1 narrative micro-unit for controlled transfer.
 *
 * The dialogue recombines already introduced A1 functions in a new setting so
 * the learner has to recognize familiar language outside the first café story.
 * It does not create mastery evidence, alter FSRS state, or represent free
 * conversation, speaking, or pronunciation assessment.
 */
private val a1TransferNarrativeMicroUnits = listOf(
    NarrativeMicroUnit(
        id = "en-a1-narrative-park-002",
        languageCode = "en",
        level = CefrLevel.A1,
        title = "A meeting in the park",
        setting = "Mia meets Chiu again in a small park.",
        beats = listOf(
            NarrativeBeat("Mia", "Hello, Chiu!"),
            NarrativeBeat("Chiu", "Hello, Mia!"),
            NarrativeBeat("Mia", "Where do you live?"),
            NarrativeBeat("Chiu", "I live in Rio."),
            NarrativeBeat("Chiu", "What do you like?"),
            NarrativeBeat("Mia", "I like books.")
        ),
        linkedReviewKeys = listOf(
            "en:a1:greeting:hello",
            "en:a1:interaction:residence-question-answer",
            "en:a1:interaction:preference-question-answer"
        )
    ),
    NarrativeMicroUnit(
        id = "pt-a1-narrative-parque-002",
        languageCode = "pt",
        level = CefrLevel.A1,
        title = "Um encontro no parque",
        setting = "Mia encontra Chiu novamente em um pequeno parque.",
        beats = listOf(
            NarrativeBeat("Mia", "Olá, Chiu!"),
            NarrativeBeat("Chiu", "Olá, Mia!"),
            NarrativeBeat("Mia", "Onde você mora?"),
            NarrativeBeat("Chiu", "Eu moro no Rio."),
            NarrativeBeat("Chiu", "Do que você gosta?"),
            NarrativeBeat("Mia", "Eu gosto de livros.")
        ),
        linkedReviewKeys = listOf(
            "pt:a1:greeting:ola",
            "pt:a1:interacao:pergunta-resposta-residencia",
            "pt:a1:interacao:pergunta-resposta-preferencia"
        )
    ),
    NarrativeMicroUnit(
        id = "es-a1-narrative-parque-002",
        languageCode = "es",
        level = CefrLevel.A1,
        title = "Un encuentro en el parque",
        setting = "Mia vuelve a encontrarse con Chiu en un pequeño parque.",
        beats = listOf(
            NarrativeBeat("Mia", "¡Hola, Chiu!"),
            NarrativeBeat("Chiu", "¡Hola, Mia!"),
            NarrativeBeat("Mia", "¿Dónde vives?"),
            NarrativeBeat("Chiu", "Vivo en Río."),
            NarrativeBeat("Chiu", "¿Qué te gusta?"),
            NarrativeBeat("Mia", "Me gustan los libros.")
        ),
        linkedReviewKeys = listOf(
            "es:a1:greeting:hola",
            "es:a1:interaccion:pregunta-respuesta-residencia",
            "es:a1:interaccion:pregunta-respuesta-preferencia"
        )
    ),
    NarrativeMicroUnit(
        id = "fr-a1-narrative-parc-002",
        languageCode = "fr",
        level = CefrLevel.A1,
        title = "Une rencontre au parc",
        setting = "Mia retrouve Chiu dans un petit parc.",
        beats = listOf(
            NarrativeBeat("Mia", "Bonjour, Chiu !"),
            NarrativeBeat("Chiu", "Bonjour, Mia !"),
            NarrativeBeat("Mia", "Où est-ce que tu habites ?"),
            NarrativeBeat("Chiu", "J’habite à Rio."),
            NarrativeBeat("Chiu", "Qu’est-ce que tu aimes ?"),
            NarrativeBeat("Mia", "J’aime les livres.")
        ),
        linkedReviewKeys = listOf(
            "fr:a1:greeting:bonjour",
            "fr:a1:interaction:question-reponse-residence",
            "fr:a1:interaction:question-reponse-preference"
        )
    ),
    NarrativeMicroUnit(
        id = "ko-a1-narrative-park-002",
        languageCode = "ko",
        level = CefrLevel.A1,
        title = "공원에서 다시 만나요",
        setting = "미아와 치우가 작은 공원에서 다시 만나요.",
        beats = listOf(
            NarrativeBeat("Mia", "안녕하세요, 치우!"),
            NarrativeBeat("Chiu", "안녕하세요, 미아!"),
            NarrativeBeat("Mia", "어디에 살아요?"),
            NarrativeBeat("Chiu", "리우에 살아요."),
            NarrativeBeat("Chiu", "무엇을 좋아해요?"),
            NarrativeBeat("Mia", "책을 좋아해요.")
        ),
        linkedReviewKeys = listOf(
            "ko:a1:greeting:annyeonghaseyo",
            "ko:a1:interaction:residence-question-answer",
            "ko:a1:interaction:preference-question-answer"
        )
    )
)

fun a1TransferNarrativeMicroUnitFor(languageCode: String): NarrativeMicroUnit? =
    a1TransferNarrativeMicroUnits.firstOrNull { it.languageCode == languageCode }
