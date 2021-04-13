package br.com.everis.sovamu.feature.login.di

import br.com.everis.sovamu.feature.login.usecase.LoginUseCase
import br.com.everis.sovamu.feature.login.usecase.LoginUseCaseImpl
import org.koin.dsl.module

val loginUseCaseModule = module {
    factory<LoginUseCase> { LoginUseCaseImpl(loginRepository = get()) }
}