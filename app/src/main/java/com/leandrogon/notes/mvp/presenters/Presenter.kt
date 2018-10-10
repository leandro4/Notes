package com.leandrogon.notes.mvp.presenters

interface Presenter<MvpView> {

    fun attachMvpView(view: MvpView)

    fun dettachMvpView()
}