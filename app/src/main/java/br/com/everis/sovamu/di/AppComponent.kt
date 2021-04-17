package br.com.everis.sovamu.di

import br.com.everis.sovamu.feature.home.di.scrumModule
import br.com.everis.sovamu.feature.login.di.loginModule

val appComponent = listOf(
    networkModule,
    viewModelModule
) + loginModule + scrumModule
