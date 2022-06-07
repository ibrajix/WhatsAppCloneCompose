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
import com.ibrajix.whatsappclonecompose.ui.components.ShowPermissionHelper
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
                navigator.popBackStack()
                navigator.navigate(ChatScreenDestination)

            }
            permissionState.status.shouldShowRationale -> {
                ShowPermissionHelper()
            }

            !permissionState.status.isGranted && !permissionState.status.shouldShowRationale -> {
                //permanently denied
                /*val customDialogState = rememberSaveable { mutableStateOf(true) }
                ShowCustomAlertDialog(openCustomDialog = customDialogState)*/
                ShowPermissionHelper()
            }

        }
    }


}

