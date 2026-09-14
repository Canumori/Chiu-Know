package com.chiu.know.model

/**
 * First short A1 narrative micro-unit.
 *
 * This file introduces narrative context only. It does not create a new mastery
 * signal, does not bypass the starter review queue and does not represent free
 * conversation, speaking or pronunciation assessment. The story deliberately
 * reuses already introduced A1 functions: greeting, name, residence and
 * preference. Visual assets are intentionally out of scope at this stage.
 *
 * Korean is included only after a documented rigorous second AI content review.
 * This does not imply human validation, psychometric validation, official CEFR
 * certification or automatic promotion of Korean placement to QUALITY_SESSION.
 */
data class NarrativeBeat(
    val speaker: String,
    val text: String
)

data class NarrativeMicroUnit(
    val id: String,
    val languageCode: String,
    val level: CefrLevel,
    val title: String,
    val setting: String,
    val beats: List<NarrativeBeat>,
    val linkedReviewKeys: List<String>
) {
    init {
        require(id.isNotBlank()) { "Narrative id must not be blank" }
        require(languageCode.isNotBlank()) { "Narrative language must not be blank" }
        require(title.isNotBlank()) { "Narrative title must not be blank" }
        require(setting.isNotBlank()) { "Narrative setting must not be blank" }
        require(beats.size >= 2) { "Narrative micro-unit must contain at least two beats" }
        require(beats.none { it.speaker.isBlank() || it.text.isBlank() }) {
            "Narrative beats must define speaker and text"
        }
        require(linkedReviewKeys.isNotEmpty()) { "Narrative must link to known review targets" }
        require(linkedReviewKeys.none { it.isBlank() }) { "Narrative review keys must not be blank" }
    }
}

private val a1FirstNarrativeMicroUnits = listOf(
    NarrativeMicroUnit(
        id = "en-a1-narrative-coffee-001",
        languageCode = "en",
        level = CefrLevel.A1,
        title = "Coffee and introductions",
        setting = "Mia meets Chiu at a small café.",
        beats = listOf(
            NarrativeBeat("Mia", "Hello! My name is Mia. What is your name?"),
            NarrativeBeat("Chiu", "My name is Chiu."),
            NarrativeBeat("Mia", "Where do you live?"),
            NarrativeBeat("Chiu", "I live in Rio."),
            NarrativeBeat("Mia", "What do you like?"),
            NarrativeBeat("Chiu", "I like coffee.")
        ),
        linkedReviewKeys = listOf(
            "en:a1:greeting:hello",
            "en:a1:grammar:question:ask-name",
            "en:a1:interaction:residence-question-answer",
            "en:a1:interaction:preference-question-answer"
        )
    ),
    NarrativeMicroUnit(
        id = "pt-a1-narrative-cafe-001",
        languageCode = "pt",
        level = CefrLevel.A1,
        title = "Café e apresentações",
        setting = "Mia conhece Chiu em um pequeno café.",
        beats = listOf(
            NarrativeBeat("Mia", "Olá! Meu nome é Mia. Qual é o seu nome?"),
            NarrativeBeat("Chiu", "Meu nome é Chiu."),
            NarrativeBeat("Mia", "Onde você mora?"),
            NarrativeBeat("Chiu", "Eu moro no Rio."),
            NarrativeBeat("Mia", "Do que você gosta?"),
            NarrativeBeat("Chiu", "Eu gosto de café.")
        ),
        linkedReviewKeys = listOf(
            "pt:a1:greeting:ola",
            "pt:a1:grammar:pergunta:nome",
            "pt:a1:interacao:pergunta-resposta-residencia",
            "pt:a1:interacao:pergunta-resposta-preferencia"
        )
    ),
    NarrativeMicroUnit(
        id = "es-a1-narrative-cafe-001",
        languageCode = "es",
        level = CefrLevel.A1,
        title = "Café y presentaciones",
        setting = "Mia conoce a Chiu en una pequeña cafetería.",
        beats = listOf(
            NarrativeBeat("Mia", "¡Hola! Me llamo Mia. ¿Cómo te llamas?"),
            NarrativeBeat("Chiu", "Me llamo Chiu."),
            NarrativeBeat("Mia", "¿Dónde vives?"),
            NarrativeBeat("Chiu", "Vivo en Río."),
            NarrativeBeat("Mia", "¿Qué te gusta?"),
            NarrativeBeat("Chiu", "Me gusta el café.")
        ),
        linkedReviewKeys = listOf(
            "es:a1:greeting:hola",
            "es:a1:grammar:pregunta:nombre",
            "es:a1:interaccion:pregunta-respuesta-residencia",
            "es:a1:interaccion:pregunta-respuesta-preferencia"
        )
    ),
    NarrativeMicroUnit(
        id = "fr-a1-narrative-cafe-001",
        languageCode = "fr",
        level = CefrLevel.A1,
        title = "Café et présentations",
        setting = "Mia rencontre Chiu dans un petit café.",
        beats = listOf(
            NarrativeBeat("Mia", "Bonjour ! Je m’appelle Mia. Comment tu t’appelles ?"),
            NarrativeBeat("Chiu", "Je m’appelle Chiu."),
            NarrativeBeat("Mia", "Où est-ce que tu habites ?"),
            NarrativeBeat("Chiu", "J’habite à Rio."),
            NarrativeBeat("Mia", "Qu’est-ce que tu aimes ?"),
            NarrativeBeat("Chiu", "J’aime le café.")
        ),
        linkedReviewKeys = listOf(
            "fr:a1:greeting:bonjour",
            "fr:a1:grammar:question:nom",
            "fr:a1:interaction:question-reponse-residence",
            "fr:a1:interaction:question-reponse-preference"
        )
    ),
    NarrativeMicroUnit(
        id = "ko-a1-narrative-coffee-001",
        languageCode = "ko",
        level = CefrLevel.A1,
        title = "카페에서 처음 만나요",
        setting = "미아와 치우가 작은 카페에서 처음 만나요.",
        beats = listOf(
            NarrativeBeat("Mia", "안녕하세요! 저는 미아예요. 이름이 뭐예요?"),
            NarrativeBeat("Chiu", "제 이름은 치우예요."),
            NarrativeBeat("Mia", "어디에 살아요?"),
            NarrativeBeat("Chiu", "리우에 살아요."),
            NarrativeBeat("Mia", "무엇을 좋아해요?"),
            NarrativeBeat("Chiu", "커피를 좋아해요.")
        ),
        linkedReviewKeys = listOf(
            "ko:a1:greeting:annyeonghaseyo",
            "ko:a1:grammar:question:ask-name",
            "ko:a1:interaction:residence-question-answer",
            "ko:a1:interaction:preference-question-answer"
        )
    )
)

fun a1FirstNarrativeMicroUnitFor(languageCode: String): NarrativeMicroUnit? =
    a1FirstNarrativeMicroUnits.firstOrNull { it.languageCode == languageCode }

/**
 * Starter-content entry point for narrative material.
 *
 * Narratives stay separate from the review queue: requesting a story does not
 * create learning evidence, change FSRS state or imply mastery. Higher CEFR
 * levels intentionally return null until real narrative content exists.
 */
fun starterNarrativeMicroUnitFor(
    languageCode: String,
    level: CefrLevel
): NarrativeMicroUnit? = when (level) {
    CefrLevel.A1 -> a1FirstNarrativeMicroUnitFor(languageCode)
    else -> null
}
