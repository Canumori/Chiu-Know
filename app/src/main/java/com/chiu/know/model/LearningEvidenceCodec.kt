package com.chiu.know.model

private const val learningEvidenceFieldSeparator = "|"

/**
 * Encodes one local learning-attempt observation using the compact DataStore format.
 * The raw learner answer is intentionally not persisted.
 */
fun encodeLearningEvidence(evidence: LearningEvidence): String =
    listOf(
        evidence.attemptedAtEpochMillis,
        evidence.activityId,
        evidence.reviewKey,
        evidence.level.name,
        evidence.primarySkill.name,
        evidence.correct
    ).joinToString(learningEvidenceFieldSeparator)

/**
 * Decodes the compact local DataStore representation used for learning attempts.
 * Invalid or obsolete entries are ignored instead of crashing the learning flow.
 */
fun decodeLearningEvidence(encoded: String): LearningEvidence? {
    val fields = encoded.split(learningEvidenceFieldSeparator)
    if (fields.size != 6) return null

    val attemptedAt = fields[0].toLongOrNull() ?: return null
    val level = CefrLevel.entries.firstOrNull { it.name == fields[3] } ?: return null
    val skill = LearningSkill.entries.firstOrNull { it.name == fields[4] } ?: return null
    val correct = when (fields[5]) {
        "true" -> true
        "false" -> false
        else -> return null
    }

    return runCatching {
        LearningEvidence(
            activityId = fields[1],
            reviewKey = fields[2],
            level = level,
            primarySkill = skill,
            correct = correct,
            attemptedAtEpochMillis = attemptedAt
        )
    }.getOrNull()
}

fun decodeLearningEvidenceSet(encoded: Set<String>): List<LearningEvidence> =
    encoded.mapNotNull(::decodeLearningEvidence)
