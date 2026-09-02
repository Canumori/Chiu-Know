# CHIU KNOW? — PROJECT STATE

## ATUALIZAÇÃO AUTORITATIVA — 2026-09-02 — PRIMEIRA FATIA PEDAGÓGICA + EVIDÊNCIA LOCAL VALIDADA

Este bloco substitui qualquer seção antiga de “estado atual” ou “próximo passo” abaixo quando houver conflito. O histórico anterior permanece no arquivo por rastreabilidade.

Antes de continuar, ler em conjunto:
1. `PROJECT_STATE.md` — estado técnico real e continuidade;
2. `PRODUCT_SPEC.md` — visão, roadmap e princípios pedagógicos;
3. `RESEARCH.md` — referências/arquitetura;
4. `VISUAL_BIBLE.md` — identidade/personagens quando a frente envolver UI, atividades, histórias ou arte.

O GitHub real vence documentação desatualizada. Não usar memória do chat como fonte única.

### HEAD / CI ATUAL
- Repositório: `Canumori/Chiu-Know`
- Branch: `main`
- Head validado mais recente: `518fcddddaf8435eff5c5db04de5074390a41086` — `test: protect persisted evidence decoding`
- Android CI #75 / run `33584347501`: COMPLETED / SUCCESS.
- Android CI #74 / run `33584337301`: COMPLETED / SUCCESS.
- Android CI #73 / run `33584156504`: COMPLETED / SUCCESS.
- Android CI #71 / run `33581452970`: COMPLETED / SUCCESS.

### FLUXO ATUAL REAL
`LANGUAGE_SELECTION → PLACEMENT_INTRO → PLACEMENT_TEST → PLACEMENT_RESULT → LEARNING_TRAIL → LEARNING_ACTIVITY`.

Se já existir nível estimado salvo para o idioma-alvo, `PLACEMENT_INTRO` permite continuar para `LEARNING_TRAIL` sem refazer o placement. O reteste permanece disponível.

### PRIMEIRA FATIA PEDAGÓGICA REAL
Modelo: `app/src/main/java/com/chiu/know/model/LearningActivity.kt`.

Campos pedagógicos já explícitos:
- habilidade principal;
- objetivo de aprendizagem;
- nível CEFR;
- conhecimento-alvo;
- tipo de resposta;
- prompt;
- feedback;
- vínculo de revisão (`reviewKey`);
- respostas aceitas.

A correção atual é determinística por `isLearningAnswerCorrect(...)`, com `trim()` + lowercase, sem apagar acentos nem reescrever a resposta. Tipos de resposta mais complexos deverão receber avaliadores próprios.

Banco inicial: `StarterLearningActivities.kt`.
Há exatamente uma atividade A1 de recuperação de vocabulário por idioma-alvo (English, Português, Español, Français, 한국어), usada apenas para validar a arquitetura ponta a ponta antes de produção em massa.

A UI da atividade já permite:
- responder;
- verificar resposta;
- receber correto/incorreto;
- receber feedback;
- voltar à trilha.

Correção que não deve ser perdida: a UI usa o avaliador determinístico real; não reintroduzir função inexistente/alternativa.

### EVIDÊNCIA DE APRENDIZAGEM — SEM CONFUNDIR COM DOMÍNIO
Modelo: `LearningEvidence.kt`.
Cada tentativa observada registra:
- `activityId`;
- `reviewKey`;
- nível;
- habilidade principal;
- correto/incorreto;
- timestamp.

Isso é evidência de uma tentativa específica. NÃO é:
- prova de domínio;
- XP;
- streak;
- evento de desbloqueio;
- decisão CEFR;
- FSRS ainda.

A UI salva a evidência localmente em DataStore por idioma-alvo sob `learning_evidence_<languageCode>`. O texto bruto digitado pelo aluno NÃO é salvo.

Formato compacto atual do evento persistido:
`timestamp|activityId|reviewKey|level|primarySkill|correct`.

Esta codificação é fundação local pequena; não tratá-la como armazenamento definitivo para histórico ilimitado.

### RESUMO/AGREGAÇÃO DE EVIDÊNCIA
Arquivo: `LearningEvidenceSummary.kt`.
Commit: `1a7c82a2c3917dbb35adb6a4b28240781eebd385` — `feat: add learning evidence summaries`.
Teste: commit `7cc3e1eb16c09fcc64fa5172b56a713834f46d84` — `test: protect learning evidence summaries`.
CI #73 / `33584156504`: SUCCESS.

