# CHIU KNOW? — CURRENT PROJECT STATE

## AUTORITATIVO — 2026-09-11 — PROGRESSIVE CUE-WITHDRAWAL MODEL, CI #424 GREEN

Latest verified production/test state before this documentation write:
- `d5ef20c0fb402313f5b7b63785259699e3d6691d` — `feat: model progressive cue withdrawal` — Android CI #423 SUCCESS.
- `c599cddc06f3042e22da4f8507a43919cbd7f0e7` — `test: cover progressive cue withdrawal model` — Android CI #424, run `34619708519`: `COMPLETED / SUCCESS`.

New model-only foundation:
- `LearningCueStage` represents the currently defensible sequence `RECOGNITION → STRUCTURED_RECONSTRUCTION → REDUCED_CUE_RETRIEVAL`;
- the mapping is limited to the deterministic closed formats already evaluated by the app: MULTIPLE_CHOICE, REORDER and FILL_IN;
- FREE_TEXT, LISTEN_AND_RESPOND and SPEAK remain deliberately unclassified until dedicated evaluators justify a cue-withdrawal claim;
- the stage is a curriculum-planning fact, never mastery, proficiency or proof of transfer;
- no activity content, queue, UI, persistence, evidence or FSRS behavior changed.

The learner-profile front was also re-audited before this work. Goal, priority, daily availability, versioned persistence, language separation, UI and conservative practice weights already exist and are covered; no duplicate preference model was added.


## AUTORITATIVO — 2026-09-10 — CLAIMS LIMITED TO CURRENT EVALUATORS, CI #421 GREEN

Latest verified production/test state before this documentation write:
- `c68a7011429530d1e11d503823ead5bf065a0d7b`
- `fix: limit claim summaries to current evaluators`
- Android CI #421, run `34502175588`: `COMPLETED / SUCCESS` on the exact SHA.
- Full repository tree was verified before CI: 231 entries, workflow and Gradle project present.

Important distinction:
- `supportedEvidenceClaims(...)` continues to describe what an activity format could support when an appropriate evaluator exists;
- `summarizeLearningEvidenceByClaim(...)` now aggregates only claims observable with current evaluation capabilities;
- WRITTEN_PRODUCTION, SPOKEN_PRODUCTION, PRONUNCIATION and INTERACTION are omitted until dedicated mechanisms exist;
- unknown activities remain omitted and duplicate activity IDs remain rejected;
- no persisted evidence format, UI, scheduler or curriculum behavior changed.


## AUTORITATIVO — 2026-09-10 — DESCRIPTIVE EVIDENCE BY CLAIM, CI #419 GREEN

Latest verified state before this documentation write:
- `eade155aa9f82bdac3fae66f68d9ce18218e9cf9` — `feat: add descriptive evidence summaries by claim` — CI #418 SUCCESS.
- `3cd5905aa9bea30a44ad38bf742c8b010cc0dd06` — `test: cover descriptive evidence summaries by claim` — Android CI #419, run `34501223143`: `COMPLETED / SUCCESS`.

New model-only foundation:
- `LearningClaimEvidenceSummary` joins observed evidence to known activities and aggregates only claims returned by `supportedEvidenceClaims(...)`;
- one attempt may contribute to multiple legitimate claims;
- unknown activity IDs are omitted instead of guessed;
- duplicate activity IDs are rejected as ambiguous;
- pronunciation and interaction remain unavailable without dedicated mechanisms;
- summaries preserve attempts, correctness, target variety and recency, with no score, mastery or CEFR sub-level;
- the persisted `LearningEvidence` format and all UI/scheduler behavior remain unchanged.


## AUTORITATIVO — 2026-09-10 — DESCRIPTIVE EVIDENCE BY SKILL, CI #416 GREEN

