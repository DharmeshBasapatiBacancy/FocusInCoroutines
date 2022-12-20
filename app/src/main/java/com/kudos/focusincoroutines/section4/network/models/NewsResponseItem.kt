package com.kudos.focusincoroutines.section4.network.models

data class NewsResponseItem(
    val author: String,
    val desription: String,
    val imageUrl: String,
    val publishedAt: String,
    val title: String,
    val url: String
)