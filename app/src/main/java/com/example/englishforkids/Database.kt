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

    override fun onCreate(db: SQLiteDatabase?) {
        //CREAR LA TABLA DE NIVELES
        db?.execSQL("CREATE TABLE levels (id INTEGER PRIMARY KEY, name TEXT, category TEXT, image TEXT)")

        //CREAR LA TABLA DE USUARIOS
        db?.execSQL(
            "CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT UNIQUE, score INTEGER, idLevel INTEGER, " + "FOREIGN KEY(idLevel) REFERENCES levels(id))"
        )

        //INSERTAR LOS NIVELES
        val defaultLevels = listOf(
            Level(null, "circle", "SHAPES", "circle"),
            Level(null, "square", "SHAPES", "square"),
            Level(null, "triangle", "SHAPES", "triangle"),
            Level(null, "rectangle", "SHAPES", "rectangle"),
            Level(null, "star", "SHAPES", "star"),
            Level(null, "car", "VEHICLES", "car"),
            Level(null, "bicycle", "VEHICLES", "bicycle"),
            Level(null, "boat", "VEHICLES", "boat"),
            Level(null, "van", "VEHICLES", "van"),
            Level(null, "motorbike", "VEHICLES", "motorbike"),
            Level(null, "taxi", "VEHICLES", "taxi"),
            Level(null, "cat", "ANIMALS", "cat"),
            Level(null, "sheep", "ANIMALS", "sheep"),
            Level(null, "lion", "ANIMALS", "lion"),
            Level(null, "dog", "ANIMALS", "dog"),
            Level(null, "elephant", "ANIMALS", "elephant"),
            Level(null, "koala", "ANIMALS", "koala") )
        defaultLevels.forEach { level ->
                val values = ContentValues().apply {
                    put("name", level.name)
                    put("category", level.category)
                    put("image", level.image)
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

    //DEVOLVER UN USUARIO
    fun getUserById(userId: Long): User? {
        val db = readableDatabase
        var user: User? = null

        val cursor = db.query(
            "users",
            arrayOf("id", "name", "score", "idLevel"),
            "id = ?",
            arrayOf(userId.toString()),
            null,
            null,
            null
        )

        if (cursor.moveToFirst()) {
            val id = cursor.getLong(cursor.getColumnIndex("id"))
            val name = cursor.getString(cursor.getColumnIndex("name"))
            val score = cursor.getInt(cursor.getColumnIndex("score"))
            val idLevel = cursor.getLong(cursor.getColumnIndex("idLevel"))

            user = User(id, name, score, idLevel)
        }

        cursor.close()
        db.close()

        return user
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}