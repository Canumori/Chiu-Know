package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Test

class LearningAudioPromptTest {
    @Test
    fun systemTtsPromptDoesNotRequireProviderSpecificResource() {
        val prompt = LearningAudioPrompt(
            id = "greeting-en",
            languageCode = "en",
            spokenText = "Good morning",
            delivery = AudioDelivery.SYSTEM_TTS,
            voiceRole = "narrator",
        )

        assertEquals(null, prompt.resourceKey)
    }

    @Test(expected = IllegalArgumentException::class)
    fun packagedAudioRequiresResourceKey() {
        LearningAudioPrompt(
            id = "greeting-en",
            languageCode = "en",
            spokenText = "Good morning",
            delivery = AudioDelivery.PACKAGED_ASSET,
        )
    }

    @Test(expected = IllegalArgumentException::class)
    fun cachedAudioRequiresResourceKey() {
        LearningAudioPrompt(
            id = "greeting-en",
            languageCode = "en",
            spokenText = "Good morning",
            delivery = AudioDelivery.CACHED_FILE,
        )
    }

    @Test
    fun playbackStateFollowsDeterministicHappyPath() {
        var state = AudioPlaybackState.IDLE

        state = reduceAudioPlayback(state, AudioPlaybackEvent.PLAY_REQUESTED)
        assertEquals(AudioPlaybackState.LOADING, state)

        state = reduceAudioPlayback(state, AudioPlaybackEvent.READY)
        assertEquals(AudioPlaybackState.READY, state)

        state = reduceAudioPlayback(state, AudioPlaybackEvent.STARTED)
        assertEquals(AudioPlaybackState.PLAYING, state)

        state = reduceAudioPlayback(state, AudioPlaybackEvent.PAUSED)
        assertEquals(AudioPlaybackState.PAUSED, state)

        state = reduceAudioPlayback(state, AudioPlaybackEvent.RESUMED)
        assertEquals(AudioPlaybackState.PLAYING, state)

        state = reduceAudioPlayback(state, AudioPlaybackEvent.COMPLETED)
        assertEquals(AudioPlaybackState.COMPLETED, state)
    }

    @Test
    fun invalidPlaybackTransitionKeepsCurrentState() {
        assertEquals(
            AudioPlaybackState.IDLE,
            reduceAudioPlayback(
                AudioPlaybackState.IDLE,
                AudioPlaybackEvent.COMPLETED,
            ),
        )
    }

    @Test
    fun failureAndResetAreExplicit() {
        assertEquals(
            AudioPlaybackState.ERROR,
            reduceAudioPlayback(
                AudioPlaybackState.PLAYING,
                AudioPlaybackEvent.FAILED,
            ),
        )
        assertEquals(
            AudioPlaybackState.IDLE,
            reduceAudioPlayback(
                AudioPlaybackState.ERROR,
                AudioPlaybackEvent.RESET,
            ),
        )
    }
}
