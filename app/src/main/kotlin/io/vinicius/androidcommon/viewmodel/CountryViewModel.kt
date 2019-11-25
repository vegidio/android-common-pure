package io.vinicius.androidcommon.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import io.vinicius.androidcommon.App
import io.vinicius.androidcommon.constant.NetworkState
import io.vinicius.androidcommon.service.CountriesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CountryViewModel : ViewModel()
{
    @Inject
    lateinit var service: CountriesService

    val state = MutableLiveData<NetworkState>()

    init { App.component.inject(this) }

    fun getCountryByCode(code: String) = liveData {
        state.value = NetworkState.LOADING

        try {
            val country = withContext(Dispatchers.IO) {
                return@withContext service.getByCountryCode(code)
            }

            emit(Result.success(country))
            state.value = NetworkState.IDLE

        } catch (e: Throwable) {
            emit(Result.failure(e))
            state.value = NetworkState.ERROR
        }
    }
}