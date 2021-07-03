package br.com.everis.sovamu.feature.updateuser.di

import br.com.everis.sovamu.feature.updateuser.network.UpdateUserApi
import org.koin.dsl.module
import retrofit2.Retrofit

val üpdateUserNetworkModule = module {
    factory{get<Retrofit>().create(UpdateUserApi::class.java)}
}

