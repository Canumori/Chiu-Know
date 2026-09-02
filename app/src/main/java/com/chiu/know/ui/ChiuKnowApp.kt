package com.chiu.know.ui

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.speech.tts.UtteranceProgressListener
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.chiu.know.R
import com.chiu.know.model.CefrLevel
import com.chiu.know.model.CefrTrailStatus
import com.chiu.know.model.LanguageOption
import com.chiu.know.model.LearningActivity
import com.chiu.know.model.TemporaryVoiceStyle
import com.chiu.know.model.temporaryVoiceSamples
import com.chiu.know.model.voiceSamplePhrase
import com.chiu.know.model.PlacementQuestion
import com.chiu.know.model.ResponseType
import com.chiu.know.model.advanceAdaptivePlacement
import com.chiu.know.model.buildCefrTrail
import com.chiu.know.model.StarterQueueReason
import com.chiu.know.model.decodeLearningEvidenceSet
import com.chiu.know.model.decodeReviewScheduleStateSet
import com.chiu.know.model.encodeLearningEvidence
import com.chiu.know.model.updateReviewScheduleStateSet
import com.chiu.know.model.isLearningAnswerCorrect
import com.chiu.know.model.learningEvidenceFor
import com.chiu.know.model.rebuildReviewScheduleStates
import com.chiu.know.model.placementQuestionForLevel
import com.chiu.know.model.startAdaptivePlacement
import com.chiu.know.model.starterLearningActivityFor
import com.chiu.know.model.starterLearningActivityForEvidence
import com.chiu.know.model.starterQueueSelection
import com.chiu.know.model.starterPlacementQuestionsFor
import com.chiu.know.model.supportedInterfaceLanguages
import com.chiu.know.model.supportedTargetLanguages
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.Date
import java.util.Locale

private val Context.languagePreferencesDataStore by preferencesDataStore(name = "language_preferences")
private val interfaceLanguageCodeKey = stringPreferencesKey("interface_language_code")
private val targetLanguageCodeKey = stringPreferencesKey("target_language_code")
private fun estimatedLevelKey(languageCode: String) = stringPreferencesKey("estimated_level_$languageCode")
private fun learningEvidenceKey(languageCode: String) = stringSetPreferencesKey("learning_evidence_$languageCode")
private fun reviewScheduleKey(languageCode: String) = stringSetPreferencesKey("review_schedule_$languageCode")

private enum class AppStep { LANGUAGE_SELECTION, PLACEMENT_INTRO, PLACEMENT_TEST, PLACEMENT_RESULT, LEARNING_TRAIL, LEARNING_ACTIVITY, VOICE_PREVIEW }

