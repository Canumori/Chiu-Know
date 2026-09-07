# CHIU KNOW? — CURRENT HANDOFF

## FINALIDADE DESTE ARQUIVO

Este arquivo existe para impedir que um novo chat retome o CHIU KNOW? a partir de memória incompleta, resumos antigos ou do topo atualmente desatualizado de `PROJECT_STATE.md`.

**Regra operacional:** o estado real do GitHub em `main` é sempre a fonte final da verdade. Este handoff registra o estado confirmado imediatamente antes de sua criação. Ao iniciar um novo chat, a primeira ação deve ser buscar novamente o HEAD real de `main` e o Android CI correspondente a esse HEAD.

O `PROJECT_STATE.md` é muito grande e as leituras atuais podem ser truncadas. **NÃO sobrescrever `PROJECT_STATE.md` a partir de conteúdo truncado.** Só alterá-lo se for possível fazer round-trip do conteúdo integral, com SHA atual do blob. Enquanto isso, este `CURRENT_HANDOFF.md` registra o checkpoint operacional mais recente e deve ser lido junto com `PROJECT_STATE.md`, `PRODUCT_SPEC.md` e `PEDAGOGY_ARCHITECTURE.md`.

---

# 1. REPOSITÓRIO E WORKFLOW

- Repositório: `Canumori/Chiu-Know`
- Branch autoritativa: `main`
- O repositório está público intencionalmente por causa dos minutos de GitHub Actions.
- O estado real de `main` prevalece sobre memória, resumos de chat e documentação histórica.

## Regra obrigatória antes de qualquer write

1. Buscar o HEAD real de `main`.
2. Buscar o Android CI correspondente **exatamente** ao SHA desse HEAD.
3. Se CI estiver `queued` ou `in_progress`, não escrever nada: parar no gate.
4. Se CI falhou, inspecionar jobs/logs e corrigir somente a falha real.
5. Se CI passou, reler qualquer arquivo que será alterado e usar seu SHA atual.
6. Fazer mudança pequena, reversível e testável.
7. Depois do commit, aguardar o Android CI desse commit.
8. Só avançar novamente com CI verde.

Nunca fazer writes sequenciais concorrentes no mesmo arquivo.
Nunca assumir que um commit mencionado neste handoff ainda é o HEAD quando o novo chat começar.

---

# 2. CHECKPOINT DE CÓDIGO CONFIRMADO ANTES DESTE HANDOFF

Checkpoint de código confirmado imediatamente antes da criação deste documento:

- `e8f7b8e34a3f5fb97e1238da9dde0d99ffe33aa6`
- mensagem: `test: guard second transfer preference cued retrieval`
- Android CI #345, run `34137900321`: `COMPLETED / SUCCESS`.

O commit de produção imediatamente anterior também está verde:

- `d949d54da8fcdb4397b401bf25a6df30b1aedb43`
- mensagem: `feat: add second transfer preference cued retrieval`
- Android CI #344, run `34127261384`: `COMPLETED / SUCCESS`.

**IMPORTANTE:** depois que este arquivo foi criado, `main` ganhou um novo commit documental. O próximo chat deve buscar o HEAD atual em vez de assumir que `e8f7b8e3...` ainda é o HEAD.

---

# 3. HISTÓRICO RECENTE — CI #339 A #345

## CI #339 — sucesso
- commit `01b69eae1f16336c6355270a7c83425231b456e0`
- `feat: add second A1 transfer narrative`
- criou a terceira narrativa A1 / segundo contexto controlado de transferência, com Barto + Chiu em uma praça.

## CI #340 — sucesso
- commit `d7942f132c7c558eaf3e164201cc8d212858a20b`
- `test: guard second A1 transfer narrative`
- adicionou guard estrutural da nova narrativa.

## CI #341 — falha conhecida e corrigida
- commit `e801a2663cf74840b76f6496c8c2653c8ded0202`
- `feat: add second A1 transfer comprehension`
- falhou por incompatibilidade com o modelo real `LearningActivity`:
  - foi usado um parâmetro inexistente `languageCode`;
  - faltavam `learningObjective` e `knowledgeTarget`;
  - o accessor tentou filtrar por `activity.languageCode`, propriedade inexistente.
