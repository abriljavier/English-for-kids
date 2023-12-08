package com.example.englishforkids

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class Database private constructor(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "english-for-kids.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
//         CREAR LA TABLA DE NIVELES
        db?.execSQL("CREATE TABLE levels (id INTEGER PRIMARY KEY, name TEXT, imagePath TEXT)")

//         CREAR LA TABLA DE USUARIOS
        db?.execSQL(
            "CREATE TABLE users (id INTEGER PRIMARY KEY, name TEXT UNIQUE, score INTEGER, idLevel INTEGER, " + "FOREIGN KEY(idLevel) REFERENCES levels(id))"
        )
    }

//    FALTAN TODOS LOS MÉTODOS DEL CRUD

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }
}