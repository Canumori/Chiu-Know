# CHIU KNOW? — CHECKPOINT A1 ADAPTATIVO — 2026-09-04

Este checkpoint registra o estado real mais recente desta frente e complementa `PROJECT_STATE.md`. Em caso de conflito, o GitHub real continua prevalecendo. Não recomeçar esta frente.

## HEAD funcional validado

- HEAD funcional antes desta atualização documental: `03487674dac0402d566ac4e771d813310339128e` — `feat: activate preference reading target`.
- Android CI #273, run `33889902199`: `COMPLETED / SUCCESS`.
- CI #273 confirmou: testes unitários verdes, build do debug APK verde e upload do artefato verde.

## CIs desta sequência

- CI #258, run `33880047922`: SUCCESS para `ff27b0cba2145cd26334ff7420a8d981e8b4b8e0` — novo segundo alvo gramatical A1 criado isoladamente.
- CI #259, run `33880279702`: SUCCESS para `ad5e9042c3b707cb117ddc7accce0a12cbc77e43` — alvo gramatical de residência integrado ao banco starter.
- CI #260, run `33880493707`: SUCCESS para `f2af7f497da158efd5682639cec7e9ae90817ebd` — novo segundo alvo de leitura A1 criado isoladamente.
- CI #261, run `33880739956`: SUCCESS para `60f8054eda9351724fe17841b879d8992728ffc7` — alvo de leitura de residência integrado ao banco starter.
- CI #262, run `33881040200`: SUCCESS para `08da1c89117e292c56255ca19b52c2c4b7e2ec39` — checkpoint da expansão A1 registrado.
- CI #263, run `33881261574`: SUCCESS para `883265bd10ed8112d77df52c7fb7a86d649f9e83` — formato REORDER do alvo gramatical de residência criado isoladamente.
- CI #264, run `33881493837`: SUCCESS para `2f0a34ee8bfd4ba78679c759a15784bb5cbd26ea` — formato REORDER de residência ativado no banco starter.
- CI #265, run `33881738844`: SUCCESS para `89041d12052902049ee9a25d450b3923587939f3` — checkpoint anterior sincronizado.
- CI #266, run `33881958140`: SUCCESS para `89e06e23ff8b32617d798280ad00420f70fba203` — formato MULTIPLE_CHOICE de leitura de residência criado isoladamente.
- CI #267, run `33882152019`: SUCCESS para `6f87384d2974ae6d505f8265950ea495192b3e2e` — reconhecimento de residência ativado no banco starter.
- CI #268, run `33882387929`: SUCCESS para `f880888fcadc960765899be1b412da9906108657` — checkpoint da transferência de leitura sincronizado.
- CI #269, run `33889009829`: SUCCESS para `e0b30adf730c279ea3b5bb444df03836c7faf12e` — terceiro alvo gramatical A1, posse básica, criado isoladamente.
- CI #270, run `33889231623`: SUCCESS para `c67a6efec33844f409b0aa08cd2a2c514449ef77` — alvo gramatical de posse ativado no banco starter.
- CI #271, run `33889468438`: SUCCESS para `6bbf64c9bca7cd6e2626f120add53f7ed8b91ade` — checkpoint do terceiro alvo gramatical sincronizado.
- CI #272, run `33889680073`: SUCCESS para `029b511890c5d35f80d0425e41f06476caf0483b` — terceiro alvo de leitura A1, preferência explícita, criado isoladamente.
- CI #273, run `33889902199`: SUCCESS para `03487674dac0402d566ac4e771d813310339128e` — leitura de preferência ativada no banco starter.

## Estado pedagógico do banco starter A1

A expansão continua pequena, determinística e baseada em `reviewKey`, sem fabricar mastery, confidence ou proficiência.

### Cobertura atual equilibrada

Há agora três alvos independentes em cada uma das competências atualmente válidas no starter sem depender de áudio/ASR:

**Vocabulário**
1. saudação básica;
2. agradecimento;
3. despedida.

**Gramática**
1. cópula/apresentação em primeira pessoa;
2. residência em primeira pessoa (`I live`, `eu moro`, `yo vivo`, `j’habite`, `저는 ... 살아요`);
3. posse básica em primeira pessoa (`I have`, `eu tenho`, `yo tengo`, `j’ai`; em coreano, construção natural com `있어요`).

**Leitura**
1. identificar nome explícito em uma apresentação curta;
2. identificar local de residência explícito em texto muito curto;
3. identificar preferência explícita em texto muito curto.

### Transferência entre formatos

- O alvo gramatical de residência possui dois contextos FILL_IN e um REORDER com o mesmo `reviewKey`.
- O alvo de leitura de residência possui dois contextos FILL_IN e um MULTIPLE_CHOICE com o mesmo `reviewKey`.
- MULTIPLE_CHOICE é tratado como reconhecimento/variação contextual, não como evidência de mastery ou como etapa automaticamente mais difícil.
- O alvo de posse possui dois contextos FILL_IN por idioma com um `reviewKey` próprio.
- O alvo de preferência possui dois contextos FILL_IN por idioma com um `reviewKey` próprio.

## Idiomas

A fundação starter mantém conteúdo em EN/PT/ES/FR/KO. Isso NÃO altera o gate do placement coreano.

- EN/PT/ES/FR continuam `QUALITY_SESSION` no placement.
- Coreano continua `LEGACY_FOUNDATION` no placement.
- O placement coreano continua proibido de promoção até revisão humana qualificada documentada das 24 questões candidatas.
- Conteúdo A1 coreano existente/novo não deve ser apresentado como validação humana do banco de placement nem como validação psicométrica.

## Limites e lacunas honestas

- LISTENING real ainda depende de áudio real validado; não fabricar listening por texto.
- SPEAKING/pronúncia continuam bloqueados até existir captura/análise honesta e critérios válidos.
- WRITING livre ainda não deve ser tratado como competência avaliada enquanto não houver tarefas e avaliação adequadas.
- O A1 ainda NÃO é um currículo completo; a cobertura atual é uma fundação balanceada.
- Funções comunicativas ainda pouco cobertas incluem perguntas básicas, necessidades/pedidos, números/horas, família/pessoas, comida/compra e preferências produzidas pelo próprio aluno.

## Regras preservadas

- Revisões FSRS vencidas continuam prioritárias sobre personalização.
- Preferências declaradas do aluno são planejamento, não evidência.
- Erros recentes observados podem orientar a escolha entre NOVOS alvos quando houver alternativas reais, mas não podem passar na frente de revisão vencida.
- Um acerto isolado nunca equivale a domínio.
- A fila deve continuar equilibrando alvos por `reviewKey` e girando contextos/formatos dentro do mesmo alvo.
- Não expor níveis superiores sem conteúdo real implementado.
- Não tocar no Supabase do CHIU PLAYER.
- Voz oficial do Chiu continua fora de GitHub/Supabase/APK até autorização específica.

## Próxima frente recomendada

1. Parar de expandir apenas por contagem: a base 3×3 de VOCABULARY/GRAMMAR/READING já está equilibrada.
2. Próximo alvo funcional recomendado: **perguntas básicas A1** (por exemplo, perguntar nome/local/preferência) porque isso começa a transformar conhecimento declarativo em interação.
3. Implementar primeiro como pequena fatia textual determinística; não chamar isso de SPEAKING nem conversação livre.
4. Depois avaliar uma primeira unidade narrativa funcional curta com personagens oficiais, preservando `VISUAL_BIBLE.md` e sem fabricar mastery.
5. Continuar aguardando resposta do voluntário para revisão humana do placement coreano; não bloquear outras frentes.
