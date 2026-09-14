# CHIU KNOW? — PRODUCT SPECIFICATION

## VISÃO
Chiu Know? é um aplicativo Android multilíngue de aprendizagem de idiomas, baseado no CEFR A1–C2, com ensino adaptativo, revisão inteligente, histórias interativas e tutor por IA.

O objetivo pedagógico principal não é maximizar cliques, tempo de tela, XP ou avanço aparente. O produto deve ajudar o aluno a desenvolver **competência real, retenção e capacidade de usar o idioma fora do aplicativo**.

## OBJETIVOS PRINCIPAIS
- Idioma da interface e idioma-alvo escolhidos independentemente desde o primeiro uso.
- Teste adaptativo inicial e reteste quando o aluno quiser.
- Resultado geral e por habilidade: gramática, vocabulário, listening, reading, writing e speaking.
- O resultado interno deve ser apresentado como **nível de proficiência estimado**, nunca como certificação oficial sem validação formal.
- Trilha CEFR: A1 → A2 → B1 → B2 → C1 → C2.
- Revisão espaçada/inteligente baseada em FSRS ou abordagem equivalente permissivamente licenciada.
- XP, sequência/streak, meta diária, progresso e conquistas.
- Histórias interativas com personagens recorrentes e decisões do aluno.
- Tutor de IA para explicações, diálogos e conteúdo dinâmico.

## PRINCÍPIOS DE EFETIVIDADE PEDAGÓGICA — REGRA DE PRODUTO
Toda nova frente de aprendizagem deve ser avaliada pela pergunta: **isto aumenta aprendizagem mensurável, retenção ou capacidade de usar o idioma?** Gamificação e aparência podem apoiar esse objetivo, mas não substituí-lo.

### 1. Compreender
O aluno deve receber explicações claras, curtas e adequadas ao nível sobre vocabulário, gramática, significado, uso e registro. Explicação não deve se transformar em excesso de teoria antes da prática.

### 2. Praticar ativamente
Não depender apenas de reconhecimento ou múltipla escolha. Conforme a habilidade e o nível permitirem, combinar reconhecimento com recuperação ativa: completar, reconstruir, transformar, escrever, responder, ouvir, falar e produzir linguagem.

### 3. Recuperar da memória e reter
Conteúdo aprendido deve reaparecer de forma planejada. Usar revisão espaçada/inteligente e desempenho real para decidir o que revisar. Uma resposta correta imediatamente após a explicação não prova domínio.

### 4. Transferir para novos contextos
Estruturas e palavras aprendidas devem reaparecer em situações diferentes: exercícios, diálogos, listening, reading, writing, speaking e histórias. O objetivo é evitar que o aluno memorize apenas o formato de uma atividade.

### 5. Feedback útil
Erro deve produzir feedback que ajude o aluno a entender o motivo e tentar novamente, e não apenas mostrar vermelho/verde. Sempre que tecnicamente possível, registrar padrões de dificuldade para adaptação posterior.

### 6. Progresso não é domínio
XP, streak, metas e conquistas são instrumentos de motivação. Não podem ser usados isoladamente como evidência de proficiência. Passar por uma atividade ou acumular XP não significa automaticamente que o conteúdo foi aprendido.

O desbloqueio/progresso pedagógico deve, quando a respectiva infraestrutura existir, considerar evidências de desempenho, recuperação posterior e retenção. Não inventar uma falsa precisão enquanto ainda não houver dados suficientes.

### 7. Habilidades integradas
A trilha deve evoluir para trabalhar gramática, vocabulário, listening, reading, writing e speaking. O equilíbrio pode variar conforme nível, conteúdo e desempenho individual, mas o produto não deve se tornar apenas um banco de perguntas escritas.

### 8. CEFR avançado exige tarefas avançadas
C1 e C2 não podem ser simplesmente atividades de A1–B2 com vocabulário mais raro. Devem progressivamente envolver nuance, inferência, registro, precisão, reformulação, compreensão de linguagem natural, argumentação, produção extensa e flexibilidade comunicativa compatíveis com o nível.

