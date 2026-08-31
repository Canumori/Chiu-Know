# CHIU KNOW? — PROJECT STATE

## REGRA AUTORITATIVA DE ISOLAMENTO DO SUPABASE — 2026-08-31

Esta regra é ABSOLUTA e deve ser conferida antes de qualquer operação de backend.

### CHIU KNOW?
- Repositório: `Canumori/Chiu-Know`
- Supabase dedicado: organização `Chiu Know`
- Supabase project ID/ref: `uskxabsodcnzlovuaurp`
- Região: `sa-east-1` (São Paulo)
- Conta/organização Supabase é separada da usada pelo Chiu Player.

### CHIU PLAYER — PROIBIDO DURANTE TRABALHOS DO CHIU KNOW?
- O Supabase project ID/ref `hpcbkvbrlwjnwlikmbfb` pertence ao CHIU PLAYER.
- NUNCA executar migrations, SQL, deploy de Edge Functions, alterações de Auth/Storage, chaves ou qualquer escrita nesse ID enquanto a frente atual for Chiu Know?.
- Não compartilhar tabelas, Auth, Storage, Edge Functions, secrets ou credenciais entre Chiu Know? e Chiu Player.

## PROTOCOLO OBRIGATÓRIO AO TROCAR A CONEXÃO SUPABASE NO CHATGPT
O plugin Supabase do ChatGPT trabalha com a organização autorizada no momento. Para mudar entre os projetos:
1. No ChatGPT: Configurações/Plugins -> Supabase -> menu de três pontos -> Reconectar.
2. Tocar em `Continuar em Supabase`.
3. No Supabase, selecionar explicitamente a organização correta para a frente de trabalho.
4. Autorizar o ChatGPT.
5. ANTES DE QUALQUER ESCRITA, fazer somente leitura (`list_organizations` e `list_projects`) e conferir nome + project ID/ref.
6. Para CHIU KNOW?, só prosseguir se aparecer organização `Chiu Know` e project ID/ref `uskxabsodcnzlovuaurp`.
7. Se aparecer `hpcbkvbrlwjnwlikmbfb`, PARAR: é o Chiu Player.
8. Se houver qualquer divergência ou dúvida, não escrever nada no Supabase até a conexão ser corrigida.

## PRINCÍPIO DE SEGURANÇA
Nunca assumir que a conexão do plugin continua na conta certa porque foi usada anteriormente. Toda sessão que pretenda escrever no Supabase deve primeiro validar o projeto real por leitura. O estado real do Supabase vence memória, chat e documentação desatualizada.
