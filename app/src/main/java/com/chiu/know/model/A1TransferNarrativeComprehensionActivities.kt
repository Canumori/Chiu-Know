package com.chiu.know.model

/**
 * Controlled A1 reading checks for the second narrative micro-unit.
 *
 * These checks verify transfer: the learner must understand familiar A1
 * functions after they are recombined in a new park context. They remain
 * outside the starter review queue, create no FSRS evidence and do not imply
 * mastery, free conversation, speaking or pronunciation assessment.
 */
private val a1TransferNarrativeComprehensionActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-transfer-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Understand a familiar preference exchange in a new short dialogue",
        knowledgeTarget = "transfer comprehension in the second A1 narrative",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "In ‘A meeting in the park’, who says ‘I like books.’?",
        feedback = "Mia answers Chiu’s question by saying ‘I like books.’",
        reviewKey = "en:a1:narrative:park:transfer-comprehension",
        acceptedAnswers = listOf("Mia"),
        responseOptions = listOf("Mia", "Chiu")
    ),
    LearningActivity(
        id = "pt-a1-narrative-transfer-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Compreender uma troca de preferência conhecida em um novo diálogo curto",
        knowledgeTarget = "compreensão por transferência na segunda narrativa A1",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Em ‘Um encontro no parque’, quem diz ‘Eu gosto de livros.’?",
        feedback = "Mia responde à pergunta de Chiu dizendo ‘Eu gosto de livros.’",
        reviewKey = "pt:a1:narrativa:parque:compreensao-transferencia",
        acceptedAnswers = listOf("Mia"),
        responseOptions = listOf("Mia", "Chiu")
    ),
    LearningActivity(
        id = "es-a1-narrative-transfer-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Comprender un intercambio conocido de preferencias en un nuevo diálogo breve",
        knowledgeTarget = "comprensión por transferencia en la segunda narración A1",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "En ‘Un encuentro en el parque’, ¿quién dice ‘Me gustan los libros.’?",
        feedback = "Mia responde a la pregunta de Chiu diciendo ‘Me gustan los libros.’",
        reviewKey = "es:a1:narrativa:parque:comprension-transferencia",
        acceptedAnswers = listOf("Mia"),
        responseOptions = listOf("Mia", "Chiu")
    ),
    LearningActivity(
        id = "fr-a1-narrative-transfer-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Comprendre un échange connu sur les préférences dans un nouveau court dialogue",
        knowledgeTarget = "compréhension par transfert dans le deuxième récit A1",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Dans « Une rencontre au parc », qui dit « J’aime les livres. » ?",
        feedback = "Mia répond à la question de Chiu en disant « J’aime les livres. »",
        reviewKey = "fr:a1:narration:parc:comprehension-transfert",
        acceptedAnswers = listOf("Mia"),
        responseOptions = listOf("Mia", "Chiu")
    ),
    LearningActivity(
        id = "ko-a1-narrative-transfer-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "새로운 짧은 대화에서 익숙한 선호 표현 이해하기",
        knowledgeTarget = "두 번째 A1 이야기의 전이 이해",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "‘공원에서 다시 만나요’에서 ‘책을 좋아해요.’라고 말한 사람은 누구예요?",
        feedback = "치우가 무엇을 좋아하는지 묻고, 미아가 ‘책을 좋아해요.’라고 대답해요.",
        reviewKey = "ko:a1:narrative:park:transfer-comprehension",
        acceptedAnswers = listOf("미아"),
        responseOptions = listOf("미아", "치우")
    )
)

fun a1TransferNarrativeComprehensionActivitiesFor(languageCode: String): List<LearningActivity> =
    a1TransferNarrativeComprehensionActivities.filter { it.id.startsWith("$languageCode-") }
