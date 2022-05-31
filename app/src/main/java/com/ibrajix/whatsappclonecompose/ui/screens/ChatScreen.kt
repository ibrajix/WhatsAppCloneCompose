package com.ibrajix.whatsappclonecompose.ui.screens

import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ibrajix.whatsappclonecompose.R
import com.ibrajix.whatsappclonecompose.datastore.Storage
import com.ibrajix.whatsappclonecompose.ui.theme.topBarTextColor
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.launch

@Destination(start = true)
@Composable
fun ChatScreen(){

    Column(modifier = Modifier
        .fillMaxSize(),
    ){
        TopBar()
    }

}

@Composable
fun TopBar(
    modifier: Modifier = Modifier
) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dataStore = Storage(context)

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(105.dp)
            .background(MaterialTheme.colors.primary)
    ) {

        Row(
            modifier
                .fillMaxWidth()
                .padding(32.dp, 32.dp, 32.dp, 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        )
        {

            //whatsapp
            Text(
                text = stringResource(id = R.string.whatsapp),
                color = MaterialTheme.colors.topBarTextColor,
                style = MaterialTheme.typography.body1,
            )

            //switch
            val switchState = remember { mutableStateOf(false) }

            Switch(
                checked = switchState.value,
                onCheckedChange = { switchState.value = it },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colors.surface,
                    uncheckedThumbColor = MaterialTheme.colors.surface,
                    checkedTrackColor = MaterialTheme.colors.topBarTextColor,
                    uncheckedTrackColor = MaterialTheme.colors.topBarTextColor,
                    checkedTrackAlpha = 1.0f,
                    uncheckedTrackAlpha = 1.0f,
                ),
            )

            if (switchState.value) {
                LaunchedEffect(switchState) {
                    scope.launch {
                        dataStore.saveNightModeSelection(true)
                    }
                }
            } else {
                LaunchedEffect(switchState) {
                    scope.launch {
                        dataStore.saveNightModeSelection(false)
                    }
                }
            }

        }

        Row(
            modifier
                .fillMaxWidth()
                .padding(27.dp, 0.dp, 0.dp, 0.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_camera),
                contentDescription = stringResource(
                    id = R.string.icon
                )
            )

        }

    }

}
