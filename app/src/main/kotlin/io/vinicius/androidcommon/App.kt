package io.vinicius.androidcommon

import android.app.Application
import io.vinicius.androidcommon.di.AppComponent
import io.vinicius.androidcommon.di.AppModule
import io.vinicius.androidcommon.di.DaggerAppComponent
import timber.log.Timber

class App : Application()
{
    companion object {
        @JvmStatic lateinit var component: AppComponent
    }

    override fun onCreate()
    {
        super.onCreate()

        // Timber logging
        setupTimber()

        // Dependency injection
        setupDagger()
    }

    //region Private Methods
    private fun setupTimber()
    {
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupDagger()
    {
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

        component.inject(this)
    }
    //endregion
}