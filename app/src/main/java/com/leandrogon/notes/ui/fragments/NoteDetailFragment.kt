package com.leandrogon.notes.ui.fragments

import android.os.Bundle
import android.view.*
import android.widget.Toast
import com.leandrogon.notes.R
import kotlinx.android.synthetic.main.fragment_note_detail.*

class NoteDetailFragment: BaseMvpFragment() {

    var editMode: Boolean = false
    set(value) {
        etTitle.isEnabled = value
        etContent.isEnabled = value
        field = value
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return LayoutInflater.from(context).inflate(R.layout.fragment_note_detail, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu?, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.menu_create_note, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == R.id.createNote) {
            
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onErrorCode(message: String) {

    }
}