`summarizeLearningEvidence(...)` agrupa por identidade de atividade/revisão e expõe apenas fatos observados:
- total de tentativas;
- corretas;
- incorretas;
- tentativa mais recente;
- se a tentativa mais recente foi correta.

Não inferir “mastery” a partir desses campos.

### LEITURA/DECODIFICAÇÃO DO HISTÓRICO LOCAL
Commit: `f7ecf3ad55e2490d86c3fb8fd27b69ffd6802f82` — `feat: decode persisted learning evidence`.
Teste: `518fcddddaf8435eff5c5db04de5074390a41086` — `test: protect persisted evidence decoding`.
CI #74 e #75: SUCCESS.

Há decodificação determinística do formato persistido de volta para `LearningEvidence`. Entradas malformadas são ignoradas de forma segura em vez de derrubar o fluxo. Os testes cobrem entrada válida, nível/habilidade inválidos, boolean inválido e conjunto misto de entradas válidas/corrompidas.

### IMPORTANTE — PEQUENO DÉBITO FUNCIONAL IDENTIFICADO
A UI da trilha/atividade ainda chama `starterLearningActivityFor(targetLanguage.code, CefrLevel.A1)` de forma fixa. Isso faz a atividade A1 aparecer mesmo para aluno estimado em B1/C1/C2.

A intenção pedagógica mínima mais segura é usar o nível estimado (`estimatedLevel`) ao procurar a atividade. Como o banco inicial possui apenas A1, somente aluno A1 verá essa atividade enquanto conteúdos reais dos demais níveis não existirem. Não fabricar atividade “equivalente” para níveis superiores nem tratar revisão A1 universal como decisão de produto sem autorização.

### LOCALIZAÇÃO PENDENTE
As strings da primeira tela de atividade foram adicionadas inicialmente ao `values/strings.xml` padrão. Ainda revisar/adicionar equivalentes em `values-pt`, `values-es`, `values-fr` e `values-ko` em commits pequenos, sem alterar lógica.

### UI PENDENTE — TELA DA TRILHA
`LearningTrailScreen` ainda usa `CenteredColumn` verticalmente centralizado. Seis cards + cabeçalho + nota + botão podem estourar em telas pequenas. Fazer mudança isolada para layout scroll-safe, sem redesign e sem alterar identidade visual.

### PERSONAGENS — MOMENTO DE ENTRADA
Os personagens começam a entrar quando a expansão de atividades/cards/contextos pedagógicos for iniciada, logo após estabilizar esta fundação de atividade/evidência. Eles não devem ser usados como decoração aleatória.

Antes de qualquer uso visual:
- reler `VISUAL_BIBLE.md`;
- reutilizar asset-mestre aprovado quando existir;
- não gerar/redesenhar por iniciativa própria;
- pedir autorização se uma nova arte/pose for necessária.

Regra absoluta permanece: Chiu do logo/ícone = Chihuahua branco fotorrealista aprovado; Chiu do universo/atividades = Chihuahua amarelo cartunesco aprovado. Nunca misturar.

### SUPABASE — NÃO TOCADO NESTA FRENTE
Nenhuma das etapas de atividade, evidência, resumo ou decodificação usou Supabase.

Chiu Know? permitido: `uskxabsodcnzlovuaurp`.
Chiu Player proibido nesta frente: `hpcbkvbrlwjnwlikmbfb`.
Se o projeto do Player aparecer numa operação do Chiu Know?, parar imediatamente.

### PRÓXIMOS PASSOS SEGUROS
1. Confirmar head/CI antes de nova escrita.
2. Corrigir em commit isolado a exposição hardcoded de A1 para procurar `starterLearningActivityFor(targetLanguage.code, estimatedLevel)`.
3. Validar CI.
4. Conectar leitura das evidências persistidas ao estado do app sem mostrar score falso de domínio; a primeira integração pode apenas produzir/usar resumo factual do histórico.
5. Localizar as novas strings nos quatro recursos de idioma restantes em commits pequenos.
6. Tornar a trilha scroll-safe em mudança isolada.
7. Depois iniciar expansão pedagógica controlada, momento em que os personagens podem entrar em contexto real, respeitando `VISUAL_BIBLE.md` e os assets aprovados.
8. Ainda não pular para implementação grande de FSRS, XP/streak, desbloqueio automático, IA, áudio/speaking, backend ou histórias completas.

