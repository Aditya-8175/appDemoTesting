package com.aditya.contactapp

import android.content.Context
import android.content.Intent
import com.aditya.mygithubdemo.OutgoingCallActivity

fun startOutgoingCall(context: Context, callerName: String) {
    val intent = Intent(context, OutgoingCallActivity::class.java).apply {
        putExtra("CALLER_NAME", callerName)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
    }
    context.startActivity(intent)
}
