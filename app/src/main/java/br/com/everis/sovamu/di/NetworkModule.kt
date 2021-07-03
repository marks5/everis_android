package br.com.everis.sovamu.di

import br.com.everis.sovamu.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val URL_BASE = "https://viacep.com.br/ws/"

val networkModule = module {

    single {

        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient().newBuilder().addInterceptor(loggingInterceptor).build()

        Retrofit.Builder()
            .client(client)
            //.baseUrl(BuildConfig.SERVER_URL)
            .baseUrl(URL_BASE)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}