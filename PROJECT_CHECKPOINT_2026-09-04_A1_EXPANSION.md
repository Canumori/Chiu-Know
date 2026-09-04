# CHIU KNOW? — CHECKPOINT A1 ADAPTATIVO — 2026-09-04

Este checkpoint registra o estado real mais recente desta frente e complementa `PROJECT_STATE.md`. Em caso de conflito, o GitHub real continua prevalecendo. Não recomeçar esta frente.

## HEAD funcional validado

- HEAD funcional antes deste documento: `60f8054eda9351724fe17841b879d8992728ffc7` — `feat: include residence reading target in starter bank`.
- Android CI #261, run `33880739956`: `COMPLETED / SUCCESS`.
- CI #261 confirmou: testes unitários verdes, build do debug APK verde e upload do artefato verde.

## CIs desta sequência

- CI #258, run `33880047922`: SUCCESS para `ff27b0cba2145cd26334ff7420a8d981e8b4b8e0` — novo segundo alvo gramatical A1 criado isoladamente.
- CI #259, run `33880279702`: SUCCESS para `ad5e9042c3b707cb117ddc7accce0a12cbc77e43` — alvo gramatical de residência integrado ao banco starter.
- CI #260, run `33880493707`: SUCCESS para `f2af7f497da158efd5682639cec7e9ae90817ebd` — novo segundo alvo de leitura A1 criado isoladamente.
- CI #261, run `33880739956`: SUCCESS para `60f8054eda9351724fe17841b879d8992728ffc7` — alvo de leitura de residência integrado ao banco starter.

## Estado pedagógico do banco starter A1

A expansão continua pequena, determinística e baseada em `reviewKey`, sem fabricar mastery, confidence ou proficiência.

### Vocabulário
Há múltiplos alvos independentes, incluindo:
- saudação básica;
- agradecimento;
- despedida.

Cada alvo recente foi implementado em mais de um contexto para favorecer recuperação e transferência, não memorização de uma única frase.

### Gramática
Agora existem pelo menos dois alvos independentes:
1. cópula/apresentação em primeira pessoa;
2. frase básica de residência em primeira pessoa (`I live`, `eu moro`, `yo vivo`, `j’habite`, `저는 ... 살아요`).

O novo alvo de residência possui dois contextos por idioma e `reviewKey` próprio.

### Leitura
Agora existem pelo menos dois alvos independentes:
1. identificar nome explícito em uma apresentação curta;
2. identificar local de residência explícito em um texto muito curto.

O novo alvo de residência possui dois contextos por idioma e `reviewKey` próprio. O avaliador continua determinístico; não há julgamento por IA.

## Idiomas

A fundação starter mantém conteúdo em EN/PT/ES/FR/KO. Isso NÃO altera o gate do placement coreano.

- EN/PT/ES/FR continuam `QUALITY_SESSION` no placement.
- Coreano continua `LEGACY_FOUNDATION` no placement.
- O placement coreano continua proibido de promoção até revisão humana qualificada documentada das 24 questões candidatas.
- Conteúdo A1 coreano existente/novo não deve ser apresentado como validação humana do banco de placement nem como validação psicométrica.

## Regras preservadas

- Revisões FSRS vencidas continuam prioritárias sobre personalização.
- Preferências declaradas do aluno são planejamento, não evidência.
- Erros recentes observados podem orientar a escolha entre NOVOS alvos quando houver alternativas reais, mas não podem passar na frente de revisão vencida.
- Um acerto isolado nunca equivale a domínio.
- A fila deve continuar equilibrando alvos por `reviewKey` e girando contextos dentro do mesmo alvo.
- Não expor níveis superiores sem conteúdo real implementado.
- Não tocar no Supabase do CHIU PLAYER.
- Voz oficial do Chiu continua fora de GitHub/Supabase/APK até autorização específica.

## Próximas frentes seguras

1. Ampliar mais um alvo independente de gramática e/ou leitura somente em pequenas fatias, mantendo CI verde entre mudanças estruturais.
2. Começar a conectar os mesmos alvos entre formatos (por exemplo, leitura -> recuperação -> REORDER) sem criar falso mastery.
3. Avaliar quando o A1 tiver cobertura suficiente para iniciar uma pequena unidade narrativa funcional com personagens oficiais, preservando `VISUAL_BIBLE.md`.
4. Continuar aguardando resposta do voluntário para revisão humana do placement coreano; não bloquear outras frentes.
