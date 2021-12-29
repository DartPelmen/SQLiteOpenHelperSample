package com.example.sqliteopenhelpersample.database

import android.provider.BaseColumns

object DatabaseContract {


    val createGenre = "CREATE TABLE ${Genres.title} (${BaseColumns._ID} integer primary key, ${Genres.columnName} varchar) IF NOT EXISTS"
    val createAuthor = "CREATE TABLE ${Authors.title} (${BaseColumns._ID} integer primary key, ${Authors.columnFirstName} varchar, ${Authors.columnPatronymic} varchar, ${Authors.columnLastName} varchar) IF NOT EXISTS"
    val createBook = "CREATE TABLE ${Books.title} (${BaseColumns._ID} integer primary key, ${Books.columnTitle} varchar, ${Books.columnGenreId} integer, foreign key( ${Books.columnGenreId}) REFERENCES ${Genres.title}(${BaseColumns._ID}) on delete cascade) IF NOT EXISTS"
    val createAuthorBookRef = "CREATE TABLE ${AuthorBookRef.title} (${AuthorBookRef.columnAuthorId} integer, ${AuthorBookRef.columnBookId} integer, primary key (${AuthorBookRef.columnAuthorId},${AuthorBookRef.columnBookId}), foreign key(${AuthorBookRef.columnBookId}) references ${Books.title}(${BaseColumns._ID}) on delete cascade, foreign key(${AuthorBookRef.columnAuthorId}) references ${Authors.title}(${BaseColumns._ID}) on delete cascade) IF NOT EXISTS"
    val dropGenre = "DROP TABLE ${Genres.title} IF EXISTS"
    val dropAuthor = "DROP TABLE ${Authors.title} IF EXISTS"
    val dropBook = "DROP TABLE ${Books.title} IF EXISTS"
    val dropAuthorBookRef = "DROP TABLE ${AuthorBookRef.title} IF EXISTS"


    object Authors:BaseColumns {
        val title = "AUTHORS"
        val columnFirstName = "fname"
        val columnPatronymic = "patronymic"
        val columnLastName = "lname"
    }
    object Books:BaseColumns {
        val title = "BOOKS"
        val columnTitle = "title"
        val columnGenreId = "genreId"
    }
    object Genres:BaseColumns {
        val title = "GENRES"
        val columnName = "name"
    }
    object AuthorBookRef {
        val title = "AUTHORBOOKREF"
        val  columnAuthorId = "authorId"
        val  columnBookId = "bookId"
    }
}