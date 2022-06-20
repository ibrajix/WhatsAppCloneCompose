package com.ibrajix.whatsappclonecompose.datastore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StorageViewModel @Inject constructor(private val dataStorage: Storage) : ViewModel() {

    val selectedTheme = dataStorage.getSelectedTheme().asLiveData()

    fun changeSelectedTheme(isDarkMode: Boolean){
        viewModelScope.launch {
            dataStorage.setSelectedTheme(isDarkMode)
        }
    }

}