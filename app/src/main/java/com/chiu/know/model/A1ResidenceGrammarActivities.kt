package com.chiu.know.model

/**
 * Second independent A1 grammar target: a basic first-person residence statement.
 *
 * Each language reuses the same target in two contexts so the learner retrieves
 * the form rather than memorising one sentence. This remains deterministic and
 * does not imply mastery from a single correct answer.
 */
private val a1ResidenceGrammarActivities = listOf(
    LearningActivity(
        id = "en-a1-residence-grammar-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Use the first-person present form of live in a basic residence statement",
        knowledgeTarget = "I live",
        responseType = ResponseType.FILL_IN,
        prompt = "Complete Mia’s sentence: I ___ in Rio.",
        feedback = "With ‘I’, the basic present form is ‘live’: I live in Rio.",
        reviewKey = "en:a1:grammar:live:first-person",
        acceptedAnswers = listOf("live")
    ),
    LearningActivity(
        id = "en-a1-residence-grammar-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Retrieve the same first-person present form in a new residence context",
        knowledgeTarget = "I live",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu talks about his home: I ___ near the park.",
        feedback = "The form remains ‘live’ after ‘I’: I live near the park.",
        reviewKey = "en:a1:grammar:live:first-person",
        acceptedAnswers = listOf("live")
    ),
    LearningActivity(
        id = "pt-a1-residence-grammar-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Usar a primeira pessoa do presente de morar em uma frase básica sobre residência",
        knowledgeTarget = "eu moro",
        responseType = ResponseType.FILL_IN,
        prompt = "Complete a frase da Mia: Eu ___ no Rio.",
        feedback = "Com ‘eu’, usamos ‘moro’: Eu moro no Rio.",
        reviewKey = "pt:a1:grammar:morar:first-person",
        acceptedAnswers = listOf("moro")
    ),
    LearningActivity(
        id = "pt-a1-residence-grammar-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Recuperar a mesma forma de morar em um novo contexto de residência",
        knowledgeTarget = "eu moro",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu fala sobre sua casa: Eu ___ perto do parque.",
        feedback = "A forma continua ‘moro’ depois de ‘eu’: Eu moro perto do parque.",
        reviewKey = "pt:a1:grammar:morar:first-person",
        acceptedAnswers = listOf("moro")
    ),
    LearningActivity(
        id = "es-a1-residence-grammar-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Usar la primera persona del presente de vivir en una frase básica sobre residencia",
        knowledgeTarget = "yo vivo",
        responseType = ResponseType.FILL_IN,
        prompt = "Completa la frase de Mia: Yo ___ en Río.",
        feedback = "Con ‘yo’, usamos ‘vivo’: Yo vivo en Río.",
        reviewKey = "es:a1:grammar:vivir:first-person",
        acceptedAnswers = listOf("vivo")
    ),
    LearningActivity(
        id = "es-a1-residence-grammar-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Recuperar la misma forma de vivir en un nuevo contexto de residencia",
        knowledgeTarget = "yo vivo",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu habla de su casa: Yo ___ cerca del parque.",
        feedback = "La forma sigue siendo ‘vivo’ después de ‘yo’: Yo vivo cerca del parque.",
        reviewKey = "es:a1:grammar:vivir:first-person",
        acceptedAnswers = listOf("vivo")
    ),
    LearningActivity(
        id = "fr-a1-residence-grammar-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Utiliser la première personne du présent d’habiter dans une phrase simple sur le lieu de vie",
        knowledgeTarget = "j’habite",
        responseType = ResponseType.FILL_IN,
        prompt = "Complétez la phrase de Mia : J’___ à Rio.",
        feedback = "Avec ‘je’ devant une voyelle, on dit ‘j’habite’ : J’habite à Rio.",
        reviewKey = "fr:a1:grammar:habiter:first-person",
        acceptedAnswers = listOf("habite")
    ),
    LearningActivity(
        id = "fr-a1-residence-grammar-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Retrouver la même forme d’habiter dans un nouveau contexte de résidence",
        knowledgeTarget = "j’habite",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu parle de son logement : J’___ près du parc.",
        feedback = "La forme reste ‘habite’ après ‘j’ : J’habite près du parc.",
        reviewKey = "fr:a1:grammar:habiter:first-person",
        acceptedAnswers = listOf("habite")
    ),
    LearningActivity(
        id = "ko-a1-residence-grammar-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "사는 곳을 말할 때 기본적인 현재형 표현 사용하기",
        knowledgeTarget = "저는 살아요",
        responseType = ResponseType.FILL_IN,
        prompt = "미아의 말을 완성하세요: 저는 리우에 ___.",
        feedback = "사는 곳을 말할 때 ‘살아요’를 쓸 수 있어요: 저는 리우에 살아요.",
        reviewKey = "ko:a1:grammar:salda:polite-present",
        acceptedAnswers = listOf("살아요")
    ),
    LearningActivity(
        id = "ko-a1-residence-grammar-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "같은 현재형 표현을 새로운 거주 상황에서 떠올리기",
        knowledgeTarget = "저는 살아요",
        responseType = ResponseType.FILL_IN,
        prompt = "치우가 집에 대해 말해요: 저는 공원 근처에 ___.",
        feedback = "다른 장소를 말해도 같은 형태를 써요: 저는 공원 근처에 살아요.",
        reviewKey = "ko:a1:grammar:salda:polite-present",
        acceptedAnswers = listOf("살아요")
    )
)

fun a1ResidenceGrammarActivitiesFor(languageCode: String): List<LearningActivity> =
    a1ResidenceGrammarActivities.filter { it.id.startsWith("$languageCode-") }
