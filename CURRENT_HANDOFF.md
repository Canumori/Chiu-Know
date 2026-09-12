# CHIU KNOW? — CURRENT HANDOFF


## AUTORITATIVO — 2026-09-12 — SUPERSEDE O BLOCO #394 ABAIXO

Estado real confirmado antes desta escrita:
- HEAD `f6b50ba1610f0118d715bd63b45eed61d98525f4`;
- commit `docs: record practice-now flow through CI 465`;
- Android CI #466, run `34659772126`: `COMPLETED / SUCCESS` no SHA exato.

Decisão de produto resolvida:
- a usuária aprovou explicitamente a opção 1;
- após a compreensão final da praça, o app mostra a transição localizada **Praticar agora**;
- a ação entra em `AppStep.LEARNING_ACTIVITY` e reutiliza a fila normal;
- revisões vencidas continuam prioritárias;
- sem revisão vencida à frente, a compreensão observada encaminha ao primeiro passo de recuperação ainda não observado do mesmo second-transfer;
- a sequência protegida continua `MULTIPLE_CHOICE → REORDER → FILL_IN`;
- existe saída clara para voltar à trilha e proteção contra toque repetido.

Commits verdes:
- `5aa93904c9d9846a91a657847c850751a0824a1e` — textos EN/PT/ES/FR/KO — CI #462 SUCCESS;
- `c71ebf3e83ffc8baa00920f15b8c1c7184b0ebdd` — `PracticeNowScreen` — CI #463 SUCCESS;
- `e55fc120821f207f84bcdaab2e8bf0a5d3b04886` — ligação praça → prática normal — CI #464 SUCCESS;
- `f33fa1c7143a8b92256bf443b4bffa62f2fc65a8` — teste de handoff nos cinco idiomas — CI #465 SUCCESS;
- `f6b50ba1610f0118d715bd63b45eed61d98525f4` — checkpoint — CI #466 SUCCESS.

Auditoria de continuidade após #466:
- resposta correta já fica bloqueada para edição;
- submissão, Continue e Back to path normais já respeitam `pendingLearningPersistenceCount`;
- Continue normal apenas limpa `feedbackActivity` depois da persistência;
- Continue de prática opcional já possui guarda por rodada;
- avanço de compreensão narrativa já valida índice e persistência;
- transições Next story e Practice now possuem guarda local;
- nenhum defeito foi comprovado nesses seams, portanto nenhum código funcional foi alterado.

Proteções permanentes:
- não criar fila, scheduler, FSRS, persistência ou mastery paralelos;
- não alterar a separação da prática opcional;
- não tocar em voz privada, Supabase, Chiu Player ou assets sem o gate correspondente;
- a escolha Praticar agora está resolvida e não deve ser perguntada novamente;
- o próximo passo de produto exige escolher uma frente concreta; não fabricar trabalho apenas para continuar produzindo commits.

`PROJECT_STATE_CURRENT.md` contém o registro operacional detalhado. O bloco antigo que começa no CI #394 permanece abaixo apenas como histórico e não define mais o estado atual.

## AUTORITATIVO — 2026-09-09 — SUPERSEDE O HANDOFF ANTIGO

Este arquivo substitui o handoff antigo que ainda estava parado no CI #345. Ele registra o estado real e recente do projeto até o código verde no CI #394.

A fonte final da verdade continua sendo sempre o GitHub real em `main` + Android CI do SHA exato. Nunca confiar apenas em memória de chat, em resumo antigo ou no topo histórico de `PROJECT_STATE.md`.

`PROJECT_STATE.md` é muito grande e pode ser retornado truncado. **NUNCA sobrescrever `PROJECT_STATE.md` a partir de uma leitura truncada.** O arquivo operacional compacto é `PROJECT_STATE_CURRENT.md`; este `CURRENT_HANDOFF.md` é o complemento mais recente e, nesta data, contém fatos posteriores ao checkpoint documental que registra verde até o CI #392.

---

# 1. REPOSITÓRIO / BRANCH / REGRA ZERO

- Repositório: `Canumori/Chiu-Know`
- Branch autoritativa: `main`
- Repo atualmente público para aproveitar minutos de GitHub Actions; isso não autoriza publicar voz privada, segredos ou dados sensíveis.
- Nunca reiniciar o projeto.
- Nunca recriar arquitetura já existente sem provar regressão real.
- Nunca pedir à usuária para programar, usar terminal ou resolver conflito manualmente se as ferramentas conectadas puderem executar o trabalho.

