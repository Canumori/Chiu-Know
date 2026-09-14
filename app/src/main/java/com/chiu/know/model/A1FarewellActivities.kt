package com.chiu.know.model

/**
 * Controlled A1 vocabulary expansion for a basic farewell expression.
 * Two contexts support retrieval without treating either attempt as mastery.
 */
private val a1FarewellActivities = listOf(
    LearningActivity(
        id = "en-a1-farewell-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Retrieve a basic farewell at the end of a social encounter",
        knowledgeTarget = "goodbye",
        responseType = ResponseType.FILL_IN,
        prompt = "Mia is leaving after class. Complete what she says: ___! See you tomorrow.",
        feedback = "‘Goodbye’ is a basic neutral farewell.",
        reviewKey = "en:a1:social:farewell:goodbye",
        acceptedAnswers = listOf("goodbye", "bye")
    ),
    LearningActivity(
        id = "en-a1-farewell-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Retrieve the same farewell in another everyday context",
        knowledgeTarget = "goodbye",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu finishes a visit and goes home. Complete what he says: ___!",
        feedback = "The same farewell can be retrieved in a different everyday situation.",
        reviewKey = "en:a1:social:farewell:goodbye",
        acceptedAnswers = listOf("goodbye", "bye")
    ),
    LearningActivity(
        id = "pt-a1-farewell-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Recuperar uma despedida básica ao fim de um encontro social",
        knowledgeTarget = "tchau",
        responseType = ResponseType.FILL_IN,
        prompt = "Mia está indo embora depois da aula. Complete o que ela diz: ___! Até amanhã.",
        feedback = "‘Tchau’ é uma despedida cotidiana e muito comum.",
        reviewKey = "pt:a1:social:farewell:tchau",
        acceptedAnswers = listOf("tchau")
    ),
    LearningActivity(
        id = "pt-a1-farewell-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Recuperar a mesma despedida em outro contexto cotidiano",
        knowledgeTarget = "tchau",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu termina uma visita e vai para casa. Complete o que ele diz: ___!",
        feedback = "A mesma despedida pode ser recuperada em outra situação cotidiana.",
        reviewKey = "pt:a1:social:farewell:tchau",
        acceptedAnswers = listOf("tchau")
    ),
    LearningActivity(
        id = "es-a1-farewell-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Recuperar una despedida básica al final de un encuentro social",
        knowledgeTarget = "adiós",
        responseType = ResponseType.FILL_IN,
        prompt = "Mia se va después de clase. Completa lo que dice: ¡___! Hasta mañana.",
        feedback = "‘Adiós’ es una despedida básica y común.",
        reviewKey = "es:a1:social:farewell:adios",
        acceptedAnswers = listOf("adiós")
    ),
    LearningActivity(
        id = "es-a1-farewell-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Recuperar la misma despedida en otro contexto cotidiano",
        knowledgeTarget = "adiós",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu termina una visita y vuelve a casa. Completa lo que dice: ¡___!",
        feedback = "La misma despedida puede usarse en otra situación cotidiana.",
        reviewKey = "es:a1:social:farewell:adios",
        acceptedAnswers = listOf("adiós")
    ),
    LearningActivity(
        id = "fr-a1-farewell-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Retrouver une formule simple pour prendre congé à la fin d’une rencontre",
        knowledgeTarget = "au revoir",
        responseType = ResponseType.FILL_IN,
        prompt = "Mia part après le cours. Complétez ce qu’elle dit : ___ ! À demain.",
        feedback = "« Au revoir » est une formule courante pour prendre congé.",
        reviewKey = "fr:a1:social:farewell:au-revoir",
        acceptedAnswers = listOf("au revoir")
    ),
    LearningActivity(
        id = "fr-a1-farewell-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Retrouver la même formule de départ dans une autre situation quotidienne",
        knowledgeTarget = "au revoir",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu termine une visite et rentre chez lui. Complétez ce qu’il dit : ___ !",
        feedback = "La même formule convient dans une autre situation quotidienne.",
        reviewKey = "fr:a1:social:farewell:au-revoir",
        acceptedAnswers = listOf("au revoir")
    ),
    LearningActivity(
        id = "ko-a1-farewell-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "일상적인 만남이 끝날 때 기본적인 작별 표현 떠올리기",
        knowledgeTarget = "또 봐요",
        responseType = ResponseType.FILL_IN,
        prompt = "수업이 끝나고 미아가 친구와 헤어집니다. 미아의 말을 완성하세요: ___!",
        feedback = "‘또 봐요’는 다시 만날 사람에게 쓸 수 있는 자연스러운 작별 표현입니다.",
        reviewKey = "ko:a1:social:farewell:tto-bwayo",
        acceptedAnswers = listOf("또 봐요")
    ),
    LearningActivity(
        id = "ko-a1-farewell-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "다른 일상 상황에서 같은 작별 표현 떠올리기",
        knowledgeTarget = "또 봐요",
        responseType = ResponseType.FILL_IN,
        prompt = "치우가 친구와 헤어져 집에 갑니다. 치우의 말을 완성하세요: ___!",
        feedback = "다시 만날 사람과 헤어질 때 ‘또 봐요’라고 말할 수 있습니다.",
        reviewKey = "ko:a1:social:farewell:tto-bwayo",
        acceptedAnswers = listOf("또 봐요")
    )
)

fun a1FarewellActivitiesFor(languageCode: String): List<LearningActivity> =
    a1FarewellActivities.filter { it.id.startsWith("$languageCode-") }
