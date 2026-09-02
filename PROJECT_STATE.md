# CHIU KNOW? — PROJECT STATE

## ATUALIZAÇÃO AUTORITATIVA — 2026-09-02 — A1 MULTIHABILIDADE EM EXPANSÃO CONTROLADA

Este bloco é o estado autoritativo mais recente. Em caso de conflito com histórico antigo, este bloco vence. Antes de continuar, ler junto com `PRODUCT_SPEC.md`, `RESEARCH.md` e `VISUAL_BIBLE.md`. O estado real do GitHub sempre vence documentação desatualizada.

### PROJETO E SEPARAÇÃO ABSOLUTA
- Repositório: `Canumori/Chiu-Know`
- Branch: `main`
- Chiu Know? Supabase permitido: `uskxabsodcnzlovuaurp`.
- Chiu Player Supabase PROIBIDO nesta frente: `hpcbkvbrlwjnwlikmbfb`.
- Não misturar código, backend, versões, branches, APKs ou decisões do Chiu Player com Chiu Know?.
- A frente recente de placement, trilha, atividades, evidência e arte não usou Supabase.

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

### APRENDIZAGEM A1 — ESTADO ATUAL
`LearningActivity.kt` declara habilidade, objetivo, CEFR, alvo de conhecimento, tipo de resposta, prompt, feedback, `reviewKey` e respostas aceitas.

A primeira fatia A1 de VOCABULÁRIO existe nos cinco idiomas e trabalha uma saudação básica. Cada idioma possui duas variantes contextuais do mesmo conhecimento, compartilhando `reviewKey`, para recuperação/transferência em vez de mera repetição da mesma pergunta.

Commit `d6297259adfd91bf14ba90a791c41463615bddde` adicionou as variantes contextuais. Commit `bdf9c7d0adb3d7174972cac72ce771faf2a5f88f` conectou a seleção ao histórico real de evidências locais. CI #90: SUCCESS.

A segunda fatia A1, agora de GRAMÁTICA, foi adicionada nos cinco idiomas. Ela trabalha apresentação básica em primeira pessoa e também possui duas variantes contextuais por idioma, com personagens canônicos em contexto textual e sem depender de novos assets binários.

Commits relevantes da expansão:
- `4bd64129dfccb31eea134035d95434d45824aa9b` — adiciona a fatia A1 de gramática;
- `8243623946038e8e2fc4f16a5e43c5faf0d23c56` — integra vocabulário + gramática à rotação A1;
- `497dd6250d85915eb226704bc681ddea539c877d` — testes específicos da gramática;
- `26c798d68f8a2ca138982dedaa43cba389d60b45` — testes da rotação integrada;
- `153c9bc1c48076b207804bda65e09e22bf6af173` — substitui expectativa antiga de apenas duas atividades.
Android CI #98 / run `33652898608`: COMPLETED / SUCCESS.

A correção continua determinística por `isLearningAnswerCorrect(...)`. Não substituir por julgamento subjetivo de IA. Não existe conteúdo falso de níveis superiores apenas para preencher a trilha.

### EVIDÊNCIA LOCAL — PRESERVAR SEM CONFUNDIR COM DOMÍNIO
Cada tentativa gera `LearningEvidence` com activityId, reviewKey, nível, habilidade, correto/incorreto e timestamp. Evidência é tentativa observada; NÃO é mastery, XP, streak, desbloqueio, CEFR ou FSRS.

Persistência local por idioma em DataStore. Resposta textual bruta do aluno não é salva. Há decoder tolerante a entradas malformadas e agregação factual por atividade/reviewKey.

O débito técnico do encoder inline foi resolvido:
- `2d003e830695b3c96a1801f9c5c29c0fc66f2958` — `encodeLearningEvidence(...)` centralizado;
- `31f1289d9115d5cc206470b1eea2a660e697dc08` — teste de roundtrip;
- `cacbe5a9ad40bee062769331053d16b90b803f70` — UI passa a usar o encoder centralizado;
- Android CI #93 / run `33652350240`: COMPLETED / SUCCESS.

DataStore string-set continua sendo fundação pequena, não armazenamento definitivo para histórico ilimitado.

