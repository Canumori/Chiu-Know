package com.chiu.know.model

/**
 * Controlled A1 vocabulary expansion: a second independent social-language
 * target per supported language, repeated in two contexts for retrieval.
 *
 * These are learning activities, not placement items and not proficiency claims.
 */
private val a1GratitudeActivities = listOf(
    LearningActivity(
        id = "en-a1-gratitude-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Retrieve a basic expression of thanks in a simple social context",
        knowledgeTarget = "thank you",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu gives Mia a small gift. Complete Mia’s reply: ___!",
        feedback = "‘Thank you’ is a basic neutral way to express thanks.",
        reviewKey = "en:a1:social:gratitude:thank-you",
        acceptedAnswers = listOf("thank you", "thanks")
    ),
    LearningActivity(
        id = "en-a1-gratitude-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Retrieve the same expression of thanks in a different everyday context",
        knowledgeTarget = "thank you",
        responseType = ResponseType.FILL_IN,
        prompt = "A neighbor opens the door for Chiu. Complete what Chiu says: ___!",
        feedback = "The same expression of thanks can be used in another everyday situation.",
        reviewKey = "en:a1:social:gratitude:thank-you",
        acceptedAnswers = listOf("thank you", "thanks")
    ),
    LearningActivity(
        id = "pt-a1-gratitude-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Recuperar uma expressão básica de agradecimento em um contexto social simples",
        knowledgeTarget = "obrigado / obrigada",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu dá um pequeno presente para Mia. Complete a resposta dela: ___!",
        feedback = "Mia pode dizer ‘Obrigada’ para agradecer.",
        reviewKey = "pt:a1:social:gratitude:obrigado",
        acceptedAnswers = listOf("obrigada", "obrigado")
    ),
    LearningActivity(
        id = "pt-a1-gratitude-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Recuperar a mesma expressão de agradecimento em outro contexto cotidiano",
        knowledgeTarget = "obrigado / obrigada",
        responseType = ResponseType.FILL_IN,
        prompt = "Uma vizinha segura a porta para Chiu. Complete o que ele diz: ___!",
        feedback = "Chiu pode dizer ‘Obrigado’ para agradecer.",
        reviewKey = "pt:a1:social:gratitude:obrigado",
        acceptedAnswers = listOf("obrigado", "obrigada")
    ),
    LearningActivity(
        id = "es-a1-gratitude-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Recuperar una expresión básica de agradecimiento en un contexto social sencillo",
        knowledgeTarget = "gracias",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu le da un pequeño regalo a Mia. Completa la respuesta de Mia: ¡___!",
        feedback = "‘Gracias’ es una forma básica y común de agradecer.",
        reviewKey = "es:a1:social:gratitude:gracias",
        acceptedAnswers = listOf("gracias")
    ),
    LearningActivity(
        id = "es-a1-gratitude-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Recuperar la misma expresión de agradecimiento en otro contexto cotidiano",
        knowledgeTarget = "gracias",
        responseType = ResponseType.FILL_IN,
        prompt = "Una vecina mantiene la puerta abierta para Chiu. Completa lo que dice Chiu: ¡___!",
        feedback = "La misma expresión ‘gracias’ sirve para agradecer en esta situación.",
        reviewKey = "es:a1:social:gratitude:gracias",
        acceptedAnswers = listOf("gracias")
    ),
    LearningActivity(
        id = "fr-a1-gratitude-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Retrouver une expression simple de remerciement dans un contexte social courant",
        knowledgeTarget = "merci",
        responseType = ResponseType.FILL_IN,
        prompt = "Chiu offre un petit cadeau à Mia. Complétez la réponse de Mia : ___ !",
        feedback = "« Merci » est une manière simple et courante de remercier.",
        reviewKey = "fr:a1:social:gratitude:merci",
        acceptedAnswers = listOf("merci")
    ),
    LearningActivity(
        id = "fr-a1-gratitude-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "Retrouver la même expression de remerciement dans une autre situation quotidienne",
        knowledgeTarget = "merci",
        responseType = ResponseType.FILL_IN,
        prompt = "Une voisine tient la porte ouverte pour Chiu. Complétez ce que dit Chiu : ___ !",
        feedback = "La même expression « merci » convient dans cette situation.",
        reviewKey = "fr:a1:social:gratitude:merci",
        acceptedAnswers = listOf("merci")
    ),
    LearningActivity(
        id = "ko-a1-gratitude-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "간단한 일상 상황에서 기본적인 감사 표현 떠올리기",
        knowledgeTarget = "감사합니다",
        responseType = ResponseType.FILL_IN,
        prompt = "치우가 미아에게 작은 선물을 줍니다. 미아의 대답을 완성하세요: ___!",
        feedback = "‘감사합니다’는 정중하게 고마움을 표현하는 기본적인 말입니다.",
        reviewKey = "ko:a1:social:gratitude:gamsahamnida",
        acceptedAnswers = listOf("감사합니다", "고맙습니다")
    ),
    LearningActivity(
        id = "ko-a1-gratitude-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.VOCABULARY,
        learningObjective = "다른 일상 상황에서 같은 감사 표현 떠올리기",
        knowledgeTarget = "감사합니다",
        responseType = ResponseType.FILL_IN,
        prompt = "이웃이 치우를 위해 문을 잡아 줍니다. 치우의 말을 완성하세요: ___!",
        feedback = "이 상황에서도 ‘감사합니다’라고 정중하게 감사할 수 있습니다.",
        reviewKey = "ko:a1:social:gratitude:gamsahamnida",
        acceptedAnswers = listOf("감사합니다", "고맙습니다")
    )
)

fun a1GratitudeActivitiesFor(languageCode: String): List<LearningActivity> =
    a1GratitudeActivities.filter { it.id.startsWith("$languageCode-") }
