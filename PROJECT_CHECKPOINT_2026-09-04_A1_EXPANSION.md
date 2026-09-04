# CHIU KNOW? — CHECKPOINT A1 ADAPTATIVO — 2026-09-04

Este checkpoint registra o estado real mais recente desta frente e complementa `PROJECT_STATE.md`. Em caso de conflito, o GitHub real continua prevalecendo. Não recomeçar esta frente.

## HEAD funcional validado

- HEAD funcional antes desta atualização documental: `181113c45ec1b58ab15c97b66be4209c7e3e49cd` — `feat: activate first A1 text microinteraction`.
- Android CI #281, run `33910429099`: `COMPLETED / SUCCESS`.
- CI #281 confirmou testes unitários, build do debug APK e upload do artefato verdes.

## CIs desta sequência

- CI #258, run `33880047922`: SUCCESS — segundo alvo gramatical A1 criado isoladamente.
- CI #259, run `33880279702`: SUCCESS — residência gramatical integrada.
- CI #260, run `33880493707`: SUCCESS — segundo alvo de leitura criado isoladamente.
- CI #261, run `33880739956`: SUCCESS — leitura de residência integrada.
- CI #262, run `33881040200`: SUCCESS — checkpoint registrado.
- CI #263, run `33881261574`: SUCCESS — REORDER de residência criado isoladamente.
- CI #264, run `33881493837`: SUCCESS — REORDER de residência ativado.
- CI #265, run `33881738844`: SUCCESS — checkpoint sincronizado.
- CI #266, run `33881958140`: SUCCESS — MULTIPLE_CHOICE de leitura de residência criado isoladamente.
- CI #267, run `33882152019`: SUCCESS — reconhecimento de residência ativado.
- CI #268, run `33882387929`: SUCCESS — checkpoint sincronizado.
- CI #269, run `33889009829`: SUCCESS — posse básica criada isoladamente.
- CI #270, run `33889231623`: SUCCESS — posse básica ativada.
- CI #271, run `33889468438`: SUCCESS — checkpoint sincronizado.
- CI #272, run `33889680073`: SUCCESS — leitura de preferência criada isoladamente.
- CI #273, run `33889902199`: SUCCESS — leitura de preferência ativada.
- CI #274, run `33890131864`: SUCCESS — cobertura A1 3×3 registrada.
- CI #275, run `33890364263`: SUCCESS — pergunta de nome criada isoladamente.
- CI #276, run `33890579764`: SUCCESS — pergunta de nome ativada.
- CI #277: SUCCESS — checkpoint posterior da frente A1.
- CI #278: SUCCESS — pergunta de residência criada isoladamente.
- CI #279: SUCCESS — pergunta de residência ativada.
- CI #280, run `33891460910`: SUCCESS para `8882ced5a223a9a10dccf1b1725bbd567f763bc1` — primeira microinteração textual criada isoladamente.
- CI #281, run `33910429099`: SUCCESS para `181113c45ec1b58ab15c97b66be4209c7e3e49cd` — primeira microinteração textual ativada no banco starter.

## Estado pedagógico do banco starter A1

A expansão continua pequena, determinística e baseada em `reviewKey`, sem fabricar mastery, confidence ou proficiência.

### Fundação balanceada 3×3

**Vocabulário**
1. saudação básica;
2. agradecimento;
3. despedida.

**Gramática declarativa**
1. cópula/apresentação em primeira pessoa;
2. residência em primeira pessoa;
3. posse básica em primeira pessoa.

**Leitura**
1. identificar nome explícito em apresentação curta;
2. identificar local de residência explícito;
3. identificar preferência explícita.

### Perguntas A1 controladas

Existem agora dois alvos interrogativos independentes, ambos como `REORDER` determinístico:

1. perguntar o nome de alguém;
2. perguntar onde alguém mora.

Exemplos de residência:
- EN: `Where do you live?`
- PT: `Onde você mora?`
- ES: `¿Dónde vives?`
- FR: `Où est-ce que tu habites ?`
- KO: `어디에 살아요?`

Essas atividades treinam formulação textual controlada. NÃO são SPEAKING, pronúncia, diálogo livre nem prova de competência conversacional.