## GATE OBRIGATÓRIO ANTES DE QUALQUER WRITE

1. Buscar o HEAD real de `main`.
2. Buscar o Android CI correspondente **exatamente** ao SHA desse HEAD.
3. Se CI estiver `queued` ou `in_progress`: não escrever nada; parar no gate.
4. Se CI falhar: inspecionar jobs/logs e corrigir apenas a falha real.
5. Se CI estiver `success`: reler cada arquivo a alterar e usar o SHA atual do blob.
6. Fazer uma única mudança pequena, reversível e testável.
7. Depois do commit, verificar o Android CI do novo SHA exato.
8. Não empilhar produção + teste + docs atrás de CI em andamento.

Se falhar somente infraestrutura externa do GitHub depois de testes/build terem passado, não alterar código de produto. Inspecionar o passo real; rerun no mesmo SHA é aceitável quando os logs provarem falha externa/transitória.

---

# 2. HEAD REAL CONFIRMADO ANTES DESTE HANDOFF

HEAD confirmado imediatamente antes desta atualização documental:

- SHA: `d880f924008d11ea331bb8b0c553a0678e8d6559`
- mensagem: `fix: serialize learning retries with persistence`
- Android CI #394
- run: `34372882404`
- estado: `COMPLETED / SUCCESS`

Commit pai:

- `ab01d129277ed3a4c905bc9ffa35e241c9b8ea24`
- `docs: record green state through CI 392`
- Android CI #393, run `34357428947`: `COMPLETED / SUCCESS`.

IMPORTANTE: este handoff cria um novo commit documental depois de `d880f924...`. Portanto o próximo chat **NÃO deve assumir que `d880f924...` ainda é HEAD**; deve buscar `main` novamente e conferir o CI do novo commit documental antes de qualquer write.

---

# 3. SEQUÊNCIA RECENTE PROTEGIDA — CI #384 A #394

## CI #384 — SUCCESS
`5e5a8e9672e9979f721a099b1854ee6a9d8bb4ae`
`fix: prevent duplicate learning attempt submission`

Verify passou a ficar desabilitado quando `checked == true`, impedindo reenvio óbvio da mesma tentativa.

## CI #385 — SUCCESS
`a1e8f03000b8708f2fa09a37c57efb630baa7740`
`fix: guard rapid duplicate learning submission`

Adicionou guard no handler `if (!checked)` para fechar a janela de double tap antes da recomposição.

## CI #386 — SUCCESS
`f8eedfa0f3b49c141a97be85b98ce4d347a828c5`
`fix: refresh learning queue when review becomes due`

A fila `NONE_DUE` passa a acordar em `nextDueAtEpochMillis` e recomputar sem exigir sair/entrar na tela. Não cria scheduler novo e não muda cálculo de due date.

## CI #387 — mesmo SHA, tentativa 2 SUCCESS
`1efce976987aaa00707065a00bbea5e95cce7681`
`fix: guard rapid duplicate placement submission`

Tentativa 1 falhou apenas no `actions/upload-artifact` com HTTP 403 externo depois de testes e build terem passado. Nenhum código foi alterado. Rerun do mesmo SHA terminou verde.

Placement agora mantém flag de submissão por `question.id`; primeira escolha fecha a questão e nova pergunta reseta pelo ID.

## CI #388 — SUCCESS
`3158be3f79237aa98220838dd65003212b0890e4`
`docs: record green state through CI 387`

## CI #389 — SUCCESS
`491112b4c54736e48fce1d42404b5c3a83a5d374`
`fix: require changed multiple-choice answer for retry`

Depois do feedback, tocar de novo na mesma opção MULTIPLE_CHOICE não reseta `checked`. Selecionar opção diferente é mudança real e reseta `checked=false`, permitindo retry genuíno.

## CI #390 — SUCCESS
`82683504452ae606d4327f0a0508cd639da8e30f`
`docs: record green state through CI 389`

## CI #391 — SUCCESS
`0b37447ccdabfde5f8b4699605468ca37dffcfdb`
`fix: keep learning feedback visible after persistence`

Foi corrigido um problema real: a tentativa era persistida imediatamente e a fila podia recalcular/trocar de atividade antes de o aluno ler o feedback.

