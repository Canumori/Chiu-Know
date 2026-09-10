# CHIU KNOW? — RESEARCH BASELINE

## PROJETOS/REFERÊNCIAS JÁ IDENTIFICADOS
- Babblr — referência de plataforma; atenção a AGPL/licença comercial.
- LexiLearn — Flutter; estudar conceitos, não assumir compatibilidade arquitetural.
- FreeLingo.
- LibreLingo — AGPL; licenças de conteúdo devem ser verificadas separadamente.
- Discens — MIT.
- android-fsrs / awesome-fsrs — referência permissiva para spaced repetition/FSRS.
- LearnLift AI Android — Kotlin/Compose.
- whisper.cpp — MIT; candidato para reconhecimento de fala offline.
- english.now.
- HSK Nest — AGPL.
- Language Nerd Tools — MIT.
- Learning Buddy AI.
- Concerto — referência em testes adaptativos.
- adaptivetesting — referência CAT/IRT.
- Open Vocabulary Level Test.
- UniversalCEFR subset.

## CONCLUSÕES DE ARQUITETURA
- Preferência: Kotlin + Jetpack Compose para Android nativo.
- Separar motor de avaliação de proficiência do LLM.
- Evoluir o placement test para CAT/IRT/Bayes com banco de itens calibrado.
- FSRS para revisão espaçada.
- ASR e pronúncia devem ser módulos distintos.
- Internacionalização e separação interface/idioma-alvo desde o primeiro build.

## LICENÇAS
Não copiar/incorporar código AGPL em um aplicativo proprietário sem decisão explícita de aceitar suas obrigações. Projetos AGPL podem ser estudados como referência arquitetural e conceitos podem ser reimplementados de forma independente. Preferir MIT/BSD/Apache quando houver alternativa. Licença do software e licença do conteúdo/dataset são verificações separadas.

## BACKEND
Supabase foi mantido por oferecer banco, Auth, Storage, Functions e Realtime em um conjunto administrável diretamente pelo ChatGPT. Chiu Know? usa conta/organização/projeto completamente separados do Chiu Player. Ver `PROJECT_STATE.md` para IDs e protocolo de segurança.


## FSRS / LICENÇAS — VERIFICAÇÃO 2026-09-02
- `open-spaced-repetition/android-fsrs`: MIT, mas último push de código em 2023, estrutura de aplicativo de exemplo e não uma dependência publicada pronta; não incorporado.
- `TheRockYT/FsrsKt`: FSRS-6 Kotlin Multiplatform publicado, porém GPL-3.0; não incorporado porque restringiria a liberdade futura do Chiu Know?.
- Decisão da usuária: preservar liberdade do projeto e desenvolver solução própria; não usar dependência GPL.
- Fórmula autoritativa consultada: wiki atual `open-spaced-repetition/awesome-fsrs/The-Algorithm`, FSRS-6 com 21 parâmetros.
- Implementação interna usa somente observações honestas existentes: incorreto→Again, correto→Good. Não fabrica ratings Hard/Easy.
- O repositório Chiu Know? continua público para minutos ilimitados do GitHub Actions. Não há arquivo `LICENSE`; publicidade do repositório e licença de reutilização são conceitos diferentes.
- Não adicionar licença, mudar visibilidade ou incorporar código de terceiros por suposição.
