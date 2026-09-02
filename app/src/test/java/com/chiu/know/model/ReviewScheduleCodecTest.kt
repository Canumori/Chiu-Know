package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ReviewScheduleCodecTest {

    private fun state(
        reviewKey: String = "en:a1:greeting:hello",
        lastReviewAt: Long = 100L,
        dueAt: Long = 200L
    ) = ReviewScheduleState(
        reviewKey = reviewKey,
        phase = ReviewPhase.REVIEW,
        difficulty = 4.5,
        stabilityDays = 2.25,
        dueAtEpochMillis = dueAt,
        lastReviewAtEpochMillis = lastReviewAt,
        reviewCount = 3,
        lapseCount = 1
    )

    @Test
    fun encodingRoundTripsAllSchedulerState() {
        val original = state()

        val encoded = encodeReviewScheduleState(original)
        val decoded = decodeReviewScheduleState(encoded)

        assertEquals(original, decoded)
        assertEquals(9, encoded.split("|").size)
    }

    @Test
    fun rejectsUnknownSchemaAndMalformedValues() {
        val valid = encodeReviewScheduleState(state())

        assertNull(decodeReviewScheduleState(valid.replaceFirst("1|", "2|")))
        assertNull(decodeReviewScheduleState("broken"))
        assertNull(decodeReviewScheduleState(valid.replace("|REVIEW|", "|UNKNOWN|")))
        assertNull(decodeReviewScheduleState(valid.replace("|4.5|", "|NaN|")))
        assertNull(decodeReviewScheduleState(valid.replace("|3|1", "|0|1")))
    }

    @Test
    fun setDecoderKeepsNewestValidStatePerKnowledgeTarget() {
        val old = state(lastReviewAt = 100L, dueAt = 200L)
        val newest = state(lastReviewAt = 300L, dueAt = 400L)
        val other = state(reviewKey = "pt:a1:greeting:ola", lastReviewAt = 50L, dueAt = 150L)

        val decoded = decodeReviewScheduleStateSet(
            setOf(
                encodeReviewScheduleState(old),
                encodeReviewScheduleState(newest),
                encodeReviewScheduleState(other),
                "invalid"
            )
        )

        assertEquals(2, decoded.size)
        assertEquals(300L, decoded.first { it.reviewKey == old.reviewKey }.lastReviewAtEpochMillis)
        assertEquals(50L, decoded.first { it.reviewKey == other.reviewKey }.lastReviewAtEpochMillis)
    }
}
