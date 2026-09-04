package com.chiu.know.model

/**
 * Recognition-format transfer for the A1 residence reading target.
 *
 * These MULTIPLE_CHOICE activities share the same residence-reading reviewKey.
 * They add contextual variation and recognition practice; they are NOT treated
 * as a less-cued step than FILL_IN and do not create a new mastery target.
 */
private val a1ResidenceReadingChoiceActivities = listOf(
    LearningActivity(
        id = "en-a1-reading-residence-choice-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recognize an explicit place of residence in a very short text",
        knowledgeTarget = "place of residence in a basic introduction",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Read: ‘Mia lives in Rio. Chiu lives in São Paulo.’ Where does Mia live?",
        feedback = "The text says ‘Mia lives in Rio’, so the answer is Rio.",
        reviewKey = "en:a1:reading:residence-place",
        acceptedAnswers = listOf("Rio"),
        responseOptions = listOf("Rio", "São Paulo")
    ),
    LearningActivity(
        id = "pt-a1-reading-residence-choice-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconhecer um local de residência explícito em um texto muito curto",
        knowledgeTarget = "local de residência em uma apresentação básica",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Leia: ‘A Mia mora no Rio. O Chiu mora em São Paulo.’ Onde a Mia mora?",
        feedback = "O texto diz ‘A Mia mora no Rio’, então a resposta é Rio.",
        reviewKey = "pt:a1:reading:residence-place",
        acceptedAnswers = listOf("Rio"),
        responseOptions = listOf("Rio", "São Paulo")
    ),
    LearningActivity(
        id = "es-a1-reading-residence-choice-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconocer un lugar de residencia explícito en un texto muy breve",
        knowledgeTarget = "lugar de residencia en una presentación básica",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Lee: ‘Mia vive en Río. Chiu vive en São Paulo.’ ¿Dónde vive Mia?",
        feedback = "El texto dice ‘Mia vive en Río’, así que la respuesta es Río.",
        reviewKey = "es:a1:reading:residence-place",
        acceptedAnswers = listOf("Río"),
        responseOptions = listOf("Río", "São Paulo")
    ),
    LearningActivity(
        id = "fr-a1-reading-residence-choice-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconnaître un lieu de résidence explicite dans un texte très court",
        knowledgeTarget = "lieu de résidence dans une présentation simple",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Lisez : « Mia habite à Rio. Chiu habite à São Paulo. » Où habite Mia ?",
        feedback = "Le texte dit « Mia habite à Rio » : la réponse est donc Rio.",
        reviewKey = "fr:a1:reading:residence-place",
        acceptedAnswers = listOf("Rio"),
        responseOptions = listOf("Rio", "São Paulo")
    ),
    LearningActivity(
        id = "ko-a1-reading-residence-choice-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "아주 짧은 글에서 명시적인 거주 장소 알아보기",
        knowledgeTarget = "기본 자기소개에 나온 거주 장소",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "읽으세요: ‘미아는 리우에 살아요. 치우는 상파울루에 살아요.’ 미아는 어디에 살아요?",
        feedback = "글에 ‘미아는 리우에 살아요’라고 되어 있으므로 정답은 리우입니다.",
        reviewKey = "ko:a1:reading:residence-place",
        acceptedAnswers = listOf("리우"),
        responseOptions = listOf("리우", "상파울루")
    )
)

fun a1ResidenceReadingChoiceActivitiesFor(languageCode: String): List<LearningActivity> =
    a1ResidenceReadingChoiceActivities.filter { it.id.startsWith("$languageCode-") }
