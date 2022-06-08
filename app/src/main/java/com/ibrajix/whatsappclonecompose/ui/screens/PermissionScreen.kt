package com.ibrajix.whatsappclonecompose.ui.screens

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale
import com.ibrajix.whatsappclonecompose.ui.components.ShowPermissionHelper
import com.ibrajix.whatsappclonecompose.ui.screens.destinations.HomeScreenDestination
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
                navigator.navigate(HomeScreenDestination)

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

