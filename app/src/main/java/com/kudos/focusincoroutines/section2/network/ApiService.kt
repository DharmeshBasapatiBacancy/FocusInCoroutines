package com.kudos.focusincoroutines.section2.network

import com.kudos.focusincoroutines.section2.network.models.GetCountriesResponse
import com.kudos.focusincoroutines.section2.network.models.GetCountriesResponseItem
import retrofit2.http.GET

interface ApiService {

    @GET("DevTides/countries/master/countriesV2.json")
    suspend fun getAllCountries(): List<GetCountriesResponseItem>

}