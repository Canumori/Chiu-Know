package com.chiu.know.model

/**
 * Normalizes learner preferences for local persistence.
 *
 * Only valid, versioned learner-declared preferences are retained. Preferences
 * are planning inputs only and must never be used as CEFR, mastery or unlock
 * evidence.
 */
fun persistedLearnerPreferences(preferences: LearnerPreferences?): String? =
    preferences?.let(::encodeLearnerPreferences)

/**
 * Reads learner preferences from local persistence and fails closed when the
 * stored value is absent, malformed, obsolete or outside the supported bounds.
 */
fun restoredLearnerPreferences(encoded: String?): LearnerPreferences? =
    encoded?.let(::decodeLearnerPreferences)
