# CHIU KNOW? — PROJECT STATE

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
- Commit `57a614ae12b1ee5e56cd387b300e866481373346` adicionou plano determinístico de confirmação do placement; CI #160 foi disparado e deve ser conferido no GitHub real antes de nova mudança estrutural.
- Política quality-first permanece: duração do teste é consequência da evidência necessária, não meta de marketing. Contrato provisório: mínimo 8 respostas, máximo de segurança 14, com fase de localização e confirmação; isso não equivale a validação psicométrica e não autoriza porcentagem falsa de confiança.
- Questões usadas numa tentativa não devem ser silenciosamente recicladas para simular nova evidência.

### Próximo passo exato
1. Conferir resultado real do CI #160.
2. Se falhar, inspecionar job/log e corrigir somente a causa.
3. Se verde, criar testes exaustivos do plano de confirmação, incluindo A1/C2, esgotamento de itens, ausência de repetição e limite máximo.
4. Só depois integrar gradualmente banco ampliado/confirmation flow ao placement real.
5. Manter CI verde entre mudanças estruturais e continuar autonomamente até surgir decisão real de produto/privacidade.

## ESTADO AUTORITATIVO — 2026-09-03 10:40 BRT — PESQUISA PEDAGÓGICA APROVADA; CI #146 VERDE

Este bloco prevalece sobre trechos antigos conflitantes deste arquivo. O estado real do GitHub/Supabase continua prevalecendo sobre a documentação. Não recomeçar a frente.

### GitHub/Android
- Repositório `Canumori/Chiu-Know`, branch `main`, deliberadamente público; não mudar visibilidade por suposição.
- Commit funcional/testes `f3668338884aca5b1dc46598cd34f70943049575` — `test: exhaustively verify adaptive placement paths`.
- Android CI #146, run `33761545918`: `COMPLETED / SUCCESS`.
- O novo teste percorre sistematicamente caminhos possíveis de acerto/erro do motor adaptativo, exige término seguro, invariantes válidos e alcançabilidade de A1–C2. Nenhuma lógica funcional de placement foi alterada nesse commit.
- Documento pedagógico aprovado criado em `PEDAGOGY_ARCHITECTURE.md`, commit `79c3cbbf3affd85edd2166d9927a8fab871f2777`.

### Pesquisa comparativa aprovada pela usuária
Foram estudadas abordagens públicas de Duolingo, Babbel, Busuu, Memrise, Rosetta Stone, LingQ, Pimsleur, ELSA Speak e HelloTalk, além do CEFR/CEFR Companion Volume e evidências sobre spacing/retrieval practice. A decisão é combinar princípios úteis em arquitetura própria, sem copiar conteúdo, assets, marca, interface ou implementação.

Direção aprovada:
- hábito/gamificação servem engajamento, nunca mastery;
- conteúdo deve evoluir de compreensão/reconhecimento para recuperação com menos pistas, produção, transferência a novo contexto e revisão espaçada;
- preservar FSRS-6 próprio separado da evidência; erro = tentativa/exposição, não domínio;
- futuramente personalizar por objetivo, disponibilidade e prioridade sem remover competências CEFR essenciais;
- CEFR deve evoluir para evidência multidimensional honesta (recepção, produção, interação, mediação e competências pertinentes), sem inventar scores por habilidade;
- listening deve progredir de fala controlada para natural, variações e material autêntico; não fingir listening sem áudio real;
- conversação deve começar guiada já em A1 e ganhar abertura/complexidade até C2;
- ASR/transcrição não equivale a pronúncia; futura avaliação deve separar inteligibilidade, sons/contrastes, ritmo/fluência, tonicidade/prosódia e entonação conforme idioma;
- gramática/vocabulário devem reaparecer em contextos e formatos diferentes, não como listas desconectadas;
- histórias com Chiu/Mia/Jurandir/demais personagens devem conectar input, vocabulário, gramática, compreensão, diálogo, produção e recuperação posterior, preservando `VISUAL_BIBLE.md`;
- feedback deve respeitar objetivo da tarefa: imediato em precisão focal quando útil; pós-bloco em fluência quando interrupção prejudicaria desempenho;
- placement atual continua adaptativo/local/determinístico, mas banco 12/idioma é fundação insuficiente para lançamento e deve ser ampliado com itens variados/revisados;
- checkpoints futuros reajustam percurso com evidência/retensão sem apagar histórico;
- métricas prioritárias: retenção, recuperação sem pista, transferência, compreensão de material não memorizado, redução de erros recorrentes e desempenho CEFR; XP/streak/tempo no app são apenas engajamento.

