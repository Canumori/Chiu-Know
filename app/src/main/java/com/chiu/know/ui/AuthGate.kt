package com.chiu.know.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.chiu.know.R
import com.chiu.know.backend.ChiuKnowBackend
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.auth.status.SessionStatus
import kotlinx.coroutines.launch

@Composable
fun AuthenticatedChiuKnowApp() {
    val sessionStatus by ChiuKnowBackend.client.auth.sessionStatus.collectAsState()
    val passwordRecoveryRequested by ChiuKnowBackend.passwordRecoveryRequested.collectAsState()

    MaterialTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            when {
                passwordRecoveryRequested && sessionStatus is SessionStatus.Authenticated -> PasswordUpdateScreen()
                sessionStatus is SessionStatus.Authenticated -> ChiuKnowApp()
                else -> EmailLoginScreen()
            }
        }
    }
}

@Composable
private fun EmailLoginScreen() {
    val scope = rememberCoroutineScope()
    val signInError = stringResource(R.string.auth_sign_in_error)
    val resetError = stringResource(R.string.auth_reset_error)
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var busy by remember { mutableStateOf(false) }
    var message by remember { mutableStateOf<String?>(null) }
    var resetSent by remember { mutableStateOf(false) }

    AuthColumn {
        Text(stringResource(R.string.auth_title), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(12.dp))
        Text(stringResource(R.string.auth_description))
        Spacer(Modifier.height(24.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it.trim() },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.auth_email)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email, imeAction = ImeAction.Next)
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.auth_password)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done)
        )
        Spacer(Modifier.height(20.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = !busy && email.isNotBlank() && password.isNotBlank(),
            onClick = {
                scope.launch {
                    busy = true
                    message = null
                    resetSent = false
                    runCatching {
                        ChiuKnowBackend.client.auth.signInWith(Email) {
                            this.email = email
                            this.password = password
                        }
                    }.onFailure { message = it.message ?: signInError }
                    busy = false
                }
            }
        ) { Text(stringResource(R.string.auth_sign_in)) }
        Spacer(Modifier.height(10.dp))
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            enabled = !busy && email.isNotBlank(),
            onClick = {
                scope.launch {
                    busy = true
                    message = null
                    resetSent = false
                    runCatching {
                        ChiuKnowBackend.client.auth.resetPasswordForEmail(email)
                    }.onSuccess {
                        resetSent = true
                    }.onFailure { message = it.message ?: resetError }
                    busy = false
                }
            }
        ) { Text(stringResource(R.string.auth_reset_password)) }
        if (resetSent) {
            Spacer(Modifier.height(16.dp))
            Text(stringResource(R.string.auth_reset_sent))
        }
        message?.let { Spacer(Modifier.height(16.dp)); Text(it) }
    }
}

@Composable
private fun PasswordUpdateScreen() {
    val scope = rememberCoroutineScope()
    val updateError = stringResource(R.string.auth_update_error)
    var password by remember { mutableStateOf("") }
    var confirmation by remember { mutableStateOf("") }
    var busy by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    AuthColumn {
        Text(stringResource(R.string.auth_choose_password), style = MaterialTheme.typography.headlineMedium, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.auth_new_password)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Next)
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = confirmation,
            onValueChange = { confirmation = it },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(stringResource(R.string.auth_confirm_password)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = ImeAction.Done)
        )
        Spacer(Modifier.height(20.dp))
        Button(
            modifier = Modifier.fillMaxWidth(),
            enabled = !busy && password.length >= 8 && password == confirmation,
            onClick = {
                scope.launch {
                    busy = true
                    error = null
                    runCatching {
                        ChiuKnowBackend.client.auth.updateUser { this.password = password }
                    }.onSuccess {
                        ChiuKnowBackend.completePasswordRecovery()
                    }.onFailure { error = it.message ?: updateError }
                    busy = false
                }
            }
        ) { Text(stringResource(R.string.auth_save_password)) }
        if (password.isNotEmpty() && password.length < 8) {
            Spacer(Modifier.height(10.dp))
            Text(stringResource(R.string.auth_password_minimum))
        } else if (confirmation.isNotEmpty() && password != confirmation) {
            Spacer(Modifier.height(10.dp))
            Text(stringResource(R.string.auth_password_mismatch))
        }
        error?.let { Spacer(Modifier.height(12.dp)); Text(it) }
    }
}

@Composable
private fun AuthColumn(content: @Composable () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) { content() }
}
