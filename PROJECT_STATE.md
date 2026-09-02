# CHIU KNOW? — PROJECT STATE

## ESTADO AUTORITATIVO — 2026-09-02 14:31 BRT — A1 MULTIHABILIDADE + EVIDÊNCIA + REORDER VALIDADO ATÉ CI #120

Este arquivo é o handoff operacional autoritativo. Em qualquer novo chat: NÃO recomeçar, NÃO inferir estado apenas pela memória e NÃO alterar antes de conferir GitHub real. O estado real do GitHub vence documentação desatualizada.

LEITURA OBRIGATÓRIA: `PROJECT_STATE.md` + `PRODUCT_SPEC.md` JUNTOS antes de qualquer alteração. Para arte/personagens, ler `VISUAL_BIBLE.md`; para referências, `RESEARCH.md`.

## 1. IDENTIDADE TÉCNICA E SEPARAÇÃO ABSOLUTA
- Repo: `Canumori/Chiu-Know`; branch: `main`; Android Kotlin + Jetpack Compose; uso inicialmente privado/restrito ~100 usuários.
- Supabase EXCLUSIVO Chiu Know?: `uskxabsodcnzlovuaurp`, org `Chiu Know` / `aeerqbmrwulxsawhjyvm`, região `sa-east-1`.
- Supabase Chiu Player PROIBIDO: `hpcbkvbrlwjnwlikmbfb`, org `Chiu` / `nnrwosbnvdvzaoflwxlo`. Se aparecer numa operação desta frente, PARAR e não escrever.
- Não misturar Player: código, branches, APKs, workflows, backend, OTA ou decisões.
- Placement/trilha/atividades/evidência/REORDER recentes são locais Android e não tocaram Supabase.

## 2. PRODUTO/PEDAGOGIA — NÃO REDUZIR
Visão: app multilíngue CEFR A1–C2, ensino adaptativo, revisão inteligente, histórias, personagens e tutor IA. Meta é competência real, retenção e uso fora do app, não cliques/XP.
Roadmap inclui interface e idioma-alvo independentes; placement/reteste; resultado geral e futuramente por seis habilidades quando houver evidência válida; trilha A1→C2; FSRS/equivalente permissivo; gamificação sem confundir com domínio; histórias; IA; listening/speaking.
Preservar integralmente os 10 princípios de `PRODUCT_SPEC.md`: compreensão, prática ativa, retrieval/retenção, transferência, feedback útil, progresso≠domínio, seis habilidades, tarefas realmente avançadas em C1/C2, adaptação por evidência, métricas de aprendizagem. Validar fatias pequenas antes de massificar.

## 3. FLUXO FUNCIONAL
`LANGUAGE_SELECTION → PLACEMENT_INTRO → PLACEMENT_TEST → PLACEMENT_RESULT → LEARNING_TRAIL → LEARNING_ACTIVITY`.
Com nível salvo pode seguir à trilha sem refazer placement; reteste disponível. Idiomas: pt/en/es/fr/ko. DataStore local.
Config: `com.chiu.know`, versionCode 1, versionName 0.1.0, minSdk 26, target/compile 35, JVM 17, Compose BOM 2024.12.01, AppCompat 1.7.0, DataStore 1.1.7, JUnit 4.13.2.

## 4. PLACEMENT — PRESERVAR
Local/determinístico; começa B1; acerto/erro estreita A1–C2; resultado do motor adaptativo. NÃO voltar a proporção fixa. Banco: 12/idioma, 2 por nível A1–C2, 5 idiomas, 60 itens, IDs únicos/testados. É estimativa, não certificação. Não inventar seis scores por habilidade: banco atual não sustenta isso.

## 5. TRILHA
`CefrTrail.kt`: COMPLETED/CURRENT/LOCKED e `buildCefrTrail`. Fundação visual/navegação, não mastery. `2121e7ca...` tornou trilha scroll-safe. Preservar; não alterar `CenteredColumn` global para corrigir tela local.

## 6. MODELO DE ATIVIDADE
Skills: GRAMMAR/VOCABULARY/LISTENING/READING/WRITING/SPEAKING. ResponseTypes: MULTIPLE_CHOICE/FILL_IN/REORDER/FREE_TEXT/LISTEN_AND_RESPOND/SPEAK. Metadados incluem id, level, skill, objective, target, responseType, prompt, feedback, reviewKey, acceptedAnswers, `responseOptions`.
`isLearningAnswerCorrect` é comparador determinístico simples (trim/lowercase, mantém acentos); não usar para escrita livre/fala/pronúncia complexa.
`b6cdaf285...` adicionou opções estruturadas; `74e460db...` testes; CI #113 SUCCESS.

