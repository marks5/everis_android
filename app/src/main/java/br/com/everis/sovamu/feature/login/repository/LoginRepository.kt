package br.com.everis.sovamu.feature.login.repository

import br.com.everis.sovamu.functional.Either
import br.com.everis.sovamu.feature.login.model.UserData

interface LoginRepository {
    suspend fun getUserData(): Either<String, UserData>
}