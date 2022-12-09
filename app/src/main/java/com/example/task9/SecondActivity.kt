package com.example.task9

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat

class SecondActivity : AppCompatActivity() {
    private lateinit var title:TextView
    private lateinit var message:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        NotificationManagerCompat.from(this).cancel(1)

        title = findViewById(R.id.textView)
        message = findViewById(R.id.textView2)
        val intentGet = intent


        title.text = intentGet.getStringExtra("title")
        message.text= intentGet.getStringExtra("text")



    }
}