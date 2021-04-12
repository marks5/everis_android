package br.com.everis.sovamu.repository

import br.com.everis.sovamu.functional.Either
import br.com.everis.sovamu.model.UserData
import br.com.everis.sovamu.network.EverisApi

class UserRepositoryImpl(private val everisApi: EverisApi) :
    BaseRepository(), UserRepository {

    override suspend fun getUserData(): Either<String, UserData> =
        safeApiCall(
            call = { everisApi.getDataAsync().await() },
            errorMessage = "Error when call api"
        )
}