@Composable
fun ChiuKnowApp() {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val preferences by context.languagePreferencesDataStore.data.collectAsState(initial = emptyPreferences())
    val persistedInterfaceCode = preferences[interfaceLanguageCodeKey]
    val persistedTargetCode = preferences[targetLanguageCodeKey]

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            var step by remember { mutableStateOf(AppStep.LANGUAGE_SELECTION) }
            var interfaceLanguage by remember(persistedInterfaceCode) { mutableStateOf(supportedInterfaceLanguages.firstOrNull { it.code == persistedInterfaceCode } ?: supportedInterfaceLanguages.first()) }
            var targetLanguage by remember(persistedTargetCode) { mutableStateOf(supportedTargetLanguages.firstOrNull { it.code == persistedTargetCode } ?: supportedTargetLanguages.first()) }
            val persistedEstimatedLevel = preferences[estimatedLevelKey(targetLanguage.code)]?.let { stored -> CefrLevel.entries.firstOrNull { it.name == stored } }
            val persistedLearningEvidence = remember(preferences, targetLanguage.code) { decodeLearningEvidenceSet(preferences[learningEvidenceKey(targetLanguage.code)].orEmpty()) }
            val persistedReviewSchedules = remember(preferences, targetLanguage.code) {
                val stored = decodeReviewScheduleStateSet(preferences[reviewScheduleKey(targetLanguage.code)].orEmpty())
                if (stored.isNotEmpty() || persistedLearningEvidence.isEmpty()) stored
                else rebuildReviewScheduleStates(persistedLearningEvidence)
            }
            var adaptiveState by remember { mutableStateOf(startAdaptivePlacement()) }
            var estimatedLevel by remember(persistedEstimatedLevel) { mutableStateOf(persistedEstimatedLevel ?: CefrLevel.A1) }
            var correctAnswers by remember { mutableIntStateOf(0) }
            var trailOpenedFromResult by remember { mutableStateOf(false) }
            val placementQuestions = starterPlacementQuestionsFor(targetLanguage.code)
            val currentQuestion = placementQuestionForLevel(placementQuestions, adaptiveState.currentLevel, adaptiveState.answeredQuestions)

            LaunchedEffect(persistedInterfaceCode) {
                val code = persistedInterfaceCode ?: return@LaunchedEffect
                AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(code))
            }

            when (step) {
                AppStep.LANGUAGE_SELECTION -> LanguageOnboardingScreen(interfaceLanguage, targetLanguage, { selected ->
                    interfaceLanguage = selected
                    coroutineScope.launch {
                        context.languagePreferencesDataStore.edit { it[interfaceLanguageCodeKey] = selected.code }
                        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(selected.code))
                    }
                }, { selected ->
                    targetLanguage = selected
                    coroutineScope.launch { context.languagePreferencesDataStore.edit { it[targetLanguageCodeKey] = selected.code } }
                }) { step = AppStep.PLACEMENT_INTRO }
                AppStep.PLACEMENT_INTRO -> PlacementTestIntroScreen(targetLanguage, persistedEstimatedLevel, { level -> estimatedLevel = level; trailOpenedFromResult = false; step = AppStep.LEARNING_TRAIL }, { adaptiveState = startAdaptivePlacement(); estimatedLevel = CefrLevel.A1; correctAnswers = 0; step = AppStep.PLACEMENT_TEST }, { step = AppStep.LANGUAGE_SELECTION })
                AppStep.PLACEMENT_TEST -> PlacementQuestionScreen(currentQuestion, adaptiveState.answeredQuestions + 1, placementQuestions.size) { selected ->
                    val answeredCorrectly = selected == currentQuestion.correctIndex
                    if (answeredCorrectly) correctAnswers++
                    val result = advanceAdaptivePlacement(adaptiveState, answeredCorrectly)
                    adaptiveState = result.state; estimatedLevel = result.estimatedLevel
                    if (result.finished) {
                        coroutineScope.launch { context.languagePreferencesDataStore.edit { it[estimatedLevelKey(targetLanguage.code)] = result.estimatedLevel.name } }
                        step = AppStep.PLACEMENT_RESULT
                    }
                }
                AppStep.PLACEMENT_RESULT -> PlacementResultScreen(estimatedLevel, correctAnswers, adaptiveState.answeredQuestions, { trailOpenedFromResult = true; step = AppStep.LEARNING_TRAIL }, { step = AppStep.PLACEMENT_INTRO }, { step = AppStep.LANGUAGE_SELECTION })
                AppStep.LEARNING_TRAIL -> LearningTrailScreen(estimatedLevel, starterLearningActivityFor(targetLanguage.code, estimatedLevel) != null, { step = AppStep.LEARNING_ACTIVITY }, { step = AppStep.VOICE_PREVIEW }) { step = if (trailOpenedFromResult) AppStep.PLACEMENT_RESULT else AppStep.PLACEMENT_INTRO }
                AppStep.VOICE_PREVIEW -> VoiceSampleScreen(targetLanguage.code) { step = AppStep.LEARNING_TRAIL }
                AppStep.LEARNING_ACTIVITY -> {
                    val queue = remember(targetLanguage.code, estimatedLevel) {
                        starterQueueSelection(
                            languageCode = targetLanguage.code,
                            level = estimatedLevel,
                            evidence = persistedLearningEvidence,
                            schedules = persistedReviewSchedules,
                            nowEpochMillis = System.currentTimeMillis()
                        )
                    }
                    var optionalPracticeRequested by remember(targetLanguage.code, estimatedLevel) { mutableStateOf(false) }
                    val optionalPracticeActivity = if (optionalPracticeRequested) {
                        starterLearningActivityForEvidence(targetLanguage.code, estimatedLevel, persistedLearningEvidence)
                    } else {
                        null
                    }
                    val activity = optionalPracticeActivity ?: queue.activity

                    when {
                        queue.reason == StarterQueueReason.NO_CONTENT ->
                            LaunchedEffect(targetLanguage.code, estimatedLevel) { step = AppStep.LEARNING_TRAIL }
                        queue.reason == StarterQueueReason.NONE_DUE && !optionalPracticeRequested ->
                            ReviewUpToDateScreen(
                                nextDueAtEpochMillis = queue.nextDueAtEpochMillis,
                                onPracticeMore = { optionalPracticeRequested = true },
                                onBack = { step = AppStep.LEARNING_TRAIL }
                            )
                        activity != null ->
                            LearningActivityScreen(activity, onAttempt = { learnerAnswer ->
                                if (!optionalPracticeRequested) {
                                    val correct = isLearningAnswerCorrect(activity, learnerAnswer)
                                    val evidence = learningEvidenceFor(activity, correct, System.currentTimeMillis())
                                    coroutineScope.launch {
                                        context.languagePreferencesDataStore.edit { prefs ->
                                            val key = learningEvidenceKey(targetLanguage.code)
                                            val current = prefs[key].orEmpty()
                                            prefs[key] = current + encodeLearningEvidence(evidence)
                                            val scheduleKey = reviewScheduleKey(targetLanguage.code)
                                            prefs[scheduleKey] = updateReviewScheduleStateSet(
                                                encoded = prefs[scheduleKey].orEmpty(),
                                                evidence = evidence
                                            )
                                        }
                                    }
                                }
                            }) { step = AppStep.LEARNING_TRAIL }
                    }
                }
            }
        }
    }
}

