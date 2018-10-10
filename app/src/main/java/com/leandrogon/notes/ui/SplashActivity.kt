package com.leandrogon.notes.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import com.leandrogon.notes.R
import com.leandrogon.notes.ui.activities.BaseActivity
import com.leandrogon.notes.ui.activities.NotesListActivity
import com.leandrogon.notes.ui.fragments.NotesListFragment

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Handler().postDelayed( {
            startActivity(Intent(this, NotesListActivity::class.java))
            finish()
        }, 1500)
    }
}