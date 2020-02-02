package io.vinicius.androidcommon.di

import io.vinicius.androidcommon.service.CountriesService
import io.vinicius.androidcommon.service.RestFactory
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import timber.log.Timber

val serviceModule = module {

    // Logging Interceptor
    single {
        val logger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) = Timber.d(message)
        }

        HttpLoggingInterceptor(logger).apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    // CountryService
    single {
        val restFactory = RestFactory(get(), get())
        restFactory.create(CountriesService::class.java)
    }
}