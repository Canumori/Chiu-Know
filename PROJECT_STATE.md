# CHIU KNOW? — PROJECT STATE

## ATUALIZAÇÃO AUTORITATIVA — 2026-09-01 — TODOS OS 5 BANCOS EXPANDIDOS E VALIDADOS

Este arquivo é a fonte autoritativa de continuidade do Chiu Know?, mas o estado real do GitHub e do Supabase sempre vence informação desatualizada. Antes de qualquer alteração, reler este arquivo e conferir o estado real do GitHub.

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
Cada idioma possui agora exatamente 12 perguntas: 2 em cada nível A1, A2, B1, B2, C1 e C2. Cada banco possui teste próprio exigindo pelo menos 2 perguntas por nível e IDs únicos.

### English (`en`)
- 12 perguntas, 2 por nível A1–C2.
- Commit de conteúdo: `3a04be3ec4e4ce33ce7f71285b18a0bb276f92b6`.
- Commit do teste `EnglishPlacementBankTest.kt`: `50a53e55a0e44c98015ee851c19ec0af0c46adfd`.
- Android CI run #36 / `33513922534`: SUCCESS; testes unitários + build debug APK + upload do artifact passaram.

### Português (`pt`)
- 12 perguntas, 2 por nível A1–C2.
- Commit de conteúdo: `f8c8ab9c78ab9ad8577222bd2eabba98ab7e517d`.
- Commit do teste `PortuguesePlacementBankTest.kt`: `a2ecbbfee847f92105f3ef2cfef6a16cb1fb0460`.
- Android CI run #39 / `33514174976`: SUCCESS.
- Job `99876893217`: unit tests, build debug APK e upload debug APK em SUCCESS.

### Español (`es`)
- 12 perguntas, 2 por nível A1–C2.
- Todas as perguntas espanholas anteriores foram preservadas e foi adicionada uma segunda pergunta por nível.
- Commit de conteúdo: `39b4fc0579ad3d693675e6934c63cfac958f0047`.
- Commit do teste `SpanishPlacementBankTest.kt`: `d34c65b4ed96e758796ed16d921117bff566989d`.
- Android CI run #42 / `33516140417`: SUCCESS.
- Job `99883520518`: unit tests, build debug APK e upload debug APK em SUCCESS.
- Artifact validado: `chiu-know-debug`, artifact ID `9803688376`.

### Français (`fr`)
- 12 perguntas, 2 por nível A1–C2.
- Todas as perguntas francesas anteriores foram preservadas e foi adicionada uma segunda pergunta por nível.
- Commit de conteúdo: `89dd0d837c4648e3f6f8ec20d268beeedad4ea6e`.
- Commit do teste `FrenchPlacementBankTest.kt`: `46160ec64ae4d6b097a5954c00696b4693b9358e`.
- Android CI run #44 / `33516828777`: SUCCESS.
- Job `99885846553`: unit tests, build debug APK e upload debug APK em SUCCESS.
- Artifact validado: `chiu-know-debug`, artifact ID `9803982887`.

### 한국어 (`ko`)
- 12 perguntas, 2 por nível A1–C2.
- Todas as perguntas coreanas anteriores foram preservadas e foi adicionada uma segunda pergunta por nível.
- Commit de conteúdo: `4f3abea74ce111ecf21fe94a4df02ce372688865`.
- Commit do teste `KoreanPlacementBankTest.kt`: `0ada06db14757c3a9983c0eac025d1ada37224fa`.
- Android CI run #46 / `33517863466`: SUCCESS.
- Job `99889347099`: unit tests, build debug APK e upload debug APK em SUCCESS.
- Artifact validado: `chiu-know-debug`, artifact ID `9804381192`, digest `sha256:937f0f1408b112eb0aa53f91796c61d2540fcc7ad41a636849302c92dd7baa77`.

## CONCLUSÃO DA EXPANSÃO POR LÍNGUA
- English: CONCLUÍDO E VALIDADO.
- Português: CONCLUÍDO E VALIDADO.
- Español: CONCLUÍDO E VALIDADO.
- Français: CONCLUÍDO E VALIDADO.
- 한국어: CONCLUÍDO E VALIDADO.
- Total atual do placement: 60 perguntas, sendo 12 por idioma e 2 por nível CEFR em cada idioma.
- A expansão de conteúdo está encerrada neste marco.
- Não alterar o motor adaptativo como consequência desta expansão; variedade de perguntas não equivale a calibração psicométrica CEFR.

## HISTÓRICO VALIDADO RELEVANTE
- Run #18: bancos iniciais por idioma — SUCCESS.
- Run #19: banco selecionado pelo idioma-alvo — SUCCESS.
- Run #22: núcleo adaptativo corrigido — SUCCESS.
- Run #26: CI executando testes antes do APK — SUCCESS.
- Run #29 / `33458895765`: adaptativo ligado à UI — SUCCESS.
- Run #33 / `33459131956`: seletor multipergunta seguro — SUCCESS.
- Run #36 / `33513922534`: inglês com 2 perguntas por nível + testes — SUCCESS.
- Run #39 / `33514174976`: português com 2 perguntas por nível + testes — SUCCESS.
- Run #42 / `33516140417`: espanhol com 2 perguntas por nível + testes — SUCCESS.
- Run #44 / `33516828777`: francês com 2 perguntas por nível + testes — SUCCESS.
- Run #46 / `33517863466`: coreano com 2 perguntas por nível + testes — SUCCESS.

## PRÓXIMO PASSO
- Não há mais idioma pendente nesta rodada de expansão dos bancos de placement.
- Antes de iniciar nova frente funcional, revisar o objetivo específico e preservar todos os cinco bancos e seus testes.
- Se futuramente forem adicionadas mais perguntas, fazê-lo de forma isolada, com IDs únicos e validação em CI.

## PROTEÇÕES CONTRA REGRESSÃO
- Mudanças pequenas, isoladas e reversíveis.
- Nunca substituir/reconstruir o projeto inteiro por conveniência.
- Não remover testes verdes existentes.
- Não alterar o algoritmo adaptativo durante expansão de conteúdo.
- Não mudar fluxo de telas durante expansão de conteúdo.
- Não introduzir dependências desnecessárias.
- Não mandar Camila editar código, usar terminal, resolver conflito ou abrir ZIP.
- Conferir sempre código real, commits e Actions antes de concluir sucesso/falha.
- Um workflow antigo falho não invalida um run atual verde; usar sempre o run correspondente ao head relevante.

## ARTE / IDENTIDADE VISUAL
- Nenhuma arte ou mascote deve ser gerada/redesenhada/substituída por iniciativa do assistente.
- Ler `VISUAL_BIBLE.md` antes de qualquer trabalho visual.
- Chiu do logo = Chihuahua branco fotorealista aprovado com cabelo castanho; reutilizar master aprovado.
- Chiu personagem do universo = cachorro cartoon amarelo/esquisito aprovado; nunca misturar os dois.

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

## CHIU PLAYER — ESTADO QUE NÃO DEVE SER CONFUNDIDO COM ESTA FRENTE
- Chiu Player é projeto separado em `Canumori/Chiu-player`.
- Não usar código, branches, Supabase, versões, APKs ou decisões do Player no Chiu Know?.

## PRINCÍPIO FINAL
- PROJECT_STATE é a fonte de continuidade; estado real GitHub/Supabase vence documentação desatualizada.
- Antes de escrever: ler PROJECT_STATE + conferir estado real.
- Não usar Supabase se o passo puder ser local.
- Preservar tudo que já está verde.
- Camila não deve receber tarefas técnicas manuais.
