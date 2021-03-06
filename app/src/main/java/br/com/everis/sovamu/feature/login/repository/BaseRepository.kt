package br.com.everis.sovamu.feature.login.repository

import br.com.everis.sovamu.functional.Either
import retrofit2.Response

open class BaseRepository {
    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>, errorMessage: String):
            Either<String, T> {
        val response = call.invoke()
        if (response.isSuccessful) return Either.Success(response.body()!!)

        return Either.Error(errorMessage)
    }
}