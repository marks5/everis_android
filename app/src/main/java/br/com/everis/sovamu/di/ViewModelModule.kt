package br.com.everis.sovamu.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val viewModelModule = module {
    factory { Dispatchers.Main }
    factory { Dispatchers.IO }
}