package com.chiu.know.model

/**
 * First quality-first expansion slice for English placement.
 *
 * Kept separate from the currently wired starter bank until this slice is
 * reviewed by tests and CI. These items add variety instead of translating or
 * duplicating the original grammar-only patterns. They are still deterministic
 * multiple-choice placement evidence, not a complete multidimensional CEFR
 * assessment.
 */
val additionalEnglishPlacementQuestions = listOf(
    PlacementQuestion(
        "en-a1-003",
        CefrLevel.A1,
        "Choose the best reply: ‘What time is it?’",
        listOf("It’s half past three.", "I am three years.", "At Monday.", "Very time."),
        0
    ),
    PlacementQuestion(
        "en-a1-004",
        CefrLevel.A1,
        "Choose the correct option: I have ___ apple in my bag.",
        listOf("a", "an", "some", "any"),
        1
    ),
    PlacementQuestion(
        "en-a2-003",
        CefrLevel.A2,
        "Choose the best option: I’m looking for my keys. Have you seen ___?",
        listOf("it", "them", "their", "they"),
        1
    ),
    PlacementQuestion(
        "en-a2-004",
        CefrLevel.A2,
        "Choose the most natural sentence.",
        listOf(
            "I’ve never been to Canada.",
            "I never have been yesterday to Canada.",
            "I didn’t never go to Canada.",
            "I’m never being to Canada."
        ),
        0
    ),
    PlacementQuestion(
        "en-b1-003",
        CefrLevel.B1,
        "Choose the best option: The woman ___ lives next door is a doctor.",
        listOf("which", "whose", "who", "where"),
        2
    ),
    PlacementQuestion(
        "en-b1-004",
        CefrLevel.B1,
        "Tom isn’t answering his phone. He ___ be in a meeting, but I’m not sure.",
        listOf("must", "might", "can’t", "should have"),
        1
    ),
    PlacementQuestion(
        "en-b2-003",
        CefrLevel.B2,
        "Choose the best option: The proposal ___ by the committee before the final vote.",
        listOf("is reviewing", "will review", "will be reviewed", "has review"),
        2
    ),
    PlacementQuestion(
        "en-b2-004",
        CefrLevel.B2,
        "She said she ___ the report by Friday, but she later asked for more time.",
        listOf("will finish", "would finish", "finishes", "has finished"),
        1
    ),
    PlacementQuestion(
        "en-c1-003",
        CefrLevel.C1,
        "Choose the most appropriate academic wording: The small sample size ___ the generalizability of the findings.",
        listOf("may limit", "totally destroys", "proves false", "has no relation with"),
        0
    ),
    PlacementQuestion(
        "en-c1-004",
        CefrLevel.C1,
        "Choose the most natural option: Rarely ___ such a comprehensive analysis of the issue.",
        listOf("we see", "do we see", "we do see", "see we"),
        1
    ),
    PlacementQuestion(
        "en-c2-003",
        CefrLevel.C2,
        "Choose the option that best preserves the nuance: Her remarks were not openly hostile, but they were clearly ___.",
        listOf("barbed", "edible", "adjacent", "literal"),
        0
    ),
    PlacementQuestion(
        "en-c2-004",
        CefrLevel.C2,
        "Choose the most precise interpretation: ‘His support was qualified rather than wholehearted.’",
        listOf(
            "He supported it without any reservations.",
            "He supported it, but with reservations or limitations.",
            "He refused to discuss it.",
            "He misunderstood the proposal completely."
        ),
        1
    )
)

val qualityEnglishPlacementQuestions: List<PlacementQuestion> =
    starterEnglishPlacementQuestions + additionalEnglishPlacementQuestions
