package com.chiu.know.model

enum class A1SecondTransferNewWorkReason {
    PREREQUISITES_NOT_EXPOSED,
    COMPREHENSION,
    RETRIEVAL_STEP,
    INITIAL_SEQUENCE_COMPLETE,
    NO_CONTENT
}

data class A1SecondTransferNewWorkSelection(
    val activity: LearningActivity?,
    val reason: A1SecondTransferNewWorkReason,
    val missingPrerequisiteReviewKeys: List<String> = emptyList()
) {
    init {
        val shouldHaveActivity = reason == A1SecondTransferNewWorkReason.COMPREHENSION ||
            reason == A1SecondTransferNewWorkReason.RETRIEVAL_STEP
        require((activity != null) == shouldHaveActivity) {
            "Second-transfer new-work reason and activity availability must agree"
        }
        require(
            missingPrerequisiteReviewKeys.isEmpty() ||
                reason == A1SecondTransferNewWorkReason.PREREQUISITES_NOT_EXPOSED
        ) {
            "Missing prerequisite keys are valid only while transfer prerequisites are not exposed"
        }
    }
}

/**
 * Selects the next unscheduled acquisition step inside the connected second A1
 * transfer unit.
 *
 * This selector is intentionally new-work only. It does not inspect due dates and
 * must not be used to outrank scheduled review. A caller composing a real session
 * must continue to give due review priority, as required by AdaptiveSessionPlan.
 *
 * Transfer is unlocked by observed exposure to every starter target linked by the
 * narrative. Correctness is not required for unlocking: an error remains an
 * observed attempt/exposure and never becomes mastery. Inside the unit, the first
 * not-yet-attempted activity is selected by activity id, so out-of-order evidence
 * cannot silently skip an earlier cue-withdrawal step.
 */
fun a1SecondTransferNewWorkSelection(
    languageCode: String,
    evidence: List<LearningEvidence>
): A1SecondTransferNewWorkSelection {
    val unit = a1SecondTransferLearningUnitFor(languageCode)
        ?: return A1SecondTransferNewWorkSelection(
            activity = null,
            reason = A1SecondTransferNewWorkReason.NO_CONTENT
        )

    val a1Evidence = evidence.filter { it.level == CefrLevel.A1 }
    val observedReviewKeys = a1Evidence.map { it.reviewKey }.toSet()
    val missingPrerequisites = unit.narrative.linkedReviewKeys.filter { it !in observedReviewKeys }
    if (missingPrerequisites.isNotEmpty()) {
        return A1SecondTransferNewWorkSelection(
            activity = null,
            reason = A1SecondTransferNewWorkReason.PREREQUISITES_NOT_EXPOSED,
            missingPrerequisiteReviewKeys = missingPrerequisites
        )
    }

    val observedActivityIds = a1Evidence.map { it.activityId }.toSet()
    if (unit.comprehension.id !in observedActivityIds) {
        return A1SecondTransferNewWorkSelection(
            activity = unit.comprehension,
            reason = A1SecondTransferNewWorkReason.COMPREHENSION
        )
    }

    unit.retrievalTracks.forEach { track ->
        val nextActivity = track.activities.firstOrNull { it.id !in observedActivityIds }
        if (nextActivity != null) {
            return A1SecondTransferNewWorkSelection(
                activity = nextActivity,
                reason = A1SecondTransferNewWorkReason.RETRIEVAL_STEP
            )
        }
    }

    return A1SecondTransferNewWorkSelection(
        activity = null,
        reason = A1SecondTransferNewWorkReason.INITIAL_SEQUENCE_COMPLETE
    )
}
