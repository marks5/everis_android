package everis_android.buildSrc

object Libs {

    object Koin {
        private const val version = "2.0.1"
        const val koinCore = "org.koin:koin-android:$version"
        const val koinExt = "org.koin:koin-androidx-viewmodel:$version"
        const val koinTest = "org.koin:koin-test:$version"
    }

    object Room {
        private const val version = "2.2.6"
        const val roomRunTime = "androidx.room:room-runtime:$version"
        const val roomCompiler = "androidx.room:room-compiler:$version"
    }

    object Retrofit {
        private const val version = "2.6.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$version"
        const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:$version"
        const val retrofitCoroutines = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
    }

    object OkHttp {
        private const val version = "3.12.0"
        const val okHttp = "com.squareup.okhttp3:okhttp:$version"
        const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:4.1.0"
    }

    object Coroutines {
        private const val version = "1.0.1"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    }
}