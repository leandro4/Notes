package com.leandrogon.notes.mvp.views

import com.leandrogon.notes.model.Note

interface NotesListView: BaseView {

    fun onNotesResponse(notes: List<Note>)
    fun onNoteDeleted(note: Note)

}