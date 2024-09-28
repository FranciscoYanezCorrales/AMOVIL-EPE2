package com.example.loginapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // Referencia a la base de datos llamada "loginapp"
        private const val DATABASE_NAME = "LoginApp.db" // Nombre de la base de datos
        private const val DATABASE_VERSION = 1
        private const val TABLE_USERS = "Users" // Nombre de la tabla
        private const val COLUMN_ID = "id"
        private const val COLUMN_USERNAME = "username"
        private const val COLUMN_PASSWORD = "password"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Crear la tabla Users con las columnas id, username y password
        val createTable = "CREATE TABLE $TABLE_USERS ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_USERNAME TEXT, $COLUMN_PASSWORD TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    // Create - Agregar un nuevo usuario
    fun addUser(username: String, password: String): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USERNAME, username)
        values.put(COLUMN_PASSWORD, password)
        val result = db.insert(TABLE_USERS, null, values)
        db.close()
        return result
    }

    // Read - Obtener todos los usuarios
    fun getAllUsers(): List<String> {
        val db = this.readableDatabase
        val users = ArrayList<String>()
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USERS", null)
        if (cursor.moveToFirst()) {
            do {
                users.add(cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_USERNAME)))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return users
    }

    // Update - Actualizar un usuario
    fun updateUser(oldUsername: String, newUsername: String, newPassword: String): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_USERNAME, newUsername)
        values.put(COLUMN_PASSWORD, newPassword)
        val result = db.update(TABLE_USERS, values, "$COLUMN_USERNAME=?", arrayOf(oldUsername))
        db.close()
        return result
    }

    // Delete - Eliminar un usuario
    fun deleteUser(username: String): Int {
        val db = this.writableDatabase
        val result = db.delete(TABLE_USERS, "$COLUMN_USERNAME=?", arrayOf(username))
        db.close()
        return result
    }

    // Método para verificar si el usuario existe con la contraseña correcta
    fun checkUser(username: String, password: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USERS WHERE $COLUMN_USERNAME = ? AND $COLUMN_PASSWORD = ?"
        val cursor = db.rawQuery(query, arrayOf(username, password))

        val exists = cursor.moveToFirst()
        cursor.close()
        db.close()

        return exists
    }
}