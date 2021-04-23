package br.com.everis.sovamu.di

import br.com.everis.sovamu.feature.home.di.homeModule
import br.com.everis.sovamu.feature.login.di.loginModule

val appComponent = listOf(
    networkModule,
    viewModelModule
) + loginModule + homeModule
