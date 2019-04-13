package com.west.pratice.guess

import android.content.res.Resources
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MaterialActivityTest {


    @Rule
    @JvmField
    val activityTestRule = ActivityTestRule<MaterialActivity>(MaterialActivity::class.java)

    private var secretNumber: SecretNumber? = null
    private var resource: Resources? = null

    fun init() {
        secretNumber = activityTestRule.activity.secretNumber
        resource = activityTestRule.activity.resources
    }

    @Test
    fun allUiTest(){
        guessWrongTest()
        replayTest()
    }

    @Test
    fun guessWrongTest() {
        init()
        val secret = secretNumber!!.secret
        for (guessNumber in 1..10) {
            if (guessNumber != secret) {
                onView(withId(R.id.ed_number)).perform(clearText())
                onView(withId(R.id.ed_number)).perform(typeTextIntoFocusedView(guessNumber.toString()))
                onView(withId(R.id.bt_ok)).perform(click())
                val message = activityTestRule.activity.message(guessNumber.validate(secret))
                onView(withText(message)).check(matches(isDisplayed()))
                onView(withText(resource?.getString(R.string.ok))).perform(click())
            }
        }
    }

    @Test
    fun replayTest() {
        init()
        onView(withId(R.id.fab)).perform(click())
        onView(withText(resource!!.getString(R.string.ok))).perform(click())
        check(secretNumber!!.guessCount == 0)
    }
}