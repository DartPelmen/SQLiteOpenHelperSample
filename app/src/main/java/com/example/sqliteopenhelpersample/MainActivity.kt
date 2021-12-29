package com.example.sqliteopenhelpersample

import android.content.ContentValues
import android.os.Bundle
import android.provider.BaseColumns
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.sqliteopenhelpersample.database.DatabaseContract
import com.example.sqliteopenhelpersample.database.DatabaseHelper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val d = DatabaseHelper.getInstance(this).writableDatabase
        val authorValues = ContentValues().apply {
            put(DatabaseContract.Authors.columnFirstName, "Alexander")
            put(DatabaseContract.Authors.columnLastName, "Pushkin")
            put(DatabaseContract.Authors.columnPatronymic, "Sergeevich")
        }
        val genreValues = ContentValues().apply {
            put(DatabaseContract.Genres.columnName,"classic")
        }
        val bookValues = ContentValues().apply {
            put(DatabaseContract.Books.columnGenreId,1)
            put(DatabaseContract.Books.columnTitle,"pushkins book")
        }
        val authorBookRefValues = ContentValues().apply {
            put(DatabaseContract.AuthorBookRef.columnAuthorId,1)
            put(DatabaseContract.AuthorBookRef.columnBookId,1)
        }

// Insert the new row, returning the primary key value of the new row
        val newAuthorRowId = d.insert(DatabaseContract.Authors.title, null, authorValues)
        val newGenreRowId = d.insert(DatabaseContract.Genres.title, null, genreValues)
        val newBookRowId = d.insert(DatabaseContract.Books.title, null, bookValues)
        val newAuthorBookRefRowId = d.insert(DatabaseContract.AuthorBookRef.title, null, authorBookRefValues)

        Log.d("AUTHOR", newAuthorRowId.toString())
        Log.d("Genre", newGenreRowId.toString())
        Log.d("Book", newBookRowId.toString())
        Log.d("AuthorBookRef", newAuthorBookRefRowId.toString())

        val r = DatabaseHelper.getInstance(this).readableDatabase

        val projection = arrayOf(BaseColumns._ID, DatabaseContract.Authors.columnLastName, DatabaseContract.Authors.columnFirstName, DatabaseContract.Authors.columnPatronymic)
        val sortOrder = "${DatabaseContract.Authors.columnLastName} DESC"

        val cursor = r.query(DatabaseContract.Authors.title, projection, "1=1", null, null,null, sortOrder)
        Log.d("SIZE", cursor.count.toString())
        with(cursor) {
            while (moveToNext()) {
                Log.d("AUTHOR", "${cursor.getLong(getColumnIndexOrThrow(BaseColumns._ID))} -|||- ${cursor.getString(getColumnIndexOrThrow(DatabaseContract.Authors.columnLastName))} -|||- ${cursor.getString(getColumnIndexOrThrow(DatabaseContract.Authors.columnFirstName))} -|||- ${cursor.getString(getColumnIndexOrThrow(DatabaseContract.Authors.columnPatronymic))}")
            }
        }
        cursor.close()
    }
}