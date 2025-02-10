package com.aditya.mygithubdemo

import android.content.Intent
import android.telecom.Connection
import android.telecom.ConnectionRequest
import android.telecom.ConnectionService
import android.telecom.PhoneAccountHandle

class YourConnectionService : ConnectionService() {
    override fun onCreateIncomingConnection(
        connectionManagerPhoneAccount: PhoneAccountHandle,
        request: ConnectionRequest
    ): Connection {
        val connection = YourConnection(this)
        connection.setRinging()

        val callerName = "Caller Name" // Retrieve caller information as needed
//        showIncomingCallNotification(this, callerName)




        // Display your custom incoming call UI
//        val intent = Intent(this, IncomingCallActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            putExtra("EXTRA_CALL_INFO", request)
//        }
//        startActivity(intent)
        return connection
    }

    override fun onCreateOutgoingConnection(
        connectionManagerPhoneAccount: PhoneAccountHandle,
        request: ConnectionRequest
    ): Connection {
        val connection = YourConnection(this)
        connection.setDialing()
        // Display your custom outgoing call UI
        val intent = Intent(this, OutgoingCallActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra("EXTRA_CALL_INFO", request)
        }
        startActivity(intent)
        return connection
    }
}

