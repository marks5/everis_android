package br.com.everis.sovamu.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import br.com.everis.sovamu.functional.Either
import br.com.everis.sovamu.model.UserData
import br.com.everis.sovamu.usecase.MainUseCase
import com.nhaarman.mockitokotlin2.any
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
class MainViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mDispatcher = Dispatchers.Unconfined
    private lateinit var viewModel: MainViewModel
    private val useCase: MainUseCase = mockk()

    @Mock
    lateinit var observer: Observer<MainViewAction>

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        viewModel = MainViewModel(useCase, mDispatcher, mDispatcher)
        viewModel.actionView.observeForever(observer)
    }

    @Test
    fun `check state success - getUserData`() {
        val response = UserData("", "", "")
        val responseOk: Either<String, UserData> = Either.Success(response)

        coEvery { useCase.execute() } returns responseOk

        viewModel.getUserData()

        assertNotNull(viewModel.actionView.value)
        verify(observer, atLeast(2)).onChanged(isA(MainViewAction.Loading::class.java))
        verify(observer).onChanged(isA(MainViewAction.Success::class.java))
        coVerify { useCase.execute() }
    }

    @Test
    fun `check state error - getUserData`() {
        val responseNok: Either<String, UserData> = Either.Error("")

        coEvery { useCase.execute() } returns responseNok

        viewModel.getUserData()

        assertNotNull(viewModel.actionView.value)
        verify(observer, atLeast(2)).onChanged(isA(MainViewAction.Loading::class.java))
        verify(observer).onChanged(isA(MainViewAction.Error::class.java))
        coVerify { useCase.execute() }
    }
}