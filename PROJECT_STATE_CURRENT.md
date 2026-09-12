# CHIU KNOW? — CURRENT PROJECT STATE


## AUTORITATIVO — 2026-09-12 — USER-DEFINED VOICE REFERENCES FOR REMAINING CAST

The user supplied the following artistic references. They define character direction, not permission to clone or impersonate a specific actor or dub performance:
- **Caca** — inspired by the Brazilian-dub energy associated with Marge Simpson: mature feminine voice, low and raspy, dry/strained comic texture, but still natural and intelligible;
- **Barto** — inspired by Chef in the Brazilian version of South Park: deep adult masculine voice, warm, resonant, confident and comically theatrical;
- **Onça** — inspired by Brazilian voice actor Rita Lopes: mature feminine voice, warm, agile, theatrical and strongly expressive;
- **Perry** — inspired by Goku in the Brazilian version of Dragon Ball Z: bright heroic masculine energy, youthful, intense, enthusiastic and capable of explosive emphasis;
- **Lara** — inspired by talking-parrot voices in commercials: sharp, nasal, fast, noisy, opinionated and birdlike, while remaining natural rather than electronically distorted;
- **Lena** — inspired by Betty Faria's vocal presence: mature feminine voice, warm, smoky, confident and elegant, delivered with Lena's deliberately slow rhythm.

Operational guardrails:
- generate original character performances from these high-level traits; do not reproduce a recognizable person's identity or reuse copyrighted dialogue;
- all voices must remain distinct, natural, expressive and non-robotic;
- every character requires sample-level user review before any audio enters GitHub or the APK;
- these directions do not approve any generated file in advance;
- **Jurandir** — inspired by Eric Cartman in the Brazilian version of South Park: original male performance with a childlike, very nasal, high/strained, spoiled, irritable and explosive comic energy; it must also suggest an agitated mosquito through performance rather than an electronic sine-wave overlay;
- every Jurandir attempt in this session was explicitly rejected: Edge/Antonio variants were robotic; synthetic buzz overlays did not sound like a mosquito; unprompted Chatterbox outputs had the wrong accent and character; Brazilian male, feminine/childlike and rough-woman prompted Chatterbox outputs still lacked the intended timbre and manner of speaking;
- the user supplied a roughly 22-second MP3 excerpt of Brazilian-dub Eric Cartman only to clarify the reference; it remains outside GitHub and the APK and must not be used to clone or reproduce a recognizable protected performance;
- no Jurandir candidate is approved or committed;
- honest next options are: obtain an original human performance with permission as a style prompt, redefine Jurandir with a different original voice, or pause Jurandir and develop easier original voices first;
- the user has not chosen among those options yet.

## AUTORITATIVO — 2026-09-12 — MIA VOICE PHYSICALLY APPROVED

User-reviewed voice direction:
- the user explicitly approved the final Mia candidate with **“Ficou ótimo”**;
- Mia is a kitten and her locked direction is delicate, sweet, young and natural rather than robotic;
- approved generated phrase: `Olá, Chiu! Meu nome é Mia. Eu gosto de livros.`;
- exact generation settings: `pt-BR-FranciscaNeural`, rate `+7%`, pitch `+42Hz`;
- earlier Dora/Kokoro variants, repeated indistinguishable variants, Ana with a US accent and the male/accented Parler result remain rejected.

Integrated in separately gated commits:
- `256266a5a680219676113125938b46f23495ec6c` — approved 24 kHz mono MP3 added as `app/src/main/res/raw/mia_voice_sample_girl.mp3` (24,912 bytes / 4.152 s) — Android CI #477, run `34710065890`: SUCCESS;
- `9e824c93a56d14856b11ebae7a6ec838a81f7eee` — Mia label and exact Portuguese sample sentence localized in EN/PT/ES/FR/KO — Android CI #478, run `34710280544`: SUCCESS;
- `8b34187bb77f3b36e8f28939bde6393ac770576d` — honest shared character-voice labels localized in EN/PT/ES/FR/KO — Android CI #479, run `34710485287`: SUCCESS;
- `51c1d481393bc2a90bbf67fbda732169804b8ebc` — Chiu and Mia exposed in the existing voice preview with mutually exclusive playback and resource cleanup — Android CI #480, run `34710674198`: SUCCESS.