Agora, no aprendizado normal, a atividade submetida fica presa em `feedbackActivity` enquanto o feedback está na tela. A persistência continua imediata; apenas a apresentação visual fica estável até o aluno escolher sair/continuar.

Prática opcional não entrou nessa persistência.

## CI #392 — SUCCESS
`081725bd95c685058da003f9a8bb0e9e04b2e52d`
`feat: continue learning after correct persisted answer`

Depois de resposta correta no aprendizado normal, a tela passou a oferecer `Continue`, evitando volta obrigatória à trilha entre todas as atividades.

Semântica protegida:
- feedback continua visível;
- Continue só pode ser usado quando persistência da tentativa terminou;
- Continue apenas limpa `feedbackActivity` e devolve seleção à fila já existente;
- review-first continua sendo decidido pela fila existente;
- erro continua permitindo corrigir e tentar de novo;
- Back to path continua disponível;
- prática opcional continua fora da persistência/FSRS.

## CI #393 — SUCCESS
`ab01d129277ed3a4c905bc9ffa35e241c9b8ea24`
`docs: record green state through CI 392`

Atualizou `PROJECT_STATE_CURRENT.md` até o fluxo pós-feedback verde.

## CI #394 — SUCCESS — CÓDIGO MAIS RECENTE ANTES DESTE HANDOFF
`d880f924008d11ea331bb8b0c553a0678e8d6559`
`fix: serialize learning retries with persistence`

Problema fechado: após um erro, o usuário podia alterar a resposta enquanto a gravação anterior ainda estava pendente e chegar a uma nova tentativa concorrente. Agora `LearningActivityScreen` recebe `canSubmit`.

No aprendizado normal:
- `canSubmit = pendingLearningPersistenceCount == 0`;
- Verify exige resposta não vazia, `!checked` e `canSubmit`;
- o próprio click handler também exige `!checked && canSubmit`;
- portanto retry normal só pode ser persistido depois que a persistência da tentativa anterior terminou.

Na prática opcional:
- `canSubmit = true` porque ela deliberadamente não persiste evidência/schedule;
- não transformar prática opcional em tentativa normal só para uniformizar UI.

`pendingLearningPersistenceCount` fica no escopo de `AppStep.LEARNING_ACTIVITY`, chaveado por idioma-alvo e nível estimado, incrementa antes do DataStore edit e decrementa em `finally`.

---

# 4. CÓDIGO ATUAL DO FLUXO DE APRENDIZAGEM — NÃO REGREDIR

Arquivo principal:
`app/src/main/java/com/chiu/know/ui/ChiuKnowApp.kt`

No estado verde #394:

- `queueRefreshTick` controla recomputação temporal de reviews due;
- `queue` vem de `learningActivityQueueSelection(...)`;
- `optionalPracticeRequested` separa prática extra;
- `feedbackActivity` fixa a atividade respondida enquanto o feedback está sendo lido;
- `pendingLearningPersistenceCount` serializa persistência normal e impede Continue/retry antes da gravação terminar;
- `activity = if (optionalPracticeRequested) optionalPracticeActivity else feedbackActivity ?: queue.activity`;
- tentativa normal chama a cadeia genérica de correctness/evidence/scheduler;
- prática opcional não persiste tentativa nem schedule;
- `canSubmit` normal exige ausência de persistência pendente;
- `canContinue` normal exige ausência de persistência pendente;
- Continue normal limpa apenas `feedbackActivity`, fazendo a fila corrente decidir o próximo estado.

Não inventar outro estado de progressão, outra fila, outro scheduler ou outro storage para resolver problemas nessa tela.

---

# 5. FILA / REVIEW-FIRST / FSRS — ARQUITETURA PROTEGIDA

Produção principal:
`app/src/main/java/com/chiu/know/model/LearningActivityQueue.kt`

Guardas:
- `LearningActivityQueueTest.kt`
- `LearningActivityQueuePersistenceIntegrationTest.kt`

Cadeia normal de tentativa:
`isLearningAnswerCorrect(...) → learningEvidenceFor(...) → encodeLearningEvidence(...) → updateReviewScheduleStateSet(...)`

`ReviewScheduleState` continua genérico por `reviewKey`.
Variantes do mesmo alvo compartilham o mesmo reviewKey/schedule.
Não criar outro scheduler ou FSRS paralelo.

