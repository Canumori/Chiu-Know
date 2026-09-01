# CHIU KNOW? — PROJECT STATE

## ATUALIZAÇÃO AUTORITATIVA — 2026-09-01 — PLACEMENT ADAPTATIVO INTEGRADO E VALIDADO NO CI

Este arquivo é a fonte autoritativa de continuidade do Chiu Know?, mas o estado real do GitHub e do Supabase sempre vence informação desatualizada.

## ESTADO ATUAL DO ANDROID
- Repositório: `Canumori/Chiu-Know`; branch `main`.
- Kotlin + Jetpack Compose; namespace/applicationId `com.chiu.know`.
- minSdk 26, targetSdk/compileSdk 35, Java/JVM 17.
- versionCode 1, versionName 0.1.0.
- Compose BOM 2024.12.01; AppCompat 1.7.0; DataStore Preferences 1.1.7.
- JUnit 4.13.2 apenas em `testImplementation`; não entra no app final.

## FLUXO ATUAL
`LANGUAGE_SELECTION → PLACEMENT_INTRO → PLACEMENT_TEST → PLACEMENT_RESULT`.

O placement agora usa o núcleo adaptativo local na interface:
- bancos-protótipo separados para Português, English, Español, Français e 한국어;
- banco selecionado pelo idioma-alvo;
- começa em B1;
- a resposta determina a próxima faixa pedida pelo motor adaptativo;
- a pergunta exibida é a pergunta existente correspondente ao `currentLevel` do motor;
- o resultado final vem do estado adaptativo, e não da antiga proporção fixa de seis respostas;
- mantém tentar novamente, alterar idiomas, DataStore e aviso explícito de protótipo/estimativa;
- continua NÃO sendo teste CEFR calibrado ou certificação oficial.

Com os bancos atuais há somente uma pergunta por nível; portanto esta integração valida o fluxo e a arquitetura adaptativa, NÃO a qualidade psicométrica do placement.

## IDIOMAS E PERSISTÊNCIA
Idiomas de interface/alvo: Português (`pt`), English (`en`), Español (`es`), Français (`fr`) e 한국어 (`ko`).
A interface usa recursos traduzidos e `AppCompatDelegate.setApplicationLocales(...)`.
`interface_language_code` e `target_language_code` são persistidos localmente via DataStore.

## HISTÓRICO VALIDADO RELEVANTE
- `1428ccbbafbb4ed3cdf44bc3c6c745e960f4c82b`: bancos iniciais por idioma; run #18 / `33449564848` SUCCESS.
- `49f7310001933445000dd08b0fa03796680a4205`: banco pelo idioma-alvo; run #19 / `33449597226` SUCCESS.
- `2539ef3bf6fdbe8a532df8d26ccefa18b3e3b3cf`: núcleo adaptativo local.
- Run #21 / `33457791947`: FAILURE localizada por `firstIndex` inexistente.
- `d6153bdfe929690266fd65a2bc6af08b4f6d727c`: correção mínima; run #22 / `33458221953` SUCCESS.
- `3709b997dae8101c4cec17b338ddf2b1d75fc3e7`: JUnit somente em testImplementation.
- `29edf362a478e87c8d6c81ec9b1e6203b576faa9`: testes do motor (início B1, caminho correto→C2, incorreto→A1, estabilidade final).
- `517d7975da49f96c4970cddf49ba28a5e994b7ea`: CI executa `:app:testDebugUnitTest` antes do APK.
- Run #26 / `33458686503`, job `99704030435`: SUCCESS completo — testes + APK + artifact.
- `982572b4389651191e504ed01aef82b41376d13a`: `feat: wire adaptive placement into UI`.
- Run #29 / `33458895765`: **SUCCESS**, concluída em 2026-09-01T01:30:04Z. Integração adaptativa à UI compilou com testes unitários e build do APK sob o workflow atual.

## PRÓXIMO PASSO EXATO
1. NÃO mexer no Supabase.
2. Não fazer redesign nem gerar arte.
3. Preservar a integração adaptativa validada pelo run #29 como nova baseline funcional.
4. Próxima mudança deve ser pequena e focada na QUALIDADE DO BANCO: ampliar as perguntas por nível/idioma para que o motor adaptativo não dependa de uma única pergunta por faixa.
5. Antes de ampliar todos os cinco idiomas de uma vez, definir uma estrutura local que suporte múltiplas perguntas por nível sem quebrar o fluxo atual.
6. Adicionar testes para seleção de pergunta por nível e para impedir acesso a banco vazio.
7. Validar essa infraestrutura no CI antes de começar uma expansão grande de conteúdo.
8. Só depois ampliar os bancos de conteúdo por idioma em etapas controladas.
9. Continuar chamando todo resultado de estimativa/protótipo até validação adequada.

## ARTE / IDENTIDADE VISUAL
- Nenhuma arte ou mascote deve ser gerada, redesenhada ou substituída por iniciativa do assistente.
- `VISUAL_BIBLE.md` é obrigatória para qualquer trabalho visual.
- Chiu do logo = Chihuahua branco fotorealista aprovado com cabelo castanho; reutilizar master aprovado.
- Chiu personagem do universo = cachorro cartoon amarelo aprovado; nunca misturar os dois.

# ISOLAMENTO ABSOLUTO DO SUPABASE
## CHIU KNOW? — ÚNICO SUPABASE PERMITIDO
- Organização `Chiu Know`, ID `aeerqbmrwulxsawhjyvm`.
- Project ref `uskxabsodcnzlovuaurp`, região `sa-east-1`.
- O desenvolvimento Android atual NÃO precisa de Supabase.

## CHIU PLAYER — PROIBIDO NESTA FRENTE
- Organização `Chiu`, ID `nnrwosbnvdvzaoflwxlo`.
- Project ref `hpcbkvbrlwjnwlikmbfb`.
- Nunca escrever nesse backend durante trabalhos do Chiu Know?.

## PROTOCOLO FUTURO
Antes de qualquer escrita futura no Supabase, reconectar se necessário e o assistente deve primeiro confirmar por leitura a organização/projeto corretos. Para Chiu Know?, somente `uskxabsodcnzlovuaurp`. Se aparecer `hpcbkvbrlwjnwlikmbfb`, parar imediatamente.

## PRINCÍPIO FINAL
- Estado real vence documentação desatualizada.
- Não usar Supabase se o passo puder ser local.
- Camila não deve receber tarefas de código, terminal, SQL ou conflitos manuais.
