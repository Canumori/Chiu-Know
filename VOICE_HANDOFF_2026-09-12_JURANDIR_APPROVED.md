# CHIU KNOW? — JURANDIR VOICE APPROVAL HANDOFF

## AUTORITATIVO — 2026-09-12 — SUPERSEDE O BLOCO ANTERIOR QUE MARCAVA JURANDIR COMO PENDENTE

Base verificada antes deste registro:
- `main` HEAD: `797c4c92c8ea7202c842d3cbb89986380eb55218`;
- commit: `docs: record voice references and Jurandir pause`;
- Android CI #483, run `34716144580`: `COMPLETED / SUCCESS` no SHA exato.

Estado real posterior ao commit #483:
- a usuária gravou e enviou uma performance original própria para Jurandir;
- o arquivo final aprovado no chat é `jurandir.mp3`;
- a usuária confirmou explicitamente o resultado final com **“Está ok”**;
- este arquivo final NÃO é `jurandir_original_attempt_1.mp3` e NÃO é o trecho de referência do Eric Cartman;
- metadata verificada do arquivo aprovado: MP3, aproximadamente 5.424 s, 24 kHz, estéreo, 87,212 bytes;
- por ser uma performance original gravada pela própria usuária e aprovada por ela, pode ser tratado como fonte autorizada do personagem Jurandir;
- o trecho de Eric Cartman continua sendo apenas referência artística externa e não deve ser publicado, incluído no APK ou usado como fonte de clonagem reconhecível;
- o `bzzz` artificial no começo continua explicitamente rejeitado;
- tentativas sintéticas anteriores de Jurandir continuam rejeitadas e não devem ser confundidas com o arquivo final aprovado.

Proteções:
- não alterar Chiu, Mia ou outras vozes já aprovadas;
- não tocar em LearningEvidence, fila, FSRS, scheduler, mastery, Supabase ou CHIU PLAYER por causa desta aprovação;
- antes de integrar o áudio no APK, preservar o arquivo aprovado sem transformação artística adicional, salvo limpeza técnica estritamente necessária e validada;
- não substituir esta gravação por uma nova síntese sem nova aprovação da usuária.

Próximo passo seguro:
1. integrar a amostra `jurandir.mp3` como recurso local do preview de vozes quando a ferramenta usada permitir upload binário sem corromper o arquivo;
2. expor Jurandir no mesmo fluxo de preview já usado para Chiu e Mia, mantendo reprodução mutuamente exclusiva e liberação de `MediaPlayer`;
3. testar em CI e depois em aparelho físico;
4. somente depois seguir para a próxima voz ainda não aprovada.

Este arquivo existe para impedir regressão de estado em novos chats: **Jurandir está aprovado no nível de amostra de voz pelo usuário; o GitHub anterior estava desatualizado nesse ponto.**
