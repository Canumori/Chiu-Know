# Korean Placement — Rigorous AI Audit (2026-09-04)

## Status and scope

This document records a second rigorous AI review of the 24 candidate Korean placement questions currently in `PlacementTest.kt` and `KoreanPlacementExpansion.kt`.

This is **not** human linguistic validation, psychometric validation, official CEFR certification, or a claim of equivalence with Sejong/other Korean curricula. Korean remains behind its existing production quality gate (`LEGACY_FOUNDATION`) and is **not** promoted automatically to `QUALITY_SESSION` by this audit.

Human external review remains desirable when available, but absence of a free human reviewer is not treated as a blocker for continued internal development. The project therefore uses documented, conservative AI review plus automated engineering checks.

## Review criteria

Every item was reviewed for:

- grammatical well-formedness;
- naturalness in contemporary Korean;
- register and contextual coherence;
- exactly one intended answer in the supplied context;
- plausibility and competitiveness of distractors;
- avoidance of accidental semantic alternatives;
- avoidance of mechanical answer-position clues;
- useful progression from elementary form recognition toward advanced discourse/collocation nuance;
- duplication/redundancy inside the 24-item bank;
- cultural neutrality and absence of unnecessary cultural knowledge requirements.

## Engineering history relevant to the audit

The original 24-item candidate bank had a severe answer-position concentration: 21 correct answers at index 0, 3 at index 1, and none at indices 2 or 3. That was an engineering flaw rather than evidence about linguistic quality. The answer positions were subsequently redistributed before the distractor/content passes.

The review then proceeded in small CI-gated blocks: starter A1/A2, additional A1/A2, starter B1/B2, additional B1/B2, starter C1/C2, and additional C1/C2. Ambiguous or weak items were rewritten or had distractors strengthened; unchanged items were explicitly re-evaluated rather than assumed valid.

## Current 24-item disposition

### A1

- `ko-a1-001` — **IMPROVED**. Basic copula item; distractors now contrast sentence type/tense rather than relying on unrelated vocabulary. Retain only while its supplied context preserves a single intended formal-polite completion.
- `ko-a1-002` — **IMPROVED**. Parallel self-identification item with controlled formal-polite target and non-equivalent distractors.
- `ko-a1-003` — **IMPROVED**. Object-particle discrimination in a highly familiar daily-routine sentence; appropriate low-load A1 evidence.
- `ko-a1-004` — **IMPROVED**. Location particle in an existential construction; basic syntax/particle evidence rather than vocabulary association.

### A2

- `ko-a2-001` — **KEEP**. Explicit past-time adverb makes the past meeting form the intended answer.
- `ko-a2-002` — **IMPROVED**. Rain context targets an appropriate umbrella instruction; must remain contextualized enough that the imperative is uniquely intended.
- `ko-a2-003` — **KEEP**. `만나서` expresses the sequential/connected action naturally in the supplied past context; alternatives test nearby connective/intent forms.
- `ko-a2-004` — **IMPROVED**. Habitual study context tests present habitual action against past/future/desire forms.

### B1

- `ko-b1-001` — **IMPROVED**. Conditional weekend context and invitation-like completion discriminate present/future conversational use from past/progressive/near-miss forms.
- `ko-b1-002` — **IMPROVED**. `-게 되면` context supports a future opportunity plus desire/experience construction without reusing an already-completed-experience reading.
- `ko-b1-003` — **IMPROVED**. Experience construction `가 본 적이 있어요` retained with structurally closer distractors.
- `ko-b1-004` — **KEEP**. Visible cloudy-sky evidence naturally licenses inference `올 것 같아요`; alternatives express incompatible relations.

### B2

- `ko-b2-001` — **IMPROVED**. Arrival-time frame requires a pre-existing sleeping state (`잠들어 있었어요`) rather than desire/future/progressive alternatives.
- `ko-b2-002` — **IMPROVED**. Counterfactual `-았/었더라면` now competes with closer negative/future/modal alternatives while preserving the intended unreal-past consequence.
- `ko-b2-003` — **KEEP**. `아무리 ... -더라도` tests concessive relation naturally and compactly.
- `ko-b2-004` — **REWRITTEN/IMPROVED**. Replaced a redundant counterfactual with `-기는커녕`, improving construct diversity within B2.

### C1

- `ko-c1-001` — **REWRITTEN/IMPROVED**. The earlier basic `-지만` contrast was too easy for the intended advanced band; current form requires finer discourse/grammatical discrimination.
- `ko-c1-002` — **IMPROVED**. Formal contrast/qualification now emphasizes discourse relation rather than a trivial connector cue.
- `ko-c1-003` — **KEEP**. Formal evaluative construction `...것에 불과하다` tests precise collocation/stance.
- `ko-c1-004` — **KEEP**. `...해야 하는 셈이다` requires interpretation of an inferred practical consequence and is suitably nuanced.

### C2

- `ko-c2-001` — **REWRITTEN/IMPROVED**. Advanced formal item now relies on nuanced rhetorical/collocational interpretation rather than a single obvious verb-object pair.
- `ko-c2-002` — **REWRITTEN/IMPROVED**. Deliberate ambiguity is tested through more competitive formal wording rather than an obvious antonym set.
- `ko-c2-003` — **KEEP**. `선뜻 받아들이기 어렵다` is a natural advanced adverbial collocation whose interpretation depends on the preceding qualification.
- `ko-c2-004` — **KEEP**. Formal committee-language item requires understanding the rhetorical consequence `핵심 쟁점을 흐리는 결과를 낳았다`.

## Bank-level conclusion

All 24 current candidate items have now received a documented second AI pass. The bank is materially stronger than the original candidate set: the answer-position pattern was removed, elementary distractors were strengthened, repeated constructs were reduced, and advanced items were made less mechanically solvable.

This audit does **not** establish psychometric difficulty thresholds or official CEFR mapping. The A1–C2 labels remain internal placement targets. Production promotion requires the project's separate quality policy and engineering gates.

## Required engineering guardrails

Automated tests should enforce at minimum:

1. exactly 24 Korean candidate placement questions;
2. exactly 4 questions for each internal A1–C2 band;
3. unique question IDs;
4. exactly 4 options per question;
5. every `correctIndex` within option bounds;
6. no pathological concentration of correct-answer positions.

These checks protect bank integrity but do not constitute linguistic or psychometric validation.

## Next safe step

Add the bank-integrity tests above in a separate small commit, wait for CI green, and only then decide whether Korean placement is ready for the next internal integration stage. Do not promote Korean automatically to `QUALITY_SESSION` solely because this audit and the engineering tests pass.
