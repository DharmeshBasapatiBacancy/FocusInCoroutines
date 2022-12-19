package com.kudos.focusincoroutines.section2.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kudos.focusincoroutines.databinding.RowItemCountryBinding
import com.kudos.focusincoroutines.section2.network.models.GetCountriesResponseItem

class CountryAdapter(private val onItemClick: (GetCountriesResponseItem) -> Unit) :
    ListAdapter<GetCountriesResponseItem, CountryAdapter.ViewHolder>(callback) {

    companion object {
        val callback = object : DiffUtil.ItemCallback<GetCountriesResponseItem>() {
            override fun areItemsTheSame(
                oldItem: GetCountriesResponseItem,
                newItem: GetCountriesResponseItem
            ) =
                oldItem.alpha2Code == newItem.alpha2Code

            override fun areContentsTheSame(
                oldItem: GetCountriesResponseItem,
                newItem: GetCountriesResponseItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(val rowItemCountryBinding: RowItemCountryBinding) :
        RecyclerView.ViewHolder(rowItemCountryBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        RowItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            rowItemCountryBinding.apply {
                val item = getItem(position)
                name.text = item.name
                capital.text = item.capital
                imageView.load(item.flagPNG)
                itemView.setOnClickListener {
                    onItemClick(item)
                }

            }
        }
    }

}