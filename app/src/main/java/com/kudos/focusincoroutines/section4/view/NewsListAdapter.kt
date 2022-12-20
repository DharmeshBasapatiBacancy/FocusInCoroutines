package com.kudos.focusincoroutines.section4.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.kudos.focusincoroutines.databinding.RowItemNewsBinding
import com.kudos.focusincoroutines.section4.network.models.NewsResponseItem

class NewsListAdapter(private val onItemClick: (NewsResponseItem) -> Unit) :
    ListAdapter<NewsResponseItem, NewsListAdapter.ViewHolder>(callback) {

    companion object {
        val callback = object : DiffUtil.ItemCallback<NewsResponseItem>() {
            override fun areItemsTheSame(
                oldItem: NewsResponseItem,
                newItem: NewsResponseItem
            ) =
                oldItem.url == newItem.url

            override fun areContentsTheSame(
                oldItem: NewsResponseItem,
                newItem: NewsResponseItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    inner class ViewHolder(val rowItemNewsBinding: RowItemNewsBinding) :
        RecyclerView.ViewHolder(rowItemNewsBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        RowItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            rowItemNewsBinding.apply {
                val item = getItem(position)
                newsTitleTextView.text = item.title
                newsAuthorTextView.text = item.author
                newsImageView.load(item.imageUrl)
                newsDateTextView.text = item.publishedAt
                itemView.setOnClickListener {
                    onItemClick(item)
                }

            }
        }
    }

}