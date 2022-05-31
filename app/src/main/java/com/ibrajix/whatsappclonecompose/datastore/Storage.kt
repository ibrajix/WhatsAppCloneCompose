package com.ibrajix.whatsappclonecompose.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Storage(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")
        val NIGHT_MODE_SELECTION = booleanPreferencesKey("night_mode_selection")
    }

    //save night mode selection
    suspend fun saveNightModeSelection(isNightModeSelected: Boolean){
        context.dataStore.edit {preferences->
            preferences[NIGHT_MODE_SELECTION] = isNightModeSelected
        }
    }

    //get night mode selected
    val getNightModeSelection : Flow<Boolean?> = context.dataStore.data
        .map { preferences->
            preferences[NIGHT_MODE_SELECTION] ?: false
        }

}