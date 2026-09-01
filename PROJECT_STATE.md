# CHIU KNOW? — PROJECT STATE

## ATUALIZAÇÃO AUTORITATIVA — 2026-09-01 — PLACEMENT ADAPTATIVO + BANCO INGLÊS EXPANDIDO E VALIDADO

Este arquivo é a fonte autoritativa de continuidade do Chiu Know?, mas o estado real do GitHub e do Supabase sempre vence informação desatualizada.

## ESTADO ATUAL DO ANDROID
- Repositório: `Canumori/Chiu-Know`; branch `main`.
- Kotlin + Jetpack Compose; namespace/applicationId `com.chiu.know`.
- minSdk 26, targetSdk/compileSdk 35, Java/JVM 17.
- versionCode 1, versionName 0.1.0.
- Compose BOM 2024.12.01; AppCompat 1.7.0; DataStore Preferences 1.1.7.
- JUnit 4.13.2 apenas em `testImplementation`.

## FLUXO ATUAL
`LANGUAGE_SELECTION → PLACEMENT_INTRO → PLACEMENT_TEST → PLACEMENT_RESULT`.

O placement usa o núcleo adaptativo local na interface:
- bancos-protótipo separados para Português, English, Español, Français e 한국어;
- começa em B1;
- resposta correta/incorreta estreita o intervalo A1–C2 e determina a próxima faixa;
- resultado final vem do estado adaptativo;
- mantém tentar novamente, alterar idiomas, DataStore e aviso explícito de protótipo/estimativa;
- NÃO é teste CEFR calibrado nem certificação oficial.

## INFRAESTRUTURA MULTIPERGUNTA — VALIDADA
- `placementQuestionsForLevel(...)` obtém todas as perguntas de um nível.
- `placementQuestionForLevel(...)` seleciona ciclicamente entre múltiplas perguntas e falha explicitamente se o nível estiver vazio.
- A UI usa esse seletor.
- `PlacementQuestionSelectionTest.kt` cobre filtro, rotação e banco vazio.
- Android CI run #33 / `33459131956`: SUCCESS.

## BANCO INGLÊS — PRIMEIRA EXPANSÃO CONTROLADA VALIDADA
- English (`en`) agora possui exatamente 2 perguntas em cada nível A1, A2, B1, B2, C1 e C2 (12 perguntas no total).
- A segunda pergunta de cada nível foi adicionada sem alterar motor adaptativo, UI, bancos dos outros quatro idiomas ou Supabase.
- Commit de conteúdo: `3a04be3ec4e4ce33ce7f71285b18a0bb276f92b6`.
- Teste `EnglishPlacementBankTest.kt` exige pelo menos 2 perguntas em cada nível e IDs únicos.
- Commit do teste: `50a53e55a0e44c98015ee851c19ec0af0c46adfd`.
- Android CI run #36 / `33513922534`: **SUCCESS**, concluída em 2026-09-01T13:33:08Z. Portanto testes + build do APK passaram com a expansão inglesa.
- Esta expansão melhora variedade do protótipo, mas ainda NÃO representa calibração psicométrica CEFR.

## HISTÓRICO VALIDADO RELEVANTE
- Run #18: bancos iniciais por idioma — SUCCESS.
- Run #19: banco pelo idioma-alvo — SUCCESS.
- Run #22: núcleo adaptativo corrigido — SUCCESS.
- Run #26: CI com testes antes do APK — SUCCESS.
- Run #29: adaptativo ligado à UI — SUCCESS.
- Run #33: seletor multipergunta seguro — SUCCESS.
- Run #36 / `33513922534`: banco inglês com 2 perguntas por nível + testes — SUCCESS.

## PRÓXIMO PASSO EXATO
1. NÃO mexer no Supabase.
2. Não fazer redesign nem gerar arte.
3. Preservar run #36 como baseline validada da expansão inglesa.
4. Continuar expansão uma língua por vez.
5. Próxima língua: Português (`pt`). Adicionar somente uma segunda pergunta por nível A1–C2, sem alterar English, Español, Français, 한국어, motor adaptativo ou UI.
6. Adicionar teste equivalente que exija pelo menos 2 perguntas por nível e IDs únicos no banco português.
7. Validar testes + APK no CI antes de tocar em Español.
8. Não ampliar várias línguas na mesma etapa.
9. Continuar chamando resultado de estimativa/protótipo até validação adequada.

## ARTE / IDENTIDADE VISUAL
- Nenhuma arte ou mascote deve ser gerada/redesenhada por iniciativa do assistente.
- `VISUAL_BIBLE.md` é obrigatória para trabalho visual.
- Chiu do logo = Chihuahua branco fotorealista aprovado; Chiu personagem = cachorro cartoon amarelo aprovado; nunca misturar.

# ISOLAMENTO ABSOLUTO DO SUPABASE
## CHIU KNOW? — ÚNICO SUPABASE PERMITIDO
- Organização `Chiu Know`, ID `aeerqbmrwulxsawhjyvm`.
- Project ref `uskxabsodcnzlovuaurp`, região `sa-east-1`.
- O desenvolvimento Android atual NÃO precisa de Supabase.

## CHIU PLAYER — PROIBIDO NESTA FRENTE
- Organização `Chiu`, ID `nnrwosbnvdvzaoflwxlo`.
- Project ref `hpcbkvbrlwjnwlikmbfb`.
- Nunca escrever nesse backend durante trabalhos do Chiu Know?.

## PRINCÍPIO FINAL
- Estado real vence documentação desatualizada.
- Não usar Supabase se o passo puder ser local.
- Camila não deve receber tarefas de código, terminal, SQL ou conflitos manuais.
