# CHIU KNOW? — PROJECT STATE

## ATUALIZAÇÃO AUTORITATIVA — 2026-09-01 — PLACEMENT ADAPTATIVO + INGLÊS E PORTUGUÊS EXPANDIDOS E VALIDADOS

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

## BANCO INGLÊS — VALIDADO
- English (`en`) possui 2 perguntas em cada nível A1, A2, B1, B2, C1 e C2: 12 perguntas no total.
- Commit de conteúdo: `3a04be3ec4e4ce33ce7f71285b18a0bb276f92b6`.
- `EnglishPlacementBankTest.kt` exige pelo menos 2 perguntas por nível e IDs únicos.
- Commit do teste: `50a53e55a0e44c98015ee851c19ec0af0c46adfd`.
- Android CI run #36 / `33513922534`: SUCCESS; testes unitários + build debug APK + upload do artifact passaram.

## BANCO PORTUGUÊS — VALIDADO
- Português (`pt`) possui 2 perguntas em cada nível A1, A2, B1, B2, C1 e C2: 12 perguntas no total.
- Commit de conteúdo: `f8c8ab9c78ab9ad8577222bd2eabba98ab7e517d` (`feat: expand Portuguese placement bank`).
- Teste equivalente exige pelo menos 2 perguntas por nível e IDs únicos.
- Commit do teste: `a2ecbbfee847f92105f3ef2cfef6a16cb1fb0460` (`test: require two Portuguese questions per level`).
- Android CI run #39 / `33514174976`: SUCCESS. Job `99876893217` completou com sucesso: unit tests, build debug APK e upload debug APK.
- A expansão portuguesa não alterou English, Español, Français, 한국어, motor adaptativo, UI ou Supabase.

## BANCOS AINDA NÃO EXPANDIDOS
- Español (`es`): ainda 1 pergunta por nível A1–C2.
- Français (`fr`): ainda 1 pergunta por nível A1–C2.
- 한국어 (`ko`): ainda 1 pergunta por nível A1–C2.
- NÃO expandir vários idiomas de uma vez.

## HISTÓRICO VALIDADO RELEVANTE
- Run #18: bancos iniciais por idioma — SUCCESS.
- Run #19: banco selecionado pelo idioma-alvo — SUCCESS.
- Run #22: núcleo adaptativo corrigido — SUCCESS.
- Run #26: CI executando testes antes do APK — SUCCESS.
- Run #29 / `33458895765`: adaptativo ligado à UI — SUCCESS.
- Run #33 / `33459131956`: seletor multipergunta seguro — SUCCESS.
- Run #36 / `33513922534`: inglês com 2 perguntas por nível + testes — SUCCESS.
- Run #39 / `33514174976`: português com 2 perguntas por nível + testes — SUCCESS.

## PRÓXIMO PASSO EXATO
1. NÃO mexer no Supabase.
2. NÃO mexer no Chiu Player.
3. Não fazer redesign nem gerar/substituir arte.
4. Preservar run #39 como baseline validada atual.
5. Próxima língua: Español (`es`).
6. Adicionar SOMENTE uma segunda pergunta por nível A1–C2 ao banco espanhol, mantendo as perguntas existentes.
7. Não alterar English, Português, Français, 한국어, motor adaptativo, UI, DataStore ou dependências.
8. Criar teste equivalente ao inglês/português exigindo pelo menos 2 perguntas em cada nível espanhol e IDs únicos.
9. Rodar/aguardar o Android CI e verificar efetivamente unit tests + build debug APK + upload do artifact.
10. Se CI falhar, investigar o run/job/log atual e corrigir somente a causa exata; não fazer rollback amplo nem mudanças por suposição.
11. Só após CI verde do espanhol atualizar este PROJECT_STATE e então considerar Français como etapa seguinte.
12. Não tocar em Français e 한국어 antes da validação do Español.
13. Continuar chamando o resultado de estimativa/protótipo; variedade de perguntas NÃO equivale a calibração psicométrica CEFR.

## PROTEÇÕES CONTRA REGRESSÃO
- Mudanças pequenas, isoladas e reversíveis.
- Nunca substituir/reconstruir o projeto inteiro por conveniência.
- Não remover testes verdes existentes.
- Não alterar o algoritmo adaptativo durante expansão de conteúdo.
- Não mudar fluxo de telas durante expansão de conteúdo.
- Não introduzir dependências desnecessárias.
- Não mandar Camila editar código, usar terminal, resolver conflito ou abrir ZIP.
- Conferir sempre código real, commits e Actions antes de concluir sucesso/falha.
- Um workflow antigo falho não invalida um run atual verde; usar sempre o run correspondente ao head atual.

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
- A manutenção recente do health check do Player foi separada e não faz parte do desenvolvimento Android do Chiu Know?.
- Não usar código, branches, Supabase, versões, APKs ou decisões do Player no Chiu Know?.

## PRINCÍPIO FINAL
- PROJECT_STATE é a fonte de continuidade; estado real GitHub/Supabase vence documentação desatualizada.
- Antes de escrever: ler PROJECT_STATE + conferir estado real.
- Não usar Supabase se o passo puder ser local.
- Preservar tudo que já está verde.
- Camila não deve receber tarefas técnicas manuais.
