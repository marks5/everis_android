package br.com.everis.sovamu.di

import br.com.everis.sovamu.repository.UserRepository
import br.com.everis.sovamu.repository.UserRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<UserRepository> { UserRepositoryImpl(get()) }
}