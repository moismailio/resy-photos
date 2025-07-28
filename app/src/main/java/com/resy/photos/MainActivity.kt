package com.resy.photos

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.resy.design_system.theme.PhotosTheme
import com.resy.ui.GlobalMessaging
import com.resy.ui.GlobalMessagingReceiver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var globalMessagingReceiver: GlobalMessagingReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            PhotosTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
                    val globalMessagingState =
                        globalMessagingReceiver.messagingFlow.collectAsState(initial = null)
                    ProcessGlobalMessage(globalMessaging = globalMessagingState.value)
                    MainNavigator(
                        modifier = Modifier.padding(paddingValues),
                    )
                }
            }
        }
    }
}

@Composable
private fun ProcessGlobalMessage(globalMessaging: GlobalMessaging?) {
    if (globalMessaging == GlobalMessaging.NoInternet)
        Toast.makeText(LocalContext.current, stringResource(id = R.string.no_internet_connection), Toast.LENGTH_LONG).show()
}

