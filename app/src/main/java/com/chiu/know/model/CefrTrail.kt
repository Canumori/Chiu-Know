package com.chiu.know.model

enum class CefrTrailStatus {
    COMPLETED,
    CURRENT,
    LOCKED
}

data class CefrTrailLevel(
    val level: CefrLevel,
    val status: CefrTrailStatus
)

/**
 * Builds the initial deterministic CEFR path from the placement estimate.
 *
 * Levels below the estimate are treated as already reached, the estimated
 * level becomes the learner's current starting point, and higher levels stay
 * locked until real lesson/progress rules are introduced.
 */
fun buildCefrTrail(estimatedLevel: CefrLevel): List<CefrTrailLevel> {
    val currentIndex = CefrLevel.entries.indexOf(estimatedLevel)
    return CefrLevel.entries.mapIndexed { index, level ->
        CefrTrailLevel(
            level = level,
            status = when {
                index < currentIndex -> CefrTrailStatus.COMPLETED
                index == currentIndex -> CefrTrailStatus.CURRENT
                else -> CefrTrailStatus.LOCKED
            }
        )
    }
}
