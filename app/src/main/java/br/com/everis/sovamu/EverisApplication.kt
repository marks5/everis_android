package br.com.everis.sovamu

import android.app.Application
import br.com.everis.sovamu.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class EverisApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EverisApplication)
            modules(provideDependency())
        }
    }

    private fun provideDependency() = appComponent
}