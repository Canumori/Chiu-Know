package com.chiu.know.model

/**
 * Additional Spanish placement items for the quality-first placement flow.
 *
 * These extend the starter bank to four distinct items per CEFR level without
 * replacing the starter questions. The bank remains an engineering placement
 * instrument, not a psychometrically calibrated exam.
 */
val additionalSpanishPlacementQuestions = listOf(
    PlacementQuestion("es-a1-003", CefrLevel.A1, "Elige la frase correcta.", listOf("Ella vive en Madrid.", "Ella vivir en Madrid.", "Ella viven en Madrid.", "Ella vivo en Madrid."), 0),
    PlacementQuestion("es-a1-004", CefrLevel.A1, "Ustedes ___ café por la mañana.", listOf("bebe", "beben", "bebo", "beber"), 1),

    PlacementQuestion("es-a2-003", CefrLevel.A2, "El fin de semana pasado, yo ___ a mis abuelos.", listOf("visito", "visité", "visitaré", "visitaba mañana"), 1),
    PlacementQuestion("es-a2-004", CefrLevel.A2, "No vino a la reunión porque ___ enferma.", listOf("estaba", "estará", "esté mañana", "estaría ayer"), 0),

    PlacementQuestion("es-b1-003", CefrLevel.B1, "Si ___ ayuda, puedes llamarme.", listOf("necesitaras ayer", "necesitas", "necesitarás", "necesitaste mañana"), 1),
    PlacementQuestion("es-b1-004", CefrLevel.B1, "Cuando era niña, ___ pasar las vacaciones en casa de mis abuelos.", listOf("suelo", "solía", "soleré", "habría solido"), 1),

    PlacementQuestion("es-b2-003", CefrLevel.B2, "Aunque él ___ mañana, no podrá terminarlo todo solo.", listOf("venga", "viene", "vendrá", "vino"), 0),
    PlacementQuestion("es-b2-004", CefrLevel.B2, "El informe debería ___ antes de que se anunciara la decisión.", listOf("haber sido revisado", "revisar", "haber revisando", "será revisado"), 0),

    PlacementQuestion("es-c1-003", CefrLevel.C1, "La propuesta parece viable, ___ todavía dependa de ajustes importantes.", listOf("aunque", "por lo tanto", "así que", "por consiguiente"), 0),
    PlacementQuestion("es-c1-004", CefrLevel.C1, "Los datos permiten plantear esa hipótesis, pero no bastan para ___ una relación causal.", listOf("establecer", "prescindir", "derogar", "disimular"), 0),

    PlacementQuestion("es-c2-003", CefrLevel.C2, "La respuesta del portavoz fue deliberadamente ___, evitando comprometer a la institución con una única interpretación.", listOf("evasiva", "tajante", "inequívoca", "literal"), 0),
    PlacementQuestion("es-c2-004", CefrLevel.C2, "Elige la formulación más natural: La nueva evidencia ___ la interpretación que hasta entonces parecía consensuada.", listOf("pone en entredicho", "hace en duda", "coloca por incierta", "lleva en sospecha"), 0)
)

val qualitySpanishPlacementQuestions: List<PlacementQuestion> =
    starterSpanishPlacementQuestions + additionalSpanishPlacementQuestions
