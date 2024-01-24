package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toAddNote = findViewById<ImageButton>(R.id.imageButton)
        toAddNote.setOnClickListener {
            startActivity(Intent(this,addNote::class.java));
            finish()
        }
        val db = SQL_Db(this);
        val notesList = arrayListOf<Note>()
        var notesView = findViewById<RecyclerView>(R.id.recyclerView)
        notesView.layoutManager = LinearLayoutManager(this);
        notesView.adapter = NotesAdapter(this,db.getAllNotes())


    }
}