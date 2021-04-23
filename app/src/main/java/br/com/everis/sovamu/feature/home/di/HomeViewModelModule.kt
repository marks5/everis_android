package br.com.everis.sovamu.feature.home.di

import br.com.everis.sovamu.feature.home.ui.ScrumViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeViewModelModule = module {
    viewModel {
        ScrumViewModel(
                mainDispatcher = get(),
                ioDispatcher = get(),
        )
    }
}