---

## ATUALIZAÇÃO AUTORITATIVA — 2026-09-01 — TRILHA CEFR PÓS-PLACEMENT INICIADA

Este arquivo é a fonte autoritativa de continuidade operacional do Chiu Know?, mas NÃO é o único documento de planejamento. Antes de qualquer nova frente, o próximo chat deve ler em conjunto, obrigatoriamente:
1. `PROJECT_STATE.md` — estado técnico real e continuidade;
2. `PRODUCT_SPEC.md` — visão, objetivos, roadmap e princípios de efetividade pedagógica;
3. `RESEARCH.md` — decisões/referências técnicas e de arquitetura;
4. `VISUAL_BIBLE.md` — identidade, personagens e regras de produção visual quando a frente envolver UI, histórias, atividades ou personagens.

O estado real do GitHub e, quando aplicável, do Supabase sempre vence documentação desatualizada.

## REGRA DE CONTINUIDADE — NÃO DECLARAR QUE “NÃO HÁ PLANEJAMENTO”
O encerramento de uma etapa no `PROJECT_STATE.md` NÃO significa que o projeto ficou sem próximos objetivos. O roadmap maior já está definido no `PRODUCT_SPEC.md` e deve orientar a sequência de desenvolvimento. Quando uma etapa terminar, consultar o plano do produto antes de pedir à usuária que reinvente o próximo objetivo.

## REGRA PEDAGÓGICA AUTORITATIVA
O `PRODUCT_SPEC.md` agora formaliza que o objetivo principal é competência real, retenção e uso do idioma fora do app — não maximizar cliques, XP ou avanço aparente. Toda nova atividade deve ser avaliada por aprendizagem mensurável, recuperação ativa, retenção, transferência para novos contextos e feedback útil. XP/streak/conquistas são motivação, não prova de domínio. C1/C2 exigem tarefas qualitativamente mais avançadas, não apenas vocabulário mais difícil.

Antes de ampliar em massa um novo tipo de atividade, validar uma fatia pequena ponta a ponta contendo: habilidade principal, objetivo de aprendizagem, CEFR, conhecimento exercitado, tipo de resposta, feedback e forma de revisão/recuperação.

## ROADMAP DE PRODUTO JÁ DEFINIDO
O Chiu Know? é um aplicativo Android multilíngue de aprendizagem de idiomas, CEFR A1–C2, com:
- idioma da interface e idioma-alvo independentes;
- placement adaptativo inicial e reteste;
- resultado geral e por habilidade: gramática, vocabulário, listening, reading, writing e speaking;
- trilha CEFR A1 → A2 → B1 → B2 → C1 → C2;
- ensino/progresso adaptativo;
- revisão inteligente/espaçada baseada em FSRS ou equivalente permissivo;
- XP, streak, meta diária, progresso e conquistas;
- histórias interativas com personagens recorrentes e decisões do aluno;
- tutor por IA para explicações, diálogos e conteúdo dinâmico;
- fala/áudio como módulos próprios, sem confundir transcrição com avaliação de pronúncia.

## QUANDO ENTRAM OS PERSONAGENS
Os personagens NÃO são detalhe opcional. Devem participar pedagogicamente de cards/contextos de atividades, trilha, histórias, diálogos e conteúdo contextual quando essas frentes forem construídas. O `PRODUCT_SPEC.md` agora também explicita que personagens devem favorecer memória e transferência: conteúdo aprendido com um personagem pode reaparecer depois com outro/contexto.

A introdução visual efetiva deve ocorrer quando a respectiva frente de UI/atividade/história for implementada. Antes de qualquer uso visual, reler `VISUAL_BIBLE.md`, verificar/reutilizar assets-mestre aprovados e pedir autorização para qualquer nova arte/pose. Não gerar nem substituir personagens por iniciativa própria.

