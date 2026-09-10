package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Test

class CefrTrailTest {
    @Test
    fun marksEstimatedLevelAsCurrent() {
        val trail = buildCefrTrail(CefrLevel.B1)

        assertEquals(CefrTrailStatus.COMPLETED, trail.first { it.level == CefrLevel.A1 }.status)
        assertEquals(CefrTrailStatus.COMPLETED, trail.first { it.level == CefrLevel.A2 }.status)
        assertEquals(CefrTrailStatus.CURRENT, trail.first { it.level == CefrLevel.B1 }.status)
        assertEquals(CefrTrailStatus.LOCKED, trail.first { it.level == CefrLevel.B2 }.status)
        assertEquals(CefrTrailStatus.LOCKED, trail.first { it.level == CefrLevel.C2 }.status)
    }

    @Test
    fun alwaysBuildsAllSixCefrLevels() {
        val trail = buildCefrTrail(CefrLevel.C2)

        assertEquals(CefrLevel.entries.toList(), trail.map { it.level })
        assertEquals(CefrTrailStatus.CURRENT, trail.last().status)
    }
}
