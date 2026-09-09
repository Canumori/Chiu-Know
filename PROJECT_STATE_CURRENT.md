# CHIU KNOW? — CURRENT PROJECT STATE

## AUTORITATIVO — 2026-09-08 — MAIN GREEN THROUGH MULTIPLE-CHOICE RETRY HARDENING, CI #389

This is the compact operational checkpoint. Historical `PROJECT_STATE.md` is too large for safe full round-trip editing and may be returned truncated. **Never overwrite historical `PROJECT_STATE.md` from a truncated read.** Read this file first, then `CURRENT_HANDOFF.md`, `PROJECT_STATE.md`, `PRODUCT_SPEC.md`, `PEDAGOGY_ARCHITECTURE.md`, and `VISUAL_BIBLE.md` before visual work. Real GitHub `main` plus Android CI for that exact SHA always override documentation.

## 1. EXACT GREEN STATE BEFORE THIS DOCUMENT COMMIT

Current code HEAD before this documentation write:
- `491112b4c54736e48fce1d42404b5c3a83a5d374`
- `fix: require changed multiple-choice answer for retry`
- Android CI #389, run `34305553667`: `COMPLETED / SUCCESS`; unit tests, debug APK build and artifact upload all succeeded.

Immediately before it:
- `3158be3f79237aa98220838dd65003212b0890e4` — `docs: record green state through CI 387` — CI #388 SUCCESS.
- `1efce976987aaa00707065a00bbea5e95cce7681` — `fix: guard rapid duplicate placement submission` — CI #387 attempt 1 failed only while finalizing `actions/upload-artifact` with external HTTP 403 after unit tests and APK build had succeeded; the same job was rerun without code changes and attempt 2 finished SUCCESS on the exact same SHA.
- `f8eedfa0f3b49c141a97be85b98ce4d347a828c5` — `fix: refresh learning queue when review becomes due` — CI #386 SUCCESS.
- `a1e8f03000b8708f2fa09a37c57efb630baa7740` — `fix: guard rapid duplicate learning submission` — CI #385 SUCCESS.
- `5e5a8e9672e9979f721a099b1854ee6a9d8bb4ae` — `fix: prevent duplicate learning attempt submission` — CI #384 SUCCESS.

Earlier optional-practice and compatible-queue foundation remains green:
- `ae0683230595c4fb50b4399575746e4ae5f8a185` — optional practice UI — CI #382 SUCCESS.
- `9827bc769c4a9a20c00a84d36800e43095517dd8` — optional-practice test — CI #381 SUCCESS.
- `412d8a8fbbba5e2e6cd8f6f8166befed729bd332` — optional-practice selector — CI #380 SUCCESS.
- `b6eec3ea22ef0c5c8073004e00995a5658e31f84` — main compatible learning queue UI — CI #377 SUCCESS.
- `576ace99d9e6df712bb68197e3c0cd5316c6153e` — persisted queue integration guard — CI #378 SUCCESS.
- adapter/test foundation: `6479a02d...` CI #374 SUCCESS and `1e360178...` CI #375 SUCCESS.

This documentation write creates a newer HEAD. Before any later write, fetch `main` again and verify the Android CI belonging exactly to the resulting SHA.

## 2. MANDATORY DEVELOPMENT GATE

Before every write:
1. fetch real `main` HEAD;
2. fetch Android CI for exactly that SHA;
3. queued/in_progress = no writes;
4. failed = inspect jobs/logs and correct only the actual failure;
5. success = re-read every file being changed and use its current blob SHA;
6. make one small, reversible, testable change;
7. wait for CI on that exact commit;
8. continue only after green.

Do not stack production, test or documentation commits behind a running CI. Prefer production and dedicated tests in separate commits. Do not ask the user to perform terminal/manual coding that connected tools can execute.

If CI fails only in external infrastructure after code validation has already passed, inspect the exact failing step. Do not modify product code to address an unrelated GitHub service failure. A clean rerun of the failed job on the same SHA is acceptable when the logs prove the failure is external/transient.

