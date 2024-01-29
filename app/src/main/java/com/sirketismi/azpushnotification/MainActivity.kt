package com.sirketismi.azpushnotification

import android.Manifest
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.google.firebase.messaging.FirebaseMessaging

//1. Permission

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        askNotificationPermission()
    }

    val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (granted) {
                getToken()
            } else {

            }
        }

    fun askNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            requestPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    fun getToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task->
            if(task.isSuccessful) {
                println(task.result)
            }
        }
    }
}

//dcY8IqNlQGu2asz24ZWIes:APA91bGKcm7ElHACpUYenxTl-dCeGO8mwUkaYBKhXcfjmmWZrS7sMqwMco2fU1vpq19d2i0GWavLz7qZHBDiYRyl9Rr4h6LZguY9Tj66j25FWo1Twjt7l5Jvw1EcXKou-FtrEkzhKTrD