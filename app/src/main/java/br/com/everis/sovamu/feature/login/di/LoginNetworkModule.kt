package br.com.everis.sovamu.feature.login.di

import br.com.everis.sovamu.feature.login.network.EverisApi
import org.koin.dsl.module
import retrofit2.Retrofit

val loginNetworkModule = module {
    factory { get<Retrofit>().create(EverisApi::class.java) }
}
