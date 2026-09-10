# CHIU KNOW? — PEDAGOGY ARCHITECTURE

Status: decisão de produto aprovada pela usuária em 2026-09-03. Este documento complementa `PROJECT_STATE.md` e `PRODUCT_SPEC.md`. Não substitui o estado real do GitHub nem as regras de segurança/visual já registradas.

## Objetivo
Construir aprendizagem efetiva e transferível para fora do app, e não maximizar cliques, XP ou acertos por reconhecimento. A arquitetura combina princípios observados em produtos líderes de ensino de línguas com CEFR e evidências de ciência da aprendizagem, sem copiar interface, conteúdo, marca ou implementação de terceiros.

## Referências de produto estudadas
Foram comparadas abordagens públicas de Duolingo, Babbel, Busuu, Memrise, Rosetta Stone, LingQ, Pimsleur, ELSA Speak e HelloTalk, além do CEFR/CEFR Companion Volume e literatura sobre spacing/retrieval practice.

## Decisões pedagógicas

### 1. Hábito sem confundir jogo com domínio
Usar sessões acessíveis, continuidade e motivação, mas XP/streak/gamificação nunca serão evidência de domínio. Progresso visual e mastery permanecem conceitos separados.

### 2. Ciclo de aprendizagem por transferência
Um alvo não deve ficar preso a uma única pergunta. Sempre que o conteúdo permitir, a progressão desejada é:
1. compreender em contexto;
2. reconhecer;
3. recuperar com pista;
4. produzir com menos pista;
5. recuperar sem pista;
6. aplicar em contexto diferente;
7. revisar espaçadamente;
8. demonstrar retenção/transferência.
Ver a resposta correta ou acertar uma única atividade não equivale a saber.

### 3. FSRS/retrieval como infraestrutura central
Preservar o scheduler FSRS-6 próprio já implementado e separado da evidência. Repetição deve favorecer recuperação ativa e espaçamento. Erro conta como tentativa/exposição, nunca como mastery. Prática opcional continua separada do agendamento até existir decisão explícita diferente.

### 4. Personalização por objetivo
Após placement, futuramente coletar objetivo do aluno (ex.: viagem, trabalho, conversação, prova, morar fora, relacionamento/cultura, aprendizado geral), disponibilidade aproximada de estudo e prioridade de competência. Isso personaliza contexto, vocabulário e distribuição de prática; NÃO remove competências essenciais do currículo CEFR.

### 5. CEFR multidimensional
A1–C2 continua como eixo global, mas o produto deve evoluir para evidência por competência. Não fabricar scores independentes a partir de múltipla escolha. Só exibir leitura, compreensão oral, escrita, fala/interação, pronúncia ou outras dimensões quando houver tarefas e evidência válidas para aquela dimensão.
Considerar recepção, produção, interação e mediação, além de competência linguística, sociolinguística e pragmática conforme evolução do produto.

### 6. Input progressivamente autêntico
Listening e reading devem evoluir de material controlado para uso real da língua. Progressão desejada de listening: fala clara/controlada → fala natural → variação de velocidade → vozes/sotaques apropriados → contexto/ruído realista → conteúdo autêntico nos níveis avançados. Não fingir listening antes de existir áudio real validado.

### 7. Conversação desde níveis iniciais
Não esperar B2 para interação. Progressão futura:
- A1: respostas altamente guiadas;
- A2: diálogos com escolhas e pequenas produções;
- B1: role-play com menos roteiro;
- B2: situações abertas;
- C1: argumentação, negociação, explicação, mudança de registro;
- C2: nuance, ambiguidade, ironia quando culturalmente apropriada, improvisação e alta flexibilidade.
Em conversação livre, evitar interromper toda frase; permitir feedback pós-bloco quando isso preservar fluência.

### 8. Pronúncia é diferente de ASR
Transcrição correta não prova boa pronúncia. Quando speaking for implementado, separar pelo menos inteligibilidade, sons/contrastes relevantes, ritmo/fluência, tonicidade/prosódia e entonação conforme o idioma. Não declarar pronúncia avaliada usando somente speech-to-text.

### 9. Gramática e vocabulário em contexto
Evitar listas desconectadas e repetição mecânica. Um mesmo alvo deve reaparecer em leitura, áudio, frase, diálogo, história, produção e revisão, quando pedagogicamente adequado. Explicações gramaticais devem ser úteis e contextualizadas, não virar finalidade isolada.