Physical-device validation:
- the user installed the APK artifact from the green documentation state, Android CI #481, run `34710836455`;
- she tested the character-voice screen and confirmed the result with **“Ficou ok”**;
- Mia's bundled sample and its playback in the tested Android device are therefore accepted;
- the first attempt to install over an older debug APK ended with `App not installed`; uninstalling the older build and installing cleanly succeeded;
- this update behavior is consistent with GitHub CI debug APKs being signed with non-stable debug keys, so future seamless in-place updates require a deliberately managed stable signing setup rather than an ad-hoc code change.

Protected facts:
- the already-approved Chiu sample and its replay behavior remain unchanged;
- starting one character stops the other, so samples cannot overlap;
- both local `MediaPlayer` resources are released when the learner leaves the screen;
- the displayed Mia sentence is exactly the Portuguese sentence in the bundled file;
- the private Chiu source recording is not in GitHub or the APK;
- no LearningEvidence, queue, FSRS, schedule, mastery, Supabase resource or CHIU PLAYER resource changed;
- Mia's physical-device playback is accepted;
- voice directions for Jurandir, Barto, Lara, Caca, Onça, Perry and Lena still require individual review and approval.

## AUTORITATIVO — 2026-09-12 — PHYSICAL CHIU VOICE PLAYBACK APPROVED

Physical-device validation:
- the user installed the APK built from the green voice-integration state;
- she opened the approved Chiu voice preview and confirmed: **“Ficou ótimo”**;
- the bundled Expressiva sample is therefore accepted both as the Chiu voice direction and as working playback on the tested Android device.

Locked result:
- keep the approved generated sample and its current playback behavior;
- do not return the preview to Android TextToSpeech;
- do not publish or bundle the private source recording;
- future Chiu lines should follow the approved expressive parameters and still be checked for generation artifacts before APK integration;
- distinct voices for Mia, Jurandir, Barto, Lara, Caca, Onça, Perry and Lena remain a future reviewed workstream, not an implicit bulk-generation approval.

Verified base before this documentation write:
- HEAD `f0c538773077fb864bcf2bb6808e9949395d3646`;
- Android CI #475, run `34674482511`: `COMPLETED / SUCCESS`.

## AUTORITATIVO — 2026-09-12 — APPROVED CHIU SAMPLE IN THE APK, CI #474 GREEN

The user explicitly approved the **Expressiva** generated candidate.

Integrated in separately gated commits:
- `a42605342a6de69de2e479db11fb432632821e26` — approved generated WAV added as `app/src/main/res/raw/chiu_voice_sample_expressive.wav` — Android CI #472 SUCCESS;
- `3efa081b35bb5585b199543241ef02fd8035a579` — honest localized labels in EN/PT/ES/FR/KO — Android CI #473 SUCCESS;
- `856cc35e79191fe50fc3255990527038f10e146e` — voice preview now plays the bundled approved WAV through `MediaPlayer` instead of Android TextToSpeech — Android CI #474 SUCCESS.

Protected facts:
- the APK contains only the reviewed generated sentence, not `Chiu-animada-recorte-final.m4a`;
- the private source recording remains outside public GitHub and the APK;
- the sample sentence is Portuguese and is displayed exactly as spoken;
- replay restarts the same approved local asset and playback resources are released when leaving the screen;
- no LearningEvidence, queue, FSRS, schedule, mastery, Supabase resource or CHIU PLAYER resource changed;
- physical-device listening remains required before treating device playback as accepted;
- bulk line generation remains subject to output validation; approval of the direction is not blanket approval of every future generated file.

## AUTORITATIVO — 2026-09-12 — CHIU EXPRESSIVE VOICE DIRECTION APPROVED

User-reviewed result:
- the user explicitly selected the **Expressiva** candidate as the approved Chiu voice direction;
- approved sample phrase: `Olá! Eu sou o Chiu. Vamos aprender juntos.`;
- generation settings used for that candidate: Chatterbox Multilingual, `language_id="pt"`, `exaggeration=0.78`, `cfg_weight=0.30`;
- the approved direction is expressive, natural and non-robotic; it is a voice-direction approval, not permission to accept unreviewed bulk outputs blindly.

Reproducible free workflow:
- `tools/voice_generation/chiu_voice_colab.ipynb` contains the zero-cost Google Colab workflow;
- `cd24a915faea57785836aff09c55e7dd0641c31c` added the notebook — Android CI #469 SUCCESS;
- `b85c6895c2e2df13802338d6998b4ac8da8644b1` aligned dependencies, private upload steps and model loading with the execution that actually generated the reviewed samples — Android CI #470 SUCCESS.

