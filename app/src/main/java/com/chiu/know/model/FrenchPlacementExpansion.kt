package com.chiu.know.model

/**
 * Additional French placement items for the quality-first placement flow.
 *
 * These extend the starter bank to four distinct items per CEFR level without
 * replacing the starter questions. The bank remains an engineering placement
 * instrument, not a psychometrically calibrated exam.
 */
val additionalFrenchPlacementQuestions = listOf(
    PlacementQuestion("fr-a1-003", CefrLevel.A1, "Elle ___ à Lyon.", listOf("habite", "habites", "habitons", "habiter"), 0),
    PlacementQuestion("fr-a1-004", CefrLevel.A1, "Vous ___ français ?", listOf("parlez", "parle", "parlons", "parler"), 0),

    PlacementQuestion("fr-a2-003", CefrLevel.A2, "La semaine dernière, j'___ mes grands-parents.", listOf("ai visité", "visite", "visiterai", "visitais demain"), 0),
    PlacementQuestion("fr-a2-004", CefrLevel.A2, "Il n'est pas venu à la réunion parce qu'il ___ malade.", listOf("était", "sera", "soit demain", "serait hier"), 0),

    PlacementQuestion("fr-b1-003", CefrLevel.B1, "Si tu ___ d'aide, appelle-moi.", listOf("as besoin", "auras besoin hier", "avais besoin demain", "avoir besoin"), 0),
    PlacementQuestion("fr-b1-004", CefrLevel.B1, "Quand j'étais enfant, je ___ mes vacances chez mes grands-parents.", listOf("passe", "passais", "passerai", "aurais passé demain"), 1),

    PlacementQuestion("fr-b2-003", CefrLevel.B2, "Quoiqu'il ___ demain, il ne pourra pas tout terminer seul.", listOf("vienne", "vient", "viendra", "est venu"), 0),
    PlacementQuestion("fr-b2-004", CefrLevel.B2, "Le rapport aurait dû ___ avant l'annonce de la décision.", listOf("être révisé", "réviser", "avoir révisant", "sera révisé"), 0),

    PlacementQuestion("fr-c1-003", CefrLevel.C1, "Cette hypothèse reste plausible, ___ les données actuelles ne permettent pas de l'établir.", listOf("même si", "donc", "par conséquent", "de sorte que"), 0),
    PlacementQuestion("fr-c1-004", CefrLevel.C1, "Les données sont compatibles avec cette explication sans pour autant la ___.", listOf("démontrer", "abroger", "omettre", "éluder"), 0),

    PlacementQuestion("fr-c2-003", CefrLevel.C2, "Sa réponse est restée délibérément ___, afin de ne privilégier aucune interprétation.", listOf("évasive", "catégorique", "univoque", "littérale"), 0),
    PlacementQuestion("fr-c2-004", CefrLevel.C2, "Choisissez la formulation la plus naturelle : Les nouveaux éléments ___ l'interprétation jusque-là tenue pour acquise.", listOf("remettent en cause", "font en doute", "mettent par incertaine", "portent en soupçon"), 0)
)

val qualityFrenchPlacementQuestions: List<PlacementQuestion> =
    starterFrenchPlacementQuestions + additionalFrenchPlacementQuestions
