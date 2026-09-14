package com.chiu.know.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chiu.know.R
import com.chiu.know.model.NarrativeCardProgress
import com.chiu.know.model.NarrativeMicroUnit

@Composable
fun NarrativeCardScreen(
    narrative: NarrativeMicroUnit,
    progress: NarrativeCardProgress,
    @DrawableRes imageResId: Int,
    onAdvance: () -> Unit,
    onBack: () -> Unit
) {
    require(progress.narrativeId == narrative.id) {
        "Narrative card progress must belong to the displayed narrative"
    }
    require(progress.beatCount == narrative.beats.size) {
        "Narrative card progress must match the displayed beat count"
    }

    var advanceHandled by remember(
        narrative.id,
        progress.currentBeatIndex,
        progress.completed
    ) {
        mutableStateOf(false)
    }
    val beat = narrative.beats[progress.currentBeatIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = narrative.title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = narrative.setting,
            style = MaterialTheme.typography.bodyLarge
        )
        Image(
            painter = painterResource(imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 2f),
            contentScale = ContentScale.Fit
        )
        Text(
            text = "${progress.currentBeatIndex + 1} / ${progress.beatCount}",
            style = MaterialTheme.typography.labelLarge
        )
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            color = MaterialTheme.colorScheme.secondaryContainer
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = beat.speaker,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = beat.text,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
        Button(
            onClick = {
                if (!advanceHandled) {
                    advanceHandled = true
                    onAdvance()
                }
            },
            enabled = !advanceHandled,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        ) {
            Text(stringResource(R.string.continue_button))
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
fun NextNarrativeScreen(
    narrative: NarrativeMicroUnit,
    @DrawableRes imageResId: Int,
    onContinue: () -> Unit,
    onBack: () -> Unit
) {
    var continueHandled by remember(narrative.id) { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.next_story),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = narrative.title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = narrative.setting,
            style = MaterialTheme.typography.bodyLarge
        )
        Image(
            painter = painterResource(imageResId),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(3f / 2f),
            contentScale = ContentScale.Fit
        )
        Button(
            onClick = {
                if (!continueHandled) {
                    continueHandled = true
                    onContinue()
                }
            },
            enabled = !continueHandled,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        ) {
            Text(stringResource(R.string.next_story))
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
fun PracticeNowScreen(
    onContinue: () -> Unit,
    onBack: () -> Unit
) {
    var continueHandled by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = stringResource(R.string.practice_now),
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(R.string.practice_now_description),
            style = MaterialTheme.typography.bodyLarge
        )
        Button(
            onClick = {
                if (!continueHandled) {
                    continueHandled = true
                    onContinue()
                }
            },
            enabled = !continueHandled,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp)
        ) {
            Text(stringResource(R.string.practice_now))
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
