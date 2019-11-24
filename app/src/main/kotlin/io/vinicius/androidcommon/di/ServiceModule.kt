package io.vinicius.androidcommon.di

import android.content.Context
import dagger.Module
import dagger.Provides
import io.vinicius.androidcommon.service.CountriesService
import io.vinicius.androidcommon.service.RestFactory
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import javax.inject.Singleton

@Module
class ServiceModule
{
    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor
    {
        val logger = object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) = Timber.d(message)
        }

        return HttpLoggingInterceptor(logger).apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
    }

    @Provides
    @Singleton
    fun provideRestFactory(context: Context, loggingInterceptor: HttpLoggingInterceptor)
        = RestFactory(context, loggingInterceptor)

    @Provides
    @Singleton
    fun provideCountryService(rf: RestFactory) = rf.create(CountriesService::class.java)
}