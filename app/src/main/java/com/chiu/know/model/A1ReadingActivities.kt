package com.chiu.know.model

/**
 * Small A1 reading-comprehension slice.
 *
 * Each language contains two short contexts that exercise the same explicit
 * reading target. They intentionally use the existing deterministic text
 * evaluator; no AI judgement or inferred mastery is involved.
 */
private val a1ReadingActivities = listOf(
    LearningActivity(
        id = "en-a1-reading-intro-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Understand an explicit personal detail in a very short introduction",
        knowledgeTarget = "name in a basic introduction",
        responseType = ResponseType.FILL_IN,
        prompt = "Read: ‘Hello! I’m Mia. I live near Chiu.’ What is the cat’s name?",
        feedback = "The text says ‘I’m Mia’, so the cat’s name is Mia.",
        reviewKey = "en:a1:reading:introduction-name",
        acceptedAnswers = listOf("Mia")
    ),
    LearningActivity(
        id = "en-a1-reading-intro-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrieve the same explicit detail from a new short introduction",
        knowledgeTarget = "name in a basic introduction",
        responseType = ResponseType.FILL_IN,
        prompt = "Read: ‘Hi! My name is Chiu. Mia is my friend.’ What is the dog’s name?",
        feedback = "The text says ‘My name is Chiu’, so the dog’s name is Chiu.",
        reviewKey = "en:a1:reading:introduction-name",
        acceptedAnswers = listOf("Chiu")
    ),
    LearningActivity(
        id = "pt-a1-reading-intro-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Compreender uma informação pessoal explícita em uma apresentação muito curta",
        knowledgeTarget = "nome em uma apresentação básica",
        responseType = ResponseType.FILL_IN,
        prompt = "Leia: ‘Olá! Eu sou a Mia. Eu moro perto do Chiu.’ Qual é o nome da gata?",
        feedback = "O texto diz ‘Eu sou a Mia’, então o nome da gata é Mia.",
        reviewKey = "pt:a1:reading:introduction-name",
        acceptedAnswers = listOf("Mia")
    ),
    LearningActivity(
        id = "pt-a1-reading-intro-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recuperar a mesma informação explícita em uma nova apresentação curta",
        knowledgeTarget = "nome em uma apresentação básica",
        responseType = ResponseType.FILL_IN,
        prompt = "Leia: ‘Oi! Meu nome é Chiu. A Mia é minha amiga.’ Qual é o nome do cachorro?",
        feedback = "O texto diz ‘Meu nome é Chiu’, então o nome do cachorro é Chiu.",
        reviewKey = "pt:a1:reading:introduction-name",
        acceptedAnswers = listOf("Chiu")
    ),
    LearningActivity(
        id = "es-a1-reading-intro-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Comprender un dato personal explícito en una presentación muy breve",
        knowledgeTarget = "nombre en una presentación básica",
        responseType = ResponseType.FILL_IN,
        prompt = "Lee: ‘¡Hola! Soy Mia. Vivo cerca de Chiu.’ ¿Cómo se llama la gata?",
        feedback = "El texto dice ‘Soy Mia’, así que la gata se llama Mia.",
        reviewKey = "es:a1:reading:introduction-name",
        acceptedAnswers = listOf("Mia")
    ),
    LearningActivity(
        id = "es-a1-reading-intro-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Recuperar el mismo dato explícito en una nueva presentación breve",
        knowledgeTarget = "nombre en una presentación básica",
        responseType = ResponseType.FILL_IN,
        prompt = "Lee: ‘¡Hola! Me llamo Chiu. Mia es mi amiga.’ ¿Cómo se llama el perro?",
        feedback = "El texto dice ‘Me llamo Chiu’, así que el perro se llama Chiu.",
        reviewKey = "es:a1:reading:introduction-name",
        acceptedAnswers = listOf("Chiu")
    ),
    LearningActivity(
        id = "fr-a1-reading-intro-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Comprendre une information personnelle explicite dans une très courte présentation",
        knowledgeTarget = "nom dans une présentation simple",
        responseType = ResponseType.FILL_IN,
        prompt = "Lisez : « Bonjour ! Je suis Mia. J’habite près de Chiu. » Comment s’appelle la chatte ?",
        feedback = "Le texte dit « Je suis Mia » : la chatte s’appelle donc Mia.",
        reviewKey = "fr:a1:reading:introduction-name",
        acceptedAnswers = listOf("Mia")
    ),
    LearningActivity(
        id = "fr-a1-reading-intro-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "Retrouver la même information explicite dans une nouvelle courte présentation",
        knowledgeTarget = "nom dans une présentation simple",
        responseType = ResponseType.FILL_IN,
        prompt = "Lisez : « Bonjour ! Je m’appelle Chiu. Mia est mon amie. » Comment s’appelle le chien ?",
        feedback = "Le texte dit « Je m’appelle Chiu » : le chien s’appelle donc Chiu.",
        reviewKey = "fr:a1:reading:introduction-name",
        acceptedAnswers = listOf("Chiu")
    ),
    LearningActivity(
        id = "ko-a1-reading-intro-001",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "아주 짧은 자기소개에서 명시적인 개인 정보 이해하기",
        knowledgeTarget = "기본 자기소개에 나온 이름",
        responseType = ResponseType.FILL_IN,
        prompt = "읽으세요: ‘안녕하세요! 저는 미아예요. 치우 근처에 살아요.’ 고양이의 이름은 무엇인가요?",
        feedback = "글에 ‘저는 미아예요’라고 되어 있으므로 고양이의 이름은 미아입니다.",
        reviewKey = "ko:a1:reading:introduction-name",
        acceptedAnswers = listOf("미아")
    ),
    LearningActivity(
        id = "ko-a1-reading-intro-002",
        level = CefrLevel.A1,
        primarySkill = LearningSkill.READING,
        learningObjective = "새로운 짧은 자기소개에서 같은 명시적 정보 찾기",
        knowledgeTarget = "기본 자기소개에 나온 이름",
        responseType = ResponseType.FILL_IN,
        prompt = "읽으세요: ‘안녕하세요! 제 이름은 치우예요. 미아는 제 친구예요.’ 강아지의 이름은 무엇인가요?",
        feedback = "글에 ‘제 이름은 치우예요’라고 되어 있으므로 강아지의 이름은 치우입니다.",
        reviewKey = "ko:a1:reading:introduction-name",
        acceptedAnswers = listOf("치우")
    )
)

fun a1ReadingActivitiesFor(languageCode: String): List<LearningActivity> =
    a1ReadingActivities.filter { it.id.startsWith("$languageCode-") }
