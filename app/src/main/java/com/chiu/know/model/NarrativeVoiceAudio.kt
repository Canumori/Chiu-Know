package com.chiu.know.model

/**
 * Optional audio attached to one written narrative beat.
 *
 * Audio here supports simultaneous written + spoken exposure. Its presence does
 * not by itself create LISTENING evidence or prove listening mastery.
 */
data class NarrativeBeatAudio(
    val speaker: String,
    val url: String
)

private val narrativeBeatAudioByKey = mapOf(
    Pair("en-a1-narrative-station-004", 0) to NarrativeBeatAudio(
        speaker = "Mia",
        url = "https://cdn.creativeclaw.co/u/144d4341/audio/53d2912e-4135-42c2-ab49-60788b94c804.wav"
    ),
    Pair("en-a1-narrative-station-004", 1) to NarrativeBeatAudio(
        speaker = "Chiu",
        url = "https://cdn.creativeclaw.co/u/144d4341/audio/b56a0b2c-bb9a-4a1f-b0dc-90ebd4cf0eb5.wav"
    ),
    Pair("en-a1-narrative-station-004", 2) to NarrativeBeatAudio(
        speaker = "Mia",
        url = "https://cdn.creativeclaw.co/u/144d4341/audio/621c3b3a-82d3-4627-9900-2d723547674f.wav"
    ),
    Pair("en-a1-narrative-station-004", 3) to NarrativeBeatAudio(
        speaker = "Chiu",
        url = "https://cdn.creativeclaw.co/u/144d4341/audio/8d2e85b0-a7d6-480f-bde8-ac1beeb1e6d5.wav"
    )
)

/**
 * Returns audio only when the mapping still matches the actual story speaker.
 * This fail-closed check prevents a stale mapping from giving a character the
 * wrong voice after a narrative edit.
 */
fun narrativeBeatAudioFor(
    narrative: NarrativeMicroUnit,
    beatIndex: Int
): NarrativeBeatAudio? {
    val beat = narrative.beats.getOrNull(beatIndex) ?: return null
    val audio = narrativeBeatAudioByKey[Pair(narrative.id, beatIndex)] ?: return null
    return audio.takeIf { it.speaker == beat.speaker }
}
