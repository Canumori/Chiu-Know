# CHIU KNOW? — PROJECT STATE

## ATUALIZAÇÃO AUTORITATIVA — 2026-09-01 — ANDROID, IDIOMAS, PERSISTÊNCIA, PLACEMENT POR IDIOMA E NÚCLEO ADAPTATIVO TESTADO

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
- AppCompat `1.7.0`.
- DataStore Preferences `1.1.7`.
- JUnit `4.13.2` somente em `testImplementation` para testes locais; não entra no app final.

### FLUXO IMPLEMENTADO
O aplicativo possui o fluxo local `LANGUAGE_SELECTION → PLACEMENT_INTRO → PLACEMENT_TEST → PLACEMENT_RESULT`.

O placement visual atual ainda é o fluxo seguro de 6 perguntas:
- bancos-protótipo separados para Português, English, Español, Français e 한국어;
- uma pergunta por faixa A1–C2;
- banco escolhido pelo idioma-alvo;
- resultado apresentado explicitamente como estimativa/protótipo, não certificação oficial CEFR;
- permite tentar novamente e alterar idiomas.

O novo núcleo adaptativo existe e já foi testado separadamente, mas AINDA NÃO está ligado à interface.

### IDIOMAS E PERSISTÊNCIA
Idiomas de interface e alvo: Português (`pt`), English (`en`), Español (`es`), Français (`fr`) e 한국어 (`ko`).
A interface usa recursos traduzidos e `AppCompatDelegate.setApplicationLocales(...)`.
As escolhas `interface_language_code` e `target_language_code` são persistidas localmente com DataStore. Nenhuma dessas etapas usa Supabase.

### BANCOS DE PLACEMENT POR IDIOMA — VALIDADO
- Commit `1428ccbbafbb4ed3cdf44bc3c6c745e960f4c82b`: bancos iniciais por idioma.
- Run #18 / `33449564848`: **SUCCESS**.
- Commit `49f7310001933445000dd08b0fa03796680a4205`: seleção do banco pelo idioma-alvo.
- Run #19 / `33449597226`: **SUCCESS**.

### NÚCLEO ADAPTATIVO LOCAL — TESTADO E VALIDADO, AINDA NÃO LIGADO À UI
- Commit `2539ef3bf6fdbe8a532df8d26ccefa18b3e3b3cf`: `feat: add local adaptive placement engine`.
- Arquivo: `app/src/main/java/com/chiu/know/model/AdaptivePlacement.kt`.
- Inicia em B1, mantém limites A1–C2 e estreita o intervalo conforme resposta correta/incorreta.
- Run #21 / `33457791947`: **FAILURE** localizada por `firstIndex` inexistente; não usar esse commit isoladamente como baseline.
- Commit `d6153bdfe929690266fd65a2bc6af08b4f6d727c`: correção mínima para `CEFR_LEVELS.indices.first`.
- Run #22 / `33458221953`: **SUCCESS**.
- Commit `3709b997dae8101c4cec17b338ddf2b1d75fc3e7`: adiciona JUnit apenas em `testImplementation`.
- Commit `29edf362a478e87c8d6c81ec9b1e6203b576faa9`: adiciona `AdaptivePlacementTest.kt` cobrindo início em B1, caminho todo correto até C2, caminho todo incorreto até A1 e estabilidade de estado finalizado.
- Commit `517d7975da49f96c4970cddf49ba28a5e994b7ea`: CI passa a executar `:app:testDebugUnitTest` antes do build.
- Android CI run #26 / `33458686503`, job `99704030435`: **SUCCESS COMPLETO**. Unit tests, build debug APK e upload do APK passaram.
- Portanto a base lógica adaptativa está compilando e com seus caminhos básicos automatizados validados.
- Isso NÃO constitui modelo psicométrico calibrado nem certificação CEFR.

