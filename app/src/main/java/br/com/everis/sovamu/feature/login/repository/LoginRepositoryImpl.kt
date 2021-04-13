package br.com.everis.sovamu.feature.login.repository

import br.com.everis.sovamu.functional.Either
import br.com.everis.sovamu.feature.login.model.UserData
import br.com.everis.sovamu.feature.login.network.EverisApi
import br.com.everis.sovamu.repository.BaseRepository

class LoginRepositoryImpl(private val everisApi: EverisApi) :
    BaseRepository(), LoginRepository {

    override suspend fun getUserData(): Either<String, UserData> =
        safeApiCall(
            call = { everisApi.getDataAsync().await() },
            errorMessage = "Error when call api"
        )
}