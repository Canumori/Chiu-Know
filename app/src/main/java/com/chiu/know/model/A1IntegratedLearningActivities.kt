package com.chiu.know.model

/**
 * Second controlled A1 slice: one small grammar target per supported language,
 * repeated across two social contexts to support retrieval and transfer.
 */
private val a1IntegratedGrammarActivities = listOf(
    LearningActivity(
        id = "en-a1-copula-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Use the first-person form of be for a basic self-introduction",
        knowledgeTarget = "I am",
        responseType = ResponseType.FILL_IN,
        prompt = "Complete Mia’s introduction: I ___ Mia.",
        feedback = "Use ‘am’ with ‘I’: I am Mia.",
        reviewKey = "en:a1:grammar:copula:first-person",
        acceptedAnswers = listOf("am")
    ),
    LearningActivity(
        id = "en-a1-copula-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Retrieve the same first-person copula in a new social context",
        knowledgeTarget = "I am",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu introduces himself to a neighbor: I ___ Chiu.",
        feedback = "The form stays ‘am’ after ‘I’, even in a different situation.",
        reviewKey = "en:a1:grammar:copula:first-person",
        acceptedAnswers = listOf("am")
    ),
    LearningActivity(
        id = "pt-a1-copula-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Usar a primeira pessoa do verbo ser em uma apresentação básica",
        knowledgeTarget = "eu sou",
        responseType = ResponseType.FILL_IN,
        prompt = "Complete a apresentação da Mia: Eu ___ a Mia.",
        feedback = "Com ‘eu’, usamos ‘sou’: Eu sou a Mia.",
        reviewKey = "pt:a1:grammar:ser:first-person",
        acceptedAnswers = listOf("sou")
    ),
    LearningActivity(
        id = "pt-a1-copula-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Recuperar a mesma forma do verbo ser em outro contexto social",
        knowledgeTarget = "eu sou",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu se apresenta para um vizinho: Eu ___ o Chiu.",
        feedback = "A forma continua ‘sou’ depois de ‘eu’, mesmo em outra situação.",
        reviewKey = "pt:a1:grammar:ser:first-person",
        acceptedAnswers = listOf("sou")
    ),
    LearningActivity(
        id = "es-a1-copula-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Usar la primera persona de ser en una presentación básica",
        knowledgeTarget = "yo soy",
        responseType = ResponseType.FILL_IN,
        prompt = "Completa la presentación de Mia: Yo ___ Mia.",
        feedback = "Con ‘yo’, usamos ‘soy’: Yo soy Mia.",
        reviewKey = "es:a1:grammar:ser:first-person",
        acceptedAnswers = listOf("soy")
    ),
    LearningActivity(
        id = "es-a1-copula-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Recuperar la misma forma de ser en otro contexto social",
        knowledgeTarget = "yo soy",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu se presenta a un vecino: Yo ___ Chiu.",
        feedback = "La forma sigue siendo ‘soy’ después de ‘yo’, incluso en otra situación.",
        reviewKey = "es:a1:grammar:ser:first-person",
        acceptedAnswers = listOf("soy")
    ),
    LearningActivity(
        id = "fr-a1-copula-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Utiliser la première personne du verbe être pour se présenter",
        knowledgeTarget = "je suis",
        responseType = ResponseType.FILL_IN,
        prompt = "Complétez la présentation de Mia : Je ___ Mia.",
        feedback = "Avec ‘je’, on utilise ‘suis’ : Je suis Mia.",
        reviewKey = "fr:a1:grammar:etre:first-person",
        acceptedAnswers = listOf("suis")
    ),
    LearningActivity(
        id = "fr-a1-copula-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Récupérer la même forme du verbe être dans un autre contexte social",
        knowledgeTarget = "je suis",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu se présente à un voisin : Je ___ Chiu.",
        feedback = "La forme reste ‘suis’ après ‘je’, même dans une autre situation.",
        reviewKey = "fr:a1:grammar:etre:first-person",
        acceptedAnswers = listOf("suis")
    ),
    LearningActivity(
        id = "ko-a1-copula-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "기본 자기소개에서 공손한 서술격 표현을 사용하기",
        knowledgeTarget = "저는 미아예요",
        responseType = ResponseType.FILL_IN,
        prompt = "미아의 자기소개를 완성하세요: 저는 미아___.",
        feedback = "받침이 없는 이름 ‘미아’ 뒤에는 ‘예요’를 쓸 수 있어요: 저는 미아예요.",
        reviewKey = "ko:a1:grammar:copula:polite-introduction",
        acceptedAnswers = listOf("예요")
    ),
    LearningActivity(
        id = "ko-a1-copula-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "같은 공손한 서술격 표현을 다른 자기소개 상황에서 떠올리기",
        knowledgeTarget = "저는 치우예요",
        responseType = ResponseType.FILL_IN,
        prompt = "치우가 이웃에게 자기소개해요: 저는 치우___.",
        feedback = "받침이 없는 이름 ‘치우’ 뒤에도 ‘예요’를 써요: 저는 치우예요.",
        reviewKey = "ko:a1:grammar:copula:polite-introduction",
        acceptedAnswers = listOf("예요")
    )
)

fun a1IntegratedGrammarActivitiesFor(languageCode: String): List<LearningActivity> =
    a1IntegratedGrammarActivities.filter { it.id.startsWith("$languageCode-") }
