package com.aditya.mygithubdemo

import android.content.Intent
import android.telecom.Connection

//class YourConnection : Connection() {
//    override fun onAnswer() {
//        super.onAnswer()
//        setActive()
//        // Handle answering the call
//    }
//
//    override fun onDisconnect() {
//        super.onDisconnect()
//        setDisconnected(DisconnectCause(DisconnectCause.LOCAL))
//        // Handle disconnection logic
//        destroy()
//    }
//
//    override fun onReject() {
//        super.onReject()
//        setDisconnected(DisconnectCause(DisconnectCause.REJECTED))
//        // Handle rejection logic
//        destroy()
//    }
//}

import android.content.Context

class YourConnection(private val context: Context) : Connection() {

    override fun onShowIncomingCallUi() {
        super.onShowIncomingCallUi()
        // Launch your custom incoming call UI
        val intent = Intent(context, IncomingCallActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }
}
