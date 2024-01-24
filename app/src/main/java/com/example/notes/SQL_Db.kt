package com.example.notes

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.notes.SQL_Db.Companion.TABLE_NAME

class SQL_Db (context:Context):SQLiteOpenHelper(context,DB_NAME,null, DB_VERSION){

    companion object{
        private const val DB_NAME = "NOTES.db"
        private const val DB_VERSION = 1
        private const val TABLE_NAME = "NOTES_TABLE"
        private const val COLUMN_id = "id"
        private const val COLUMN_TITLE = "title"
        private const val COLUMN_CONTENT = "content"
    }

    override fun onCreate(db: SQLiteDatabase?) {
       val createTable = "CREATE TABLE $TABLE_NAME ($COLUMN_id INTEGER PRIMARY KEY , $COLUMN_TITLE TEXT,$COLUMN_CONTENT TEXT)"
    db!!.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun insert(note:Note){
        val database  = writableDatabase
        val value = ContentValues().apply {
            put(COLUMN_TITLE,note.title)
            put(COLUMN_CONTENT,note.content)

        }
        database.insert(TABLE_NAME,null,value)
        database.close()
    }
    fun getAllNotes():List<Note>{
        var notesList = mutableListOf<Note>()
        val querry = "SELECT * FROM $TABLE_NAME"
        val db = readableDatabase
        val cursor = db.rawQuery(querry,null)
        while(cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_id))
            val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
            val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))
            notesList.add(Note(id,title,content))
        }
        cursor.close()
        db.close()
        return notesList
    }
    fun updateNote(note:Note){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_TITLE,note.title)
            put(COLUMN_CONTENT,note.content)
        }
        db.update(TABLE_NAME,values,"$COLUMN_id = ?", arrayOf(note.id.toString()))
        db.close()
    }
    fun delete(noteId:Int){
        val db = writableDatabase;
        db.delete(TABLE_NAME,"$COLUMN_id =?", arrayOf(noteId.toString()))
        db.close()
    }
    fun getNoteById(noteId:Int):Note{
        val db = readableDatabase;
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_id = $noteId"
        val cursor = db.rawQuery(query,null)
        cursor.moveToFirst()
        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_id))
        val title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TITLE))
        val content = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CONTENT))
        cursor.close()
        return Note(id,title,content)

    }
}