- isso foi erro de implementação/compilação, não problema pedagógico do conteúdo.

## CI #342 — sucesso
- commit `71604b19f4187a35216fc64e4d5d21f00ba086d5`
- `fix: align second transfer comprehension model`
- correção alinhada ao contrato real de `LearningActivity`:
  - sem `languageCode` no objeto;
  - com `learningObjective` e `knowledgeTarget`;
  - filtragem por prefixo do `id`.

## CI #343 — sucesso
- commit `384cd0b1e3ee8b475d7cdb9ea8ad526abc7e7093`
- `test: guard second A1 transfer comprehension`
- teste dedicado da compreensão da segunda transferência.

## CI #344 — sucesso
- commit `d949d54da8fcdb4397b401bf25a6df30b1aedb43`
- `feat: add second transfer preference cued retrieval`
- criou recuperação com pista da preferência no novo contexto.

## CI #345 — sucesso
- commit `e8f7b8e34a3f5fb97e1238da9dde0d99ffe33aa6`
- `test: guard second transfer preference cued retrieval`
- protegeu a atividade contra regressões de estrutura, grounding narrativo, starter queue e coreano.

---

# 4. TERCEIRA NARRATIVA A1 / SEGUNDO CONTEXTO DE TRANSFERÊNCIA

Arquivo:
`app/src/main/java/com/chiu/know/model/A1SecondTransferNarrativeMicroUnit.kt`

Accessor:
`a1SecondTransferNarrativeMicroUnitFor(languageCode: String)`

Objetivo:
- mudar interlocutor e cenário;
- reutilizar residência e preferência já introduzidas;
- testar transferência contextual;
- não criar mastery/FSRS por si só;
- não fingir free writing, conversação, speaking ou pronúncia.

Exatamente uma narrativa por EN/PT/ES/FR/KO, A1, 6 beats.

## EN
- id `en-a1-narrative-square-003`
- title `A meeting in the square`
- setting `Barto meets Chiu in a small square.`
- Barto: `Hello, Chiu!`
- Chiu: `Hello, Barto!`
- Barto: `Where do you live?`
- Chiu: `I live in Rio.`
- Barto: `What do you like?`
- Chiu: `I like coffee.`

## PT
- id `pt-a1-narrative-praca-003`
- title `Um encontro na praça`
- setting `Barto encontra Chiu em uma pequena praça.`
- Barto: `Olá, Chiu!`
- Chiu: `Olá, Barto!`
- Barto: `Onde você mora?`
- Chiu: `Eu moro no Rio.`
- Barto: `Do que você gosta?`
- Chiu: `Eu gosto de café.`

## ES
- id `es-a1-narrative-plaza-003`
- title `Un encuentro en la plaza`
- setting `Barto se encuentra con Chiu en una pequeña plaza.`
- Barto: `¡Hola, Chiu!`
- Chiu: `¡Hola, Barto!`
- Barto: `¿Dónde vives?`
- Chiu: `Vivo en Río.`
- Barto: `¿Qué te gusta?`
- Chiu: `Me gusta el café.`

## FR
- id `fr-a1-narrative-place-003`
- title `Une rencontre sur la place`
- setting `Barto rencontre Chiu sur une petite place.`
- Barto: `Bonjour, Chiu !`
- Chiu: `Bonjour, Barto !`
- Barto: `Où est-ce que tu habites ?`
- Chiu: `J’habite à Rio.`
- Barto: `Qu’est-ce que tu aimes ?`
- Chiu: `J’aime le café.`

## KO
- id `ko-a1-narrative-square-003`
- title `광장에서 만나요`
- setting `바르토가 작은 광장에서 치우를 만나요.`
- Barto: `안녕하세요, 치우!`
- Chiu: `안녕하세요, 바르토!`
- Barto: `어디에 살아요?`
- Chiu: `리우에 살아요.`
- Barto: `무엇을 좋아해요?`
- Chiu: `커피를 좋아해요.`

Os `linkedReviewKeys` reutilizam os targets de greeting/residence/preference já existentes em cada idioma.

