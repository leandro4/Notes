package com.leandrogon.notes.ui.activities

import android.os.Bundle
import com.leandrogon.notes.ui.fragments.NoteDetailFragment

class NoteDetailActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.setDisplayHomeAsUpEnabled(true);
        supportActionBar?.setDisplayShowHomeEnabled(true);

        val fragment = NoteDetailFragment()
        fragment.activityProgress = this
        loadFragment(fragment)
    }
}