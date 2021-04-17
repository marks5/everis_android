package br.com.everis.sovamu

import android.app.Application
import br.com.everis.sovamu.di.appComponent
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber
import timber.log.Timber.DebugTree

class EverisApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@EverisApplication)
            modules(provideDependency())
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        AndroidThreeTen.init(this);
    }

    private fun provideDependency() = appComponent
}