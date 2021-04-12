package br.com.everis.sovamu.di

import br.com.everis.sovamu.usecase.MainUseCase
import br.com.everis.sovamu.usecase.MainUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<MainUseCase> { MainUseCaseImpl(userRepository = get()) }
}