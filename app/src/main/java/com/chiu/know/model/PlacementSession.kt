package com.chiu.know.model

/**
 * Model-layer orchestration for one placement attempt.
 *
 * The session owns question reuse protection, adaptive locating, confirmation
 * evidence and conservative completion. It intentionally contains no UI and no
 * calibrated confidence score.
 */
enum class PlacementSessionPhase {
    LOCATE,
    CONFIRM,
    COMPLETE,
    BANK_INSUFFICIENT
}

enum class PlacementTerminalReason {
    BANK_INSUFFICIENT,
    MAX_EVIDENCE_INCONCLUSIVE
}

data class PlacementSessionQuestion(
    val question: PlacementQuestion,
    val confirmationRole: PlacementConfirmationRole? = null
)

data class PlacementSessionState(
    val adaptiveState: AdaptivePlacementState,
    val phase: PlacementSessionPhase,
    val current: PlacementSessionQuestion?,
    val usedQuestionIds: Set<String>,
    val confirmationQueue: List<PlacementSessionQuestion> = emptyList(),
    val confirmationEvidence: List<PlacementConfirmationEvidence> = emptyList(),
    val provisionalLevel: CefrLevel? = null,
    val finalDecision: PlacementConfirmationDecision? = null,
    val answeredQuestions: Int = 0,
    val terminalReason: PlacementTerminalReason? = null
) {
    val isFinished: Boolean
        get() = phase == PlacementSessionPhase.COMPLETE || phase == PlacementSessionPhase.BANK_INSUFFICIENT
}

fun startPlacementSession(
    questions: List<PlacementQuestion>
): PlacementSessionState {
    val adaptive = startAdaptivePlacement()
    val first = nextUnusedPlacementQuestion(questions, adaptive.currentLevel, emptySet())
        ?: return PlacementSessionState(
            adaptiveState = adaptive,
            phase = PlacementSessionPhase.BANK_INSUFFICIENT,
            current = null,
            usedQuestionIds = emptySet(),
            terminalReason = PlacementTerminalReason.BANK_INSUFFICIENT
        )

    return PlacementSessionState(
        adaptiveState = adaptive,
        phase = PlacementSessionPhase.LOCATE,
        current = PlacementSessionQuestion(first),
        usedQuestionIds = setOf(first.id)
    )
}

fun advancePlacementSession(
    state: PlacementSessionState,
    answeredCorrectly: Boolean,
    questions: List<PlacementQuestion>,
    policy: PlacementQualityPolicy = PlacementQualityPolicy()
): PlacementSessionState {
    require(!state.isFinished) { "Placement session is already finished" }
    val current = requireNotNull(state.current) { "Active placement session must have a question" }
    val answeredCount = state.answeredQuestions + 1

    return when (state.phase) {
        PlacementSessionPhase.LOCATE -> advanceLocating(
            state = state,
            answeredCorrectly = answeredCorrectly,
            questions = questions,
            policy = policy,
            answeredCount = answeredCount
        )

        PlacementSessionPhase.CONFIRM -> advanceConfirmation(
            state = state,
            current = current,
            answeredCorrectly = answeredCorrectly,
            questions = questions,
            policy = policy,
            answeredCount = answeredCount
        )

        PlacementSessionPhase.COMPLETE,
        PlacementSessionPhase.BANK_INSUFFICIENT -> error("Finished session cannot advance")
    }
}

