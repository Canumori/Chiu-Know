# CHIU KNOW? — PROJECT STATE

## ESTADO AUTORITATIVO — 2026-09-02 20:45 BRT — VOZ DO CHIU APROVADA PRIVADAMENTE; CÓDIGO VALIDADO ATÉ CI #139

Este arquivo é o handoff operacional autoritativo. Em qualquer novo chat: NÃO recomeçar, NÃO inferir estado apenas pela memória e NÃO alterar antes de conferir GitHub real. O estado real do GitHub vence documentação desatualizada.

LEITURA OBRIGATÓRIA: `PROJECT_STATE.md` + `PRODUCT_SPEC.md` JUNTOS antes de qualquer alteração. Para arte/personagens, ler `VISUAL_BIBLE.md`; para referências, `RESEARCH.md`.

## 1. IDENTIDADE TÉCNICA E SEPARAÇÃO ABSOLUTA
- Repo: `Canumori/Chiu-Know`; branch: `main`; Android Kotlin + Jetpack Compose; uso inicialmente privado/restrito ~100 usuários.
- O repositório está PUBLICAMENTE visível para usar minutos ilimitados do GitHub Actions; não mudar a visibilidade por suposição. Não existe arquivo `LICENSE` neste estado: visibilidade pública não torna automaticamente o código GPL ou permissivamente licenciado.
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
Exatamente 8 atividades/idioma = 40 total:
- 2 VOCABULARY FILL_IN;
- 2 GRAMMAR FILL_IN;
- 2 READING FILL_IN;
- 1 GRAMMAR REORDER;
- 1 READING MULTIPLE_CHOICE.
REORDER compartilha reviewKey com grammar FILL_IN. MULTIPLE_CHOICE compartilha reviewKey com reading FILL_IN. Formato diferente não cria artificialmente conhecimento/mastery novo. Continuam 3 reviewKeys/idioma. Não há B1/C2 artificial.
Arquivos: `StarterLearningActivities.kt`, `A1IntegratedLearningActivities.kt`, `A1ReadingActivities.kt`, `A1ReorderActivities.kt`, `A1MultipleChoiceActivities.kt`.

REORDER UI: commit `1cfdbf3aa6418eba221a15a781e569fb8e3fe1b2`; tokens por `responseOptions`, seleção por índice, usados somem, desfazer, montagem com espaços, check determinístico; FILL_IN preservado. CI #114 run `33658745087` SUCCESS.
MULTIPLE_CHOICE UI dedicada: commit `a407785b503c46e5c3d2fa017b65248e9e8b693b`; opções em botões, seleção visível, resposta determinística; FILL_IN/REORDER preservados. CI #122 run `33662794556` SUCCESS.
Fatia controlada nos cinco idiomas + testes de contrato/integração/rotação: commit `c386afff4d99fa9a3a08022ac1c60e7c0a6d06b9`; CI #123 run `33663382962` SUCCESS. MULTIPLE_CHOICE permanece reconhecimento minoritário, não formato dominante.

## 8. SELETOR POR EVIDÊNCIA
`starterLearningActivityForEvidence`: agrupa candidatos por reviewKey em ordem estável; escolhe alvo com menos tentativas; empate pela ordem curricular; variante = tentativas daquele reviewKey módulo nº variantes. NÃO é mastery/FSRS/progresso CEFR. Acerto/erro não declara domínio.
Base: `571b1dab...`; testes `7a5130c...`; CI #108 SUCCESS. UI `150fbff...`; regressão sintática #109 corrigida estreitamente por `aae3d727...`; #110 SUCCESS.
Preservar congelamento da atividade durante feedback: salvar evidência não troca exercício na mesma composição; sair/reentrar permite seleção nova.

### Proteção REORDER mais recente
Commit `73b734950aa2498e53a83bf71c2683a01bc9bb1b` — `test: protect reorder rotation within grammar evidence`.
CI #120 / run `33660512925`: **COMPLETED / SUCCESS** em 2026-09-02.
O teste prova a rotação do alvo grammar `FILL_IN 1 → FILL_IN 2 → REORDER → ciclo`, e prova que erro conta como tentativa/exposição sem virar mastery. Esse é o último HEAD funcional explicitamente validado antes deste commit documental.

## 9. EVIDÊNCIA LOCAL
`LearningEvidence`: activityId, reviewKey, level, primarySkill, correct, timestamp. DataStore por idioma. Codec: `timestamp|activityId|reviewKey|level|primarySkill|correct`; não guarda resposta bruta; malformed ignorado. `LearningEvidenceSummary` descreve uma atividade. `ReviewEvidenceSummary` agrega por nível + reviewKey através de variantes, mantendo fatos separados; commit `e0351805...`, CI #124 SUCCESS.