### TRILHA CEFR
Commit `2121e7ca3a16811e5046f71d7b331bde26568dc3` mantém `LearningTrailScreen` scroll-safe. Preservar em telas pequenas. Não alterar `CenteredColumn` globalmente para resolver rolagem local.

### PRINCÍPIO PEDAGÓGICO AUTORITATIVO
O objetivo é competência real, retenção e uso do idioma fora do app, não maximizar cliques/XP. Antes de produzir atividades em massa, validar pequenas fatias ponta a ponta. Evoluir para gramática, vocabulário, listening, reading, writing e speaking. C1/C2 exigem tarefas qualitativamente avançadas. Não fabricar scores por habilidade sem evidência suficiente.

A rotação atual ainda é uma fundação simples. Ela alterna atividades A1 disponíveis com base no histórico, mas isso NÃO é um algoritmo de mastery nem revisão espaçada completa. Não chamar de FSRS.

### PERSONAGENS E VISUAL — PRESERVAÇÃO OBRIGATÓRIA
Nome canônico do mosquito/pernilongo: **Jurandir**. O nome antigo está aposentado e só pode aparecer como proveniência histórica na Bíblia Visual.

Elenco canônico: Chiu amarelo, Mia, Jurandir, Barto, Lara, Caca, Onça, Perry e Lena.

REGRA ABSOLUTA DOS DOIS CHIUS:
- logo/ícone: somente Chihuahua branco fotorrealista aprovado;
- universo, cards, exercícios, histórias e atividades: somente Chihuahua amarelo cartunesco aprovado;
- nunca misturar os dois.

`VISUAL_BIBLE.md` foi endurecido no commit `73d96780a5af5d4d0b65289d27c5c4069bf413f3` com registro das quatro pranchas e protocolo obrigatório: master aprovado é imutável; reutilizar arquivo em vez de recriar; nova pose é candidata e precisa ser mostrada/aprovada; não deformar/recolorir/trocar estilo; usar masters individuais transparentes de alta resolução quando disponíveis; manter registro de aprovação.

As pranchas visuais B/C/D foram aprovadas como direção. A prancha C/master sheet transparente foi gerada na conversa, mas o binário aprovado ainda NÃO está incorporado ao repositório/APK. Ausência do binário no GitHub não autoriza inventar substituto nem regenerar silenciosamente.

A usuária autorizou criar novas poses necessárias, mas toda pose nova continua `sample-first`: mostrar e obter aprovação explícita antes de integrar no APK.

### PRÓXIMA FRENTE EXATA
1. Confirmar HEAD e CI reais antes de escrever.
2. Continuar a expansão A1 em fatias pequenas, priorizando diversidade real de habilidade/tipo de resposta, não volume de perguntas equivalentes.
3. Próxima candidata segura: uma pequena fatia de READING A1 com compreensão curta e avaliação determinística, sem áudio/IA/backend e sem exigir nova arte.
4. Depois, validar recorrência do conhecimento entre vocabulário/gramática/reading sem inferir mastery.
5. Listening, speaking e escrita livre exigem mecanismos/evaluadores próprios e devem entrar somente em fatias controladas; não fingir que FILL_IN cobre essas habilidades.
6. Não pular prematuramente para FSRS completo, XP/streak, desbloqueio automático, IA, backend ou histórias completas.
7. Resultado por seis habilidades continua obrigação do roadmap; não fabricar seis scores com o placement atual.
8. Se uma frente exigir nova pose/asset, parar antes da integração e mostrar amostra à usuária.
9. Fora de decisão real de produto/arte, continuar etapas seguras sem pedir microdecisões.

### REGRA DE CONTINUIDADE
A usuária não é programadora. Não pedir edição de código, terminal, conflitos ou ZIP. Usar ferramentas conectadas. Preservar o que funciona, fazer mudanças pequenas/reversíveis e validar CI.

A usuária pediu explicitamente continuar todas as etapas seguras até surgir uma decisão real dela. CI assíncrono pode exigir novo turno para conferência, mas isso não é decisão de produto.

---

## HISTÓRICO ANTERIOR
Commits e arquivos anteriores permanecem no GitHub. Em conflito, esta atualização autoritativa vence.