## 3. A1 SECOND TRANSFER — SQUARE — PROTECTED CONTENT

Narrative: `A1SecondTransferNarrativeMicroUnit.kt`.
Exactly 6 beats for EN/PT/ES/FR/KO with Barto and Chiu.

EN: `Hello, Chiu!` / `Hello, Barto!` / `Where do you live?` / `I live in Rio.` / `What do you like?` / `I like coffee.`
PT: `Olá, Chiu!` / `Olá, Barto!` / `Onde você mora?` / `Eu moro no Rio.` / `Do que você gosta?` / `Eu gosto de café.`
ES: `¡Hola, Chiu!` / `¡Hola, Barto!` / `¿Dónde vives?` / `Vivo en Río.` / `¿Qué te gusta?` / `Me gusta el café.`
FR: `Bonjour, Chiu !` / `Bonjour, Barto !` / `Où est-ce que tu habites ?` / `J’habite à Rio.` / `Qu’est-ce que tu aimes ?` / `J’aime le café.`
KO: `안녕하세요, 치우!` / `안녕하세요, 바르토!` / `어디에 살아요?` / `리우에 살아요.` / `무엇을 좋아해요?` / `커피를 좋아해요.`

Residence and preference both preserve:
`context/comprehension → MULTIPLE_CHOICE cued retrieval → REORDER → FILL_IN`.

`A1SecondTransferLearningUnit.kt` connects narrative, comprehension, residence track and preference track. Residence/preference remain separate review/evidence targets. New work unlocks after exposure to all linked starter `reviewKey`s; correctness is not required. Error = attempt/exposure, not mastery.

Do not recreate these activities. Do not advance automatically to `FREE_TEXT`.

## 4. REVIEW-FIRST / FSRS — DO NOT DUPLICATE

Second-transfer review:
- is separate from starter review;
- consumes schedules already created through the generic scheduler;
- uses strong closed REORDER/FILL_IN variants;
- never creates its own schedules;
- uses the same generic `ReviewScheduleState` keyed by `reviewKey`;
- stays below due-review priority rules.

The composed A1 queue already prioritizes due starter review and due second-transfer review before new work. A2/B1/B2/C1/C2 retain existing starter behavior.

Do not create another scheduler, FSRS persistence path, storage system or artificial mastery state. Historical rebuild already groups by `reviewKey`; variants sharing a target update the same schedule.

## 5. REAL LEARNING UI — CURRENT STATE

Primary compatible queue:
- production: `app/src/main/java/com/chiu/know/model/LearningActivityQueue.kt`
- model guard: `app/src/test/java/com/chiu/know/model/LearningActivityQueueTest.kt`
- persistence integration guard: `app/src/test/java/com/chiu/know/model/LearningActivityQueuePersistenceIntegrationTest.kt`
- UI call site: `app/src/main/java/com/chiu/know/ui/ChiuKnowApp.kt`

`ChiuKnowApp.kt` calls `learningActivityQueueSelection(...)` for the real learning activity screen. For A1 it can surface second-transfer new work/review through the existing `StarterQueueSelection` semantics. For A2–C2 it delegates to the old starter queue unchanged.

The normal attempt path remains generic:
`isLearningAnswerCorrect(...) → learningEvidenceFor(...) → encodeLearningEvidence(...) → updateReviewScheduleStateSet(...)`.

The CI #378 integration guard proves for EN/PT/ES/FR/KO that persisted incorrect attempts remain exposure, generated schedules survive encode/decode, due second-transfer review returns through the compatible queue, REORDER/FILL_IN are used for review, and due review outranks remaining new work.

### Attempt-submission hardening now green

