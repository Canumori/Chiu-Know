# CHIU KNOW? — CURRENT PROJECT STATE

## AUTORITATIVO — 2026-09-07/08 — LEARNING UI WIRED TO COMPOSED A1 QUEUE; CI #378 GREEN

This compact file is the current operational checkpoint. It exists because historical `PROJECT_STATE.md` is too large for safe full round-trip editing through the current GitHub connector and may be returned truncated. **Never overwrite historical `PROJECT_STATE.md` from a truncated read.** Read this file first, then `CURRENT_HANDOFF.md`, `PROJECT_STATE.md`, `PRODUCT_SPEC.md`, `PEDAGOGY_ARCHITECTURE.md`, and `VISUAL_BIBLE.md` before visual work. Real GitHub `main` plus the Android CI for that exact SHA always remain the final source of truth.

## 1. EXACT CHECKPOINT BEFORE THIS DOCUMENT COMMIT

Latest code/test HEAD before this documentation update:
- `576ace99d9e6df712bb68197e3c0cd5316c6153e`
- `test: guard persisted compatible learning queue flow`
- Android CI #378, run `34175651048`: `COMPLETED / SUCCESS`.

Production UI wiring immediately before it:
- `b6eec3ea22ef0c5c8073004e00995a5658e31f84`
- `feat: wire compatible learning activity queue`
- Android CI #377, run `34175331300`: `COMPLETED / SUCCESS`.

Adapter and its original model guard remain green:
- `6479a02dad9a21dd84907d4713215dc9655c5242` — `feat: expose compatible learning activity queue` — CI #374 SUCCESS.
- `1e3601789321cb65479afbb342997ff91da61f52` — `test: guard compatible learning activity queue` — CI #375 SUCCESS.

This documentation write creates a newer HEAD. Any continuation MUST fetch `main` again and verify the Android CI for the exact resulting SHA before another write.

## 2. MANDATORY DEVELOPMENT WORKFLOW

Before every write:
1. Fetch real `main` HEAD.
2. Fetch Android CI for exactly that SHA.
3. If queued/in_progress: no writes; remain at gate.
4. If failed: inspect jobs/logs and correct only the real failure.
5. If success: re-read every file to be changed and use its current blob SHA.
6. Make one small, reversible, testable change.
7. Wait for CI on that exact commit.
8. Continue only after green.

Never stack production + test + docs commits behind a running CI. Production and tests should preferably stay in separate commits. Never ask the user to perform terminal/manual coding that connected tools can do.

## 3. A1 SECOND TRANSFER — SQUARE — CURRENT CONTENT

Narrative: `A1SecondTransferNarrativeMicroUnit.kt`.
Exactly 6 beats for EN/PT/ES/FR/KO with Barto and Chiu.

EN: `Hello, Chiu!` / `Hello, Barto!` / `Where do you live?` / `I live in Rio.` / `What do you like?` / `I like coffee.`
PT: `Olá, Chiu!` / `Olá, Barto!` / `Onde você mora?` / `Eu moro no Rio.` / `Do que você gosta?` / `Eu gosto de café.`
ES: `¡Hola, Chiu!` / `¡Hola, Barto!` / `¿Dónde vives?` / `Vivo en Río.` / `¿Qué te gusta?` / `Me gusta el café.`
FR: `Bonjour, Chiu !` / `Bonjour, Barto !` / `Où est-ce que tu habites ?` / `J’habite à Rio.` / `Qu’est-ce que tu aimes ?` / `J’aime le café.`
KO: `안녕하세요, 치우!` / `안녕하세요, 바르토!` / `어디에 살아요?` / `리우에 살아요.` / `무엇을 좋아해요?` / `커피를 좋아해요.`

Both residence and preference have the protected closed progression:
`context/comprehension → MULTIPLE_CHOICE cued retrieval → REORDER → FILL_IN`.

Do not recreate these activities. Do not jump to `FREE_TEXT`. The current evaluator is conservative exact matching after trim/lowercase and is not a robust open-writing evaluator.

## 4. CONNECTED SECOND-TRANSFER UNIT AND REVIEW

`A1SecondTransferLearningUnit.kt` connects:
- narrative;
- comprehension;
- residence retrieval track;
- preference retrieval track.

Each retrieval track preserves `MULTIPLE_CHOICE → REORDER → FILL_IN`. Residence and preference remain separate evidence/review targets.

