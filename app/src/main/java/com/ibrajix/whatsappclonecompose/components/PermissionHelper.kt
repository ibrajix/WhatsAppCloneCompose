package com.ibrajix.whatsappclonecompose.components

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.ibrajix.whatsappclonecompose.BuildConfig
import com.ibrajix.whatsappclonecompose.R

@Composable
fun ShowPermissionHelper() {

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
                ContextCompat.startActivity(context, intent, null)
            })
        {
            Text(text = stringResource(id = R.string.grant_permission))
        }
    }

}