package com.demo.nyarticleapp

import android.app.Application
import com.demo.nyarticleapp.di.appModule
import com.demo.nyarticleapp.di.repositoryModule
import com.demo.nyarticleapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class NYTArticleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@NYTArticleApp)
            modules(
                listOf(
                    appModule, viewModelModule, repositoryModule
                )
            )
        }
    }
}