package com.kudos.focusincoroutines.section2.repository

import com.kudos.focusincoroutines.section2.network.ApiService
import com.kudos.focusincoroutines.section2.network.models.GetCountriesResponse
import com.kudos.focusincoroutines.section2.network.models.GetCountriesResponseItem
import javax.inject.Inject

class CountryRepository @Inject constructor(
    private val apiService: ApiService,
) {

    suspend fun getAllCountries(): List<GetCountriesResponseItem> {
        return apiService.getAllCountries()
    }

}