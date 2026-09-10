package com.chiu.know.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class A1MultipleChoiceActivitiesTest {

    @Test
    fun providesOneDeterministicA1ReadingChoicePerSupportedLanguage() {
        supportedTargetLanguages.forEach { language ->
            val activities = a1MultipleChoiceActivitiesFor(language.code)

            assertEquals(1, activities.size)
            val activity = activities.single()
            assertEquals(CefrLevel.A1, activity.level)
            assertEquals(LearningSkill.READING, activity.primarySkill)
            assertEquals(ResponseType.MULTIPLE_CHOICE, activity.responseType)
            assertTrue(activity.acceptedAnswers.all { it in activity.responseOptions })
            assertTrue(activity.responseOptions.distinct().size == activity.responseOptions.size)
        }
    }

    @Test
    fun keepsChoiceAsTransferWithinExistingReadingReviewTarget() {
        supportedTargetLanguages.forEach { language ->
            val choice = a1MultipleChoiceActivitiesFor(language.code).single()
            val reading = a1ReadingActivitiesFor(language.code).first()

            assertEquals(reading.reviewKey, choice.reviewKey)
            assertEquals(reading.knowledgeTarget, choice.knowledgeTarget)
        }
    }
}
