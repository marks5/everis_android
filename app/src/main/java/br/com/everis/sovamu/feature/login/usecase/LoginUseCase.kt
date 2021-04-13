package br.com.everis.sovamu.feature.login.usecase

import br.com.everis.sovamu.functional.Either
import br.com.everis.sovamu.feature.login.model.UserData

interface LoginUseCase {
    suspend fun execute(): Either<String, UserData>
}