Infraestrutura de revisão própria, sem dependência GPL:
- `ReviewScheduleState`/`ReviewScheduler`: fronteira persistível versionada, separada de UI/evidência/mastery; `824ecdd...`, CI #126 SUCCESS.
- `PrivateFsrsScheduler`: núcleo FSRS-6 implementado internamente a partir das fórmulas públicas atuais; o app mapeia honestamente incorreto→Again e correto→Good, sem inventar Hard/Easy. Primeira tentativa `5b6b1eba...` teve somente erro de compilação no teste (#127); correção estreita `01a6b79...`, CI #128 SUCCESS.
- `ReviewScheduleCodec`: round-trip versionado, malformed/versão desconhecida ignorados, mantém estado mais novo por reviewKey; `9829a95...`, CI #129 SUCCESS.
- Persistência DataStore junto da evidência, ainda sem mudar seleção visível; `dfd7c7a...`, CI #130 SUCCESS.
- `StarterReviewQueue`: distingue DUE_REVIEW, NEW_TARGET, NONE_DUE e NO_CONTENT; prioriza vencido, depois conhecimento sem estado; `a6c0b1a...`, CI #131 SUCCESS.
- Decisão aprovada pela usuária: quando não houver revisão vencida, mostrar “Revisões em dia”, próximo horário e prática opcional; prática opcional inicialmente não altera scheduler.
- Fila conectada à UI nos cinco idiomas preservando feedback congelado; prática opcional dá feedback sem persistir evidência/agendamento; `e088f92...`, CI #133 / run `33666462350` SUCCESS.
- Migração: se houver evidência histórica e nenhum schedule salvo, reconstruir cronologicamente por reviewKey sem apagar histórico; `387f8fd...`, CI #134 / run `33666723947` SUCCESS.

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
Mastery real; otimização personalizada dos parâmetros FSRS; desbloqueio por retenção; FREE_TEXT apropriado; listening/áudio real; speaking/ASR/pronúncia; writing real; histórias funcionais; tutor IA; gamificação completa; seis scores válidos no placement; integração binária final dos personagens; conteúdo A1–C2 completo.

## 14. PRÓXIMO PASSO EXATO
HEAD atual antes deste commit documental: `addf6ef8fdb480a9f4893ff1a2cb80c389cad211`, CI #139 / run `33670744666` SUCCESS.

DECISÃO DE VOZ REGISTRADA:
- a usuária rejeitou todas as três amostras robóticas produzidas pelo TTS do Android;
- nenhuma delas é voz oficial e elas não devem ser usadas como identidade do Chiu;
- a usuária gravou uma interpretação própria e autorizou criar variações privadas;
- o estilo animado foi escolhido;
- uma segunda gravação corrigiu uma palavra/plural;
- o acabamento aprovado remove aproximadamente quatro segundos anteriores à fala, ruídos de manuseio, ruído contínuo e a sobra final;
- a candidata final aprovada chama-se `Chiu-animada-recorte-final.m4a`, tem cerca de 15,4 segundos e permanece em armazenamento privado fora do GitHub;
- esta aprovação define a identidade vocal candidata do Chiu, mas o binário ainda NÃO foi integrado ao APK;
- não colocar a gravação original nem a voz derivada no repositório público;
- não usar a gravação para outros personagens, clonagem externa ou treinamento sem nova autorização expressa.

BLOQUEIO/DECISÃO REAL ANTES DA INTEGRAÇÃO:
O repositório é público. Embutir o arquivo de voz diretamente em um commit tornaria o áudio publicamente baixável. A integração precisa escolher um caminho privado:
1. armazenar no Supabase exclusivo do Chiu Know? com acesso privado/controlado; ou
2. manter fora do backend e criar um processo de build privado que injete o áudio sem versioná-lo publicamente.

Não tocar no Supabase do Chiu Player. Se a opção 1 for autorizada, usar SOMENTE `uskxabsodcnzlovuaurp`, ler a skill Supabase, conferir projeto real, desenhar bucket/políticas e não presumir autenticação existente. Não remover a prévia TTS nem criar conteúdo LISTEN_AND_RESPOND real até a estratégia privada estar decidida e testada.

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
`PROJECT_STATE.md`, `PRODUCT_SPEC.md`, `VISUAL_BIBLE.md`, `RESEARCH.md`, `.github/workflows/android-ci.yml`, `ChiuKnowApp.kt`, `LearningActivity.kt`, `StarterLearningActivities.kt`, `A1IntegratedLearningActivities.kt`, `A1ReadingActivities.kt`, `A1ReorderActivities.kt`, `A1MultipleChoiceActivities.kt`, `StarterReviewSelection.kt`, `LearningEvidence.kt`, `LearningEvidenceCodec.kt`, `LearningEvidenceSummary.kt`, `ReviewEvidenceSummary.kt`, `ReviewScheduleState.kt`, `PrivateFsrsScheduler.kt`, `ReviewScheduleCodec.kt`, `ReviewSchedulePersistence.kt`, `StarterReviewQueue.kt`, `LearningAudioPrompt.kt`; testes especialmente `StarterReviewSelectionTest.kt`, `StarterLearningActivitiesIntegrationTest.kt`, `LearningActivityTest.kt`, `A1ReorderActivitiesTest.kt`.

## 18. MARCOS FINAIS DESTE HANDOFF
- `b85e6c3...`: 7 atividades starter/idioma; CI #118 SUCCESS.
- `5d44f438793408fbbbddbfc586f637585acc8a6e`: refresh anterior do handoff; CI #119 / run `33659861778` SUCCESS.
- `73b734950aa2498e53a83bf71c2683a01bc9bb1b`: proteção da rotação FILL_IN/FILL_IN/REORDER e erro como tentativa; CI #120 / run `33660512925` SUCCESS.
- `a407785b503c46e5c3d2fa017b65248e9e8b693b`: UI MULTIPLE_CHOICE dedicada; CI #122 / run `33662794556` SUCCESS.
- `c386afff4d99fa9a3a08022ac1c60e7c0a6d06b9`: fatia A1 MULTIPLE_CHOICE nos cinco idiomas e testes; CI #123 / run `33663382962` SUCCESS.
- `e0351805eca7ff35400cbeb76f50171f40850ab1`: evidência agregada por reviewKey sem mastery; CI #124 / run `33663675090` SUCCESS.
- `824ecdd0c3b679277dc18e401474ef0657205ccc`: fronteira própria do scheduler; CI #126 SUCCESS.
- `5b6b1eba9735aa11c3dba95049f8595999982678`: núcleo FSRS-6; CI #127 falhou por assinatura de assert no teste, sem regressão de produção.
- `01a6b79ba85ed4376303a0595c2dd23428aeffe7`: correção estreita do teste; CI #128 SUCCESS.
- `9829a95e4d42cfab51c9ae7c76954e306a85075e`: codec versionado; CI #129 SUCCESS.
- `dfd7c7a54c02ade892a266e2bdbf9b096c8ea5a5`: persistência junto da evidência; CI #130 SUCCESS.
- `a6c0b1ad996524efd3bd9a334fdb86302fb48c7a`: fila conservadora; CI #131 / run `33665698425` SUCCESS.
- `802b22f5160d90d2ba22f5c5c7bded32b2e5d7f0`: documentação/licenças/decisão; CI #132 / run `33665991859` SUCCESS.
- `e088f92bd6edda99895b1e5217b7d866b12146b6`: fila conectada + prática opcional localizada; CI #133 / run `33666462350` SUCCESS.
- `387f8fde97b92a025aebdd477aad24b44519d277`: reconstrução de schedule por evidência histórica; CI #134 / run `33666723947` SUCCESS.
- `12f47a176470f43439ded27a70708ee4ada5131d`: documentação da revisão ativa/migração e handoff de áudio; CI #135 / run `33666911683` SUCCESS.
- `5dff578c2ae9e5d351b77935975fae4135899111`: contrato neutro de áudio, vínculo obrigatório em LISTEN_AND_RESPOND e estado determinístico de reprodução; CI #136 / run `33667291082` SUCCESS.
- `b491a251b368ea431bdc160b547542e80724aa99`: documentação do contrato neutro e decisão de voz; CI #137 / run `33667581198` SUCCESS.
- `6d67f9618d7a90683f9b9e4a6e5b22d07d9034e7`: prévia temporária com três estilos de voz do Android nos cinco idiomas; CI #138 / run `33670372566` SUCCESS; artifact `9862277634`.
- `addf6ef8fdb480a9f4893ff1a2cb80c389cad211`: documentação da prévia TTS e handoff; CI #139 / run `33670744666` SUCCESS.
- Voz privada aprovada fora do GitHub: `Chiu-animada-recorte-final.m4a`; não integrar no repo público.
- O commit que grava este documento passa a ser o novo HEAD documental; conferir seu CI antes de prosseguir.