Privacy and integration gates remain:
- neither the canonical source recording nor the generated WAV candidates is stored in public GitHub;
- the source recording must never enter the public repository or APK;
- generated final lines may enter the APK only after file-level validation and appropriate review;
- CHIU PLAYER remains out of scope;
- other official characters still require distinct reviewed natural voices.

## AUTORITATIVO — 2026-09-12 — VOICE AUTHORIZATION RECONFIRMED

The user corrected an incomplete historical record and explicitly reconfirmed:
- the canonical Chiu source recording is `Chiu-animada-recorte-final.m4a`, approximately 15.4 seconds / 309 KB;
- the recording may be uploaded to **private storage in the CHIU KNOW? Supabase project only**;
- it may be used as the authorized source/reference for producing the remaining Chiu lines through voice cloning/generation;
- the other official characters must also receive distinct generated voices;
- every character voice must sound natural, expressive and non-robotic;
- the previously approved “third option” remains the intended Chiu direction, and the same natural-quality standard applies to the other characters.

Scope and safety:
- this authorization supersedes the older blanket “do not upload without explicit authorization” gate for the specific private-storage and voice-production purposes above;
- it does not authorize publishing the source recording in the public GitHub repository;
- it does not authorize using the CHIU PLAYER project or mixing its storage, credentials or quotas;
- before sending the source recording to a specific external voice provider, verify the provider, cost, retention/privacy terms and technical fit; do not silently choose a paid or privacy-sensitive provider;
- generated character audio may enter the APK only after its voice direction is reviewed and accepted; the private source recording itself should not be committed to public GitHub.

Current physical-test finding:
- the APK's `VoiceSampleScreen` still uses Android `TextToSpeech` with temporary NEUTRAL/CALM/LIVELY pitch/rate variants;
- therefore the voice heard in the current APK is not the approved recording and is not a clone;
- replacing that temporary preview is now an approved workstream, subject to the provider/storage gates above.


## AUTORITATIVO — 2026-09-11 — EXPLICIT PRACTICE-NOW HANDOFF, CI #465 GREEN

User decision:
- option 1 was explicitly approved;
- after the final square comprehension, the app now shows a localized **Practice now** transition instead of returning directly to the trail.

Implemented in separately gated commits:
- localized action and honest description in EN/PT/ES/FR/KO: `5aa93904c9d9846a91a657847c850751a0824a1e` — Android CI #462 SUCCESS;
- guarded `PracticeNowScreen` with an explicit action and clear Back to path: `c71ebf3e83ffc8baa00920f15b8c1c7184b0ebdd` — Android CI #463 SUCCESS;
- final square comprehension → Practice now → existing normal learning activity flow: `e55fc120821f207f84bcdaab2e8bf0a5d3b04886` — Android CI #464 SUCCESS;
- all-language queue contract test for comprehension → related first retrieval step: `f33fa1c7143a8b92256bf443b4bffa62f2fc65a8` — Android CI #465, run `34659625287`: COMPLETED / SUCCESS.

Protected semantics:
- the transition creates no activity, queue, scheduler, FSRS state, mastery state or persistence format;
- it enters the existing `AppStep.LEARNING_ACTIVITY` path;
- the existing review-first priority remains authoritative;
- when no due review outranks new work, observed square comprehension leads to the first not-yet-observed retrieval activity in the same second-transfer unit;
- the validated target order and cue withdrawal remain `MULTIPLE_CHOICE → REORDER → FILL_IN`;
- normal attempts continue through the existing serialized LearningEvidence and review-schedule path;
- optional practice remains separate and non-persistent;
- Back to path remains available;
- no visual asset, voice file, Supabase resource or Chiu Player resource was changed.

The previous pending choice between Practice now and direct return is resolved and must not be asked again.

## HANDOFF OPERACIONAL — 2026-09-11 — PAUSE AFTER CI #460

Exact verified base before this handoff write:
- HEAD `73115c5ea9e72d2292cced5383aab48081552381`;
- commit `docs: record observed practice screen through CI 459`;
- Android CI #460, run `34657975904`: `COMPLETED / SUCCESS` on that exact SHA.

