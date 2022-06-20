package com.ibrajix.whatsappclonecompose.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.ibrajix.whatsappclonecompose.utils.Constants.CONTACTS_COLLECTION
import com.ibrajix.whatsappclonecompose.utils.Constants.QUERY_PROPERTY
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirestoreModule {

    @Provides
    @Singleton
    fun provideGetContactsFromFirestore() = FirebaseFirestore.getInstance()
        .collection(CONTACTS_COLLECTION)
        .orderBy(QUERY_PROPERTY, Query.Direction.ASCENDING)

}