The second-transfer new-work selector unlocks only after exposure to every starter `linkedReviewKey` from the narrative. Correctness is NOT required: error remains an observed attempt/exposure, not mastery. It chooses the first unattempted activity by `activityId` and does not inspect due dates.

Second-transfer review:
- is separate from starter review;
- consumes only schedules that already exist;
- uses the strong closed REORDER/FILL_IN variants;
- does not create schedules by itself;
- shares generic `ReviewScheduleState` persistence by `reviewKey`;
- preserves due-review priority over new work.

Do not create another scheduler, another FSRS persistence path, or artificial mastery state.

## 5. LEARNING ACTIVITY QUEUE — NOW WIRED TO THE REAL UI

Production adapter:
`app/src/main/java/com/chiu/know/model/LearningActivityQueue.kt`

Model guard:
`app/src/test/java/com/chiu/know/model/LearningActivityQueueTest.kt`

Real UI call site:
`app/src/main/java/com/chiu/know/ui/ChiuKnowApp.kt`

Persistence integration guard:
`app/src/test/java/com/chiu/know/model/LearningActivityQueuePersistenceIntegrationTest.kt`

`learningActivityQueueSelection(...)` intentionally returns `StarterQueueSelection` so the current UI can preserve its established handling.

For A1:
- delegates to the composed review-first A1 queue, including second-transfer review and new work;
- `DUE_STARTER_REVIEW` / `DUE_SECOND_TRANSFER_REVIEW` → `StarterQueueReason.DUE_REVIEW`;
- `SECOND_TRANSFER_NEW_WORK` / `STARTER_NEW_TARGET` → `StarterQueueReason.NEW_TARGET`;
- preserves `NONE_DUE` and `nextDueAtEpochMillis`;
- preserves `NO_CONTENT`.

For A2/B1/B2/C1/C2:
- delegates unchanged to `starterQueueSelection(...)`.

The real learning screen was changed in commit `b6eec3ea...` to call `learningActivityQueueSelection(...)` instead of `starterQueueSelection(...)`. No visual change, scheduler duplication, storage duplication or FSRS change was introduced.

The screen still uses the generic attempt path:
`isLearningAnswerCorrect(...) → learningEvidenceFor(...) → encodeLearningEvidence(...) → updateReviewScheduleStateSet(...)`.

The CI #378 integration test proves for EN/PT/ES/FR/KO that:
- ready transfer work reaches the compatible queue;
- a persisted incorrect attempt is still exposure/evidence;
- DataStore-style evidence/schedule encode/decode survives the flow;
- the created transfer schedule is consumed at its real generated `dueAtEpochMillis`;
- due transfer review returns as `DUE_REVIEW`;
- review uses REORDER/FILL_IN rather than the easier MULTIPLE_CHOICE new-work variant;
- due review outranks remaining new work.

## 6. REVIEW / FSRS FACTS PROVED

- `ReviewScheduleState` is generic by `reviewKey`.
- Variants sharing one `reviewKey` update one schedule.
- Historical rebuild groups evidence by `reviewKey`.
- The app can now actually present second-transfer work through the main learning UI and persist evidence/schedules through the existing path.
- Review-first remains in force.
- Error = attempt/exposure, NOT mastery.
- XP, streak and learner preferences are not CEFR mastery evidence.

## 7. NEXT SAFE TECHNICAL STEP — DO NOT EXECUTE BLINDLY

After verifying the CI of this documentation commit and ensuring there is no newer HEAD, inspect the remaining learning-screen compatibility paths before expanding content.

A concrete legacy seam discovered after CI #378:
- the primary learning queue now uses `learningActivityQueueSelection(...)`;
- the optional `Practice more` branch in `ChiuKnowApp.kt` still calls `starterLearningActivityForEvidence(...)` directly;
- therefore optional practice is currently starter-only even when A1 second-transfer content has already been introduced.

This is a **gap to investigate, not yet a product decision that optional practice must change**.

Before any write on this seam:
1. re-read `ChiuKnowApp.kt`, `StarterReviewSelection.kt`, second-transfer review/new-work selectors and relevant tests;
2. preserve the existing rule that optional practice does NOT create/update schedules;
3. never let optional practice introduce second-transfer content before its prerequisites/initial sequence have been exposed;
4. do not let optional practice override due review;
5. preserve A2–C2 behavior exactly;
6. prefer a small model compatibility adapter plus dedicated test before UI wiring, if the code supports that cleanly;
7. do not change visuals or add content merely to solve this seam.

