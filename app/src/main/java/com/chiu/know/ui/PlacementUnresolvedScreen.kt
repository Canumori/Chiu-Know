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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.chiu.know.R
import com.chiu.know.model.PlacementTerminalReason

/**
 * Honest terminal state for quality placement attempts that cannot produce a
 * defensible CEFR estimate. It never displays or persists a provisional level.
 */
@Composable
internal fun PlacementUnresolvedScreen(
    reason: PlacementTerminalReason,
    onRetry: () -> Unit,
    onChangeLanguage: () -> Unit
) {
    val title = when (reason) {
        PlacementTerminalReason.MAX_EVIDENCE_INCONCLUSIVE ->
            stringResource(R.string.placement_inconclusive_title)
        PlacementTerminalReason.BANK_INSUFFICIENT ->
            stringResource(R.string.placement_bank_insufficient_title)
    }
    val description = when (reason) {
        PlacementTerminalReason.MAX_EVIDENCE_INCONCLUSIVE ->
            stringResource(R.string.placement_inconclusive_description)
        PlacementTerminalReason.BANK_INSUFFICIENT ->
            stringResource(R.string.placement_bank_insufficient_description)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(16.dp))
        Text(text = description, style = MaterialTheme.typography.bodyLarge)
        Spacer(Modifier.height(28.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            onClick = onRetry
        ) {
            Text(stringResource(R.string.try_again))
        }
        Spacer(Modifier.height(12.dp))
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(18.dp),
            onClick = onChangeLanguage
        ) {
            Text(stringResource(R.string.change_languages))
        }
    }
}