### CI PRINCIPAL CONFIRMADO
- #5 / `33441495451`: SUCCESS — primeiro fluxo local.
- #8 / `33444348819`: SUCCESS — idioma da interface.
- #14 / `33445288797`: SUCCESS — fluxo multilíngue.
- #15 / `33446164837`: SUCCESS — DataStore dependency.
- #18 / `33449564848`: SUCCESS — bancos por idioma.
- #19 / `33449597226`: SUCCESS — banco conforme idioma-alvo.
- #21 / `33457791947`: FAILURE localizada e corrigida.
- #22 / `33458221953`: SUCCESS — núcleo adaptativo compilando.
- #26 / `33458686503`: SUCCESS — testes unitários + APK.

### PRÓXIMO PASSO EXATO
1. Não mexer no Supabase.
2. Integrar o núcleo adaptativo à interface em UMA mudança pequena, isolada e reversível.
3. Preservar seleção de idioma, DataStore, voltar/tentar novamente e aviso de protótipo.
4. Usar as perguntas existentes do banco do idioma-alvo, selecionando a pergunta correspondente ao `currentLevel` pedido pelo motor adaptativo.
5. O resultado deve vir do estado adaptativo, não da antiga proporção fixa de 6 respostas.
6. Não ampliar bancos nem fazer calibração nesta mesma mudança.
7. Manter o CI executando testes unitários antes do APK e validar a integração antes de qualquer avanço posterior.
8. Continuar chamando o resultado de estimativa/protótipo até existir validação adequada.

## ARTE / IDENTIDADE VISUAL
- Nenhuma arte ou mascote deve ser gerada, redesenhada ou substituída por iniciativa do assistente.
- `VISUAL_BIBLE.md` é obrigatória para qualquer trabalho visual.
- Chiu do logo = cabeça fotorealista aprovada do Chihuahua branco com cabelo castanho; reutilizar o master aprovado quando identidade exata for necessária.
- Chiu personagem do universo = cachorro cartoon amarelo aprovado; nunca misturar os dois.

# REGRA ABSOLUTA DE ISOLAMENTO DO SUPABASE

## CHIU KNOW? — ÚNICO SUPABASE PERMITIDO
- Repositório: `Canumori/Chiu-Know`.
- Organização: `Chiu Know`.
- Organization ID: `aeerqbmrwulxsawhjyvm`.
- Project ID/ref: `uskxabsodcnzlovuaurp`.
- Região: `sa-east-1`.
- Host: `db.uskxabsodcnzlovuaurp.supabase.co`.
- O desenvolvimento Android atual NÃO precisa de Supabase.

## CHIU PLAYER — PROIBIDO DURANTE TRABALHOS DO CHIU KNOW?
- Organização: `Chiu`.
- Organization ID: `nnrwosbnvdvzaoflwxlo`.
- Project ID/ref: `hpcbkvbrlwjnwlikmbfb`.
- NUNCA executar migrations, SQL, Edge Functions, Auth/Storage, secrets, chaves ou qualquer escrita nesse projeto durante trabalhos do Chiu Know?.
- Nunca compartilhar tabelas, Auth, Storage, Edge Functions, secrets ou credenciais entre os dois produtos.

## PROTOCOLO ANTES DE QUALQUER ESCRITA FUTURA NO SUPABASE
1. Só usar Supabase se o passo realmente exigir backend.
2. Reconectar o plugin à organização correta quando necessário.
3. O assistente deve primeiro fazer apenas leituras de organizações/projetos.
4. Para Chiu Know?, só escrever após confirmar `uskxabsodcnzlovuaurp` na organização `Chiu Know` (`aeerqbmrwulxsawhjyvm`).
5. Se aparecer `hpcbkvbrlwjnwlikmbfb`, PARAR: é o Chiu Player.
6. Camila não deve receber tarefas de SQL, terminal, código ou resolução manual de conflitos.

## PRINCÍPIO FINAL DE SEGURANÇA
- Estado real do GitHub/Supabase vence documentação desatualizada.
- Não usar Supabase se o passo puder ser local.
- `uskxabsodcnzlovuaurp` = CHIU KNOW?.
- `hpcbkvbrlwjnwlikmbfb` = CHIU PLAYER.
- Em dúvida: parar e conferir antes de escrever.
