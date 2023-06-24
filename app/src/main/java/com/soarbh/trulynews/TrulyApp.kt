package com.soarbh.trulynews

import android.app.Application
import android.content.res.Resources
import com.soarbh.trulynews.di.appModule
import com.sorabh.data.koin.NewsKoinComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class TrulyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@TrulyApp)
            modules(appModule)
        }
    }

    companion object {
        @Suppress("FunctionName")
        fun R(): Resources = NewsKoinComponent.context.resources
    }
}