`LearningActivityScreen` blocks repeated submission of the same checked answer in multiple layers:
- the Verify button is disabled when `checked == true`;
- the Verify click handler also guards with `if (!checked)` before calling `onAttempt(...)`, closing the rapid-double-event window before recomposition;
- for `MULTIPLE_CHOICE`, tapping the already-selected option after feedback no longer resets `checked`; selecting a genuinely different option still changes `answer` and resets `checked = false` so a retry remains possible.

This protects persisted evidence and scheduler mutation from accidental duplicate submissions while preserving genuine retries after a real answer change. REORDER and text editing behavior remain unchanged.

### Due-time refresh now green

The learning queue includes a refresh tick and a `LaunchedEffect` that waits until `nextDueAtEpochMillis` while the screen is in `NONE_DUE`. When the due time arrives, it recomputes the queue so a newly due review can appear without requiring the user to leave and re-enter the screen. This does not create another scheduler and does not alter due-date calculation.

## 6. OPTIONAL “PRACTICE MORE” — COMPATIBLE AND WIRED

Production selector:
`app/src/main/java/com/chiu/know/model/LearningOptionalPractice.kt`

Dedicated test:
`app/src/test/java/com/chiu/know/model/LearningOptionalPracticeTest.kt`

Real UI:
`ChiuKnowApp.kt` calls `learningActivityForOptionalPractice(...)` when `optionalPracticeRequested` is true.

Rules protected:
- A2–C2 preserve starter optional-practice behavior;
- A1 stays starter-only until the second-transfer initial sequence is fully exposed;
- after that, strong second-transfer REORDER/FILL_IN variants may join optional practice;
- optional practice remains exposure balancing, not mastery;
- optional practice does **not** persist `LearningEvidence` and does **not** call `updateReviewScheduleStateSet(...)` because the existing `if (!optionalPracticeRequested)` gate remains intact;
- optional practice therefore cannot create or mutate FSRS schedules;
- it is offered only from the existing `NONE_DUE` path and does not outrank due review.

CI proof:
- CI #380: production selector green;
- CI #381: dedicated five-language test green;
- CI #382: real UI wiring green.

Do not revert this branch to `starterLearningActivityForEvidence(...)` unless a real regression demands it.

## 7. RESOLVED COMPATIBILITY SEAMS AND NEXT SAFE INVESTIGATION

### Trail availability investigation — RESOLVED, NO BUG

`LearningTrailScreen` still receives availability from:
`starterLearningActivityFor(targetLanguage.code, estimatedLevel) != null`.

This was investigated against the actual supported languages and queue architecture. There is currently no reachable supported-language state in which compatible content exists while this check is false:
- EN/PT/ES/FR/KO all have starter A1 content;
- A1 second transfer unlocks only on top of that starter foundation;
- A2–C2 compatible queue behavior delegates to starter content.

Therefore no change was made. Do not revisit unless future content architecture introduces compatible content independent of starter presence.

### Placement rapid-double-tap — RESOLVED

`PlacementQuestionScreen` keeps a per-question submission flag keyed by `question.id`. Once one option is submitted, all options are disabled and the handler refuses another submission for that same question. A new question ID resets the flag. The placement decision algorithms themselves were not changed.

### MULTIPLE_CHOICE same-selection retry — RESOLVED

After feedback, selecting the same already-selected multiple-choice option no longer resets `checked` and therefore cannot re-enable Verify for an unchanged answer. Selecting a different option still resets `checked = false` and allows a genuine retry. This is a UI-only guard; persistence, evidence, correctness evaluation and scheduler semantics were not changed.

Commit/CI proof:
- `491112b4c54736e48fce1d42404b5c3a83a5d374` — `fix: require changed multiple-choice answer for retry` — CI #389 SUCCESS.

### Next safe technical investigation — post-feedback progression/navigation

Current `LearningActivityScreen` shows feedback and always offers `Back to path`; it does not expose a dedicated post-feedback `Continue to next activity` action. Do **not** call this a bug automatically and do not add auto-advance blindly.

