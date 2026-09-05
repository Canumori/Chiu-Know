package com.chiu.know.model

/**
 * Controlled A1 comprehension checks that require tracking who said a line in
 * the first narrative micro-unit. These activities stay outside the starter
 * review queue and do not create FSRS evidence or imply mastery.
 */
private val a1NarrativeSpeakerTrackingActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-speaker-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Identify who asks an explicit question in a short dialogue",
        knowledgeTarget = "speaker tracking in the first A1 narrative",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "In ‘Coffee and introductions’, who asks ‘Where do you live?’",
        feedback = "Mia asks Chiu ‘Where do you live?’",
        reviewKey = "en:a1:narrative:coffee:speaker-tracking",
        acceptedAnswers = listOf("Mia"),
        responseOptions = listOf("Mia", "Chiu")
    ),
    LearningActivity(
        id = "pt-a1-narrative-speaker-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Identificar quem faz uma pergunta explícita em um diálogo curto",
        knowledgeTarget = "rastreamento de interlocutor na primeira narrativa A1",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Em ‘Café e apresentações’, quem pergunta ‘Onde você mora?’",
        feedback = "Mia pergunta a Chiu ‘Onde você mora?’",
        reviewKey = "pt:a1:narrativa:cafe:interlocutor",
        acceptedAnswers = listOf("Mia"),
        responseOptions = listOf("Mia", "Chiu")
    ),
    LearningActivity(
        id = "es-a1-narrative-speaker-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Identificar quién hace una pregunta explícita en un diálogo breve",
        knowledgeTarget = "seguimiento del interlocutor en la primera narración A1",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "En ‘Café y presentaciones’, ¿quién pregunta ‘¿Dónde vives?’?",
        feedback = "Mia le pregunta a Chiu ‘¿Dónde vives?’",
        reviewKey = "es:a1:narrativa:cafe:interlocutor",
        acceptedAnswers = listOf("Mia"),
        responseOptions = listOf("Mia", "Chiu")
    ),
    LearningActivity(
        id = "fr-a1-narrative-speaker-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Identifier qui pose une question explicite dans un court dialogue",
        knowledgeTarget = "suivi du locuteur dans le premier récit A1",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Dans « Café et présentations », qui demande « Où est-ce que tu habites ? » ?",
        feedback = "Mia demande à Chiu « Où est-ce que tu habites ? »",
        reviewKey = "fr:a1:narration:cafe:locuteur",
        acceptedAnswers = listOf("Mia"),
        responseOptions = listOf("Mia", "Chiu")
    ),
    LearningActivity(
        id = "ko-a1-narrative-speaker-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "짧은 대화에서 누가 질문했는지 이해하기",
        knowledgeTarget = "첫 A1 이야기의 화자 구분",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "‘카페에서 처음 만나요’에서 ‘어디에 살아요?’라고 말한 사람은 누구예요?",
        feedback = "미아가 치우에게 ‘어디에 살아요?’라고 물어요.",
        reviewKey = "ko:a1:narrative:cafe:speaker-tracking",
        acceptedAnswers = listOf("미아"),
        responseOptions = listOf("미아", "치우")
    )
)

fun a1NarrativeSpeakerTrackingActivitiesFor(languageCode: String): List<LearningActivity> =
    a1NarrativeSpeakerTrackingActivities.filter { it.id.startsWith("$languageCode-") }