private fun advanceLocating(
    state: PlacementSessionState,
    answeredCorrectly: Boolean,
    questions: List<PlacementQuestion>,
    policy: PlacementQualityPolicy,
    answeredCount: Int
): PlacementSessionState {
    val step = advanceAdaptivePlacement(state.adaptiveState, answeredCorrectly)

    if (!step.finished) {
        val next = nextUnusedPlacementQuestion(
            questions = questions,
            level = requireNotNull(step.nextLevel),
            usedQuestionIds = state.usedQuestionIds
        ) ?: return state.copy(
            adaptiveState = step.state,
            phase = PlacementSessionPhase.BANK_INSUFFICIENT,
            current = null,
            answeredQuestions = answeredCount,
            terminalReason = PlacementTerminalReason.BANK_INSUFFICIENT
        )

        return state.copy(
            adaptiveState = step.state,
            current = PlacementSessionQuestion(next),
            usedQuestionIds = state.usedQuestionIds + next.id,
            answeredQuestions = answeredCount
        )
    }

    val provisional = step.estimatedLevel
    val queue = buildConfirmationQueue(
        questions = questions,
        provisionalLevel = provisional,
        usedQuestionIds = state.usedQuestionIds,
        alreadyAnswered = answeredCount,
        policy = policy
    ) ?: return state.copy(
        adaptiveState = step.state,
        phase = PlacementSessionPhase.BANK_INSUFFICIENT,
        current = null,
        provisionalLevel = provisional,
        answeredQuestions = answeredCount,
        finalDecision = decidePlacementFromConfirmation(
            provisionalLevel = provisional,
            evidence = emptyList(),
            policy = policy,
            bankInsufficient = true
        ),
        terminalReason = PlacementTerminalReason.BANK_INSUFFICIENT
    )

    val first = queue.firstOrNull()
        ?: return state.copy(
            adaptiveState = step.state,
            phase = PlacementSessionPhase.BANK_INSUFFICIENT,
            current = null,
            provisionalLevel = provisional,
            answeredQuestions = answeredCount,
            terminalReason = PlacementTerminalReason.BANK_INSUFFICIENT
        )

    return state.copy(
        adaptiveState = step.state,
        phase = PlacementSessionPhase.CONFIRM,
        current = first,
        usedQuestionIds = state.usedQuestionIds + first.question.id,
        confirmationQueue = queue.drop(1),
        provisionalLevel = provisional,
        answeredQuestions = answeredCount
    )
}

private fun advanceConfirmation(
    state: PlacementSessionState,
    current: PlacementSessionQuestion,
    answeredCorrectly: Boolean,
    questions: List<PlacementQuestion>,
    policy: PlacementQualityPolicy,
    answeredCount: Int
): PlacementSessionState {
    val role = requireNotNull(current.confirmationRole)
    val evidence = state.confirmationEvidence + PlacementConfirmationEvidence(
        questionId = current.question.id,
        level = current.question.level,
        role = role,
        correct = answeredCorrectly
    )

    if (state.confirmationQueue.isNotEmpty() && answeredCount < policy.maximumAnsweredQuestions) {
        val next = state.confirmationQueue.first()
        return state.copy(
            current = next,
            usedQuestionIds = state.usedQuestionIds + next.question.id,
            confirmationQueue = state.confirmationQueue.drop(1),
            confirmationEvidence = evidence,
            answeredQuestions = answeredCount
        )
    }

    val provisional = requireNotNull(state.provisionalLevel)
    val decision = decidePlacementFromConfirmation(
        provisionalLevel = provisional,
        evidence = evidence,
        policy = policy
    )

    val enoughAnswers = answeredCount >= policy.minimumAnsweredQuestions
    val decisive = decision.status !in setOf(
        PlacementDecisionStatus.NEEDS_MORE_EVIDENCE,
        PlacementDecisionStatus.BANK_INSUFFICIENT
    )

    if (enoughAnswers && decisive) {
        return state.copy(
            phase = PlacementSessionPhase.COMPLETE,
            current = null,
            confirmationQueue = emptyList(),
            confirmationEvidence = evidence,
            finalDecision = decision,
            answeredQuestions = answeredCount,
            terminalReason = null
        )
    }

    if (answeredCount < policy.maximumAnsweredQuestions) {
        val supplemental = nextSupplementalConfirmationQuestion(
            questions = questions,
            provisionalLevel = provisional,
            usedQuestionIds = state.usedQuestionIds
        )
        if (supplemental != null) {
            return state.copy(
                phase = PlacementSessionPhase.CONFIRM,
                current = supplemental,
                usedQuestionIds = state.usedQuestionIds + supplemental.question.id,
                confirmationQueue = emptyList(),
                confirmationEvidence = evidence,
                finalDecision = null,
                answeredQuestions = answeredCount,
                terminalReason = null
            )
        }
    }

    val hitEvidenceCap = answeredCount >= policy.maximumAnsweredQuestions
    return state.copy(
        phase = PlacementSessionPhase.BANK_INSUFFICIENT,
        current = null,
        confirmationQueue = emptyList(),
        confirmationEvidence = evidence,
        finalDecision = if (hitEvidenceCap) decision else PlacementConfirmationDecision(
            provisionalLevel = provisional,
            decidedLevel = null,
            status = PlacementDecisionStatus.BANK_INSUFFICIENT
        ),
        answeredQuestions = answeredCount,
        terminalReason = if (hitEvidenceCap) {
            PlacementTerminalReason.MAX_EVIDENCE_INCONCLUSIVE
        } else {
            PlacementTerminalReason.BANK_INSUFFICIENT
        }
    )
}