Prioridade A1 composta:
1. due starter review;
2. due second-transfer review;
3. second-transfer new work;
4. starter new target;
5. NONE_DUE;
6. NO_CONTENT.

A2/B1/B2/C1/C2 continuam delegando ao comportamento starter existente.

Erro = tentativa/exposição; não significa mastery.
Acerto imediato também não prova mastery.

---

# 6. A1 SECOND TRANSFER — PRAÇA — CONTEÚDO PROTEGIDO

Narrativa:
`A1SecondTransferNarrativeMicroUnit.kt`

Exatamente 6 beats, EN/PT/ES/FR/KO, Barto pergunta e Chiu responde.

EN:
`Hello, Chiu!` / `Hello, Barto!` / `Where do you live?` / `I live in Rio.` / `What do you like?` / `I like coffee.`

PT:
`Olá, Chiu!` / `Olá, Barto!` / `Onde você mora?` / `Eu moro no Rio.` / `Do que você gosta?` / `Eu gosto de café.`

ES:
`¡Hola, Chiu!` / `¡Hola, Barto!` / `¿Dónde vives?` / `Vivo en Río.` / `¿Qué te gusta?` / `Me gusta el café.`

FR:
`Bonjour, Chiu !` / `Bonjour, Barto !` / `Où est-ce que tu habites ?` / `J’habite à Rio.` / `Qu’est-ce que tu aimes ?` / `J’aime le café.`

KO:
`안녕하세요, 치우!` / `안녕하세요, 바르토!` / `어디에 살아요?` / `리우에 살아요.` / `무엇을 좋아해요?` / `커피를 좋아해요.`

Residence e preference preservam progressão:
`context/comprehension → MULTIPLE_CHOICE → REORDER → FILL_IN`.

`A1SecondTransferLearningUnit.kt` liga narrativa + compreensão + residence + preference.
Residence/preference são alvos separados.
Novo trabalho desbloqueia por exposição aos reviewKeys starter ligados; correção não é exigida.
Não pular automaticamente para FREE_TEXT.

---

# 7. PRÁTICA OPCIONAL — REGRA ABSOLUTA

Produção:
`LearningOptionalPractice.kt`

Teste:
`LearningOptionalPracticeTest.kt`

Regras já verdes:
- A2–C2 preservam comportamento starter;
- A1 fica starter-only até completar exposição da sequência second-transfer;
- depois podem entrar variantes fortes REORDER/FILL_IN do second transfer;
- é balanceamento de exposição, não mastery;
- NÃO persiste `LearningEvidence`;
- NÃO chama `updateReviewScheduleStateSet(...)`;
- NÃO cria/muta FSRS schedule;
- só aparece a partir de NONE_DUE;
- nunca outranka due review.

No CI #394, prática opcional continua podendo submeter sem `pendingLearningPersistenceCount` justamente porque não possui persistência de tentativa.

---

# 8. LEARNINGACTIVITY — CONTRATO REAL

`LearningActivity` NÃO possui propriedade `languageCode`.

Campos atuais relevantes:
- `id`
- `level`
- `primarySkill`
- `learningObjective`
- `knowledgeTarget`
- `responseType`
- `prompt`
- `feedback`
- `reviewKey`
- `acceptedAnswers`
- `responseOptions` quando aplicável
- `audioPromptId` opcional

Quando necessário, filtragem por idioma usa padrão de prefixo do ID, por exemplo `it.id.startsWith("$languageCode-")`.

CI #341 falhou historicamente porque foi inventado `languageCode` e foram omitidos campos obrigatórios. Não repetir.

---

# 9. PEDAGOGIA / CLAIMS — NÃO INVENTAR

Qualidade > quantidade.
Progressão desejada quando tecnicamente defensável:
`context → recognition → cued retrieval → fewer cues → new context → spaced review → retention/transfer`.

Não fabricar:
- mastery;
- avaliação robusta de free writing;
- speaking;
- pronúncia;
- ASR;
- scores de proficiência por habilidade sem evidência válida;
- validação psicométrica;
- certificação CEFR.

`FREE_TEXT` existir no enum não significa que escrita aberta esteja robustamente avaliada.
XP/streak/preferences não são evidência de domínio CEFR.

---

# 10. KOREANO

