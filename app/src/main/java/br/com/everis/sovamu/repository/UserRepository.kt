package br.com.everis.sovamu.repository

import br.com.everis.sovamu.functional.Either
import br.com.everis.sovamu.model.UserData

interface UserRepository {
    suspend fun getUserData(): Either<String, UserData>
}