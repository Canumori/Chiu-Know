# CHIU KNOW? — JURANDIR VOICE APPROVAL HANDOFF

## AUTORITATIVO — 2026-09-12 — SUPERSEDE O BLOCO ANTERIOR QUE MARCAVA JURANDIR COMO PENDENTE

Base verificada antes deste registro:
- `main` estava verde em `797c4c92c8ea7202c842d3cbb89986380eb55218` — `docs: record voice references and Jurandir pause`;
- Android CI #483, run `34716144580`: `COMPLETED / SUCCESS` no SHA exato.

Estado real posterior ao commit #483:
- a usuária gravou e enviou uma performance original própria para Jurandir;
- o arquivo final aprovado no chat é `jurandir.mp3`;
- a usuária confirmou explicitamente o resultado final com **“Está ok”**;
- este arquivo final NÃO é `jurandir_original_attempt_1.mp3` e NÃO é o trecho de referência do Eric Cartman;
- metadata verificada do arquivo aprovado: MP3, aproximadamente 5.424 s, 24 kHz, estéreo, 87.212 bytes;
- o trecho de Eric Cartman continua sendo apenas referência artística externa e não deve ser publicado, incluído no APK ou usado como fonte de clonagem reconhecível;
- o `bzzz` artificial no começo continua explicitamente rejeitado;
- tentativas sintéticas anteriores de Jurandir continuam rejeitadas e não devem ser confundidas com o arquivo final aprovado.

## REGRA POSTERIOR E OBRIGATÓRIA — NÃO COLOCAR JURANDIR NO APK

Durante a continuação da frente de voz, a usuária determinou explicitamente: **“Não quero que você coloque nenhuma voz no APK.”**

Aplicação segura dessa instrução no contexto de Jurandir:
- NÃO adicionar `jurandir.mp3`, versão limpa, WAV, MP3 convertido, clone ou derivado como recurso `res/raw` do APK;
- NÃO expor Jurandir no preview de vozes do APK sem uma nova decisão explícita da usuária;
- manter `jurandir.mp3` apenas como amostra original aprovada fora do APK e fora do repositório público;
- NÃO publicar no GitHub o trecho de Cartman nem a gravação original da usuária;
- NÃO substituir a gravação aprovada por síntese nova sem nova aprovação;
- não inferir dessa regra uma remoção automática das amostras antigas de Chiu e Mia já aprovadas e fisicamente testadas; qualquer mudança nelas exige decisão específica.

Correção operacional executada:
- uma tentativa intermediária chegou a anexar `jurandir_voice_sample.mp3` ao diretório do app;
- assim que a instrução anterior da usuária foi recuperada, o arquivo foi removido novamente antes de qualquer wiring de UI;
- o estado autoritativo deve manter Jurandir FORA do APK;
- a linha principal deve ser mantida sem o commit intermediário que continha o binário, sempre que possível, para evitar exposição desnecessária da gravação no histórico normal.

Proteções permanentes:
- não alterar Chiu, Mia ou outras vozes já aprovadas como efeito colateral desta correção;
- não tocar em LearningEvidence, fila, FSRS, scheduler, mastery, Supabase ou CHIU PLAYER por causa desta frente;
- a voz de Jurandir está aprovada no nível artístico/amostra, mas **aprovação da voz não é autorização para empacotá-la no APK**;
- antes de qualquer futura integração de voz no APK, conferir novamente a decisão explícita da usuária.

Próximo passo seguro da frente de vozes:
1. preservar `jurandir.mp3` fora do APK e do GitHub público como referência aprovada;
2. seguir para a próxima voz ainda não aprovada usando apenas amostras externas ao APK;
3. pedir à usuária somente avaliação artística quando houver uma nova amostra para ouvir.

Este arquivo existe para impedir regressão de estado em novos chats: **Jurandir está aprovado como voz/amostra, porém não deve ser colocado no APK.**