@Composable
private fun LanguageOnboardingScreen(interfaceLanguage: LanguageOption, targetLanguage: LanguageOption, onInterfaceLanguageSelected: (LanguageOption) -> Unit, onTargetLanguageSelected: (LanguageOption) -> Unit, onContinue: () -> Unit) {
    CenteredColumn { Text(stringResource(R.string.app_name), style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold); Spacer(Modifier.height(12.dp)); Text(stringResource(R.string.welcome_title), style = MaterialTheme.typography.titleMedium); Spacer(Modifier.height(32.dp)); LanguageSelector(stringResource(R.string.interface_language), interfaceLanguage, supportedInterfaceLanguages, onInterfaceLanguageSelected); Spacer(Modifier.height(20.dp)); LanguageSelector(stringResource(R.string.target_language), targetLanguage, supportedTargetLanguages, onTargetLanguageSelected); Spacer(Modifier.height(32.dp)); Button(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onContinue) { Text(stringResource(R.string.continue_button)) } }
}

@Composable
private fun PlacementTestIntroScreen(targetLanguage: LanguageOption, previousLevel: CefrLevel?, onContinueLearning: (CefrLevel) -> Unit, onStart: () -> Unit, onBack: () -> Unit) {
    CenteredColumn { Text(stringResource(R.string.placement_title), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold); Spacer(Modifier.height(16.dp)); Text(stringResource(R.string.placement_description, targetLanguage.label), style = MaterialTheme.typography.bodyLarge); Spacer(Modifier.height(12.dp)); Text(stringResource(R.string.placement_prototype_note), style = MaterialTheme.typography.bodyMedium); Spacer(Modifier.height(24.dp)); if (previousLevel != null) { Text("${stringResource(R.string.estimated_level)}: ${previousLevel.name}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold); Spacer(Modifier.height(12.dp)); Button(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = { onContinueLearning(previousLevel) }) { Text(stringResource(R.string.continue_to_path)) }; Spacer(Modifier.height(12.dp)) }; Button(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onStart) { Text(stringResource(R.string.start_level_test)) }; Spacer(Modifier.height(12.dp)); OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onBack) { Text(stringResource(R.string.back_button)) } }
}

@Composable
private fun PlacementQuestionScreen(question: PlacementQuestion, number: Int, total: Int, onAnswer: (Int) -> Unit) {
    CenteredColumn { Text(stringResource(R.string.question_progress, number, total), style = MaterialTheme.typography.labelLarge); Spacer(Modifier.height(12.dp)); Text(question.level.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold); Spacer(Modifier.height(20.dp)); Text(question.prompt, style = MaterialTheme.typography.headlineSmall); Spacer(Modifier.height(28.dp)); question.options.forEachIndexed { index, option -> OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), onClick = { onAnswer(index) }) { Text(option) }; Spacer(Modifier.height(10.dp)) } }
}

@Composable
private fun PlacementResultScreen(level: CefrLevel, correctAnswers: Int, total: Int, onContinue: () -> Unit, onRestart: () -> Unit, onChangeLanguage: () -> Unit) {
    CenteredColumn { Text(stringResource(R.string.estimated_level), style = MaterialTheme.typography.titleLarge); Spacer(Modifier.height(12.dp)); Text(level.name, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold); Spacer(Modifier.height(12.dp)); Text(stringResource(R.string.correct_answers, correctAnswers, total)); Spacer(Modifier.height(8.dp)); Text(stringResource(R.string.prototype_score_note), style = MaterialTheme.typography.bodyMedium); Spacer(Modifier.height(28.dp)); Button(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onContinue) { Text(stringResource(R.string.continue_to_path)) }; Spacer(Modifier.height(12.dp)); OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onRestart) { Text(stringResource(R.string.try_again)) }; Spacer(Modifier.height(12.dp)); OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onChangeLanguage) { Text(stringResource(R.string.change_languages)) } }
}

