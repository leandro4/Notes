package com.leandrogon.notes.ui.activities

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.leandrogon.notes.R
import kotlinx.android.synthetic.main.activity_base_content.*

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_content)
        setSupportActionBar(toolbar)
    }

    protected fun loadFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fragmentContent, fragment)
        transaction.commit()
    }

    protected fun setToolbarTitle(resTitleId: Int) {
        toolbar.title = getString(resTitleId)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}