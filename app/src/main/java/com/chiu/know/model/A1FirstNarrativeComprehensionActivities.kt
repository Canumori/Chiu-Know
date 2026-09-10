package com.chiu.know.model

/**
 * First controlled comprehension checks tied to the A1 narrative micro-unit.
 *
 * These activities remain outside starterLearningActivitiesFor so they do not
 * automatically enter the review queue or create FSRS scheduling evidence.
 * They check explicit facts from the story and do not imply mastery.
 */
private val a1FirstNarrativeComprehensionActivities = listOf(
    LearningActivity(
        id = "en-a1-narrative-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Understand one explicit preference stated in the short narrative",
        knowledgeTarget = "explicit preference in the first A1 narrative",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "After reading ‘Coffee and introductions’, what does Chiu like?",
        feedback = "In the story, Chiu says ‘I like coffee.’",
        reviewKey = "en:a1:narrative:coffee:explicit-preference",
        acceptedAnswers = listOf("coffee"),
        responseOptions = listOf("coffee", "Rio")
    ),
    LearningActivity(
        id = "en-a1-narrative-comprehension-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Understand one explicit residence fact stated in the short narrative",
        knowledgeTarget = "explicit residence in the first A1 narrative",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "After reading ‘Coffee and introductions’, where does Chiu live?",
        feedback = "In the story, Chiu says ‘I live in Rio.’",
        reviewKey = "en:a1:narrative:coffee:explicit-residence",
        acceptedAnswers = listOf("Rio"),
        responseOptions = listOf("Rio", "coffee")
    ),
    LearningActivity(
        id = "pt-a1-narrative-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Compreender uma preferência explícita apresentada na narrativa curta",
        knowledgeTarget = "preferência explícita na primeira narrativa A1",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Depois de ler ‘Café e apresentações’, do que Chiu gosta?",
        feedback = "Na história, Chiu diz ‘Eu gosto de café.’",
        reviewKey = "pt:a1:narrativa:cafe:preferencia-explicita",
        acceptedAnswers = listOf("café"),
        responseOptions = listOf("café", "Rio")
    ),
    LearningActivity(
        id = "pt-a1-narrative-comprehension-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Compreender uma informação explícita de residência apresentada na narrativa curta",
        knowledgeTarget = "residência explícita na primeira narrativa A1",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Depois de ler ‘Café e apresentações’, onde Chiu mora?",
        feedback = "Na história, Chiu diz ‘Eu moro no Rio.’",
        reviewKey = "pt:a1:narrativa:cafe:residencia-explicita",
        acceptedAnswers = listOf("Rio"),
        responseOptions = listOf("Rio", "café")
    ),
    LearningActivity(
        id = "es-a1-narrative-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Comprender una preferencia explícita presentada en la narración breve",
        knowledgeTarget = "preferencia explícita en la primera narración A1",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Después de leer ‘Café y presentaciones’, ¿qué le gusta a Chiu?",
        feedback = "En la historia, Chiu dice ‘Me gusta el café.’",
        reviewKey = "es:a1:narrativa:cafe:preferencia-explicita",
        acceptedAnswers = listOf("café"),
        responseOptions = listOf("café", "Río")
    ),
    LearningActivity(
        id = "es-a1-narrative-comprehension-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Comprender un dato explícito de residencia presentado en la narración breve",
        knowledgeTarget = "residencia explícita en la primera narración A1",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Después de leer ‘Café y presentaciones’, ¿dónde vive Chiu?",
        feedback = "En la historia, Chiu dice ‘Vivo en Río.’",
        reviewKey = "es:a1:narrativa:cafe:residencia-explicita",
        acceptedAnswers = listOf("Río"),
        responseOptions = listOf("Río", "café")
    ),
    LearningActivity(
        id = "fr-a1-narrative-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Comprendre une préférence explicite présentée dans le court récit",
        knowledgeTarget = "préférence explicite dans le premier récit A1",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Après avoir lu « Café et présentations », qu’est-ce que Chiu aime ?",
        feedback = "Dans l’histoire, Chiu dit « J’aime le café. »",
        reviewKey = "fr:a1:narration:cafe:preference-explicite",
        acceptedAnswers = listOf("café"),
        responseOptions = listOf("café", "Rio")
    ),
    LearningActivity(
        id = "fr-a1-narrative-comprehension-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Comprendre une information explicite de résidence présentée dans le court récit",
        knowledgeTarget = "résidence explicite dans le premier récit A1",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "Après avoir lu « Café et présentations », où habite Chiu ?",
        feedback = "Dans l’histoire, Chiu dit « J’habite à Rio. »",
        reviewKey = "fr:a1:narration:cafe:residence-explicite",
        acceptedAnswers = listOf("Rio"),
        responseOptions = listOf("Rio", "café")
    ),
    LearningActivity(
        id = "ko-a1-narrative-comprehension-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "짧은 이야기에서 명시적으로 나온 선호 정보 이해하기",
        knowledgeTarget = "첫 A1 이야기의 명시적인 선호 정보",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "‘카페에서 처음 만나요’를 읽은 뒤 답하세요. 치우는 무엇을 좋아해요?",
        feedback = "이야기에서 치우는 ‘커피를 좋아해요.’라고 말합니다.",
        reviewKey = "ko:a1:narrative:cafe:explicit-preference",
        acceptedAnswers = listOf("커피"),
        responseOptions = listOf("커피", "리우")
    ),
    LearningActivity(
        id = "ko-a1-narrative-comprehension-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "짧은 이야기에서 명시적으로 나온 거주지 정보 이해하기",
        knowledgeTarget = "첫 A1 이야기의 명시적인 거주지 정보",
        responseType = ResponseType.MULTIPLE_CHOICE,
        prompt = "‘카페에서 처음 만나요’를 읽은 뒤 답하세요. 치우는 어디에 살아요?",
        feedback = "이야기에서 치우는 ‘리우에 살아요.’라고 말합니다.",
        reviewKey = "ko:a1:narrative:cafe:explicit-residence",
        acceptedAnswers = listOf("리우"),
        responseOptions = listOf("리우", "커피")
    )
)

fun a1FirstNarrativeComprehensionActivitiesFor(languageCode: String): List<LearningActivity> =
    a1FirstNarrativeComprehensionActivities.filter { it.id.startsWith("$languageCode-") }