Current user-visible A1 experience:
- café story → café comprehension → explicit Next story preview;
- park story → park comprehension → explicit Next story preview;
- square story → square comprehension → trail;
- all three approved surreal assets are in `drawable-nodpi`;
- the trail exposes the normal compatible learning queue, the three-story experience, observed-practice summaries and temporary voice preview;
- observed practice is descriptive only and cannot be called mastery, score or CEFR certification.

Exact pending product decision — DO NOT ASSUME:
After the final square comprehension, the current app returns directly to the learning trail. The assistant proposed:
1. offer an explicit localized **Practice now** transition into active retrieval with fewer cues (recommended); or
2. preserve the direct return to the trail.
The user has not yet chosen. The next chat must ask/receive this choice before changing that final transition.

If option 1 is approved:
- first recheck real HEAD and exact-SHA Android CI;
- inspect the existing compatible queue and the already-written A1 MULTIPLE_CHOICE → REORDER → FILL_IN tracks;
- do not create a parallel activity sequence, queue, scheduler, FSRS implementation or mastery state;
- determine whether the existing queue can supply the intended active retrieval without exposing unrelated/due work;
- if a dedicated handoff into the existing activities cannot be made honestly, stop and request a narrower product decision;
- add localized UI text, production wiring and tests as separate gated commits when practical;
- preserve persistence serialization, retry, correct-feedback lock, review-first priority and optional-practice separation.

No uncommitted GitHub write or code change is pending at this pause.


## AUTORITATIVO — 2026-09-11 — HONEST OBSERVED-PRACTICE SCREEN, CI #459 GREEN

A concrete product gap was closed: descriptive evidence-by-skill summaries already existed and were tested, but were not visible in the app.

Implemented:
- localized observed-practice labels in EN/PT/ES/FR/KO;
- localization commit `24ad7db267da8c4531deadaeb5592ef6f8a6dfbf` — Android CI #457 SUCCESS;
- `ObservedPracticeScreen` displays only observed skill, CEFR level, total/correct/incorrect attempts and distinct practiced targets;
- an empty state is shown honestly before any learning attempt;
- the screen explicitly states that observations are not a proficiency score or mastery;
- screen commit `ee4b8b6759159dacc96c4cd27e8a05aad8b23cb8` — Android CI #458 SUCCESS;
- the learning trail now exposes “View observed practice” and feeds it only `summarizeLearningEvidenceBySkill(persistedLearningEvidence)` for the selected target language;
- wiring commit `217178805f203c9772f8c32879857a682c3ffa6a` — Android CI #459, run `34657828705`: `COMPLETED / SUCCESS`.

Protected semantics:
- no percentages, grade, stars, ranking, mastery, CEFR sub-level or certification are calculated;
- empty skills are omitted rather than assigned artificial zeroes;
- the screen is read-only and does not change evidence, queue, FSRS, unlocks or learner preferences;
- target-language separation follows the existing persisted evidence key;
- the underlying summary model and its dedicated tests were not duplicated or rewritten.


## AUTORITATIVO — 2026-09-11 — COMPLETE THREE-CONTEXT A1 STORY CHAIN, CI #455 GREEN

Approved third visual:
- the user explicitly approved the first square candidate with “Está linda”;
- exact master: `exec-7ec17e7d-fafa-4ada-a44d-f3fcaa53c325.png`;
- Android derivation: `app/src/main/res/drawable-nodpi/a1_story_square_surreal.webp`, 1280×853, approximately 258 KB;
- asset commit `326c9433df6a9ade4bd92b63ec080ecceb2bf1c2` — Android CI #453 SUCCESS;
- `VISUAL_BIBLE.md` records the exact approved composition, characters and no-retouch rule;
- approval record commit `30e4b47fe273b529958103ab18b5432dbea261f2` — Android CI #454 SUCCESS.

Complete connected experience:
- the A1 flow now exposes all three already-validated narrative contexts;
- café story → café comprehension → explicit Next story preview;
- park story → park comprehension → explicit Next story preview;
- square story with Chiu and Barto → square comprehension → trail;
- each preview uses the correct approved 3:2 asset and requires an explicit guarded learner action;
- production commit `a79e6ebf88b9ffd7d035b6fbf2cc33151e2cfdab` — Android CI #455, run `34655079852`: `COMPLETED / SUCCESS`.

