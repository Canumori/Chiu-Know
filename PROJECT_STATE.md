# CHIU KNOW? — PROJECT STATE

## ATUALIZAÇÃO AUTORITATIVA — 2026-09-01 — PLACEMENT EXPANDIDO; CONTINUIDADE DEVE SEGUIR O PLANO DO PRODUTO

Este arquivo é a fonte autoritativa de continuidade operacional do Chiu Know?, mas NÃO é o único documento de planejamento. Antes de qualquer nova frente, o próximo chat deve ler em conjunto, obrigatoriamente:
1. `PROJECT_STATE.md` — estado técnico real e continuidade;
2. `PRODUCT_SPEC.md` — visão, objetivos e roadmap funcional do produto;
3. `RESEARCH.md` — decisões/referências técnicas e de arquitetura;
4. `VISUAL_BIBLE.md` — identidade, personagens e regras de produção visual quando a frente envolver UI, histórias, atividades ou personagens.

O estado real do GitHub e, quando aplicável, do Supabase sempre vence documentação desatualizada.

## REGRA DE CONTINUIDADE — NÃO DECLARAR QUE “NÃO HÁ PLANEJAMENTO”
O encerramento de uma etapa no `PROJECT_STATE.md` NÃO significa que o projeto ficou sem próximos objetivos. O roadmap maior já está definido no `PRODUCT_SPEC.md` e deve orientar a sequência de desenvolvimento. Quando uma etapa terminar, consultar o plano do produto antes de pedir à usuária que reinvente o próximo objetivo.

## ROADMAP DE PRODUTO JÁ DEFINIDO
O Chiu Know? é um aplicativo Android multilíngue de aprendizagem de idiomas, CEFR A1–C2, com:
- idioma da interface e idioma-alvo independentes;
- placement adaptativo inicial e reteste;
- resultado geral e por habilidade: gramática, vocabulário, listening, reading, writing e speaking;
- trilha CEFR A1 → A2 → B1 → B2 → C1 → C2;
- ensino/progresso adaptativo;
- revisão inteligente/espaçada baseada em FSRS ou equivalente permissivo;
- XP, streak, meta diária, progresso e conquistas;
- histórias interativas com personagens recorrentes e decisões do aluno;
- tutor por IA para explicações, diálogos e conteúdo dinâmico;
- fala/áudio como módulos próprios, sem confundir transcrição com avaliação de pronúncia.

O placement atual é apenas uma parte desse roadmap. O próximo chat deve comparar o que já existe com `PRODUCT_SPEC.md` e continuar pela próxima lacuna lógica, em etapas pequenas e testáveis, sem apagar ou reescrever o que já está verde.

## QUANDO ENTRAM OS PERSONAGENS
Os personagens NÃO são um detalhe opcional nem devem ser esquecidos até o fim. Eles fazem parte do produto planejado, especialmente em:
- cards/contextos de atividades (exercícios, listening, speaking, reading, testes, conquistas etc.);
- trilha e apresentação pedagógica quando a UI dessa frente for construída;
- histórias interativas, onde são personagens recorrentes e parte central da experiência;
- diálogos/tutor/conteúdo contextual quando isso for implementado de forma compatível com o roadmap.

A introdução visual efetiva deve ocorrer quando a respectiva frente de UI/atividade/história for implementada, e não como uma troca decorativa prematura no placement já validado. Antes de qualquer uso visual, reler `VISUAL_BIBLE.md`, verificar/reutilizar assets-mestre aprovados e pedir autorização para qualquer nova arte/pose que precise ser criada. Não gerar nem substituir personagens por iniciativa própria.

### Elenco canônico registrado em `VISUAL_BIBLE.md`
- Chiu — Chihuahua amarelo cartunesco, maluco/curioso; personagem do universo.
- Mia — gata fashion/dramática, fofoqueira, odeia segunda-feira e ama café.
- Zé Pernilongo — mosquito falante, inquieto, atrapalhado/esquecido, coração e apetite enormes.
- Barto — morcego noturno filosófico; gosta de terror e tem medo de altura apesar de voar.
- Lara — arara barulhenta/opinativa; repete tudo e não tem filtro.
- Caca — capivara zen até ficar estressada; gosta de água morna/chá e evita responsabilidades.
- Onça — forte e decidida, mas insegura por dentro; odeia perder.
- Perry — ornitorrinco brilhante/esquisito; inventa coisas inúteis que às vezes funcionam.
- Lena — preguiça muito lenta; dormir, comer e procrastinar.

