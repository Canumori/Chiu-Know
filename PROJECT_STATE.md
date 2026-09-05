# CHIU KNOW? — PROJECT STATE

## ESTADO AUTORITATIVO — 2026-09-04 — COREANO REVISADO POR IA EM QUALITY_SESSION; CI #313 VERDE

**ESTE É O BLOCO AUTORITATIVO MAIS RECENTE E PREVALECE SOBRE TODOS OS BLOCOS ANTIGOS CONFLITANTES DESTE ARQUIVO, especialmente os que exigem revisão humana como gate, mantêm `ko` em `LEGACY_FOUNDATION` ou proíbem `QUALITY_SESSION`.** O histórico antigo permanece abaixo apenas para auditoria. O estado real do GitHub/Supabase continua sendo a fonte final da verdade.

### HEAD / CI de entrada confirmado
- HEAD antes deste checkpoint: `4ffb4a850eb76aee58f9339613a663b46f8b5761` — `docs: align Korean placement audit with reviewed rollout`.
- Android CI #313, run `33934986156`: `COMPLETED / SUCCESS`.

### Coreano — decisão vigente
- A decisão antiga de exigir revisão humana qualificada como gate obrigatório foi **SUPERADA** por decisão posterior da usuária.
- Como é difícil obter revisão humana gratuita, foi aprovado que a própria IA faça uma segunda revisão linguística rigorosa do coreano.
- Isso NÃO é revisão humana, validação psicométrica, certificação CEFR nem validação independente.
- Revisão humana externa continua desejável se futuramente disponível, mas **não é bloqueadora**.
- Processo aprovado/executado: naturalidade, gramática, registro, contexto, resposta única, qualidade dos distratores, alternativas semanticamente plausíveis, pistas mecânicas, distribuição das posições corretas, progressão relativa, neutralidade cultural, correções dos itens problemáticos, testes automatizados e CI verde.

### Placement coreano atual
- Banco combinado: `candidateKoreanPlacementQuestions`, com 24 questões, 4 por rótulo interno A1–C2, em `PlacementTest.kt` + `KoreanPlacementExpansion.kt`.
- A1–C2 são alvos internos de placement. Não alegar certificação/equivalência oficial CEFR, calibração psicométrica, validação independente ou equivalência automática com King Sejong.
- Resultado é estimativa pedagógica de nível.
- Correções de ambiguidade concluídas no commit `86d9edd8ce4de9a23b77bb1767f7da0efc3b7fc9` (`fix: remove ambiguity from Korean A1-A2 placement items`), CI #310 verde:
  - `ko-a1-001`: `저___ 학생입니다.`; opções `는 / 를 / 에 / 와`; correta índice 0.
  - `ko-a2-002`: `밖에 나가려는 친구에게 말합니다. "지금 비가 오니까 우산을 ___."`; opções `챙겼어요 / 챙길 거예요 / 챙기고 있어요 / 챙기세요`; correta índice 3.
- Integridade protegida em `KoreanPlacementCandidateBankTest.kt`, commit `f0309cc1b2907fda1571a527120de10d7389bd5d`, CI #309 verde: 24 itens; 4 por A1–C2; IDs/prompts únicos; quatro opções; índice válido; sem vazios/duplicatas internas; ID coerente com nível; starter+expansion; posições corretas 0–3 entre 4 e 8 ocorrências cada. Isso é guardrail de engenharia, não validação psicométrica.
- Auditoria: `KOREAN_PLACEMENT_AI_AUDIT_2026-09-04.md`, atualizada no commit `4ffb4a850eb76aee58f9339613a663b46f8b5761`; deve ser interpretada como segunda revisão rigorosa por IA, não revisão humana/psicometria/certificação.

### Rollout vigente
- Coreano habilitado em `QUALITY_SESSION` no commit `fc176c92cb769397fe283dc7bb85ecf6d381b5b9` — `feat: enable reviewed Korean quality placement`; Android CI #311, run `33930358594`: `SUCCESS`.
- Estado validado: EN/PT/ES/FR/KO = `QUALITY_SESSION`.
- `ko` usa exatamente `candidateKoreanPlacementQuestions` (24 itens). Não voltar a `LEGACY_FOUNDATION` por causa do histórico abaixo.

