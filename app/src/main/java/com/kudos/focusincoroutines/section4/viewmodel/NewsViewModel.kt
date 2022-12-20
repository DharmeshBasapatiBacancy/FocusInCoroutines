package com.kudos.focusincoroutines.section4.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.kudos.focusincoroutines.section4.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(newsRepository: NewsRepository) : ViewModel() {

    val newsArticles = newsRepository.getNewsArticles().asLiveData()

}