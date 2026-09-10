package com.chiu.know.model

/**
 * Third controlled A1 reading target: identify an explicit preference in a
 * very short text. Two contexts share one reviewKey per language so the target
 * is the comprehension skill, not memorization of one sentence.
 */
private val a1PreferenceReadingActivities = listOf(
    LearningActivity(
        id = "en-a1-reading-preference-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Understand an explicit simple preference in a very short text",
        knowledgeTarget = "explicit preference in a short text",
        responseType = ResponseType.FILL_IN,
        prompt = "Read: ‘Mia likes tea. Chiu likes coffee.’ What does Mia like?",
        feedback = "The text says ‘Mia likes tea’, so the answer is tea.",
        reviewKey = "en:a1:reading:explicit-preference",
        acceptedAnswers = listOf("tea")
    ),
    LearningActivity(
        id = "en-a1-reading-preference-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Find the same kind of explicit preference in a new short context",
        knowledgeTarget = "explicit preference in a short text",
        responseType = ResponseType.FILL_IN,
        prompt = "Read: ‘Chiu likes apples. Mia likes bananas.’ What does Chiu like?",
        feedback = "The text says ‘Chiu likes apples’, so the answer is apples.",
        reviewKey = "en:a1:reading:explicit-preference",
        acceptedAnswers = listOf("apples", "apple")
    ),
    LearningActivity(
        id = "pt-a1-reading-preference-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Compreender uma preferência simples e explícita em um texto muito curto",
        knowledgeTarget = "preferência explícita em texto curto",
        responseType = ResponseType.FILL_IN,
        prompt = "Leia: ‘A Mia gosta de chá. O Chiu gosta de café.’ Do que a Mia gosta?",
        feedback = "O texto diz ‘A Mia gosta de chá’, então a resposta é chá.",
        reviewKey = "pt:a1:reading:preferencia-explicita",
        acceptedAnswers = listOf("chá")
    ),
    LearningActivity(
        id = "pt-a1-reading-preference-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Encontrar o mesmo tipo de preferência explícita em outro contexto curto",
        knowledgeTarget = "preferência explícita em texto curto",
        responseType = ResponseType.FILL_IN,
        prompt = "Leia: ‘O Chiu gosta de maçãs. A Mia gosta de bananas.’ Do que o Chiu gosta?",
        feedback = "O texto diz ‘O Chiu gosta de maçãs’, então a resposta é maçãs.",
        reviewKey = "pt:a1:reading:preferencia-explicita",
        acceptedAnswers = listOf("maçãs", "maçã")
    ),
    LearningActivity(
        id = "es-a1-reading-preference-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Comprender una preferencia simple y explícita en un texto muy breve",
        knowledgeTarget = "preferencia explícita en un texto breve",
        responseType = ResponseType.FILL_IN,
        prompt = "Lee: ‘A Mia le gusta el té. A Chiu le gusta el café.’ ¿Qué le gusta a Mia?",
        feedback = "El texto dice ‘A Mia le gusta el té’, así que la respuesta es té.",
        reviewKey = "es:a1:reading:preferencia-explicita",
        acceptedAnswers = listOf("té")
    ),
    LearningActivity(
        id = "es-a1-reading-preference-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Encontrar el mismo tipo de preferencia explícita en otro contexto breve",
        knowledgeTarget = "preferencia explícita en un texto breve",
        responseType = ResponseType.FILL_IN,
        prompt = "Lee: ‘A Chiu le gustan las manzanas. A Mia le gustan los plátanos.’ ¿Qué le gusta a Chiu?",
        feedback = "El texto dice ‘A Chiu le gustan las manzanas’, así que la respuesta es manzanas.",
        reviewKey = "es:a1:reading:preferencia-explicita",
        acceptedAnswers = listOf("manzanas", "manzana")
    ),
    LearningActivity(
        id = "fr-a1-reading-preference-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Comprendre une préférence simple et explicite dans un texte très court",
        knowledgeTarget = "préférence explicite dans un texte court",
        responseType = ResponseType.FILL_IN,
        prompt = "Lisez : « Mia aime le thé. Chiu aime le café. » Qu’est-ce que Mia aime ?",
        feedback = "Le texte dit « Mia aime le thé » : la réponse est donc le thé.",
        reviewKey = "fr:a1:reading:preference-explicite",
        acceptedAnswers = listOf("le thé", "thé")
    ),
    LearningActivity(
        id = "fr-a1-reading-preference-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrouver le même type de préférence explicite dans un autre contexte court",
        knowledgeTarget = "préférence explicite dans un texte court",
        responseType = ResponseType.FILL_IN,
        prompt = "Lisez : « Chiu aime les pommes. Mia aime les bananes. » Qu’est-ce que Chiu aime ?",
        feedback = "Le texte dit « Chiu aime les pommes » : la réponse est donc les pommes.",
        reviewKey = "fr:a1:reading:preference-explicite",
        acceptedAnswers = listOf("les pommes", "pommes", "pomme")
    ),
    LearningActivity(
        id = "ko-a1-reading-preference-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "아주 짧은 글에서 명시적인 선호 정보 이해하기",
        knowledgeTarget = "짧은 글에 나온 명시적인 선호",
        responseType = ResponseType.FILL_IN,
        prompt = "읽으세요: ‘미아는 차를 좋아해요. 치우는 커피를 좋아해요.’ 미아는 무엇을 좋아하나요?",
        feedback = "글에 ‘미아는 차를 좋아해요’라고 되어 있으므로 답은 차입니다.",
        reviewKey = "ko:a1:reading:explicit-preference",
        acceptedAnswers = listOf("차")
    ),
    LearningActivity(
        id = "ko-a1-reading-preference-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "새로운 짧은 글에서 같은 유형의 명시적인 선호 정보 찾기",
        knowledgeTarget = "짧은 글에 나온 명시적인 선호",
        responseType = ResponseType.FILL_IN,
        prompt = "읽으세요: ‘치우는 사과를 좋아해요. 미아는 바나나를 좋아해요.’ 치우는 무엇을 좋아하나요?",
        feedback = "글에 ‘치우는 사과를 좋아해요’라고 되어 있으므로 답은 사과입니다.",
        reviewKey = "ko:a1:reading:explicit-preference",
        acceptedAnswers = listOf("사과")
    )
)

fun a1PreferenceReadingActivitiesFor(languageCode: String): List<LearningActivity> =
    a1PreferenceReadingActivities.filter { it.id.startsWith("$languageCode-") }
