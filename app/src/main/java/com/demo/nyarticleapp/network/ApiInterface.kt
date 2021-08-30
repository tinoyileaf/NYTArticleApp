package com.demo.nyarticleapp.network

import com.demo.nyarticleapp.data.model.ArticleResponse
import com.demo.nyarticleapp.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET(Constants.API_MOST_POPULAR)
    suspend fun getMostViewedArticles(@Path("period") period: Int,
                                      @Query("api-key") key: String): ArticleResponse
}