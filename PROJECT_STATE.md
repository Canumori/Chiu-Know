# CHIU KNOW? — PROJECT STATE

## ESTADO AUTORITATIVO — 2026-09-03 — CI #162 VERDE; DIREÇÃO DE VOZES NATURAIS REGISTRADA

Este bloco prevalece sobre trechos antigos conflitantes deste arquivo. O estado real do GitHub/Supabase continua prevalecendo sobre a documentação. Não recomeçar a frente.

### Vozes dos personagens — decisão da usuária
- Chiu mantém como identidade própria a voz-base já gravada/aprovada `Chiu-animada-recorte-final.m4a`; não reutilizar essa identidade para os demais personagens.
- Para Mia, Jurandir, Barto, Lara, Caca, Onça, Perry e Lena, antes de pedir novas gravações definitivas, apresentar futuramente **amostras curtas de sugestões de voz** para aprovação da usuária.
- Critério absoluto de qualidade: vozes devem soar **normais, naturais, humanas e expressivas**, comparáveis em naturalidade a vozes conversacionais modernas e à amostra aprovada do Chiu. Não aceitar TTS metálico, artificial ou claramente robótico como voz oficial.
- As propostas podem variar identidade vocal, gênero/sexo percebido, idade aparente, ritmo, timbre, energia e personalidade conforme o personagem, mas sem caricatura que prejudique naturalidade ou inteligibilidade pedagógica.
- Nenhuma voz sugerida se torna oficial sem aprovação explícita da usuária. Se nenhuma sugestão servir para um personagem, considerar gravação-base própria como foi feito com Chiu.
- Antes de incorporar qualquer provedor/tecnologia de síntese/clonagem ao APK, verificar privacidade, retenção de áudio, direitos/licença e uso comercial. Não enviar amostras pessoais/vozes aprovadas a serviço externo sem autorização específica após essa verificação.
- Esta decisão é direção futura; **não interromper a frente atual de placement para produzir vozes agora**.

### Placement — estado validado
- CI #160 passou para `57a614ae12b1ee5e56cd387b300e866481373346`: plano determinístico de confirmação.
- CI #161 passou para `ce92c76520efe0eb7cebc4f990cbf3a428766077`: documentação da prioridade de continuidade até 12/09.
- CI #162, run `33778682639`, passou para `a68b569002d10ef5ed8dcbead48fcc9fb124993c`: testes exaustivos protegem o plano de confirmação, incluindo A1/C2, fronteiras adjacentes, ausência de repetição, esgotamento seguro e contagens configuráveis.
- Inglês possui banco candidato de 24 questões, 4 por nível A1–C2. Outros idiomas permanecem nos starter banks até expansão/revisão equivalente.
- Próximo passo: construir regra conservadora e testável que use a evidência de confirmação para confirmar/revisar o nível provisório, sem porcentagem falsa de confiança nem alegação psicométrica não validada; depois integrar gradualmente ao fluxo real.

## ESTADO AUTORITATIVO — 2026-09-03 — PRIORIDADE DE CONTINUIDADE ATÉ 12/09; QUALIDADE NÃO NEGOCIÁVEL

Este bloco prevalece sobre trechos antigos conflitantes deste arquivo. O estado real do GitHub/Supabase continua prevalecendo sobre a documentação. Não recomeçar a frente.

### Decisão operacional da usuária — prazo de 12 de setembro
- A assinatura ChatGPT Plus da usuária termina em **12/09/2026** e depois ela pretende continuar no **ChatGPT Free**.
- A importância de 12/09 NÃO é lançar às pressas. É aproveitar até essa data as capacidades/ferramentas atualmente disponíveis no Plus para deixar o projeto o mais autônomo, documentado, testado e recuperável possível.
- **QUALIDADE NÃO PODE SER SACRIFICADA PARA CUMPRIR 12/09.** Se houver conflito entre qualidade/confiabilidade pedagógica e quantidade de funcionalidades, preservar qualidade e registrar claramente o que continuará depois.
- Objetivo de produto permanece ambicioso: APK confiável em que a pessoa realmente aprenda de forma avançada e completa, buscando superar limitações das soluções atuais por arquitetura própria baseada em evidência, sem copiar concorrentes e sem fazer alegações não validadas.
- Até 12/09, priorizar especialmente trabalho estrutural que possa ficar mais difícil com limites futuros: arquitetura principal, integração segura, testes automatizados, CI, contratos pedagógicos, placement robusto, persistência, documentação, decisões de segurança/privacidade, handoffs e um fluxo APK ponta a ponta tão completo quanto a qualidade permitir.
- Depois de 12/09, continuar o desenvolvimento **com o ChatGPT**, usando as capacidades que estiverem disponíveis no plano Free naquele momento. Não pressupor antecipadamente quais ferramentas/limites existirão no Free; verificar o que estiver realmente disponível e adaptar o processo.
- Preferência explícita da usuária: **não migrar o desenvolvimento para outra IA apenas por causa da mudança de plano**. Manter continuidade com ChatGPT dentro das capacidades disponíveis.
- Portanto cada chat deve deixar handoff forte no repositório. Ao trocar de chat, o próximo deve ler este arquivo e os documentos obrigatórios, conferir GitHub real e continuar do ponto registrado, sem pedir que a usuária reconstrua o histórico.

