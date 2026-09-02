# CHIU KNOW? — PROJECT STATE

## ESTADO AUTORITATIVO — 2026-09-02 — A1 MULTIHABILIDADE + EVIDÊNCIA + REORDER VALIDADO

Este arquivo é o handoff operacional autoritativo do projeto. Em qualquer novo chat, NÃO recomeçar, NÃO inferir estado apenas pela memória e NÃO seguir blocos históricos antigos quando conflitarem com esta seção. O estado real do GitHub vence documentação desatualizada.

LEITURA OBRIGATÓRIA ANTES DE QUALQUER ALTERAÇÃO: este `PROJECT_STATE.md` junto com `PRODUCT_SPEC.md`. Para arte/personagens, ler também `VISUAL_BIBLE.md`; para referências técnicas/pedagógicas, `RESEARCH.md`.

## 1. PROJETO E SEPARAÇÃO ABSOLUTA
- Repositório: `Canumori/Chiu-Know`.
- Branch de trabalho atual: `main`.
- Android nativo: Kotlin + Jetpack Compose.
- Uso inicialmente privado/restrito, aproximadamente até 100 usuários.
- Supabase EXCLUSIVO permitido para Chiu Know?: projeto ref `uskxabsodcnzlovuaurp`, organização `Chiu Know` / `aeerqbmrwulxsawhjyvm`, região `sa-east-1`.
- Supabase do Chiu Player PROIBIDO nesta frente: `hpcbkvbrlwjnwlikmbfb`, organização `Chiu` / `nnrwosbnvdvzaoflwxlo`.
- Se `hpcbkvbrlwjnwlikmbfb` aparecer numa operação de Chiu Know?, PARAR e não escrever.
- Não misturar código, branches, versões, APKs, workflows, backend, OTA ou decisões do Chiu Player.
- Toda a frente recente de placement, trilha, atividades, Reading, evidência, seleção e REORDER é local Android e NÃO tocou Supabase.

## 2. VISÃO DE PRODUTO QUE NÃO PODE SER REDUZIDA
Chiu Know? é um app multilíngue CEFR A1–C2 com ensino adaptativo, revisão inteligente, histórias interativas, personagens recorrentes e tutor por IA. Objetivo principal: competência real, retenção e capacidade de usar o idioma fora do app — não maximizar cliques, XP ou tempo de tela.

Roadmap continua incluindo:
- interface e idioma-alvo independentes;
- placement inicial e reteste;
- resultado geral e, quando houver evidência válida, por grammar/vocabulary/listening/reading/writing/speaking;
- trilha A1→A2→B1→B2→C1→C2;
- revisão espaçada/FSRS ou equivalente permissivo;
- XP, streak, meta diária, progresso e conquistas como motivação, não prova de domínio;
- histórias interativas;
- tutor por IA;
- áudio/listening/speaking com arquitetura própria e sem confundir ASR com avaliação de pronúncia.

Não fabricar funcionalidades do roadmap antes de existir infraestrutura/evidência real.

## 3. PRINCÍPIOS PEDAGÓGICOS ABSOLUTOS
Preservar os princípios definidos em `PRODUCT_SPEC.md`:
1. compreender com explicações claras e proporcionais ao nível;
2. praticar ativamente, não apenas reconhecer;
3. recuperar da memória e reter;
4. transferir conhecimento para contextos diferentes;
5. feedback útil e explicativo;
6. progresso/XP não equivalem a domínio;
7. integrar seis habilidades ao longo da evolução;
8. C1/C2 exigem tarefas qualitativamente avançadas, não A1 com palavras raras;
9. adaptação baseada em evidência reproduzível;
10. medir aprendizagem/retorno/retensão, não apenas engajamento.

Antes de massificar conteúdo, validar pequena fatia ponta a ponta. Cada atividade deve declarar habilidade, objetivo, CEFR, alvo de conhecimento, tipo de resposta, feedback e caminho de revisão.

## 4. ESTADO ANDROID FUNCIONAL
Fluxo atual:
`LANGUAGE_SELECTION → PLACEMENT_INTRO → PLACEMENT_TEST → PLACEMENT_RESULT → LEARNING_TRAIL → LEARNING_ACTIVITY`.

Se houver nível salvo para o idioma-alvo, o aluno pode continuar para a trilha sem refazer placement; reteste permanece disponível.

