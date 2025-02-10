package com.aditya.mygithubdemo

import android.telecom.Call
import android.telecom.InCallService
import android.content.Intent
import android.util.Log

class MyInCallService : InCallService() {
    override fun onCallAdded(call: Call) {
        super.onCallAdded(call)
        Log.d("MyInCallService", "Incoming call added: ${call.details.handle}")
        // Launch your custom incoming call UI.
        val intent = Intent(this, IncomingCallActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        // You may pass call details via extras if needed.
        startActivity(intent)
    }

    override fun onCallRemoved(call: Call) {
        super.onCallRemoved(call)
        Log.d("MyInCallService", "Call removed")
    }
}
