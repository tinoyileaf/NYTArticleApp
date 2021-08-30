package com.demo.nyarticleapp.ui

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.demo.nyarticleapp.data.model.ArticleResponse
import com.demo.nyarticleapp.data.model.ResultsItem
import com.demo.nyarticleapp.data.repository.AppsRepository
import com.demo.nyarticleapp.di.appModule
import com.demo.nyarticleapp.di.viewModelModule
import com.demo.nyarticleapp.network.ApiInterface
import com.demo.nyarticleapp.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExperimentalCoroutinesApi
class MainActivityViewModelUnitTest : KoinTest {

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var context: Application

    @Mock
    private lateinit var apiInterfaceTest: ApiInterface

    @Mock
    private lateinit var appRepositoryTest: AppsRepository

    @Mock
    private lateinit var itemsObserver: Observer<List<ResultsItem?>?>

    @Mock
    private lateinit var errorObserver: Observer<String?>

    @Mock
    private lateinit var progressObserver: Observer<Boolean>

    private lateinit var mainActivityViewModel: MainActivityViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        startKoin {
            androidContext(context)
            modules(appModule, viewModelModule)
        }

        mainActivityViewModel = MainActivityViewModel(
            appRepositoryTest,
            apiInterfaceTest,
        )
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun getArticleTest() {
        testCoroutineRule.runBlockingTest {
            val result =
                ArticleResponse(
                    status = "OK",
                    numResults = 20,
                    copyright = "Some content",
                    results = arrayListOf<ResultsItem>()
                )
            val path = 7
            val key = "api-key"
            mainActivityViewModel.articleList.observeForever(itemsObserver)
            Mockito.doReturn(result)
                .`when`(apiInterfaceTest)
                .getMostViewedArticles(path, key)
            mainActivityViewModel.articleList.value = result.results
            apiInterfaceTest.getMostViewedArticles(path, key)
            Mockito.verify(apiInterfaceTest, Mockito.times(1))
                .getMostViewedArticles(path, key)
            Mockito.verify(itemsObserver, Mockito.times(1))
                .onChanged(mainActivityViewModel.articleList.value)
            mainActivityViewModel.articleList.removeObserver(itemsObserver)
        }
    }

    @Test
    fun checkError() {
        mainActivityViewModel.errorMessage.observeForever(errorObserver)
        mainActivityViewModel.errorMessage.value = "error message"
        Mockito.verify(errorObserver, Mockito.times(1))
            .onChanged(mainActivityViewModel.errorMessage.value)
        mainActivityViewModel.errorMessage.removeObserver(errorObserver)
    }

    @Test
    fun testProgress() {
        mainActivityViewModel.progress.observeForever(progressObserver)
        mainActivityViewModel.progress.value = true
        Mockito.verify(progressObserver, Mockito.times(1))
            .onChanged(mainActivityViewModel.progress.value)
        mainActivityViewModel.progress.removeObserver(progressObserver)
    }
}