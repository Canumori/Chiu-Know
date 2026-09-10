package com.chiu.know.model

/**
 * Second independent A1 reading target: identify an explicit place of residence
 * in a very short text. Two contexts per language support transfer without
 * requiring AI judgement or turning one correct answer into a mastery claim.
 */
private val a1ResidenceReadingActivities = listOf(
    LearningActivity(
        id = "en-a1-reading-residence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Understand an explicit place of residence in a very short text",
        knowledgeTarget = "place of residence in a basic introduction",
        responseType = ResponseType.FILL_IN,
        prompt = "Read: ‘Hello! I’m Mia. I live in Rio. Chiu lives nearby.’ Where does Mia live?",
        feedback = "The text says ‘I live in Rio’, so Mia lives in Rio.",
        reviewKey = "en:a1:reading:residence-place",
        acceptedAnswers = listOf("Rio")
    ),
    LearningActivity(
        id = "en-a1-reading-residence-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Find the same type of explicit residence detail in a new short text",
        knowledgeTarget = "place of residence in a basic introduction",
        responseType = ResponseType.FILL_IN,
        prompt = "Read: ‘My name is Chiu. I live in São Paulo. Mia is in Rio.’ Where does Chiu live?",
        feedback = "The text says ‘I live in São Paulo’, so Chiu lives in São Paulo.",
        reviewKey = "en:a1:reading:residence-place",
        acceptedAnswers = listOf("São Paulo", "Sao Paulo")
    ),
    LearningActivity(
        id = "pt-a1-reading-residence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Compreender um local de residência explícito em um texto muito curto",
        knowledgeTarget = "local de residência em uma apresentação básica",
        responseType = ResponseType.FILL_IN,
        prompt = "Leia: ‘Olá! Eu sou a Mia. Eu moro no Rio. O Chiu mora perto.’ Onde a Mia mora?",
        feedback = "O texto diz ‘Eu moro no Rio’, então a Mia mora no Rio.",
        reviewKey = "pt:a1:reading:residence-place",
        acceptedAnswers = listOf("Rio", "Rio de Janeiro")
    ),
    LearningActivity(
        id = "pt-a1-reading-residence-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Encontrar o mesmo tipo de informação de residência em um novo texto curto",
        knowledgeTarget = "local de residência em uma apresentação básica",
        responseType = ResponseType.FILL_IN,
        prompt = "Leia: ‘Meu nome é Chiu. Eu moro em São Paulo. A Mia está no Rio.’ Onde o Chiu mora?",
        feedback = "O texto diz ‘Eu moro em São Paulo’, então o Chiu mora em São Paulo.",
        reviewKey = "pt:a1:reading:residence-place",
        acceptedAnswers = listOf("São Paulo", "Sao Paulo")
    ),
    LearningActivity(
        id = "es-a1-reading-residence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Comprender un lugar de residencia explícito en un texto muy breve",
        knowledgeTarget = "lugar de residencia en una presentación básica",
        responseType = ResponseType.FILL_IN,
        prompt = "Lee: ‘¡Hola! Soy Mia. Vivo en Río. Chiu vive cerca.’ ¿Dónde vive Mia?",
        feedback = "El texto dice ‘Vivo en Río’, así que Mia vive en Río.",
        reviewKey = "es:a1:reading:residence-place",
        acceptedAnswers = listOf("Río", "Rio")
    ),
    LearningActivity(
        id = "es-a1-reading-residence-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Encontrar el mismo tipo de dato de residencia en un nuevo texto breve",
        knowledgeTarget = "lugar de residencia en una presentación básica",
        responseType = ResponseType.FILL_IN,
        prompt = "Lee: ‘Me llamo Chiu. Vivo en São Paulo. Mia está en Río.’ ¿Dónde vive Chiu?",
        feedback = "El texto dice ‘Vivo en São Paulo’, así que Chiu vive en São Paulo.",
        reviewKey = "es:a1:reading:residence-place",
        acceptedAnswers = listOf("São Paulo", "Sao Paulo")
    ),
    LearningActivity(
        id = "fr-a1-reading-residence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Comprendre un lieu de résidence explicite dans un texte très court",
        knowledgeTarget = "lieu de résidence dans une présentation simple",
        responseType = ResponseType.FILL_IN,
        prompt = "Lisez : « Bonjour ! Je suis Mia. J’habite à Rio. Chiu habite tout près. » Où habite Mia ?",
        feedback = "Le texte dit « J’habite à Rio » : Mia habite donc à Rio.",
        reviewKey = "fr:a1:reading:residence-place",
        acceptedAnswers = listOf("Rio")
    ),
    LearningActivity(
        id = "fr-a1-reading-residence-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrouver le même type d’information de résidence dans un nouveau texte court",
        knowledgeTarget = "lieu de résidence dans une présentation simple",
        responseType = ResponseType.FILL_IN,
        prompt = "Lisez : « Je m’appelle Chiu. J’habite à São Paulo. Mia est à Rio. » Où habite Chiu ?",
        feedback = "Le texte dit « J’habite à São Paulo » : Chiu habite donc à São Paulo.",
        reviewKey = "fr:a1:reading:residence-place",
        acceptedAnswers = listOf("São Paulo", "Sao Paulo")
    ),
    LearningActivity(
        id = "ko-a1-reading-residence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "아주 짧은 글에서 명시적인 거주 장소 이해하기",
        knowledgeTarget = "기본 자기소개에 나온 거주 장소",
        responseType = ResponseType.FILL_IN,
        prompt = "읽으세요: ‘안녕하세요! 저는 미아예요. 리우에 살아요. 치우는 근처에 살아요.’ 미아는 어디에 살아요?",
        feedback = "글에 ‘리우에 살아요’라고 되어 있으므로 미아는 리우에 살아요.",
        reviewKey = "ko:a1:reading:residence-place",
        acceptedAnswers = listOf("리우")
    ),
    LearningActivity(
        id = "ko-a1-reading-residence-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "새로운 짧은 글에서 같은 유형의 거주 정보 찾기",
        knowledgeTarget = "기본 자기소개에 나온 거주 장소",
        responseType = ResponseType.FILL_IN,
        prompt = "읽으세요: ‘제 이름은 치우예요. 상파울루에 살아요. 미아는 리우에 있어요.’ 치우는 어디에 살아요?",
        feedback = "글에 ‘상파울루에 살아요’라고 되어 있으므로 치우는 상파울루에 살아요.",
        reviewKey = "ko:a1:reading:residence-place",
        acceptedAnswers = listOf("상파울루")
    )
)

fun a1ResidenceReadingActivitiesFor(languageCode: String): List<LearningActivity> =
    a1ResidenceReadingActivities.filter { it.id.startsWith("$languageCode-") }