### Estado recente do placement
- CI #159 (`db200e6e4920e1575c9c9a95dd88e2e14aa298e0`) passou: testes protegem seleção de questões sem repetição.
- Inglês possui banco candidato expandido de 24 questões, 4 por nível A1–C2; demais idiomas permanecem nos bancos starter até expansão/revisão equivalente.
- Commit `57a614ae12b1ee5e56cd387b300e866481373346` adicionou plano determinístico de confirmação do placement; CI #160 passou.
- Política quality-first permanece: duração do teste é consequência da evidência necessária, não meta de marketing. Contrato provisório: mínimo 8 respostas, máximo de segurança 14, com fase de localização e confirmação; isso não equivale a validação psicométrica e não autoriza porcentagem falsa de confiança.
- Questões usadas numa tentativa não devem ser silenciosamente recicladas para simular nova evidência.

## ESTADO AUTORITATIVO — 2026-09-03 10:40 BRT — PESQUISA PEDAGÓGICA APROVADA; CI #146 VERDE

Este bloco prevalece sobre trechos antigos conflitantes deste arquivo. O estado real do GitHub/Supabase continua prevalecendo sobre a documentação. Não recomeçar a frente.

### GitHub/Android
- Repositório `Canumori/Chiu-Know`, branch `main`, deliberadamente público; não mudar visibilidade por suposição.
- Commit funcional/testes `f3668338884aca5b1dc46598cd34f70943049575` — `test: exhaustively verify adaptive placement paths`.
- Android CI #146, run `33761545918`: `COMPLETED / SUCCESS`.
- Documento pedagógico aprovado criado em `PEDAGOGY_ARCHITECTURE.md`, commit `79c3cbbf3affd85edd2166d9927a8fab871f2777`.

### Pesquisa comparativa aprovada pela usuária
Foram estudadas abordagens públicas de Duolingo, Babbel, Busuu, Memrise, Rosetta Stone, LingQ, Pimsleur, ELSA Speak e HelloTalk, além do CEFR/CEFR Companion Volume e evidências sobre spacing/retrieval practice. A decisão é combinar princípios úteis em arquitetura própria, sem copiar conteúdo, assets, marca, interface ou implementação.

Direção aprovada:
- hábito/gamificação servem engajamento, nunca mastery;
- conteúdo deve evoluir de compreensão/reconhecimento para recuperação com menos pistas, produção, transferência a novo contexto e revisão espaçada;
- preservar FSRS-6 próprio separado da evidência; erro = tentativa/exposição, não domínio;
- futuramente personalizar por objetivo, disponibilidade e prioridade sem remover competências CEFR essenciais;
- CEFR deve evoluir para evidência multidimensional honesta, sem inventar scores por habilidade;
- listening deve progredir de fala controlada para natural; não fingir listening sem áudio real;
- conversação deve começar guiada já em A1 e ganhar abertura/complexidade até C2;
- ASR/transcrição não equivale a pronúncia;
- gramática/vocabulário devem reaparecer em contextos e formatos diferentes;
- histórias com os personagens devem conectar input, compreensão, produção e recuperação posterior, preservando `VISUAL_BIBLE.md`;
- métricas prioritárias são aprendizagem/retenção/transferência, não XP/streak como mastery.

Modelo-alvo aprovado:
`PLACEMENT → DIAGNÓSTICO HONESTO → OBJETIVO/PREFERÊNCIAS → CONTEÚDO CEFR → INPUT COMPREENSÍVEL → PRÁTICA ATIVA → FEEDBACK → RETRIEVAL COM MENOS PISTAS → FSRS → TRANSFERÊNCIA → INTERAÇÃO/CONVERSAÇÃO → CHECKPOINT → REAJUSTE`.

### Voz/Storage — permanece congelado com segurança
`Chiu-animada-recorte-final.m4a` continua privada, fora do GitHub/Supabase/APK. Não usar `storage.objects` via SQL, service_role, bucket público, Edge Function privilegiada ou outro bypass. Quando houver operação normal de Storage, executar primeiro o teste descartável registrado no histórico e só depois pedir confirmação imediata para a voz aprovada.

## ESTADO AUTORITATIVO — 2026-09-03 10:16 BRT — STORAGE PRIVADO RECONFIRMADO; TESTE DE OBJETO BLOQUEADO PELA FERRAMENTA

### Supabase correto
- Projeto Chiu Know `uskxabsodcnzlovuaurp`; organização `aeerqbmrwulxsawhjyvm`; região `sa-east-1`.
- Supabase Chiu Player `hpcbkvbrlwjnwlikmbfb` é proibido e não deve ser tocado.
- Bucket `character-voices` privado, limite 2 MiB, allowlist `audio/mp4`, `audio/x-m4a`, `audio/m4a`, sem objeto aprovado enviado.
- Política de leitura somente para usuário autenticado/autorizado; sem upload/update/delete normal do APK.
- A conexão disponível não expunha operação normal de upload/download/delete de Storage; não improvisar bypass.

## NOTA DE PRESERVAÇÃO DO HISTÓRICO
O histórico operacional detalhado permanece no Git e nos commits anteriores. Em caso de auditoria, consultar versões anteriores do arquivo e commits registrados.

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