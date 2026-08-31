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

## INSTRUÇÕES QUE O CHAT DEVE DAR À CAMILA QUANDO FOR NECESSÁRIO TROCAR PARA O CHIU KNOW?
Camila não deve receber instruções técnicas de banco, terminal ou código. Se a conexão estiver no Chiu Player e o trabalho exigir Supabase do Chiu Know?, o assistente deve pedir SOMENTE estes passos, um de cada vez se necessário:
1. Abrir o aplicativo ChatGPT.
2. Ir em `Configurações` -> `Plugins` -> `Supabase`.
3. Tocar nos três pontos no canto superior direito.
4. Tocar em `Reconectar` — NÃO tocar em `Desinstalar`.
5. Na tela `Fazer conexão com Supabase`, tocar em `Continuar em Supabase`.
6. Na página de autorização do Supabase, conferir/selecionar a organização `Chiu Know`.
7. Tocar em `Authorize ChatGPT` / `Autorizar ChatGPT`.
8. Avisar ao assistente quando concluir. Se a tela for diferente ou houver dúvida, pedir um print e NÃO mandar Camila adivinhar.
9. Depois disso, O ASSISTENTE — não Camila — deve executar leituras `list_organizations` e `list_projects` e confirmar que vê `Chiu Know` / `uskxabsodcnzlovuaurp` antes de qualquer escrita.

Se futuramente for necessário voltar ao Chiu Player, explicar que o mesmo procedimento de Reconectar é usado, mas escolhendo a organização/conta do Chiu Player; o chat do Chiu Player deve conferir o ID real antes de escrever.

## PRINCÍPIO DE SEGURANÇA
Nunca assumir que a conexão do plugin continua na conta certa porque foi usada anteriormente. Toda sessão que pretenda escrever no Supabase deve primeiro validar o projeto real por leitura. O estado real do Supabase vence memória, chat e documentação desatualizada.
