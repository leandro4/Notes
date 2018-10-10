package com.leandrogon.notes.ui.fragments

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AlertDialog
import android.view.View
import com.leandrogon.notes.R
import com.leandrogon.notes.mvp.presenters.BasePresenter
import com.leandrogon.notes.mvp.presenters.Presenter
import com.leandrogon.notes.mvp.views.BaseView

abstract class BaseMvpFragment: Fragment(), BaseView {

    //protected lateinit var presenter

    var progressBar: View? = null

    protected fun showErrorMessage(message: String) {
        context?.let {
            AlertDialog.Builder(it)
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok) { dialog, _ -> dialog.dismiss() }
                    .show()
        }
    }

    override fun getMvpContext(): Context? {
        return context
    }

    override fun onError(var1: Throwable) {
        showErrorMessage(var1.message?: run { getString(R.string.warning_generic_error) })
    }

    override fun onNoInternetConnection() {
        showErrorMessage(getString(R.string.warning_no_conection))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //presenter.attachMvpView(this)
    }

    override fun onDestroy() {
        //presenter.dettachMvpView()
        super.onDestroy()
    }

    override fun showProgressView() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgressView() {
        progressBar?.visibility = View.GONE
    }
}