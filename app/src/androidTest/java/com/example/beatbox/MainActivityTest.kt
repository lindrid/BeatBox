package com.example.beatbox

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

// more about UI Testing with Espresso
// https://www.section.io/engineering-education/automating-ui-tests-in-android-using-espresso/

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
  @get:Rule
  val activityRule = ActivityScenarioRule(MainActivity::class.java)

  @Test
  fun showsTwoFileNames() {
    onView(withText("65_cjipie.wav")).check(matches(isDisplayed()))
    onView(withText("66_indios.wav")).check(matches(isDisplayed()))
  }
}