---

# 5. COMPREENSÃO DA SEGUNDA TRANSFERÊNCIA — COMPLETA E PROTEGIDA

Produção:
`app/src/main/java/com/chiu/know/model/A1SecondTransferNarrativeComprehensionActivities.kt`

Teste:
`app/src/test/java/com/chiu/know/model/A1SecondTransferNarrativeComprehensionActivitiesTest.kt`

Estado:
- produção corrigida no CI #342;
- teste dedicado verde no CI #343.

Uma atividade A1 READING MULTIPLE_CHOICE por idioma.

Foco: identificar o que Chiu diz gostar na nova praça.

Respostas corretas:
- EN `coffee`
- PT `café`
- ES `café`
- FR `café`
- KO `커피`

Distratores contrastam com `books/livros/libros/livres/책`.

O teste prova:
- exatamente uma atividade por idioma;
- A1 / READING / MULTIPLE_CHOICE;
- uma resposta aceita presente em duas opções distintas;
- review keys fora da starter review queue;
- resposta e feedback ancorados na fala real de Chiu na narrativa;
- coreano usa a forma revisada `커피를 좋아해요.`.

---

# 6. PREFERENCE CUED RETRIEVAL NA PRAÇA — COMPLETA E PROTEGIDA

Produção:
`app/src/main/java/com/chiu/know/model/A1SecondTransferNarrativePreferenceCuedRetrievalActivities.kt`

Teste:
`app/src/test/java/com/chiu/know/model/A1SecondTransferNarrativePreferenceCuedRetrievalActivitiesTest.kt`

Estado:
- produção verde no CI #344;
- teste verde no CI #345.

Formato:
- uma atividade por EN/PT/ES/FR/KO;
- A1;
- READING;
- MULTIPLE_CHOICE;
- fechada e determinística;
- pergunta completa de Barto como pista;
- resposta completa de Chiu como alternativa correta;
- resposta de residência como distrator contextual;
- fora da starter review queue.

## Respostas exatas

EN:
- pergunta `What do you like?`
- correta `I like coffee.`
- distrator `I live in Rio.`

PT:
- pergunta `Do que você gosta?`
- correta `Eu gosto de café.`
- distrator `Eu moro no Rio.`

ES:
- pergunta `¿Qué te gusta?`
- correta `Me gusta el café.`
- distrator `Vivo en Río.`

FR:
- pergunta `Qu’est-ce que tu aimes ?`
- correta `J’aime le café.`
- distrator `J’habite à Rio.`

KO:
- pergunta `무엇을 좋아해요?`
- correta `커피를 좋아해요.`
- distrator `리우에 살아요.`

O teste dedicado prova, para todos os idiomas:
- exatamente uma atividade;
- A1 / READING / MULTIPLE_CHOICE;
- uma resposta aceita e duas opções distintas;
- a pergunta existe como fala de Barto na narrativa;
- a fala imediatamente seguinte é de Chiu e é exatamente a resposta aceita;
- prompt contém a pergunta real;
- feedback contém a resposta real;
- review key não colide com starter queue;
- no coreano, pergunta/resposta/distrator são exatamente os esperados.

---

# 7. PROGRESSÃO A1 JÁ EXISTENTE ANTES DA PRAÇA

Não apagar/recomeçar.

## Primeira narrativa — café
`A1FirstNarrativeMicroUnit.kt`

Já possui:
- compreensão factual;
- speaker tracking;
- sequence comprehension;
- testes dedicados.

## Segunda narrativa — parque
`A1TransferNarrativeMicroUnit.kt`

A frente de residência no parque já passou por:
1. compreensão/contexto;
2. cued retrieval com alternativas completas;
3. REORDER;
4. FILL_IN.

A frente de preferência no parque já passou por:
1. compreensão/contexto;
2. cued retrieval;
3. REORDER;
4. FILL_IN.

Os respectivos testes foram fortalecidos para grounding no diálogo real.

Essa sequência é o padrão de referência para a retirada gradual de pistas na praça.

---

# 8. PRÓXIMO PASSO PEDAGÓGICO RECOMENDADO

