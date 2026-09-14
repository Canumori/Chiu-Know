package com.chiu.know.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chiu.know.R
import com.chiu.know.model.LearningSkill
import com.chiu.know.model.LearningSkillEvidenceSummary

@Composable
fun ObservedPracticeScreen(
    summaries: List<LearningSkillEvidenceSummary>,
    onBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.observed_practice_title),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(R.string.observed_practice_note),
            style = MaterialTheme.typography.bodyLarge
        )
        if (summaries.isEmpty()) {
            Text(
                text = stringResource(R.string.observed_practice_empty),
                style = MaterialTheme.typography.bodyLarge
            )
        } else {
            summaries.forEach { summary ->
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(18.dp),
                    color = MaterialTheme.colorScheme.secondaryContainer
                ) {
                    Text(
                        text = stringResource(
                            R.string.observed_practice_item,
                            skillLabel(summary.skill),
                            summary.totalAttempts,
                            summary.correctAttempts,
                            summary.incorrectAttempts,
                            summary.distinctReviewTargetCount
                        ) + " · ${summary.level.name}",
                        modifier = Modifier.padding(18.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
        OutlinedButton(
            onClick = onBack,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        ) {
            Text(stringResource(R.string.back_to_path))
        }
    }
}

@Composable
private fun skillLabel(skill: LearningSkill): String = when (skill) {
    LearningSkill.GRAMMAR -> stringResource(R.string.skill_grammar)
    LearningSkill.VOCABULARY -> stringResource(R.string.skill_vocabulary)
    LearningSkill.LISTENING -> stringResource(R.string.skill_listening)
    LearningSkill.READING -> stringResource(R.string.skill_reading)
    LearningSkill.WRITING -> stringResource(R.string.skill_writing)
    LearningSkill.SPEAKING -> stringResource(R.string.skill_speaking)
}
