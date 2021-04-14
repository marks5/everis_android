package br.com.everis.sovamu

import android.app.Application
import br.com.everis.sovamu.di.appComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class EverisApplication : Application() {

    private val everisTag = "EVERIS_APP_ANDROID"

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EverisApplication)
            modules(provideDependency())
        }
        Timber.tag(everisTag)
    }

    private fun provideDependency() = appComponent
}