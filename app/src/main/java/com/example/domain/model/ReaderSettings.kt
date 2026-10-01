package com.example.domain.model

enum class ReaderTheme(val displayName: String) {
    PARCHMENT("Parchment"),
    SEPIA("Sepia"),
    DAY("Day (Pure White)"),
    NIGHT("Night (Slate)"),
    OLED("OLED (Pure Black)")
}

enum class ReaderFontFamily(val displayName: String) {
    SERIF("Literary Serif"),
    SANS("Clean Sans"),
    ARABIC_NASKH("Naskh / Nastaliq Style")
}

enum class LineSpacing(val displayName: String, val multiplier: Float) {
    COMPACT("Compact", 1.35f),
    NORMAL("Comfortable", 1.65f),
    RELAXED("Spacious", 2.0f)
}

data class ReaderSettings(
    val fontSizeSp: Float = 18f,
    val theme: ReaderTheme = ReaderTheme.PARCHMENT,
    val fontFamily: ReaderFontFamily = ReaderFontFamily.SERIF,
    val lineSpacing: LineSpacing = LineSpacing.NORMAL,
    val speechRate: Float = 1.0f,
    val isTtsPlaying: Boolean = false
)
