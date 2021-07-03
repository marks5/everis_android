package br.com.everis.sovamu.feature.updateuser.network

import br.com.everis.sovamu.feature.updateuser.model.UpdateUser
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UpdateUserApi {

    @POST("/api/updateuser")
    fun getDataAsync(): Deferred<Response<UpdateUser>>

    @GET("{cep}/json")
    fun getDataCepAsync(@Path("cep") cep: String): Deferred<Response<UpdateUser>>

}
