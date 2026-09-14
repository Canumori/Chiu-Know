package com.chiu.know.backend

import android.content.Intent
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.storage.Storage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object ChiuKnowBackend {
    const val projectId = "uskxabsodcnzlovuaurp"
    const val voiceBucket = "character-voices"

    private val mutablePasswordRecoveryRequested = MutableStateFlow(false)
    val passwordRecoveryRequested = mutablePasswordRecoveryRequested.asStateFlow()

    val client = createSupabaseClient(
        supabaseUrl = "https://uskxabsodcnzlovuaurp.supabase.co",
        supabaseKey = "sb_publishable_AwCki0ovkuwypZoERBGqjQ_t7CBOTD6"
    ) {
        install(Auth) {
            scheme = "chiuknow"
            host = "auth-callback"
        }
        install(Storage)
    }

    fun noteAuthIntent(intent: Intent) {
        val uri = intent.data ?: return
        val recoveryInQuery = uri.getQueryParameter("type") == "recovery"
        val recoveryInFragment = uri.fragment
            ?.split('&')
            ?.any { it == "type=recovery" }
            ?: false
        if (recoveryInQuery || recoveryInFragment) {
            mutablePasswordRecoveryRequested.value = true
        }
    }

    fun completePasswordRecovery() {
        mutablePasswordRecoveryRequested.value = false
    }
}
