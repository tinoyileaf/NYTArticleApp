package com.demo.nyarticleapp.di

import com.demo.nyarticleapp.ui.MainActivityViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainActivityViewModel(get(), get())
    }
}