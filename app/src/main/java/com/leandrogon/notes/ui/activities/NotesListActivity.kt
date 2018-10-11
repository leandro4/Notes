package com.leandrogon.notes.ui.activities

import android.os.Bundle
import com.leandrogon.notes.R
import com.leandrogon.notes.ui.fragments.NotesListFragment

class NotesListActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setToolbarTitle(R.string.notes_list_title)

        val fragment = NotesListFragment()
        fragment.activityProgress = this
        loadFragment(fragment)
    }
}