package com.ibrajix.whatsappclonecompose.screens.chat.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ibrajix.whatsappclonecompose.screens.chat.model.Contact
import com.ibrajix.whatsappclonecompose.screens.chat.model.DataOrException
import com.ibrajix.whatsappclonecompose.screens.chat.repo.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(private val chatRepository: ChatRepository) : ViewModel (
) {


    var loading = mutableStateOf(false)

    val data: MutableState<DataOrException<List<Contact>, Exception>> = mutableStateOf(
        DataOrException(
            listOf(),
            Exception("")
        )
    )

    init {
        getContacts()
    }

    private fun getContacts(){
        viewModelScope.launch {
            loading.value = true
            data.value = chatRepository.getContactsFromFireStore()
            loading.value = false
        }
    }

}