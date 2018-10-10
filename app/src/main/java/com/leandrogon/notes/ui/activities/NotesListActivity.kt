package com.leandrogon.notes.ui.activities

import android.os.Bundle
import com.leandrogon.notes.ui.fragments.NotesListFragment

class NotesListActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadFragment(NotesListFragment())
    }
}