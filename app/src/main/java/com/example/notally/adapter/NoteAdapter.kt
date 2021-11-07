package com.example.notally.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.notally.repos.model.Note
import com.example.notally.repos.model.NoteDiff

const val MAX_SPAN_COUNT = 2

class NoteAdapter(
    private val noteListener: NoteListener
) : ListAdapter<Note, NoteViewHolder>(NoteDiff) {

    interface NoteListener {
        fun onNoteClick(note: Note)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        NoteViewHolder.from(noteListener, parent)

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = getItem(position)
        holder.bind(note)
    }
}
