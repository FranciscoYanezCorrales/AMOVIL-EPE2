package com.example.loginapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText
    lateinit var loginBtn: Button
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar la base de datos
        databaseHelper = DatabaseHelper(this)

        // Agregar usuarios predeterminados
        //databaseHelper.addUser("admin", "12345")
        //databaseHelper.addUser("user", "password")

        // Referencias a los campos de entrada
        usernameInput = findViewById(R.id.username_input)
        passwordInput = findViewById(R.id.password_input)
        loginBtn = findViewById(R.id.Login_btn)

        // Configurar el botón de inicio de sesión
        loginBtn.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            // Verificar las credenciales con la base de datos
            val isValid = databaseHelper.checkUser(username, password)


            if (isValid) {
                // Mostrar mensaje de login correcto
                Toast.makeText(this, "Login correcto", Toast.LENGTH_SHORT).show()

                // Redirigir a la pantalla de elección de acción
                val intent = Intent(this, ChoiceActivity::class.java)
                intent.putExtra("USERNAME", username)
                startActivity(intent)
            } else {
                // Mostrar mensaje de error
                Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
            }
        }
    }
}