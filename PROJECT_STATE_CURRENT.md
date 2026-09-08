# CHIU KNOW? — CURRENT PROJECT STATE

## AUTORITATIVO — 2026-09-07/08 — A1 SECOND TRANSFER CONNECTED TO COMPATIBLE LEARNING QUEUE; CI #375 GREEN

This compact file is the current operational project-state checkpoint. It exists because the historical `PROJECT_STATE.md` is now too large for safe full round-trip editing through the current GitHub connector and is returned truncated. **Do not overwrite the historical `PROJECT_STATE.md` from a truncated read.** Read this file first, then `CURRENT_HANDOFF.md`, `PROJECT_STATE.md`, `PRODUCT_SPEC.md`, `PEDAGOGY_ARCHITECTURE.md`, and `VISUAL_BIBLE.md` before visual work. The real GitHub `main` HEAD and exact CI for that SHA always remain the final source of truth.

## 1. EXACT CHECKPOINT BEFORE THIS DOCUMENT COMMIT

- Code/test HEAD: `1e3601789321cb65479afbb342997ff91da61f52`
- Commit: `test: guard compatible learning activity queue`
- Android CI #375, run `34174752286`: `COMPLETED / SUCCESS`.
- Production adapter immediately before it: `6479a02dad9a21dd84907d4713215dc9655c5242` — `feat: expose compatible learning activity queue` — Android CI #374 `SUCCESS`.

This documentation commit will create a newer HEAD. A new chat MUST query `main` again and verify the Android CI for the exact new HEAD before writing anything.

## 2. MANDATORY DEVELOPMENT WORKFLOW

Before every write:
1. Fetch real `main` HEAD.
2. Fetch Android CI for exactly that SHA.
3. If queued/in_progress: no writes; remain at gate.
4. If failed: inspect job/logs and fix only the real failure.
5. If success: re-read every file to be changed and use its current blob SHA.
6. Make one small, reversible, testable change.
7. Wait for CI on that exact commit.
8. Continue only after green.

Never stack production + test + docs writes behind a running CI. Never infer rollback from stale documentation. Never ask the user to do terminal/manual coding when the connected tools can perform the work.

## 3. A1 SECOND TRANSFER — SQUARE — CURRENT CONTENT

Narrative file: `A1SecondTransferNarrativeMicroUnit.kt`.
Exactly 6 beats per EN/PT/ES/FR/KO with Barto and Chiu.

EN: `Hello, Chiu!` / `Hello, Barto!` / `Where do you live?` / `I live in Rio.` / `What do you like?` / `I like coffee.`
PT: `Olá, Chiu!` / `Olá, Barto!` / `Onde você mora?` / `Eu moro no Rio.` / `Do que você gosta?` / `Eu gosto de café.`
ES: `¡Hola, Chiu!` / `¡Hola, Barto!` / `¿Dónde vives?` / `Vivo en Río.` / `¿Qué te gusta?` / `Me gusta el café.`
FR: `Bonjour, Chiu !` / `Bonjour, Barto !` / `Où est-ce que tu habites ?` / `J’habite à Rio.` / `Qu’est-ce que tu aimes ?` / `J’aime le café.`
KO: `안녕하세요, 치우!` / `안녕하세요, 바르토!` / `어디에 살아요?` / `리우에 살아요.` / `무엇을 좋아해요?` / `커피를 좋아해요.`

The square now has protected closed progressions for BOTH preference and residence:
- contextual comprehension;
- cued retrieval / MULTIPLE_CHOICE;
- REORDER;
- FILL_IN.

Do NOT jump to FREE_TEXT. The current evaluator is not a robust open-writing evaluator and there is no validated open-writing policy. Do not claim free writing, speaking, pronunciation or mastery.

## 4. COMMITS/CI AFTER THE OLD CURRENT_HANDOFF CHECKPOINT

Preference square:
- `43a0fe7903a192c95ce61bb4e108a1e73d73d770` — preference REORDER — CI #347 SUCCESS.
- `5e17b57c689f126c7c45415edd330a2417b385b5` — guard preference REORDER — CI #348 SUCCESS.
- `3d6a3e81c619562dfbe5f1ff05dd5a4b969ef07b` — preference FILL_IN — CI #349 SUCCESS.
- `1295f346f10a992ada05118e6dbdd4b5193e8ed4` — guard preference FILL_IN — CI #350 SUCCESS.