Idiomas-alvo atuais:
- Português `pt`
- English `en`
- Español `es`
- Français `fr`
- 한국어 `ko`

Preferências e estado relevante são persistidos localmente via DataStore.

Configuração conhecida:
- namespace/applicationId `com.chiu.know`;
- versionCode 1;
- versionName 0.1.0;
- minSdk 26;
- target/compile 35;
- Java/JVM 17;
- Compose BOM 2024.12.01;
- AppCompat 1.7.0;
- DataStore 1.1.7;
- JUnit 4.13.2 em testes locais.

## 5. PLACEMENT — PRESERVAR
Placement adaptativo é LOCAL e determinístico:
- começa em B1;
- acerto/erro estreita progressivamente o intervalo CEFR A1–C2;
- resultado final vem do estado do motor adaptativo;
- NÃO voltar ao antigo cálculo fixo por proporção de acertos.

Banco completo atual:
- 12 perguntas por idioma;
- 2 por nível A1, A2, B1, B2, C1, C2;
- 5 idiomas;
- total 60 itens;
- IDs únicos e testes de banco.

O resultado é estimativa/protótipo de proficiência, não certificação CEFR oficial. O placement atual NÃO possui evidência válida suficiente para produzir seis notas independentes por habilidade. Não inventar esses scores.

## 6. TRILHA CEFR
Modelo `CefrTrail.kt`:
- `CefrTrailStatus { COMPLETED, CURRENT, LOCKED }`;
- `buildCefrTrail(estimatedLevel)`.

É fundação de navegação, não sistema completo de mastery/desbloqueio. Não afirmar que níveis estão pedagogicamente dominados só por status visual.

Commit `2121e7ca3a16811e5046f71d7b331bde26568dc3` tornou `LearningTrailScreen` scroll-safe. Preservar em telas pequenas. Não alterar `CenteredColumn` globalmente para resolver rolagem local.

## 7. MODELO DE ATIVIDADE
Arquivo principal: `LearningActivity.kt`.

`LearningSkill`:
- GRAMMAR
- VOCABULARY
- LISTENING
- READING
- WRITING
- SPEAKING

`ResponseType`:
- MULTIPLE_CHOICE
- FILL_IN
- REORDER
- FREE_TEXT
- LISTEN_AND_RESPOND
- SPEAK

Metadados de atividade incluem id, level, primarySkill, learningObjective, knowledgeTarget, responseType, prompt, feedback, reviewKey, acceptedAnswers e agora `responseOptions` para tipos interativos estruturados.

Avaliador atual `isLearningAnswerCorrect(...)` é determinístico, faz trim/lowercase e não apaga acentos. Não usar esse comparador simples como se fosse avaliador de escrita livre, pronúncia ou fala complexa.

### Fundação de respostas estruturadas
- commit `b6cdaf285a24cc4f054a9560410840dbfe41e48a` — adiciona `responseOptions` e validação para respostas interativas;
- commit `74e460db871324701666ee4ad09d219b0a1d0613` — testes da estrutura;
- Android CI #113 validou essa fundação com sucesso antes da UI REORDER.

## 8. A1 — CONTEÚDO VALIDADO ATUAL
A fundação A1 agora usa quatro formas de prática dentro de três habilidades reais:
- VOCABULARY FILL_IN: 2 variantes contextuais por idioma;
- GRAMMAR FILL_IN: 2 variantes contextuais por idioma;
- READING FILL_IN: 2 variantes contextuais por idioma;
- GRAMMAR REORDER: 1 variante de reconstrução por idioma, transferindo o mesmo alvo gramatical para uma resposta mais ativa.

Portanto, no HEAD validado `b85e6c3ce63452fc4eb92853b7858571a5f99915`, existem exatamente **7 atividades A1 candidatas por idioma** e 35 atividades starter no total.

O REORDER gramatical reutiliza deliberadamente o mesmo `reviewKey` do alvo de gramática FILL_IN. Ele NÃO cria artificialmente um novo conhecimento só porque o formato de resposta mudou. Assim continuam existindo 3 alvos/reviewKeys por idioma: saudação, copula/apresentação e leitura de introdução.

Não existe conteúdo artificial B1/C2 no starter para preencher trilha.

### Vocabulary
Arquivo `StarterLearningActivities.kt`.
- 2 contextos por idioma para saudação básica.
- Exemplos de reviewKey: `en:a1:greeting:hello`, `pt:a1:greeting:ola`, `es:a1:greeting:hola`, `fr:a1:greeting:bonjour`, `ko:a1:greeting:annyeonghaseyo`.

