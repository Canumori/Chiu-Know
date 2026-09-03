package com.chiu.know

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.chiu.know.backend.ChiuKnowBackend
import com.chiu.know.ui.AuthenticatedChiuKnowApp
import io.github.jan.supabase.auth.handleDeeplinks

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ChiuKnowBackend.noteAuthIntent(intent)
        ChiuKnowBackend.client.handleDeeplinks(intent)
        setContent {
            AuthenticatedChiuKnowApp()
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        ChiuKnowBackend.noteAuthIntent(intent)
        ChiuKnowBackend.client.handleDeeplinks(intent)
    }
}