Latest verified state before this documentation write:
- `195a0c7cbf8f13ab0a266922ee78045beba0030d` — `feat: add descriptive evidence summaries by skill` — CI #415 SUCCESS.
- `357cfdde5fd90b6d90b58643b73eb9f8a2a17f4b` — `test: cover descriptive evidence summaries by skill` — Android CI #416, run `34497386866`: `COMPLETED / SUCCESS`.

New model-only foundation:
- `LearningSkillEvidenceSummary` groups observed `LearningEvidence` by declared `LearningSkill` and CEFR level;
- it preserves total/correct/incorrect attempts, distinct review-target count, first observation and latest observation as separate facts;
- empty skills are omitted instead of receiving an artificial zero/mastery state;
- results are ordered by latest observed evidence;
- it deliberately calculates no percentage, grade, mastery, proficiency sub-score or CEFR sub-level;
- no UI, scheduler, placement result or curriculum behavior changed.


## AUTORITATIVO — 2026-09-10 — PREFERENCE LANGUAGE-SAFE PERSISTENCE, CI #413 GREEN

Latest verified production state before this documentation write:
- `9e2e011fa4f0bc83c0a15a48509c85096280dc37`
- `fix: persist learner preferences for selected language`
- Android CI #413, run `34495917189`: `COMPLETED / SUCCESS` on the exact SHA.

Learner-preference persistence now captures the target-language code before launching the asynchronous DataStore write. Rapid navigation to language selection can no longer store one language's selected preferences under another language's key. Preference content, curriculum behavior and personalization rules did not change.


## AUTORITATIVO — 2026-09-10 — PLACEMENT LANGUAGE-SAFE PERSISTENCE, CI #411 GREEN

Latest verified production state before this documentation write:
- `9cfd522ab9332f7ed7c7fb4e9f2ca8ff29bf238e`
- `fix: persist placement level for completed language`
- Android CI #411, run `34494483656`: `COMPLETED / SUCCESS` on the exact SHA.

A real asynchronous persistence race was closed in both quality-session and legacy placement completion:
- the completed target-language code is captured before launching the DataStore write;
- the decided level is persisted under that captured language key;
- changing language immediately after placement can no longer write the completed level under a different language;
- placement banks, scoring, terminal decisions and UI progression remain unchanged.


## AUTORITATIVO — 2026-09-10 — ALL-LANGUAGE PLACEMENT RUNTIME CONTRACT, CI #409 GREEN

Latest verified test state before this documentation write:
- `eb34ab15cfd462d5f3aa5158c15bc1b20d3abea8`
- `test: cover placement runtime sessions for all languages`
- Android CI #409, run `34492953275`: `COMPLETED / SUCCESS` on the exact SHA.

A new integration contract now exercises the actual `placementRuntimeSelection(...)` path for every supported target language (EN/PT/ES/FR/KO). It verifies that each production-selected quality session:
- always exposes one current question while active;
- stays within `maximumAnsweredQuestions`;
- terminates with no current question;
- never reuses a question ID;
- keeps presented-question and answered-question accounting consistent.

No placement bank, scoring decision, runtime mode or production behavior changed.


## AUTORITATIVO — 2026-09-10 — OPTIONAL CONTINUE ROUND GUARD, CI #407 GREEN

Latest verified production state before this documentation write:
- `e1cc0d147f83456619973ec30e932f8406467ea4`
- `fix: guard duplicate optional practice continue`
- Android CI #407, run `34492052225`: `COMPLETED / SUCCESS` on the exact SHA.

The five-activity optional session now protects rapid repeated Continue taps:
- each displayed optional-practice round can be counted only once;
- the guard uses the displayed round number rather than `activity.id`, so a legitimate next round still works if content availability causes the same activity ID to appear again;
- Practice again and a fresh Practice more entry reset the round guard;
- no temporary session evidence is persisted and normal LearningEvidence/FSRS remain unchanged.


## AUTORITATIVO — 2026-09-10 — CORRECT-FEEDBACK ANSWER LOCK, CI #405 GREEN

