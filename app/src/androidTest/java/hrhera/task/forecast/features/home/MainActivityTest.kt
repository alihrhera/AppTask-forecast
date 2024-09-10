package hrhera.task.forecast.features.home

import android.graphics.Rect
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isClickable
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import hrhera.task.forecast.R
import org.hamcrest.CoreMatchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {


    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun checkActivityVisibility() {
        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }

    @Test
    fun checkRetryButtonIsClickable() {
        onView(withId(R.id.retry)).check(matches(isClickable()));
    }

    @Test
    fun checkLoadingIsVisibleClickable() {
        onView(withId(R.id.cities_loading)).check { view, _ ->
            val isVisible = view.visibility == View.VISIBLE
            val rect = Rect()
            view.getGlobalVisibleRect(rect)
            println("Visibility: $isVisible, Global Rect: $rect")
        }
    }

    @Test
    fun checkCityIsVisible() {
        onView(withId(R.id.cityContainer)).check(matches(isDisplayed()))
    }


    @Test
    fun checkCityIsClickable() {
        onView(withId(R.id.cities)).check(matches(isClickable()))
    }

}