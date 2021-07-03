package br.com.everis.sovamu.feature.updateuser.repository

import br.com.everis.sovamu.feature.login.repository.BaseRepository
import br.com.everis.sovamu.feature.updateuser.model.UpdateUser
import br.com.everis.sovamu.feature.updateuser.network.UpdateUserApi
import br.com.everis.sovamu.functional.Either

class UpdateUserRepositoryImpl(private val updateUserApi: UpdateUserApi):
    BaseRepository(),UpdateUserRepository {

    override suspend fun putUpdateUser(): Either<String, UpdateUser> =
        safeApiCall(
            call = { updateUserApi.getDataAsync().await() },
            errorMessage = "Error when call api"
        )

    override suspend fun getEnderecoUser(cep:String): Either<String, UpdateUser> =
        safeApiCall(
            call = { updateUserApi.getDataCepAsync(cep).await() },
            errorMessage = "Error when call api"
        )
}