Residence square:
- `9b036ce88385d71a18ef381b0513c823549e7dbe` — residence cued retrieval — CI #351 SUCCESS.
- `0d5a261739fe9bb667a23d27f00150856fa0f365` — guard residence cued retrieval — CI #352 SUCCESS.
- `4693549fc74b9e447d7d5e4ebc6c4cfd2ddf5e33` — residence REORDER — CI #353 SUCCESS.
- `0e363d24482dda530ff0a87119446ab63f77c031` — guard residence REORDER — CI #354 SUCCESS.
- `06e97ad1794068e97d035b2bb0f02f23eacf4447` — residence FILL_IN — CI #355 SUCCESS.
- `e436012bbaa9b90f855033f3c7efd37a1e8b8a2e` — guard residence FILL_IN — CI #356 SUCCESS.

Review/scheduling and connected unit:
- `4c4d8b47...` — second-transfer review queue — CI #357 SUCCESS.
- `793f5357...` — guard second-transfer review queue — CI #358 SUCCESS.
- `1819c90f...` — prove second-transfer review scheduling integration — CI #359 SUCCESS.
- `37207ac9...` — connect second-transfer learning unit — CI #360 SUCCESS.
- `1109730a...` — guard connected second-transfer learning unit — CI #361 SUCCESS.
- `742bb974...` — second-transfer new-work progression — CI #362 SUCCESS (subsequent main history confirmed progression continued beyond this point).

Subsequent main work already implemented and tested before the current adapter includes review-first priority and A1 queue composition. Do not recreate these layers. Inspect real history/files if exact intermediate SHAs are needed.

Compatible queue:
- `6479a02dad9a21dd84907d4713215dc9655c5242` — `feat: expose compatible learning activity queue` — CI #374 SUCCESS.
- `1e3601789321cb65479afbb342997ff91da61f52` — `test: guard compatible learning activity queue` — CI #375 SUCCESS.

## 5. REVIEW / FSRS FACTS NOW PROVED

- `ReviewScheduleState` persistence/scheduling is generic by `reviewKey`.
- Evidence variants sharing one `reviewKey` update one schedule rather than inventing mastery.
- Historical rebuild groups evidence by `reviewKey`.
- The second-transfer review queue is separate from the starter queue and consumes only schedules that already exist.
- It does not create schedules by itself.
- The app's generic evidence/schedule update path can schedule second-transfer targets when those activities are actually presented.
- Review-first policy is preserved: due review precedes new work.
- Error is an attempt/exposure, not mastery.
- Preferences/XP/streak are not CEFR mastery evidence.

## 6. CONNECTED A1 SECOND-TRANSFER LEARNING UNIT

`A1SecondTransferLearningUnit.kt` connects:
- narrative;
- comprehension;
- residence retrieval track;
- preference retrieval track.

Each retrieval track preserves the validated closed progression `MULTIPLE_CHOICE → REORDER → FILL_IN`.
Residence and preference remain separate evidence targets/review keys.
The unit does not change UI, FSRS or mastery semantics by itself.

The new-work selector only unlocks the square after exposure to the narrative's linked starter review keys. Exposure/attempt is enough; correctness/mastery is NOT required. It then chooses the first unattempted activity by `activityId`. It does not inspect due dates, so it cannot override due-review priority.

## 7. LEARNING ACTIVITY QUEUE ADAPTER

Production: `app/src/main/java/com/chiu/know/model/LearningActivityQueue.kt`.
Test: `app/src/test/java/com/chiu/know/model/LearningActivityQueueTest.kt`.

`learningActivityQueueSelection(...)` is a compatibility adapter for the current learning UI:
- A1 delegates to the composed A1 review-first queue, including connected second-transfer work.
- A2/B1/B2/C1/C2 delegate unchanged to `starterQueueSelection(...)`.
- It intentionally returns `StarterQueueSelection`, preserving existing UI handling and generic evidence/FSRS persistence.
- A1 `DUE_STARTER_REVIEW` and `DUE_SECOND_TRANSFER_REVIEW` map to `StarterQueueReason.DUE_REVIEW`.
- A1 `SECOND_TRANSFER_NEW_WORK` and `STARTER_NEW_TARGET` map to `StarterQueueReason.NEW_TARGET`.
- `NONE_DUE` preserves `nextDueAtEpochMillis`.
- `NO_CONTENT` remains `NO_CONTENT`.
- Negative time is rejected.

