package com.ibrajix.whatsappclonecompose.screens.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ibrajix.whatsappclonecompose.R
import com.ibrajix.whatsappclonecompose.components.CircularProgressBar
import com.ibrajix.whatsappclonecompose.screens.chat.model.Contact
import com.ibrajix.whatsappclonecompose.screens.chat.viewmodel.ChatViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Composable
@Destination
fun ChatScreen(modifier: Modifier = Modifier) {

    val chatViewModel: ChatViewModel = hiltViewModel()
    val contacts = chatViewModel.data.value

    contacts.data?.let {
        LazyColumn{
            items(
                items = it
            ){ contact: Contact ->
                ContactView(contact)
            }
        }
    }

    //for exception
    contacts.e?.let {
        Text(
            text = it.message?: stringResource(id = R.string.error_message)
        )
    }

    //show progress bar
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement =  Arrangement.Center,
        horizontalAlignment =  Alignment.CenterHorizontally
    ){
        CircularProgressBar(
            isDisplayed = chatViewModel.loading.value
        )
    }

}


@Composable
fun ContactView(contact: Contact){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 12.dp)
    ) {

        contact.name?.let { name->
            Text(
                text = name,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.Start),
            )
        }

    }
}