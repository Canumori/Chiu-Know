# CHIU KNOW? — CURRENT HANDOFF — 2026-09-13

This file is the compact authoritative operational handoff for the current `main` state. When older project-state blocks conflict with this file, use this file plus the real GitHub/Supabase state.

## Verified green base

- HEAD before this documentation commit: `399f9e39ee5544b2fec4e3df0770e8bf9aa39e23`
- Commit: `test: prove A1 basic request starter integration`
- Android CI #492, run `34766471914`: `COMPLETED / SUCCESS` on that exact SHA
- CI #492 passed unit tests, debug APK build, and artifact upload.

## Newly completed A1 basic-request slice

A minimal controlled A1 request function was added for the five supported languages EN/PT/ES/FR/KO.

Protected design:
- communicative target: a polite basic request for water;
- `ResponseType.REORDER`;
- no audio;
- no free speaking/writing claim;
- no new scheduler, queue, FSRS, mastery state, persistence format, Supabase resource or infrastructure change;
- one controlled activity per supported language;
- unsupported languages expose no content.

Gated commits:
1. `8559e3cbc30c0235dfb549a1125146bb8428a150` — isolated A1 basic-request content model; Android CI #489 SUCCESS.
2. `0ee5f6a430752f1d1ce245742e5fc582cb536c6c` — isolated contract tests across EN/PT/ES/FR/KO; Android CI #490 SUCCESS.
3. `d2679490597aa37d86d0f7d3dd27cd1cfe89fb4b` — minimal starter activation via `a1BasicRequestActivitiesFor(languageCode)` only; Android CI #491 SUCCESS.
4. `399f9e39ee5544b2fec4e3df0770e8bf9aa39e23` — integration test proving the request is present through `starterLearningActivitiesFor` in every supported language; Android CI #492 SUCCESS.

No regression was observed in the existing selector/scheduler contracts during this gated sequence.

## Voice work — current authoritative rule

The recent cast auditions were approved externally and recorded separately. Do NOT infer that approval authorizes adding those audio files to the APK or public GitHub.

Current explicit operational rule:
- do not add the newly approved external cast audio files to the APK or public repository unless the user explicitly changes this instruction;
- existing historical Chiu/Mia bundled samples are a separate already-integrated state and must not be confused with the newly approved external audition set;
- do not regenerate or alter approved cast directions unless the user asks;
- do not clone or impersonate protected public performers; named references define broad traits only.

Approved external audition set currently includes Barto, Cacá, Onça, Perry, Lara, Lena and Jurandir's user-owned reference state, as recorded in the voice handoff files. Chiu and Mia have their separately approved historical state.

## Product/engineering guardrails

- Do not restart or redesign the project from scratch.
- Real GitHub/Supabase state overrides stale documentation.
- Check exact HEAD and exact-SHA Android CI before every write.
- One reversible change at a time; wait for green CI before the next write.
- Preserve current persistence serialization, retry behavior, review-first priority, queue semantics, optional-practice non-persistence and observed-practice honesty.
- Do not create a second queue, scheduler, FSRS system or mastery model.
- Do not touch CHIU PLAYER resources.
- Avoid unrelated refactors.
- Product priority remains measurable language learning and retention rather than ornamental gamification.

## Next safe development method

Before adding more A1 content, audit the real current activity inventory against the product specification and recent tests. Only fill a communicative-function gap that is genuinely absent. Prefer a small isolated content slice, then tests, then starter integration, each behind its own green CI gate. Do not reuse stale historical TODOs blindly.
