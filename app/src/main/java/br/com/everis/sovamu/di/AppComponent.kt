package br.com.everis.sovamu.di

import br.com.everis.sovamu.feature.login.di.loginModule

val appComponent = listOf(
    networkModule,
    viewModelModule
) + loginModule
