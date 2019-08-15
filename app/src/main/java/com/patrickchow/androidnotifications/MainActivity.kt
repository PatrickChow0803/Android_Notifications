package com.patrickchow.androidnotifications

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.getSystemService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var notificationManager : NotificationManager
        var notificationChannel : NotificationChannel
        var builder : Notification.Builder
        val channelId = "$packageName.notification"
        val description = "Test Notification"

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        btn_notification.setOnClickListener{

            //Makes it so that when the notification is clicked on, the FullScreen Activity starts up
            val intent = Intent(this, FullscreenActivity::class.java)
            intent.putExtra("key", "Value was passed successfully")
            val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel = NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.description = "Test test test"
                notificationChannel.enableLights(false)
                notificationChannel.lightColor = Color.BLACK
                notificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(this, channelId)
                        .setContentTitle("Code Android")
                        .setContentText("Test Notification")
                        .setSmallIcon(R.drawable.notification_icon_background)
                        .setContentIntent(pendingIntent)
            }
            else {
                builder = Notification.Builder(this)
                        .setContentTitle("Code Android")
                        .setContentText("Test Notification")
                        .setSmallIcon(R.drawable.notification_icon_background)
                        .setContentIntent(pendingIntent)
            }
            notificationManager.notify(123, builder.build())
        }

    }
}