Latest verified production state before this documentation write:
- `437d7af8ef2231649ab13766c31f54ba8f1d2bef`
- `fix: keep correct learning feedback immutable`
- Android CI #405, run `34490147701`: `COMPLETED / SUCCESS` on the exact SHA.

A real post-answer lifecycle issue was closed:
- after a correct answer, MULTIPLE_CHOICE, REORDER and text controls are disabled while feedback is displayed;
- each edit handler independently rechecks whether the current checked answer is correct, protecting rapid taps before recomposition;
- a correct activity can no longer lose its feedback or accept an unintended extra attempt before Continue;
- incorrect answers remain editable and preserve the existing genuine retry behavior;
- normal persistence serialization, optional-practice separation and Continue behavior remain unchanged.


## AUTORITATIVO — 2026-09-10 — NORMAL-LEARNING EXIT SERIALIZATION, CI #402/#403 GREEN

Latest verified production state before this documentation write:
- `3936f438bbecacd9b89e28c0f52a92d4afaeb25a`
- `fix: scope persistence exit guard to learning`
- Duplicate Android CI runs #402 and #403 both finished `COMPLETED / SUCCESS` on the exact SHA.

A real lifecycle window was closed:
- normal learning now disables Back to path while `pendingLearningPersistenceCount > 0`;
- the parent `onBack` handler independently rechecks the counter, protecting rapid taps before recomposition;
- this prevents leaving/re-entering while the previous DataStore write is pending and resubmitting against stale queue state;
- optional practice remains freely exitable because it intentionally has no persistence;
- voice preview and unrelated Back buttons retain their original behavior.

CI #401 failed on the first implementation because a broad replacement applied `canExit` to VoiceSampleScreen. The follow-up commit restored that unrelated button and scoped the guard only to LearningActivityScreen; both exact-SHA verification runs passed.


## AUTORITATIVO — 2026-09-10 — OPTIONAL PRACTICE FIVE-ACTIVITY SESSION, CI #399 GREEN

Latest verified production state before this documentation write:
- `5593d0ed6156cd2c838ace251ae1e99301c77965`
- `fix: restore full tree with optional session summary`
- Android CI #399, run `34487431192`: `COMPLETED / SUCCESS` on the exact SHA.
- The repository tree was explicitly verified complete with 226 entries, including Android CI workflow, Gradle project files and tests.

Optional Practice now:
- remains strictly local/non-persistent and does not mutate LearningEvidence storage, FSRS schedules, mastery or unlock state;
- uses temporary session-only evidence to balance and advance to another activity after a correct answer;
- preserves useful feedback and genuine retry after an error;
- offers Continue after a correct optional-practice answer;
- completes after exactly 5 correct activities;
- shows a localized summary in EN/PT/ES/FR/KO;
- offers Practice again or Back to path;
- has a dedicated model test proving transient session evidence changes the next optional selection without mutating the persisted evidence list.

Relevant commits:
- `a73f4508f56563f5ff578af6ae1b45518cc98c0a` — optional non-persistent progression — CI #397 SUCCESS.
- `bad6a2c4114a26dae203855f5b01da24f2905678` — dedicated progression test — CI #398 SUCCESS.
- `4273228e5ba52a8bee623e70936e0a69b820b2b2` / `14bb91738bcd04d11ae1fe6a2839049ee394aa92` — intermediate same-feature commits whose direct Git tree construction did not inherit the base tree and therefore did not start CI.
- `5593d0ed6156cd2c838ace251ae1e99301c77965` — restored the complete parent tree while preserving the feature; CI #399 SUCCESS.

Do not remove the temporary/persisted separation. Optional session evidence may guide only the current in-memory practice session and must never be written to DataStore or passed into the normal learning queue.


## AUTORITATIVO — 2026-09-10 — MAIN GREEN THROUGH RETRY-PERSISTENCE SERIALIZATION + HANDOFF, CI #395

