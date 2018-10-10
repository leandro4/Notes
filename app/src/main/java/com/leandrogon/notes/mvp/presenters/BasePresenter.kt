package com.leandrogon.notes.mvp.presenters

import com.leandrogon.notes.mvp.views.BaseView
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<T: BaseView> : Presenter<T> {

    protected var mvpView: T? = null
    protected var compositeSubscription: CompositeDisposable? = null

    override fun attachMvpView(view: T) {
        this.mvpView = view
        this.compositeSubscription = CompositeDisposable()
    }

    override fun dettachMvpView() {
        this.mvpView = null
        compositeSubscription?.let {
            it.clear()
        }
    }
}