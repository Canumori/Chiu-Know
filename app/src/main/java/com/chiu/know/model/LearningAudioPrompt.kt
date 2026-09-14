package com.chiu.know.model

enum class AudioDelivery {
    SYSTEM_TTS,
    PACKAGED_ASSET,
    CACHED_FILE,
}

data class LearningAudioPrompt(
    val id: String,
    val languageCode: String,
    val spokenText: String,
    val delivery: AudioDelivery,
    val resourceKey: String? = null,
    val voiceRole: String? = null,
) {
    init {
        require(id.isNotBlank()) { "Audio prompt id cannot be blank" }
        require(languageCode.isNotBlank()) { "Audio prompt languageCode cannot be blank" }
        require(spokenText.isNotBlank()) { "Audio prompt spokenText cannot be blank" }
        require(voiceRole == null || voiceRole.isNotBlank()) {
            "Audio prompt voiceRole cannot be blank"
        }
        require(
            delivery == AudioDelivery.SYSTEM_TTS || !resourceKey.isNullOrBlank(),
        ) {
            "Packaged and cached audio prompts require a resourceKey"
        }
    }
}

enum class AudioPlaybackState {
    IDLE,
    LOADING,
    READY,
    PLAYING,
    PAUSED,
    COMPLETED,
    ERROR,
}

enum class AudioPlaybackEvent {
    PLAY_REQUESTED,
    READY,
    STARTED,
    PAUSED,
    RESUMED,
    COMPLETED,
    FAILED,
    RESET,
}

fun reduceAudioPlayback(
    state: AudioPlaybackState,
    event: AudioPlaybackEvent,
): AudioPlaybackState =
    when {
        event == AudioPlaybackEvent.RESET -> AudioPlaybackState.IDLE
        event == AudioPlaybackEvent.FAILED -> AudioPlaybackState.ERROR
        event == AudioPlaybackEvent.PLAY_REQUESTED &&
            state in setOf(
                AudioPlaybackState.IDLE,
                AudioPlaybackState.COMPLETED,
                AudioPlaybackState.ERROR,
            ) -> AudioPlaybackState.LOADING
        event == AudioPlaybackEvent.READY &&
            state == AudioPlaybackState.LOADING -> AudioPlaybackState.READY
        event == AudioPlaybackEvent.STARTED &&
            state in setOf(
                AudioPlaybackState.LOADING,
                AudioPlaybackState.READY,
            ) -> AudioPlaybackState.PLAYING
        event == AudioPlaybackEvent.PAUSED &&
            state == AudioPlaybackState.PLAYING -> AudioPlaybackState.PAUSED
        event == AudioPlaybackEvent.RESUMED &&
            state == AudioPlaybackState.PAUSED -> AudioPlaybackState.PLAYING
        event == AudioPlaybackEvent.COMPLETED &&
            state in setOf(
                AudioPlaybackState.PLAYING,
                AudioPlaybackState.PAUSED,
            ) -> AudioPlaybackState.COMPLETED
        else -> state
    }
