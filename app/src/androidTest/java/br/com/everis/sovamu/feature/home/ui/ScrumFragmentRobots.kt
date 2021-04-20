package br.com.everis.sovamu.feature.home.ui

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import br.com.everis.sovamu.R

class ScrumFragmentRobots {

    val date = "20/04/2021"
    val datePlus1 = "21/04/2021"
    val dateMinus1 = "19/04/2021"

    val intent = Intent(ApplicationProvider.getApplicationContext(), HomeActivity::class.java)

    fun viewAddDay(): ViewInteraction {
        return Espresso.onView(withId(R.id.addDay))
    }

    fun viewMinusDay(): ViewInteraction {
        return Espresso.onView(withId(R.id.minusDay))
    }

    fun tvDate(): ViewInteraction {
        return Espresso.onView(withId(R.id.tvDate))
    }

    fun addDay(): ViewInteraction {
        return viewAddDay().perform(click())
    }

    fun minusDay(): ViewInteraction {
        return viewMinusDay().perform(click())
    }

    fun verifyDate(date: String): ViewInteraction? {
        return tvDate()
            .check(matches(withText(date)))
    }

}