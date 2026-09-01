# CHIU KNOW? — PROJECT STATE

## ATUALIZAÇÃO AUTORITATIVA — 2026-09-01 — PLACEMENT ADAPTATIVO + SELETOR MULTIPERGUNTA VALIDADO

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

## INFRAESTRUTURA PARA MÚLTIPLAS PERGUNTAS POR NÍVEL — VALIDADA
- `PlacementTest.kt` possui `placementQuestionsForLevel(...)` para obter todas as perguntas de um nível.
- Possui `placementQuestionForLevel(...)`, que seleciona de forma cíclica entre múltiplas perguntas e falha explicitamente se o nível estiver sem conteúdo.
- A UI usa esse seletor em vez de `first { level == ... }`.
- Teste `PlacementQuestionSelectionTest.kt` cobre: filtro por nível, rotação entre múltiplas perguntas e erro explícito para banco vazio.
- Commits da infraestrutura: `2e611fcea5be22f6d10ada096633f60b6d439230`, `2532900844919885b14adf57bb689e3d910c30f9` e `24e3d54fb2d3c494acdb98acffd53146a06d1e70`.
- Android CI run #33 / `33459131956`: **SUCCESS**, concluída em 2026-09-01T01:33:38Z.

## HISTÓRICO VALIDADO RELEVANTE
- `1428ccbbafbb4ed3cdf44bc3c6c745e960f4c82b`: bancos iniciais por idioma; run #18 SUCCESS.
- `49f7310001933445000dd08b0fa03796680a4205`: banco pelo idioma-alvo; run #19 SUCCESS.
- `d6153bdfe929690266fd65a2bc6af08b4f6d727c`: núcleo adaptativo corrigido; run #22 SUCCESS.
- `517d7975da49f96c4970cddf49ba28a5e994b7ea`: CI executa testes antes do APK; run #26 SUCCESS.
- `982572b4389651191e504ed01aef82b41376d13a`: adaptativo ligado à UI; run #29 SUCCESS.
- `24e3d54fb2d3c494acdb98acffd53146a06d1e70`: UI passa a usar seletor seguro multipergunta; run #33 SUCCESS.

## PRÓXIMO PASSO EXATO
1. NÃO mexer no Supabase.
2. Não fazer redesign nem gerar arte.
3. Preservar o run #33 como baseline da infraestrutura multipergunta.
4. Expandir conteúdo em ETAPAS, uma língua por vez, começando por English (`en`).
5. Nesta primeira expansão, adicionar somente uma segunda pergunta por nível A1–C2 no banco inglês, sem alterar o motor adaptativo.
6. Adicionar teste que confirme cobertura mínima de duas perguntas por nível no banco inglês.
7. Validar testes + APK no CI antes de tocar em Português, Español, Français ou 한국어.
8. Não ampliar todos os idiomas de uma vez.
9. Continuar chamando o resultado de estimativa/protótipo até validação adequada.

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
