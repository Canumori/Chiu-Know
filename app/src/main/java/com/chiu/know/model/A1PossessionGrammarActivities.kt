package com.chiu.know.model

/**
 * Third controlled A1 grammar target: expressing basic possession.
 *
 * Each language keeps two short contexts under one reviewKey so retrieval can
 * vary without fabricating separate mastery targets. Korean uses the natural
 * existential possession pattern rather than forcing an English-style 'have'.
 */
private val a1PossessionGrammarActivities = listOf(
    LearningActivity(
        id = "en-a1-possession-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Use the first-person form of have to express basic possession",
        knowledgeTarget = "I have",
        responseType = ResponseType.FILL_IN,
        prompt = "Complete Mia’s sentence: I ___ a book.",
        feedback = "Use ‘have’ with ‘I’: I have a book.",
        reviewKey = "en:a1:grammar:possession:first-person",
        acceptedAnswers = listOf("have")
    ),
    LearningActivity(
        id = "en-a1-possession-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Retrieve the same possession structure in a new basic context",
        knowledgeTarget = "I have",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu talks about home: I ___ a small bed.",
        feedback = "The form remains ‘have’ after ‘I’: I have a small bed.",
        reviewKey = "en:a1:grammar:possession:first-person",
        acceptedAnswers = listOf("have")
    ),
    LearningActivity(
        id = "pt-a1-possession-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Usar a primeira pessoa de ter para expressar posse básica",
        knowledgeTarget = "eu tenho",
        responseType = ResponseType.FILL_IN,
        prompt = "Complete a frase da Mia: Eu ___ um livro.",
        feedback = "Com ‘eu’, usamos ‘tenho’: Eu tenho um livro.",
        reviewKey = "pt:a1:grammar:ter:first-person",
        acceptedAnswers = listOf("tenho")
    ),
    LearningActivity(
        id = "pt-a1-possession-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Recuperar a mesma estrutura de posse em outro contexto básico",
        knowledgeTarget = "eu tenho",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu fala sobre a casa: Eu ___ uma cama pequena.",
        feedback = "A forma continua ‘tenho’ depois de ‘eu’: Eu tenho uma cama pequena.",
        reviewKey = "pt:a1:grammar:ter:first-person",
        acceptedAnswers = listOf("tenho")
    ),
    LearningActivity(
        id = "es-a1-possession-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Usar la primera persona de tener para expresar posesión básica",
        knowledgeTarget = "yo tengo",
        responseType = ResponseType.FILL_IN,
        prompt = "Completa la frase de Mia: Yo ___ un libro.",
        feedback = "Con ‘yo’, usamos ‘tengo’: Yo tengo un libro.",
        reviewKey = "es:a1:grammar:tener:first-person",
        acceptedAnswers = listOf("tengo")
    ),
    LearningActivity(
        id = "es-a1-possession-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Recuperar la misma estructura de posesión en otro contexto básico",
        knowledgeTarget = "yo tengo",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu habla de su casa: Yo ___ una cama pequeña.",
        feedback = "La forma sigue siendo ‘tengo’ después de ‘yo’: Yo tengo una cama pequeña.",
        reviewKey = "es:a1:grammar:tener:first-person",
        acceptedAnswers = listOf("tengo")
    ),
    LearningActivity(
        id = "fr-a1-possession-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Utiliser la première personne d’avoir pour exprimer une possession simple",
        knowledgeTarget = "j’ai",
        responseType = ResponseType.FILL_IN,
        prompt = "Complétez la phrase de Mia : J’___ un livre.",
        feedback = "Avec « je », on utilise « ai » : J’ai un livre.",
        reviewKey = "fr:a1:grammar:avoir:first-person",
        acceptedAnswers = listOf("ai")
    ),
    LearningActivity(
        id = "fr-a1-possession-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Retrouver la même structure de possession dans un autre contexte simple",
        knowledgeTarget = "j’ai",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu parle de sa maison : J’___ un petit lit.",
        feedback = "La forme reste « ai » : J’ai un petit lit.",
        reviewKey = "fr:a1:grammar:avoir:first-person",
        acceptedAnswers = listOf("ai")
    ),
    LearningActivity(
        id = "ko-a1-possession-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "기본적인 소유를 나타낼 때 ‘있어요’ 표현 사용하기",
        knowledgeTarget = "저는 ... 있어요",
        responseType = ResponseType.FILL_IN,
        prompt = "미아의 문장을 완성하세요: 저는 책이 ___.",
        feedback = "기본적인 소유를 말할 때 ‘있어요’를 쓸 수 있어요: 저는 책이 있어요.",
        reviewKey = "ko:a1:grammar:possession:isseoyo",
        acceptedAnswers = listOf("있어요")
    ),
    LearningActivity(
        id = "ko-a1-possession-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "같은 소유 표현을 다른 기본 상황에서 떠올리기",
        knowledgeTarget = "저는 ... 있어요",
        responseType = ResponseType.FILL_IN,
        prompt = "치우가 집에 대해 말해요: 저는 작은 침대가 ___.",
        feedback = "같은 표현을 쓸 수 있어요: 저는 작은 침대가 있어요.",
        reviewKey = "ko:a1:grammar:possession:isseoyo",
        acceptedAnswers = listOf("있어요")
    )
)

fun a1PossessionGrammarActivitiesFor(languageCode: String): List<LearningActivity> =
    a1PossessionGrammarActivities.filter { it.id.startsWith("$languageCode-") }
