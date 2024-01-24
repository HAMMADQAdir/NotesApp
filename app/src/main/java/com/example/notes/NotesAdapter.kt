package com.example.notes

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(val context: Activity, var notesList: List<Note>) :
    RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NotesAdapter.NotesViewHolder {
        // Inflate the item layout
        val view = LayoutInflater.from(parent.context).inflate(R.layout.notes_view, parent, false)
        return NotesViewHolder(view)
    }

    override fun onBindViewHolder(holder: NotesAdapter.NotesViewHolder, position: Int) {
        // Bind data to the views in each item
        val note = notesList[position]

        holder.titleTextView.text = note.title.toString()
        holder.contentTextView.text = note.content.toString()
        holder.btn.setOnClickListener {

            val intent = Intent(holder.itemView.context,UpdateNoteActivity::class.java)
            intent.putExtra("NOTEID",note.id);

            holder.itemView.context.startActivity(intent);
            refreshData(notesList)
        }

    }

    override fun getItemCount(): Int {
        return notesList.size
    }
  fun refreshData(notelist:List<Note>){
    this.notesList = notelist
    notifyDataSetChanged()
}
    // ViewHolder class to hold references to the views in each item
    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
         val titleTextView: TextView = itemView.findViewById(R.id.editTextText)
         val contentTextView: TextView = itemView.findViewById(R.id.editTextText2)
         val btn = itemView.findViewById<CardView>(R.id.cardView)

//        fun bind(note: Note) {
//            // Bind data to the views
//            titleTextView.text = note.title
//            contentTextView.text = note.content
//            btn.setOnClickListener {
//               val intent = Intent(holde)
//            }


    }
}