### Grammar FILL_IN
Arquivo `A1IntegratedLearningActivities.kt`.
- 2 contextos por idioma para apresentação/copula em primeira pessoa.
- English: `I am` / resposta `am`.
- Português: `eu sou` / `sou`.
- Español: `yo soy` / `soy`.
- Français: `je suis` / `suis`.
- 한국어: introdução polida com `예요`.

Commits principais:
- `4bd64129dfccb31eea134035d95434d45824aa9b` — primeira fatia controlada de grammar;
- `8243623946038e8e2fc4f16a5e43c5faf0d23c56` — integração ao starter;
- `497dd6250d85915eb226704bc681ddea539c877d` e `26c798d68f8a2ca138982dedaa43cba389d60b45` — proteção por testes;
- `153c9bc1c48076b207804bda65e09e22bf6af173` — remove teste antigo obsoleto;
- Android CI #98: SUCCESS.

### Reading
Arquivo `A1ReadingActivities.kt`.
Dois contextos por idioma, com Mia e Chiu, para localizar o nome explicitamente apresentado.

Commits:
- `8e50f38ecde412122c2ade5e6aeff3042cc97bab` — primeira fatia Reading;
- `e883d91cd3ebaf1db0a3cd49957f518f34ec6e33` — integração;
- `592b1871e9439eb75c513a1329775e2ceab50258` — teste inicial;
- CI #102 falhou por teste integrado antigo ainda esperar 4 atividades, não por defeito da produção Reading;
- `e33b256a6fdc14e3f3ed634d9f5cd3d04467d891` corrigiu somente expectativa obsoleta; CI #103 SUCCESS;
- `622054a72adb5335861023932d11c41eef124b45` — segundo contexto por idioma;
- `19d3a31aba62b9d65b2b5e96ecd9c81026f60edd` — testes da transferência;
- `8560f386327305cc957ddb34f7d50b88116a73da` — rotação integrada de 6 atividades; CI #106 SUCCESS.

### REORDER — NOVA FRENTE VALIDADA
A UI real agora possui caminho específico para `ResponseType.REORDER`:
- tokens vêm explicitamente de `responseOptions`;
- aluno toca nos tokens para montar a frase;
- tokens já usados deixam de aparecer como disponíveis;
- há ação de desfazer último token;
- resposta efetiva é montada deterministicamente com espaços;
- botão de verificar só habilita quando existe resposta;
- feedback continua usando avaliador determinístico;
- FILL_IN continua no caminho anterior de `OutlinedTextField`, preservado.

Commit UI:
- `1cfdbf3aa6418eba221a15a781e569fb8e3fe1b2` — `feat: add deterministic reorder interaction to learning UI`.
- Android CI #114 / run `33658745087`: COMPLETED / SUCCESS.

Primeira fatia A1 REORDER nos cinco idiomas:
- `bb14c01e4681e4cce02ee6b6cb8ae5a970e10445` — cria atividades REORDER;
- `8d448129ba7fde8b9a38b9294cf4d0d193ce9579` — integra ao starter bank;
- `2dfa9721d51f65c187f85b2a737d53209b900ae9` — testes específicos;
- `b85e6c3ce63452fc4eb92853b7858571a5f99915` — atualiza proteção integrada para starter com 7 atividades por idioma.

Android CI #118 / run `33659507938` no HEAD `b85e6c3ce63452fc4eb92853b7858571a5f99915`: **COMPLETED / SUCCESS** em 2026-09-02. Este é o último HEAD funcional explicitamente validado antes deste commit de documentação.

## 9. SELEÇÃO BASEADA EM EVIDÊNCIA OBSERVADA
Arquivo `StarterReviewSelection.kt`.
Função `starterLearningActivityForEvidence(languageCode, level, evidence)`.

Algoritmo atual:
- obtém candidatos do idioma e nível;
- agrupa por `reviewKey`, preservando ordem estável do currículo;
- filtra evidência ao nível e reviewKeys candidatos;
- escolhe o reviewKey com MENOS tentativas observadas;
- empate mantém ordem estável;
- dentro do alvo escolhido, alterna variantes pelo número de tentativas daquele reviewKey módulo quantidade de variantes.

