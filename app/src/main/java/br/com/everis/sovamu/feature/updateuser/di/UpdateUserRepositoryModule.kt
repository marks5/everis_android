package br.com.everis.sovamu.feature.updateuser.di

import br.com.everis.sovamu.feature.updateuser.repository.UpdateUserRepository
import br.com.everis.sovamu.feature.updateuser.repository.UpdateUserRepositoryImpl
import org.koin.dsl.module

val updateUserRepositoryModule = module {
    factory<UpdateUserRepository> { UpdateUserRepositoryImpl(get()) }
}

