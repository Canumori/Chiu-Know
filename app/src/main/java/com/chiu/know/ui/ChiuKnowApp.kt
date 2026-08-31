package com.chiu.know.ui

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import com.chiu.know.R
import com.chiu.know.model.LanguageOption
import com.chiu.know.model.PlacementQuestion
import com.chiu.know.model.estimateLevel
import com.chiu.know.model.starterEnglishPlacementQuestions
import com.chiu.know.model.supportedInterfaceLanguages
import com.chiu.know.model.supportedTargetLanguages

private enum class AppStep { LANGUAGE_SELECTION, PLACEMENT_INTRO, PLACEMENT_TEST, PLACEMENT_RESULT }

@Composable
fun ChiuKnowApp() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            var step by remember { mutableStateOf(AppStep.LANGUAGE_SELECTION) }
            var interfaceLanguage by remember { mutableStateOf(supportedInterfaceLanguages.first()) }
            var targetLanguage by remember { mutableStateOf(supportedTargetLanguages.first()) }
            var questionIndex by remember { mutableIntStateOf(0) }
            var correctAnswers by remember { mutableIntStateOf(0) }

            when (step) {
                AppStep.LANGUAGE_SELECTION -> LanguageOnboardingScreen(interfaceLanguage, targetLanguage, { selected ->
                    interfaceLanguage = selected
                    AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(selected.code))
                }, { targetLanguage = it }) { step = AppStep.PLACEMENT_INTRO }
                AppStep.PLACEMENT_INTRO -> PlacementTestIntroScreen(targetLanguage, onStart = {
                    questionIndex = 0
                    correctAnswers = 0
                    step = AppStep.PLACEMENT_TEST
                }, onBack = { step = AppStep.LANGUAGE_SELECTION })
                AppStep.PLACEMENT_TEST -> PlacementQuestionScreen(
                    question = starterEnglishPlacementQuestions[questionIndex],
                    number = questionIndex + 1,
                    total = starterEnglishPlacementQuestions.size,
                    onAnswer = { selected ->
                        if (selected == starterEnglishPlacementQuestions[questionIndex].correctIndex) correctAnswers++
                        if (questionIndex == starterEnglishPlacementQuestions.lastIndex) step = AppStep.PLACEMENT_RESULT else questionIndex++
                    }
                )
                AppStep.PLACEMENT_RESULT -> PlacementResultScreen(
                    correctAnswers = correctAnswers,
                    total = starterEnglishPlacementQuestions.size,
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
        Text("Let's find your level", style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold); Spacer(Modifier.height(16.dp))
        Text("We'll estimate your current ${targetLanguage.label} level from A1 to C2.", style = MaterialTheme.typography.bodyLarge); Spacer(Modifier.height(12.dp))
        Text("This first local prototype checks the test flow. The final calibrated adaptive bank will be language-specific. Your result is an estimated proficiency level, not an official certificate.", style = MaterialTheme.typography.bodyMedium); Spacer(Modifier.height(32.dp))
        Button(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onStart) { Text("Start level test") }; Spacer(Modifier.height(12.dp))
        OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onBack) { Text("Back") }
    }
}

@Composable
private fun PlacementQuestionScreen(question: PlacementQuestion, number: Int, total: Int, onAnswer: (Int) -> Unit) {
    CenteredColumn {
        Text("Question $number of $total", style = MaterialTheme.typography.labelLarge); Spacer(Modifier.height(12.dp))
        Text(question.level.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold); Spacer(Modifier.height(20.dp))
        Text(question.prompt, style = MaterialTheme.typography.headlineSmall); Spacer(Modifier.height(28.dp))
        question.options.forEachIndexed { index, option ->
            OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(16.dp), onClick = { onAnswer(index) }) { Text(option) }
            Spacer(Modifier.height(10.dp))
        }
    }
}

@Composable
private fun PlacementResultScreen(correctAnswers: Int, total: Int, onRestart: () -> Unit, onChangeLanguage: () -> Unit) {
    val level = estimateLevel(correctAnswers, total)
    CenteredColumn {
        Text("Estimated level", style = MaterialTheme.typography.titleLarge); Spacer(Modifier.height(12.dp))
        Text(level.name, style = MaterialTheme.typography.headlineLarge, fontWeight = FontWeight.Bold); Spacer(Modifier.height(12.dp))
        Text("$correctAnswers of $total answers correct in this prototype."); Spacer(Modifier.height(8.dp))
        Text("This prototype score is not yet the final calibrated CEFR assessment.", style = MaterialTheme.typography.bodyMedium); Spacer(Modifier.height(28.dp))
        Button(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onRestart) { Text("Try again") }; Spacer(Modifier.height(12.dp))
        OutlinedButton(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(18.dp), onClick = onChangeLanguage) { Text("Change languages") }
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
