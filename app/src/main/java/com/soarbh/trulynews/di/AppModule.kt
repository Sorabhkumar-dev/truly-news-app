package com.soarbh.trulynews.di

import com.sorabh.data.koin.dataModule
import org.koin.dsl.module

val appModule = module {
    includes(uiModule, dataModule)
}