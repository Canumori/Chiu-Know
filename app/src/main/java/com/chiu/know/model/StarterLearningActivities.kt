package com.chiu.know.model

private val starterLearningActivities = listOf(
    LearningActivity(
        id = "en-a1-greeting-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Retrieve a basic neutral greeting in context",
        knowledgeTarget = "hello",
        responseType = ResponseType.FILL_IN,
        prompt = "Complete the greeting: ___! Nice to meet you.",
        feedback = "‘Hello’ is a common neutral greeting.",
        reviewKey = "en:a1:greeting:hello",
        acceptedAnswers = listOf("hello")
    ),
    LearningActivity(
        id = "en-a1-greeting-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Retrieve the same greeting in a new social context",
        knowledgeTarget = "hello",
        responseType = ResponseType.FILL_IN,
        prompt = "Mia meets a new neighbor. Complete what she says: ___! I’m Mia.",
        feedback = "The same greeting can be retrieved in a different social situation.",
        reviewKey = "en:a1:greeting:hello",
        acceptedAnswers = listOf("hello")
    ),
    LearningActivity(
        id = "pt-a1-greeting-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Recuperar uma saudação básica e neutra em contexto",
        knowledgeTarget = "olá",
        responseType = ResponseType.FILL_IN,
        prompt = "Complete a saudação: ___! Prazer em conhecer você.",
        feedback = "‘Olá’ é uma saudação neutra e comum.",
        reviewKey = "pt:a1:greeting:ola",
        acceptedAnswers = listOf("olá")
    ),
    LearningActivity(
        id = "pt-a1-greeting-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Recuperar a mesma saudação em um novo contexto social",
        knowledgeTarget = "olá",
        responseType = ResponseType.FILL_IN,
        prompt = "Mia conhece uma nova vizinha. Complete o que ela diz: ___! Eu sou a Mia.",
        feedback = "A mesma saudação pode ser recuperada em uma situação social diferente.",
        reviewKey = "pt:a1:greeting:ola",
        acceptedAnswers = listOf("olá")
    ),
    LearningActivity(
        id = "es-a1-greeting-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Recuperar un saludo básico y neutro en contexto",
        knowledgeTarget = "hola",
        responseType = ResponseType.FILL_IN,
        prompt = "Completa el saludo: ___! Mucho gusto.",
        feedback = "‘Hola’ es un saludo neutro y común.",
        reviewKey = "es:a1:greeting:hola",
        acceptedAnswers = listOf("hola")
    ),
    LearningActivity(
        id = "es-a1-greeting-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Recuperar el mismo saludo en un nuevo contexto social",
        knowledgeTarget = "hola",
        responseType = ResponseType.FILL_IN,
        prompt = "Mia conoce a una nueva vecina. Completa lo que dice: ___! Soy Mia.",
        feedback = "El mismo saludo puede recuperarse en una situación social diferente.",
        reviewKey = "es:a1:greeting:hola",
        acceptedAnswers = listOf("hola")
    ),
    LearningActivity(
        id = "fr-a1-greeting-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Récupérer une salutation neutre de base en contexte",
        knowledgeTarget = "bonjour",
        responseType = ResponseType.FILL_IN,
        prompt = "Complétez la salutation : ___ ! Enchanté(e).",
        feedback = "‘Bonjour’ est une salutation neutre et courante.",
        reviewKey = "fr:a1:greeting:bonjour",
        acceptedAnswers = listOf("bonjour")
    ),
    LearningActivity(
        id = "fr-a1-greeting-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Récupérer la même salutation dans un nouveau contexte social",
        knowledgeTarget = "bonjour",
        responseType = ResponseType.FILL_IN,
        prompt = "Mia rencontre une nouvelle voisine. Complétez ce qu’elle dit : ___ ! Je suis Mia.",
        feedback = "La même salutation peut être récupérée dans une situation sociale différente.",
        reviewKey = "fr:a1:greeting:bonjour",
        acceptedAnswers = listOf("bonjour")
    ),
    LearningActivity(
        id = "ko-a1-greeting-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "기본적인 공손한 인사말을 문맥에서 떠올리기",
        knowledgeTarget = "안녕하세요",
        responseType = ResponseType.FILL_IN,
        prompt = "인사말을 완성하세요: ___! 만나서 반가워요.",
        feedback = "‘안녕하세요’는 기본적이고 공손한 인사말입니다.",
        reviewKey = "ko:a1:greeting:annyeonghaseyo",
        acceptedAnswers = listOf("안녕하세요")
    ),
    LearningActivity(
        id = "ko-a1-greeting-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "같은 인사말을 새로운 사회적 상황에서 떠올리기",
        knowledgeTarget = "안녕하세요",
        responseType = ResponseType.FILL_IN,
        prompt = "미아가 새 이웃을 만났어요. 미아의 말을 완성하세요: ___! 저는 미아예요.",
        feedback = "같은 인사말을 다른 사회적 상황에서도 떠올릴 수 있어야 합니다.",
        reviewKey = "ko:a1:greeting:annyeonghaseyo",
        acceptedAnswers = listOf("안녕하세요")
    )
)

fun starterLearningActivitiesFor(languageCode: String): List<LearningActivity> =
    starterLearningActivities.filter { it.id.startsWith("$languageCode-") } +
        a1IntegratedGrammarActivitiesFor(languageCode) +
        a1ReadingActivitiesFor(languageCode) +
        a1ReorderActivitiesFor(languageCode) +
        a1MultipleChoiceActivitiesFor(languageCode)

fun starterLearningActivityFor(
    languageCode: String,
    level: CefrLevel,
    priorAttemptCount: Int = 0
): LearningActivity? {
    val candidates = starterLearningActivitiesFor(languageCode).filter { it.level == level }
    if (candidates.isEmpty()) return null
    return candidates[Math.floorMod(priorAttemptCount, candidates.size)]
}
