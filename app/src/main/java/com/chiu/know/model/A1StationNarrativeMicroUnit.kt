package com.chiu.know.model

/**
 * Fourth A1 narrative micro-unit in a surreal station.
 *
 * The dialogue contextualizes the already introduced restroom-location
 * question and comprehension-repair phrase. It remains isolated from starter
 * sequencing and does not create mastery evidence, alter FSRS state, or claim
 * free conversation, speaking, pronunciation, or broader communicative skill.
 */
private val a1StationNarrativeMicroUnits = listOf(
    NarrativeMicroUnit(
        id = "en-a1-narrative-station-004",
        languageCode = "en",
        level = CefrLevel.A1,
        title = "The impossible station",
        setting = "Mia looks for the restroom in a station with looping tracks.",
        beats = listOf(
            NarrativeBeat("Mia", "Where is the restroom?"),
            NarrativeBeat("Chiu", "Over there!"),
            NarrativeBeat("Mia", "I don't understand."),
            NarrativeBeat("Chiu", "The restroom is behind that door.")
        ),
        linkedReviewKeys = listOf(
            "en:a1:vocabulary:basic-location:restroom",
            "en:a1:vocabulary:comprehension-repair"
        )
    ),
    NarrativeMicroUnit(
        id = "pt-a1-narrative-estacao-004",
        languageCode = "pt",
        level = CefrLevel.A1,
        title = "A estação impossível",
        setting = "Mia procura o banheiro em uma estação com trilhos em laço.",
        beats = listOf(
            NarrativeBeat("Mia", "Onde fica o banheiro?"),
            NarrativeBeat("Chiu", "Ali!"),
            NarrativeBeat("Mia", "Eu não entendo."),
            NarrativeBeat("Chiu", "O banheiro fica atrás daquela porta.")
        ),
        linkedReviewKeys = listOf(
            "pt:a1:vocabulary:basic-location:banheiro",
            "pt:a1:vocabulary:comprehension-repair"
        )
    ),
    NarrativeMicroUnit(
        id = "es-a1-narrative-estacion-004",
        languageCode = "es",
        level = CefrLevel.A1,
        title = "La estación imposible",
        setting = "Mia busca el baño en una estación con vías en bucle.",
        beats = listOf(
            NarrativeBeat("Mia", "¿Dónde está el baño?"),
            NarrativeBeat("Chiu", "¡Allí!"),
            NarrativeBeat("Mia", "No entiendo."),
            NarrativeBeat("Chiu", "El baño está detrás de esa puerta.")
        ),
        linkedReviewKeys = listOf(
            "es:a1:vocabulary:basic-location:bano",
            "es:a1:vocabulary:comprehension-repair"
        )
    ),
    NarrativeMicroUnit(
        id = "fr-a1-narrative-gare-004",
        languageCode = "fr",
        level = CefrLevel.A1,
        title = "La gare impossible",
        setting = "Mia cherche les toilettes dans une gare aux rails en boucle.",
        beats = listOf(
            NarrativeBeat("Mia", "Où sont les toilettes ?"),
            NarrativeBeat("Chiu", "Là-bas !"),
            NarrativeBeat("Mia", "Je ne comprends pas."),
            NarrativeBeat("Chiu", "Les toilettes sont derrière cette porte.")
        ),
        linkedReviewKeys = listOf(
            "fr:a1:vocabulary:basic-location:toilettes",
            "fr:a1:vocabulary:comprehension-repair"
        )
    ),
    NarrativeMicroUnit(
        id = "ko-a1-narrative-station-004",
        languageCode = "ko",
        level = CefrLevel.A1,
        title = "이상한 역",
        setting = "미아가 고리처럼 이어진 선로가 있는 역에서 화장실을 찾아요.",
        beats = listOf(
            NarrativeBeat("Mia", "화장실이 어디예요?"),
            NarrativeBeat("Chiu", "저기예요!"),
            NarrativeBeat("Mia", "이해가 안 돼요."),
            NarrativeBeat("Chiu", "화장실은 저 문 뒤에 있어요.")
        ),
        linkedReviewKeys = listOf(
            "ko:a1:vocabulary:basic-location:restroom",
            "ko:a1:vocabulary:comprehension-repair"
        )
    )
)

fun a1StationNarrativeMicroUnitFor(languageCode: String): NarrativeMicroUnit? =
    a1StationNarrativeMicroUnits.firstOrNull { it.languageCode == languageCode }