Modelo-alvo aprovado:
`PLACEMENT → DIAGNÓSTICO HONESTO → OBJETIVO/PREFERÊNCIAS → CONTEÚDO CEFR → INPUT COMPREENSÍVEL → PRÁTICA ATIVA → FEEDBACK → RETRIEVAL COM MENOS PISTAS → FSRS → TRANSFERÊNCIA → INTERAÇÃO/CONVERSAÇÃO → CHECKPOINT → REAJUSTE`.

`PEDAGOGY_ARCHITECTURE.md` é agora leitura obrigatória junto de `PROJECT_STATE.md` e `PRODUCT_SPEC.md` para mudanças pedagógicas. Para arte continua obrigatório `VISUAL_BIBLE.md`; para pesquisa, `RESEARCH.md`.

### Voz/Storage — permanece congelado com segurança
Nada desta frente autoriza contornar o bloqueio anterior. `Chiu-animada-recorte-final.m4a` continua privada, fora do GitHub/Supabase/APK. Não usar `storage.objects` via SQL, service_role, bucket público, Edge Function privilegiada ou outro bypass. Quando houver operação normal de Storage, executar primeiro o teste descartável registrado abaixo e só depois pedir confirmação imediata para a voz aprovada.

### Próximos passos seguros
1. Usar a nova arquitetura pedagógica como contrato, sem massificar conteúdo ainda.
2. Fortalecer/expandir gradualmente placement por nível e variedade, com testes e CI verde entre mudanças estruturais.
3. Definir contrato de evidência por competência antes de qualquer score multidimensional visível.
4. Definir modelo de objetivo/disponibilidade/prioridade sem alterar currículo essencial.
5. Planejar progressão de retirada de pistas/transferência e histórias como unidades pedagógicas conectadas.
6. Listening real continua dependente da frente segura de áudio; speaking/pronúncia somente com mecanismo honesto de captura/análise.
7. Parar e pedir decisão da usuária apenas quando houver escolha real de produto/privacidade/experiência, não por microetapas técnicas.

## ESTADO AUTORITATIVO — 2026-09-03 10:16 BRT — STORAGE PRIVADO RECONFIRMADO; TESTE DE OBJETO BLOQUEADO PELA FERRAMENTA

Este bloco prevalece sobre trechos antigos conflitantes deste arquivo. O estado real do GitHub e do Supabase continua prevalecendo sobre a documentação. Não recomeçar a frente.

### GitHub/Android reconfirmados
- Repositório `Canumori/Chiu-Know`, branch `main`, deliberadamente público; não mudar visibilidade por suposição.
- HEAD observado antes deste commit documental: `3371b296473bc27ff70ef24fa5de1b735eeff866` — `docs: record validated email auth and private voice handoff`.
- Android CI #144, run `33758178184`, job `100657596732`: `COMPLETED / SUCCESS`.
- O job `build-debug` passou por unit tests, build debug, upload do APK e etapas finais.

### Supabase correto reconfirmado
- A conexão disponível mostrou somente o projeto Chiu Know `uskxabsodcnzlovuaurp`.
- Organização: `aeerqbmrwulxsawhjyvm`; região `sa-east-1`; status `ACTIVE_HEALTHY`.
- O Supabase do Chiu Player `hpcbkvbrlwjnwlikmbfb` não apareceu e não foi tocado.
- Existe exatamente 1 autorização habilitada em `private.app_user_access`; nenhum e-mail, UUID ou dado pessoal foi registrado aqui.
- Bucket `character-voices` reconfirmado com `public=false`, limite 2 MiB e allowlist `audio/mp4`, `audio/x-m4a`, `audio/m4a`.
- Estado do bucket reconfirmado: 0 objetos.
- Política real encontrada no bucket: somente SELECT `authorized_users_read_character_voices`, role `authenticated`, condicionada a linha própria habilitada em `private.app_user_access`.
- Não há política INSERT/UPDATE/DELETE para usuários comuns do APK.
- Advisor de performance: zero lints.
- Advisor de segurança agora retorna 1 WARN: `auth_leaked_password_protection`, indicando proteção contra senhas vazadas desativada. Isto não foi criado nesta frente e não deve ser confundido com falha do Storage; avaliar habilitação separadamente quando houver ferramenta/configuração apropriada.

