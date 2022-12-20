package com.kudos.focusincoroutines.section4.view

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kudos.focusincoroutines.databinding.ActivityNewsListBinding
import com.kudos.focusincoroutines.section4.network.models.NewsResponseItem
import com.kudos.focusincoroutines.section4.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsListActivity : AppCompatActivity() {
    private lateinit var newsArticlesList: MutableList<NewsResponseItem>
    private lateinit var newsListAdapter: NewsListAdapter
    private lateinit var binding: ActivityNewsListBinding

    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {

        }
        setupNewsList()
        observeNewsList()
    }

    private fun observeNewsList() {
        newsArticlesList = mutableListOf<NewsResponseItem>()
        newsViewModel.newsArticles.observe(this) {
            binding.apply {
                loadingView.visibility = View.GONE
                newsList.visibility = View.VISIBLE
                newsArticlesList.add(0, it)
                newsListAdapter.submitList(newsArticlesList)
                newsListAdapter.notifyItemInserted(0)
                newsList.smoothScrollToPosition(0)
            }
        }
    }

    private fun setupNewsList() {
        newsListAdapter = NewsListAdapter {}
        binding.newsList.apply {
            layoutManager = LinearLayoutManager(this@NewsListActivity)
            adapter = newsListAdapter
        }
    }
}