**Não executar cegamente sem conferir o HEAD/CI real.**

Se o novo chat encontrar `main` com apenas alterações documentais depois do checkpoint e Android CI verde, o próximo pequeno slice recomendado é:

### Preference REORDER na praça
Criar uma atividade de reconstrução da resposta de Chiu à pergunta de Barto, uma por idioma, seguindo o padrão já validado em:
`A1TransferNarrativePreferenceReorderRetrievalActivities.kt`

Objetivo:
- retirar a alternativa de frase completa;
- exigir reconstrução por tokens;
- manter atividade fechada/determinística;
- continuar fora da starter review queue;
- manter grounding na troca imediata Barto → Chiu;
- não alegar mastery, writing livre, speaking ou pronúncia.

Depois:
1. commit de produção;
2. esperar CI verde;
3. criar teste dedicado em commit separado, se necessário;
4. esperar CI verde;
5. só então considerar FILL_IN da preferência na praça.

Não pular diretamente para `FREE_TEXT`.

---

# 9. LEARNINGACTIVITY — CONTRATO REAL QUE NÃO PODE SER INVENTADO

O modelo atual `LearningActivity` NÃO possui `languageCode`.

Campos obrigatórios atuais incluem:
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

Para filtrar idioma nas famílias atuais, o padrão existente usa prefixo do `id`, por exemplo:
`it.id.startsWith("$languageCode-")`.

Não repetir o erro do CI #341.

`isLearningAnswerCorrect` atualmente faz normalização conservadora: trim + lowercase e igualdade exata com accepted answers. Não apaga acentos nem reescreve respostas.

---

# 10. FREE_TEXT / SPEAKING / PRONÚNCIA — NÃO INVENTAR

`ResponseType` contém `FREE_TEXT`, mas isso NÃO significa que exista escrita livre pedagogicamente válida.

Estado atual:
- `FREE_TEXT` não tem semântica distinta robusta de avaliação;
- a UI trata tipos textuais simples com campo de texto;
- o avaliador global é igualdade determinística conservadora;
- não há política de equivalentes, tolerância linguística, erros aceitáveis ou avaliação AI para free writing.

Portanto:
- não chamar FILL_IN de escrita livre;
- não usar `FREE_TEXT` como “sem pistas” só porque o enum existe;
- não alegar escrita livre avaliada;
- não alegar speaking avaliado;
- não alegar pronúncia avaliada;
- não há ASR/pronúncia real validada.

Antes de qualquer etapa real sem pistas, definir explicitamente política/evaluator apropriado.

---

# 11. REGRAS PEDAGÓGICAS PERMANENTES

Antes de mudanças pedagógicas, ler:
- `CURRENT_HANDOFF.md`
- `PROJECT_STATE.md`
- `PRODUCT_SPEC.md`
- `PEDAGOGY_ARCHITECTURE.md`

Fluxo desejado:
`contexto → reconhecimento → recuperação com pistas → menos pistas → sem pistas → novo contexto → revisão espaçada → retenção/transferência`

Regras duras:
- qualidade > quantidade;
- erro = tentativa/exposição, não mastery;
- XP/streak não são domínio;
- preferências do aluno são planejamento, nunca evidência de proficiência;
- revisões vencidas têm prioridade;
- não fabricar mastery;
- não fabricar scores por habilidade sem evidência real;
- não pular fundamentos;
- não massificar conteúdo antes de validar pequenos slices ponta a ponta.

---

# 12. COREANO — REGRA ATUAL E DEFINITIVA

A regra antiga de revisão humana obrigatória foi superada.

Decisão vigente:
- a IA faz segunda revisão linguística rigorosa do coreano;
- revisão humana externa é desejável se um dia estiver disponível, mas não é gate obrigatório.

Nunca chamar a revisão por IA de:
- revisão humana;
- validação psicométrica;
- certificação CEFR;
- validação independente.

A revisão rigorosa deve checar:
- naturalidade;
- gramática;
- registro;
- contexto;
- resposta única defensável;
- qualidade dos distratores;
- alternativas plausíveis;
- pistas mecânicas;
- progressão relativa;
- neutralidade cultural;
- testes automatizados;
- CI verde.

