package com.chiu.know.ui

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chiu.know.model.LanguageOption
import com.chiu.know.model.supportedInterfaceLanguages
import com.chiu.know.model.supportedTargetLanguages

private enum class AppStep {
    LANGUAGE_SELECTION,
    PLACEMENT_INTRO
}

@Composable
fun ChiuKnowApp() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            var step by remember { mutableStateOf(AppStep.LANGUAGE_SELECTION) }
            var interfaceLanguage by remember { mutableStateOf(supportedInterfaceLanguages.first()) }
            var targetLanguage by remember { mutableStateOf(supportedTargetLanguages.first()) }

            when (step) {
                AppStep.LANGUAGE_SELECTION -> LanguageOnboardingScreen(
                    interfaceLanguage = interfaceLanguage,
                    targetLanguage = targetLanguage,
                    onInterfaceLanguageSelected = { interfaceLanguage = it },
                    onTargetLanguageSelected = { targetLanguage = it },
                    onContinue = { step = AppStep.PLACEMENT_INTRO }
                )

                AppStep.PLACEMENT_INTRO -> PlacementTestIntroScreen(
                    targetLanguage = targetLanguage,
                    onBack = { step = AppStep.LANGUAGE_SELECTION }
                )
            }
        }
    }
}

@Composable
private fun LanguageOnboardingScreen(
    interfaceLanguage: LanguageOption,
    targetLanguage: LanguageOption,
    onInterfaceLanguageSelected: (LanguageOption) -> Unit,
    onTargetLanguageSelected: (LanguageOption) -> Unit,
    onContinue: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Chiu Know?",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "Choose your languages",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(32.dp))

        LanguageSelector(
            title = "App language",
            selected = interfaceLanguage,
            options = supportedInterfaceLanguages,
            onSelected = onInterfaceLanguageSelected
        )

        Spacer(modifier = Modifier.height(20.dp))

        LanguageSelector(
            title = "Language you want to learn",
            selected = targetLanguage,
            options = supportedTargetLanguages,
            onSelected = onTargetLanguageSelected
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            onClick = onContinue
        ) {
            Text("Continue")
        }
    }
}

@Composable
private fun PlacementTestIntroScreen(
    targetLanguage: LanguageOption,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Let's find your level",
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "We'll estimate your current ${targetLanguage.label} level from A1 to C2.",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "The test will adapt as you answer. Your result will be an estimated proficiency level, not an official certificate.",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            onClick = {
                // The calibrated/adaptive question engine is the next isolated step.
            }
        ) {
            Text("Start level test")
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            onClick = onBack
        ) {
            Text("Back")
        }
    }
}

@Composable
private fun LanguageSelector(
    title: String,
    selected: LanguageOption,
    options: List<LanguageOption>,
    onSelected: (LanguageOption) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = title, style = MaterialTheme.typography.labelLarge)
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            onClick = { expanded = true }
        ) {
            Text(selected.label)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option.label) },
                    onClick = {
                        onSelected(option)
                        expanded = false
                    }
                )
            }
        }
    }
}
