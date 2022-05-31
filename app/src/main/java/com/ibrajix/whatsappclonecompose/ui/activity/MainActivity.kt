package com.ibrajix.whatsappclonecompose.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.ibrajix.whatsappclonecompose.R
import com.ibrajix.whatsappclonecompose.datastore.Storage
import com.ibrajix.whatsappclonecompose.ui.screens.ChatScreen
import com.ibrajix.whatsappclonecompose.ui.theme.BlueCheck
import com.ibrajix.whatsappclonecompose.ui.theme.StatusBarColorDark
import com.ibrajix.whatsappclonecompose.ui.theme.StatusBarColorLight
import com.ibrajix.whatsappclonecompose.ui.theme.WhatsAppCloneComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        setContent {

            val context = LocalContext.current
            val dataStore = Storage(context)
            val wasNightModeSelected = dataStore.getNightModeSelection.collectAsState(false)

            val systemUiController = rememberSystemUiController()

            if (wasNightModeSelected.value == true){
                systemUiController.setStatusBarColor(
                    color = MaterialTheme.colors.StatusBarColorDark
                )
                Toast.makeText(this, "Dark mode on", Toast.LENGTH_SHORT).show()
            }
            else{
                systemUiController.setStatusBarColor(
                    color = MaterialTheme.colors.StatusBarColorLight
                )
                Toast.makeText(this, "Dark mode off", Toast.LENGTH_SHORT).show()
            }

            WhatsAppCloneComposeTheme(darkTheme = wasNightModeSelected.value?: false) {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    ChatScreen()
                }

            }
        }

    }
}