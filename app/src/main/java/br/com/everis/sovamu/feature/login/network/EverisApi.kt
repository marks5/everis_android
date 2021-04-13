package br.com.everis.sovamu.feature.login.network

import br.com.everis.sovamu.feature.login.model.UserData
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

interface EverisApi {

    @GET("/api/loguser")
    fun getDataAsync(): Deferred<Response<UserData>>
}