package br.com.everis.sovamu.feature.updateuser.di

import br.com.everis.sovamu.feature.updateuser.usecase.UpdateUserUseCase
import br.com.everis.sovamu.feature.updateuser.usecase.UpdateUserUseCaseImpl
import org.koin.dsl.module

val updateUserUseCaseModule = module {
    factory<UpdateUserUseCase> { UpdateUserUseCaseImpl(updateUserRepository = get() ) }
}

