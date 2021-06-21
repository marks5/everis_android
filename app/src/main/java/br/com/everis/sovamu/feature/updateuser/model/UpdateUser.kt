package br.com.everis.sovamu.feature.updateuser.model

import com.google.gson.annotations.SerializedName

data class UpdateUser(
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    val passwordNew: String,
    val confirmation: String,

    @SerializedName("zipCode")
    val cep: String,
    @SerializedName("publicPlace")
    val logradouro: String,
    @SerializedName("complement")
    val complemento: String,
    @SerializedName("district")
    val bairro: String,
    @SerializedName("locality")
    val localidade: String,
    val uf: String
)