### Conteúdo/narrativa coreana já existente
- Formas A1 revisadas incluem `안녕하세요`, `저는 미아예요`, `저는 치우예요`, `이름이 뭐예요?`, `어디에 살아요?`, `리우에 살아요`, `무엇을 좋아해요?`, `커피를 좋아해요`, `저는 책이 있어요`, `감사합니다`, `고맙습니다`, `또 봐요`. Não reabrir sem motivo linguístico real.
- `A1FirstNarrativeMicroUnit.kt`: `ko-a1-narrative-coffee-001`, título `카페에서 처음 만나요`, com Mia/Chiu e conceitos greeting/name/residence/preference.
- `A1FirstNarrativeComprehensionActivities.kt`: leitura A1 específica da narrativa; pergunta sobre o que Chiu gosta, correta `커피`, distrator `리우`. Fora da starter queue normal e da evidência FSRS/mastery; sem speaking, áudio ou visual integrado.

### Regras pedagógicas e próximas ações
- Fluxo: contexto → reconhecimento → recuperação com pistas → menos pistas → sem pistas → novo contexto → revisão espaçada → retenção/transferência.
- Regras duras permanecem fora da discricionariedade da IA: nível/elegibilidade, FSRS, revisões vencidas, limites de conteúdo, evidência, não fabricar mastery e não pular fundamentos.
- Erro conta tentativa/exposição, não mastery. Preferências do aluno são planejamento, nunca evidência. Revisões vencidas têm prioridade.
- Placement adaptativo: mínimo 8, máximo 14, evidência inicial + confirmação de fronteira, resultado inconclusivo explícito quando necessário, não salvar nível se `null`, sem fabricar revisão para baixo, subida mais rigorosa, no máximo uma banda durante confirmação. Nunca voltar a porcentagem fixa simples nem fabricar seis scores multidimensionais.
- Depois do CI deste checkpoint, inspecionar o fluxo coreano real e escolher a próxima pequena fatia pedagógica de alto valor, provavelmente continuação A1 contextual/narrativa, mas sem executar cegamente.
- Toda nova fatia coreana deve receber revisão linguística rigorosa por IA, contexto natural, respostas inequívocas, testes e CI verde. Sem visual novo sem aprovação; sem áudio até autorização.

### Limites permanentes relevantes
- `FREE_TEXT` ainda tem avaliador determinístico/simples; não chamar resposta fechada de escrita livre. Speaking/ASR/pronúncia real ainda não existe e nunca deve ser alegado como avaliado/dominado.
- Voz oficial privada: `Chiu-animada-recorte-final.m4a` (~15,4 s / 309 KB). Não GitHub público, não fornecedor externo, não Supabase/APK sem autorização específica.
- Visual: ler `VISUAL_BIBLE.md`; Chiu realista branco/cabelo castanho SOMENTE logo/ícone; Chiu amarelo/cartunesco SEMPRE interno. Jurandir é o mosquito definitivo. Masters aprovados não são redesenhados; nova pose exige aprovação visual prévia.
- Supabase CHIU KNOW?: project `uskxabsodcnzlovuaurp`, org `aeerqbmrwulxsawhjyvm`, `sa-east-1`. CHIU PLAYER project `hpcbkvbrlwjnwlikmbfb`, org `nnrwosbnvdvzaoflwxlo`: PROIBIDO tocar/misturar. Nunca `service_role` no APK. Deep link `chiuknow://auth-callback` testado.
- Usuária não programa: trabalhar autonomamente; não mandar terminal, edição de código, conflitos, ZIP ou decisões técnicas desnecessárias.
- Antes de writes: confirmar HEAD/CI, refetch/usar SHA atual; mudanças pequenas, reversíveis e testáveis; esperar CI verde antes de avançar.

# CHIU KNOW? — PROJECT STATE

## ESTADO AUTORITATIVO — 2026-09-04 — TEXTO DO PLACEMENT CORRIGIDO; PROTOCOLO HUMANO COREANO PRONTO; CI #238 VERDE

Este bloco prevalece sobre trechos antigos conflitantes deste arquivo. O estado real do GitHub/Supabase continua prevalecendo sobre a documentação. Não recomeçar estas frentes.

