package com.leandrogon.notes.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.leandrogon.notes.R
import com.leandrogon.notes.model.Note

class NotesListAdapter(var notes: MutableList<Note>, var callback: NoteListener): RecyclerView.Adapter<NotesListAdapter.NotesListViewHolder>() {

    interface NoteListener {
        fun onViewClick(note: Note)
        fun onDeleteNoteClick(note: Note)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): NotesListViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.view_note_row, p0, false)
        return NotesListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NotesListViewHolder, position: Int) {
        val note = notes.get(position)

        holder.titleTv.text = note.title
        holder.subtitleTv.text = note.content
        holder.deleteIv.setOnClickListener { callback.onDeleteNoteClick(note) }
        holder.containerView.setOnClickListener { callback.onViewClick(note) }
    }

    fun addNote(note: Note) {
        notes.add(note)
        notifyItemInserted(notes.size - 1)
    }

    class NotesListViewHolder(var view: View) : RecyclerView.ViewHolder(view) {

        var titleTv: TextView
        var subtitleTv: TextView
        var deleteIv: View
        var containerView: View

        init {
            titleTv = view.findViewById(R.id.titleTv)
            subtitleTv = view.findViewById(R.id.subtitleTv)
            deleteIv = view.findViewById(R.id.deleteIv)
            containerView = view.findViewById(R.id.container)
        }
    }
}