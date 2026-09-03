package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class PlacementAttemptSelectionTest {

    @Test
    fun selectsFirstUnusedQuestionAtRequestedLevel() {
        val questions = listOf(
            PlacementQuestion("b1-1", CefrLevel.B1, "First", listOf("a", "b", "c", "d"), 0),
            PlacementQuestion("b1-2", CefrLevel.B1, "Second", listOf("a", "b", "c", "d"), 1),
            PlacementQuestion("b2-1", CefrLevel.B2, "Other level", listOf("a", "b", "c", "d"), 2)
        )

        val selected = nextUnusedPlacementQuestion(
            questions = questions,
            level = CefrLevel.B1,
            usedQuestionIds = setOf("b1-1")
        )

        assertEquals("b1-2", selected?.id)
    }

    @Test
    fun returnsNullInsteadOfRepeatingWhenLevelIsExhausted() {
        val questions = listOf(
            PlacementQuestion("b1-1", CefrLevel.B1, "First", listOf("a", "b", "c", "d"), 0),
            PlacementQuestion("b1-2", CefrLevel.B1, "Second", listOf("a", "b", "c", "d"), 1)
        )

        val selected = nextUnusedPlacementQuestion(
            questions = questions,
            level = CefrLevel.B1,
            usedQuestionIds = setOf("b1-1", "b1-2")
        )

        assertNull(selected)
    }

    @Test
    fun englishCandidateBankUsesValidatedExpansion() {
        val questions = placementCandidateQuestionsFor("en")

        assertEquals(24, questions.size)
        CefrLevel.entries.forEach { level ->
            assertEquals(4, questions.count { it.level == level })
        }
        assertEquals(questions.size, questions.map { it.id }.toSet().size)
    }

    @Test
    fun otherLanguagesStayOnStarterBankUntilReviewedExpansionExists() {
        listOf("pt", "es", "fr", "ko").forEach { languageCode ->
            assertEquals(
                starterPlacementQuestionsFor(languageCode),
                placementCandidateQuestionsFor(languageCode)
            )
        }
    }

    @Test
    fun repeatedSelectionCanConsumeEnglishLevelWithoutDuplicateIds() {
        val questions = placementCandidateQuestionsFor("en")
        val used = linkedSetOf<String>()

        repeat(4) {
            val selected = nextUnusedPlacementQuestion(questions, CefrLevel.C1, used)
            requireNotNull(selected)
            assertTrue(used.add(selected.id))
        }

        assertNull(nextUnusedPlacementQuestion(questions, CefrLevel.C1, used))
        assertEquals(4, used.size)
    }
}
