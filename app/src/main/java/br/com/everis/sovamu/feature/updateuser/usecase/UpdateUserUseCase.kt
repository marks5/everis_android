package br.com.everis.sovamu.feature.updateuser.usecase

import br.com.everis.sovamu.feature.updateuser.model.UpdateUser
import br.com.everis.sovamu.functional.Either

interface UpdateUserUseCase {
    suspend fun execute(): Either<String, UpdateUser>
    suspend fun executeEnderecoUser(cep: String): Either<String, UpdateUser>
}