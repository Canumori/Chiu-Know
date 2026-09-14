package com.chiu.know.model

/**
 * Small controlled A1 slice for identifying a close family member.
 *
 * These activities use REORDER to require active reconstruction of a short,
 * useful family-identification sentence without claiming open writing,
 * speaking or broader conversational mastery.
 *
 * This file remains isolated from the starter bank until content and tests are
 * green on their own.
 */
private val a1FamilyIdentificationActivities = listOf(
    LearningActivity(
        id = "en-a1-family-identification-mother-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Build a basic sentence to identify a close family member",
        knowledgeTarget = "This is my mother.",
        responseType = ResponseType.REORDER,
        prompt = "Mia is showing a family photo. Put the words in order to identify her mother.",
        feedback = "A basic family-identification sentence is: This is my mother.",
        reviewKey = "en:a1:vocabulary:family-identification:mother",
        acceptedAnswers = listOf("This is my mother."),
        responseOptions = listOf("This", "is", "my", "mother.")
    ),
    LearningActivity(
        id = "pt-a1-family-identification-mother-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Montar uma frase básica para identificar um familiar próximo",
        knowledgeTarget = "Esta é minha mãe.",
        responseType = ResponseType.REORDER,
        prompt = "Mia está mostrando uma foto da família. Coloque as palavras em ordem para identificar a mãe dela.",
        feedback = "Uma frase básica para identificar um familiar é: Esta é minha mãe.",
        reviewKey = "pt:a1:vocabulary:family-identification:mother",
        acceptedAnswers = listOf("Esta é minha mãe."),
        responseOptions = listOf("Esta", "é", "minha", "mãe.")
    ),
    LearningActivity(
        id = "es-a1-family-identification-mother-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Construir una frase básica para identificar a un familiar cercano",
        knowledgeTarget = "Esta es mi madre.",
        responseType = ResponseType.REORDER,
        prompt = "Mia está mostrando una foto de su familia. Ordena las palabras para identificar a su madre.",
        feedback = "Una frase básica para identificar a un familiar es: Esta es mi madre.",
        reviewKey = "es:a1:vocabulary:family-identification:mother",
        acceptedAnswers = listOf("Esta es mi madre."),
        responseOptions = listOf("Esta", "es", "mi", "madre.")
    ),
    LearningActivity(
        id = "fr-a1-family-identification-mother-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Construire une phrase simple pour identifier un membre proche de la famille",
        knowledgeTarget = "C’est ma mère.",
        responseType = ResponseType.REORDER,
        prompt = "Mia montre une photo de sa famille. Remettez les mots dans l’ordre pour identifier sa mère.",
        feedback = "Une phrase simple pour identifier un membre de la famille est : C’est ma mère.",
        reviewKey = "fr:a1:vocabulary:family-identification:mother",
        acceptedAnswers = listOf("C’est ma mère."),
        responseOptions = listOf("C’est", "ma", "mère.")
    ),
    LearningActivity(
        id = "ko-a1-family-identification-mother-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "가까운 가족을 소개하는 기본 표현을 만들기",
        knowledgeTarget = "이분은 제 어머니예요.",
        responseType = ResponseType.REORDER,
        prompt = "미아가 가족사진을 보여 주고 있어요. 어머니를 소개하는 표현이 되도록 순서대로 고르세요.",
        feedback = "가족을 소개하는 기본적인 표현은 ‘이분은 제 어머니예요.’예요.",
        reviewKey = "ko:a1:vocabulary:family-identification:mother",
        acceptedAnswers = listOf("이분은 제 어머니예요."),
        responseOptions = listOf("이분은", "제", "어머니예요.")
    )
)

fun a1FamilyIdentificationActivitiesFor(languageCode: String): List<LearningActivity> =
    a1FamilyIdentificationActivities.filter { it.id.startsWith("$languageCode-") }
