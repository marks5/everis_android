package br.com.everis.sovamu.feature.home.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeActivityTest {

    lateinit var scenario: ActivityScenario<HomeActivity>
    lateinit var robots: HomeRobots

    @Before
    fun setup() {
        robots = HomeRobots()
        scenario = launch(robots.intent)
    }

    @Test
    fun navigateScrumFragment() {
        with(robots) {
            clickScrumMenu()
            checkShowScrumView()
        }
    }

    @Test
    fun navigateBlockFragment() {
        with(robots) {
            clickBlockMenu()
            checkShowBlockView()
        }
    }

    @Test
    fun navigateDailyFragment() {
        with(robots) {
            clickDailyMenu()
            checkShowDailyView()
        }
    }

    @Test
    fun scrollScrumRecyclerView() {
        with(robots) {
            clickScrumMenu()
            scrollScrumRecyclerView()
            checkLastItem()
            checkShowScrumView()
        }
    }

    @Test
    fun swipeNotificationView() {
        with(robots) {
            clickScrumMenu()
            checkShowScrumView()
            check_Visible_NotificationView()
            swipeLeftNotification()
            swipeRightNotification()
            swipeLeftNotification()
            check_Gone_NotificationView()
        }
    }

    @After
    fun tearDown() {
        scenario.close()
    }
}