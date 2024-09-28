package com.example.loginapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    lateinit var usernameInput: EditText
    lateinit var passwordInput: EditText
    lateinit var registerBtn: Button
    lateinit var updateBtn: Button
    lateinit var deleteBtn: Button
    lateinit var viewUsersBtn: Button
    lateinit var userListView: ListView
    lateinit var databaseHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializar la base de datos
        databaseHelper = DatabaseHelper(this)

        usernameInput = findViewById(R.id.new_username_input)
        passwordInput = findViewById(R.id.new_password_input)
        registerBtn = findViewById(R.id.register_btn)
        updateBtn = findViewById(R.id.update_btn)
        deleteBtn = findViewById(R.id.delete_btn)
        viewUsersBtn = findViewById(R.id.view_users_btn)
        userListView = findViewById(R.id.user_list_view)

        // Botón para registrar un usuario
        registerBtn.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                databaseHelper.addUser(username, password)
                Toast.makeText(this, "Usuario registrado", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón para actualizar un usuario
        updateBtn.setOnClickListener {
            val username = usernameInput.text.toString()
            val password = passwordInput.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val result = databaseHelper.updateUser(username, username, password)
                if (result > 0) {
                    Toast.makeText(this, "Usuario actualizado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Botón para eliminar un usuario
        deleteBtn.setOnClickListener {
            val username = usernameInput.text.toString()
            if (username.isEmpty()) {
                Toast.makeText(this, "Ingrese el nombre de usuario", Toast.LENGTH_SHORT).show()
            } else {
                val result = databaseHelper.deleteUser(username)
                if (result > 0) {
                    Toast.makeText(this, "Usuario eliminado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Usuario no encontrado", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Botón para ver todos los usuarios registrados
        viewUsersBtn.setOnClickListener {
            val users = databaseHelper.getAllUsers()
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, users)
            userListView.adapter = adapter
        }
    }
}