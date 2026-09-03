package com.chiu.know.backend

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.storage.Storage

object ChiuKnowBackend {
    const val projectId = "uskxabsodcnzlovuaurp"
    const val voiceBucket = "character-voices"

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
}
