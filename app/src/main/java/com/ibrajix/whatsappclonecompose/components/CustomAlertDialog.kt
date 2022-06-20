package com.ibrajix.whatsappclonecompose.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ibrajix.whatsappclonecompose.R
import com.ibrajix.whatsappclonecompose.style.theme.greyColor
import com.ibrajix.whatsappclonecompose.style.theme.tealGreenOpacity


/*
* Will use later on
* */


@Composable
fun ShowCustomAlertDialog(openCustomDialog: MutableState<Boolean>){

    if (openCustomDialog.value){
        Dialog(
            onDismissRequest = {
                openCustomDialog.value = false
            }) {
            CustomAlertDialog(openCustomDialog = openCustomDialog)
        }
    }
}

@Composable
fun CustomAlertDialog(modifier: Modifier = Modifier, openCustomDialog: MutableState<Boolean>){
    
    Card(
        shape = RoundedCornerShape(10.dp),
        modifier = modifier.padding(10.dp, 5.dp, 10.dp, 10.dp),
        elevation = 8.dp
    ) {
        Column(
            modifier = modifier.background(MaterialTheme.colors.background)
        ) {

            Image(
                painter = painterResource(id = R.drawable.ic_contact_permission),
                contentScale = ContentScale.Fit,
                colorFilter  = ColorFilter.tint(
                    color = MaterialTheme.colors.onBackground
                ),
                contentDescription = stringResource(id = R.string.icon),
                modifier = modifier
                    .padding(top = 35.dp)
                    .height(70.dp)
                    .fillMaxWidth())
            
            Column(modifier = 
            modifier.padding(16.dp)) {
                
                Text(
                    text = stringResource(id = R.string.permission_needed),
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.body1,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                
                Text(
                    text = stringResource(id = R.string.permission_needed_details),
                    textAlign = TextAlign.Center,
                    modifier = modifier
                        .padding(top = 10.dp, start = 25.dp, end = 25.dp)
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.body2
                )
                
            }

            Row(modifier = modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .background(MaterialTheme.colors.tealGreenOpacity),
                horizontalArrangement = Arrangement.SpaceAround
            ) {

                TextButton(onClick = { 
                    openCustomDialog.value = false
                }) {
                    Text(
                        text = stringResource(id = R.string.no),
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.greyColor,
                        modifier = modifier.padding(top = 5.dp, bottom = 5.dp)
                    )
                }

                TextButton(onClick = {
                    //open settings dialog to grant permission

                }) {
                    Text(
                        text = stringResource(id = R.string.allow),
                        fontWeight = FontWeight.ExtraBold,
                        color = MaterialTheme.colors.greyColor,
                        modifier = modifier
                            .padding(top = 5.dp, bottom = 5.dp)
                    )
                }
                
            }
        }
    }
}