package com.soarbh.trulynews

import android.app.Application
import com.soarbh.trulynews.di.appModule
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

}