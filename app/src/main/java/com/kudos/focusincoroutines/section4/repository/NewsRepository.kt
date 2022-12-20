package com.kudos.focusincoroutines.section4.repository

import com.kudos.focusincoroutines.section4.network.NewsApiService
import com.kudos.focusincoroutines.section4.network.models.NewsResponseItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsApiService: NewsApiService) {

    fun getNewsArticles(): Flow<NewsResponseItem> {
        return flow {
            val newsSource = newsApiService.getNewsArticles()
            newsSource.forEach {
                emit(it)
                delay(3000L)
            }
        }
    }

}