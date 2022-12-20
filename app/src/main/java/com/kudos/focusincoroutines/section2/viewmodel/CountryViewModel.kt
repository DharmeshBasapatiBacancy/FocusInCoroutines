package com.kudos.focusincoroutines.section2.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudos.focusincoroutines.section2.network.models.Country
import com.kudos.focusincoroutines.section2.network.models.GetCountriesResponseItem
import com.kudos.focusincoroutines.section2.repository.CountryRepository
import com.kudos.focusincoroutines.section2.util.ApiResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryViewModel @Inject constructor(private val countryRepository: CountryRepository) :
    ViewModel() {

    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true

        val dummyData = generateDummyCountries()

        countries.value = dummyData
        countryLoadError.value = ""
        loading.value = false
    }

    private fun generateDummyCountries(): List<Country> {
        val countries = arrayListOf<Country>()
        countries.add(Country("dummyCountry1", "dummyCapital1", ""))
        countries.add(Country("dummyCountry2", "dummyCapital2", ""))
        countries.add(Country("dummyCountry3", "dummyCapital3", ""))
        countries.add(Country("dummyCountry4", "dummyCapital4", ""))
        countries.add(Country("dummyCountry5", "dummyCapital5", ""))
        return countries
    }

    private fun onError(message: String) {
        countryLoadError.value = message
        loading.value = false
    }

    private val _getCountriesList =
        MutableLiveData<ApiResponse<List<GetCountriesResponseItem>?>>()
    val countriesList: LiveData<ApiResponse<List<GetCountriesResponseItem>?>> =
        _getCountriesList

    fun getAllCountries() {
        viewModelScope.launch {
            val response = countryRepository.getAllCountries()
            _getCountriesList.value = ApiResponse.Success(response)
        }
    }

}