### 10. Histórias como estrutura pedagógica
Chiu, Mia, Jurandir e demais personagens não são decoração. Histórias devem poder conectar input, vocabulário, gramática, compreensão, diálogo, produção e recuperação posterior em novo contexto. Preservar integralmente `VISUAL_BIBLE.md` e a regra absoluta dos dois Chius.

### 11. Feedback útil
Feedback deve explicar o erro ou fornecer contraste/pista quando isso ajuda. Diferenciar prática de fluência de prática de precisão. Em tarefas de fluência, feedback pode vir depois do bloco; em exercícios focais, feedback imediato pode ser melhor.

### 12. Placement e checkpoints
Placement atual continua local, determinístico, adaptativo e inicia em B1. O banco atual (12 itens/idioma; 2 por A1–C2) é fundação, não avaliação final confiável. Expandir com itens variados e revisados e, futuramente, evidência de competências reais. Não voltar a porcentagem fixa e não chamar de certificação.
Checkpoints futuros devem reajustar o percurso com base em evidência recente e retenção, sem apagar histórico.

## Modelo-alvo de jornada
`PLACEMENT → DIAGNÓSTICO HONESTO → OBJETIVO/PREFERÊNCIAS → CONTEÚDO CEFR → INPUT COMPREENSÍVEL → PRÁTICA ATIVA → FEEDBACK → RETRIEVAL COM MENOS PISTAS → FSRS → TRANSFERÊNCIA PARA NOVO CONTEXTO → INTERAÇÃO/CONVERSAÇÃO → CHECKPOINT → REAJUSTE`

## O que aproveitar conceitualmente dos líderes
- Duolingo: hábito, sessões acessíveis, adaptação e motivação; não copiar dependência de XP.
- Babbel: contexto real, diálogos, gramática contextual e revisão de dificuldades.
- Busuu: CEFR, objetivos/plano, checkpoints e produção/interação.
- Memrise: exposição a linguagem e vozes naturais.
- LingQ: input compreensível e progressão para material autêntico.
- Pimsleur: recuperação ativa e intervalos progressivos.
- ELSA Speak: tratar pronúncia com granularidade própria, não como simples transcrição.
- HelloTalk: valor da interação real/social; qualquer componente social futuro exigirá desenho próprio de privacidade, moderação e segurança.

## Regras de não-cópia
Não copiar textos, exercícios, bancos, assets, fluxos proprietários, marcas, personagens ou implementação dos concorrentes. Usar somente princípios pedagógicos gerais e pesquisa pública para orientar solução própria.

## Métricas que importam
Priorizar retenção posterior, recuperação sem pistas, transferência para contexto novo, compreensão de material não memorizado, redução de erros recorrentes e desempenho em tarefas adequadas ao CEFR. Tempo no app, streak e XP podem medir engajamento, mas nunca substituem aprendizagem.

## Estado atual versus destino
Já existem: placement adaptativo local, trilha CEFR, atividades FILL_IN/REORDER/MULTIPLE_CHOICE, evidência local, feedback, FSRS-6 próprio, fila de revisão e prática opcional.
Ainda não existem de forma válida/completa: placement robusto por competências, listening real em massa, speaking/ASR, avaliação de pronúncia, escrita livre avançada, histórias funcionais completas, tutor IA, checkpoints multidimensionais, conteúdo A1–C2 completo e mastery real.

## Próximas frentes seguras
1. Fortalecer testes/contratos do placement e ampliar gradualmente o banco, sem massificar itens rasos.
2. Definir modelo de evidência por competência antes de exibir scores multidimensionais.
3. Definir perfil de objetivo/disponibilidade/prioridade sem alterar ainda o currículo essencial.
4. Planejar progressão de retirada de pistas e transferência entre formatos.
5. Planejar histórias como unidades pedagógicas conectadas.
6. Listening real depende da frente de áudio/voz segura já registrada em `PROJECT_STATE.md`; não contornar o bloqueio de Storage.
7. Speaking/pronúncia só depois de mecanismo honesto de captura/análise e critérios válidos.

## Regra de implementação
Fazer fatias pequenas, reversíveis e testadas. CI verde antes da próxima mudança estrutural. Não publicar APK automaticamente. Não tocar no Supabase do Chiu Player. Não inventar funcionalidades que ainda não existem.