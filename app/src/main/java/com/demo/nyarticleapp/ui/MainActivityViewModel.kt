package com.demo.nyarticleapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.nyarticleapp.data.model.ResultsItem
import com.demo.nyarticleapp.data.repository.AppsRepository
import com.demo.nyarticleapp.network.ApiInterface
import com.demo.nyarticleapp.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val appsRepository: AppsRepository,
    private val apiInterface: ApiInterface
) : ViewModel() {

    val progress = SingleLiveEvent<Boolean>()
    val errorMessage = SingleLiveEvent<String?>()
    val articleList = SingleLiveEvent<List<ResultsItem?>?>()

    fun getArticles() {
        if (articleList.value != null) {
            articleList.postValue(articleList.value)
            return
        }
        progress.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = appsRepository.getMostViewedArticles(apiInterface, period = 7)
                when (response.status) {
                    "OK" -> {
                        val remoteData = response.results
                        articleList.postValue(remoteData)
                    }
                    else -> {
                        errorMessage.postValue(null)
                    }
                }
            } catch (e: Exception) {
                errorMessage.postValue(e.message)
                e.printStackTrace()
            } finally {
                progress.postValue(false)
            }
        }
    }
}