Formas A1 fortes já revisadas e que não devem ser reabertas sem motivo linguístico real incluem:
- `안녕하세요`
- `저는 미아예요`
- `저는 치우예요`
- `제 이름은 치우예요`
- `이름이 뭐예요?`
- `어디에 살아요?`
- `리우에 살아요`
- `무엇을 좋아해요?`
- `커피를 좋아해요`
- `저는 책이 있어요`
- `감사합니다`
- `고맙습니다`
- `또 봐요`

Na praça atual, a troca revisada é:
`무엇을 좋아해요?` → `커피를 좋아해요.`

---

# 13. PLACEMENT COREANO E CEFR — NÃO REGREDIR

EN/PT/ES/FR/KO estão em `QUALITY_SESSION`, sujeito sempre ao código real atual.

Coreano usa o banco combinado `candidateKoreanPlacementQuestions`, 24 itens, 4 por rótulo interno A1–C2.

A1–C2 no placement são alvos internos de classificação.
Nunca alegar:
- certificação CEFR oficial;
- equivalência oficial CEFR;
- calibração psicométrica validada;
- validação independente;
- equivalência automática ao King Sejong.

Resultado deve ser tratado como estimativa pedagógica de nível.

---

# 14. SUPABASE — SEPARAÇÃO ABSOLUTA

CHIU KNOW?:
- project `uskxabsodcnzlovuaurp`
- org `aeerqbmrwulxsawhjyvm`
- region `sa-east-1`

CHIU PLAYER — PROIBIDO tocar durante trabalho do Know?:
- project `hpcbkvbrlwjnwlikmbfb`
- org `nnrwosbnvdvzaoflwxlo`

Nunca misturar:
- projetos;
- quotas;
- buckets;
- Edge Functions;
- credenciais;
- secrets.

Nunca colocar `service_role` no APK.

Auth deep link `chiuknow://auth-callback` já foi fisicamente testado.

---

# 15. VOZ — PRIVADA

Voz oficial aprovada do Chiu:
`Chiu-animada-recorte-final.m4a`

Características registradas:
- ~15,4 s;
- ~309 KB;
- aprovada após corte de silêncio/ruído.

Regra absoluta:
- PRIVATE;
- não colocar no GitHub público;
- não enviar a fornecedor externo;
- não colocar no Supabase;
- não embutir no APK;
- qualquer uma dessas ações depende de autorização explícita da usuária.

Bucket privado `character-voices` existe no Supabase do Know?, mas isso NÃO autoriza upload da voz.

---

# 16. VISUAL — REGRA ABSOLUTA DOS DOIS CHIUS

Antes de qualquer trabalho visual, ler `VISUAL_BIBLE.md`.

- Chiu realista branco com cabelo castanho: **SOMENTE ícone/logo do APK**.
- Chiu amarelo/cartunesco/esquisito aprovado: **SEMPRE personagem interno** em histórias, cards, exercícios e universo do app.
- Nunca misturar nem substituir um pelo outro.

Personagens oficiais atuais:
- Chiu
- Mia (gata)
- Jurandir (mosquito; nome definitivo, não Zé Pernilongo)
- Barto (morcego)
- Lara (arara)
- Caca (capivara)
- Onça
- Perry (ornitorrinco)
- Lena (preguiça)

Masters aprovados não devem ser redesenhados.
Nova pose é candidata e deve ser mostrada à usuária e aprovada antes de integração. Sem exceção.

---

# 17. USUÁRIA / FORMA DE TRABALHO

A usuária não programa e espera que o chat trabalhe autonomamente.

Quando ela disser `Continue`:
- não perguntar o que fazer se o próximo passo puder ser inferido com segurança;
- buscar estado real;
- seguir gates de CI;
- avançar uma pequena fatia;
- parar apenas em gate real, risco, necessidade de aprovação visual/voz/dados/publicação ou decisão genuína da usuária.

Não mandar a usuária editar código manualmente, resolver conflitos, usar terminal ou montar ZIP quando isso puder ser feito pelas ferramentas.

