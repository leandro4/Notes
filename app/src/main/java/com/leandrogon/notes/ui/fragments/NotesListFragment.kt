package com.leandrogon.notes.ui.fragments

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.leandrogon.notes.R
import com.leandrogon.notes.model.Note
import com.leandrogon.notes.mvp.presenters.NotesListPresenter
import com.leandrogon.notes.mvp.views.NotesListView
import com.leandrogon.notes.ui.adapters.NotesListAdapter
import kotlinx.android.synthetic.main.fragment_notes_list.*

class NotesListFragment: BaseMvpFragment(), NotesListView, NotesListAdapter.NoteListener {

    var presenter: NotesListPresenter = NotesListPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_notes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setProgressView()
        presenter.attachMvpView(this)
        presenter.getNotes()
    }

    private fun setProgressView() {
        progressBar = progressView
    }

    override fun onNotesResponse(notes: List<Note>) {
        emtpyView.visibility = if (notes.isEmpty()) View.VISIBLE else View.GONE
        rvNotes.layoutManager = LinearLayoutManager(context)
        rvNotes.adapter = NotesListAdapter(notes.toMutableList(), this)
    }

    override fun onViewClick(note: Note) {

    }

    override fun onDeleteNoteClick(note: Note) {

    }

    override fun onNoteDeleted(noteId: String) {

    }

    override fun onErrorCode(message: String) {
    }

    override fun onDestroy() {
        presenter.dettachMvpView()
        super.onDestroy()
    }
}