### Resultado da tentativa do teste descartável
- A skill Supabase atual foi lida integralmente e a documentação atual de Storage foi consultada.
- Buckets privados exigem controle por RLS para download e não equivalem a URL pública permanente.
- A conexão Supabase disponível neste chat NÃO expõe operação normal de upload/download/delete de objetos do Storage.
- Também não foi encontrada integração adicional instalada que ofereça upload normal ao Supabase Storage.
- Portanto o teste reversível com `storage-policy-test.m4a` NÃO foi executado.
- Nenhum objeto temporário foi criado; nada precisou ser removido.
- Não foi feita inserção direta em `storage.objects`, não foi pedido/usado `service_role`, não foi criado bypass, não foi criada política temporária de upload e nenhum segredo foi exposto.
- A voz aprovada `Chiu-animada-recorte-final.m4a` permanece intocada, fora do GitHub e fora do Supabase.

### Próximo passo exato
1. Não enviar a voz aprovada enquanto o teste real de Storage não puder ser executado pelo mecanismo normal de objetos.
2. Quando uma conexão/ferramenta com operações normais de Storage estiver disponível, reconfirmar primeiro o projeto `uskxabsodcnzlovuaurp`, bucket e políticas.
3. Gerar/localizar áudio silencioso descartável sem dados pessoais e fazer upload normal; não manipular `storage.objects` via SQL.
4. Confirmar caminho, tamanho, MIME, bucket privado e ausência de URL pública permanente.
5. Testar download autenticado/autorizado e confirmar bloqueio anônimo/não autorizado.
6. Remover o objeto descartável pelo mecanismo normal de Storage e confirmar 0 objetos novamente.
7. Somente se todos os testes passarem, pedir confirmação imediata da usuária antes de enviar `Chiu-animada-recorte-final.m4a`.
8. Até lá, não improvisar com Edge Function privilegiada, política anônima temporária, GitHub Action com segredo, service_role ou outro backend.

## NOTA DE PRESERVAÇÃO DO HISTÓRICO
O histórico operacional detalhado anterior a este bloco permanece no Git do arquivo e nos commits anteriores. Em caso de necessidade de auditoria, consultar a versão `5dc1bcde09904d954a49434ad835127105dfe9c3` do blob anterior e os commits registrados. Este arquivo foi condensado nesta atualização para manter o topo autoritativo legível sem apagar as regras operacionais essenciais.

## REGRAS OPERACIONAIS PERMANENTES
- Ler `PROJECT_STATE.md` + `PRODUCT_SPEC.md` + `PEDAGOGY_ARCHITECTURE.md` antes de mudança pedagógica; arte exige `VISUAL_BIBLE.md`; pesquisa exige `RESEARCH.md`.
- GitHub/Supabase reais vencem documentação stale.
- Repo `Canumori/Chiu-Know`, branch `main`, público deliberadamente para Actions.
- Supabase exclusivo Chiu Know: `uskxabsodcnzlovuaurp`, org `aeerqbmrwulxsawhjyvm`, região `sa-east-1`.
- Supabase Chiu Player `hpcbkvbrlwjnwlikmbfb` é proibido.
- Usuária não programa: não mandar terminal, edição de código, conflitos ou ZIP.
- Mudanças pequenas, reversíveis, testadas; CI verde antes de próxima mudança estrutural.
- Não publicar/distribuir/OTA automaticamente.
- Placement: local/determinístico/adaptativo, começa B1; não voltar a porcentagem fixa; banco atual 12/idioma, 2 por A1–C2, 5 idiomas; insuficiente para lançamento; não inventar seis scores.
- Preservar trilha, DataStore, evidência, feedback congelado, fila e FSRS-6 próprio separado de mastery.
- Erro conta tentativa/exposição, não mastery; progresso visual/XP não é domínio.
- Starter A1 existente usa FILL_IN, REORDER e MULTIPLE_CHOICE com reviewKeys compartilhados quando o conhecimento é o mesmo; formato diferente não cria mastery novo.
- Ainda não existem mastery real, listening real em massa, speaking/ASR/pronúncia válida, writing livre avançado, histórias completas, tutor IA, conteúdo A1–C2 completo ou scores multidimensionais válidos.
- Visual absoluto: Chiu realista branco/cabelo castanho SOMENTE logo/ícone; Chiu amarelo cartunesco SEMPRE no universo. Mosquito definitivo Jurandir. Não alterar masters aprovados.
- Voz aprovada privada `Chiu-animada-recorte-final.m4a`: não GitHub público, não outro projeto/personagem, não fornecedor externo sem nova autorização, não Supabase até teste normal de Storage passar.
- Ao comando “Continue”, avançar autonomamente até decisão REAL da usuária; não parar em microcommit se CI/ferramentas permitirem.
- Ao fim de frente relevante, atualizar este arquivo com HEAD/CI/decisões/próximo passo.