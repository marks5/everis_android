package br.com.everis.sovamu.feature.login.di

val loginModule = listOf(
        loginNetworkModule,
        loginRepositoryModule,
        loginUseCaseModule,
        loginViewModelModule
)