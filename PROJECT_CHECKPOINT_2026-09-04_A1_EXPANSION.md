# CHIU KNOW? — CHECKPOINT A1 ADAPTATIVO — 2026-09-04

Este checkpoint registra o estado real mais recente desta frente e complementa `PROJECT_STATE.md`. Em caso de conflito, o GitHub real continua prevalecendo. Não recomeçar esta frente.

## HEAD funcional validado

- HEAD funcional antes desta atualização documental: `6f87384d2974ae6d505f8265950ea495192b3e2e` — `feat: activate residence reading recognition`.
- Android CI #267, run `33882152019`: `COMPLETED / SUCCESS`.
- CI #267 confirmou: testes unitários verdes, build do debug APK verde e upload do artefato verde.

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

O alvo de residência possui dois contextos FILL_IN por idioma e um terceiro formato REORDER que compartilha o mesmo `reviewKey`. Assim, o mesmo conhecimento progride de recuperação com pista para reconstrução com menos pista sem criar um alvo artificial separado.

### Leitura
Agora existem pelo menos dois alvos independentes:
1. identificar nome explícito em uma apresentação curta;
2. identificar local de residência explícito em um texto muito curto.

O alvo de leitura de residência possui dois contextos FILL_IN por idioma e um terceiro contexto MULTIPLE_CHOICE que compartilha o mesmo `reviewKey`. A múltipla escolha é tratada explicitamente como prática de reconhecimento/variação contextual, NÃO como uma etapa com menos pistas ou como evidência de mastery. O avaliador continua determinístico; não há julgamento por IA.

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
- A fila deve continuar equilibrando alvos por `reviewKey` e girando contextos/formatos dentro do mesmo alvo.
- Não expor níveis superiores sem conteúdo real implementado.
- Não tocar no Supabase do CHIU PLAYER.
- Voz oficial do Chiu continua fora de GitHub/Supabase/APK até autorização específica.

## Próximas frentes seguras

1. Ampliar um terceiro alvo independente de gramática ou leitura somente em pequena fatia, mantendo CI verde entre criação e ativação.
2. Depois, avaliar a cobertura A1 por função comunicativa e competência para evitar crescer de forma desequilibrada.
3. Quando houver cobertura suficiente, iniciar uma pequena unidade narrativa funcional com personagens oficiais, preservando `VISUAL_BIBLE.md` e sem fabricar mastery.
4. Continuar aguardando resposta do voluntário para revisão humana do placement coreano; não bloquear outras frentes.
