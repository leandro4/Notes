package com.leandrogon.notes.mvp.views

import com.leandrogon.notes.model.Note

interface NoteDetailView: BaseView {

    fun onNoteCreated(note: Note)

    fun onNoteUpdated(note: Note)

}