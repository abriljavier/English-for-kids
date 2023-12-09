package com.example.englishforkids

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class Database(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "english-for-kids.db"
        private const val DATABASE_VERSION = 1
    }

    //CREAR LA BBDD, LAS TABLAS E INTRODUCIR LEVELS
    override fun onCreate(db: SQLiteDatabase?) {
        //CREAR LA TABLA DE NIVELES
        db?.execSQL("CREATE TABLE levels (id INTEGER PRIMARY KEY, name TEXT, category TEXT)")

        //CREAR LA TABLA DE USUARIOS
        db?.execSQL(
            "CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT UNIQUE, score INTEGER, idLevel INTEGER, " + "FOREIGN KEY(idLevel) REFERENCES levels(id))"
        )

        //INSERTAR LOS NIVELES
        val defaultLevels = listOf(
            Level(null, "circle", "SHAPES"),
            Level(null, "square", "SHAPES"),
            Level(null, "triangle", "SHAPES"),
            Level(null, "rectangle", "SHAPES"),
            Level(null, "star", "SHAPES"),
            Level(null, "car", "VEHICLES"),
            Level(null, "bicycle", "VEHICLES"),
            Level(null, "boat", "VEHICLES"),
            Level(null, "van", "VEHICLES"),
            Level(null, "motorbike", "VEHICLES"),
            Level(null, "taxi", "VEHICLES"),
            Level(null, "cat", "ANIMALS"),
            Level(null, "sheep", "ANIMALS"),
            Level(null, "lion", "ANIMALS"),
            Level(null, "dog", "ANIMALS"),
            Level(null, "elephant", "ANIMALS"),
            Level(null, "koala", "ANIMALS") )
        defaultLevels.forEach { level ->
                val values = ContentValues().apply {
                    put("name", level.name)
                    put("category", level.category)
                }
                db?.insert("levels", null, values)
            }

        val cursor = db?.rawQuery("SELECT * FROM levels", null)
        if (cursor != null) {
            Log.d("Database", "Number of rows in 'levels': ${cursor.count}")
            cursor.close()
        }
    }

    //INSERTAR USUARIO
    fun addUser(name: String, score: Int, idLevel: Int): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("name", name)
            put("score", score)
            put("idLevel", idLevel)
        }

        return db.insert("users", null, values)
    }

    //RECUPERAR LOS NOMBRES DE TODOS LOS USUARIOS PARA EL SPINNER
    fun getAllUserNames(): List<String> {
        val db = readableDatabase
        val projection = arrayOf("name") // Columnas que deseas recuperar

        // Puedes agregar condiciones adicionales si es necesario
        val cursor = db.query(
            "users", // Nombre de la tabla
            projection, // Columnas que deseas recuperar
            null, // Cláusula WHERE
            null, // Valores para la cláusula WHERE
            null, // GROUP BY
            null, // HAVING
            null  // ORDER BY
        )

        val userNames = mutableListOf<String>()

        with(cursor) {
            while (moveToNext()) {
                val userName = getString(getColumnIndexOrThrow("name"))
                userNames.add(userName)
            }
        }

        cursor.close()
        return userNames
    }

    //DEVOLVER LA LISTA DE USUARIOS
    fun getAllUsers(): List<User> {
        val userList = mutableListOf<User>()
        val query = "SELECT * FROM users"
        val cursor = readableDatabase.rawQuery(query, null)
        while (cursor.moveToNext()) {
            val id = cursor.getInt(0)
            val name = cursor.getString(1)
            val score = cursor.getInt(2)
            val idLevel = cursor.getInt(3)

            val user = User(id, name, score, idLevel)
            userList.add(user)
        }
        cursor.close()
        return userList
    }

    //ACTUALIZAR EL LEVEL Y LA PUNTUACIÓN DE UN USUARIO POR ID DE USER
    fun updateUserLevel(userId: Int, nextLevel: Int, newScore: Int): Int {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put("idLevel", nextLevel)
            put("score", newScore)
        }

        return db.update("users", contentValues, "id = ?", arrayOf(userId.toString()))
    }

    //DEVOLVER UN NIVEL POR ID
    fun getLevelById(levelId: Long): Level? {
        val db = readableDatabase
        var level: Level? = null

        val cursor = db.query(
            "levels",
            arrayOf("id", "name", "category"),
            "id = ?",
            arrayOf(levelId.toString()),
            null,
            null,
            null
        )

        cursor.use {
            if (it.moveToFirst()) {
                level = Level(
                    it.getInt(0),
                    it.getString(1),
                    it.getString(2),
                )
            }
        }

        return level
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}