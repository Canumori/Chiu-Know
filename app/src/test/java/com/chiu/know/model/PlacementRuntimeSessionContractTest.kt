package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class PlacementRuntimeSessionContractTest {

    @Test
    fun everySupportedRuntimeSelectionCompletesWithoutQuestionReuse() {
        val policy = PlacementQualityPolicy()

        supportedTargetLanguages.forEach { language ->
            val runtime = placementRuntimeSelection(language.code)
            assertEquals(PlacementRuntimeMode.QUALITY_SESSION, runtime.mode)

            var state = startPlacementSession(runtime.questions)
            while (!state.isFinished) {
                assertTrue(state.answeredQuestions < policy.maximumAnsweredQuestions)
                assertEquals(state.answeredQuestions + 1, state.usedQuestionIds.size)
                assertTrue(requireNotNull(state.current).question.id in state.usedQuestionIds)

                state = advancePlacementSession(
                    state = state,
                    answeredCorrectly = state.answeredQuestions % 2 == 0,
                    questions = runtime.questions,
                    policy = policy
                )
            }

            assertNull(state.current)
            assertTrue(state.answeredQuestions <= policy.maximumAnsweredQuestions)
            assertEquals(state.answeredQuestions, state.usedQuestionIds.size)
            assertEquals(state.usedQuestionIds.size, state.usedQuestionIds.toSet().size)
        }
    }
}
