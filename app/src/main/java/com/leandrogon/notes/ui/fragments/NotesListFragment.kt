package com.leandrogon.notes.ui.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.leandrogon.notes.R
import com.leandrogon.notes.model.Note
import com.leandrogon.notes.mvp.presenters.NotesListPresenter
import com.leandrogon.notes.mvp.views.NotesListView
import com.leandrogon.notes.ui.activities.NoteDetailActivity
import com.leandrogon.notes.ui.adapters.NotesListAdapter
import com.leandrogon.notes.utils.Constants
import kotlinx.android.synthetic.main.fragment_notes_list.*

class NotesListFragment: BaseMvpFragment(), NotesListView, NotesListAdapter.NoteListener {

    var presenter: NotesListPresenter? = null
    var adapter: NotesListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_notes_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startPresenter()
        startUI()

        presenter?.getNotes()
        swipeRefresh.isRefreshing = true

        swipeRefresh.setOnRefreshListener {
            presenter?.getNotes()
        }
    }

    override fun startPresenter() {
        presenter = NotesListPresenter()
        presenter?.attachMvpView(this)
    }

    private fun startUI() {
        fabCreate.setOnClickListener(this::createNewNote)
    }

    private fun createNewNote(view: View) {
        val intent = Intent(context, NoteDetailActivity::class.java)
        intent.putExtra(Constants.NOTE_DETAIL_MODE, Constants.CREATE_NOTE)
        startActivityForResult(intent, Constants.REQUEST_CODE_CREATE_NOTE)
    }

    override fun onNotesResponse(notes: List<Note>) {
        emtpyView.visibility = if (notes.isEmpty()) View.VISIBLE else View.GONE
        rvNotes.layoutManager = LinearLayoutManager(context)
        adapter = NotesListAdapter(notes.toMutableList(), this)
        rvNotes.adapter = adapter
    }

    override fun hideProgressView() {
        super.hideProgressView()
        swipeRefresh.isRefreshing = false
    }

    override fun onViewClick(note: Note) {
        val intent = Intent(context, NoteDetailActivity::class.java)
        intent.putExtra(Constants.NOTE_DETAIL_MODE, Constants.EDIT_NOTE)
        intent.putExtra(Constants.NOTE_DETAIL_EXTRA, note)
        startActivityForResult(intent, Constants.REQUEST_CODE_EDIT_NOTE)
    }

    override fun onDeleteNoteClick(note: Note) {
        val dialog = AlertDialog.Builder(context!!)
                .setTitle(resources.getString(R.string.generic_warning_title))
                .setMessage(resources.getString(R.string.delete_note_message_dialog))
                .setNegativeButton(resources.getString(R.string.generic_cancel_button), null)
                .setPositiveButton(resources.getString(R.string.generic_accept_button)) { _,_ -> presenter?.deleteNote(note) }.create()
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.show()
    }

    override fun onNoteDeleted(note: Note) {
        adapter?.deleteNote(note)
    }

    override fun onErrorCode(message: String) {
        showErrorMessage(getString(R.string.generic_error_message))
    }

    override fun onDestroy() {
        presenter?.dettachMvpView()
        super.onDestroy()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            data?.let {
                when (requestCode) {
                    Constants.REQUEST_CODE_CREATE_NOTE -> {
                        val note = it.getParcelableExtra(Constants.NOTE_DETAIL_EXTRA) as Note
                        addNote(note)
                    }
                    Constants.REQUEST_CODE_EDIT_NOTE -> {
                        val note = it.getParcelableExtra(Constants.NOTE_DETAIL_EXTRA) as Note
                        updateNote(note)
                    }
                }
            }
        }
    }

    private fun updateNote(note: Note) {
        adapter?.updateNote(note)
    }

    private fun addNote(note: Note) {
        adapter?.addNote(note)
    }
}