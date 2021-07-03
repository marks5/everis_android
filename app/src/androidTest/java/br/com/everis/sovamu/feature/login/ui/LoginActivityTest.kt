package br.com.everis.sovamu.feature.login.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test

import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginActivityTest {

    lateinit var scenario: ActivityScenario<LoginActivity>
    lateinit var robots: LoginRobots

    @Before
    fun setUp() {
        robots = LoginRobots()
    }

    @Test
    fun loginOk() {
        scenario = launch(robots.intent)

        robots.insereEmail("teste@teste.com")
            .insereSenha("12345")
            .fecharTeclado()

        robots.checarEmail().checarPasswd()

        robots.clicaEntrar()
    }

    @Test
    fun loginError() {
        scenario = launch(robots.intent)

        robots.insereEmail("teste@teste,com")
            .insereSenha("12345")
            .fecharTeclado()

        robots.checarBtnEntrarDisabled()
    }

    @Test
    fun loginEmptyPasswd() {
        scenario = launch(robots.intent)

        robots.insereEmail("teste@teste.com")
            .insereSenha("")
            .fecharTeclado()

        robots.checarBtnEntrarDisabled()
    }

    @Test
    fun loginEmptyPasswdAndErrorEmail() {
        scenario = launch(robots.intent)

        robots.insereEmail("teste@teste.com")
            .insereSenha("")
            .fecharTeclado()

        robots.checarBtnEntrarDisabled()
    }

    @Test
    fun showDialogSubmitPasswordEmail() {
        scenario = launch(robots.intent)
        robots.clickEsqueciMinhaSenha()

        robots.insereEmailEsqueciMinhaSenha("teste@teste.com.br")
            .fecharTeclado()

        robots.clicaSetPositiveButtonDialog()
    }

    @Test
    fun showDialogCancelSubmitEmail() {
        scenario = launch(robots.intent)
        robots.clickEsqueciMinhaSenha()

        robots.insereEmailEsqueciMinhaSenha("teste@teste.com.br")
                .fecharTeclado()

        robots.clicaSetNegativeButtonDialog()
    }


    @After
    fun tearDown() {
        scenario.close()
    }


}