package com.chiu.know.model

data class LanguageOption(
    val code: String,
    val label: String
)

val supportedInterfaceLanguages = listOf(
    LanguageOption("pt", "Português"),
    LanguageOption("en", "English"),
    LanguageOption("es", "Español"),
    LanguageOption("fr", "Français"),
    LanguageOption("ko", "한국어")
)

val supportedTargetLanguages = listOf(
    LanguageOption("en", "English"),
    LanguageOption("pt", "Português"),
    LanguageOption("es", "Español"),
    LanguageOption("fr", "Français"),
    LanguageOption("ko", "한국어")
)
