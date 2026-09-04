package com.chiu.know.model

/**
 * First controlled A1 question-forming slice.
 *
 * This is still deterministic text practice, not speaking or free conversation.
 * The learner reconstructs one common question for asking someone's name.
 */
private val a1BasicQuestionActivities = listOf(
    LearningActivity(
        id = "en-a1-question-name-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Form a basic question to ask someone's name",
        knowledgeTarget = "What is your name?",
        responseType = ResponseType.REORDER,
        prompt = "Mia meets someone new. Put the words in order to ask their name.",
        feedback = "A common basic question is: What is your name?",
        reviewKey = "en:a1:grammar:question:ask-name",
        acceptedAnswers = listOf("What is your name"),
        responseOptions = listOf("your", "name", "What", "is")
    ),
    LearningActivity(
        id = "pt-a1-question-name-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Formar uma pergunta básica para saber o nome de alguém",
        knowledgeTarget = "Qual é o seu nome?",
        responseType = ResponseType.REORDER,
        prompt = "A Mia conhece alguém. Coloque as palavras em ordem para perguntar o nome da pessoa.",
        feedback = "Uma pergunta básica e natural é: Qual é o seu nome?",
        reviewKey = "pt:a1:grammar:pergunta:nome",
        acceptedAnswers = listOf("Qual é o seu nome"),
        responseOptions = listOf("seu", "nome", "Qual", "é", "o")
    ),
    LearningActivity(
        id = "es-a1-question-name-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Formar una pregunta básica para saber el nombre de alguien",
        knowledgeTarget = "¿Cómo te llamas?",
        responseType = ResponseType.REORDER,
        prompt = "Mia conoce a alguien. Ordena las palabras para preguntar su nombre.",
        feedback = "Una pregunta básica y natural es: ¿Cómo te llamas?",
        reviewKey = "es:a1:grammar:pregunta:nombre",
        acceptedAnswers = listOf("Cómo te llamas"),
        responseOptions = listOf("llamas", "Cómo", "te")
    ),
    LearningActivity(
        id = "fr-a1-question-name-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "Former une question simple pour demander le prénom de quelqu’un",
        knowledgeTarget = "Comment tu t’appelles ?",
        responseType = ResponseType.REORDER,
        prompt = "Mia rencontre quelqu’un. Remettez les mots dans l’ordre pour demander son prénom.",
        feedback = "Une question simple et naturelle est : Comment tu t’appelles ?",
        reviewKey = "fr:a1:grammar:question:nom",
        acceptedAnswers = listOf("Comment tu t’appelles"),
        responseOptions = listOf("t’appelles", "Comment", "tu")
    ),
    LearningActivity(
        id = "ko-a1-question-name-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.GRAMMAR,
        learningObjective = "상대방의 이름을 묻는 기본적인 질문 만들기",
        knowledgeTarget = "이름이 뭐예요?",
        responseType = ResponseType.REORDER,
        prompt = "미아가 처음 만난 사람에게 이름을 물어요. 표현을 올바른 순서로 놓으세요.",
        feedback = "기본적으로 ‘이름이 뭐예요?’라고 물을 수 있어요.",
        reviewKey = "ko:a1:grammar:question:ask-name",
        acceptedAnswers = listOf("이름이 뭐예요"),
        responseOptions = listOf("뭐예요", "이름이")
    )
)

fun a1BasicQuestionActivitiesFor(languageCode: String): List<LearningActivity> =
    a1BasicQuestionActivities.filter { it.id.startsWith("$languageCode-") }