@Composable
private fun LearningTrailScreen(estimatedLevel: CefrLevel, hasFoundationActivity: Boolean, onStartFoundationActivity: () -> Unit, onPreviewVoices: () -> Unit, onBack: () -> Unit) {
    val trail = remember(estimatedLevel) { buildCefrTrail(estimatedLevel) }
    val scrollState = rememberScrollState()
    Column(modifier = Modifier.fillMaxSize().verticalScroll(scrollState).padding(horizontal = 24.dp, vertical = 32.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(stringResource(R.string.learning_path_title), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold); Spacer(Modifier.height(12.dp)); Text(stringResource(R.string.learning_path_description), style = MaterialTheme.typography.bodyLarge); Spacer(Modifier.height(20.dp)); trail.forEach { item -> val statusLabel = when (item.status) { CefrTrailStatus.COMPLETED -> stringResource(R.string.trail_completed); CefrTrailStatus.CURRENT -> stringResource(R.string.trail_current); CefrTrailStatus.LOCKED -> stringResource(R.string.trail_locked) }; Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), tonalElevation = if (item.status == CefrTrailStatus.CURRENT) 6.dp else 1.dp) { Text("${item.level.name} · $statusLabel", modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp), style = MaterialTheme.typography.titleMedium, fontWeight = if (item.status == CefrTrailStatus.CURRENT) FontWeight.Bold else FontWeight.Normal) }; Spacer(Modifier.height(8.dp)) }; if (hasFoundationActivity) { Spacer(Modifier.height(8.dp)); Button(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onStartFoundationActivity) { Text(stringResource(R.string.start_foundation_activity)) } }; Spacer(Modifier.height(12.dp)); Text(stringResource(R.string.trail_foundation_note), style = MaterialTheme.typography.bodyMedium); Spacer(Modifier.height(16.dp)); OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onPreviewVoices) { Text(stringResource(R.string.preview_temporary_voices)) }; Spacer(Modifier.height(12.dp)); OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onBack) { Text(stringResource(R.string.back_button)) }
    }
}