### 9. Adaptação com evidência
A adaptação deve responder ao histórico de desempenho do aluno e a critérios reproduzíveis. IA pode personalizar explicações e gerar variações controladas, mas não deve declarar domínio ou nível apenas por impressão subjetiva.

### 10. Medir o que importa
À medida que a infraestrutura amadurecer, acompanhar separadamente exposição, acerto imediato, recuperação posterior, retenção e desempenho por habilidade. Métricas de engajamento podem existir, mas não devem substituir métricas de aprendizagem.

## PERSONAGENS COMO FERRAMENTA PEDAGÓGICA
Os personagens canônicos definidos em `VISUAL_BIBLE.md` devem servir também à aprendizagem, e não apenas à decoração.

Quando cards, atividades, diálogos e histórias forem implementados, conteúdo previamente aprendido pode reaparecer com personagens e situações diferentes. Exemplo de princípio: uma estrutura apresentada numa atividade com um personagem pode reaparecer posteriormente em diálogo com outro e, depois, numa história, exigindo recuperação e transferência.

Regras:
- não inserir personagem apenas para preencher espaço visual;
- quando fizer sentido, associar personagem/situação a contexto comunicativo memorável;
- variar contexto sem alterar artificialmente a identidade canônica do personagem;
- preservar `VISUAL_BIBLE.md` como fonte autoritativa de identidade;
- novas artes/poses somente conforme as regras de autorização já registradas no projeto.

## AVALIAÇÃO ADAPTATIVA
A classificação CEFR não deve depender apenas da opinião de um LLM. Usar banco de itens calibrado/determinístico e evolução para CAT/IRT/Bayes conforme dados e validação permitirem. IA generativa pode ajudar a criar explicações e conteúdo, mas a estimativa de nível precisa de critérios mensuráveis e reproduzíveis.

A avaliação deve evoluir, quando houver conteúdo e validação suficientes, para fornecer resultado geral e evidências separadas por gramática, vocabulário, listening, reading, writing e speaking. Não apresentar granularidade como cientificamente calibrada antes de ela realmente ser validada.

## FALA E ÁUDIO
- Considerar whisper.cpp para ASR offline quando tecnicamente adequado.
- Transcrição e avaliação de pronúncia são problemas diferentes; não tratar precisão da transcrição como nota de pronúncia.
- Histórias podem usar áudio pré-gerado/cacheado; conversa dinâmica pode usar TTS em tempo real.
- Speaking deve avaliar comunicação de forma compatível com a capacidade técnica disponível; não prometer avaliação precisa de pronúncia sem mecanismo próprio validado.

## ARQUITETURA PRETENDIDA
- Android nativo: Kotlin + Jetpack Compose.
- UI própria da família Chiu.
- Backend dedicado e isolado no Supabase do Chiu Know?.
- Internacionalização desde o início.
- Preferir componentes MIT/BSD/Apache/permissivos.
- Não incorporar código AGPL em app proprietário sem decisão explícita sobre obrigações da licença.

## CRITÉRIO PARA NOVAS ATIVIDADES
Antes de ampliar em massa qualquer tipo de lição/atividade, validar uma pequena fatia ponta a ponta. Cada tipo de atividade deve declarar, mesmo que internamente no modelo de conteúdo:
- habilidade principal;
- objetivo de aprendizagem;
- nível CEFR pretendido;
- conteúdo/conhecimento exercitado;
- tipo de resposta exigida do aluno;
- feedback esperado;
- como o conteúdo poderá voltar em revisão/recuperação.

Isso evita produzir centenas de exercícios antes de sabermos se o modelo pedagógico e técnico funciona.

## ESCOPO DE USO
Projeto pensado inicialmente para uso privado/restrito, aproximadamente até 100 usuários, sem pressupor distribuição pública em larga escala.