REGRA ABSOLUTA: Chiu do logo/ícone e Chiu personagem são identidades visuais diferentes. Logo/ícone usa somente o Chihuahua branco fotorrealista aprovado com cabelo castanho. Histórias, cards, exercícios e universo usam o Chihuahua amarelo cartunesco aprovado. Nunca misturar.

## ESTADO ATUAL DO ANDROID
- Repositório: `Canumori/Chiu-Know`; branch `main`.
- Kotlin + Jetpack Compose; namespace/applicationId `com.chiu.know`.
- minSdk 26, targetSdk/compileSdk 35, Java/JVM 17.
- versionCode 1, versionName 0.1.0.
- Compose BOM 2024.12.01; AppCompat 1.7.0; DataStore Preferences 1.1.7.
- JUnit 4.13.2 apenas em `testImplementation`.

## FLUXO ATUAL
`LANGUAGE_SELECTION → PLACEMENT_INTRO → PLACEMENT_TEST → PLACEMENT_RESULT`.

O placement usa núcleo adaptativo local:
- bancos separados para Português, English, Español, Français e 한국어;
- começa em B1;
- resposta correta/incorreta estreita o intervalo A1–C2 e determina a próxima faixa;
- resultado final vem do estado adaptativo;
- mantém tentar novamente, alterar idiomas, DataStore e aviso explícito de protótipo/estimativa;
- NÃO é teste CEFR calibrado nem certificação oficial.

## INFRAESTRUTURA MULTIPERGUNTA — VALIDADA
- `placementQuestionsForLevel(...)` obtém todas as perguntas de um nível.
- `placementQuestionForLevel(...)` seleciona ciclicamente entre múltiplas perguntas e falha explicitamente se o nível estiver vazio.
- A UI usa esse seletor com `adaptiveState.answeredQuestions` como índice de tentativa.
- `PlacementQuestionSelectionTest.kt` cobre filtro, rotação e banco vazio.
- Commit do seletor: `2e611fcea5be22f6d10ada096633f60b6d439230`.
- Commit dos testes do seletor: `2532900844919885b14adf57bb689e3d910c30f9`.
- Commit da UI usando seletor seguro: `24e3d54fb2d3c494acdb98acffd53146a06d1e70`.
- Android CI run #33 / `33459131956`: SUCCESS.

## TODOS OS BANCOS DE PLACEMENT — VALIDADOS
Cada idioma possui exatamente 12 perguntas: 2 em cada nível A1, A2, B1, B2, C1 e C2. Cada banco possui teste próprio exigindo pelo menos 2 perguntas por nível e IDs únicos.

### English (`en`)
- 12 perguntas, 2 por nível A1–C2.
- Conteúdo: `3a04be3ec4e4ce33ce7f71285b18a0bb276f92b6`.
- Teste: `50a53e55a0e44c98015ee851c19ec0af0c46adfd`.
- CI #36 / `33513922534`: SUCCESS.

### Português (`pt`)
- 12 perguntas, 2 por nível A1–C2.
- Conteúdo: `f8c8ab9c78ab9ad8577222bd2eabba98ab7e517d`.
- Teste: `a2ecbbfee847f92105f3ef2cfef6a16cb1fb0460`.
- CI #39 / `33514174976`: SUCCESS.

### Español (`es`)
- 12 perguntas, 2 por nível A1–C2.
- Conteúdo: `39b4fc0579ad3d693675e6934c63cfac958f0047`.
- Teste: `d34c65b4ed96e758796ed16d921117bff566989d`.
- CI #42 / `33516140417`: SUCCESS.
- Artifact `chiu-know-debug`, ID `9803688376`.

### Français (`fr`)
- 12 perguntas, 2 por nível A1–C2.
- Conteúdo: `89dd0d837c4648e3f6f8ec20d268beeedad4ea6e`.
- Teste: `46160ec64ae4d6b097a5954c00696b4693b9358e`.
- CI #44 / `33516828777`: SUCCESS.
- Artifact `chiu-know-debug`, ID `9803982887`.

### 한국어 (`ko`)
- 12 perguntas, 2 por nível A1–C2.
- Conteúdo: `4f3abea74ce111ecf21fe94a4df02ce372688865`.
- Teste: `0ada06db14757c3a9983c0eac025d1ada37224fa`.
- CI #46 / `33517863466`: SUCCESS.
- Artifact `chiu-know-debug`, ID `9804381192`.

