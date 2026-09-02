# CHIU KNOW? — PROJECT STATE

## ATUALIZAÇÃO AUTORITATIVA — 2026-09-02 — FUNDAÇÃO PEDAGÓGICA ESTÁVEL + PERSONAGENS LIBERADOS PARA AMOSTRAS

Este bloco é o estado autoritativo mais recente. Em caso de conflito com histórico antigo deste arquivo, este bloco vence. Antes de continuar, ler junto com `PRODUCT_SPEC.md`, `RESEARCH.md` e `VISUAL_BIBLE.md`. O estado real do GitHub sempre vence documentação desatualizada.

### PROJETO E SEPARAÇÃO ABSOLUTA
- Repositório: `Canumori/Chiu-Know`
- Branch: `main`
- Chiu Know? Supabase permitido: `uskxabsodcnzlovuaurp`.
- Chiu Player Supabase PROIBIDO nesta frente: `hpcbkvbrlwjnwlikmbfb`.
- Não misturar código, backend, versões, branches, APKs ou decisões do Chiu Player com Chiu Know?.
- A frente recente de placement, trilha, atividade, evidência e arte não usou Supabase.

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

### PRIMEIRA FATIA DE APRENDIZAGEM
`LearningActivity.kt` define habilidade, objetivo, CEFR, alvo de conhecimento, tipo de resposta, prompt, feedback, reviewKey e respostas aceitas.

`StarterLearningActivities.kt` contém uma atividade A1 de recuperação de vocabulário por idioma. A UI usa o nível estimado real ao procurar atividade; não existe mais exposição A1 hardcoded para aluno de nível superior.

A correção é determinística por `isLearningAnswerCorrect(...)`. Não substituir por julgamento subjetivo de IA.

A tela permite responder, verificar, receber correto/incorreto, receber feedback e voltar à trilha.

As strings dessa primeira atividade estão localizadas nos cinco idiomas. Últimos commits relevantes:
- PT `15ead3963059462468ab92ad12aafd55c0b94e52`;
- ES `a1f4caf2873a3cb69a1360999cda9ca15eaf62ee`;
- FR `b5ec61bc797c6b06125fad2b7628524319dc27c7`;
- KO `b47064dc5992f54b63fab308f2f889cb4badcfd7`.
Korean CI #83 / run `33646155836`: SUCCESS.

### EVIDÊNCIA LOCAL — PRESERVAR SEM CONFUNDIR COM DOMÍNIO
Cada tentativa gera `LearningEvidence` com activityId, reviewKey, nível, habilidade, correto/incorreto e timestamp. Evidência é tentativa observada; NÃO é mastery, XP, streak, desbloqueio, CEFR ou FSRS.

Persistência local por idioma em DataStore. Resposta textual bruta do aluno não é salva. Há decoder tolerante a entradas malformadas e agregação factual por atividade/reviewKey. O estado persistido é restaurado internamente, sem exibir score falso de domínio.

Ainda existe débito técnico pequeno: encoder permanece inline em `ChiuKnowApp.kt`; futuramente pode virar função dedicada com teste roundtrip. DataStore string-set é fundação pequena, não armazenamento definitivo para histórico ilimitado.

### TRILHA CEFR — SCROLL CORRIGIDO
Commit `2121e7ca3a16811e5046f71d7b331bde26568dc3` — `fix: make CEFR learning trail scroll-safe`.
Android CI #84 / run `33646698497`: COMPLETED / SUCCESS.

Somente `LearningTrailScreen` ganhou rolagem própria. O `CenteredColumn` compartilhado NÃO foi alterado globalmente. Preservar essa correção para telas pequenas.

### PRINCÍPIO PEDAGÓGICO AUTORITATIVO
O objetivo do produto é competência real, retenção e uso do idioma fora do app, não maximizar cliques/XP. Antes de produzir atividades em massa, validar pequenas fatias ponta a ponta. Evoluir para gramática, vocabulário, listening, reading, writing e speaking. C1/C2 exigem tarefas qualitativamente avançadas. Não fabricar scores por habilidade sem evidência suficiente.

