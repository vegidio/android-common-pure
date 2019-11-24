package io.vinicius.androidcommon.di

import dagger.Component
import io.vinicius.androidcommon.App
import io.vinicius.androidcommon.viewmodel.CountryViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    ServiceModule::class
])

interface AppComponent
{
    // App
    fun inject(target: App)

    // View Models
    fun inject(target: CountryViewModel)
}