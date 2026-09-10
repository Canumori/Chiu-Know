package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1IntegratedLearningActivitiesTest {

    @Test
    fun providesTwoA1GrammarTransferContextsForEverySupportedLanguage() {
        supportedTargetLanguages.forEach { language ->
            val activities = a1IntegratedGrammarActivitiesFor(language.code)
            assertEquals(2, activities.size)
            assertTrue(activities.all { it.level == CefrLevel.A1 })
            assertTrue(activities.all { it.primarySkill == LearningSkill.GRAMMAR })
            assertTrue(activities.all { it.responseType == ResponseType.FILL_IN })
            assertEquals(1, activities.map { it.reviewKey }.distinct().size)
            assertTrue(activities.all { it.reviewKey.startsWith("${language.code}:a1:grammar:") })
        }
    }
}