`LearningActivityQueueTest.kt` protects:
- A1 ready transfer new work → NEW_TARGET for EN/PT/ES/FR/KO;
- A1 second-transfer due review → DUE_REVIEW and REORDER/FILL_IN;
- A1 NONE_DUE preserves next due timestamp;
- unsupported A1 language preserves NO_CONTENT;
- A2–C2 remain exactly equivalent to starter queue;
- negative queue time is rejected.

## 8. NEXT SAFE TECHNICAL STEP

Do not execute blindly. First verify the new documentation HEAD and exact CI.

If green and no newer work exists, inspect `ChiuKnowApp` / the current learning screen and its tests to determine the smallest safe wiring change from direct `starterQueueSelection(...)` usage to `learningActivityQueueSelection(...)`.

Before writing UI integration:
- prove the current call site and state flow;
- preserve generic evidence creation and `updateReviewScheduleStateSet(...)` behavior;
- preserve `NO_CONTENT`, `NONE_DUE`, DUE_REVIEW and NEW_TARGET UI semantics;
- do not create a second scheduler or persistence path;
- do not expose FREE_TEXT;
- do not claim speaking/pronunciation/mastery;
- production change and test must be separate commits, each with green CI.

## 9. LEARNINGACTIVITY CONTRACT — DO NOT INVENT FIELDS

Current required fields include `id`, `level`, `primarySkill`, `learningObjective`, `knowledgeTarget`, `responseType`, `prompt`, `feedback`, `reviewKey`, `acceptedAnswers`; `responseOptions` as appropriate; `audioPromptId` optional.
There is NO `languageCode` property on `LearningActivity`. Filter language by ID prefix where needed: `it.id.startsWith("$languageCode-")`.
CI #341 previously failed because an implementation invented `languageCode` and omitted required fields. Never repeat it.

## 10. PERMANENT PROJECT GUARDRAILS

Pedagogy:
- Quality > quantity.
- Context → recognition → cued retrieval → fewer cues → new context → spaced review → retention/transfer, only where technically defensible.
- Error = attempt/exposure, not mastery.
- Never fabricate mastery or per-skill scores.
- FREE_TEXT enum existence is not permission to pretend open writing is evaluated.
- No validated speaking/pronunciation/ASR assessment.

Korean:
- Old mandatory-human-review gate is superseded. Rigorous second AI linguistic review is accepted; external human review remains desirable but not blocking.
- Never call AI review human review, psychometric validation, CEFR certification or independent validation.
- EN/PT/ES/FR/KO placement currently belongs to QUALITY_SESSION subject to real code.
- Strong reviewed A1 forms include `안녕하세요`, `저는 미아예요`, `저는 치우예요`, `제 이름은 치우예요`, `이름이 뭐예요?`, `어디에 살아요?`, `리우에 살아요`, `무엇을 좋아해요?`, `커피를 좋아해요`, `저는 책이 있어요`, `감사합니다`, `고맙습니다`, `또 봐요`. Do not reopen without real linguistic reason.

Supabase absolute separation:
- CHIU KNOW?: project `uskxabsodcnzlovuaurp`, org `aeerqbmrwulxsawhjyvm`, region `sa-east-1`.
- CHIU PLAYER: project `hpcbkvbrlwjnwlikmbfb`, org `nnrwosbnvdvzaoflwxlo` — forbidden while working on Know?.
- Never mix resources/quotas/buckets/functions/credentials/secrets.
- Never put `service_role` in APK.
- Auth deep link physically tested: `chiuknow://auth-callback`.

Voice:
- Official private voice: `Chiu-animada-recorte-final.m4a`, about 15.4 s / 309 KB.
- Do not put it on public GitHub, external provider, Supabase or APK without explicit user authorization.
- Private bucket existence does not imply upload authorization.

Visual absolute rule:
- Realistic white Chiu with brown hair ONLY APK icon/logo.
- Yellow/weird cartoon Chiu ALWAYS internal character in stories/cards/exercises.
- Never mix or substitute them.
- Approved masters are not arbitrarily redrawn.
- Any new pose must be shown to and approved by the user before integration.
- Official characters: Chiu, Mia, Jurandir (mosquito; definitive name, NOT Zé Pernilongo), Barto, Lara, Caca, Onça, Perry, Lena.

## 11. HANDOFF RULE

When the user says `Continue`, do not ask what to do. Verify real HEAD + exact CI, read the current state files and relevant code, then continue the next small safe slice autonomously. Stop only for a real gate, a genuine product decision, explicit authorization requirement (voice/data/publication), or required visual approval.