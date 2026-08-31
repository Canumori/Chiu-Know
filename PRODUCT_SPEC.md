# CHIU KNOW? — PRODUCT SPECIFICATION

## VISÃO
Chiu Know? é um aplicativo Android multilíngue de aprendizagem de idiomas, baseado no CEFR A1–C2, com ensino adaptativo, revisão inteligente, histórias interativas e tutor por IA.

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

## AVALIAÇÃO ADAPTATIVA
A classificação CEFR não deve depender apenas da opinião de um LLM. Usar banco de itens calibrado/determinístico e evolução para CAT/IRT/Bayes conforme dados e validação permitirem. IA generativa pode ajudar a criar explicações e conteúdo, mas a estimativa de nível precisa de critérios mensuráveis e reproduzíveis.

## FALA E ÁUDIO
- Considerar whisper.cpp para ASR offline quando tecnicamente adequado.
- Transcrição e avaliação de pronúncia são problemas diferentes; não tratar precisão da transcrição como nota de pronúncia.
- Histórias podem usar áudio pré-gerado/cacheado; conversa dinâmica pode usar TTS em tempo real.

## ARQUITETURA PRETENDIDA
- Android nativo: Kotlin + Jetpack Compose.
- UI própria da família Chiu.
- Backend dedicado e isolado no Supabase do Chiu Know?.
- Internacionalização desde o início.
- Preferir componentes MIT/BSD/Apache/permissivos.
- Não incorporar código AGPL em app proprietário sem decisão explícita sobre obrigações da licença.

## ESCOPO DE USO
Projeto pensado inicialmente para uso privado/restrito, aproximadamente até 100 usuários, sem pressupor distribuição pública em larga escala.
