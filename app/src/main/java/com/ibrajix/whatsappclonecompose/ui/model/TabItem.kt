package com.ibrajix.whatsappclonecompose.ui.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.ibrajix.whatsappclonecompose.R
import com.ibrajix.whatsappclonecompose.ui.screens.CallsScreen
import com.ibrajix.whatsappclonecompose.ui.screens.CameraScreen
import com.ibrajix.whatsappclonecompose.ui.screens.ChatScreen
import com.ibrajix.whatsappclonecompose.ui.screens.StatusScreen


sealed class TabItem(
    val index: Int,
    @DrawableRes val icon: Int?,
    @StringRes val title: Int,
    val screenToLoad: @Composable () -> Unit
){

    object Camera: TabItem(0, R.drawable.ic_camera, R.string.empty_string, {
        CameraScreen()
    })

    object Chat: TabItem(1, null, R.string.chats, {
        ChatScreen()
    })

    object Status: TabItem(2, null, R.string.status, {
        StatusScreen()
    })

    object Call: TabItem(3, null, R.string.calls, {
        CallsScreen()
    })

}