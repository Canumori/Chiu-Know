# CHIU KNOW? — CURRENT PROJECT STATE

## AUTORITATIVO — 2026-09-08 — MAIN GREEN THROUGH OPTIONAL PRACTICE UI, CI #382

This is the compact operational checkpoint. Historical `PROJECT_STATE.md` is too large for safe full round-trip editing and may be returned truncated. **Never overwrite historical `PROJECT_STATE.md` from a truncated read.** Read this file first, then `CURRENT_HANDOFF.md`, `PROJECT_STATE.md`, `PRODUCT_SPEC.md`, `PEDAGOGY_ARCHITECTURE.md`, and `VISUAL_BIBLE.md` before visual work. Real GitHub `main` plus Android CI for that exact SHA always override documentation.

## 1. EXACT GREEN STATE BEFORE THIS DOCUMENT COMMIT

Current code HEAD before this documentation write:
- `ae0683230595c4fb50b4399575746e4ae5f8a185`
- `feat: wire compatible optional learning practice`
- Android CI #382, run `34176650009`: `COMPLETED / SUCCESS`.

Immediately before it:
- `9827bc769c4a9a20c00a84d36800e43095517dd8` — `test: guard compatible optional learning practice` — CI #381 SUCCESS.
- `412d8a8fbbba5e2e6cd8f6f8166befed729bd332` — `feat: expose compatible optional learning practice` — CI #380 SUCCESS.

Main learning queue integration already green:
- `b6eec3ea22ef0c5c8073004e00995a5658e31f84` — `feat: wire compatible learning activity queue` — CI #377 SUCCESS.
- `576ace99d9e6df712bb68197e3c0cd5316c6153e` — `test: guard persisted compatible learning queue flow` — CI #378 SUCCESS.
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

`ChiuKnowApp.kt` now calls `learningActivityQueueSelection(...)` for the real learning activity screen. For A1 it can surface second-transfer new work/review through the existing `StarterQueueSelection` semantics. For A2–C2 it delegates to the old starter queue unchanged.

The normal attempt path remains generic:
`isLearningAnswerCorrect(...) → learningEvidenceFor(...) → encodeLearningEvidence(...) → updateReviewScheduleStateSet(...)`.

The CI #378 integration guard proves for EN/PT/ES/FR/KO that persisted incorrect attempts remain exposure, generated schedules survive encode/decode, due second-transfer review returns through the compatible queue, REORDER/FILL_IN are used for review, and due review outranks remaining new work.

## 6. OPTIONAL “PRACTICE MORE” — NOW COMPATIBLE AND WIRED

Production selector:
`app/src/main/java/com/chiu/know/model/LearningOptionalPractice.kt`

Dedicated test:
`app/src/test/java/com/chiu/know/model/LearningOptionalPracticeTest.kt`

Real UI:
`ChiuKnowApp.kt` now calls `learningActivityForOptionalPractice(...)` when `optionalPracticeRequested` is true.

Rules now protected:
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

## 7. NEXT SAFE TECHNICAL INVESTIGATION — NOT YET A BUG

After the CI for this documentation commit is green, inspect this remaining compatibility seam before expanding A1 content:

`LearningTrailScreen` availability is currently called with:
`starterLearningActivityFor(targetLanguage.code, estimatedLevel) != null`.

Determine whether this creates any **real reachable-content problem** now that the primary A1 queue includes second transfer. Do not change it merely because the name says `starter`. Prove a concrete state where compatible learning content exists but the trail incorrectly hides/disables entry. If no such state exists, leave it alone and inspect the next real seam instead.

Any fix, if genuinely needed, should be a small model/UI compatibility change with a dedicated test and must preserve A2–C2 behavior.

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
