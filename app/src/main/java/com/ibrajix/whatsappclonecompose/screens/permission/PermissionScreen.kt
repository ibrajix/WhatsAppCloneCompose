package com.ibrajix.whatsappclonecompose.screens.permission

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
import com.google.accompanist.permissions.rememberPermissionState
import com.ibrajix.whatsappclonecompose.components.ShowPermissionHelper
import com.ibrajix.whatsappclonecompose.screens.destinations.HomeScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun PermissionScreen(navigator: DestinationsNavigator) {

    val permissionState = rememberPermissionState(permission = Manifest.permission.CAMERA)

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
            permissionState.hasPermission -> {
                //go to chat screen
                navigator.popBackStack()
                navigator.navigate(HomeScreenDestination)

            }
            permissionState.shouldShowRationale -> {
                ShowPermissionHelper()
            }

            !permissionState.hasPermission && !permissionState.shouldShowRationale -> {
                //permanently denied
                /*val customDialogState = rememberSaveable { mutableStateOf(true) }
                ShowCustomAlertDialog(openCustomDialog = customDialogState)*/
                ShowPermissionHelper()
            }

        }
    }


}

