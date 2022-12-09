package com.example.task9

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat



class MainActivity : AppCompatActivity() {

    private lateinit var button: Button
    private val CHANNEL_ID = "channel"
    private lateinit var title:EditText
    private lateinit var text:EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)



        createNotificationChannel()



        button.setOnClickListener {

            showNotification()

        }

    }

    private fun showNotification(){

        val bitmap = BitmapFactory.decodeResource(resources,
           R.drawable.android
        )

        title = findViewById(R.id.name)
        text = findViewById(R.id.text)
        val boldTitle: CharSequence =
            Html.fromHtml("<b>" + title.text.toString().uppercase() + "</b>" )






        val mainIntent = Intent(this, SecondActivity::class.java)
        mainIntent.putExtra("title",title.text.toString())
        mainIntent.putExtra("text",text.text.toString())

        val pendingIntent = PendingIntent.getActivity(this,0,mainIntent,0)

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_menu_call)
            .setContentTitle(boldTitle)
            .setContentText(text.text.toString())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setLargeIcon(bitmap)
            .setStyle(NotificationCompat.BigPictureStyle()
                .bigPicture(bitmap))
            .addAction(R.drawable.android,"Reply",pendingIntent)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)






        with(NotificationManagerCompat.from(this)) {
            notify(1,builder.build())
        }





    }

    private fun createNotificationChannel(){

        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Channel Name"
            val descriptionText ="Channel Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

    }


}