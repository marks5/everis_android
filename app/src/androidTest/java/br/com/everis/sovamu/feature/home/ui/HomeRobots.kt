package br.com.everis.sovamu.feature.home.ui

import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.everis.sovamu.R
import br.com.everis.sovamu.util.recyclerItemAtPosition
import org.hamcrest.Matchers.not
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeRobots {

    val intent = Intent(ApplicationProvider.getApplicationContext(), HomeActivity::class.java)

    fun scrumNavigationView(): ViewInteraction {
        return onView(withId(R.id.scrumFragment))
    }

    fun blockNavigationView(): ViewInteraction {
        return onView(withId(R.id.blockFragment))
    }

    fun dailyNavigationView(): ViewInteraction {
        return onView(withId(R.id.dailyFragment))
    }

    fun scrumRecyclerView(): ViewInteraction {
        return onView(withId(R.id.rv_task))
    }

    fun notificationView(): ViewInteraction {
        return onView(withId(R.id.notification))
    }

    fun clickBlockMenu(): HomeRobots {
        blockNavigationView().perform(click())
        return this
    }

    fun clickDailyMenu(): HomeRobots {
        dailyNavigationView().perform(click())
        return this
    }

    fun clickScrumMenu(): HomeRobots {
        scrumNavigationView().perform(click())
        return this
    }

    fun checkShowBlockView(): HomeRobots {
        blockNavigationView().check(matches(isDisplayed()))
        return this
    }

    fun checkShowScrumView(): HomeRobots {
        scrumNavigationView().check(matches(isDisplayed()))
        return this
    }

    fun checkShowDailyView(): HomeRobots {
        dailyNavigationView().check(matches(isDisplayed()))
        return this
    }

    fun scrollScrumRecyclerView(): HomeRobots {
        scrumRecyclerView().perform(scrollToPosition<ScrumAdapter.ViewHolder>(3))
        return this
    }

    fun checkLastItem(): HomeRobots {
        scrumRecyclerView().check(
                matches(
                        recyclerItemAtPosition(
                                3,
                                hasDescendant(withText("90th minute"))
                        )
                )
        )
        return this
    }

    fun swipeRightNotification(): HomeRobots {
       notificationView().perform(swipeRight())
       return this
    }

    fun swipeLeftNotification(): HomeRobots {
        notificationView().perform(swipeLeft())
        return this
    }

    fun check_Gone_NotificationView(): HomeRobots {
        notificationView().check(matches(not(isDisplayed())))
        return this
    }

    fun check_Visible_NotificationView(): HomeRobots {
        notificationView().check(matches(isDisplayed()))
        return this
    }

    fun closeKeyboard(): HomeRobots {
        Espresso.closeSoftKeyboard()
        return this
    }

}