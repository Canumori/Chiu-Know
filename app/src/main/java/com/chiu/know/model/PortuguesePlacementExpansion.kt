package com.chiu.know.model

/**
 * Additional Portuguese placement items for the quality-first placement flow.
 *
 * These extend the starter bank to four distinct items per CEFR level without
 * replacing the already reviewed starter questions. The bank remains an
 * engineering placement instrument, not a psychometrically calibrated exam.
 */
val additionalPortuguesePlacementQuestions = listOf(
    PlacementQuestion("pt-a1-003", CefrLevel.A1, "Escolha a frase correta.", listOf("Ela mora no Rio.", "Ela morar no Rio.", "Ela moram no Rio.", "Ela moro no Rio."), 0),
    PlacementQuestion("pt-a1-004", CefrLevel.A1, "Vocês ___ café de manhã.", listOf("bebe", "bebem", "bebo", "beber"), 1),

    PlacementQuestion("pt-a2-003", CefrLevel.A2, "No fim de semana passado, eu ___ meus avós.", listOf("visito", "visitei", "visitarei", "visitava amanhã"), 1),
    PlacementQuestion("pt-a2-004", CefrLevel.A2, "Ela não veio à reunião porque ___ doente.", listOf("estava", "estará", "esteja amanhã", "estaria ontem"), 0),

    PlacementQuestion("pt-b1-003", CefrLevel.B1, "Se você ___ ajuda, pode me ligar.", listOf("precisasse ontem", "precisar", "precisará", "precisou amanhã"), 1),
    PlacementQuestion("pt-b1-004", CefrLevel.B1, "Quando eu era criança, ___ passar as férias na casa dos meus avós.", listOf("costumo", "costumava", "costumarei", "teria costumado"), 1),

    PlacementQuestion("pt-b2-003", CefrLevel.B2, "Mesmo que ele ___ amanhã, não conseguirá concluir tudo sozinho.", listOf("venha", "vem", "virá", "veio"), 0),
    PlacementQuestion("pt-b2-004", CefrLevel.B2, "O relatório deveria ___ antes de a decisão ter sido anunciada.", listOf("ser revisado", "revisar", "ter revisando", "será revisado"), 0),

    PlacementQuestion("pt-c1-003", CefrLevel.C1, "A proposta parece viável, ___ ainda dependa de ajustes importantes.", listOf("embora", "portanto", "logo", "por conseguinte"), 0),
    PlacementQuestion("pt-c1-004", CefrLevel.C1, "Os dados permitem levantar essa hipótese, mas não são suficientes para ___ uma relação causal.", listOf("estabelecer", "prescindir", "revogar", "dissimular"), 0),

    PlacementQuestion("pt-c2-003", CefrLevel.C2, "A resposta do porta-voz foi deliberadamente ___, evitando comprometer a instituição com uma interpretação única.", listOf("evasiva", "taxativa", "inequívoca", "literal"), 0),
    PlacementQuestion("pt-c2-004", CefrLevel.C2, "Escolha a formulação mais natural: A nova evidência ___ a interpretação que até então parecia consensual.", listOf("põe em xeque", "faz em dúvida", "coloca por incerto", "leva em suspeito"), 0)
)

val qualityPortuguesePlacementQuestions: List<PlacementQuestion> =
    starterPortuguesePlacementQuestions + additionalPortuguesePlacementQuestions
