package com.example.mobprogtk3.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class SQLiteHelper(context: Context?) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE result (_id INTEGER PRIMARY KEY AUTOINCREMENT, value TEXT NOT NULL);")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Schema Upgrade
    }

    fun dbSave(result: String) {
        Log.d("SAVE DB", "dbSave Called")
        val db = writableDatabase
        val values = ContentValues()
        Log.d("SAVE DB result", "result: $result")
        Log.d("SAVE DB values", "values: $values")
        values.put("value", result)
        db.insert("result", null, values)
        db.close()
    }

    companion object {
        private const val DATABASE_NAME = "TK3_DB"
        private const val DATABASE_VERSION = 1
    }

}