Investigate the actual end-to-end flow against `PRODUCT_SPEC.md` and the queue/persistence timing. The product spec requires useful feedback and the ability to understand an error and try again; the current screen already supports retry after a genuine answer change. Determine whether, after a correct checked response, requiring a return to the path creates a real product-flow mismatch or whether the existing navigation is intentional at this prototype stage. Any future change must preserve persistence before progression, review-first priority, retry-after-error behavior, and optional-practice isolation.

## 8. LEARNINGACTIVITY CONTRACT

Never invent fields. `LearningActivity` has no `languageCode` property.
Required current fields include `id`, `level`, `primarySkill`, `learningObjective`, `knowledgeTarget`, `responseType`, `prompt`, `feedback`, `reviewKey`, `acceptedAnswers`, plus `responseOptions` where applicable; `audioPromptId` is optional.

When filtering by language, established code uses ID prefixes such as `it.id.startsWith("$languageCode-")`. CI #341 previously failed because a nonexistent `languageCode` property was invented and required fields were omitted. Do not repeat it.

## 9. PEDAGOGY / CLAIMS — PERMANENT GUARDRAILS

Quality > quantity. Desired progression where technically defensible:
`context → recognition → cued retrieval → fewer cues → new context → spaced review → retention/transfer`.

Never fabricate mastery, per-skill proficiency scores without valid evidence, free-writing evaluation, speaking, pronunciation, ASR or CEFR certification. `FREE_TEXT` enum existence does not mean open writing is robustly evaluated. Error remains an attempt/exposure, not mastery. XP, streak and preferences are not CEFR mastery evidence.

## 10. KOREAN

Mandatory human-review gate is superseded. Rigorous second AI linguistic review is accepted; external human review is desirable but not blocking. Never call AI review human review, psychometric validation, CEFR certification or independent validation.

EN/PT/ES/FR/KO placement currently uses `QUALITY_SESSION`, subject always to real code. Reviewed A1 forms include `안녕하세요`, `저는 미아예요`, `저는 치우예요`, `제 이름은 치우예요`, `이름이 뭐예요?`, `어디에 살아요?`, `리우에 살아요`, `무엇을 좋아해요?`, `커피를 좋아해요`, `저는 책이 있어요`, `감사합니다`, `고맙습니다`, `또 봐요`. Do not reopen without a real linguistic reason.

## 11. SUPABASE — ABSOLUTE SEPARATION

CHIU KNOW?: project `uskxabsodcnzlovuaurp`, org `aeerqbmrwulxsawhjyvm`, region `sa-east-1`.
CHIU PLAYER: project `hpcbkvbrlwjnwlikmbfb`, org `nnrwosbnvdvzaoflwxlo` — forbidden while working on Chiu Know?.

Never mix resources, quotas, buckets, functions, credentials or secrets. Never put `service_role` in the APK. Auth deep link physically tested: `chiuknow://auth-callback`.

## 12. VOICE / VISUAL — ABSOLUTE RULES

Official private voice: `Chiu-animada-recorte-final.m4a`, approximately 15.4 s / 309 KB. Do not put it on public GitHub, external provider, Supabase or APK without explicit user authorization. A private bucket is not upload authorization.

Two Chius must never be confused:
- realistic white Chihuahua with brown hair: APK logo/icon ONLY;
- yellow/weird cartoon Chihuahua: ALWAYS internal stories/cards/exercises/screens/app universe.

Do not arbitrarily redraw approved masters. Any new pose must be shown to the user and approved before APK integration. Official characters: Chiu, Mia, Jurandir, Barto, Lara, Caca, Onça, Perry, Lena. Jurandir is the definitive mosquito name; never revert to Zé Pernilongo.

## 13. HANDOFF RULE

When the user says `Continue`, fetch real HEAD + exact CI, read this compact state and relevant code, select the smallest safe next slice, implement, wait for CI, test separately where appropriate, and continue. Stop only for a real CI gate, genuine product decision, explicit authorization requirement, voice/data/publication requirement, or required visual approval.