### PERSONAGENS — DECISÃO NOVA E AUTORIZAÇÃO
A usuária forneceu uma prancha visual do Universo Chiu Know? com todo o elenco e confirmou que aquela é a referência que faltava. Depois pediu mudar o nome do antigo Zé Pernilongo para **Jurandir**. Foi gerada uma nova versão da prancha com JURANDIR e a usuária respondeu **“Aprovado”**.

Portanto:
- o nome canônico agora é **Jurandir**;
- o nome antigo Zé Pernilongo está aposentado e não deve reaparecer em UI, histórias, atividades, documentação ou novos assets;
- `VISUAL_BIBLE.md` foi atualizado no commit `bdb093ece0eb97d85d0f26cc7bbd1a9a380834c9`.

Elenco canônico atual:
1. Chiu — Chihuahua amarelo cartunesco maluco/curioso;
2. Mia — gata fashion/dramática;
3. Jurandir — mosquito/pernilongo inquieto, atrapalhado/esquecido;
4. Barto — morcego filosófico/noturno;
5. Lara — arara barulhenta/opinativa;
6. Caca — capivara zen;
7. Onça — onça-pintada forte, decidida e insegura por dentro;
8. Perry — ornitorrinco brilhante/esquisito;
9. Lena — preguiça lenta/procrastinadora.

REGRA ABSOLUTA DOS DOIS CHIUS:
- logo/ícone: somente Chihuahua branco fotorrealista aprovado, cabelo castanho, olhos grandes e dentinhos;
- universo, cards, exercícios, histórias e atividades: somente Chihuahua amarelo cartunesco aprovado;
- nunca misturar os dois.

### STATUS DOS ASSETS E AUTORIZAÇÃO VISUAL
A prancha aprovada existe como imagem/anexo da conversa, mas o arquivo binário ainda não está incorporado ao repositório. O repositório registra a identidade e a aprovação em `VISUAL_BIBLE.md` para não haver perda de continuidade.

A usuária AUTORIZOU criar novas poses/imagens necessárias dos personagens oficiais para as primeiras atividades pedagógicas, mantendo rigorosamente a aparência canônica. Condição obrigatória: **ela quer ver amostras e aprová-las antes de qualquer incorporação ao APK**.

Não integrar uma nova amostra automaticamente. Primeiro mostrar. Somente após aprovação explícita pode entrar no app. Não redesenhar identidade dos personagens livremente.

### PRÓXIMA FRENTE EXATA
A fundação técnica anterior está validada. Agora iniciar expansão pedagógica controlada conforme `PRODUCT_SPEC.md`, começando por pequenas amostras/fatias e não por produção em massa.

Sequência segura:
1. Confirmar o HEAD e CI reais antes de escrever código.
2. Se a frente exigir personagem visual, usar a referência aprovada e produzir AMOSTRA primeiro; mostrar à usuária antes de integrar.
3. Expandir atividades em fatias pequenas com habilidade, objetivo, CEFR, conhecimento-alvo, tipo de resposta, feedback e reviewKey explícitos.
4. Projetar recorrência/retrieval do mesmo conteúdo em contexto diferente antes de chamar algo de aprendido.
5. Não pular prematuramente para implementação grande de FSRS, XP/streak, desbloqueio automático, IA, áudio/speaking, backend ou histórias completas.
6. Resultado por seis habilidades continua obrigação do roadmap, mas não fabricar seis scores com o placement atual. Primeiro criar evidência adequada por habilidade.
7. Quando uma nova decisão real de produto/arte for necessária, parar e perguntar à usuária. Fora disso, continuar por etapas seguras sem pedir microdecisões.

### REGRA DE CONTINUIDADE PARA O PRÓXIMO CHAT
A usuária não é programadora. Não pedir que edite código, use terminal, resolva conflitos ou manipule ZIP. Usar ferramentas do GitHub e demais conectores quando necessário. Preservar o que funciona, fazer mudanças pequenas/reversíveis, validar CI após mudanças, e não depender apenas da memória do chat.

A usuária pediu explicitamente: **continuar todas as etapas seguras até surgir uma decisão real dela e só então informá-la**. Se um CI estiver assíncrono, será necessário novo turno para verificar, mas não transformar isso em decisão de produto.

---

## HISTÓRICO ANTERIOR
O estado anterior detalhava a construção da trilha, atividade e evidência local. Os arquivos e commits permanecem no GitHub. Quando houver conflito, a atualização autoritativa acima vence.
