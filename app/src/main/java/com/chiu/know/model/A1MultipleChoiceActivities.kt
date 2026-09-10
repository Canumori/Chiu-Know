package com.chiu.know.model

/**
 * First controlled A1 multiple-choice slice.
 *
 * Each item asks for recognition of an explicit name in a very short text.
 * It reuses the existing reading reviewKey because the knowledge target is the
 * same; MULTIPLE_CHOICE is a different response format, not a new mastery claim.
 */
private val a1MultipleChoiceActivities = listOf(
    LearningActivity(
        id = "en-a1-reading-intro-choice-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recognize an explicit speaker name in a very short introduction",
        knowledgeTarget = "name in a basic introduction",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Read: ‘Hello! I’m Mia. Chiu is my friend.’ Who is speaking?",
        feedback = "The speaker says ‘I’m Mia’, so Mia is speaking.",
        reviewKey = "en:a1:reading:introduction-name",
        acceptedAnswers = listOf("Mia"),
        responseOptions = listOf("Mia", "Chiu")
    ),
    LearningActivity(
        id = "pt-a1-reading-intro-choice-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconhecer o nome explícito de quem fala em uma apresentação muito curta",
        knowledgeTarget = "nome em uma apresentação básica",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Leia: ‘Olá! Eu sou a Mia. O Chiu é meu amigo.’ Quem está falando?",
        feedback = "A personagem diz ‘Eu sou a Mia’, então é a Mia quem está falando.",
        reviewKey = "pt:a1:reading:introduction-name",
        acceptedAnswers = listOf("Mia"),
        responseOptions = listOf("Mia", "Chiu")
    ),
    LearningActivity(
        id = "es-a1-reading-intro-choice-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconocer el nombre explícito de quien habla en una presentación muy breve",
        knowledgeTarget = "nombre en una presentación básica",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Lee: ‘¡Hola! Soy Mia. Chiu es mi amigo.’ ¿Quién habla?",
        feedback = "La personaje dice ‘Soy Mia’, así que Mia es quien habla.",
        reviewKey = "es:a1:reading:introduction-name",
        acceptedAnswers = listOf("Mia"),
        responseOptions = listOf("Mia", "Chiu")
    ),
    LearningActivity(
        id = "fr-a1-reading-intro-choice-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconnaître le nom explicite de la personne qui parle dans une très courte présentation",
        knowledgeTarget = "nom dans une présentation simple",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Lisez : « Bonjour ! Je suis Mia. Chiu est mon ami. » Qui parle ?",
        feedback = "La personne dit « Je suis Mia » : c’est donc Mia qui parle.",
        reviewKey = "fr:a1:reading:introduction-name",
        acceptedAnswers = listOf("Mia"),
        responseOptions = listOf("Mia", "Chiu")
    ),
    LearningActivity(
        id = "ko-a1-reading-intro-choice-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "아주 짧은 자기소개에서 말하는 사람의 이름 알아보기",
        knowledgeTarget = "기본 자기소개에 나온 이름",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "읽으세요: ‘안녕하세요! 저는 미아예요. 치우는 제 친구예요.’ 누가 말하고 있나요?",
        feedback = "‘저는 미아예요’라고 말했으므로 미아가 말하고 있습니다.",
        reviewKey = "ko:a1:reading:introduction-name",
        acceptedAnswers = listOf("미아"),
        responseOptions = listOf("미아", "치우")
    )
)

fun a1MultipleChoiceActivitiesFor(languageCode: String): List<LearningActivity> =
    a1MultipleChoiceActivities.filter { it.id.startsWith("$languageCode-") }
