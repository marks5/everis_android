package br.com.everis.sovamu.feature.login.di

import br.com.everis.sovamu.feature.login.repository.LoginRepository
import br.com.everis.sovamu.feature.login.repository.LoginRepositoryImpl
import org.koin.dsl.module

val loginRepositoryModule = module {
    factory<LoginRepository> { LoginRepositoryImpl(get()) }
}