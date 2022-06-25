package com.ibrajix.whatsappclonecompose.screens.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ibrajix.whatsappclonecompose.R
import com.ibrajix.whatsappclonecompose.components.CircularProgressBar
import com.ibrajix.whatsappclonecompose.screens.chat.model.Contact
import com.ibrajix.whatsappclonecompose.screens.chat.viewmodel.ChatViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.skydoves.landscapist.glide.GlideImage
import java.text.SimpleDateFormat
import java.util.*

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

    val dateFormat = SimpleDateFormat("hh:mm a", Locale.US)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 25.dp, top = 12.dp, bottom = 12.dp, end = 12.dp),
    ) {

        GlideImage(
            imageModel = contact.profilePhoto,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .size(50.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f),
            verticalArrangement = Arrangement.Center
        ) {

            Text(
                modifier = Modifier
                    .padding(start = 10.dp),

                text = contact.name?:"",
                style = MaterialTheme.typography.h3
            )

            Text(
                modifier = Modifier
                    .padding(start = 10.dp),
                text = contact.lastMessage?:"",
                style = MaterialTheme.typography.subtitle2
            )
            
        }

        Text(
            modifier = Modifier
                .padding(end = 20.dp)
                .wrapContentHeight(Alignment.Bottom),
            textAlign = TextAlign.Center,
            text = dateFormat.format(contact.date!!).toString(),
            style = MaterialTheme.typography.subtitle2
        )

    }
}