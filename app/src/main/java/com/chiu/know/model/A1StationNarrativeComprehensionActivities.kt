package com.chiu.know.model

/**
 * Closed reading-comprehension check for the surreal station narrative.
 *
 * It checks recognition of Mia's comprehension-repair phrase in context.
 * This remains deterministic recognition outside starter sequencing and does
 * not create mastery, free-writing, speaking, or pronunciation evidence.
 */
private val a1StationNarrativeComprehensionActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-station-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recognize a comprehension-repair phrase in a location exchange",
        knowledgeTarget = "comprehension repair in the station narrative",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "At the station, what does Mia say when Chiu's direction is not clear?",
        feedback = "Mia says: ‘I don't understand.’",
        reviewKey = "en:a1:narrative:station:comprehension-repair",
        acceptedAnswers = listOf("I don't understand."),
        responseOptions = listOf("I don't understand.", "Where is the restroom?")
    ),
    LearningActivity(
        id = "pt-a1-narrative-estacao-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconhecer uma frase de reparo de compreensão em uma conversa de localização",
        knowledgeTarget = "reparo de compreensão na narrativa da estação",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Na estação, o que Mia diz quando a indicação de Chiu não fica clara?",
        feedback = "Mia diz: ‘Eu não entendo.’",
        reviewKey = "pt:a1:narrativa:estacao:reparo-compreensao",
        acceptedAnswers = listOf("Eu não entendo."),
        responseOptions = listOf("Eu não entendo.", "Onde fica o banheiro?")
    ),
    LearningActivity(
        id = "es-a1-narrative-estacion-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconocer una frase de reparación de comprensión en un intercambio de ubicación",
        knowledgeTarget = "reparación de comprensión en la narrativa de la estación",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "En la estación, ¿qué dice Mia cuando la indicación de Chiu no está clara?",
        feedback = "Mia dice: No entiendo.",
        reviewKey = "es:a1:narrativa:estacion:reparacion-comprension",
        acceptedAnswers = listOf("No entiendo."),
        responseOptions = listOf("No entiendo.", "¿Dónde está el baño?")
    ),
    LearningActivity(
        id = "fr-a1-narrative-gare-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Reconnaître une phrase de réparation de compréhension dans un échange de localisation",
        knowledgeTarget = "réparation de compréhension dans le récit de la gare",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "À la gare, que dit Mia quand l’indication de Chiu n’est pas claire ?",
        feedback = "Mia dit : Je ne comprends pas.",
        reviewKey = "fr:a1:narration:gare:reparation-comprehension",
        acceptedAnswers = listOf("Je ne comprends pas."),
        responseOptions = listOf("Je ne comprends pas.", "Où sont les toilettes ?")
    ),
    LearningActivity(
        id = "ko-a1-narrative-station-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "위치 안내 대화에서 이해하지 못했음을 알리는 표현 알아보기",
        knowledgeTarget = "역 이야기의 이해 확인 표현",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "역에서 치우의 설명이 분명하지 않을 때 미아는 뭐라고 말해요?",
        feedback = "미아는 ‘이해가 안 돼요.’라고 말해요.",
        reviewKey = "ko:a1:narrative:station:comprehension-repair",
        acceptedAnswers = listOf("이해가 안 돼요."),
        responseOptions = listOf("이해가 안 돼요.", "화장실이 어디예요?")
    )
)

fun a1StationNarrativeComprehensionActivitiesFor(languageCode: String): List<LearningActivity> =
    a1StationNarrativeComprehensionActivities.filter { it.id.startsWith("$languageCode-") }