Importante após REORDER: como Grammar FILL_IN e Grammar REORDER compartilham o mesmo reviewKey, a rotação daquele alvo passa naturalmente pelas variantes disponíveis sem fingir que REORDER é um conhecimento diferente.

Isso NÃO é mastery, FSRS, spaced repetition completa, XP, streak, desbloqueio ou inferência CEFR. Correção/incorreção não é usada para declarar domínio.

Commits:
- `571b1dab6f2260922c52a744089ec60a7a75e243` — seletor;
- `7a5130c090c38c9e5373ca6f8b1566d0f4d8d9cd` — testes;
- CI #108 / run `33654755461`: SUCCESS.

UI conectada ao seletor:
- `150fbff9624a648e668f2812c4842aaaa5b03517` — app usa seletor baseado em evidência;
- esse commit apagou acidentalmente uma `}` de `PlacementResultScreen`, causando CI #109 FAILURE na compilação/testes;
- `aae3d7276ac401056f68f931a2d2cb7c207c9465` restaurou somente a fronteira sintática;
- CI #110 / run `33655546522`: SUCCESS.

Comportamento de tela preservado: atividade é congelada durante a composição da tela para que salvar a tentativa não troque o exercício antes de o aluno ler o feedback. Ao sair/reentrar, nova composição seleciona com evidência atualizada.

## 10. EVIDÊNCIA LOCAL
`LearningEvidence` registra uma tentativa observada com:
- activityId;
- reviewKey;
- level;
- primarySkill;
- correct;
- timestamp.

Não armazena resposta textual bruta do aluno.
Persistência local por idioma via DataStore.
Formato serializado atual: `timestamp|activityId|reviewKey|level|primarySkill|correct`.
Decoder ignora entradas malformadas.
Há agregação factual por atividade/reviewKey.

Commits relevantes:
- `83a2d6b155894ad9350a9aaa86f253e08270bdc7` — modelo de evidência;
- `b3d137196a6dab1974d00765a1e0fd0b9b0a7b8` — persistência local;
- `1a7c82a2c3917dbb35adb6a4b28240781eebd385` — resumo factual;
- `f7ecf3ad55e2490d86c3fb8fd27b69ffd6802f82` — codec tolerante;
- `1c5d51aaf87b793e8f59af2a1e9b63f723959a6` — restauração local;
- `49f8fa766c8b853a34394ac63455d50b46dfc5ae` — remove display hardcoded em inglês;
- `2d003e830695b3c96a1801f9c5c29c0fc66f2958` — encoder centralizado;
- `31f1289d9115d5cc206470b1eea2a660e697dc08` — roundtrip test;
- `cacbe5a9ad40bee062769331053d16b90b803f70` — UI usa encoder centralizado;
- CI #93 / run `33652350240`: SUCCESS.

DataStore string-set é fundação pequena, NÃO armazenamento definitivo para histórico ilimitado.

## 11. LOCALIZAÇÃO DA UI DE ATIVIDADE
Strings da tela de atividade foram localizadas para default English + Português + Español + Français + 한국어.
Commits/CI conhecidos:
- PT `15ead3963059462468ab92ad12aafd55c0b94e52`, CI #80 SUCCESS;
- ES `a1f4caf2873a3cb69a1360999cda9ca15eaf62ee`, CI #81 SUCCESS;
- FR `b5ec61bc7976b06125fad2b7628524319dc27c7`, CI #82 SUCCESS;
- KO `b47064dc5992f54b63fab308f2f889cb4badcfd7`, CI #83 SUCCESS.

Nota técnica atual: o botão de desfazer do REORDER usa símbolo `↶`; se futuramente ganhar texto, localizar em todos os idiomas. Não introduzir strings visíveis hardcoded desnecessariamente.

## 12. PERSONAGENS E IDENTIDADE VISUAL — NÃO REGREDIR
Nome: **Chiu Know?** — o ponto de interrogação faz parte da identidade.
Estética: branco perolado, rosa/rosé, molduras arredondadas/peroladas, patas/corações, expressiva e estranha/cartunesca — não “cute genérico”.

### Regra absoluta dos dois Chius
1. LOGO/ÍCONE: somente o Chihuahua branco fotorrealista aprovado, cabelo castanho tipo bowl-cut, olhos grandes escuros, dentinhos. Não redesenhar por IA quando identidade exata for necessária; reutilizar master aprovado.
2. UNIVERSO/ATIVIDADES/HISTÓRIAS/CARDS: sempre o Chihuahua amarelo cartunesco/esquisito aprovado, olhos enormes, orelhas grandes, dentes/língua/expressão exagerados.
Nunca trocar ou misturar os dois.