## 7. STARTER A1 VALIDADO
Exatamente 7 atividades/idioma = 35 total:
- 2 VOCABULARY FILL_IN;
- 2 GRAMMAR FILL_IN;
- 2 READING FILL_IN;
- 1 GRAMMAR REORDER.
REORDER compartilha reviewKey com grammar FILL_IN por ser transferência do mesmo conhecimento. Continuam 3 reviewKeys/idioma. Não há B1/C2 artificial.
Arquivos: `StarterLearningActivities.kt`, `A1IntegratedLearningActivities.kt`, `A1ReadingActivities.kt`, `A1ReorderActivities.kt`.

REORDER UI: commit `1cfdbf3aa6418eba221a15a781e569fb8e3fe1b2`; tokens por `responseOptions`, seleção por índice, usados somem, desfazer, montagem com espaços, check determinístico; FILL_IN preservado. CI #114 run `33658745087` SUCCESS.
Conteúdo/integracão/testes: `bb14c01e...`, `8d448129...`, `2dfa9721...`, `b85e6c3ce...`; CI #118 run `33659507938` SUCCESS.

## 8. SELETOR POR EVIDÊNCIA
`starterLearningActivityForEvidence`: agrupa candidatos por reviewKey em ordem estável; escolhe alvo com menos tentativas; empate pela ordem curricular; variante = tentativas daquele reviewKey módulo nº variantes. NÃO é mastery/FSRS/progresso CEFR. Acerto/erro não declara domínio.
Base: `571b1dab...`; testes `7a5130c...`; CI #108 SUCCESS. UI `150fbff...`; regressão sintática #109 corrigida estreitamente por `aae3d727...`; #110 SUCCESS.
Preservar congelamento da atividade durante feedback: salvar evidência não troca exercício na mesma composição; sair/reentrar permite seleção nova.

### Proteção REORDER mais recente
Commit `73b734950aa2498e53a83bf71c2683a01bc9bb1b` — `test: protect reorder rotation within grammar evidence`.
CI #120 / run `33660512925`: **COMPLETED / SUCCESS** em 2026-09-02.
O teste prova a rotação do alvo grammar `FILL_IN 1 → FILL_IN 2 → REORDER → ciclo`, e prova que erro conta como tentativa/exposição sem virar mastery. Esse é o último HEAD funcional explicitamente validado antes deste commit documental.

## 9. EVIDÊNCIA LOCAL
`LearningEvidence`: activityId, reviewKey, level, primarySkill, correct, timestamp. DataStore por idioma. Codec: `timestamp|activityId|reviewKey|level|primarySkill|correct`; não guarda resposta bruta; malformed ignorado. Summary registra fatos, não mastery. Commits relevantes: `83a2d6b...`, `b3d1371...`, `1a7c82a...`, `f7ecf3a...`, `2d003e8...`, `31f1289...`, `cacbe5a...`.

## 10. VISUAL — ABSOLUTO
Ler `VISUAL_BIBLE.md` antes de arte.
- Logo/ícone: SOMENTE Chiu realista branco com cabelo castanho escuro bowl-cut, olhos grandes/dentinhos; reutilizar master aprovado, não redesenhar quando identidade exata necessária.
- Universo/atividades/histórias: SEMPRE Chiu cartunesco amarelo/esquisito aprovado. Nunca trocar os dois.
- Elenco: Chiu, Mia, Jurandir, Barto, Lara, Caca, Onça, Perry, Lena. Mosquito = JURANDIR definitivamente; nome antigo só como proveniência histórica.
- Pranchas A/B/C/D aprovadas conforme Visual Bible. Master aprovado imutável. Nova pose = candidata mostrada à usuária antes de integrar; aprovada vira canonical reusable asset.
- Não deformar/recolorir arbitrariamente/misturar estilos. Personagens têm função pedagógica.
- Não declarar prancha PNG/binário integrado ao GitHub/APK sem verificar binário real. Até este estado, nenhum novo binário visual foi integrado ao APK.

## 11. I18N
UI de atividade localizada default/pt/es/fr/ko; commits `15ead396...`, `a1f4caf...`, `b5ec61bc...`, `b47064dc...`, CIs #80–#83 verdes. Não hardcodar interface em inglês sem necessidade.

## 12. REGRESSÕES A NÃO REPETIR
- #102: teste antigo esperava 4 atividades após Reading; produção não era causa; `e33b256...` → #103 verde.
- #109: reconstrução ampla de `ChiuKnowApp.kt` apagou `}` de `PlacementResultScreen`; `aae3d727...` → #110 verde. Preferir patches estreitos e preservar fronteiras de funções.
- Sempre validar CI do HEAD novo; não massificar conteúdo antes da infraestrutura.

