//package com.aditya.mygithubdemo
//
//import android.app.Application
//import android.app.NotificationChannel
//import android.app.NotificationManager
//import android.content.Context
//import android.media.AudioAttributes
//import android.media.RingtoneManager
//import android.os.Build
//
//class ContactApp: Application() {
//
//    override fun onCreate() {
//        super.onCreate()
//        registerSelfManagedPhoneAccount(this)
//
//        createNotificationChannel(this)
//
//    }
//
//
//    fun createNotificationChannel(context: Context) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channelName = "Incoming Calls"
//            val channelDescription = "Channel for incoming call notifications"
//            val importance = NotificationManager.IMPORTANCE_HIGH
//
//            val channel = NotificationChannel(CALL_CHANNEL_ID, channelName, importance).apply {
//                description = channelDescription
//                val ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
//                setSound(
//                    ringtoneUri,
//                    AudioAttributes.Builder()
//                        .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
//                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
//                        .build()
//                )
//            }
//
//            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }
//
//
//}




package com.aditya.mygithubdemo

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.role.RoleManager
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.telecom.PhoneAccount
import android.telecom.PhoneAccountHandle
import android.telecom.TelecomManager
import android.util.Log

const val CALL_CHANNEL_ID = "CALL_CHANNEL_ID"
const val REQUEST_CODE_SET_DEFAULT_DIALER = 1001

class ContactApp : Application() {
    override fun onCreate() {
        super.onCreate()
        registerManagedPhoneAccount(this)
        createNotificationChannel(this)
        promptForDefaultDialer()
    }
}
fun registerManagedPhoneAccount(context: Context) {
    val telecomManager = context.getSystemService(Context.TELECOM_SERVICE) as TelecomManager

    Log.d("DefaultDialerCheck", "Default dialer is: ${telecomManager.defaultDialerPackage}")

    val phoneAccountHandle = PhoneAccountHandle(
        ComponentName(context, MyConnectionService::class.java), // Use the ConnectionService here!
        "your_phone_account_id"
    )
    val phoneAccount = PhoneAccount.builder(phoneAccountHandle, "Connects App")
        .setCapabilities(
            PhoneAccount.CAPABILITY_CALL_PROVIDER
//                    or
//                    PhoneAccount.CAPABILITY_HOLD or
//                    PhoneAccount.CAPABILITY_SUPPORT_HOLD
        )
        .build()
    telecomManager.registerPhoneAccount(phoneAccount)
}

//
//fun registerManagedPhoneAccount(context: Context) {
//    val telecomManager = context.getSystemService(Context.TELECOM_SERVICE) as TelecomManager
//    // Use your InCallService class here.
//    val phoneAccountHandle = PhoneAccountHandle(
//        ComponentName(context, MyInCallService::class.java),
//        "your_phone_account_id"
//    )
//    val phoneAccount = PhoneAccount.builder(phoneAccountHandle, "Connects App")
//        // Use managed call provider capabilities instead of self-managed.
//        .setCapabilities(
//            PhoneAccount.CAPABILITY_CALL_PROVIDER
////                    or
////                    PhoneAccount.CAPABILITY_HOLD or
////                    PhoneAccount.CAPABILITY_SUPPORT_HOLD
//        )
//        .build()
//    telecomManager.registerPhoneAccount(phoneAccount)
//}

fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelName = "Incoming Calls"
        val channelDescription = "Channel for incoming call notifications"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(CALL_CHANNEL_ID, channelName, importance).apply {
            description = channelDescription
            val ringtoneUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            setSound(
                ringtoneUri,
                AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION_RINGTONE)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build()
            )
        }
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}

fun Application.promptForDefaultDialer() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        val roleManager = getSystemService(RoleManager::class.java)
        if (!roleManager.isRoleHeld(RoleManager.ROLE_DIALER)) {
            val intent = roleManager.createRequestRoleIntent(RoleManager.ROLE_DIALER)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    } else {
        val telecomManager = getSystemService(Context.TELECOM_SERVICE) as TelecomManager
        if (telecomManager.defaultDialerPackage != packageName) {
            val intent = Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER).apply {
                putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, packageName)
                addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            }
            startActivity(intent)
        }
    }
}

