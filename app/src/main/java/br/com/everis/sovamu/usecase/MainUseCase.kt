package br.com.everis.sovamu.usecase

import br.com.everis.sovamu.functional.Either
import br.com.everis.sovamu.model.UserData

interface MainUseCase {
    suspend fun execute(): Either<String, UserData>
}