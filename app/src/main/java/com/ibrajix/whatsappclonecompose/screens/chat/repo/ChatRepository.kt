package com.ibrajix.whatsappclonecompose.screens.chat.repo

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.ibrajix.whatsappclonecompose.screens.chat.model.Contact
import com.ibrajix.whatsappclonecompose.screens.chat.model.DataOrException
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatRepository @Inject constructor(private val getContacts: Query) {

    suspend fun getContactsFromFireStore() : DataOrException<List<Contact>, Exception> {

        val dataOrException = DataOrException<List<Contact>, Exception>()

        try {
            dataOrException.data = getContacts.get().await().map { document->
                document.toObject(Contact::class.java)
            }
        }
        catch (e: FirebaseFirestoreException){
            dataOrException.e = e
        }

        return dataOrException
    }

}