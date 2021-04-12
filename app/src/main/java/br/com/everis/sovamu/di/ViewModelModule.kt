package br.com.everis.sovamu.di

import br.com.everis.sovamu.ui.MainViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    factory { Dispatchers.Main }
    factory { Dispatchers.IO }

    viewModel {
        MainViewModel(
            mainDispatcher = get(),
            ioDispatcher = get(),
            useCase = get()
        )
    }
}