This is the compact operational checkpoint. Historical `PROJECT_STATE.md` is too large for safe full round-trip editing and may be returned truncated. **Never overwrite historical `PROJECT_STATE.md` from a truncated read.** Read this file first, then `CURRENT_HANDOFF.md`, historical `PROJECT_STATE.md`, `PRODUCT_SPEC.md`, `PEDAGOGY_ARCHITECTURE.md`, and `VISUAL_BIBLE.md` before visual work. Real GitHub `main` plus Android CI for that exact SHA always override documentation.

## 1. EXACT GREEN STATE BEFORE THIS DOCUMENT COMMIT

Current HEAD before this documentation write:
- `88aa412747549c2d834d58a24b3af0c57efb970a`
- `docs: refresh current handoff through CI 394`
- Android CI #395, run `34375793682`: `COMPLETED / SUCCESS` on the exact SHA.

Most recent production code immediately before that handoff:
- `d880f924008d11ea331bb8b0c553a0678e8d6559`
- `fix: serialize learning retries with persistence`
- Android CI #394, run `34372882404`: `COMPLETED / SUCCESS`.

Immediately before it:
- `ab01d129277ed3a4c905bc9ffa35e241c9b8ea24` — `docs: record green state through CI 392` — CI #393 SUCCESS.
- `081725bd95c685058da003f9a8bb0e9e04b2e52d` — `feat: continue learning after correct persisted answer` — CI #392 SUCCESS.
- `0b37447ccdabfde5f8b4699605468ca37dffcfdb` — `fix: keep learning feedback visible after persistence` — CI #391 SUCCESS.
- `82683504452ae606d4327f0a0508cd639da8e30f` — `docs: record green state through CI 389` — CI #390 SUCCESS.
- `491112b4c54736e48fce1d42404b5c3a83a5d374` — `fix: require changed multiple-choice answer for retry` — CI #389 SUCCESS.
- `3158be3f79237aa98220838dd65003212b0890e4` — `docs: record green state through CI 387` — CI #388 SUCCESS.
- `1efce976987aaa00707065a00bbea5e95cce7681` — placement rapid-double-tap guard — CI #387 attempt 1 external upload-artifact HTTP 403 after tests/build passed; same SHA attempt 2 SUCCESS.
- `f8eedfa0f3b49c141a97be85b98ce4d347a828c5` — due-time queue refresh — CI #386 SUCCESS.
- `a1e8f03000b8708f2fa09a37c57efb630baa7740` — rapid duplicate learning submission guard — CI #385 SUCCESS.
- `5e5a8e9672e9979f721a099b1854ee6a9d8bb4ae` — duplicate learning attempt prevention — CI #384 SUCCESS.

Optional-practice / compatible-queue foundation remains green:
- `ae0683230595c4fb50b4399575746e4ae5f8a185` — optional practice UI — CI #382 SUCCESS.
- `9827bc769c4a9a20c00a84d36800e43095517dd8` — optional-practice test — CI #381 SUCCESS.
- `412d8a8fbbba5e2e6cd8f6f8166befed729bd332` — optional-practice selector — CI #380 SUCCESS.
- `b6eec3ea22ef0c5c8073004e00995a5658e31f84` — compatible learning queue UI — CI #377 SUCCESS.
- `576ace99d9e6df712bb68197e3c0cd5316c6153e` — persisted queue integration guard — CI #378 SUCCESS.

This documentation write creates a newer HEAD. Before any later write, fetch `main` again and verify Android CI for exactly that resulting SHA.

## 2. MANDATORY DEVELOPMENT GATE

Before every write:
1. fetch real `main` HEAD;
2. fetch Android CI for exactly that SHA;
3. queued/in_progress = no write;
4. failed = inspect jobs/logs and fix only the real failure;
5. success = reread every file to be changed and use its current blob SHA;
6. make one small, reversible, testable change;
7. wait for CI on that exact commit;
8. continue only after green.

Do not stack production, tests or docs behind running CI. Prefer production and dedicated tests as separate commits. Do not ask the user to perform terminal/manual coding that connected tools can execute.

