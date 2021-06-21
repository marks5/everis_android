package br.com.everis.sovamu.feature.updateuser.repository

import br.com.everis.sovamu.feature.updateuser.model.UpdateUser
import br.com.everis.sovamu.functional.Either

interface UpdateUserRepository {
    suspend fun putUpdateUser(): Either<String, UpdateUser>
    suspend fun getEnderecoUser(cep: String): Either<String, UpdateUser>
}