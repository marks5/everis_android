package br.com.everis.sovamu.usecase

import br.com.everis.sovamu.functional.Either
import br.com.everis.sovamu.model.UserData
import br.com.everis.sovamu.repository.UserRepositoryImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainUseCaseImplTest {

    private lateinit var useCase: MainUseCaseImpl
    private val repository = mockk<UserRepositoryImpl>()

    @Before
    fun setup() {
        useCase = MainUseCaseImpl(repository)
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