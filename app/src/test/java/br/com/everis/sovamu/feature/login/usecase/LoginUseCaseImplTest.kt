package br.com.everis.sovamu.feature.login.usecase

import br.com.everis.sovamu.feature.login.model.UserData
import br.com.everis.sovamu.feature.login.repository.LoginRepositoryImpl
import br.com.everis.sovamu.feature.login.usecase.LoginUseCaseImpl
import br.com.everis.sovamu.functional.Either

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LoginUseCaseImplTest {

    private lateinit var useCase: LoginUseCaseImpl
    private val repository = mockk<LoginRepositoryImpl>()

    @Before
    fun setup() {
        useCase = LoginUseCaseImpl(repository)
    }

    @Test
    fun `check success - execute`() = runBlocking {
        val response = UserData("", "", "")
        val responseOk = Either.Success(response)

        coEvery { repository.getUserData() } returns responseOk

        useCase.execute()

        coVerify { repository.getUserData() }
        assert(repository.getUserData().isSuccess)
    }

    @Test
    fun `check error - execute`() = runBlocking {
        val responseNok = Either.Error("")

        coEvery { repository.getUserData() } returns responseNok

        useCase.execute()

        coVerify { repository.getUserData() }
        assert(repository.getUserData().isError)
    }
}