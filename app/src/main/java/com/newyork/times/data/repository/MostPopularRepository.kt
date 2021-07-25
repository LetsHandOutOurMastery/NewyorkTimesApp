package com.newyork.times.data.repository

import androidx.lifecycle.LiveData
import com.newyork.times.model.Article
import com.newyork.times.utils.Resource

interface MostPopularRepository {
    suspend fun fetchArticles(period: Int): Resource<List<Article>>

    fun observableArticleList(filter:String) : LiveData<List<Article>>
}