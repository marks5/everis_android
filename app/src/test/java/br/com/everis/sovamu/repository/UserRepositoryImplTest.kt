package br.com.everis.sovamu.repository

import br.com.everis.sovamu.model.UserData
import br.com.everis.sovamu.network.EverisApi
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Response

@RunWith(JUnit4::class)
class UserRepositoryImplTest {

    private lateinit var repository: UserRepositoryImpl
    private val api = mockk<EverisApi>()
    private val mockResponse = mockk<Response<UserData>>()

    @Before
    fun setup() {
        repository = UserRepositoryImpl(api)
    }

    @Test
    fun `check success - getUserData`() = runBlocking {
        every { mockResponse.isSuccessful } returns true
        every { mockResponse.body() } returns UserData("", "", "")

        coEvery { api.getDataAsync().await() } returns mockResponse

        val dataReceived = repository.getUserData()

        assertNotNull(dataReceived)
        assert(dataReceived.isSuccess)
    }

    @Test
    fun `check error - getUserData`() = runBlocking {
        every { mockResponse.isSuccessful } returns false
        every { mockResponse.body() } returns UserData("", "", "")

        coEvery { api.getDataAsync().await() } returns mockResponse

        val dataReceived = repository.getUserData()

        assertNotNull(dataReceived)
        assert(dataReceived.isError)
    }

}