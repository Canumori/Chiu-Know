# CHIU KNOW? — REVISÃO RIGOROSA POR IA DO CONTEÚDO COREANO A1

## Estado autoritativo — 2026-09-04

Esta revisão registra uma segunda passada interna por IA, deliberadamente rigorosa, para o conteúdo coreano A1 já usado no starter bank. Ela existe porque a ausência de revisor humano voluntário não deve bloquear indefinidamente o coreano. Esta revisão NÃO é validação humana, certificação CEFR, validação psicométrica nem equivalência com King Sejong Institute.

## Critérios aplicados

Cada item revisado foi verificado quanto a:

1. correção gramatical;
2. naturalidade para coreano contemporâneo em registro educacional neutro;
3. consistência de registro, com preferência pelo estilo polido `-요` nas interações;
4. adequação ao A1 e ausência de complexidade desnecessária;
5. resposta determinística quando o exercício exige resposta fechada;
6. coerência entre prompt, resposta aceita, feedback e reviewKey;
7. plausibilidade de distratores;
8. ausência de ambiguidade não intencional;
9. continuidade entre reconhecimento, recuperação e transferência;
10. risco de tradução literal do inglês/português que soe pouco natural em coreano.

## Referência normativa adicional

A regra de `이에요/예요` usada em `저는 미아예요` e `저는 치우예요` foi conferida contra orientação atual do 국립국어원: `이에요` pode ocorrer após substantivos e, após substantivo sem 받침, a forma reduzida `예요` é padrão e particularmente natural. Portanto `미아예요` e `치우예요` são formas adequadas.

## Blocos revisados nesta passada

- saudação inicial do starter bank;
- `A1IntegratedLearningActivities.kt`;
- `A1ReorderActivities.kt`;
- `A1MultipleChoiceActivities.kt`;
- `A1GratitudeActivities.kt`;
- `A1FarewellActivities.kt`;
- `A1PossessionGrammarActivities.kt`;
- `A1ResidenceGrammarActivities.kt`;
- `A1ResidenceReadingActivities.kt`;
- `A1ReadingActivities.kt`;
- `A1PreferenceReadingActivities.kt`;
- `A1BasicQuestionActivities.kt`;
- `A1ResidenceQuestionActivities.kt`;
- `A1ResidenceMicroInteractionActivities.kt`;
- `A1ResidenceMicroInteractionRetrievalActivities.kt`;
- `A1PreferenceMicroInteractionActivities.kt`;
- `A1PreferenceMicroInteractionRetrievalActivities.kt`.

## Formas centrais aprovadas

As seguintes formas são adequadas para a fundação A1 atual e podem ser reutilizadas em microinterações e narrativa curta:

- `안녕하세요` — saudação polida básica;
- `저는 미아예요 / 저는 치우예요` — apresentação simples;
- `제 이름은 치우예요` — forma explícita para dizer o nome;
- `이름이 뭐예요?` — pergunta básica de nome em registro `-요`;
- `어디에 살아요?` — pergunta básica de residência;
- `리우에 살아요` — resposta natural e simples de residência;
- `무엇을 좋아해요?` — pergunta gramatical e adequada sobre preferência; `뭘 좋아해요?` é mais coloquial, mas não deve substituir automaticamente a forma didática atual;
- `커피를 좋아해요` — resposta natural de preferência;
- `저는 책이 있어요` — padrão existencial natural para posse básica;
- `감사합니다 / 고맙습니다` — agradecimentos polidos válidos;
- `또 봐요` — despedida natural quando se espera reencontro.

## Problemas encontrados

### 1. Despedida redundante

Item: `ko-a1-farewell-001`.

Estado encontrado: o blank aceita `또 봐요`, mas o prompt continua com `내일 봐요`, produzindo `또 봐요! 내일 봐요.`. As duas frases são corretas isoladamente, porém a combinação é pedagogicamente artificial e desnecessariamente repetitiva.

Correção exigida: manter o alvo `또 봐요`, mas retirar `내일 봐요` do prompt desse item ou reescrever o contexto para não duplicar a mesma função comunicativa.

### 2. Referência espacial semanticamente fraca em leitura

Item: `ko-a1-reading-intro-001`.

Estado encontrado: `치우 근처에 살아요` é possível em interpretação contextual, mas usa uma pessoa como ponto espacial sem explicitar casa/local e soa menos natural/preciso para material A1 isolado.

Correção recomendada: reutilizar um local já ensinado, por exemplo `리우에 살아요`, preservando o objetivo real do item (identificar o nome) sem introduzir uma formulação espacial desnecessariamente estranha.

## Itens que não exigem correção nesta passada

- `저는 미아예요` / `저는 치우예요`: corretos e naturais;
- explicação de `예요` após nomes sem 받침: correta;
- `이름이 뭐예요?`: adequada para interação básica entre personagens em registro polido;
- `어디에 살아요?`, `리우에 살아요`, `공원 근처에 살아요`: naturais;
- `무엇을 좋아해요?`, `커피를 좋아해요`: corretos; a forma com `무엇을` é um pouco mais explícita/didática que `뭘`, o que é aceitável no A1;
- `저는 책이 있어요`, `저는 작은 침대가 있어요`: estruturas naturais para posse existencial;
- `감사합니다` e `고맙습니다`: ambas aceitáveis como respostas fechadas equivalentes nos contextos atuais;
- distratores das microinterações de residência e preferência: pertencem a funções diferentes e não criam segunda resposta plausível;
- pares recognition → retrieval de residência e preferência: mantêm o mesmo `reviewKey` e reduzem pistas sem alegar speaking/mastery.

## Regra de liberação da primeira narrativa coreana

A primeira narrativa coreana A1 pode ser adicionada após:

1. corrigir os dois pontos acima;
2. CI verde para cada alteração estrutural/conteudística conforme a política de mudanças pequenas;
3. a narrativa reutilizar somente estruturas A1 já aprovadas nesta revisão;
4. manter registro polido consistente;
5. não introduzir fala livre, pronúncia, ASR ou alegação de domínio;
6. testar que todos os `linkedReviewKeys` da narrativa coreana existem realmente no starter bank.

## Narrativa coreana candidata após correções

Contexto recomendado: Mia e Chiu se encontram em um café pequeno.

Sequência linguística candidata:

- 미아: `안녕하세요! 저는 미아예요. 이름이 뭐예요?`
- 치우: `제 이름은 치우예요.`
- 미아: `어디에 살아요?`
- 치우: `리우에 살아요.`
- 미아: `무엇을 좋아해요?`
- 치우: `커피를 좋아해요.`

Essa sequência é curta, coerente, usa somente material já introduzido e mantém o registro `-요`. Ela representa contexto narrativo e compreensão/recuperação controlada; não representa conversa livre, speaking, pronúncia ou mastery.

## Estado final desta revisão

O núcleo linguístico A1 coreano revisado é suficientemente sólido para continuar o desenvolvimento. O coreano NÃO deve mais ficar parado aguardando obrigatoriamente revisor humano. Há duas correções localizadas a aplicar antes da narrativa. Depois delas e de CI verde, a primeira narrativa coreana A1 está autorizada pela política interna rigorosa documentada neste arquivo.