Protected semantics:
- no silent transition between stories;
- rapid repeated Next story taps remain guarded;
- Back to path is available at each story and preview;
- viewing cards creates no evidence, schedule, unlock or mastery;
- closed comprehension attempts use the existing serialized LearningEvidence path;
- narrative comprehension remains outside the review queue and creates no orphan FSRS schedule;
- no new queue, persistence format, scheduler or artificial mastery state was introduced;
- the content and its all-language model tests existed before this UI exposure and were not rewritten.


## AUTORITATIVO — 2026-09-11 — EXPLICIT CAFÉ → PARK STORY TRANSITION, CI #451 GREEN

Approved second visual:
- the user confirmed the **second** generated park candidate as approved;
- exact master: `exec-d9eeaba2-6ee3-468e-90e9-00a1c1595fed.png`;
- Android derivation: `app/src/main/res/drawable-nodpi/a1_story_park_surreal.webp`, 1280×853, approximately 259 KB;
- asset commit `e1cee4e291d0dce9fb6d2bf27ef1b3f1bd582e96` — Android CI #446 SUCCESS;
- `VISUAL_BIBLE.md` records the first park candidate as rejected for insufficient surrealism and the second as the only canonical version;
- approval record commit `7db69617dc97acc0e9502db905a078fab5a21d20` — Android CI #447 SUCCESS.

Connected story experience:
- after the café cards and their dedicated comprehension, the app offers the park as **Next story**;
- localized action exists in EN/PT/ES/FR/KO;
- localization commit `af29f58329bec09475b197e48f439c4bbab1796d` — CI #449 SUCCESS;
- `NextNarrativeScreen` shows the park title, setting and approved image, with a guarded explicit action and a clear Back to path;
- component commit `3c50c36fb840c6cd359d1369d2a9ee1c46e90f7d` — CI #450 SUCCESS;
- the final flow is café story → café comprehension → explicit Next story preview → park story → park comprehension → trail;
- wiring commit `9c874a4cef0ac7936e94b2748e5c2ac307b45eb9` — Android CI #451, run `34651914665`: `COMPLETED / SUCCESS`.

Protected semantics:
- there is no silent auto-advance from café comprehension into the park;
- rapid repeated taps on Next story are guarded;
- Back to path remains available before starting the park;
- story viewing writes no evidence, schedule, mastery or unlock;
- comprehension attempts reuse existing LearningEvidence persistence guards and remain outside the review queue, so no orphan FSRS schedule is created;
- no new persistence format, queue, scheduler or mastery state was introduced;
- only the first two of the already-validated three A1 narrative contexts are exposed; the third remains unexposed and has no approved visual asset.


## AUTORITATIVO — 2026-09-11 — FIRST A1 STORY EXPERIENCE IN APP, CI #444 GREEN

Approved visual:
- final canonical café scene: `exec-123a0c4c-da72-429d-8791-a25d610d2db7.png`;
- Android derivation: `app/src/main/res/drawable-nodpi/a1_story_cafe_surreal.webp`, 1280×853, approximately 184 KB;
- the derivation preserves the approved composition and replaces all earlier rejected café candidates;
- asset-only commit `7a8942e4d99852c53c84021d65121dd651ac9cac` — Android CI #438 SUCCESS.
- `VISUAL_BIBLE.md` records the exact approved scene and makes strongly surreal/caricatural/crazy settings the future minimum; approval-doc commit `c0edfe1fc62e6098e30b8fc9c68023ace5d9a7a1` — CI #437 SUCCESS.

Sequential UI foundation:
- `NarrativeCardScreen` shows title, setting, approved 3:2 image, one dialogue beat, progress, guarded Continue and clear Back;
- commit `d25c3cf79cd2b26e3ca1fc2950567e285eb26b91` — Android CI #439 SUCCESS;
- localized `read_story` action exists in EN/PT/ES/FR/KO;
- commit `ca7dbc8dd2d0b98fec02028de57ee7b185dbf5ce` — Android CI #440 SUCCESS.

Story-session contract:
- `NarrativeSessionProgress` enforces STORY → COMPREHENSION → COMPLETE;
- six story beats remain visible one at a time;
- the two dedicated first-story comprehension activities follow in order;
- state advances are idempotent outside their phase and language/level mismatches fail closed;
- production commit `72ab059383f785cf0ab3a7587a7fe6a6c478f290` — CI #441 SUCCESS;
- all-language dedicated tests commit `20f52ad2800ab14a171f07cf60398dc72e76098e` — CI #442 SUCCESS.

