# Korean Placement — Rigorous AI Audit (2026-09-04)

## Status and scope

This document records a second rigorous AI review of the 24 Korean placement questions in `PlacementTest.kt` and `KoreanPlacementExpansion.kt`, together with the corrections and engineering checks that followed that review.

This is **not** human linguistic validation, psychometric validation, official CEFR certification, or a claim of equivalence with Sejong/other Korean curricula. The A1–C2 labels remain internal placement targets for pedagogical routing inside CHIU KNOW?.

Human external review remains desirable when available, but absence of a free human reviewer is not treated as a blocker. The project therefore uses documented, conservative AI review plus automated engineering checks and CI.

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

The original 24-item candidate bank had a severe answer-position concentration: 21 correct answers at index 0, 3 at index 1, and none at indices 2 or 3. That was an engineering flaw rather than evidence about linguistic quality. The answer positions were redistributed before the later distractor/content passes.

The review then proceeded in small CI-gated blocks: starter A1/A2, additional A1/A2, starter B1/B2, additional B1/B2, starter C1/C2, and additional C1/C2. Ambiguous or weak items were rewritten or had distractors strengthened; unchanged items were explicitly re-evaluated rather than assumed valid.

A subsequent audit found two remaining ambiguities in the starter bank. Both were corrected before rollout:

- `ko-a1-001` was changed from an ambiguous copula completion to `저___ 학생입니다.` with options `는`, `를`, `에`, `와` and correct index `0`. This now tests the topic particle with one intended answer.
- `ko-a2-002` was changed to `밖에 나가려는 친구에게 말합니다. "지금 비가 오니까 우산을 ___."` with options `챙겼어요`, `챙길 거예요`, `챙기고 있어요`, `챙기세요` and correct index `3`. The explicit addressee/advice context makes the imperative the intended answer.

Those corrections passed Android CI before Korean placement was promoted.

## Current 24-item disposition

### A1

- `ko-a1-001` — **REWRITTEN/IMPROVED**. Topic-particle discrimination in `저___ 학생입니다.`; the previous copula-completion ambiguity was removed.
- `ko-a1-002` — **IMPROVED**. Self-identification item with controlled formal-polite target and non-equivalent distractors.
- `ko-a1-003` — **IMPROVED**. Object-particle discrimination in a highly familiar daily-routine sentence; appropriate low-load A1 evidence.
- `ko-a1-004` — **IMPROVED**. Location particle in an existential construction; basic syntax/particle evidence rather than vocabulary association.

### A2

- `ko-a2-001` — **KEEP**. Explicit past-time adverb makes the past meeting form the intended answer.
- `ko-a2-002` — **REWRITTEN/IMPROVED**. Explicit friend-going-out advice context now makes `챙기세요` the intended umbrella instruction rather than leaving several natural completions available.
- `ko-a2-003` — **KEEP**. `만나서` expresses the sequential/connected action naturally in the supplied past context; alternatives test nearby connective/intent forms.
- `ko-a2-004` — **IMPROVED**. Habitual study context tests present habitual action against past/future/desire forms.

### B1

- `ko-b1-001` — **IMPROVED**. Conditional weekend context and invitation-like completion discriminate present/future conversational use from past/progressive/near-miss forms.
- `ko-b1-002` — **IMPROVED**. `-게 되면` context supports a future opportunity plus desire/experience construction without reusing an already-completed-experience reading.
- `ko-b1-003` — **IMPROVED**. Experience construction `가 본 적이 있어요` retained with structurally closer distractors.
- `ko-b1-004` — **KEEP**. Visible cloudy-sky evidence naturally licenses inference `올 것 같아요`; alternatives express incompatible relations.

### B2

- `ko-b2-001` — **IMPROVED**. Arrival-time frame requires a pre-existing sleeping state (`잠들어 있었어요`) rather than desire/future/progressive alternatives.
- `ko-b2-002` — **IMPROVED**. Counterfactual `-았/었더라면` competes with closer negative/future/modal alternatives while preserving the intended unreal-past consequence.
- `ko-b2-003` — **KEEP**. `아무리 ... -더라도` tests concessive relation naturally and compactly.
- `ko-b2-004` — **REWRITTEN/IMPROVED**. Replaced a redundant counterfactual with `-기는커녕`, improving construct diversity within B2.

### C1

- `ko-c1-001` — **REWRITTEN/IMPROVED**. The earlier basic `-지만` contrast was too easy for the intended advanced band; current form requires finer discourse/grammatical discrimination.
- `ko-c1-002` — **IMPROVED**. Formal contrast/qualification emphasizes discourse relation rather than a trivial connector cue.
- `ko-c1-003` — **KEEP**. Formal evaluative construction `...것에 불과하다` tests precise collocation/stance.
- `ko-c1-004` — **KEEP**. `...해야 하는 셈이다` requires interpretation of an inferred practical consequence and is suitably nuanced.

### C2

- `ko-c2-001` — **REWRITTEN/IMPROVED**. Advanced formal item relies on nuanced rhetorical/collocational interpretation rather than a single obvious verb-object pair.
- `ko-c2-002` — **REWRITTEN/IMPROVED**. Deliberate ambiguity is tested through more competitive formal wording rather than an obvious antonym set.
- `ko-c2-003` — **KEEP**. `선뜻 받아들이기 어렵다` is a natural advanced adverbial collocation whose interpretation depends on the preceding qualification.
- `ko-c2-004` — **KEEP**. Formal committee-language item requires understanding the rhetorical consequence `핵심 쟁점을 흐리는 결과를 낳았다`.

## Bank-level conclusion

All 24 Korean placement items have received the documented second AI pass. The bank is materially stronger than the original candidate set: the answer-position pattern was removed, elementary distractors were strengthened, repeated constructs were reduced, advanced items were made less mechanically solvable, and the two remaining A1/A2 ambiguities were explicitly corrected.

This audit does **not** establish psychometric difficulty thresholds or official CEFR mapping. CI green demonstrates technical integrity, not human linguistic validation or psychometric validity.

## Engineering guardrails now in place

Automated tests enforce the Korean bank's structural integrity, including:

1. exactly 24 Korean placement questions;
2. exactly 4 questions for each internal A1–C2 band;
3. unique question IDs and prompts;
4. exactly 4 options per question;
5. every `correctIndex` within option bounds;
6. nonblank, nonduplicated option text;
7. answer-position distribution guarded against pathological concentration;
8. candidate-session traversal terminating within the placement quality policy;
9. completed candidate sessions requiring sufficient evidence and a real decision;
10. final decisions constrained to the established one-band confirmation rule.

These checks protect bank/session integrity but do not constitute linguistic or psychometric validation.

## Production rollout status

After the rigorous AI review, the ambiguity corrections, the bank/session guardrails, and green CI, Korean placement was promoted in a separate implementation step to `QUALITY_SESSION` using the 24-question reviewed bank.

That promotion does not change the interpretation of the result: CHIU KNOW? provides a **pedagogical level estimate**, not an official certificate, credential, psychometric diagnosis, or institutional equivalence.

A later qualified human review may still be incorporated as a quality improvement without rewriting the historical record of this AI-reviewed rollout.