## MARCO DE VALIDAÇÃO FINAL
- Total atual: 60 perguntas, 12 por idioma, 2 por nível CEFR em cada idioma.
- A expansão de conteúdo do placement está encerrada.
- Android CI #47 / run `33518166184`, head `38f80015bc6c809974ed15e66215da53a75ee034`: COMPLETED / SUCCESS.
- Não alterar o motor adaptativo como consequência desta expansão.

## PRÓXIMO PASSO DE CONTINUIDADE
1. Não continuar adicionando perguntas apenas por continuar a rodada anterior.
2. Reler `PRODUCT_SPEC.md` e comparar roadmap versus funcionalidades já implementadas.
3. Escolher a próxima lacuna lógica do roadmap e implementá-la em fatia pequena, testável e reversível.
4. Preservar placement, cinco bancos, testes, DataStore e fluxo atual.
5. Quando a próxima frente envolver cards/atividades/trilha/histórias, incorporar o elenco canônico conforme `VISUAL_BIBLE.md`, reutilizando assets aprovados; novas artes somente com autorização.
6. Não pular diretamente para uma implementação grande de IA, áudio, backend ou histórias sem construir e validar as fundações anteriores necessárias.

## PROTEÇÕES CONTRA REGRESSÃO
- Mudanças pequenas, isoladas e reversíveis.
- Nunca substituir/reconstruir o projeto inteiro por conveniência.
- Não remover testes verdes existentes.
- Não alterar o algoritmo adaptativo durante expansão de conteúdo.
- Não mudar fluxo de telas sem relação com a frente atual.
- Não introduzir dependências desnecessárias.
- Não mandar Camila editar código, usar terminal, resolver conflito ou abrir ZIP.
- Conferir código real, commits e Actions antes de concluir sucesso/falha.
- Um workflow antigo falho não invalida um run atual verde; usar o run correspondente ao head relevante.

## ARTE / IDENTIDADE VISUAL
- `VISUAL_BIBLE.md` é autoritativo para personagens e identidade.
- Nenhuma arte ou mascote deve ser gerada/redesenhada/substituída por iniciativa do assistente.
- Chiu do logo = Chihuahua branco fotorealista aprovado com cabelo castanho; reutilizar master aprovado.
- Chiu personagem = cachorro cartoon amarelo/esquisito aprovado; nunca misturar os dois.
- Identidade dos demais personagens também é canônica; objetos, pose, expressão, roupa e situação podem variar, mas a identidade não.
- Para identidade exata, reutilizar assets-mestre. Para novas poses, criar biblioteca canônica controlada somente após autorização/aprovação.

# ISOLAMENTO ABSOLUTO DO SUPABASE
## CHIU KNOW? — ÚNICO SUPABASE PERMITIDO
- Organização `Chiu Know`, ID `aeerqbmrwulxsawhjyvm`.
- Project ref `uskxabsodcnzlovuaurp`, região `sa-east-1`.
- O desenvolvimento Android atual NÃO precisa de Supabase.

## CHIU PLAYER — PROIBIDO NESTA FRENTE
- Organização `Chiu`, ID `nnrwosbnvdvzaoflwxlo`.
- Project ref `hpcbkvbrlwjnwlikmbfb`.
- Nunca escrever nesse backend durante trabalhos do Chiu Know?.

## PROTOCOLO FUTURO DE SUPABASE
Antes de qualquer escrita futura no Supabase para Chiu Know?, ler a skill Supabase e verificar por leitura organização e projeto reais. Somente `uskxabsodcnzlovuaurp` é permitido. Se aparecer `hpcbkvbrlwjnwlikmbfb`, parar imediatamente.

## PRINCÍPIO FINAL
- `PROJECT_STATE.md` = estado/continuidade; `PRODUCT_SPEC.md` = roadmap/objetivos; `RESEARCH.md` = arquitetura/referências; `VISUAL_BIBLE.md` = personagens/identidade.
- O próximo chat deve ler esses documentos em conjunto conforme a frente, e nunca concluir que o fim de uma tarefa significa fim do planejamento.
- Antes de escrever: conferir estado real do GitHub; usar Supabase apenas quando necessário e somente o projeto do Chiu Know?.
- Preservar tudo que já está verde.