### Elenco canônico
- Chiu — Chihuahua amarelo cartunesco, maluco/curioso; personagem do universo.
- Mia — gata fashion/dramática, fofoqueira, odeia segunda-feira e ama café.
- Zé Pernilongo — mosquito falante, inquieto, atrapalhado/esquecido, coração e apetite enormes.
- Barto — morcego noturno filosófico; gosta de terror e tem medo de altura apesar de voar.
- Lara — arara barulhenta/opinativa; repete tudo e não tem filtro.
- Caca — capivara zen até ficar estressada; gosta de água morna/chá e evita responsabilidades.
- Onça — forte e decidida, mas insegura por dentro; odeia perder.
- Perry — ornitorrinco brilhante/esquisito; inventa coisas inúteis que às vezes funcionam.
- Lena — preguiça muito lenta; dormir, comer e procrastinar.

REGRA ABSOLUTA: Chiu do logo/ícone e Chiu personagem são identidades visuais diferentes. Logo/ícone usa somente o Chihuahua branco fotorrealista aprovado com cabelo castanho. Histórias, cards, exercícios e universo usam o Chihuahua amarelo cartunesco aprovado. Nunca misturar.

## ESTADO ATUAL DO ANDROID
- Repositório: `Canumori/Chiu-Know`; branch `main`.
- Kotlin + Jetpack Compose; namespace/applicationId `com.chiu.know`.
- minSdk 26, targetSdk/compileSdk 35, Java/JVM 17.
- versionCode 1, versionName 0.1.0.
- Compose BOM 2024.12.01; AppCompat 1.7.0; DataStore Preferences 1.1.7.
- JUnit 4.13.2 apenas em `testImplementation`.

## FLUXO ATUAL REAL
`LANGUAGE_SELECTION → PLACEMENT_INTRO → PLACEMENT_TEST → PLACEMENT_RESULT → LEARNING_TRAIL`.

Se já existir resultado salvo para o idioma-alvo, `PLACEMENT_INTRO` também permite continuar diretamente para `LEARNING_TRAIL` sem obrigar novo teste. O reteste continua disponível.

## PLACEMENT — PRESERVAR
O placement usa núcleo adaptativo local:
- bancos separados para Português, English, Español, Français e 한국어;
- começa em B1;
- resposta correta/incorreta estreita o intervalo A1–C2 e determina a próxima faixa;
- resultado final vem do estado adaptativo;
- NÃO é teste CEFR calibrado nem certificação oficial.

Todos os cinco bancos possuem 12 perguntas: 2 em cada A1, A2, B1, B2, C1 e C2; total 60. Cada banco possui teste próprio para cobertura por nível e IDs únicos. Infraestrutura multipergunta e testes existentes permanecem protegidos.

Marcos anteriores relevantes:
- seletor seguro: `2e611fcea5be22f6d10ada096633f60b6d439230`;
- testes do seletor: `2532900844919885b14adf57bb689e3d910c30f9`;
- UI usando seletor: `24e3d54fb2d3c494acdb98acffd53146a06d1e70`;
- validação final dos 60 itens: Android CI #47 / `33518166184`: SUCCESS.

## NOVA FUNDAÇÃO — TRILHA CEFR
Arquivo: `app/src/main/java/com/chiu/know/model/CefrTrail.kt`.

Modelo determinístico atual:
- nível abaixo do placement = `COMPLETED`/alcançado;
- nível estimado = `CURRENT`;
- níveis acima = `LOCKED`;
- sempre constrói A1, A2, B1, B2, C1, C2 na ordem.

Isso é apenas a fundação visual/lógica inicial. `COMPLETED` significa que o placement colocou o aluno acima daquele ponto de partida; NÃO deve futuramente ser interpretado automaticamente como prova granular de domínio de cada conteúdo daquele nível.

Teste: `CefrTrailTest.kt`, cobrindo nível atual, estados e seis níveis.

Commit da primeira tela pós-placement: `f36782391c6be9ba6908d4522907d4e6bcd9b238` — `feat: add post-placement CEFR path screen`.
Android CI #56 / run `33533174746`: COMPLETED / SUCCESS.

## PERSISTÊNCIA/RETOMADA DA TRILHA
Commit: `15036eb13d6fcc6f15dc5b870e2573489f056743` — `feat: persist and resume CEFR learning path`.
Android CI #57 / run `33534825288`: COMPLETED / SUCCESS.

Comportamento:
- nível estimado é salvo localmente no DataStore por código do idioma-alvo (`estimated_level_<languageCode>`);
- idiomas diferentes mantêm níveis estimados separados;
- ao concluir placement, o nível é persistido;
- ao retornar a um idioma com nível salvo, a introdução mostra o nível anterior e permite `Continue to my path`;
- o usuário ainda pode refazer o placement;
- nenhum Supabase foi necessário para isso.

