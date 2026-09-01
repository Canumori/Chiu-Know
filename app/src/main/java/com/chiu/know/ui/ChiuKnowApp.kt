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
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.chiu.know.R
import com.chiu.know.model.CefrLevel
import com.chiu.know.model.LanguageOption
import com.chiu.know.model.PlacementQuestion
import com.chiu.know.model.advanceAdaptivePlacement
import com.chiu.know.model.placementQuestionForLevel
import com.chiu.know.model.startAdaptivePlacement
import com.chiu.know.model.starterPlacementQuestionsFor
import com.chiu.know.model.supportedInterfaceLanguages
import com.chiu.know.model.supportedTargetLanguages
import kotlinx.coroutines.launch

private val Context.languagePreferencesDataStore by preferencesDataStore(name = "language_preferences")
private val interfaceLanguageCodeKey = stringPreferencesKey("interface_language_code")
private val targetLanguageCodeKey = stringPreferencesKey("target_language_code")

private enum class AppStep { LANGUAGE_SELECTION, PLACEMENT_INTRO, PLACEMENT_TEST, PLACEMENT_RESULT }

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
            var interfaceLanguage by remember(persistedInterfaceCode) {
                mutableStateOf(supportedInterfaceLanguages.firstOrNull { it.code == persistedInterfaceCode } ?: supportedInterfaceLanguages.first())
            }
            var targetLanguage by remember(persistedTargetCode) {
                mutableStateOf(supportedTargetLanguages.firstOrNull { it.code == persistedTargetCode } ?: supportedTargetLanguages.first())
            }
            var adaptiveState by remember { mutableStateOf(startAdaptivePlacement()) }
            var estimatedLevel by remember { mutableStateOf(CefrLevel.A1) }
            var correctAnswers by remember { mutableIntStateOf(0) }
            val placementQuestions = starterPlacementQuestionsFor(targetLanguage.code)
            val currentQuestion = placementQuestionForLevel(
                questions = placementQuestions,
                level = adaptiveState.currentLevel,
                attemptIndex = adaptiveState.answeredQuestions
            )

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

                AppStep.PLACEMENT_INTRO -> PlacementTestIntroScreen(targetLanguage, onStart = {
                    adaptiveState = startAdaptivePlacement()
                    estimatedLevel = CefrLevel.A1
                    correctAnswers = 0
                    step = AppStep.PLACEMENT_TEST
                }, onBack = { step = AppStep.LANGUAGE_SELECTION })

                AppStep.PLACEMENT_TEST -> PlacementQuestionScreen(
                    question = currentQuestion,
                    number = adaptiveState.answeredQuestions + 1,
                    total = placementQuestions.size,
                    onAnswer = { selected ->
                        val answeredCorrectly = selected == currentQuestion.correctIndex
                        if (answeredCorrectly) correctAnswers++
                        val result = advanceAdaptivePlacement(adaptiveState, answeredCorrectly)
                        adaptiveState = result.state
                        estimatedLevel = result.estimatedLevel
                        if (result.finished) step = AppStep.PLACEMENT_RESULT
                    }
                )

                AppStep.PLACEMENT_RESULT -> PlacementResultScreen(
                    level = estimatedLevel,
                    correctAnswers = correctAnswers,
                    total = adaptiveState.answeredQuestions,
                    onRestart = { step = AppStep.PLACEMENT_INTRO },
                    onChangeLanguage = { step = AppStep.LANGUAGE_SELECTION }
                )
            }
        }
    }
}

@Composable
private fun LanguageOnboardingScreen(interfaceLanguage: LanguageOption, targetLanguage: LanguageOption, onInterfaceLanguageSelected: (LanguageOption) -> Unit, onTargetLanguageSelected: (LanguageOption) -> Unit, onContinue: () -> Unit) {
    CenteredColumn {
        Text(stringResource(R.string.app_name), style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp)); Text(stringResource(R.string.welcome_title), style = MaterialTheme.typography.titleMedium); Spacer(Modifier.height(32.dp))
        LanguageSelector(stringResource(R.string.interface_language), interfaceLanguage, supportedInterfaceLanguages, onInterfaceLanguageSelected); Spacer(Modifier.height(20.dp))
        LanguageSelector(stringResource(R.string.target_language), targetLanguage, supportedTargetLanguages, onTargetLanguageSelected); Spacer(Modifier.height(32.dp))
        Button(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onContinue) { Text(stringResource(R.string.continue_button)) }
    }
}

@Composable
private fun PlacementTestIntroScreen(targetLanguage: LanguageOption, onStart: () -> Unit, onBack: () -> Unit) {
    CenteredColumn {
        Text(stringResource(R.string.placement_title), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold); Spacer(Modifier.height(16.dp))
        Text(stringResource(R.string.placement_description, targetLanguage.label), style = MaterialTheme.typography.bodyLarge); Spacer(Modifier.height(12.dp))
        Text(stringResource(R.string.placement_prototype_note), style = MaterialTheme.typography.bodyMedium); Spacer(Modifier.height(32.dp))
        Button(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onStart) { Text(stringResource(R.string.start_level_test)) }; Spacer(Modifier.height(12.dp))
        OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onBack) { Text(stringResource(R.string.back_button)) }
    }
}

@Composable
private fun PlacementQuestionScreen(question: PlacementQuestion, number: Int, total: Int, onAnswer: (Int) -> Unit) {
    CenteredColumn {
        Text(stringResource(R.string.question_progress, number, total), style = MaterialTheme.typography.labelLarge); Spacer(Modifier.height(12.dp))
        Text(question.level.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold); Spacer(Modifier.height(20.dp))
        Text(question.prompt, style = MaterialTheme.typography.headlineSmall); Spacer(Modifier.height(28.dp))
        question.options.forEachIndexed { index, option ->
            OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), onClick = { onAnswer(index) }) { Text(option) }
            Spacer(Modifier.height(10.dp))
        }
    }
}

@Composable
private fun PlacementResultScreen(level: CefrLevel, correctAnswers: Int, total: Int, onRestart: () -> Unit, onChangeLanguage: () -> Unit) {
    CenteredColumn {
        Text(stringResource(R.string.estimated_level), style = MaterialTheme.typography.titleLarge); Spacer(Modifier.height(12.dp))
        Text(level.name, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold); Spacer(Modifier.height(12.dp))
        Text(stringResource(R.string.correct_answers, correctAnswers, total)); Spacer(Modifier.height(8.dp))
        Text(stringResource(R.string.prototype_score_note), style = MaterialTheme.typography.bodyMedium); Spacer(Modifier.height(28.dp))
        Button(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onRestart) { Text(stringResource(R.string.try_again)) }; Spacer(Modifier.height(12.dp))
        OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onChangeLanguage) { Text(stringResource(R.string.change_languages)) }
    }
}

@Composable
private fun CenteredColumn(content: @Composable () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(horizontal = 24.dp, vertical = 32.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) { content() }
}

@Composable
private fun LanguageSelector(title: String, selected: LanguageOption, options: List<LanguageOption>, onSelected: (LanguageOption) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(title, style = MaterialTheme.typography.labelLarge); Spacer(Modifier.height(8.dp))
        OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), onClick = { expanded = true }) { Text(selected.label) }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEach { option -> DropdownMenuItem(text = { Text(option.label) }, onClick = { onSelected(option); expanded = false }) }
        }
    }
}
