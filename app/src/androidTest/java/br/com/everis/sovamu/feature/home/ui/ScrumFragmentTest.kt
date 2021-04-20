package br.com.everis.sovamu.feature.home.ui

import androidx.test.core.app.ActivityScenario
import br.com.everis.sovamu.feature.login.ui.LoginActivity
import br.com.everis.sovamu.utils.DateFormatUtil
import junit.framework.TestCase
import org.junit.Test

class ScrumFragmentTest : TestCase() {

    private lateinit var scenario: ActivityScenario<LoginActivity>
    private lateinit var robots: ScrumFragmentRobots

    public override fun setUp() {
        robots = ScrumFragmentRobots()
    }

    @Test
    fun test() {
        scenario = ActivityScenario.launch(robots.intent)

        val localDate = DateFormatUtil.brTime().toLocalDate()
        val date = DateFormatUtil.getDateFormatted(localDate)
        robots.verifyDate(date)

        val localDatePlus1 = localDate.plusDays(1)
        val datePlus1 = DateFormatUtil.getDateFormatted(localDatePlus1)
        robots.addDay()
        robots.verifyDate(datePlus1)

        val localDateMinus1 = localDate.minusDays(1)
        val dateMinus1 = DateFormatUtil.getDateFormatted(localDateMinus1)
        robots.minusDay()
        robots.verifyDate(dateMinus1)
    }

}