package io.vinicius.androidcommon.service

import io.vinicius.androidcommon.model.Country
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesService
{
    companion object {
        internal const val baseUrl = "https://restcountries.eu/rest/v2/"
    }

    @GET("alpha/{countryCode}")
    suspend fun getByCountryCode(
        @Path("countryCode") countryCode: String
    ): Country
}