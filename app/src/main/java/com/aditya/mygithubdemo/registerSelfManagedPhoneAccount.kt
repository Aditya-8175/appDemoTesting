package com.aditya.mygithubdemo

import android.content.ComponentName
import android.content.Context
import android.telecom.PhoneAccount
import android.telecom.PhoneAccountHandle
import android.telecom.TelecomManager

fun registerSelfManagedPhoneAccount(context: Context) {
    val telecomManager = context.getSystemService(Context.TELECOM_SERVICE) as TelecomManager

    val phoneAccountHandle = PhoneAccountHandle(
        ComponentName(context, YourConnectionService::class.java),
        "your_phone_account_id"
    )

    val phoneAccount = PhoneAccount.builder(phoneAccountHandle, "Connects App")
        .setCapabilities(PhoneAccount.CAPABILITY_SELF_MANAGED)
        .build()

    telecomManager.registerPhoneAccount(phoneAccount)
}
