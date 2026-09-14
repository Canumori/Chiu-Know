package com.chiu.know.model

/**
 * Controlled A1 reading checks that require understanding the order of turns in
 * the first narrative micro-unit. These activities remain outside the starter
 * review queue, create no FSRS evidence and do not imply mastery or speaking.
 */
private val a1NarrativeSequenceActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-sequence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Understand what question follows an explicit answer in a short dialogue",
        knowledgeTarget = "turn sequence in the first A1 narrative",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "In ‘Coffee and introductions’, after Chiu says ‘I live in Rio.’, what does Mia ask next?",
        feedback = "After Chiu says where he lives, Mia asks ‘What do you like?’",
        reviewKey = "en:a1:narrative:coffee:turn-sequence",
        acceptedAnswers = listOf("What do you like?"),
        responseOptions = listOf("What do you like?", "What is your name?")
    ),
    LearningActivity(
        id = "pt-a1-narrative-sequence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Compreender qual pergunta vem após uma resposta explícita em um diálogo curto",
        knowledgeTarget = "sequência de turnos na primeira narrativa A1",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Em ‘Café e apresentações’, depois que Chiu diz ‘Eu moro no Rio.’, o que Mia pergunta em seguida?",
        feedback = "Depois que Chiu diz onde mora, Mia pergunta ‘Do que você gosta?’",
        reviewKey = "pt:a1:narrativa:cafe:sequencia-turnos",
        acceptedAnswers = listOf("Do que você gosta?"),
        responseOptions = listOf("Do que você gosta?", "Qual é o seu nome?")
    ),
    LearningActivity(
        id = "es-a1-narrative-sequence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Comprender qué pregunta sigue a una respuesta explícita en un diálogo breve",
        knowledgeTarget = "secuencia de turnos en la primera narración A1",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "En ‘Café y presentaciones’, después de que Chiu dice ‘Vivo en Río.’, ¿qué pregunta Mia a continuación?",
        feedback = "Después de que Chiu dice dónde vive, Mia pregunta ‘¿Qué te gusta?’",
        reviewKey = "es:a1:narrativa:cafe:secuencia-turnos",
        acceptedAnswers = listOf("¿Qué te gusta?"),
        responseOptions = listOf("¿Qué te gusta?", "¿Cómo te llamas?")
    ),
    LearningActivity(
        id = "fr-a1-narrative-sequence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Comprendre quelle question suit une réponse explicite dans un court dialogue",
        knowledgeTarget = "séquence des tours de parole dans le premier récit A1",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Dans « Café et présentations », après que Chiu dit « J’habite à Rio. », que demande Mia ensuite ?",
        feedback = "Après que Chiu dit où il habite, Mia demande « Qu’est-ce que tu aimes ? »",
        reviewKey = "fr:a1:narration:cafe:sequence-tours",
        acceptedAnswers = listOf("Qu’est-ce que tu aimes ?"),
        responseOptions = listOf("Qu’est-ce que tu aimes ?", "Comment tu t’appelles ?")
    ),
    LearningActivity(
        id = "ko-a1-narrative-sequence-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "짧은 대화에서 다음에 이어지는 질문 이해하기",
        knowledgeTarget = "첫 A1 이야기의 대화 순서",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "‘카페에서 처음 만나요’에서 치우가 ‘리우에 살아요.’라고 말한 다음, 미아는 무엇을 물어요?",
        feedback = "치우가 사는 곳을 말한 다음, 미아는 ‘무엇을 좋아해요?’라고 물어요.",
        reviewKey = "ko:a1:narrative:cafe:turn-sequence",
        acceptedAnswers = listOf("무엇을 좋아해요?"),
        responseOptions = listOf("무엇을 좋아해요?", "이름이 뭐예요?")
    )
)

fun a1NarrativeSequenceActivitiesFor(languageCode: String): List<LearningActivity> =
    a1NarrativeSequenceActivities.filter { it.id.startsWith("$languageCode-") }
