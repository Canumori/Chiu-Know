# CHIU KNOW? — PROJECT STATE

## ATUALIZAÇÃO AUTORITATIVA — 2026-09-01 — ANDROID, IDIOMAS E PERSISTÊNCIA

Este arquivo é a fonte autoritativa de continuidade do Chiu Know?, mas o estado real do GitHub e do Supabase sempre vence qualquer informação que tenha ficado desatualizada.

## ESTADO ATUAL DO ANDROID
- Repositório: `Canumori/Chiu-Know`.
- Branch atual de desenvolvimento: `main`.
- Stack: Kotlin + Jetpack Compose.
- Módulo: `app`.
- Namespace/applicationId: `com.chiu.know`.
- `minSdk = 26`, `targetSdk = 35`, `compileSdk = 35`.
- `versionCode = 1`, `versionName = 0.1.0`.
- Java/JVM 17.
- Compose BOM `2024.12.01`.
- AppCompat `1.7.0` foi adicionado para suporte ao idioma do aplicativo.
- DataStore Preferences `1.1.7` foi adicionado no commit `84809ae59c90ef1cdef51815208d0442f99f254d`; antes de implementar leitura/gravação, conferir o CI desse commit.

### FLUXO IMPLEMENTADO
O aplicativo já possui o fluxo local:
1. `LANGUAGE_SELECTION`
2. `PLACEMENT_INTRO`
3. `PLACEMENT_TEST`
4. `PLACEMENT_RESULT`

O botão Continue JÁ navega para o placement test. A afirmação antiga de que ele não navegava está revogada.

O placement test atual:
- possui 6 perguntas locais, uma por faixa A1–C2;
- usa atualmente um banco inicial em inglês independentemente do idioma-alvo;
- conta respostas corretas e chama `estimateLevel(correctAnswers, total)`;
- apresenta explicitamente o resultado como nível estimado/protótipo, não certificação oficial CEFR;
- permite tentar novamente e voltar para alterar idiomas;
- ainda NÃO é o banco adaptativo/calibrado final.

### IDIOMAS
Idiomas de interface e idiomas-alvo atualmente cadastrados:
- Português (`pt`)
- English (`en`)
- Español (`es`)
- Français (`fr`)
- 한국어 (`ko`)

Recursos de interface existem em:
- `app/src/main/res/values/strings.xml`
- `app/src/main/res/values-pt/strings.xml`
- `app/src/main/res/values-es/strings.xml`
- `app/src/main/res/values-fr/strings.xml`
- `app/src/main/res/values-ko/strings.xml`

O fluxo de placement já usa recursos traduzidos para textos de interface. As perguntas/opções continuam sendo conteúdo do idioma-alvo e NÃO devem ser tratadas como simples strings de interface.

A seleção do idioma da interface já chama `AppCompatDelegate.setApplicationLocales(...)`.

### CI CONFIRMADO
- Run #5 / `33441495451`: sucesso no primeiro fluxo local de placement.
- Run #8 / `33444348819`, SHA `da923930e55d7d0f3736ade3f194b6230cd737eb`: sucesso na aplicação do idioma selecionado à interface.
- Run #14 / `33445288797`, SHA `81f972998f662535f30dd7e7c4bac29daeaaf7a3`: sucesso na integração do fluxo de placement com os recursos multilíngues.

### ÚLTIMA ALTERAÇÃO / PRÓXIMO PASSO EXATO
Último commit conhecido nesta continuidade: `84809ae59c90ef1cdef51815208d0442f99f254d` — `feat: add DataStore preferences dependency`.

PRÓXIMO PASSO OBRIGATÓRIO:
1. Conferir no GitHub Actions o CI do commit `84809ae5...` antes de acrescentar outra mudança.
2. Se falhar, investigar job/log e corrigir somente a causa dessa etapa.
3. Se passar, implementar de forma pequena a persistência LOCAL das escolhas de idioma da interface e idioma-alvo usando DataStore.
4. Validar novamente no CI.
5. Só depois avançar para bancos de placement específicos por idioma e evolução do mecanismo adaptativo.

Não conectar Supabase para realizar essa persistência: ela é local.

## ARTE / IDENTIDADE VISUAL
- Nenhuma arte ou mascote deve ser gerada, redesenhada ou substituída por iniciativa do assistente.
- `VISUAL_BIBLE.md` é obrigatória para qualquer trabalho visual.
- O Chiu do logo é a cabeça fotorealista aprovada do Chihuahua branco com cabelo castanho estilo bowl cut; quando identidade exata for necessária, reutilizar o master aprovado e NÃO redesenhar por IA.
- O Chiu personagem do universo é o cachorro cartoon amarelo aprovado e NÃO deve ser misturado com o Chiu do logo.
- Só trabalhar em arte quando Camila pedir/autorizar explicitamente.

# REGRA ABSOLUTA DE ISOLAMENTO DO SUPABASE

