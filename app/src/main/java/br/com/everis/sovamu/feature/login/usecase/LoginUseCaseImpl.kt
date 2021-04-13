package br.com.everis.sovamu.feature.login.usecase

import br.com.everis.sovamu.functional.Either
import br.com.everis.sovamu.feature.login.model.UserData
import br.com.everis.sovamu.feature.login.repository.LoginRepository

class LoginUseCaseImpl(private val loginRepository: LoginRepository) : LoginUseCase {

    override suspend fun execute(): Either<String, UserData> =
       loginRepository.getUserData()
}