## PRINCÍPIOS PEDAGÓGICOS FORMALIZADOS
Commit `890190897fdaec2b4548bac77f3339029a40bae2` atualizou `PRODUCT_SPEC.md` com os princípios de efetividade. O Android CI #58 / run `33538226112` foi disparado por esse commit e, no momento desta atualização de estado, ainda deve ser conferido no GitHub antes de declarar seu resultado final.

## PRÓXIMO PASSO DE CONTINUIDADE
1. Primeiro conferir o resultado final do Android CI #58 / `33538226112`.
2. Se verde, continuar a trilha com a **primeira fatia pedagógica real**, não apenas mais decoração/UI.
3. Antes de criar muitas lições, definir/implementar um pequeno modelo de atividade que carregue explicitamente: habilidade principal, objetivo, CEFR, conhecimento-alvo, tipo de resposta, feedback e vínculo futuro de revisão.
4. Implementar somente uma pequena atividade ponta a ponta para validar arquitetura e pedagogia.
5. Como esta é a entrada da frente de cards/atividades, reler `VISUAL_BIBLE.md` e verificar assets aprovados. Personagem pode entrar se houver asset-mestre apropriado; se exigir nova pose/arte, pedir autorização antes de gerar.
6. Ainda não pular para implementação grande de FSRS, IA, áudio, backend ou histórias. Construir primeiro o modelo de atividade e a coleta de evidência de aprendizagem que essas frentes precisarão.
7. Preservar placement, 60 perguntas, testes, persistência e trilha já verdes.

## PROTEÇÕES CONTRA REGRESSÃO
- Mudanças pequenas, isoladas e reversíveis.
- Nunca substituir/reconstruir o projeto inteiro por conveniência.
- Não remover testes verdes existentes.
- Não alterar o algoritmo adaptativo sem frente específica e validação.
- Não mudar fluxo de telas sem relação com a frente atual.
- Não introduzir dependências desnecessárias.
- Não mandar Camila editar código, usar terminal, resolver conflito ou abrir ZIP.
- Conferir código real, commits e Actions antes de concluir sucesso/falha.
- Um workflow antigo falho não invalida um run atual verde; usar o run correspondente ao head relevante.

## ARTE / IDENTIDADE VISUAL
- `VISUAL_BIBLE.md` é autoritativo.
- Nenhuma arte/mascote deve ser gerada, redesenhada ou substituída por iniciativa do assistente.
- Chiu do logo = Chihuahua branco fotorrealista aprovado com cabelo castanho; reutilizar master aprovado.
- Chiu personagem = cachorro cartoon amarelo/esquisito aprovado; nunca misturar os dois.
- Identidade dos demais personagens é canônica.
- Para identidade exata, reutilizar assets-mestre. Novas poses somente após autorização/aprovação.

# ISOLAMENTO ABSOLUTO DO SUPABASE
## CHIU KNOW? — ÚNICO SUPABASE PERMITIDO
- Organização `Chiu Know`, ID `aeerqbmrwulxsawhjyvm`.
- Project ref `uskxabsodcnzlovuaurp`, região `sa-east-1`.
- A frente Android atual NÃO precisa de Supabase.

## CHIU PLAYER — PROIBIDO NESTA FRENTE
- Organização `Chiu`, ID `nnrwosbnvdvzaoflwxlo`.
- Project ref `hpcbkvbrlwjnwlikmbfb`.
- Nunca escrever nesse backend durante trabalhos do Chiu Know?.

## PROTOCOLO FUTURO DE SUPABASE
Antes de qualquer escrita futura no Supabase para Chiu Know?, ler a skill Supabase e verificar por leitura organização e projeto reais. Somente `uskxabsodcnzlovuaurp` é permitido. Se aparecer `hpcbkvbrlwjnwlikmbfb`, parar imediatamente.

## PRINCÍPIO FINAL
- `PROJECT_STATE.md` = estado/continuidade.
- `PRODUCT_SPEC.md` = roadmap, objetivos e regras pedagógicas.
- `RESEARCH.md` = arquitetura/referências.
- `VISUAL_BIBLE.md` = personagens/identidade.
- Antes de escrever: conferir estado real do GitHub; usar Supabase apenas quando necessário e somente o projeto do Chiu Know?.
- Preservar tudo que já está verde.
