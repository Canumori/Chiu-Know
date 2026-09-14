package com.chiu.know.model

/**
 * Reduced-cue retrieval for the restroom-location question from the station.
 *
 * One essential element is removed from the established phrase. The learner
 * must retrieve it without receiving a word bank. This remains deterministic
 * FILL_IN practice, not free writing, speaking, pronunciation, or listening.
 */
private val a1StationLocationFillInRetrievalActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-station-location-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Retrieve the essential place word in the station location question",
        knowledgeTarget = "Where is the restroom?",
        responseType = ResponseType.FILL_IN,
        prompt = "At the station, complete Mia’s question: Where is the ___?",
        feedback = "The complete question is: Where is the restroom?",
        reviewKey = "en:a1:vocabulary:basic-location:restroom",
        acceptedAnswers = listOf("restroom")
    ),
    LearningActivity(
        id = "pt-a1-narrative-estacao-localizacao-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Recuperar a palavra essencial de lugar na pergunta da estação",
        knowledgeTarget = "Onde fica o banheiro?",
        responseType = ResponseType.FILL_IN,
        prompt = "Na estação, complete a pergunta de Mia: Onde fica o ___?",
        feedback = "A pergunta completa é: Onde fica o banheiro?",
        reviewKey = "pt:a1:vocabulary:basic-location:banheiro",
        acceptedAnswers = listOf("banheiro")
    ),
    LearningActivity(
        id = "es-a1-narrative-estacion-ubicacion-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Recuperar la palabra esencial de lugar en la pregunta de la estación",
        knowledgeTarget = "¿Dónde está el baño?",
        responseType = ResponseType.FILL_IN,
        prompt = "En la estación, completa la pregunta de Mia: ¿Dónde está el ___?",
        feedback = "La pregunta completa es: ¿Dónde está el baño?",
        reviewKey = "es:a1:vocabulary:basic-location:bano",
        acceptedAnswers = listOf("baño")
    ),
    LearningActivity(
        id = "fr-a1-narrative-gare-localisation-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Retrouver le mot essentiel de lieu dans la question à la gare",
        knowledgeTarget = "Où sont les toilettes ?",
        responseType = ResponseType.FILL_IN,
        prompt = "À la gare, complète la question de Mia : Où sont les ___ ?",
        feedback = "La question complète est : Où sont les toilettes ?",
        reviewKey = "fr:a1:vocabulary:basic-location:toilettes",
        acceptedAnswers = listOf("toilettes")
    ),
    LearningActivity(
        id = "ko-a1-narrative-station-location-fill-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "역에서 화장실 위치를 묻는 표현의 핵심 부분 떠올리기",
        knowledgeTarget = "화장실이 어디예요?",
        responseType = ResponseType.FILL_IN,
        prompt = "역에서 미아의 질문을 완성하세요: 화장실이 ___?",
        feedback = "완전한 질문은 ‘화장실이 어디예요?’예요.",
        reviewKey = "ko:a1:vocabulary:basic-location:restroom",
        acceptedAnswers = listOf("어디예요")
    )
)

fun a1StationLocationFillInRetrievalActivitiesFor(languageCode: String): List<LearningActivity> =
    a1StationLocationFillInRetrievalActivities.filter { it.id.startsWith("$languageCode-") }
