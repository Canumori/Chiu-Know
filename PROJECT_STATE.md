# CHIU KNOW? — PROJECT STATE

## ESTADO AUTORITATIVO — 2026-09-03 21:54 BRT — REVISÃO HUMANA DO COREANO É GATE OBRIGATÓRIO

Este bloco prevalece sobre trechos antigos conflitantes deste arquivo. O estado real do GitHub/Supabase continua prevalecendo sobre a documentação. Não recomeçar a frente.

### Decisão explícita da usuária
- A usuária aprovou exigir **revisão humana qualificada** antes de habilitar o coreano em `QUALITY_SESSION`.
- Portanto o banco candidato coreano de 24 questões NÃO pode ser promovido à produção apenas por CI verde, revisão por IA ou comparação documental.
- Gate mínimo: revisão das 24 questões por pessoa com proficiência alta/nativa em coreano e competência suficiente para julgar naturalidade, correção, ambiguidade, adequação das alternativas e progressão relativa de dificuldade.
- Idealmente, quando possível, preferir professor(a) de coreano/L2 ou profissional com experiência em avaliação/ensino; não alegar validação psicométrica mesmo após essa revisão.
- A revisão humana deve verificar pelo menos: coreano natural e correto; uma resposta inequivocamente defensável quando o item exigir resposta única; distratores plausíveis sem serem enganosos; ausência de pistas artificiais; adequação cultural; progressão de dificuldade; e se itens altos realmente exigem nuance/inferência/registro/relação lógica, não apenas gramática ou vocabulário raro.
- Qualquer item reprovado deve ser corrigido/substituído e voltar aos testes automatizados antes do rollout.
- Até o gate humano ser satisfeito e documentado, `ko` permanece em `LEGACY_FOUNDATION` e `isQualityPlacementEnabled("ko") == false`.

### Referência externa preservada
- King Sejong Institute organiza o currículo em Beginner 1–2, Intermediate 1–2 e Advanced 1–2; não tratar isso como equivalência automática A1–C2.
- O próprio teste de nível público do King Sejong atualmente avalia Beginner/Intermediate em listening e reading e recomenda faixas 1A–4B; portanto não usá-lo como validação externa de um placement A1–C2 completo do CHIU KNOW?.
- Cursos avançados King Sejong 5/6 são referência de complexidade linguística e temática, não calibração psicométrica do nosso banco.

### Estado técnico preservado
- Banco candidato coreano: 24 questões, 4 por rótulo de engenharia A1–C2; CI #214 (`33821961869`) verde para mecânica/testes/build/APK.
- Inglês, português, espanhol e francês permanecem em `QUALITY_SESSION`.
- Coreano permanece deliberadamente bloqueado no legado até revisão humana.

### Próximo passo autônomo
1. Não procurar atalhos para eliminar o gate humano.
2. Preparar no projeto um protocolo/checklist de revisão do banco coreano que permita a um revisor humano avaliar cada item de forma consistente, sem exigir conhecimento de programação.
3. Preservar as 24 questões candidatas e a trava de produção.
4. Enquanto a revisão humana não estiver disponível/concluída, avançar em outras frentes estruturais de alta prioridade que não dependam dela, mantendo mudanças pequenas e CI verde.

## ESTADO AUTORITATIVO — 2026-09-03 21:31 BRT — FRANCÊS EM QUALITY_SESSION; COREANO CANDIDATO VERDE MAS BLOQUEADO PARA REVISÃO LINGUÍSTICA

Este bloco prevalece sobre trechos antigos conflitantes deste arquivo. O estado real do GitHub/Supabase continua prevalecendo sobre a documentação. Não recomeçar a frente.

### HEAD / CI validado
- HEAD funcional/testes: commit `68766894d8153e276a8204051850ed5148546054` — `test: exhaustively verify Korean candidate sessions`.
- Android CI #214, run `33821961869`: `COMPLETED / SUCCESS`; testes unitários, build debug APK e upload do artefato concluíram com sucesso.
- Rollout francês protegido por commit `bf690c7fc4d7ed13f7f2a90837dcb709486dd6f1` — `test: protect French quality placement rollout`.
- Android CI #211, run `33821741917`: `COMPLETED / SUCCESS`; testes unitários, build debug APK e upload do artefato concluíram com sucesso.
- Banco/sessões franceses antes do rollout: commit `745456c6d1a2a16dcad63721edbfeeb2a0f03c12`, Android CI #209, run `33821612606`: `COMPLETED / SUCCESS`.
- Semântica conservadora de evidência suplementar: commit `4c93bfd60b1e6e47fbca5c348e28c4b75ac90da1`, Android CI #206, run `33821351838`: `COMPLETED / SUCCESS`.

