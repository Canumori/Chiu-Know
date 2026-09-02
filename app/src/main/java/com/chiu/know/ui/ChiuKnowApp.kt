package com.chiu.know.ui

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.chiu.know.model.PlacementQuestion
import com.chiu.know.model.advanceAdaptivePlacement
import com.chiu.know.model.buildCefrTrail
import com.chiu.know.model.isLearningAnswerCorrect
import com.chiu.know.model.learningEvidenceFor
import com.chiu.know.model.placementQuestionForLevel
import com.chiu.know.model.startAdaptivePlacement
import com.chiu.know.model.starterLearningActivityFor
import com.chiu.know.model.starterPlacementQuestionsFor
import com.chiu.know.model.supportedInterfaceLanguages
import com.chiu.know.model.supportedTargetLanguages
import kotlinx.coroutines.launch

private val Context.languagePreferencesDataStore by preferencesDataStore(name = "language_preferences")
private val interfaceLanguageCodeKey = stringPreferencesKey("interface_language_code")
private val targetLanguageCodeKey = stringPreferencesKey("target_language_code")
private fun estimatedLevelKey(languageCode: String) = stringPreferencesKey("estimated_level_$languageCode")
private fun learningEvidenceKey(languageCode: String) = stringSetPreferencesKey("learning_evidence_$languageCode")

private enum class AppStep { LANGUAGE_SELECTION, PLACEMENT_INTRO, PLACEMENT_TEST, PLACEMENT_RESULT, LEARNING_TRAIL, LEARNING_ACTIVITY }

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
                AppStep.LEARNING_TRAIL -> LearningTrailScreen(estimatedLevel, starterLearningActivityFor(targetLanguage.code, CefrLevel.A1) != null, { step = AppStep.LEARNING_ACTIVITY }) { step = if (trailOpenedFromResult) AppStep.PLACEMENT_RESULT else AppStep.PLACEMENT_INTRO }
                AppStep.LEARNING_ACTIVITY -> {
                    val activity = starterLearningActivityFor(targetLanguage.code, CefrLevel.A1)
                    if (activity == null) LaunchedEffect(targetLanguage.code) { step = AppStep.LEARNING_TRAIL }
                    else LearningActivityScreen(activity, onAttempt = { learnerAnswer ->
                        val correct = isLearningAnswerCorrect(activity, learnerAnswer)
                        val evidence = learningEvidenceFor(activity, correct, System.currentTimeMillis())
                        coroutineScope.launch {
                            context.languagePreferencesDataStore.edit { prefs ->
                                val key = learningEvidenceKey(targetLanguage.code)
                                val current = prefs[key].orEmpty()
                                val encoded = listOf(
                                    evidence.attemptedAtEpochMillis,
                                    evidence.activityId,
                                    evidence.reviewKey,
                                    evidence.level.name,
                                    evidence.primarySkill.name,
                                    evidence.correct
                                ).joinToString("|")
                                prefs[key] = current + encoded
                            }
                        }
                    }) { step = AppStep.LEARNING_TRAIL }
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
private fun LearningTrailScreen(estimatedLevel: CefrLevel, hasFoundationActivity: Boolean, onStartFoundationActivity: () -> Unit, onBack: () -> Unit) {
    val trail = remember(estimatedLevel) { buildCefrTrail(estimatedLevel) }
    CenteredColumn { Text(stringResource(R.string.learning_path_title), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold); Spacer(Modifier.height(12.dp)); Text(stringResource(R.string.learning_path_description), style = MaterialTheme.typography.bodyLarge); Spacer(Modifier.height(20.dp)); trail.forEach { item -> val statusLabel = when (item.status) { CefrTrailStatus.COMPLETED -> stringResource(R.string.trail_completed); CefrTrailStatus.CURRENT -> stringResource(R.string.trail_current); CefrTrailStatus.LOCKED -> stringResource(R.string.trail_locked) }; Surface(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), tonalElevation = if (item.status == CefrTrailStatus.CURRENT) 6.dp else 1.dp) { Text("${item.level.name} · $statusLabel", modifier = Modifier.padding(horizontal = 18.dp, vertical = 12.dp), style = MaterialTheme.typography.titleMedium, fontWeight = if (item.status == CefrTrailStatus.CURRENT) FontWeight.Bold else FontWeight.Normal) }; Spacer(Modifier.height(8.dp)) }; if (hasFoundationActivity) { Spacer(Modifier.height(8.dp)); Button(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onStartFoundationActivity) { Text(stringResource(R.string.start_foundation_activity)) } }; Spacer(Modifier.height(12.dp)); Text(stringResource(R.string.trail_foundation_note), style = MaterialTheme.typography.bodyMedium); Spacer(Modifier.height(16.dp)); OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onBack) { Text(stringResource(R.string.back_button)) } }
}

@Composable
private fun LearningActivityScreen(activity: LearningActivity, onAttempt: (String) -> Unit, onBack: () -> Unit) {
    var answer by remember(activity.id) { mutableStateOf("") }; var checked by remember(activity.id) { mutableStateOf(false) }; val correct = checked && isLearningAnswerCorrect(activity, answer)
    CenteredColumn { Text(stringResource(R.string.activity_title), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold); Spacer(Modifier.height(8.dp)); Text("${activity.level.name} · ${activity.primarySkill.name}", style = MaterialTheme.typography.labelLarge); Spacer(Modifier.height(20.dp)); Text(activity.prompt, style = MaterialTheme.typography.headlineSmall); Spacer(Modifier.height(20.dp)); OutlinedTextField(value = answer, onValueChange = { answer = it; checked = false }, modifier = Modifier.fillMaxWidth(), label = { Text(stringResource(R.string.activity_answer_hint)) }, singleLine = true, keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)); Spacer(Modifier.height(16.dp)); Button(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), enabled = answer.isNotBlank(), onClick = { checked = true; onAttempt(answer) }) { Text(stringResource(R.string.check_answer)) }; if (checked) { Spacer(Modifier.height(20.dp)); Text(if (correct) stringResource(R.string.answer_correct) else stringResource(R.string.answer_incorrect), style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold); Spacer(Modifier.height(8.dp)); Text("${stringResource(R.string.activity_feedback)}: ${activity.feedback}", style = MaterialTheme.typography.bodyLarge) }; Spacer(Modifier.height(20.dp)); OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onBack) { Text(stringResource(R.string.back_to_path)) } }
}

@Composable
private fun CenteredColumn(content: @Composable () -> Unit) { Column(modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 32.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) { content() } }

@Composable
private fun LanguageSelector(title: String, selected: LanguageOption, options: List<LanguageOption>, onSelected: (LanguageOption) -> Unit) { var expanded by remember { mutableStateOf(false) }; Column(modifier = Modifier.fillMaxWidth()) { Text(title, style = MaterialTheme.typography.labelLarge); Spacer(Modifier.height(8.dp)); OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), onClick = { expanded = true }) { Text(selected.label) }; DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) { options.forEach { option -> DropdownMenuItem(text = { Text(option.label) }, onClick = { onSelected(option); expanded = false }) } } } }
