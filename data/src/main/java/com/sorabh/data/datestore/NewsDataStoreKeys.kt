package com.sorabh.data.datestore

import androidx.datastore.preferences.core.booleanPreferencesKey

object NewsDataStoreKeys {
        val IS_FIRST_TIME = booleanPreferencesKey("is_first_time_launched")
}