@Composable
private fun VoiceSampleScreen(languageCode: String, onBack: () -> Unit) {
    val context = LocalContext.current
    val mainHandler = remember { Handler(Looper.getMainLooper()) }
    var engine by remember { mutableStateOf<TextToSpeech?>(null) }
    var ready by remember { mutableStateOf(false) }
    var unavailable by remember { mutableStateOf(false) }
    var playingStyle by remember { mutableStateOf<TemporaryVoiceStyle?>(null) }
    val phrase = remember(languageCode) { voiceSamplePhrase(languageCode) }

    DisposableEffect(languageCode) {
        val textToSpeech = TextToSpeech(context.applicationContext) { status ->
            mainHandler.post {
                ready = status == TextToSpeech.SUCCESS
                unavailable = status != TextToSpeech.SUCCESS
            }
        }
        textToSpeech.setOnUtteranceProgressListener(object : UtteranceProgressListener() {
            override fun onStart(utteranceId: String?) {
                mainHandler.post { playingStyle = TemporaryVoiceStyle.entries.firstOrNull { it.name == utteranceId } }
            }

            override fun onDone(utteranceId: String?) {
                mainHandler.post { playingStyle = null }
            }

            @Deprecated("Android callback")
            override fun onError(utteranceId: String?) {
                mainHandler.post { playingStyle = null; unavailable = true }
            }

            override fun onError(utteranceId: String?, errorCode: Int) {
                mainHandler.post { playingStyle = null; unavailable = true }
            }
        })
        engine = textToSpeech
        onDispose {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
    }

    LaunchedEffect(ready, languageCode, engine) {
        val textToSpeech = engine
        if (ready && textToSpeech != null) {
            val result = textToSpeech.setLanguage(Locale.forLanguageTag(languageCode))
            unavailable = result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED
        }
    }

    CenteredColumn {
        Text(stringResource(R.string.voice_samples_title), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))
        Text(stringResource(R.string.voice_samples_description), style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(12.dp))
        Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), tonalElevation = 1.dp) {
            Text(phrase, modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp), style = MaterialTheme.typography.titleMedium)
        }
        Spacer(Modifier.height(16.dp))
        temporaryVoiceSamples().forEach { sample ->
            val label = when (sample.style) {
                TemporaryVoiceStyle.NEUTRAL -> stringResource(R.string.voice_sample_neutral)
                TemporaryVoiceStyle.CALM -> stringResource(R.string.voice_sample_calm)
                TemporaryVoiceStyle.LIVELY -> stringResource(R.string.voice_sample_lively)
            }
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                enabled = ready && !unavailable,
                onClick = {
                    engine?.apply {
                        setSpeechRate(sample.speechRate)
                        setPitch(sample.pitch)
                        speak(phrase, TextToSpeech.QUEUE_FLUSH, null, sample.style.name)
                    }
                }
            ) {
                Text(if (playingStyle == sample.style) stringResource(R.string.voice_sample_playing, label) else label)
            }
            Spacer(Modifier.height(8.dp))
        }
        if (!ready && !unavailable) Text(stringResource(R.string.voice_samples_loading))
        if (unavailable) Text(stringResource(R.string.voice_samples_unavailable), style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(12.dp))
        Text(stringResource(R.string.voice_samples_temporary_note), style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(20.dp))
        OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onBack) { Text(stringResource(R.string.back_to_path)) }
    }
}

@Composable
private fun ReviewUpToDateScreen(
    nextDueAtEpochMillis: Long?,
    onPracticeMore: () -> Unit,
    onBack: () -> Unit
) {
    val nextReview = nextDueAtEpochMillis?.let {
        DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT).format(Date(it))
    }

    CenteredColumn {
        Text(stringResource(R.string.review_up_to_date_title), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))
        Text(stringResource(R.string.review_up_to_date_description), style = MaterialTheme.typography.bodyLarge)
        if (nextReview != null) {
            Spacer(Modifier.height(12.dp))
            Text(stringResource(R.string.next_review_time, nextReview), style = MaterialTheme.typography.titleMedium)
        }
        Spacer(Modifier.height(24.dp))
        Button(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onPracticeMore) {
            Text(stringResource(R.string.practice_more))
        }
        Spacer(Modifier.height(8.dp))
        Text(stringResource(R.string.optional_practice_note), style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.height(12.dp))
        OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onBack) {
            Text(stringResource(R.string.back_to_path))
        }
    }
}

