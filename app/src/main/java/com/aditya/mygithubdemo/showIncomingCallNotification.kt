//import android.Manifest
//import android.app.Activity
//import android.app.PendingIntent
//import android.content.Context
//import android.content.Intent
//import android.content.pm.PackageManager
//import androidx.core.app.ActivityCompat
//import androidx.core.app.NotificationCompat
//import androidx.core.app.NotificationManagerCompat
//import com.aditya.mygithubdemo.CALL_CHANNEL_ID
//import com.aditya.mygithubdemo.IncomingCallActivity
//import com.aditya.mygithubdemo.R
//
////const val INCOMING_CALL_NOTIFICATION_ID = 1 // Unique identifier for the notification
//
//fun showIncomingCallNotification(context: Context, callerName: String) {
//    val intent = Intent(context, IncomingCallActivity::class.java).apply {
//        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//    }
//    val pendingIntent = PendingIntent.getActivity(
//        context,
//        0,
//        intent,
//        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
//    )
//
//    val notification = NotificationCompat.Builder(context, 1)
//        .setSmallIcon(R.drawable.ic_launcher_background) // Ensure this drawable exists
//        .setContentTitle("Incoming Call")
//        .setContentText(callerName)
//        .setPriority(NotificationCompat.PRIORITY_HIGH)
//        .setFullScreenIntent(pendingIntent, true)
//        .build()
//
//    with(NotificationManagerCompat.from(context)) {
//        if (ActivityCompat.checkSelfPermission(
//                context,
//                Manifest.permission.POST_NOTIFICATIONS
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            // Request the missing permission
//            ActivityCompat.requestPermissions(
//                context as Activity,
//                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
//                0
//            )
//            return
//        }
//        notify(INCOMING_CALL_NOTIFICATION_ID, notification)
//    }
//}
