package com.sorabh.data.koin

import android.content.Context
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

object NewsKoinComponent : KoinComponent {
    val context: Context by inject()
}