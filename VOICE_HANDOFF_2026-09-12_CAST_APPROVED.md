# CHIU KNOW? — APPROVED EXTERNAL CHARACTER VOICES

## AUTORITATIVO — 2026-09-12

Base verificada antes deste registro:
- `main`: `905dcc5ad5f9bd734ccee41faa34302290933566` — `docs: keep approved Jurandir voice outside APK`;
- Android CI #487 / run `34719862447`: `COMPLETED / SUCCESS` no mesmo SHA.

## REGRA ABSOLUTA DE INTEGRAÇÃO

A usuária determinou explicitamente: **“Não quero que você coloque nenhuma voz no APK.”**

Portanto, as aprovações abaixo são apenas de identidade artística e amostras externas. NÃO adicionar estes áudios, clones, conversões ou derivados ao APK, `res/raw`, assets do app ou repositório público sem nova autorização explícita. Não publicar gravações privadas da usuária. Não alterar vozes já aprovadas como efeito colateral.

## ONÇA — APROVADA

A usuária aprovou a versão final com **“Ta bom”**.

Direção aprovada:
- feminina adulta e inequivocamente feminina;
- registro mais profundo, íntimo, sensual e levemente rouco;
- presença felina, segura, com clima de rádio noturna;
- alongamento vem de atuação/cadência, NÃO de slow motion global;
- nunca masculinizar.

Amostra aprovada externa:
- modelo: `speech/elevenlabs-v3`;
- voz: Carla — Brazilian Portuguese female (`m151rjrbWXbBqyq56tly`);
- URL externa: `https://cdn.creativeclaw.co/u/144d4341/audio/be3946f1-ae75-426d-89e3-85b4058fc393.mp3`;
- texto: `[low, intimate] Eu sou a Onça... [breathes softly] não tenho pressa. [softly] Gosto de chegar devagar... observar... sentir. [whispers] E quando eu decido me aproximar... você já percebeu.`

Rejeições importantes que NÃO devem voltar:
- voz masculinizada;
- fala em slow motion;
- Altair/xAI masculino;
- tentativas MiniMax anteriores apenas mais graves/lentas.

## PERRY — APROVADO

A usuária ouviu a primeira amostra desta rodada e respondeu **“Está ótimo”**.

Direção aprovada:
- masculino jovem;
- heroico, energético, luminoso e carismático;
- intensidade de herói de anime sem imitar personagem/dublador específico;
- não infantil e não excessivamente gritante.

Amostra aprovada externa:
- modelo: `speech/elevenlabs-v3`;
- URL externa: `https://cdn.creativeclaw.co/u/144d4341/audio/665d5c38-dc06-4abb-adb5-89658a8cb9a8.mp3`;
- texto: `[excited] Eu sou o Perry! [confident] Quando aparece um desafio, eu não recuo. Eu penso rápido, sigo em frente e dou tudo de mim. [playful] E se parecer impossível... melhor ainda.`

A aprovação desta amostra fixa a identidade artística. Não substituir por uma interpretação infantil ou por imitação exata de voz pública.

## LARA — APROVADA

A primeira tentativa soou parecida demais com as outras mulheres. A tentativa seguinte ficou caricata/artificial demais. A usuária aprovou a versão intermediária final com **“Está bom”**.

Direção aprovada:
- feminina, mais aguda e rápida que as demais;
- falante, intrometida, espirituosa e levemente caótica;
- distinta das outras personagens femininas;
- ainda humana e natural, sem voz artificial de desenho.

Amostra aprovada externa:
- modelo: `speech/minimax-hd`;
- voz-base: `English_Upbeat_Woman`;
- URL externa: `https://cdn.creativeclaw.co/u/144d4341/audio/52e9ebd9-fce5-49cf-8482-86c0b5f86bd9.mp3`;
- texto: `Eu sou a Lara! <#0.10#> Eu vejo tudo, escuto tudo e, claro, tenho opinião sobre tudo. <#0.12#> Ninguém perguntou? Ótimo. Eu respondo mesmo assim!`

Não voltar à versão `English_radiant_girl`, que foi considerada estranha/caricata demais.

## LENA — APROVADA

As tentativas com vozes adultas apenas mais graves foram rejeitadas porque não soavam realmente idosas. A usuária pediu explicitamente **“uma voz de senhora de idade”**. A solução aprovada foi trocar para uma voz-base realmente sênior brasileira. A usuária respondeu **“Ótimo”**.

Direção aprovada:
- mulher idosa / senhora de idade, claramente mais velha que as demais;
- brasileira, sóbria, lúcida, experiente e segura;
- textura vocal de idade real, não apenas pitch/timbre engrossado;
- feminina; não caricata, frágil ou em slow motion;
- presença calma e firme.

Amostra aprovada externa:
- modelo: `speech/elevenlabs-v3`;
- voz pública sênior brasileira usada na geração: ID `FrCDCQwye0euHmliGxP9`;
- URL externa: `https://cdn.creativeclaw.co/u/144d4341/audio/765c9458-5366-45a4-b510-791e5882e899.mp3`;
- texto: `Eu sou a Lena. Aprendi a escutar antes de responder. Com o tempo, a gente descobre que nem toda palavra precisa ser dita... mas, quando é preciso falar, eu falo com clareza.`

Não voltar às tentativas `Portuguese_SereneWoman`, `Portuguese_Narrator` ou `Wise_Woman` apenas com ajustes de grave/pitch: elas foram rejeitadas por não transmitirem idade suficiente.

## ESTADO DA FRENTE DE VOZES APÓS ESTA RODADA

Aprovadas e preservadas externamente:
- Chiu;
- Mia;
- Jurandir;
- Cacá;
- Barto;
- Onça;
- Perry;
- Lara;
- Lena.

Todas as aprovações de voz são **aprovações artísticas**, não autorização de empacotamento no APK.

Próximo passo seguro:
1. manter todos os áudios aprovados fora do APK e do GitHub público;
2. não regenerar personagens aprovados sem solicitação explícita;
3. continuar o desenvolvimento por outra frente apenas depois de verificar novamente o HEAD real e o CI verde;
4. se a frente de voz for retomada, usar este arquivo e `VOICE_HANDOFF_2026-09-12_JURANDIR_APPROVED.md` como referências autoritativas recentes.
