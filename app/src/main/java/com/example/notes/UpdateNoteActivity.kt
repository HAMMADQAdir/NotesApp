package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class UpdateNoteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = SQL_Db(this);
        setContentView(R.layout.activity_update_note)
        val intent = intent
        val ID = intent.getIntExtra("NOTEID", -1)


        val currentNote = db.getNoteById(ID)

       var notTitle = findViewById<EditText>(R.id.Update_title)
        var noteContent = findViewById<EditText>(R.id.update_content)

        if (ID != -1) {

          notTitle.setText(currentNote.title)
            noteContent.setText(currentNote.content)

        } else {
            Toast.makeText(this, "NoteID not found", Toast.LENGTH_SHORT).show()
        }

        val UpdateButton = findViewById<Button>(R.id.updateBtn)
        UpdateButton.setOnClickListener {
            db.updateNote(Note(ID, notTitle.text.toString(),noteContent.text.toString()));
            Toast.makeText(this, "saved", Toast.LENGTH_SHORT).show()

           finish()
        }


    }
}