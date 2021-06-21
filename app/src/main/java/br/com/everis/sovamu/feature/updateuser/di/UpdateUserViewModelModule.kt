package br.com.everis.sovamu.feature.updateuser.di

import br.com.everis.sovamu.feature.updateuser.ui.UpdateUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val updateUserViewModelModule = module {
    viewModel {
        UpdateUserViewModel(
            mainDispatcher = get(),
            ioDispatcher = get(),
            useCase = get()
        )
    }
}