### HEAD / CI validado
- Limpeza da linguagem antiga de “protótipo” no placement concluída em EN/PT/ES/FR/KO, preservando linguagem epistemicamente honesta.
- Commits da limpeza de UI: EN `30ee2e2f4e8518cf2d51bb951011e847d3ec6ca1`, PT `e5c214d74208f0a6f345bba883b1c907b99a3109`, ES `9cb1e89ab30728d33a095451d20105a7a752a9f0`, FR `10a82142358ee770f30f2d79c2bc7b22f4895a2c`, KO `26ebdfda35082f38c3a3ea4635ceef74bfad39ea`.
- Android CI #237, run `33835829402`: `COMPLETED / SUCCESS`; testes unitários, build debug APK e upload do artefato concluídos com sucesso para o HEAD da limpeza de strings.
- Criado `KOREAN_PLACEMENT_REVIEW.md` no commit `14aeb3b60850104e30a4276191ef11a8728ad6cd` — `docs: add Korean placement human review protocol`.
- Android CI #238, run `33835985430`: `COMPLETED / SUCCESS`; testes unitários, build debug APK e upload do artefato concluídos com sucesso.

### Placement — linguagem de UI agora
- A interface não chama mais o teste/resultado de “primeiro protótipo” ou “pontuação de protótipo”.
- O texto informa que o teste fornece uma **estimativa de nível CEFR** com o banco atual específico do idioma.
- O texto afirma explicitamente que o resultado NÃO é certificado oficial e NÃO é avaliação/score CEFR psicometricamente validado ou validado de forma independente.
- Contagem de acertos continua podendo ser mostrada como dado bruto da sessão, sem convertê-la em falsa precisão de proficiência.
- As chaves internas `placement_prototype_note` e `prototype_score_note` continuam com nomes antigos apenas para evitar churn técnico; os valores visíveis estão corrigidos.

### Coreano — protocolo humano agora existe
- `KOREAN_PLACEMENT_REVIEW.md` contém as 24 questões candidatas completas, organizadas por rótulo interno A1–C2.
- Para cada item o documento mostra ID, enunciado, alternativas, resposta atualmente indicada e campos de decisão humana `APROVAR / CORRIGIR / SUBSTITUIR`.
- Checklist obrigatório cobre naturalidade/correção, resposta única defensável, resposta indicada, qualidade dos distratores, pistas artificiais, adequação cultural/registro, dificuldade relativa e demanda linguística/cognitiva nos níveis altos.
- Há também revisão global do banco e identificação/qualificação do revisor.
- O próprio documento declara **PENDENTE DE REVISÃO HUMANA QUALIFICADA** e deixa todos os campos de aprovação em branco.
- IA, CI verde, tradução automática ou este documento NÃO satisfazem o gate humano.
- `ko` permanece `LEGACY_FOUNDATION`; NÃO habilitar `QUALITY_SESSION` até a revisão humana das 24 questões, correções exigidas e novo CI verde.

### Dependência humana real desta frente
- Para avançar especificamente o rollout do placement coreano, agora é necessário um revisor humano qualificado conforme o protocolo.
- Perfil mínimo: proficiência alta/nativa em coreano e competência para julgar naturalidade, ambiguidade, alternativas, registro, adequação cultural e progressão relativa; idealmente professor(a) de coreano/L2 ou profissional de avaliação/ensino.
- A revisão humana não deve ser apresentada como validação psicométrica.
- Enquanto o revisor não estiver disponível, outras frentes independentes podem continuar, mas não existe ação técnica legítima que substitua este gate.

### Regras que permanecem inalteradas
- EN/PT/ES/FR continuam em `QUALITY_SESSION`; coreano continua bloqueado no legado.
- Preferências do aprendiz continuam sendo planejamento, nunca CEFR/mastery/evidência.
- Revisões vencidas continuam prioritárias sobre personalização.
- Voz oficial do Chiu continua privada e fora de GitHub/Supabase/APK até autorização específica.

## ESTADO AUTORITATIVO — 2026-09-04 — PERFIL DE APRENDIZ INTEGRADO E CI #231 VERDE