### Placement — estado real por idioma
- Inglês, português, espanhol e agora francês estão em `QUALITY_SESSION`.
- Cada um desses quatro idiomas possui 24 questões de engenharia, 4 por rótulo CEFR A1–C2, validação estrutural e exploração exaustiva de caminhos da sessão.
- Francês foi expandido em `FrenchPlacementExpansion.kt`, validado estruturalmente e por caminhos exaustivos antes de ser habilitado em `PlacementProductionGate.kt`.
- Coreano continua deliberadamente em `LEGACY_FOUNDATION` na produção.
- Foi criado `candidateKoreanPlacementQuestions` com 24 itens e testes estruturais/exaustivos; CI #214 verde comprova a mecânica do candidato, NÃO sua calibração linguística/CEFR.
- O teste da trava de produção comprova explicitamente que `ko` continua usando `starterKoreanPlacementQuestions` e `isQualityPlacementEnabled("ko") == false`.

### Referência externa usada para a decisão coreana
- A progressão oficial do King Sejong Institute utiliza seis níveis próprios: Beginner 1–2, Intermediate 1–2 e Advanced 1–2.
- Essa progressão foi usada apenas como referência de aumento de complexidade. NÃO registrar nem alegar equivalência automática entre esses seis níveis e CEFR A1–C2.
- Cursos avançados oficiais trabalham com temas abstratos e discursivos (educação, mudanças sociais, ciência, clima, IA, mercado, cultura, opinião pública, literatura) e integração de habilidades; portanto itens altos do CHIU KNOW? não devem se limitar a “gramática rara”.
- Antes de habilitar o coreano em `QUALITY_SESSION`, é necessária revisão linguística/calibração de dificuldade das 24 perguntas candidatas. CI verde sozinho não satisfaz esse requisito.

### Placement — endurecimentos já concluídos nesta frente
- IDs de perguntas são validados globalmente como não vazios e únicos antes do início da sessão.
- O teto máximo de evidência vale também durante `LOCATE`; nenhuma pergunta extra é apresentada após o teto.
- Fila obrigatória incompleta no teto termina inconclusiva sem nível decidido.
- Evidência suplementar de fronteira inferior não pode fabricar revisão para baixo apenas por ter sido coletada para completar o contrato de duração.
- Evidência superior suplementar não pode resgatar uma fronteira obrigatória superior que falhou; movimento para cima continua estrito.
- Evidência fresca no nível provisório continua podendo resolver ambiguidade 1–1 de forma conservadora.
- `MAX_EVIDENCE_INCONCLUSIVE` e `BANK_INSUFFICIENT` permanecem distintos na UI, sem salvar CEFR nem mostrar confidence falsa.

### Próximo ponto que exige decisão real de qualidade
- NÃO habilitar coreano em `QUALITY_SESSION` por automatismo.
- A próxima decisão de produto era qual padrão de revisão linguística seria exigido antes do rollout coreano. Essa decisão foi tomada no bloco autoritativo acima: **revisão humana qualificada é obrigatória**.

## NOTA DE PRESERVAÇÃO DO HISTÓRICO
Os blocos autoritativos anteriores e o histórico operacional detalhado permanecem no Git e nos commits anteriores. Em caso de auditoria, consultar versões anteriores do arquivo e commits registrados.

## REGRAS OPERACIONAIS PERMANENTES
- Ler `PROJECT_STATE.md` + `PRODUCT_SPEC.md` + `PEDAGOGY_ARCHITECTURE.md` antes de mudança pedagógica; arte exige `VISUAL_BIBLE.md`; pesquisa exige `RESEARCH.md`.
- GitHub/Supabase reais vencem documentação stale.
- Repo `Canumori/Chiu-Know`, branch `main`, público deliberadamente para Actions.
- Supabase exclusivo Chiu Know: `uskxabsodcnzlovuaurp`; Supabase Chiu Player é proibido.
- Usuária não programa: não mandar terminal, edição de código, conflitos ou ZIP.
- Mudanças pequenas, reversíveis, testadas; CI verde antes de próxima mudança estrutural.
- Não publicar/distribuir/OTA automaticamente.
- Placement local/determinístico/adaptativo; não voltar a porcentagem fixa nem inventar seis scores.
- Preservar trilha, DataStore, evidência, feedback congelado, fila e FSRS-6 próprio separado de mastery.
- Erro conta tentativa/exposição, não mastery; progresso visual/XP não é domínio.
- Ainda não existem mastery real, speaking/ASR/pronúncia válida, writing livre avançado, histórias completas, tutor IA, conteúdo A1–C2 completo ou scores multidimensionais válidos.
- Visual absoluto: Chiu realista branco/cabelo castanho SOMENTE logo/ícone; Chiu amarelo cartunesco SEMPRE no universo. Mosquito definitivo Jurandir. Não alterar masters aprovados.
- Voz aprovada privada `Chiu-animada-recorte-final.m4a`: não GitHub público, não outro projeto/personagem, não fornecedor externo sem autorização e verificação.
- Ao comando “Continue”, avançar autonomamente até decisão REAL da usuária; não parar em microcommit se CI/ferramentas permitirem.
- Ao fim de frente relevante, atualizar este arquivo com HEAD/CI/decisões/próximo passo.