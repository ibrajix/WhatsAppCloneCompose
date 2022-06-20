package com.ibrajix.whatsappclonecompose.components

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable

@Composable
fun CircularProgressBar(
    isDisplayed: Boolean
) {
    if (isDisplayed){
        CircularProgressIndicator()
    }
}