### Elenco canônico
- Chiu — Chihuahua amarelo;
- Mia — gata dramática/elegante;
- Jurandir — pernilongo/mosquito falante, atrapalhado/esquecido, grande coração/apetite;
- Barto — morcego filosófico;
- Lara — arara opinativa/barulhenta;
- Caca — capivara zen até se estressar;
- Onça — forte/decidida, insegura por dentro;
- Perry — ornitorrinco brilhante/esquisito;
- Lena — preguiça muito lenta.

**Jurandir é definitivo.** O nome antigo do mosquito está aposentado e só pode existir como proveniência histórica na Bíblia Visual. Não reutilizar em UI, histórias, atividades, docs novas ou assets.

### Pranchas aprovadas e protocolo
`VISUAL_BIBLE.md` registra:
- Prancha A: elenco/universo original fornecido pela usuária;
- Prancha B: amostras pedagógicas, aprovada como direção;
- Prancha C: character asset/sticker sheet transparente, aprovada como direção/master process;
- Prancha D: conceito de telas, aprovada como direção UX/visual, não especificação técnica pixel-perfect.

Master aprovado é imutável. Reutilizar arquivo, não recriar. Nova pose = nova candidata mostrada à usuária antes de integração. Não deformar, recolorir, espelhar/cortar sem motivo. Preferir master individual transparente de alta resolução. Runtime pode ter cópia otimizada sem alterar desenho/proporções/cores.

A master sheet transparente aprovada foi gerada na conversa, mas o binário ainda NÃO está incorporado ao GitHub/APK. Não afirmar o contrário. Não regenerar silenciosamente substituto. Quando houver caminho seguro para integrar binário exato, verificar primeiro que é o arquivo aprovado.

## 13. REGRESSÕES IMPORTANTES JÁ APRENDIDAS
- CI #102: falha por teste integrado obsoleto após Reading; produção Reading não era a causa. Corrigido estreitamente.
- CI #109: falta acidental de `}` em `PlacementResultScreen` durante reconstrução de `ChiuKnowApp.kt`; não era falha do seletor por evidência. Corrigido por `aae3d727...` e #110 verde.
- Não interpretar workflow antigo vermelho como falha do HEAD atual. Conferir sempre run/head SHA.
- Não fazer alterações grandes enquanto CI da mudança estrutural anterior ainda não foi validado.

## 14. ARQUIVOS-CHAVE ATUAIS
Documentação:
- `PROJECT_STATE.md`
- `PRODUCT_SPEC.md`
- `RESEARCH.md`
- `VISUAL_BIBLE.md`
- `README.md`

Produção relevante:
- `app/src/main/java/com/chiu/know/MainActivity.kt`
- `app/src/main/java/com/chiu/know/model/AdaptivePlacement.kt`
- `LanguageOption.kt`
- `PlacementTest.kt`
- `CefrTrail.kt`
- `LearningActivity.kt`
- `StarterLearningActivities.kt`
- `A1IntegratedLearningActivities.kt`
- `A1ReadingActivities.kt`
- arquivo da fatia A1 REORDER criado na sequência `bb14c01...` (confirmar nome real no GitHub antes de editar);
- `StarterReviewSelection.kt`
- `LearningEvidence.kt`
- `LearningEvidenceSummary.kt`
- `LearningEvidenceCodec.kt`
- `app/src/main/java/com/chiu/know/ui/ChiuKnowApp.kt`
- resources `values/`, `values-pt/`, `values-es/`, `values-fr/`, `values-ko/`.

Testes relevantes incluem placement, trilha, activity model, A1 grammar, A1 reading, starter integration, starter review selection, evidence/model/codec/summary e nova fatia REORDER. Confirmar nomes reais antes de modificar.

## 15. O QUE AINDA NÃO EXISTE — NÃO FINGIR
- Não há Writing livre validado.
- Não há Listening real com áudio validado.
- Não há Speaking real/ASR/pronunciation scoring validado.
- Não há FSRS completo/mastery engine.
- Não há progressão pedagógica completa A1–C2.
- Não há seis scores de placement válidos por habilidade.
- Não há tutor IA integrado de produção.
- Não há histórias completas de produção.
- Não há gamificação completa XP/streak/metas/conquistas.
- Não há integração binária final dos personagens aprovados no APK.
- Não há banco massivo de conteúdo; deliberadamente validamos fatias pequenas antes de escalar.

