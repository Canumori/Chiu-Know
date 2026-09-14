package com.chiu.know.model

/**
 * First controlled A1 structured-production slice.
 *
 * Each activity reuses the same basic first-person copula target already taught
 * in the FILL_IN grammar pair, but asks the learner to reconstruct the whole
 * sentence from explicit tokens. Sharing the reviewKey keeps this as transfer
 * of the same knowledge rather than inventing a new mastery target.
 */
private val a1ReorderActivities = listOf(
    LearningActivity(
        id = "en-a1-copula-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Reconstruct a basic first-person self-introduction",
        knowledgeTarget = "I am",
        responseType = ResponseType.REORDER,
        prompt = "Put the words in order to introduce Mia.",
        feedback = "A basic first-person introduction uses: I am Mia.",
        reviewKey = "en:a1:grammar:copula:first-person",
        acceptedAnswers = listOf("I am Mia"),
        responseOptions = listOf("Mia", "I", "am")
    ),
    LearningActivity(
        id = "pt-a1-copula-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Reconstruir uma apresentação básica na primeira pessoa",
        knowledgeTarget = "eu sou",
        responseType = ResponseType.REORDER,
        prompt = "Coloque as palavras em ordem para a Mia se apresentar.",
        feedback = "Uma apresentação básica em primeira pessoa pode ser: Eu sou a Mia.",
        reviewKey = "pt:a1:grammar:ser:first-person",
        acceptedAnswers = listOf("Eu sou a Mia"),
        responseOptions = listOf("Mia", "sou", "Eu", "a")
    ),
    LearningActivity(
        id = "es-a1-copula-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Reconstruir una presentación básica en primera persona",
        knowledgeTarget = "yo soy",
        responseType = ResponseType.REORDER,
        prompt = "Ordena las palabras para que Mia se presente.",
        feedback = "Una presentación básica en primera persona puede ser: Yo soy Mia.",
        reviewKey = "es:a1:grammar:ser:first-person",
        acceptedAnswers = listOf("Yo soy Mia"),
        responseOptions = listOf("Mia", "soy", "Yo")
    ),
    LearningActivity(
        id = "fr-a1-copula-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Reconstruire une présentation simple à la première personne",
        knowledgeTarget = "je suis",
        responseType = ResponseType.REORDER,
        prompt = "Remettez les mots dans l’ordre pour que Mia se présente.",
        feedback = "Une présentation simple à la première personne peut être : Je suis Mia.",
        reviewKey = "fr:a1:grammar:etre:first-person",
        acceptedAnswers = listOf("Je suis Mia"),
        responseOptions = listOf("Mia", "suis", "Je")
    ),
    LearningActivity(
        id = "ko-a1-copula-reorder-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "기본 자기소개 문장을 올바른 순서로 재구성하기",
        knowledgeTarget = "저는 미아예요",
        responseType = ResponseType.REORDER,
        prompt = "미아의 자기소개가 되도록 표현을 올바른 순서로 놓으세요.",
        feedback = "기본적인 자기소개는 ‘저는 미아예요’처럼 말할 수 있어요.",
        reviewKey = "ko:a1:grammar:copula:polite-introduction",
        acceptedAnswers = listOf("저는 미아예요"),
        responseOptions = listOf("미아예요", "저는")
    )
)

fun a1ReorderActivitiesFor(languageCode: String): List<LearningActivity> =
    a1ReorderActivities.filter { it.id.startsWith("$languageCode-") }
