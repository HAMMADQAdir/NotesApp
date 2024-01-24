package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import java.lang.Exception

class addNote : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_note)

        val db = SQL_Db(this)


      findViewById<Button>(R.id.add).setOnClickListener {
          var title =findViewById<EditText>(R.id.title)
          var content =findViewById<EditText>(R.id.content)
          db.insert(Note(0, title.text.toString(), content.text.toString()))
          Toast.makeText(this, "Note saved", Toast.LENGTH_SHORT).show()
      }

    }
}