---

# 18. PROMPT PARA O PRÓXIMO CHAT

Copiar a partir daqui para um novo chat:

---

Quero continuar o desenvolvimento do **CHIU KNOW?** EXATAMENTE do ponto em que o chat anterior parou.

## REGRA ZERO
NÃO RECOMECE O PROJETO.
NÃO INVENTE O ESTADO.
NÃO CONFIE APENAS NA MEMÓRIA DO CHAT.
NÃO ME PEÇA O HISTÓRICO ANTERIOR.
NÃO ALTERE COISAS PORQUE “PARECEM MELHORES” SEM VERIFICAR O CÓDIGO E AS REGRAS REAIS.

O repositório é `Canumori/Chiu-Know`, branch autoritativa `main`.

### PRIMEIRAS AÇÕES OBRIGATÓRIAS
1. Leia `CURRENT_HANDOFF.md` inteiro.
2. Leia o topo atual relevante de `PROJECT_STATE.md`, mas saiba que ele é um arquivo enorme e pode ser truncado; NUNCA sobrescreva `PROJECT_STATE.md` usando uma leitura truncada.
3. Leia `PRODUCT_SPEC.md` e `PEDAGOGY_ARCHITECTURE.md` antes de qualquer mudança pedagógica.
4. Se houver trabalho visual, leia `VISUAL_BIBLE.md` antes de fazer qualquer coisa.
5. Busque o HEAD real de `main`.
6. Busque o Android CI correspondente exatamente ao SHA do HEAD real.
7. Se CI estiver queued/in_progress, pare no gate e não escreva nada.
8. Se CI falhou, inspecione logs e corrija somente a falha real.
9. Se CI passou, só então avance.

### CHECKPOINT DE CÓDIGO DO CHAT ANTERIOR
Antes da criação do handoff documental, o código confirmado estava em:
`e8f7b8e34a3f5fb97e1238da9dde0d99ffe33aa6`
`test: guard second transfer preference cued retrieval`
Android CI #345 = SUCCESS.

O commit de produção anterior:
`d949d54da8fcdb4397b401bf25a6df30b1aedb43`
`feat: add second transfer preference cued retrieval`
Android CI #344 = SUCCESS.

Depois disso foi criado `CURRENT_HANDOFF.md`, então **não assuma que e8f7b8e3 é o HEAD atual**. Descubra o HEAD real.

### ESTADO PEDAGÓGICO ATUAL
A terceira narrativa A1 / segundo contexto de transferência já existe em:
`A1SecondTransferNarrativeMicroUnit.kt`.

Contexto: Barto encontra Chiu em uma praça. São 6 beats por idioma EN/PT/ES/FR/KO.

Troca de preferência:
- EN: `What do you like?` → `I like coffee.`
- PT: `Do que você gosta?` → `Eu gosto de café.`
- ES: `¿Qué te gusta?` → `Me gusta el café.`
- FR: `Qu’est-ce que tu aimes ?` → `J’aime le café.`
- KO: `무엇을 좋아해요?` → `커피를 좋아해요.`

Já existem e estão protegidos:
1. compreensão fechada dessa nova narrativa;
2. `preference cued retrieval` com frase completa.

Produção da compreensão:
`A1SecondTransferNarrativeComprehensionActivities.kt`
Teste:
`A1SecondTransferNarrativeComprehensionActivitiesTest.kt`
CI #343 = SUCCESS.

Produção do cued retrieval:
`A1SecondTransferNarrativePreferenceCuedRetrievalActivities.kt`
Teste:
`A1SecondTransferNarrativePreferenceCuedRetrievalActivitiesTest.kt`
CI #344 e #345 = SUCCESS.

No cued retrieval coreano:
- prompt contém `무엇을 좋아해요?`;
- correta `커피를 좋아해요.`;
- distrator `리우에 살아요.`.

O teste prova que a pergunta é de Barto e que a fala imediatamente seguinte de Chiu é exatamente a resposta aceita. Também prova que essas activities ficam fora da starter review queue.