If CI fails only in external infrastructure after code validation passed, inspect the exact step. Do not modify product code for an unrelated GitHub service failure. A clean rerun on the same SHA is acceptable when logs prove the failure is external/transient.

## 3. A1 SECOND TRANSFER — PRAÇA — PROTECTED

Narrative file: `A1SecondTransferNarrativeMicroUnit.kt`.
Exactly 6 beats for EN/PT/ES/FR/KO with Barto asking and Chiu responding.

EN: `Hello, Chiu!` / `Hello, Barto!` / `Where do you live?` / `I live in Rio.` / `What do you like?` / `I like coffee.`
PT: `Olá, Chiu!` / `Olá, Barto!` / `Onde você mora?` / `Eu moro no Rio.` / `Do que você gosta?` / `Eu gosto de café.`
ES: `¡Hola, Chiu!` / `¡Hola, Barto!` / `¿Dónde vives?` / `Vivo en Río.` / `¿Qué te gusta?` / `Me gusta el café.`
FR: `Bonjour, Chiu !` / `Bonjour, Barto !` / `Où est-ce que tu habites ?` / `J’habite à Rio.` / `Qu’est-ce que tu aimes ?` / `J’aime le café.`
KO: `안녕하세요, 치우!` / `안녕하세요, 바르토!` / `어디에 살아요?` / `리우에 살아요.` / `무엇을 좋아해요?` / `커피를 좋아해요.`

Residence and preference both preserve:
`context/comprehension → MULTIPLE_CHOICE → REORDER → FILL_IN`.

`A1SecondTransferLearningUnit.kt` connects narrative, comprehension, residence and preference tracks. Residence/preference stay separate review/evidence targets. New work unlocks after exposure to all linked starter `reviewKey`s; correctness is not required. Error = attempt/exposure, not mastery. Do not recreate these activities and do not jump automatically to `FREE_TEXT`.

## 4. REVIEW-FIRST / FSRS — DO NOT DUPLICATE

Second-transfer review:
- is separate from starter review;
- consumes schedules created through the generic scheduler;
- uses strong closed REORDER/FILL_IN variants;
- never creates its own schedules;
- uses generic `ReviewScheduleState` keyed by `reviewKey`.

Composed A1 priority remains:
1. due starter review;
2. due second-transfer review;
3. second-transfer new work;
4. starter new target;
5. NONE_DUE;
6. NO_CONTENT.

A2–C2 retain starter behavior. Never create another scheduler, FSRS persistence path, storage system or artificial mastery state. Variants sharing a `reviewKey` update the same schedule.

## 5. REAL LEARNING UI — CURRENT GREEN BEHAVIOR

Primary queue:
- production: `app/src/main/java/com/chiu/know/model/LearningActivityQueue.kt`
- model guard: `app/src/test/java/com/chiu/know/model/LearningActivityQueueTest.kt`
- persistence integration: `app/src/test/java/com/chiu/know/model/LearningActivityQueuePersistenceIntegrationTest.kt`
- UI: `app/src/main/java/com/chiu/know/ui/ChiuKnowApp.kt`

Normal attempt path remains:
`isLearningAnswerCorrect(...) → learningEvidenceFor(...) → encodeLearningEvidence(...) → updateReviewScheduleStateSet(...)`.

### Submission/retry hardening

`LearningActivityScreen` protects repeated submission in multiple layers:
- Verify is disabled when `checked == true`;
- click handler also guards `if (!checked)` before `onAttempt(...)`;
- MULTIPLE_CHOICE reselecting the same option after feedback does not reset `checked`;
- selecting a genuinely different option resets `checked = false` and allows retry;
- REORDER/text edits still reset `checked` because they genuinely change the answer.

### Feedback visibility — RESOLVED

