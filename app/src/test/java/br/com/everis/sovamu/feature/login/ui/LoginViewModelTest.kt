package br.com.everis.sovamu.feature.login.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.everis.sovamu.feature.login.model.UserData
import br.com.everis.sovamu.feature.login.usecase.LoginUseCase
import br.com.everis.sovamu.functional.Either
import com.nhaarman.mockitokotlin2.atLeast
import com.nhaarman.mockitokotlin2.verify
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.Dispatchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.isA
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class LoginViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mDispatcher = Dispatchers.Unconfined
    private lateinit var viewModel: LoginViewModel
    private val useCase: LoginUseCase = mockk()

    @Mock
    lateinit var observer: Observer<LoginViewAction>

    @Mock
    lateinit var observerForm: Observer<Boolean>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = LoginViewModel(useCase, mDispatcher, mDispatcher)
        viewModel.actionView.observeForever(observer)
    }

    @Test
    fun `check state success - getUserData`() {
        val response = UserData("", "", "")
        val responseOk: Either<String, UserData> = Either.Success(response)

        coEvery { useCase.execute() } returns responseOk

        viewModel.getUserData()

        assertNotNull(viewModel.actionView.value)
        verify(observer, atLeast(2)).onChanged(isA(LoginViewAction.Loading::class.java))
        verify(observer).onChanged(isA(LoginViewAction.Success::class.java))
        coVerify { useCase.execute() }
    }

    @Test
    fun `check state error - getUserData`() {
        val responseNok: Either<String, UserData> = Either.Error("")

        coEvery { useCase.execute() } returns responseNok

        viewModel.getUserData()

        assertNotNull(viewModel.actionView.value)
        verify(observer, atLeast(2)).onChanged(isA(LoginViewAction.Loading::class.java))
        verify(observer).onChanged(isA(LoginViewAction.Error::class.java))
        coVerify { useCase.execute() }
    }

    @Test
    fun `check state of e-mail inserted`() {
        val email = "teste@teste.com"
        viewModel.setEmail(email)

        assertEquals("teste@teste.com", viewModel.email.value)
    }

    @Test
    fun `check state of password inserted`() {
        val pass = "12345"
        viewModel.setPassword(pass)

        assertEquals("12345", viewModel.senha.value)
    }

    @Test
    fun `check state of error`() {
        val error = "Wrong API call"
        viewModel.setMsgError(error)

        assertEquals("Wrong API call", viewModel.msgError.value)
    }

    @Test
    fun `check state valid form with valid e-mail and passw`() {
        val pass = "12345"
        val email = "teste@teste.com"
        viewModel.setPassword(pass)
        viewModel.setEmail(email)

        viewModel.isValid.observeForever(observerForm)

        assertEquals(true, viewModel.isValid.value)
    }

    @Test
    fun `check state invalid form with invalid e-mail and empty passw`() {
        val pass = ""
        val email = "teste@teste,com"
        viewModel.setPassword(pass)
        viewModel.setEmail(email)

        viewModel.isValid.observeForever(observerForm)

        assertEquals(false, viewModel.isValid.value)
    }
}