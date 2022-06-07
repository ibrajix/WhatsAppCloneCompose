package com.ibrajix.whatsappclonecompose.ui.screens

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.ibrajix.whatsappclonecompose.BuildConfig
import com.ibrajix.whatsappclonecompose.R
import com.ibrajix.whatsappclonecompose.ui.components.ShowCustomAlertDialog
import com.ibrajix.whatsappclonecompose.ui.screens.destinations.ChatScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionScreen(navigator: DestinationsNavigator) {

    val permissionState = rememberPermissionState(permission = Manifest.permission.READ_CONTACTS)


    val lifecycleOwner = LocalLifecycleOwner.current
    DisposableEffect(
        key1 = lifecycleOwner,
        effect = {
        val observer = LifecycleEventObserver{_, event->
            if (event == Lifecycle.Event.ON_START){
                permissionState.launchPermissionRequest()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    })

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        when{
            permissionState.status.isGranted -> {
                //go to chat screen
                navigator.navigate(ChatScreenDestination)
            }
            permissionState.status.shouldShowRationale -> {

                //show permission dialog
                val customDialogState = rememberSaveable { mutableStateOf(true) }
                ShowCustomAlertDialog(openCustomDialog = customDialogState)

                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(text = stringResource(id = R.string.permission_needed))
                    Text(text = stringResource(id = R.string.permission_needed_details_screen))
                }

            }

            !permissionState.status.isGranted && !permissionState.status.shouldShowRationale -> {

                //permanently denied
                val customDialogState = rememberSaveable { mutableStateOf(true) }
                ShowCustomAlertDialog(openCustomDialog = customDialogState)

                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.permission_needed),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body1
                    )

                    Text(
                        modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                        text = stringResource(id = R.string.permission_needed_details_screen),
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.body2
                    )

                    val context = LocalContext.current

                    Button(
                        modifier = Modifier.padding(top = 10.dp),
                        onClick = {
                            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", BuildConfig.APPLICATION_ID, null))
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            startActivity(context, intent, null)
                        })
                    {
                        Text(text = stringResource(id = R.string.grant_permission))
                    }
                }

            }

        }
    }
}

@Composable
fun OpenSettings(){
    val context = LocalContext.current
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.fromParts("package", BuildConfig.APPLICATION_ID, null))
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(context, intent, null)
}