Real app integration:
- A1 trail exposes “Read the story” only when narrative plus dedicated comprehension exist;
- flow is trail → illustrated six-card story → two comprehension activities → trail;
- story viewing alone writes no evidence, schedule, unlock or mastery;
- comprehension attempts reuse the existing correctness and LearningEvidence encoding path;
- retry, correct-feedback lock, rapid Continue guard and persistence exit serialization remain active;
- integration commit `4c030828e11edbf91f76223c2a74d13feefe7db8` — CI #443 SUCCESS;
- narrative comprehension remains outside the review queue, so it deliberately writes evidence without creating orphan FSRS schedules;
- correction commit `8985e79f3eace7e79fc34a4c64b35a0b1ec68582` — Android CI #444, run `34627106257`: `COMPLETED / SUCCESS`.

No second/third narrative was exposed in the UI, and no unapproved park/square asset was added. The next visual step may create an approval candidate for the already-written park narrative, preserving canonical characters and the strengthened surreal-setting rule.


## AUTORITATIVO — 2026-09-11 — SEQUENTIAL STORY CARDS + CANONICAL VISUAL LOCK, CI #433 GREEN

User-approved story experience:
- title and setting first;
- dialogue shown one beat at a time in sequential cards;
- explicit Continue between beats;
- comprehension and the existing closed retrieval progression follow the story;
- viewing narrative context alone creates no LearningEvidence, FSRS update, mastery or transfer claim.

Verified implementation foundation:
- `94605071e5b4574728913ca139deeae47d29ab52` — immutable/idempotent `NarrativeCardProgress` model — Android CI #431 SUCCESS.
- `8d1e2db27377a49afb9782f816c3f6cd2bb07efa` — tests for one-beat-at-a-time advancement, completion after the final beat, invalid-index rejection and post-completion idempotence — Android CI #432 SUCCESS.
- These commits do not yet wire narratives into `ChiuKnowApp.kt` or the learning queue.

Visual continuity:
- the user re-supplied the three previously approved visual boards in the working chat;
- no binary was uploaded to the public repository or APK;
- `VISUAL_BIBLE.md` now declares the first canonical cast board dominant for identity;
- only pose, interaction and situation may change; crazy/asymmetric faces, oversized eyes, teeth, muzzles, proportions, colors, clothes and accessories must not be softened, beautified or reinterpreted;
- `40f2635d801f999288a67965e767e4b87f2598a6` — visual identity lock documentation — Android CI #433, run `34623551075`: `COMPLETED / SUCCESS`.

Candidate status:
- first generated Chiu-and-Mia café scene: REJECTED because faces were softened and too normal;
- second revised café scene: CANDIDATE ONLY / NOT APPROVED; it must not enter the APK without explicit approval;
- if any generated candidate conflicts with the first canonical cast board, the canonical board wins.


## AUTORITATIVO — 2026-09-11 — CONNECTED A1 NARRATIVES + SHARED CUE CONTRACT, CI #429 GREEN

Latest verified production/test state before this documentation write:
- `01da3053261507dfe5604a8b366183f964b84ccc` — A1 second-transfer tracks now validate their unchanged MULTIPLE_CHOICE → REORDER → FILL_IN order through the shared `LearningCueStage` contract — Android CI #426 SUCCESS.
- `42080e5d60d60e9e7570a5738978301859d4ef9b` — integration test rejects unclassified FREE_TEXT inside the current A1 cue-withdrawal track — Android CI #427 SUCCESS.
- `0f60865b9f6a4ace20084347ef05a5bb78e73d9c` — `NarrativeLearningSequence` connects the three existing A1 contexts structurally — Android CI #428 SUCCESS.
- `e2ff9b6a2fb1b9a0571b9052e29a5cb705a1563f` — dedicated narrative-sequence tests for EN/PT/ES/FR/KO — Android CI #429, run `34621087204`: `COMPLETED / SUCCESS`.

Protected semantics:
- later narratives may reuse only review targets introduced by the first context;
- sequence units must share language and CEFR level while keeping distinct IDs and settings;
- this is curriculum structure only: it does not prove transfer, create evidence, unlock content, alter FSRS or enter the learning queue;
- no narrative text, character, activity, UI or visual asset changed.

Read-only UI audit after CI #429:
- neither `ChiuKnowApp.kt` nor `LearningActivityQueue.kt` currently references narratives;
- therefore exposing stories is a real product/visual decision, not a safe implicit wiring change;
- next work must obtain the user's choice for the first story experience before integrating it.


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
