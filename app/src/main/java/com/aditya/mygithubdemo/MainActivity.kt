package com.aditya.mygithubdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.aditya.mygithubdemo.ui.theme.MyGithubDemoTheme

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.os.IBinder
import android.telecom.TelecomManager
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.platform.LocalContext

//const val CALL_CHANNEL_ID = "CALL_CHANNEL_ID"


class MainActivity : ComponentActivity() {

    private fun requestDefaultDialer() {
        val telecomManager = getSystemService(TELECOM_SERVICE) as TelecomManager
        if (telecomManager.defaultDialerPackage != packageName) {
            val intent = Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER)
            intent.putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, packageName)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyGithubDemoTheme {
                val permissions = arrayOf(
                    android.Manifest.permission.CALL_PHONE,
                    android.Manifest.permission.READ_PHONE_STATE,
                    android.Manifest.permission.ANSWER_PHONE_CALLS
                )

                ActivityResultContracts.RequestMultiplePermissions().launch(permissions)


            }
        }
    }

}


class CallActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val isIncoming = intent.getBooleanExtra("INCOMING_CALL", false)
        val callerNumber = intent.getStringExtra("CALLER_NUMBER") ?: "Unknown"

        setContent {
            MyGithubDemoTheme {
                CallScreen(
                    isIncoming = isIncoming,
                    callerNumber = callerNumber,
                    onAccept = { answerCall() },
                    onReject = { endCall() }
                )
            }
        }
    }

    private fun answerCall() {
        // Implement call answering logic
    }

    private fun endCall() {
        // Implement call ending logic
        finish()
    }
}


