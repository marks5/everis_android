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
}