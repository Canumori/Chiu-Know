# CHIU KNOW? — PROJECT STATE

## ATUALIZAÇÃO AUTORITATIVA — 2026-09-02 — READING A1 + SELEÇÃO BALANCEADA POR EVIDÊNCIA

Este bloco é o estado autoritativo mais recente. Em caso de conflito com histórico antigo, este bloco vence. Antes de continuar, ler junto com `PRODUCT_SPEC.md`, `RESEARCH.md` e `VISUAL_BIBLE.md`. O estado real do GitHub sempre vence documentação desatualizada.

### PROJETO E SEPARAÇÃO ABSOLUTA
- Repositório: `Canumori/Chiu-Know`
- Branch: `main`
- Chiu Know? Supabase permitido: `uskxabsodcnzlovuaurp`.
- Chiu Player Supabase PROIBIDO nesta frente: `hpcbkvbrlwjnwlikmbfb`.
- Não misturar código, backend, versões, branches, APKs ou decisões do Chiu Player com Chiu Know?.
- A frente recente de placement, trilha, atividades, evidência, Reading e seleção balanceada NÃO usou Supabase.

### ESTADO ANDROID REAL
Fluxo atual:
`LANGUAGE_SELECTION → PLACEMENT_INTRO → PLACEMENT_TEST → PLACEMENT_RESULT → LEARNING_TRAIL → LEARNING_ACTIVITY`.

Se houver nível estimado salvo por idioma-alvo, o aluno pode continuar para a trilha sem refazer placement; reteste permanece disponível.

Placement:
- núcleo adaptativo local preservado;
- cinco idiomas: Português, English, Español, Français, 한국어;
- 12 perguntas por idioma, duas por nível A1/A2/B1/B2/C1/C2;
- total 60;
- resultado é estimativa/protótipo, não certificação CEFR oficial;
- não voltar ao cálculo antigo fixo por proporção de acertos.

### APRENDIZAGEM A1 — ESTADO ATUAL VALIDADO
`LearningActivity.kt` declara habilidade, objetivo, CEFR, alvo de conhecimento, tipo de resposta, prompt, feedback, `reviewKey` e respostas aceitas.

A fundação A1 agora cobre três habilidades reais nos cinco idiomas:
- VOCABULARY: 2 variantes contextuais por idioma para a mesma saudação básica;
- GRAMMAR: 2 variantes contextuais por idioma para apresentação básica em primeira pessoa;
- READING: 2 variantes contextuais por idioma para localizar um nome explicitamente informado em uma apresentação curta.

Cada par compartilha seu `reviewKey`, de forma que o mesmo conhecimento possa reaparecer em contexto diferente sem duplicar artificialmente o alvo pedagógico. Hoje existem exatamente 6 atividades A1 candidatas por idioma: 2 Vocabulary + 2 Grammar + 2 Reading. Não existe conteúdo artificial de níveis superiores para preencher trilha.

Commits da expansão Reading:
- `8e50f38ecde412122c2ade5e6aeff3042cc97bab` — primeira fatia A1 de Reading determinística;
- `e883d91cd3ebaf1db0a3cd49957f518f34ec6e33` — integra Reading ao starter bank;
- `592b1871e9439eb75c513a1329775e2ceab50258` — testes específicos iniciais de Reading;
- `e33b256a6fdc14e3f3ed634d9f5cd3d04467d891` — atualiza teste integrado antigo para aceitar Reading; Android CI #103 / run `33653824194`: SUCCESS;
- `622054a72adb5335861023932d11c41eef124b45` — adiciona segundo contexto de Reading por idioma;
- `19d3a31aba62b9d65b2b5e96ecd9c81026f60edd` — protege transferência contextual de Reading;
- `8560f386327305cc957ddb34f7d50b88116a73da` — protege rotação de 6 atividades; Android CI #106 / run `33654295692`: SUCCESS.

A correção continua determinística por `isLearningAnswerCorrect(...)`. Não substituir por julgamento subjetivo de IA.

### SELEÇÃO A1 BASEADA EM EVIDÊNCIA OBSERVADA
Foi criada uma camada pura de seleção em `StarterReviewSelection.kt`:
- commit `571b1dab6f2260922c52a744089ec60a7a75e243` — `starterLearningActivityForEvidence(...)`;
- escolhe primeiro o `reviewKey` com menos tentativas observadas;
- em empate mantém ordem estável do currículo;
- dentro do alvo escolhido alterna as variantes contextuais;
- filtra evidência pelo nível e pelos `reviewKey` realmente candidatos;
- NÃO usa acerto para declarar domínio;
- NÃO é FSRS, mastery, XP, streak, desbloqueio ou progressão CEFR.

Commit `7a5130c090c38c9e5373ca6f8b1566d0f4d8d9cd` adicionou testes dessa seleção. Android CI #108 / run `33654755461`: SUCCESS.

A UI real foi então conectada a essa seleção em `ChiuKnowApp.kt`:
- commit `150fbff9624a648e668f2812c4842aaaa5b03517` — troca a escolha antiga por contagem total pelo seletor balanceado;
- esse commit removeu acidentalmente uma chave de fechamento de `PlacementResultScreen`, causando CI #109 FAILURE ainda na etapa de testes/compilação;
- commit `aae3d7276ac401056f68f931a2d2cb7c207c9465` restaurou somente essa fronteira sintática;
- Android CI #110 / run `33655546522`: COMPLETED / SUCCESS.

