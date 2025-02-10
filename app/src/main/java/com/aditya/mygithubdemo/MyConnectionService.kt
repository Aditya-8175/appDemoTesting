package com.aditya.mygithubdemo

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.telecom.Connection
import android.telecom.ConnectionRequest
import android.telecom.ConnectionService
import android.telecom.PhoneAccountHandle
import android.telecom.DisconnectCause
import android.telecom.PhoneAccount
import android.telecom.TelecomManager
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

//class MyConnectionService : ConnectionService() {
//    override fun onCreateIncomingConnection(
//        connectionManagerPhoneAccount: PhoneAccountHandle,
//        request: ConnectionRequest
//    ): Connection {
//        Log.d("MyConnectionService", "Incoming connection created")
//        // Create and configure your Connection instance here
//        val connection = MyConnection()
//        connection.setRinging()
//        return connection
//    }
//
//    override fun onCreateOutgoingConnection(
//        connectionManagerPhoneAccount: PhoneAccountHandle,
//        request: ConnectionRequest
//    ): Connection {
//        Log.d("MyConnectionService", "Outgoing connection created")
//        // Create and configure your Connection instance here
//        val connection = MyConnection()
//        connection.setDialing()
//        return connection
//    }
//}
//
//class MyConnection : Connection() {
//    override fun onAnswer() {
//        Log.d("MyConnection", "Call answered")
//        setActive()
//    }
//
//    override fun onReject() {
//        Log.d("MyConnection", "Call rejected")
//        setDisconnected(DisconnectCause(DisconnectCause.REJECTED))
//        destroy()
//    }
//
//    override fun onDisconnect() {
//        Log.d("MyConnection", "Call disconnected")
//        setDisconnected(DisconnectCause(DisconnectCause.LOCAL))
//        destroy()
//    }
//
//    // Optionally override other callbacks as needed.
//}


class MyConnectionService : ConnectionService() {
    override fun onCreateIncomingConnection(
        connectionManagerPhoneAccount: PhoneAccountHandle?,
        request: ConnectionRequest
    ): Connection {
        val callerName = request.extras.getString(TelecomManager.EXTRA_INCOMING_CALL_ADDRESS)
        val connection = createConnection(request)
        connection.setCallerDisplayName(callerName, TelecomManager.PRESENTATION_ALLOWED)

        val telecomManager = getSystemService(Context.TELECOM_SERVICE) as TelecomManager

        val phoneAccount = PhoneAccount.Builder(
            PhoneAccountHandle(ComponentName(this, MyConnectionService::class.java), "YourApp"),
            "Your App Name"
        ).setCapabilities(PhoneAccount.CAPABILITY_CALL_PROVIDER).build()

        telecomManager.registerPhoneAccount(phoneAccount)

//        val context = LocalContext.current
        // Launch custom UI
        val intent = Intent(this, CallActivity::class.java).apply {
            putExtra("INCOMING_CALL", true)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)

        return connection
    }

    private fun createConnection(request: ConnectionRequest): Connection {
        return object : Connection() {
            override fun onAnswer() {
                super.onAnswer()
                // Handle call answered
            }

            override fun onDisconnect() {
                super.onDisconnect()
                // Handle call ended
            }
        }
    }
}




@Composable
fun CallScreen(
    isIncoming: Boolean,
    callerNumber: String,
    onAccept: () -> Unit,
    onReject: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = if (isIncoming) "Incoming Call" else "Outgoing Call")
            Text(text = callerNumber)

            if (isIncoming) {
                Row {
                    Button(onClick = onAccept) { Text("Accept") }
                    Spacer(Modifier.width(16.dp))
                    Button(onClick = onReject) { Text("Reject") }
                }
            } else {
                Button(onClick = onReject) { Text("End Call") }
            }
        }
    }
}