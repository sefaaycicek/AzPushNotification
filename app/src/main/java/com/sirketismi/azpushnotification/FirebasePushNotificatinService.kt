package com.sirketismi.azpushnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.os.bundleOf
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import java.util.Date

class FirebasePushNotificatinService : FirebaseMessagingService() {
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val resultIntent = Intent(this, MainActivity::class.java)

        val bundle = bundleOf("prm-1" to 100)
        resultIntent.putExtras(bundle)

        resultIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)

        val taskStackBuilder : TaskStackBuilder = TaskStackBuilder.create(this)
        taskStackBuilder.addParentStack(MainActivity::class.java)
        taskStackBuilder.addNextIntent(resultIntent)

        var requestCode = 1001
        val pendingIntent : PendingIntent = taskStackBuilder.getPendingIntent(requestCode, PendingIntent.FLAG_IMMUTABLE)

        val channelId = "tanitim"
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentText("Bildirim detayı")
            .setContentTitle("Bildirim başlığı")
            .setAutoCancel(false)
            .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                "Tanıtım Bildirimleri",
                NotificationManager.IMPORTANCE_HIGH)
            notificationManager.createNotificationChannel(channel)
        }

        val id = Date().time.toInt()
        notificationManager.notify(id, notificationBuilder.build())
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

        // api'yi güncelle

    }
}

//access_token": "ya29.a0AfB_byDEs16uebO_SD0Grn4SWqq5OROyaPo7ujcQFFRFyhRJ7Uj8vRj5JExQ64ttOxaN_7sEJWcjeEPfRNK2jeggRBReYUwvi2daKy0SccPZtBuh_7qmbu1RSz9rDFdLIz-zY790rpf-jpH2TlGWt7tx003O_lpUt6GeaCgYKATcSARESFQHGX2MiFbvmtu7B9ld34U6o62rufA0171