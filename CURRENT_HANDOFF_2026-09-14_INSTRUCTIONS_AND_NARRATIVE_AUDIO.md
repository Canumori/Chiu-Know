# CHIU KNOW? — HANDOFF AUTORITATIVO — 2026-09-14 — ENUNCIADOS E ÁUDIO NARRATIVO

## Escopo absoluto

Este registro pertence SOMENTE ao repositório `Canumori/Chiu-Know`, branch `main`.
Não tocar em nenhum componente do ChiuPlayer.

## 1. Enunciados para iniciantes — decisão aprovada

Para atividades de aprendizagem, especialmente A1, o aluno não deve precisar compreender uma instrução no idioma-alvo para descobrir o que deve fazer.

Regra aprovada:
- a instrução operacional da atividade deve aparecer no idioma da interface/língua materna escolhida pelo usuário;
- o conteúdo que está sendo aprendido continua no idioma-alvo;
- não misturar compreensão do enunciado com a habilidade que a atividade pretende medir;
- exemplos: escolher resposta, completar lacuna, ordenar palavras e digitar resposta devem ser instruídos na língua da interface.

Implementação:
- `d7fd1675160791ef109d3bbf0b03f3a8bcbb3cd7` — adiciona instruções localizadas por tipo de resposta;
- o primeiro commit removeu acidentalmente uma chave estrutural e o Android CI #556 falhou;
- `b78e0848acdf1b6b6e5cbd1c9050bc0213a989c1` — restaura somente a estrutura necessária mantendo a instrução localizada;
- Android CI #557: SUCCESS, incluindo testes e build.

Não alterar FSRS, respostas corretas, placement ou conteúdo-alvo por causa dessa regra.

## 2. Falas das histórias — direção pedagógica aprovada

A usuária aprovou a ideia de as histórias poderem apresentar simultaneamente:
- fala escrita do personagem;
- áudio daquela mesma fala com a voz do próprio personagem;
- possibilidade de repetir a fala.

Finalidade:
- exposição combinada a reading + listening;
- ajudar iniciantes a associar grafia, ritmo, pronúncia e entonação.

Regra de honestidade pedagógica:
- ouvir enquanto lê NÃO constitui evidência suficiente de domínio de listening;
- não gerar evidência de listening ou mastery apenas por reproduzir áudio junto ao texto;
- avaliação real de listening precisa posteriormente de atividade em que o áudio forneça evidência independente do texto visível.

## 3. PILOTOS DE ÁUDIO DA ESTAÇÃO — REJEITADOS

Foram gerados quatro pilotos para a estação em inglês usando Chatterbox Multilingual para as falas:
- Mia: `Where is the restroom?`
- Chiu: `Over there!`
- Mia: `I don't understand.`
- Chiu: `The restroom is behind that door.`

A usuária rejeitou os resultados fisicamente/auditivamente porque:
- a pronúncia estava errada;
- o resultado soava robótico;
- as vozes ficaram praticamente iguais entre si;
- portanto NÃO preservaram as identidades vocais escolhidas para Mia e Chiu.

DECISÃO ABSOLUTA:
- esses quatro pilotos NÃO são vozes aprovadas de Mia ou Chiu;
- NÃO integrar esses áudios no APK;
- NÃO reutilizar suas URLs em narrativa futura;
- NÃO alegar que representam as vozes escolhidas;
- NÃO gerar lote de outras histórias/idiomas a partir desse resultado.

O mapeamento experimental `NarrativeVoiceAudio.kt`, criado em `3a46dc2de88ea69d96f6be56bb0532484b473aad`, foi removido em:
- `5bcb56f831b7aa87b837c45a2236a21d4be4d3f9` — `audio: remove rejected station voice pilots`;
- Android CI #559: SUCCESS.

## 4. Referências vocais que continuam válidas

As referências previamente aprovadas no projeto permanecem válidas e NÃO foram substituídas pelos pilotos rejeitados.

Para os dois personagens presentes na estação:
- Mia: `app/src/main/res/raw/mia_voice_sample_girl.mp3`;
- Chiu: `app/src/main/res/raw/chiu_voice_sample_expressive.wav`.

Qualquer geração futura precisa preservar timbres claramente distintos e a identidade aprovada de cada personagem.

Antes de produzir novas falas:
1. usar somente a referência correspondente ao personagem;
2. gerar UMA fala curta de teste;
3. verificar pronúncia natural no idioma-alvo;
4. verificar que o timbre permanece reconhecivelmente diferente dos demais personagens;
5. obter aprovação auditiva antes de gerar lote;
6. só então integrar áudio à narrativa.

Se uma ferramenta/motor não preservar a identidade vocal, abandonar aquele caminho em vez de tentar compensar com velocidade, ruído, equalização ou outros ajustes cosméticos.

## 5. Estado atual após a rejeição

- a ideia pedagógica de texto + voz permanece aprovada;
- nenhum dos pilotos rejeitados deve entrar no app;
- a narrativa continua funcional como leitura enquanto não houver áudio aprovado;
- não declarar listening implementado apenas porque a arquitetura futura foi discutida;
- não conectar reprodução de áudio à tela narrativa até existir amostra de voz aprovada para a fala correspondente;
- não modificar as amostras vocais aprovadas existentes sem autorização.

## 6. Próximo passo seguro para voz

Quando o trabalho de voz for retomado:
- testar uma única fala da Mia em inglês com a referência `mia_voice_sample_girl.mp3` usando um motor apropriado para inglês e alta naturalidade;
- comparar auditivamente com a referência aprovada;
- somente após aprovação, fazer uma única fala do Chiu com `chiu_voice_sample_expressive.wav`;
- confirmar que Mia e Chiu soam claramente diferentes;
- não gerar as quatro falas completas nem outros idiomas antes dessa aprovação.

Este arquivo deve ser lido junto com `PROJECT_STATE_CURRENT.md`, `CURRENT_HANDOFF.md` e os handoffs de voz existentes antes de qualquer trabalho futuro com narrativa ou vozes.
