package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class LearningEvidenceCodecTest {

    @Test
    fun decodesPersistedEvidence() {
        val decoded = decodeLearningEvidence(
            "1234|en-a1-greeting-001|en:a1:greeting:hello|A1|VOCABULARY|true"
        )

        requireNotNull(decoded)
        assertEquals("en-a1-greeting-001", decoded.activityId)
        assertEquals("en:a1:greeting:hello", decoded.reviewKey)
        assertEquals(CefrLevel.A1, decoded.level)
        assertEquals(LearningSkill.VOCABULARY, decoded.primarySkill)
        assertEquals(true, decoded.correct)
        assertEquals(1234L, decoded.attemptedAtEpochMillis)
    }

    @Test
    fun ignoresMalformedEvidence() {
        assertNull(decodeLearningEvidence("broken"))
        assertNull(decodeLearningEvidence("123|id|key|Z9|VOCABULARY|true"))
        assertNull(decodeLearningEvidence("123|id|key|A1|UNKNOWN|true"))
        assertNull(decodeLearningEvidence("123|id|key|A1|VOCABULARY|maybe"))
    }

    @Test
    fun setDecoderKeepsValidEntriesOnly() {
        val decoded = decodeLearningEvidenceSet(
            setOf(
                "100|one|one:key|A1|VOCABULARY|false",
                "invalid",
                "200|two|two:key|B1|READING|true"
            )
        )

        assertEquals(2, decoded.size)
        assertEquals(setOf("one", "two"), decoded.map { it.activityId }.toSet())
    }
}