Gate obrigatório de revisão humana foi supersedido.
Segunda revisão linguística rigorosa por IA é aceita; revisão humana externa é desejável, não bloqueante.
Nunca chamar revisão por IA de revisão humana, validação independente, validação psicométrica ou certificação CEFR.

Formas A1 já revisadas incluem:
`안녕하세요`, `저는 미아예요`, `저는 치우예요`, `제 이름은 치우예요`, `이름이 뭐예요?`, `어디에 살아요?`, `리우에 살아요`, `무엇을 좋아해요?`, `커피를 좋아해요`, `저는 책이 있어요`, `감사합니다`, `고맙습니다`, `또 봐요`.

Não reabrir sem motivo linguístico real.

---

# 11. SUPABASE — SEPARAÇÃO ABSOLUTA

CHIU KNOW?:
- project `uskxabsodcnzlovuaurp`
- org `aeerqbmrwulxsawhjyvm`
- region `sa-east-1`

CHIU PLAYER — NÃO TOCAR durante trabalho do Know?:
- project `hpcbkvbrlwjnwlikmbfb`
- org `nnrwosbnvdvzaoflwxlo`

Nunca misturar recursos, quotas, buckets, funções, credenciais ou secrets.
Nunca colocar `service_role` no APK.
Auth deep link fisicamente testado: `chiuknow://auth-callback`.

---

# 12. VOZ — REGRA ABSOLUTA

Voz privada oficial:
`Chiu-animada-recorte-final.m4a`
aproximadamente 15,4 s / 309 KB.

NÃO colocar em GitHub público, provedor externo, Supabase ou APK sem autorização explícita da usuária.
Bucket privado existir não constitui autorização de upload.

---

# 13. VISUAL — REGRA ABSOLUTA

Dois Chius diferentes:

- Chihuahua branco realista com cabelo castanho = SOMENTE logo/ícone do APK.
- Chihuahua amarelo/esquisito cartunesco = SEMPRE personagem interno em histórias, cards, exercícios e telas.

Nunca misturar/substituir os dois.
Não redesenhar arbitrariamente masters aprovados.
Nova pose precisa ser mostrada e aprovada antes de integrar ao APK.

Personagens oficiais atuais:
Chiu, Mia, Jurandir, Barto, Lara, Caca, Onça, Perry, Lena.
Jurandir é o nome definitivo do mosquito; “Zé Pernilongo” é apenas histórico.

Antes de trabalho visual, reler `VISUAL_BIBLE.md`.

---

# 14. PRÓXIMA AÇÃO DO NOVO CHAT

O novo chat NÃO deve começar programando.

Primeiro:
1. buscar HEAD real de `main`;
2. buscar Android CI do SHA exato;
3. ler `PROJECT_STATE_CURRENT.md`;
4. ler este `CURRENT_HANDOFF.md`;
5. reler `ChiuKnowApp.kt` no SHA atual;
6. se relevante, consultar `PRODUCT_SPEC.md` e `PEDAGOGY_ARCHITECTURE.md`;
7. só então escolher o próximo menor seam real.

Como `d880f924...` já resolveu serialização de retry normal com persistência, NÃO repetir esse trabalho.

Próximas investigações seguras, sem assumir bug:
- verificar se `Back to path` durante uma persistência normal pendente possui semântica segura com o `coroutineScope` atual e as chaves capturadas no momento da tentativa;
- verificar se editar uma resposta correta enquanto a persistência ainda está pendente pode produzir UX incoerente, embora novo submit esteja bloqueado por `canSubmit`;
- confirmar que Continue repetido rapidamente é inofensivo antes de inventar novo guard;
- confirmar que após Continue os estados `NONE_DUE`/`NO_CONTENT` continuam coerentes com o contrato da fila;
- confirmar que prática opcional entra/sai sem criar evidência ou schedule.

Se investigação não demonstrar problema real, não mudar código apenas para “fazer alguma coisa”. Registrar conclusão e escolher outro seam comprovável.

---

# 15. REGRA DE CONTINUIDADE

Quando a usuária disser apenas `Continue`:
- não perguntar o que ela quer estudar/desenvolver;
- buscar HEAD + CI;
- respeitar o gate;
- ler este handoff e o estado compacto;
- escolher a menor mudança necessária baseada em código real;
- fazer uma alteração por vez;
- parar no novo CI gate.

Nunca reabrir problemas já resolvidos apenas porque outro chat não lembra deles.
