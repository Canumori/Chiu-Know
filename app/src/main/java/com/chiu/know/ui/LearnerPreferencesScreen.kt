package com.chiu.know.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.chiu.know.R
import com.chiu.know.model.LearnerPreferences
import com.chiu.know.model.LearningGoal
import com.chiu.know.model.LearningPriority

@Composable
fun LearnerPreferencesScreen(
    initialPreferences: LearnerPreferences = LearnerPreferences(),
    onContinue: (LearnerPreferences) -> Unit,
    onBack: () -> Unit
) {
    var goal by remember(initialPreferences) { mutableStateOf(initialPreferences.goal) }
    var priority by remember(initialPreferences) { mutableStateOf(initialPreferences.priority) }
    var dailyMinutesText by remember(initialPreferences) { mutableStateOf(initialPreferences.dailyMinutes.toString()) }
    var goalExpanded by remember { mutableStateOf(false) }
    var priorityExpanded by remember { mutableStateOf(false) }

    val dailyMinutes = dailyMinutesText.toIntOrNull()
    val validMinutes = dailyMinutes != null &&
        dailyMinutes in LearnerPreferences.MIN_DAILY_MINUTES..LearnerPreferences.MAX_DAILY_MINUTES

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(text = stringResource(R.string.learner_preferences_title))
        Text(text = stringResource(R.string.learner_preferences_description))

        Column {
            Text(text = stringResource(R.string.learning_goal_label))
            OutlinedButton(
                onClick = { goalExpanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = goalLabel(goal))
            }
            DropdownMenu(expanded = goalExpanded, onDismissRequest = { goalExpanded = false }) {
                LearningGoal.entries.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(goalLabel(option)) },
                        onClick = {
                            goal = option
                            goalExpanded = false
                        }
                    )
                }
            }
        }

        Column {
            Text(text = stringResource(R.string.learning_priority_label))
            OutlinedButton(
                onClick = { priorityExpanded = true },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = priorityLabel(priority))
            }
            DropdownMenu(expanded = priorityExpanded, onDismissRequest = { priorityExpanded = false }) {
                LearningPriority.entries.forEach { option ->
                    DropdownMenuItem(
                        text = { Text(priorityLabel(option)) },
                        onClick = {
                            priority = option
                            priorityExpanded = false
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            value = dailyMinutesText,
            onValueChange = { value -> dailyMinutesText = value.filter(Char::isDigit).take(3) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.daily_minutes_label)) },
            supportingText = {
                Text(
                    stringResource(
                        R.string.daily_minutes_hint,
                        LearnerPreferences.MIN_DAILY_MINUTES,
                        LearnerPreferences.MAX_DAILY_MINUTES
                    )
                )
            },
            isError = dailyMinutesText.isNotEmpty() && !validMinutes,
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedButton(onClick = onBack, modifier = Modifier.weight(1f)) {
                Text(stringResource(R.string.back_button))
            }
            Button(
                onClick = {
                    val minutes = dailyMinutes ?: return@Button
                    onContinue(LearnerPreferences(goal, priority, minutes))
                },
                enabled = validMinutes,
                modifier = Modifier.weight(1f)
            ) {
                Text(stringResource(R.string.continue_button))
            }
        }
    }
}

@Composable
private fun goalLabel(goal: LearningGoal): String = stringResource(
    when (goal) {
        LearningGoal.GENERAL -> R.string.learning_goal_general
        LearningGoal.CONVERSATION -> R.string.learning_goal_conversation
        LearningGoal.TRAVEL -> R.string.learning_goal_travel
        LearningGoal.LIVING_ABROAD -> R.string.learning_goal_living_abroad
        LearningGoal.WORK -> R.string.learning_goal_work
        LearningGoal.STUDY_OR_EXAM -> R.string.learning_goal_study_exam
        LearningGoal.CULTURE_AND_MEDIA -> R.string.learning_goal_culture_media
    }
)

@Composable
private fun priorityLabel(priority: LearningPriority): String = stringResource(
    when (priority) {
        LearningPriority.BALANCED -> R.string.learning_priority_balanced
        LearningPriority.LISTENING -> R.string.learning_priority_listening
        LearningPriority.SPEAKING -> R.string.learning_priority_speaking
        LearningPriority.READING -> R.string.learning_priority_reading
        LearningPriority.WRITING -> R.string.learning_priority_writing
    }
)
