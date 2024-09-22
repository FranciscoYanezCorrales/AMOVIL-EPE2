package com.example.loginapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText
    lateinit var registerBtn: Button
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializar la base de datos
        databaseHelper = DatabaseHelper(this)

        usernameInput = findViewById(R.id.new_username_input)
        passwordInput = findViewById(R.id.new_password_input)
        registerBtn = findViewById(R.id.register_btn)

        // Configurar el botón de registro
        registerBtn.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            // Validar los campos
            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Agregar el nuevo usuario a la base de datos
                databaseHelper.addUser(username, password)
                Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
                finish() // Cerrar la actividad después de registrar
            }
        }
    }
}