Este bloco prevalece sobre trechos antigos conflitantes deste arquivo. O estado real do GitHub/Supabase continua prevalecendo sobre a documentação. Não recomeçar a frente.

### HEAD / CI validado
- Integração funcional do perfil de aprendiz: commit `e6a9cef15b7bf2c97540421f62828219a112a0d9` — `feat: integrate learner preferences into learning flow`.
- Android CI #231, run `33835575532`: `COMPLETED / SUCCESS`; testes unitários, build debug APK e upload do artefato concluíram com sucesso.
- Tela isolada criada em `LearnerPreferencesScreen.kt`: commit `4726569a5fd6cb1b2cead7165b79687293486158`.
- Strings base EN: commit `b339aff25f9d43efc177345d89ae73d1f130937b`.
- Localização PT: commit `a9f57fcff767b70d935dbdb129db9d7295a562dd`, CI #227 verde.
- Localizações ES/FR/KO: commits `32efb5b8981174cdc57b7917b0ce7349a5e81379`, `829f01beaef2f523e63050a80fcfcc6b65fdb562`, `0ea4b1577e5773bf95a6ad0fc30536ac6b02c8f8`; CI #230 verde.

### Perfil de aprendiz — estado real
- Modelo canônico permanece `LearnerPreferences(goal, priority, dailyMinutes)` em `LearnerProfile.kt`.
- Objetivos: GENERAL, CONVERSATION, TRAVEL, WORK, STUDY_OR_EXAM, LIVING_ABROAD, CULTURE_AND_MEDIA.
- Prioridades: BALANCED, LISTENING, SPEAKING, READING, WRITING.
- Minutos diários válidos: 5–180; padrão 15.
- Persistência local usa codec versionado `v1|GOAL|PRIORITY|minutes` e falha fechada para formato inválido/obsoleto/fora do intervalo.
- O DataStore agora salva preferências por idioma-alvo na chave `learner_preferences_<languageCode>`.
- Dados inválidos restauram `null`, sem fabricar perfil.
- Fluxo novo: após resultado válido do placement, se ainda não houver preferências daquele idioma, abrir `LEARNER_PREFERENCES`; depois seguir para `LEARNING_TRAIL`.
- Usuário com nível já salvo entra na tela de preferências apenas se aquele idioma ainda não tiver perfil salvo; perfil existente evita loop de onboarding.
- Preferências são mantidas separadamente por idioma-alvo.

### Limites pedagógicos protegidos
- Preferências são apenas insumos de planejamento; NÃO alteram CEFR, placement, mastery, evidência, confidence ou desbloqueio.
- Alterar preferência NÃO apaga evidência, agenda FSRS, placement ou nível estimado.
- A fila usa preferências somente para reordenar alvos NOVOS ainda não agendados.
- Revisão explicitamente vencida continua com prioridade absoluta sobre qualquer preferência.
- Todas as competências continuam presentes; prioridade não remove competência essencial.
- `dailyMinutes` é planejamento de carga, nunca evidência de proficiência ou domínio.

### Interface/localização
- A tela de preferências possui recursos localizados em inglês, português, espanhol, francês e coreano.
- A disponibilidade da interface coreana NÃO altera o gate do placement coreano: `ko` permanece `LEGACY_FOUNDATION` até revisão humana qualificada.

### Próximos passos seguros
1. Não reabrir a arquitetura já integrada sem motivo real.
2. Revisar separadamente textos antigos de UI que ainda chamam placement/score de “protótipo”, mantendo linguagem honesta: nível estimado, não certificado, sem alegar calibração/validação psicométrica inexistente.
3. Continuar fortalecendo pequenas fatias ponta a ponta do conteúdo e da personalização sem fabricar mastery ou scores multidimensionais.
4. Coreano continua bloqueado para `QUALITY_SESSION` até documentação de revisão humana qualificada das 24 questões candidatas.
5. Voz oficial do Chiu continua privada e fora de GitHub/Supabase/APK até autorização específica para a etapa de voz.

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
- Ao comando “Continue”, avançar autonomously até decisão REAL da usuária; não parar em microcommit se CI/ferramentas permitirem.
- Ao fim de frente relevante, atualizar este arquivo com HEAD/CI/decisões/próximo passo.