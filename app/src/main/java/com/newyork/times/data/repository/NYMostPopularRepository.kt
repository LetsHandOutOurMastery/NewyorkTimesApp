package com.newyork.times.data.repository

import androidx.lifecycle.LiveData
import com.newyork.times.data.local.ArticleDao
import com.newyork.times.data.remote.ListApi
import com.newyork.times.model.Article
import com.newyork.times.model.MostPopularList
import com.newyork.times.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NYMostPopularRepository @Inject constructor(
    private val articleDao: ArticleDao,
    private val listApi: ListApi
) : MostPopularRepository {
    override suspend fun fetchArticles(period: Int): Resource<List<Article>> {
        return try {
            withContext(Dispatchers.IO) {
                Resource.loading(null)
                val mostPopularListResponse: Response<MostPopularList> =
                    listApi.fetchArticles(period)
                if (mostPopularListResponse.isSuccessful) {
                    mostPopularListResponse.body()?.let {
                        // if successful response insert into db
                        it.let {
                            articleDao.insertArticleList(it.results)
                        }
                        return@let Resource.success(null)
                    } ?: Resource.error("Some unknown error occurred", null)
                } else {
                    Resource.error("Some unknown error occurred", null)
                }
            }
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }

    override fun observableArticleList(filter: String): LiveData<List<Article>> =
        articleDao.getAllArticles(filter)


}