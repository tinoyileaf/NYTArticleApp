package com.demo.nyarticleapp.data.repository

import com.demo.nyarticleapp.data.model.ArticleResponse
import com.demo.nyarticleapp.network.ApiInterface
import com.demo.nyarticleapp.utils.Constants

class AppsRepository {

    suspend fun getMostViewedArticles(
        apiInterface: ApiInterface, period: Int
    ): ArticleResponse {
        return apiInterface.getMostViewedArticles(period = period, key = Constants.API_KEY)
    }
}