### Primeira microinteração textual ativa

O banco starter agora contém uma primeira troca curta entre personagens oficiais: Mia pergunta onde Chiu mora e o aluno reconhece a resposta contextualmente adequada.

- Tipo: `MULTIPLE_CHOICE`.
- Skill registrada: `READING`.
- `reviewKey` próprio de interação por idioma.
- Objetivo: reconhecer relação pergunta → resposta adequada em contexto.
- Não é tratada como speaking, produção livre, avaliação de conversação ou mastery interativo.
- A atividade reutiliza apenas os nomes/personagens no texto; nenhum novo asset visual foi incorporado ao APK.

### Transferência entre formatos

- Residência gramatical: dois FILL_IN + um REORDER no mesmo `reviewKey`.
- Leitura de residência: dois FILL_IN + um MULTIPLE_CHOICE no mesmo `reviewKey`.
- MULTIPLE_CHOICE é reconhecimento/variação, não evidência automática de dificuldade maior ou mastery.
- Posse: dois FILL_IN por idioma no mesmo alvo.
- Preferência: dois FILL_IN por idioma no mesmo alvo.

## Idiomas e coreano

A fundação starter mantém EN/PT/ES/FR/KO. Isso NÃO promove o placement coreano.

- EN/PT/ES/FR continuam `QUALITY_SESSION` no placement.
- Coreano continua `LEGACY_FOUNDATION` no placement.
- A política mais recente do projeto torna revisão humana coreana fortemente preferível, mas não um bloqueio eterno: se não houver voluntário, somente a rota de segunda revisão AI estrita e documentada prevista em `KOREAN_PLACEMENT_POLICY.md` pode avançar a análise.
- Em qualquer rota, não alegar revisão humana, certificação CEFR, equivalência King Sejong ou validação psicométrica inexistentes.
- Conteúdo A1 coreano não valida automaticamente o banco de placement.

## Limites honestos

- LISTENING real ainda depende de áudio real validado; não fabricar listening por texto.
- SPEAKING/pronúncia continuam bloqueados até existir captura/análise honesta e critérios válidos.
- WRITING livre ainda não deve ser tratado como competência avaliada sem tarefas e avaliação adequadas.
- O A1 ainda NÃO é currículo completo.
- A microinteração atual é reconhecimento textual; produção de resposta ainda é uma lacuna.
- Funções ainda pouco cobertas: necessidades/pedidos, números/horas, família/pessoas, comida/compra e produção própria de preferências.

## Regras preservadas

- Revisões FSRS vencidas continuam prioritárias sobre personalização.
- Preferências declaradas são planejamento, não evidência.
- Erros recentes repetidos podem orientar apenas a escolha entre NOVOS alvos; nunca passam na frente de revisão vencida.
- Um acerto isolado nunca equivale a domínio.
- A fila continua equilibrando por `reviewKey` e girando contextos/formatos dentro do alvo.
- Não expor níveis superiores sem conteúdo real.
- Não tocar no Supabase do CHIU PLAYER.
- Voz oficial do Chiu permanece fora de GitHub/Supabase/APK até autorização específica.
- Antes de trabalho visual, ler `VISUAL_BIBLE.md`; Chiu branco realista é só logo/ícone e Chiu amarelo cartunesco é o personagem interno.

## Próxima frente recomendada

1. Não saltar diretamente para uma grande história visual: os binários canônicos ainda não estão no repo e novas poses exigem amostra/aprovação conforme `VISUAL_BIBLE.md`.
2. Primeiro fortalecer a microinteração com uma etapa de recuperação/produção textual controlada, reduzindo pistas após o reconhecimento.
3. Depois criar uma segunda microinteração sobre preferência, conectando conteúdo já existente em vez de adicionar vocabulário aleatório.
4. Só então estruturar uma primeira micro-unidade narrativa curta em texto/modelo, antes de qualquer integração de novos assets visuais.
5. Continuar aguardando eventual voluntário coreano sem bloquear as demais frentes; se não houver resposta, seguir a política de fallback AI documentado em `KOREAN_PLACEMENT_POLICY.md`.
