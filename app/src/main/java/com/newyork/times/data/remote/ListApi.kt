package com.newyork.times.data.remote

import com.newyork.times.BuildConfig
import com.newyork.times.model.MostPopularList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ListApi {
    @GET("svc/mostpopular/v2/mostviewed/all-sections/{period}.json")
    suspend fun fetchArticles(
        @Path("period") period: Int = 7, // weekly
        @Query("api-key") apiKey: String = BuildConfig.API_KEY
    ): Response<MostPopularList>
}
