package com.demo.nyarticleapp.di

import com.demo.nyarticleapp.data.repository.AppsRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        AppsRepository()
    }
}