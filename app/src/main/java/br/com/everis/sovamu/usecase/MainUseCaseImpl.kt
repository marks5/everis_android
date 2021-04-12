package br.com.everis.sovamu.usecase

import br.com.everis.sovamu.functional.Either
import br.com.everis.sovamu.model.UserData
import br.com.everis.sovamu.repository.UserRepository

class MainUseCaseImpl(private val userRepository: UserRepository) : MainUseCase {

    override suspend fun execute(): Either<String, UserData> =
        userRepository.getUserData()
}