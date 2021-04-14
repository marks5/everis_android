package everis_android.buildSrc

object Libs {

    object Koin {
        private const val version = "2.2.2"
        const val koinCore = "org.koin:koin-android:$version"
        const val koinExt = "org.koin:koin-androidx-viewmodel:$version"
        const val koinTest = "org.koin:koin-test:$version"
        const val pluginKoin = "org.koin:koin-gradle-plugin:$version"
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
        private const val version = "4.9.0"
        const val okHttp = "com.squareup.okhttp3:okhttp:$version"
        const val okHttpInterceptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Log {
        private const val version = "4.7.1"
        const val timber = "com.jakewharton.timber:timber:$version"
    }

    object Kotlin {
        private const val version = "1.0.1"
        private const val kotlinVersion = "1.4.31"
        const val pluginKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
        const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
    }

    object Gson {
        private const val version = "2.8.5"
        const val googleGson = "com.google.code.gson:gson:$version"
    }

    object Test {
        private const val junitVersion = "4.13.2"
        private const val jUnitExtVersion = "1.1.2"
        private const val espressoVersion = "3.3.0"
        private const val mockkVersion = "1.9.3"
        private const val mockitoVersion = "2.2.0"

        const val jUnit = "junit:junit:$junitVersion"
        const val jUnitExt = "androidx.test.ext:junit:$jUnitExtVersion"
        const val espresso = "androidx.test.espresso:espresso-core:$espressoVersion"
        const val mockk = "io.mockk:mockk:$mockkVersion"
        const val mockito = "com.nhaarman.mockitokotlin2:mockito-kotlin:$mockitoVersion"
        const val androidCore = "androidx.arch.core:core-testing:2.1.0"
    }

    object Material {
        private const val version = "1.3.0"
        const val materialDesign = "com.google.android.material:material:$version"
    }

    object Androidx {
        const val androidxCore = "androidx.core:core-ktx:1.3.2"
        const val appCompat = "androidx.appcompat:appcompat:1.2.0"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:2.0.4"
    }

}