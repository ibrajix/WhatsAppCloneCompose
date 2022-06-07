package com.ibrajix.whatsappclonecompose.di

import com.ibrajix.whatsappclonecompose.datastore.Storage
import com.ibrajix.whatsappclonecompose.datastore.StorageInterface
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class StorageModule {

    @Binds
    abstract fun bindDataStorage(storage: Storage): StorageInterface

}