## CHIU KNOW? — ÚNICO SUPABASE PERMITIDO PARA ESTE PROJETO
- Repositório: `Canumori/Chiu-Know`.
- Organização Supabase dedicada: `Chiu Know`.
- Organization ID: `aeerqbmrwulxsawhjyvm`.
- Projeto: `Chiu Know`.
- Project ID/ref: `uskxabsodcnzlovuaurp`.
- Região: `sa-east-1` (São Paulo).
- Host conhecido: `db.uskxabsodcnzlovuaurp.supabase.co`.
- Esta conta/organização é SEPARADA da usada pelo Chiu Player.
- Até o estado registrado aqui, o desenvolvimento Android atual NÃO precisa de Supabase e nenhuma escrita de backend é necessária para o próximo passo.

## CHIU PLAYER — PROIBIDO DURANTE TRABALHOS DO CHIU KNOW?
- Organização do Chiu Player: `Chiu`.
- Organization ID conhecido: `nnrwosbnvdvzaoflwxlo`.
- Project ID/ref do CHIU PLAYER: `hpcbkvbrlwjnwlikmbfb`.
- Esse projeto NÃO pertence ao Chiu Know?.
- NUNCA executar migrations, SQL, deploy de Edge Functions, alterações de Auth/Storage, secrets, chaves ou qualquer escrita em `hpcbkvbrlwjnwlikmbfb` enquanto a frente atual for Chiu Know?.
- NUNCA compartilhar tabelas, Auth, Storage, Edge Functions, secrets ou credenciais entre os dois projetos.

### NOTA DE CONTEXTO SOBRE O CHIU PLAYER — NÃO CONFUNDIR COM CHIU KNOW?
Esta informação serve apenas para impedir interpretações erradas entre os projetos:
- No Chiu Player, o aplicativo gera atividade real no Supabase principalmente pela comunicação com o backend do próprio Chiu Player.
- No estado conhecido do Chiu Player, há chamadas à Edge Function `chiu-panel`; operações como `sync`, sincronização aparelho/listas, verificações de autorização e outras informações dependentes do painel podem gerar requisições reais ao Supabase do CHIU PLAYER.
- O Chiu Player também possui o workflow `supabase-health.yml`, configurado para realizar uma chamada automática duas vezes por dia para atividade/health check periódico.
- Portanto, não é necessário abrir manualmente o Chiu Player apenas para tentar gerar atividade periódica no Supabase do Chiu Player.
- NADA desta nota autoriza usar `hpcbkvbrlwjnwlikmbfb` no Chiu Know?. São backends totalmente separados.

# PROTOCOLO OBRIGATÓRIO ANTES DE QUALQUER ESCRITA NO SUPABASE
Nunca confiar apenas no chat, memória ou no fato de uma conexão ter funcionado anteriormente.

Quando algum passo FUTURO do Chiu Know? realmente exigir Supabase:
1. No ChatGPT: Configurações/Plugins -> Supabase -> menu de três pontos -> Reconectar.
2. Tocar em `Continuar em Supabase`.
3. Selecionar explicitamente a organização correta.
4. Autorizar o ChatGPT.
5. O ASSISTENTE deve primeiro fazer somente leituras `list_organizations` e `list_projects`.
6. Para CHIU KNOW?, só é permitido escrever se estiver confirmado: organização `Chiu Know`, organization ID `aeerqbmrwulxsawhjyvm`, projeto/ref `uskxabsodcnzlovuaurp`.
7. Se aparecer `hpcbkvbrlwjnwlikmbfb`, PARAR imediatamente: é o Chiu Player.
8. Se houver divergência, ambiguidade ou ausência de confirmação, NÃO fazer qualquer escrita.

## INSTRUÇÕES À CAMILA SE FOR NECESSÁRIO TROCAR A CONEXÃO
Camila não deve receber tarefas de banco, SQL, terminal, código ou resolução manual de conflitos. Se a conexão precisar ser trocada, pedir somente:
1. Abrir ChatGPT.
2. Ir em `Configurações` -> `Plugins` -> `Supabase`.
3. Tocar nos três pontos.
4. Tocar em `Reconectar` — NÃO em `Desinstalar`.
5. Tocar em `Continuar em Supabase`.
6. Selecionar/conferir a organização `Chiu Know`.
7. Tocar em `Authorize ChatGPT` / `Autorizar ChatGPT`.
8. Avisar quando terminar. Se a tela for diferente, pedir print e não mandar Camila adivinhar.
9. Depois disso, O ASSISTENTE valida organização/projeto por leitura antes de qualquer escrita.

Se futuramente for necessário voltar ao Chiu Player, o mesmo procedimento de Reconectar pode ser usado escolhendo a organização correta do Chiu Player, e o chat daquela frente deve validar o ID real antes de escrever.

## PRINCÍPIO FINAL DE SEGURANÇA
- Estado real do GitHub/Supabase vence documentação desatualizada.
- Não usar Supabase se o passo puder ser realizado localmente.
- Chiu Know? e Chiu Player são projetos, repositórios e backends independentes.
- `uskxabsodcnzlovuaurp` = CHIU KNOW?.
- `hpcbkvbrlwjnwlikmbfb` = CHIU PLAYER.
- Se houver dúvida entre esses IDs: PARAR, conferir, só então prosseguir.