A ligação UI ↔ evidência ↔ seletor está, portanto, validada no HEAD `aae3d7276ac401056f68f931a2d2cb7c207c9465`.

### EVIDÊNCIA LOCAL — PRESERVAR SEM CONFUNDIR COM DOMÍNIO
Cada tentativa gera `LearningEvidence` com activityId, reviewKey, nível, habilidade, correto/incorreto e timestamp. Evidência é tentativa observada; NÃO é mastery, XP, streak, desbloqueio, CEFR ou FSRS.

Persistência local por idioma em DataStore. Resposta textual bruta do aluno não é salva. Há decoder tolerante a entradas malformadas e agregação factual por atividade/reviewKey.

Infra já validada:
- `2d003e830695b3c96a1801f9c5c29c0fc66f2958` — `encodeLearningEvidence(...)` centralizado;
- `31f1289d9115d5cc206470b1eea2a660e697dc08` — teste de roundtrip;
- `cacbe5a9ad40bee062769331053d16b90b803f70` — UI usa o encoder centralizado;
- Android CI #93 / run `33652350240`: SUCCESS.

DataStore string-set continua sendo fundação pequena, não armazenamento definitivo para histórico ilimitado.

### TRILHA CEFR
Commit `2121e7ca3a16811e5046f71d7b331bde26568dc3` mantém `LearningTrailScreen` scroll-safe. Preservar em telas pequenas. Não alterar `CenteredColumn` globalmente para resolver rolagem local.

### PRINCÍPIO PEDAGÓGICO AUTORITATIVO
O objetivo é competência real, retenção e uso do idioma fora do app, não maximizar cliques/XP. Antes de produzir atividades em massa, validar pequenas fatias ponta a ponta. Evoluir para gramática, vocabulário, listening, reading, writing e speaking. C1/C2 exigem tarefas qualitativamente avançadas. Não fabricar scores por habilidade sem evidência suficiente.

A seleção atual apenas equilibra exposição/repetição observada entre alvos disponíveis. Ela NÃO mede retenção ao longo do tempo, dificuldade, estabilidade de memória ou domínio. Não chamar de revisão espaçada completa nem FSRS.

### PERSONAGENS E VISUAL — PRESERVAÇÃO OBRIGATÓRIA
Nome canônico do mosquito/pernilongo: **Jurandir**. O nome antigo está aposentado e só pode aparecer como proveniência histórica na Bíblia Visual.

Elenco canônico: Chiu amarelo, Mia, Jurandir, Barto, Lara, Caca, Onça, Perry e Lena.

REGRA ABSOLUTA DOS DOIS CHIUS:
- logo/ícone: somente Chihuahua branco fotorrealista aprovado;
- universo, cards, exercícios, histórias e atividades: somente Chihuahua amarelo cartunesco aprovado;
- nunca misturar os dois.

`VISUAL_BIBLE.md` registra quatro pranchas e protocolo obrigatório: master aprovado é imutável; reutilizar arquivo em vez de recriar; nova pose é candidata e precisa ser mostrada/aprovada; não deformar/recolorir/trocar estilo; usar masters individuais transparentes de alta resolução quando disponíveis; manter registro de aprovação.

As pranchas visuais B/C/D foram aprovadas como direção. A prancha C/master sheet transparente foi gerada na conversa, mas o binário aprovado ainda NÃO está incorporado ao repositório/APK. Ausência do binário no GitHub não autoriza inventar substituto nem regenerar silenciosamente.

### PRÓXIMA FRENTE EXATA
1. Confirmar HEAD e CI reais antes de escrever.
2. A fundação A1 de Vocabulary + Grammar + Reading já está ponta a ponta e com seleção balanceada por evidência observada.
3. Próximo passo seguro: revisar o modelo atual de `ResponseType` e a UI para identificar qual nova habilidade/tipo de resposta pode entrar sem fingir capacidade inexistente.
4. Priorizar diversidade real de resposta em vez de apenas produzir mais FILL_IN. Uma candidata possível é REORDER/transformação se a UI atual puder suportar de modo determinístico; verificar código antes de decidir.
5. Listening e Speaking exigem áudio/ASR/TTS ou mecanismos próprios e podem exigir decisão de produto/arquitetura. Não fingir implementação por meio de texto.
6. Escrita livre exige avaliador próprio; não usar correspondência exata simples como se fosse avaliação de escrita.
7. Não pular prematuramente para FSRS completo, XP/streak, desbloqueio automático, IA, backend ou histórias completas.
8. Resultado por seis habilidades continua obrigação do roadmap; não fabricar seis scores com o placement atual.
9. Se uma frente exigir nova pose/asset, parar antes da integração e mostrar amostra à usuária.
10. Fora de decisão real de produto/arte, continuar etapas seguras sem pedir microdecisões.

### REGRA DE CONTINUIDADE
A usuária não é programadora. Não pedir edição de código, terminal, conflitos ou ZIP. Usar ferramentas conectadas. Preservar o que funciona, fazer mudanças pequenas/reversíveis e validar CI.

A usuária pediu explicitamente continuar todas as etapas seguras até surgir uma decisão real dela. CI assíncrono pode exigir novo turno para conferência, mas isso não é decisão de produto.

---

## HISTÓRICO ANTERIOR
Commits e arquivos anteriores permanecem no GitHub. Em conflito, esta atualização autoritativa vence.