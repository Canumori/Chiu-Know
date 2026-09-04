# CHIU KNOW? — POLÍTICA DO PLACEMENT COREANO

## Estado autoritativo — 2026-09-04

Este documento registra a decisão de produto mais recente e **substitui qualquer regra anterior que tratasse revisão humana externa como bloqueio absoluto** para o placement coreano.

## Objetivo do placement

O placement do CHIU KNOW? existe para orientar o percurso de aprendizagem dentro do aplicativo. Ele fornece uma **estimativa pedagógica de nível**, não uma certificação oficial, diploma, credencial profissional ou avaliação psicometricamente validada.

O objetivo do produto é ajudar a pessoa a aprender e ajustar conteúdo, dificuldade e revisões de maneira útil e conservadora. O aplicativo não deve sugerir que o resultado serve para fins acadêmicos, profissionais, migratórios ou regulatórios que exijam certificação formal.

## Revisão externa

Uma revisão humana independente por pessoa altamente proficiente/nativa em coreano, idealmente com experiência em ensino ou avaliação, continua sendo **fortemente desejável** porque acrescenta uma segunda opinião e pode detectar problemas que passaram pela revisão interna.

Porém, a ausência de revisor voluntário ou de recursos financeiros **não deve paralisar o projeto nem impedir indefinidamente a disponibilização do coreano**.

## Caminho sem revisor externo

Se não houver revisor externo disponível, o coreano poderá avançar somente depois de uma **segunda revisão interna por IA deliberadamente rigorosa e documentada**, separada da primeira revisão, cobrindo todas as 24 questões candidatas.

Essa revisão deve, no mínimo, verificar por item:

1. naturalidade e correção gramatical;
2. existência de uma única melhor resposta quando o formato exigir resposta única;
3. plausibilidade dos distratores, evitando alternativas absurdas que entreguem a resposta;
4. ausência de ambiguidade não intencional;
5. adequação de registro e contexto;
6. ausência de dependência cultural não explicada;
7. progressão relativa de dificuldade dentro do banco;
8. nos níveis altos, demanda real de nuance, inferência, relação lógica, precisão ou registro, e não apenas vocabulário raro;
9. consistência entre enunciado, resposta correta e explicação pedagógica futura;
10. inexistência de padrões mecânicos fáceis de explorar nas respostas.

Qualquer item duvidoso deve ser corrigido ou substituído e voltar aos testes automatizados e CI antes de eventual rollout.

## Salvaguardas obrigatórias

Mesmo após a revisão interna rigorosa:

- o resultado deve continuar sendo chamado de **estimativa de nível**;
- não alegar certificação CEFR oficial;
- não alegar validação psicométrica;
- não alegar equivalência automática com King Sejong Institute ou outra instituição;
- não usar o resultado como prova de proficiência para terceiros;
- CI verde comprova integridade técnica, não validade linguística ou psicométrica;
- se posteriormente surgir revisor humano qualificado, sua revisão deve ser incorporada como melhoria de qualidade, sem reescrever retroativamente o histórico.

## Estado técnico atual

O coreano permanece em `LEGACY_FOUNDATION` neste momento. A mudança desta política **não habilita automaticamente** `QUALITY_SESSION`. Antes de qualquer rollout, deve existir uma segunda revisão rigorosa documentada das 24 questões, correções necessárias, testes automatizados e CI verde.

## Relação com KOREAN_PLACEMENT_REVIEW.md

`KOREAN_PLACEMENT_REVIEW.md` continua útil como protocolo para um eventual voluntário humano. Seus campos em branco não bloqueiam mais indefinidamente o projeto. Se houver revisão externa, usar esse protocolo. Se não houver, seguir o caminho interno rigoroso definido neste documento.
