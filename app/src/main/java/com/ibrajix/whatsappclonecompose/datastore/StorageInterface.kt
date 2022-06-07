package com.ibrajix.whatsappclonecompose.datastore

import kotlinx.coroutines.flow.Flow

interface StorageInterface {
    fun getSelectedTheme() : Flow<Boolean>
    suspend fun setSelectedTheme(theme: Boolean)
}