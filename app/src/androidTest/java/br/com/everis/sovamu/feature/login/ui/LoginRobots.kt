package br.com.everis.sovamu.feature.login.ui

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.everis.sovamu.R
import org.hamcrest.CoreMatchers.not
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginRobots {

    private val EMAIL = "teste@teste.com"
    private val PASSWD = "12345"

    val intent = Intent(ApplicationProvider.getApplicationContext(), LoginActivity::class.java)

    fun edtEmailLogin(): ViewInteraction {
        return onView(withId(R.id.edtEmail))
    }

    fun edtPasswdLogin(): ViewInteraction {
        return onView(withId(R.id.edtPassword))
    }

    fun btnEntrarLogin(): ViewInteraction {
        return onView(withId(R.id.btnEntrar))
    }

    fun insereEmail(text: String) : LoginRobots {
        edtEmailLogin().perform(typeText(text))
        return this
    }

    fun insereSenha(passwd: String) : LoginRobots {
        edtPasswdLogin().perform(typeText(passwd))
        return this
    }

    fun checarEmail(): LoginRobots {
        edtEmailLogin().check(matches(withText(EMAIL)))
        return this
    }

    fun checarPasswd(): LoginRobots {
        edtPasswdLogin().check(matches(withText(PASSWD)))
        return this
    }

    fun checarBtnEntrarDisabled(): LoginRobots {
        btnEntrarLogin().check(matches(not(isEnabled())))
        return this
    }

    fun clicaEntrar(): LoginRobots {
        btnEntrarLogin().perform(click())
        return this
    }

    fun fecharTeclado() : LoginRobots {
        Espresso.closeSoftKeyboard()
        return this
    }
}