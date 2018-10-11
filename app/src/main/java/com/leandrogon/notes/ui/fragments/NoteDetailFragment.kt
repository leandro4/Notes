package com.leandrogon.notes.ui.fragments

import android.os.Bundle
import android.view.*
import com.leandrogon.notes.R
import com.leandrogon.notes.utils.Constants
import com.leandrogon.notes.utils.Constants.CREATE_NOTE
import com.leandrogon.notes.utils.Constants.EDIT_NOTE
import com.leandrogon.notes.utils.Constants.NOTE_DETAIL_MODE
import kotlinx.android.synthetic.main.fragment_note_detail.*

class NoteDetailFragment: BaseMvpFragment() {

    private var menu: Menu? = null

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

    override fun onCreateOptionsMenu(menu: Menu?, menuInflater: MenuInflater) {
        val mode = activity?.intent?.getIntExtra(NOTE_DETAIL_MODE, Constants.CREATE_NOTE)
        when (mode) {
            CREATE_NOTE -> menuInflater.inflate(R.menu.menu_create_note, menu)
            EDIT_NOTE -> menuInflater.inflate(R.menu.menu_detail_note, menu)
        }
        this.menu = menu
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {
            R.id.noteCreateAction -> {
                // TODO: create note
                return true
            }
            R.id.noteEditAction -> {
                editMode = !editMode
            }
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onErrorCode(message: String) {

    }
}