package com.kudos.focusincoroutines.section4.network

import com.kudos.focusincoroutines.section4.network.models.NewsResponse
import retrofit2.http.GET


interface NewsApiService {

    @GET("news.json")
    suspend fun getNewsArticles(): NewsResponse

}