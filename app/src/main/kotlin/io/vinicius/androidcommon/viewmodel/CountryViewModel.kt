package io.vinicius.androidcommon.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.vinicius.androidcommon.App
import io.vinicius.androidcommon.model.Country
import io.vinicius.androidcommon.service.CountriesService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CountryViewModel : ViewModel()
{
    @Inject
    lateinit var service: CountriesService

    val country = MutableLiveData<Country>()

    init { App.component.inject(this) }

    suspend fun getCountryByCode(code: String)
    {
        country.value = withContext(Dispatchers.IO) {
            return@withContext service.getByCountryCode(code)
        }
    }
}