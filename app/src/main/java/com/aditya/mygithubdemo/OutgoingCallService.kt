package com.aditya.mygithubdemo

import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

 class OutgoingCallService : Service() {
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        val number = intent?.getStringExtra(Intent.EXTRA_PHONE_NUMBER)
        val callIntent = Intent(this, CallActivity::class.java).apply {
            putExtra("INCOMING_CALL", false)
            putExtra("CALLER_NUMBER", number)
            flags =
        }
        startActivity(callIntent)
        return START_NOT_STICKY
    }

}


class OutgoingCallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val serviceIntent = Intent(context, OutgoingCallService::class.java)
        context.startService(serviceIntent)
    }
}