private fun nextSupplementalConfirmationQuestion(
    questions: List<PlacementQuestion>,
    provisionalLevel: CefrLevel,
    usedQuestionIds: Set<String>
): PlacementSessionQuestion? {
    val levels = CefrLevel.entries
    val index = levels.indexOf(provisionalLevel)
    val targets = listOfNotNull(
        provisionalLevel to PlacementConfirmationRole.ESTIMATED_LEVEL,
        levels.getOrNull(index - 1)?.let { it to PlacementConfirmationRole.LOWER_BOUNDARY },
        levels.getOrNull(index + 1)?.let { it to PlacementConfirmationRole.UPPER_BOUNDARY }
    )

    return targets.firstNotNullOfOrNull { (level, role) ->
        nextUnusedPlacementQuestion(questions, level, usedQuestionIds)?.let {
            PlacementSessionQuestion(it, role)
        }
    }
}

/**
 * Builds the mandatory confirmation plan and then adds fresh nearby evidence
 * until the session can satisfy the minimum-answer contract. Supplemental items
 * never weaken the mandatory role counts used by the decision rule.
 */
private fun buildConfirmationQueue(
    questions: List<PlacementQuestion>,
    provisionalLevel: CefrLevel,
    usedQuestionIds: Set<String>,
    alreadyAnswered: Int,
    policy: PlacementQualityPolicy
): List<PlacementSessionQuestion>? {
    val targets = placementConfirmationTargets(provisionalLevel, policy)
    val mandatory = selectPlacementConfirmationQuestions(
        questions = questions,
        targets = targets,
        usedQuestionIds = usedQuestionIds
    ) ?: return null

    val queue = targets.zip(mandatory).map { (target, question) ->
        PlacementSessionQuestion(question, target.role)
    }.toMutableList()

    val used = (usedQuestionIds + mandatory.map { it.id }).toMutableSet()
    val levels = CefrLevel.entries
    val index = levels.indexOf(provisionalLevel)
    val supplementalTargets = listOfNotNull(
        provisionalLevel to PlacementConfirmationRole.ESTIMATED_LEVEL,
        levels.getOrNull(index - 1)?.let { it to PlacementConfirmationRole.LOWER_BOUNDARY },
        levels.getOrNull(index + 1)?.let { it to PlacementConfirmationRole.UPPER_BOUNDARY }
    )

    var targetIndex = 0
    while (alreadyAnswered + queue.size < policy.minimumAnsweredQuestions &&
        alreadyAnswered + queue.size < policy.maximumAnsweredQuestions
    ) {
        var added = false
        var attempts = 0
        while (attempts < supplementalTargets.size && !added) {
            val (level, role) = supplementalTargets[targetIndex % supplementalTargets.size]
            targetIndex++
            attempts++
            val question = nextUnusedPlacementQuestion(questions, level, used)
            if (question != null) {
                queue += PlacementSessionQuestion(question, role)
                used += question.id
                added = true
            }
        }
        if (!added) return null
    }

    return queue
}
