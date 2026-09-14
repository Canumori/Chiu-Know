package com.chiu.know.model

/**
 * Small controlled A1 slice for asking where the restroom is.
 *
 * These activities use REORDER to require active reconstruction of a short,
 * useful location question without claiming open writing, speaking or broader
 * conversational mastery.
 *
 * This file remains isolated from the starter bank until content and tests are
 * green on their own.
 */
private val a1BasicLocationActivities = listOf(
    LearningActivity(
        id = "en-a1-basic-location-restroom-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Build a basic question to ask where the restroom is",
        knowledgeTarget = "Where is the restroom?",
        responseType = ResponseType.REORDER,
        prompt = "Mia needs the restroom. Put the words in order to ask where it is.",
        feedback = "A natural basic location question is: Where is the restroom?",
        reviewKey = "en:a1:vocabulary:basic-location:restroom",
        acceptedAnswers = listOf("Where is the restroom?"),
        responseOptions = listOf("Where", "is", "the", "restroom?")
    ),
    LearningActivity(
        id = "pt-a1-basic-location-restroom-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Montar uma pergunta básica para saber onde fica o banheiro",
        knowledgeTarget = "Onde fica o banheiro?",
        responseType = ResponseType.REORDER,
        prompt = "Mia precisa ir ao banheiro. Coloque as palavras em ordem para perguntar onde ele fica.",
        feedback = "Uma pergunta básica e natural de localização é: Onde fica o banheiro?",
        reviewKey = "pt:a1:vocabulary:basic-location:banheiro",
        acceptedAnswers = listOf("Onde fica o banheiro?"),
        responseOptions = listOf("Onde", "fica", "o", "banheiro?")
    ),
    LearningActivity(
        id = "es-a1-basic-location-restroom-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Construir una pregunta básica para saber dónde está el baño",
        knowledgeTarget = "¿Dónde está el baño?",
        responseType = ResponseType.REORDER,
        prompt = "Mia necesita ir al baño. Ordena las palabras para preguntar dónde está.",
        feedback = "Una pregunta básica y natural de ubicación es: ¿Dónde está el baño?",
        reviewKey = "es:a1:vocabulary:basic-location:bano",
        acceptedAnswers = listOf("¿Dónde está el baño?"),
        responseOptions = listOf("¿Dónde", "está", "el", "baño?")
    ),
    LearningActivity(
        id = "fr-a1-basic-location-restroom-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Construire une question simple pour demander où sont les toilettes",
        knowledgeTarget = "Où sont les toilettes ?",
        responseType = ResponseType.REORDER,
        prompt = "Mia cherche les toilettes. Remettez les mots dans l’ordre pour demander où elles se trouvent.",
        feedback = "Une question simple et naturelle est : Où sont les toilettes ?",
        reviewKey = "fr:a1:vocabulary:basic-location:toilettes",
        acceptedAnswers = listOf("Où sont les toilettes ?"),
        responseOptions = listOf("Où", "sont", "les", "toilettes", "?")
    ),
    LearningActivity(
        id = "ko-a1-basic-location-restroom-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "화장실의 위치를 묻는 기본 표현을 만들기",
        knowledgeTarget = "화장실이 어디예요?",
        responseType = ResponseType.REORDER,
        prompt = "미아가 화장실을 찾고 있어요. 위치를 묻는 표현이 되도록 순서대로 고르세요.",
        feedback = "화장실의 위치를 물을 때 ‘화장실이 어디예요?’라고 할 수 있어요.",
        reviewKey = "ko:a1:vocabulary:basic-location:restroom",
        acceptedAnswers = listOf("화장실이 어디예요?"),
        responseOptions = listOf("화장실이", "어디예요?")
    )
)

fun a1BasicLocationActivitiesFor(languageCode: String): List<LearningActivity> =
    a1BasicLocationActivities.filter { it.id.startsWith("$languageCode-") }