Normal learning keeps the submitted activity pinned in `feedbackActivity` while feedback is displayed. Persistence still happens immediately in the existing DataStore/evidence/schedule path. Queue recomputation behind the UI cannot replace the feedback screen before the learner chooses to leave/continue. Optional practice stays outside this persistence path.

Proof:
- `0b37447ccdabfde5f8b4699605468ca37dffcfdb` — CI #391 SUCCESS.

### Post-feedback progression — GREEN

After a correct normal-learning answer, a dedicated Continue action is available instead of forcing a trip back to the trail.

Safety semantics:
- feedback remains visible;
- Continue cannot be used while persistence is pending;
- after persistence, Continue clears `feedbackActivity` and returns selection to the already-existing queue;
- review-first remains controlled by that queue;
- incorrect answer still allows a genuine edit + retry;
- Back to path remains available;
- optional practice remains outside scheduler-mutating progression.

Proof:
- `081725bd95c685058da003f9a8bb0e9e04b2e52d` — CI #392 SUCCESS.

### Retry persistence serialization — GREEN

The normal learning flow now serializes retries with persistence using `pendingLearningPersistenceCount` in the `AppStep.LEARNING_ACTIVITY` scope, keyed by target language and estimated level.

Protected behavior from CI #394:
- normal-learning `canSubmit = pendingLearningPersistenceCount == 0`;
- Verify requires nonblank answer, `!checked`, and `canSubmit`;
- its handler also guards `!checked && canSubmit`;
- the counter increments before the DataStore write and decrements in `finally`;
- therefore a corrected retry cannot be persisted concurrently with the previous attempt;
- Continue is also held until persistence is finished;
- optional practice deliberately keeps `canSubmit = true` because it does not persist evidence or schedules.

Proof:
- `d880f924008d11ea331bb8b0c553a0678e8d6559` — `fix: serialize learning retries with persistence` — CI #394 SUCCESS.

### Due-time refresh

When the queue is `NONE_DUE`, a refresh tick plus `LaunchedEffect` waits for `nextDueAtEpochMillis` and recomputes at due time. This does not create another scheduler or alter due-date calculation.

## 6. OPTIONAL “PRACTICE MORE” — PROTECTED

Production: `LearningOptionalPractice.kt`.
Test: `LearningOptionalPracticeTest.kt`.

Rules:
- A2–C2 preserve starter optional-practice behavior;
- A1 stays starter-only until second-transfer initial sequence is fully exposed;
- then strong second-transfer REORDER/FILL_IN variants may join optional practice;
- optional practice is exposure balancing, not mastery;
- it does **not** persist `LearningEvidence` and does **not** call `updateReviewScheduleStateSet(...)`;
- it cannot create/mutate FSRS schedules;
- it is offered only from `NONE_DUE` and never outranks due review;
- CI #394 must not be “simplified” by applying the normal persistence counter to optional practice, because there is intentionally no attempt persistence there.

Do not revert this to `starterLearningActivityForEvidence(...)` unless a real regression requires it.

## 7. RESOLVED SEAMS / NEXT SAFE INVESTIGATION

Resolved:
- trail availability: NO BUG under current EN/PT/ES/FR/KO architecture;
- placement rapid-double-tap: guarded per `question.id`;
- learning rapid duplicate submission: guarded;
- MULTIPLE_CHOICE same-selection retry loophole: guarded;
- due-time stale queue: fixed;
- feedback disappearing after persistence: fixed;
- correct-answer progression requiring forced path round-trip: fixed with Continue after persistence;
- corrected retry racing an earlier DataStore write: fixed by serializing normal-learning persistence in CI #394.

### Next safe technical investigation

Investigate first; do not assume a bug. Check the current end-to-end lifecycle after Continue and retry serialization, especially:
- whether rapid repeated taps on Continue can trigger an unintended extra transition before recomposition;
- whether persistence-complete/counter state resets correctly when the next activity ID changes;
- whether optional practice exits/loops exactly as intended and remains non-persistent;
- whether `NONE_DUE` / `NO_CONTENT` states reached after Continue remain consistent with the queue contract.

