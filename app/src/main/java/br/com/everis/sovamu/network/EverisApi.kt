package br.com.everis.sovamu.network

import br.com.everis.sovamu.model.UserData
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface EverisApi {

    @GET("/api/loguser")
    fun getDataAsync(): Deferred<Response<UserData>>
}