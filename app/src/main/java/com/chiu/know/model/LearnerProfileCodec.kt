package com.chiu.know.model

private const val learnerPreferencesVersion = "v1"
private const val learnerPreferencesFieldSeparator = "|"

/**
 * Compact local persistence format for learner-declared preferences.
 *
 * Preferences are planning inputs only. They contain no proficiency, mastery,
 * score or CEFR evidence and must never be interpreted as such.
 */
fun encodeLearnerPreferences(preferences: LearnerPreferences): String =
    listOf(
        learnerPreferencesVersion,
        preferences.goal.name,
        preferences.priority.name,
        preferences.dailyMinutes
    ).joinToString(learnerPreferencesFieldSeparator)

/**
 * Decodes only the explicitly supported version. Invalid, obsolete or
 * out-of-range data fails closed so corrupted local state cannot silently
 * manufacture a learner profile.
 */
fun decodeLearnerPreferences(encoded: String): LearnerPreferences? {
    val fields = encoded.split(learnerPreferencesFieldSeparator)
    if (fields.size != 4 || fields[0] != learnerPreferencesVersion) return null

    val goal = LearningGoal.entries.firstOrNull { it.name == fields[1] } ?: return null
    val priority = LearningPriority.entries.firstOrNull { it.name == fields[2] } ?: return null
    val dailyMinutes = fields[3].toIntOrNull() ?: return null

    return runCatching {
        LearnerPreferences(
            goal = goal,
            priority = priority,
            dailyMinutes = dailyMinutes
        )
    }.getOrNull()
}
