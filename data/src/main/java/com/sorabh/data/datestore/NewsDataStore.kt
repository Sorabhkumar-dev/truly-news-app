package com.sorabh.data.datestore

import kotlinx.coroutines.flow.Flow

interface NewsDataStore {

    suspend fun isAppFirstTimeLaunched(isFirst: Boolean)

    val isAppFirstLaunched: Flow<Boolean>
}