Also inspect whether `LearningTrailScreen` availability being based on `starterLearningActivityFor(...) != null` causes any real reachable-content problem. Do not change it unless a concrete problem is proved.

## 8. LEARNINGACTIVITY CONTRACT — DO NOT INVENT FIELDS

`LearningActivity` has no `languageCode` property.
Required current fields include:
- `id`
- `level`
- `primarySkill`
- `learningObjective`
- `knowledgeTarget`
- `responseType`
- `prompt`
- `feedback`
- `reviewKey`
- `acceptedAnswers`
- `responseOptions` when applicable
- optional `audioPromptId`.

When language filtering is needed, the established pattern is ID prefix, e.g. `it.id.startsWith("$languageCode-")`.

CI #341 previously failed because implementation invented `languageCode` and omitted required fields. Never repeat that error.

## 9. PEDAGOGY — PERMANENT GUARDRAILS

Quality > quantity.
Desired progression where technically defensible:
`context → recognition → cued retrieval → fewer cues → new context → spaced review → retention/transfer`.
Do not force a nonexistent step merely to complete a theoretical sequence.

Never fabricate:
- mastery;
- per-skill proficiency scores without valid evidence;
- free-writing evaluation;
- speaking;
- pronunciation;
- ASR;
- CEFR certification.

`FREE_TEXT` enum existence is not permission to pretend open writing is validly evaluated.
Optional practice remains separate from scheduler mutation unless a later explicit product decision changes that rule.

## 10. KOREAN — CURRENT RULE

The old mandatory-human-review gate is superseded. Rigorous second AI linguistic review is accepted; external human review remains desirable but is not blocking.

Never call AI review:
- human review;
- psychometric validation;
- CEFR certification;
- independent validation.

EN/PT/ES/FR/KO placement currently belongs to `QUALITY_SESSION`, subject always to real code.
Strong reviewed A1 forms include:
`안녕하세요`, `저는 미아예요`, `저는 치우예요`, `제 이름은 치우예요`, `이름이 뭐예요?`, `어디에 살아요?`, `리우에 살아요`, `무엇을 좋아해요?`, `커피를 좋아해요`, `저는 책이 있어요`, `감사합니다`, `고맙습니다`, `또 봐요`.
Do not reopen them without a real linguistic reason.

## 11. SUPABASE — ABSOLUTE SEPARATION

CHIU KNOW?:
- project `uskxabsodcnzlovuaurp`
- org `aeerqbmrwulxsawhjyvm`
- region `sa-east-1`

CHIU PLAYER:
- project `hpcbkvbrlwjnwlikmbfb`
- org `nnrwosbnvdvzaoflwxlo`
- forbidden while working on Chiu Know?.

Never mix resources, quotas, buckets, functions, credentials or secrets. Never put `service_role` in the APK.
Auth deep link physically tested: `chiuknow://auth-callback`.

## 12. VOICE — ABSOLUTE RULE

Official private voice:
`Chiu-animada-recorte-final.m4a`, approximately 15.4 s / 309 KB.

Do not put it on public GitHub, external provider, Supabase or APK without explicit user authorization. A private bucket existing does not authorize upload.

## 13. VISUAL — ABSOLUTE RULE

There are two Chius and they must never be confused:
- realistic white Chihuahua with brown hair: APK logo/icon ONLY;
- yellow/weird cartoon Chihuahua: ALWAYS the internal character in stories, cards, exercises, screens and the app universe.

Approved masters are not arbitrarily redrawn. A new pose must be shown to the user and approved before APK integration.
Official characters: Chiu, Mia, Jurandir, Barto, Lara, Caca, Onça, Perry, Lena.
Jurandir is the definitive mosquito name; do not revert to Zé Pernilongo.

## 14. HANDOFF RULE

When the user says `Continue`, do not ask what to do. Fetch real HEAD + exact CI, read this state and the relevant code, choose the next smallest safe slice, implement it, wait for CI, test separately where appropriate, and continue. Stop only for a real CI gate, genuine product decision, explicit authorization requirement, voice/data/publication requirement, or required visual approval.
