package com.chiu.know.model

enum class TemporaryVoiceStyle {
    NEUTRAL,
    CALM,
    LIVELY,
}

data class TemporaryVoiceSample(
    val style: TemporaryVoiceStyle,
    val speechRate: Float,
    val pitch: Float,
)

fun temporaryVoiceSamples(): List<TemporaryVoiceSample> = listOf(
    TemporaryVoiceSample(TemporaryVoiceStyle.NEUTRAL, speechRate = 1.0f, pitch = 1.0f),
    TemporaryVoiceSample(TemporaryVoiceStyle.CALM, speechRate = 0.88f, pitch = 0.96f),
    TemporaryVoiceSample(TemporaryVoiceStyle.LIVELY, speechRate = 1.08f, pitch = 1.08f),
)

fun voiceSamplePhrase(languageCode: String): String = when (languageCode) {
    "pt" -> "Olá! Eu sou o Chiu. Vamos aprender juntos."
    "en" -> "Hello! I'm Chiu. Let's learn together."
    "es" -> "¡Hola! Soy Chiu. Aprendamos juntos."
    "fr" -> "Bonjour ! Je suis Chiu. Apprenons ensemble."
    "ko" -> "안녕하세요! 저는 치우예요. 같이 배워요."
    else -> "Hello! I'm Chiu. Let's learn together."
}
