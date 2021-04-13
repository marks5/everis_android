package br.com.everis.sovamu.feature.login.di

import br.com.everis.sovamu.feature.login.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loginViewModelModule = module {
    viewModel {
        LoginViewModel(
                mainDispatcher = get(),
                ioDispatcher = get(),
                useCase = get()
        )
    }
}