### PRÓXIMO PASSO RECOMENDADO, SE O ESTADO REAL CONTINUAR COMPATÍVEL
Depois de confirmar HEAD/CI e reler os arquivos atuais, criar o próximo slice de retirada de pistas para **preferência na praça**:

**REORDER da resposta de Chiu**.

Use como referência o padrão já validado em:
`A1TransferNarrativePreferenceReorderRetrievalActivities.kt`

e seus testes.

Faça uma activity por EN/PT/ES/FR/KO, A1, READING, `ResponseType.REORDER`, fechada e determinística, grounded na pergunta de Barto e resposta de Chiu.

Não invente semântica nova. Inspecione o contrato atual de `LearningActivity` antes de escrever.

Depois do commit de produção, espere CI verde. Só depois crie/fortaleça teste dedicado em commit separado. Espere CI verde de novo. Depois, e somente depois, poderá considerar FILL_IN da preferência na praça.

### ERRO QUE NÃO PODE SER REPETIDO
No CI #341 foi criado `LearningActivity(languageCode=...)`, mas o modelo real NÃO possui `languageCode`; também faltavam `learningObjective` e `knowledgeTarget`.

Contrato atual de `LearningActivity` deve ser lido no código. O padrão de filtragem usado pelas famílias recentes é por prefixo do `id`:
`it.id.startsWith("$languageCode-")`.

Não repita esse erro.

### FREE_TEXT / SPEAKING
NÃO use `FREE_TEXT` como “sem pistas”. O enum existe, mas a avaliação real ainda é simples/determinística e não há política robusta de equivalentes, tolerância linguística ou escrita livre.

Não alegue:
- escrita livre avaliada;
- speaking avaliado;
- pronúncia avaliada;
- mastery por passar uma atividade;
- FSRS automaticamente criado por essas atividades de transferência.

### COREANO
A exigência antiga de revisão humana obrigatória foi SUPERADA.
A regra atual é segunda revisão linguística rigorosa por IA. Revisão humana externa é desejável, mas não bloqueadora.

Não chame isso de revisão humana, validação psicométrica, certificação CEFR ou validação independente.

Formas atuais da praça já revisadas:
`어디에 살아요?` → `리우에 살아요.`
`무엇을 좋아해요?` → `커피를 좋아해요.`

Não reabra essas formas sem motivo linguístico real.

### PLACEMENT
EN/PT/ES/FR/KO estão em `QUALITY_SESSION`, sujeito ao código real atual.
Coreano possui banco combinado de 24 itens, 4 por rótulo interno A1–C2.
Nunca alegar certificação CEFR oficial, calibração psicométrica ou equivalência oficial.

### SUPABASE — NÃO MISTURAR
CHIU KNOW?:
project `uskxabsodcnzlovuaurp`
org `aeerqbmrwulxsawhjyvm`
region `sa-east-1`

CHIU PLAYER — PROIBIDO tocar neste trabalho:
project `hpcbkvbrlwjnwlikmbfb`
org `nnrwosbnvdvzaoflwxlo`

Nunca colocar `service_role` no APK.

### VOZ
A voz oficial `Chiu-animada-recorte-final.m4a` é PRIVADA.
Não GitHub público.
Não fornecedor externo.
Não Supabase.
Não APK.
Qualquer uso/upload depende de autorização explícita da usuária.

### VISUAL ABSOLUTO
Chiu realista branco/cabelo castanho = SOMENTE logo/ícone.
Chiu amarelo/cartunesco/esquisito = SEMPRE personagem interno.
Nunca misturar.
Nova pose deve ser mostrada e aprovada antes de integração.
Jurandir é o mosquito definitivo, não Zé Pernilongo.

### FORMA DE TRABALHO
A usuária não programa.
Trabalhe autonomamente.
Quando ela disser `Continue`, confira o estado real e avance a próxima pequena fatia segura sem perguntar o que fazer se isso estiver claro.

Cada write:
- HEAD/CI real antes;
- arquivo atual + SHA antes;
- mudança pequena;
- commit;
- CI correspondente;
- só continuar verde.

Se o estado real tiver mudado externamente, siga o estado real e NÃO sobrescreva trabalho novo.

---

Fim do handoff.