@Composable
private fun LearningActivityScreen(activity: LearningActivity, onAttempt: (String) -> Unit, onBack: () -> Unit) {
    var answer by remember(activity.id) { mutableStateOf("") }
    var selectedTokenIndices by remember(activity.id) { mutableStateOf(emptyList<Int>()) }
    var checked by remember(activity.id) { mutableStateOf(false) }
    val effectiveAnswer = if (activity.responseType == ResponseType.REORDER) selectedTokenIndices.joinToString(" ") { activity.responseOptions[it] } else answer
    val correct = checked && isLearningAnswerCorrect(activity, effectiveAnswer)

    CenteredColumn {
        Text(stringResource(R.string.activity_title), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(8.dp))
        Text("${activity.level.name} · ${activity.primarySkill.name}", style = MaterialTheme.typography.labelLarge)
        Spacer(Modifier.height(20.dp))
        Text(activity.prompt, style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(20.dp))

        when (activity.responseType) {
            ResponseType.REORDER -> {
                Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), tonalElevation = 1.dp) { Text(text = if (effectiveAnswer.isBlank()) "…" else effectiveAnswer, modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp), style = MaterialTheme.typography.titleMedium) }
                Spacer(Modifier.height(12.dp))
                activity.responseOptions.forEachIndexed { index, token ->
                    if (index !in selectedTokenIndices) {
                        OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), onClick = { selectedTokenIndices = selectedTokenIndices + index; checked = false }) { Text(token) }
                        Spacer(Modifier.height(8.dp))
                    }
                }
                if (selectedTokenIndices.isNotEmpty()) {
                    OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), onClick = { selectedTokenIndices = selectedTokenIndices.dropLast(1); checked = false }) { Text("↶") }
                    Spacer(Modifier.height(8.dp))
                }
            }
            ResponseType.MULTIPLE_CHOICE -> {
                activity.responseOptions.forEach { option ->
                    val selected = answer == option
                    if (selected) {
                        Button(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), onClick = { answer = option; checked = false }) { Text(option) }
                    } else {
                        OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), onClick = { answer = option; checked = false }) { Text(option) }
                    }
                    Spacer(Modifier.height(8.dp))
                }
            }
            else -> {
                OutlinedTextField(value = answer, onValueChange = { answer = it; checked = false }, modifier = Modifier.fillMaxWidth(), label = { Text(stringResource(R.string.activity_answer_hint)) }, singleLine = true, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done))
            }
        }

        Spacer(Modifier.height(16.dp))
        Button(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), enabled = effectiveAnswer.isNotBlank(), onClick = { checked = true; onAttempt(effectiveAnswer) }) { Text(stringResource(R.string.check_answer)) }
        if (checked) {
            Spacer(Modifier.height(20.dp))
            Text(if (correct) stringResource(R.string.answer_correct) else stringResource(R.string.answer_incorrect), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(8.dp))
            Text("${stringResource(R.string.activity_feedback)}: ${activity.feedback}", style = MaterialTheme.typography.bodyLarge)
        }
        Spacer(Modifier.height(20.dp))
        OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onBack) { Text(stringResource(R.string.back_to_path)) }
    }
}

@Composable
private fun CenteredColumn(content: @Composable () -> Unit) { Column(modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 32.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) { content() } }

@Composable
private fun LanguageSelector(title: String, selected: LanguageOption, options: List<LanguageOption>, onSelected: (LanguageOption) -> Unit) { var expanded by remember { mutableStateOf(false) }; Column(modifier = Modifier.fillMaxWidth()) { Text(title, style = MaterialTheme.typography.labelLarge); Spacer(Modifier.height(8.dp)); OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), onClick = { expanded = true }) { Text(selected.label) }; DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) { options.forEach { option -> DropdownMenuItem(text = { Text(option.label) }, onClick = { onSelected(option); expanded = false }) } } } }