## 16. PRIORIDADE ATÉ 13 DE SETEMBRO / CONTINUIDADE COM FREE
A usuária informou que o ChatGPT Plus termina no dia 13 e depois a conta ficará Free. Isso NÃO muda os princípios nem autoriza correr com baixa qualidade. A prioridade operacional até lá é deixar a arquitetura difícil, APK utilizável, testes e documentação/handoff tão sólidos quanto possível, para que depois o projeto continue em blocos menores mesmo se houver limites de ferramentas/modelos.

Não prometer que o Free terá exatamente as mesmas ferramentas/limites. O repositório e documentação devem ser suficientes para reconstruir o contexto sem depender da memória do chat.

## 17. PRÓXIMA FRENTE EXATA
Estado validado antes deste commit documental:
- HEAD funcional: `b85e6c3ce63452fc4eb92853b7858571a5f99915`;
- Android CI #118 / run `33659507938`: COMPLETED / SUCCESS;
- 7 atividades A1 por idioma;
- REORDER funcional na UI e integrado ao starter;
- seleção por evidência preservada;
- Supabase não tocado.

Próximos passos seguros, em ordem:
1. Após este commit de documentação, conferir CI do próprio handoff se workflow disparar; documentação não deve alterar comportamento Android.
2. Reinspecionar os testes do seletor com o novo fato de Grammar possuir 3 variantes no mesmo reviewKey (2 FILL_IN + 1 REORDER). Garantir por teste que a rotação por evidência realmente alcança REORDER sem criar reviewKey falso e que evidência estrangeira/correção não altera indevidamente a seleção.
3. Se verde, decidir tecnicamente a próxima infraestrutura difícil que não exija decisão da usuária. Priorizar suporte correto de outro tipo de resposta somente se puder ser validado ponta a ponta.
4. `MULTIPLE_CHOICE` pode ser próximo candidato técnico porque `responseOptions` já existe, mas NÃO massificar reconhecimento nem substituir recuperação ativa. Inspecionar UI/modelo primeiro.
5. Writing/FREE_TEXT: não implementar avaliação por igualdade exata como se fosse escrita real. Exige política/evaluator próprio; pode virar ponto de decisão.
6. Listening/Speaking: exigem áudio/TTS/ASR e distinção ASR versus pronúncia; não fingir por texto. Pode exigir decisão de arquitetura/produto.
7. Não pular para FSRS completo, backend, IA ou histórias completas antes de a fundação necessária estar clara.
8. Se uma nova frente exigir pose/asset, PARAR antes da integração e mostrar amostra à usuária conforme `VISUAL_BIBLE.md`.
9. Continuar automaticamente por etapas seguras até surgir decisão REAL da usuária; não pedir microdecisões técnicas.

## 18. REGRA DE CONTINUIDADE PARA QUALQUER NOVO CHAT
Quando a usuária disser “Continue”:
1. usar ferramentas GitHub;
2. ler `PROJECT_STATE.md` e `PRODUCT_SPEC.md` JUNTOS antes de escrever;
3. conferir estado real da `main`, HEAD, commits e CI relevante;
4. se houver conflito, GitHub real vence docs;
5. preservar tudo que funciona;
6. mudanças pequenas, isoladas e reversíveis;
7. validar CI antes de empilhar mudança estrutural seguinte;
8. se backend for realmente necessário, conferir Supabase correto e manter separação absoluta;
9. nunca pedir à usuária para editar código, usar terminal, resolver conflitos ou abrir ZIP;
10. não publicar/distribuir automaticamente;
11. registrar no `PROJECT_STATE.md` mudanças relevantes antes de deixar um handoff importante;
12. continuar até precisar de decisão real dela.

A usuária não é programadora. Respostas devem explicar de forma simples o que foi validado, o que mudou, o que foi preservado e o próximo passo.

---

## HISTÓRICO
O GitHub preserva commits e workflows anteriores. Este bloco autoritativo substitui descrições antigas conflitantes, mas não apaga o histórico técnico. Para detalhes de produto, `PRODUCT_SPEC.md` continua obrigatório; para arte, `VISUAL_BIBLE.md` é a fonte autoritativa; para pesquisa, `RESEARCH.md`.