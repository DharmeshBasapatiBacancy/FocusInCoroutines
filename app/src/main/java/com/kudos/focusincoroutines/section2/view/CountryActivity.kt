package com.kudos.focusincoroutines.section2.view

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kudos.focusincoroutines.databinding.ActivityCountryBinding
import com.kudos.focusincoroutines.section2.viewmodel.CountryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryActivity : AppCompatActivity() {
    private lateinit var countryAdapter: CountryAdapter
    private lateinit var binding: ActivityCountryBinding

    private val countryViewModel: CountryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupList()
        observeCountry()
    }

    private fun observeCountry() {

        countryViewModel.getAllCountries()
        countryViewModel.countriesList.observe(this) {
            Log.d("TAG", "observeCountry: ${it.data}")
            if (it.data?.isNotEmpty()!!) {
                binding.countriesList.visibility = View.VISIBLE
                binding.listError.visibility = View.GONE
                binding.loadingView.visibility = View.GONE
                countryAdapter.submitList(it.data)
            } else {
                binding.countriesList.visibility = View.GONE
                binding.listError.visibility = View.VISIBLE
                binding.loadingView.visibility = View.GONE
            }

        }
    }

    private fun setupList() {
        countryAdapter = CountryAdapter {}
        binding.apply {
            countriesList.apply {
                layoutManager = LinearLayoutManager(this@CountryActivity)
                adapter = countryAdapter
            }
        }
    }
}