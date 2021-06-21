package br.com.everis.sovamu.feature.updateuser.usecase

import br.com.everis.sovamu.feature.updateuser.model.UpdateUser
import br.com.everis.sovamu.feature.updateuser.repository.UpdateUserRepository
import br.com.everis.sovamu.functional.Either

class UpdateUserUseCaseImpl(private val updateUserRepository: UpdateUserRepository): UpdateUserUseCase {

    override suspend fun execute(): Either<String, UpdateUser> =
        updateUserRepository.putUpdateUser()

    override suspend fun executeEnderecoUser(cep: String): Either<String, UpdateUser> =
        updateUserRepository.getEnderecoUser(cep)

}