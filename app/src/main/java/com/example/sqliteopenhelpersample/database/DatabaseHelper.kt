package com.example.sqliteopenhelpersample.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "sqliteopenhelper-sample.db"
        @Volatile
        private var instance: DatabaseHelper? = null
        fun getInstance(context: Context): DatabaseHelper {
            if (instance==null)
                synchronized(DatabaseHelper::class.java) {
                    if (instance == null) {
                        instance = DatabaseHelper(context)
                    }
                }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL(DatabaseContract.createAuthor)
        db.execSQL(DatabaseContract.createGenre)
        db.execSQL(DatabaseContract.createBook)
        db.execSQL(DatabaseContract.createAuthorBookRef)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.beginTransaction()
        db.execSQL(DatabaseContract.dropAuthorBookRef)
        db.execSQL(DatabaseContract.dropBook)
        db.execSQL(DatabaseContract.dropAuthor)
        db.execSQL(DatabaseContract.dropGenre)
        db.execSQL(DatabaseContract.createAuthor)
        db.execSQL(DatabaseContract.createGenre)
        db.execSQL(DatabaseContract.createBook)
        db.execSQL(DatabaseContract.createAuthorBookRef)
        db.endTransaction()
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db,newVersion,oldVersion)
    }
}