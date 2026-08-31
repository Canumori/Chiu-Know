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
import androidx.compose.ui.unit.dp
import com.chiu.know.model.LanguageOption
import com.chiu.know.model.supportedInterfaceLanguages
import com.chiu.know.model.supportedTargetLanguages

@Composable
fun ChiuKnowApp() {
    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            LanguageOnboardingScreen()
        }
    }
}

@Composable
private fun LanguageOnboardingScreen() {
    var interfaceLanguage by remember { mutableStateOf(supportedInterfaceLanguages.first()) }
    var targetLanguage by remember { mutableStateOf(supportedTargetLanguages.first()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Chiu Know?",
            style = MaterialTheme.typography.headlineLarge
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
            onSelected = { interfaceLanguage = it }
        )

        Spacer(modifier = Modifier.height(20.dp))

        LanguageSelector(
            title = "Language you want to learn",
            selected = targetLanguage,
            options = supportedTargetLanguages,
            onSelected = { targetLanguage = it }
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            onClick = {
                // Navigation and persistence will be added in the next isolated step.
            }
        ) {
            Text("Continue")
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