If code proves a real issue, make only the smallest supported correction. Do not invent a new progression state, scheduler, queue or storage layer.

## 8. LEARNINGACTIVITY CONTRACT

Never invent fields. `LearningActivity` has no `languageCode` property.
Required current fields include `id`, `level`, `primarySkill`, `learningObjective`, `knowledgeTarget`, `responseType`, `prompt`, `feedback`, `reviewKey`, `acceptedAnswers`, plus `responseOptions` where applicable; `audioPromptId` is optional.

Language filtering uses established ID-prefix semantics where needed. CI #341 previously failed because a nonexistent `languageCode` property was invented and required fields were omitted. Do not repeat it.

## 9. PEDAGOGY / CLAIMS — PERMANENT

Quality > quantity. Preferred progression where defensible:
`context → recognition → cued retrieval → fewer cues → new context → spaced review → retention/transfer`.

Useful feedback and retry after error are product rules. Do not fabricate mastery, robust free-writing evaluation, speaking/pronunciation/ASR, per-skill proficiency, psychometric validation or CEFR certification. `FREE_TEXT` enum existence does not mean open writing is robustly evaluated. Error remains attempt/exposure, not mastery. XP/streak/preferences are not CEFR mastery evidence.

## 10. KOREAN

Mandatory human-review gate is superseded. Rigorous second AI linguistic review is accepted; external human review is desirable but not blocking. Never call AI review human review, psychometric validation, CEFR certification or independent validation.

EN/PT/ES/FR/KO placement currently uses `QUALITY_SESSION`, subject to real code. Reviewed A1 forms include `안녕하세요`, `저는 미아예요`, `저는 치우예요`, `제 이름은 치우예요`, `이름이 뭐예요?`, `어디에 살아요?`, `리우에 살아요`, `무엇을 좋아해요?`, `커피를 좋아해요`, `저는 책이 있어요`, `감사합니다`, `고맙습니다`, `또 봐요`. Do not reopen without a real linguistic reason.

## 11. SUPABASE — ABSOLUTE SEPARATION

CHIU KNOW?:
- project `uskxabsodcnzlovuaurp`
- org `aeerqbmrwulxsawhjyvm`
- region `sa-east-1`

CHIU PLAYER:
- project `hpcbkvbrlwjnwlikmbfb`
- org `nnrwosbnvdvzaoflwxlo`
- forbidden while working on Chiu Know?.

Never mix resources, quotas, buckets, functions, credentials or secrets. Never put `service_role` in the APK. Auth deep link physically tested: `chiuknow://auth-callback`.

## 12. VOICE / VISUAL — ABSOLUTE

Official private voice: `Chiu-animada-recorte-final.m4a`, approximately 15.4 s / 309 KB. Do not put it on public GitHub, external provider, Supabase or APK without explicit user authorization. A private bucket existing is not upload authorization.

Two Chius must never be confused:
- realistic white Chihuahua with brown hair = APK logo/icon ONLY;
- yellow/weird cartoon Chihuahua = ALWAYS internal stories/cards/exercises/screens/app universe.

Do not arbitrarily redraw approved masters. Any new pose must be shown to the user and approved before APK integration. Official characters: Chiu, Mia, Jurandir, Barto, Lara, Caca, Onça, Perry, Lena. Jurandir is the definitive mosquito name; never revert to Zé Pernilongo except historical mention.

## 13. HANDOFF RULE

When the user says `Continue`, fetch real HEAD + exact CI first. Then read this compact state and `CURRENT_HANDOFF.md`, reread relevant code, select the smallest safe next slice, implement, and stop at the exact new CI gate. Stop earlier only for a genuine product decision, explicit authorization requirement, voice/data/publication requirement, or required visual approval.

The next chat must not trust a SHA copied into a prompt as current without verifying `main`; documentation may itself have created a newer commit. Real GitHub state always wins.