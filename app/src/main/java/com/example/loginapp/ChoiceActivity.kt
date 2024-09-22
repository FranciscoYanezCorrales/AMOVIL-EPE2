package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ChoiceActivity : AppCompatActivity() {

    lateinit var sendMailBtn: Button
    lateinit var newUserBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choice)

        sendMailBtn = findViewById(R.id.send_mail_btn)
        newUserBtn = findViewById(R.id.new_user_btn)

        val username = intent.getStringExtra("USERNAME")

        // Botón para enviar correo
        sendMailBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("USERNAME", username)
            startActivity(intent)
        }

        // Botón para registrar nuevo usuario
        newUserBtn.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}