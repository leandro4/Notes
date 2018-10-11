package com.leandrogon.notes.ui.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.*
import com.leandrogon.notes.R
import com.leandrogon.notes.model.Note
import com.leandrogon.notes.mvp.presenters.NoteDetailPresenter
import com.leandrogon.notes.mvp.views.NoteDetailView
import com.leandrogon.notes.utils.Constants
import com.leandrogon.notes.utils.Constants.CREATE_NOTE
import com.leandrogon.notes.utils.Constants.EDIT_NOTE
import com.leandrogon.notes.utils.Constants.NOTE_DETAIL_MODE
import com.leandrogon.notes.utils.DialogUtil
import kotlinx.android.synthetic.main.fragment_note_detail.*

class NoteDetailFragment: BaseMvpFragment(), NoteDetailView {

    private var menu: Menu? = null
    private var note: Note? = null
    private var presenter: NoteDetailPresenter? = null

    private var createMode: Boolean = false

    var editMode: Boolean = false
    set(value) {
        field = value
        etTitle.isEnabled = value
        etContent.isEnabled = value
        setEditActionIcon()
    }

    private fun setEditActionIcon() {
        menu?.let {
            val icon = resources.getDrawable(if (editMode) R.drawable.ic_done_white_24dp else R.drawable.ic_edit_white_24dp)
            it.findItem(R.id.noteEditAction).icon = icon
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_note_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createMode = activity?.intent?.getIntExtra(NOTE_DETAIL_MODE, Constants.CREATE_NOTE) == Constants.CREATE_NOTE

        if (!createMode) {
            autocompleteForm()
            editMode = false
        }
    }

    override fun startPresenter() {
        presenter = NoteDetailPresenter()
        presenter?.attachMvpView(this)
    }

    private fun autocompleteForm() {
        note = activity?.intent?.getParcelableExtra(Constants.NOTE_DETAIL_EXTRA) as Note
        note?.let {
            etTitle.setText(it.title)
            etContent.setText(it.content)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, menuInflater: MenuInflater) {
        if (createMode) {
            menuInflater.inflate(R.menu.menu_create_note, menu)
        }
        else {
            menuInflater.inflate(R.menu.menu_detail_note, menu)
        }
        this.menu = menu
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {
            R.id.noteCreateAction -> {
                if (checkEmptyFields()) {
                    showNotEmptyAlert()
                } else {
                    createNote()
                }
                return true
            }
            R.id.noteEditAction -> {
                if (editMode) {
                    if (checkEmptyFields()) {
                        showNotEmptyAlert()
                    } else {
                        showConfirmUpdate()
                    }
                } else {
                    editMode = !editMode
                }
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showNotEmptyAlert() {
        DialogUtil.showGenericAlertDialog(context!!, getString(R.string.generic_warning_title), getString(R.string.empty_fields_title))
    }

    private fun createNote() {
        presenter?.createNote(Note(null, etTitle.text.toString(), etContent.text.toString()))
    }

    private fun showConfirmUpdate() {
        note?.title = etTitle.text.toString()
        note?.content = etContent.text.toString()
        val dialog = AlertDialog.Builder(context!!)
                .setTitle(resources.getString(R.string.update_note_title))
                .setMessage(resources.getString(R.string.update_note_message))
                .setNegativeButton(resources.getString(R.string.generic_cancel_button), null)
                .setPositiveButton(resources.getString(R.string.generic_accept_button)) { _, _ -> presenter?.updateNote(note!!) }.create()
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.show()
    }

    override fun onNoteCreated(note: Note) {
        finishActivityResultNote(note)
    }

    override fun onNoteUpdated(note: Note) {
        editMode = !editMode
        finishActivityResultNote(note)
    }

    private fun finishActivityResultNote(note: Note) {
        activity?.let {
            val intent = Intent()
            intent.putExtra(Constants.NOTE_DETAIL_EXTRA, note)
            it.setResult(Activity.RESULT_OK, intent)
            it.finish()
        }
    }

    override fun onErrorCode(message: String) {
        showErrorMessage(getString(R.string.generic_error_message))
    }

    private fun checkEmptyFields(): Boolean {
        if (etTitle.text.toString().trim().isEmpty()) return true
        if (etContent.text.toString().trim().isEmpty()) return true
        return false
    }
}