## 13. NÃO EXISTE AINDA — NÃO INVENTAR
Mastery real; FSRS/scheduler completo; desbloqueio por retenção; UI dedicada MULTIPLE_CHOICE no learning flow; FREE_TEXT apropriado; listening/áudio real; speaking/ASR/pronúncia; writing real; histórias funcionais; tutor IA; gamificação completa; seis scores válidos no placement; integração binária final dos personagens; conteúdo A1–C2 completo.

## 14. PRÓXIMO PASSO EXATO
Último HEAD funcional validado: `73b734950aa2498e53a83bf71c2683a01bc9bb1b`, CI #120 verde. Este commit documental deve ter seu CI conferido primeiro no próximo chat.
Depois:
1. inspecionar `LearningActivityScreen` e contrato MULTIPLE_CHOICE;
2. criar UI determinística dedicada MULTIPLE_CHOICE, pequena/isolada, sem conteúdo em massa;
3. preservar FILL_IN/REORDER;
4. testar sem infraestrutura pesada desnecessária;
5. só então pequena fatia A1 MULTIPLE_CHOICE nos 5 idiomas se houver função pedagógica clara;
6. atualizar integração/seletor conforme reviewKeys/variantes reais;
7. CI verde antes de avançar;
8. continuar priorizando infraestrutura difícil (response types, revisão/retenção, áudio apropriado, persistência/navegação, assets, estrutura de conteúdo) antes de massificar A1–C2.
MULTIPLE_CHOICE é reconhecimento e não deve dominar o app. ReviewKey representa conhecimento, não formato.

## 15. PRAZO
Plus termina dia 13 segundo a usuária. Até lá, priorizar APK ponta a ponta cada vez mais utilizável, infraestrutura difícil e documentação para continuidade posterior. Não sacrificar pedagogia para fingir A1–C2 completo. Continuidade deve depender do GitHub, não memória do chat.

## 16. PROTOCOLO OBRIGATÓRIO PARA PRÓXIMO CHAT
1. Usar GitHub.
2. Ler INTEIRO `PROJECT_STATE.md` + `PRODUCT_SPEC.md` antes de qualquer alteração.
3. Arte → ler `VISUAL_BIBLE.md`; pesquisa → `RESEARCH.md`.
4. Conferir main HEAD, commits e Actions reais; GitHub real vence docs.
5. CI running/queued do HEAD: conferir antes de empilhar mudança estrutural.
6. Falha: inspecionar job/log/diff e corrigir causa real estreitamente; sem rollback amplo.
7. Usuária não programa: não mandar editar código/terminal/conflitos/ZIP.
8. Fazer pelo GitHub tudo que as ferramentas permitirem.
9. Mudanças pequenas, isoladas, reversíveis, testadas.
10. Não publicar/distribuir/OTA automaticamente.
11. Não tocar Supabase sem necessidade; se necessário, SOMENTE `uskxabsodcnzlovuaurp`.
12. Não inventar estado/features/scores/mastery/conteúdo.
13. Preservar placement adaptativo, trilha scroll-safe, DataStore, evidência, seletor e feedback congelado.
14. Preservar regra dos dois Chius e personagens.
15. Ao “Continue”, avançar por etapas seguras até decisão REAL da usuária; não parar a cada microcommit se CI/ferramentas permitirem; não alegar background.
16. Atualizar este arquivo ao fim de frente relevante com HEAD/CI/próximo passo.

## 17. ARQUIVOS-CHAVE
`PROJECT_STATE.md`, `PRODUCT_SPEC.md`, `VISUAL_BIBLE.md`, `RESEARCH.md`, `.github/workflows/android-ci.yml`, `ChiuKnowApp.kt`, `LearningActivity.kt`, `StarterLearningActivities.kt`, `A1IntegratedLearningActivities.kt`, `A1ReadingActivities.kt`, `A1ReorderActivities.kt`, `StarterReviewSelection.kt`, `LearningEvidence.kt`, `LearningEvidenceCodec.kt`, `LearningEvidenceSummary.kt`; testes especialmente `StarterReviewSelectionTest.kt`, `StarterLearningActivitiesIntegrationTest.kt`, `LearningActivityTest.kt`, `A1ReorderActivitiesTest.kt`.

## 18. MARCOS FINAIS DESTE HANDOFF
- `b85e6c3...`: 7 atividades starter/idioma; CI #118 SUCCESS.
- `5d44f438793408fbbbddbfc586f637585acc8a6e`: refresh anterior do handoff; CI #119 / run `33659861778` SUCCESS.
- `73b734950aa2498e53a83bf71c2683a01bc9bb1b`: proteção da rotação FILL_IN/FILL_IN/REORDER e erro como tentativa; CI #120 / run `33660512925` SUCCESS.
- O commit que grava este documento passa a ser o novo HEAD documental; conferir seu CI no próximo chat antes de prosseguir.
