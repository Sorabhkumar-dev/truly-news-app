package com.sorabh.data.datestore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.sorabh.data.util.Constant
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class NewsDataStoreImpl constructor(private val context: Context) : NewsDataStore {
    private val Context.dataStore by preferencesDataStore(Constant.DATA_STORE_NAME)
    override suspend fun isAppFirstTimeLaunched(isFirst: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[NewsDataStoreKeys.IS_FIRST_TIME] = isFirst
        }
    }

    override val isAppFirstLaunched: Flow<Boolean> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            preferences[NewsDataStoreKeys.IS_FIRST_TIME] ?: false
        }
}