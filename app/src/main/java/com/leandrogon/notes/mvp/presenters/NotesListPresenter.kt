package com.leandrogon.notes.mvp.presenters

import com.leandrogon.notes.api.CustomDisposableObserver
import com.leandrogon.notes.api.NotesService
import com.leandrogon.notes.model.Note
import com.leandrogon.notes.model.responses.NotesListResponse
import com.leandrogon.notes.mvp.views.NotesListView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NotesListPresenter: BasePresenter<NotesListView>() {

    fun getNotes() {
        mvpView?.showProgressView()

        compositeSubscription?.add(NotesService.getNotes()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : CustomDisposableObserver<NotesListResponse>() {
                    override fun onNoInternetConnection() {
                        mvpView?.onNoInternetConnection()
                        mvpView?.hideProgressView()
                    }

                    override fun onObserverError(e: Throwable) {
                        mvpView?.onError(e)
                        mvpView?.hideProgressView()
                    }

                    override fun onErrorCode(code: Int, message: String) {
                        mvpView?.onErrorCode(message)
                        mvpView?.hideProgressView()
                    }

                    override fun onNext(t: NotesListResponse) {
                        mvpView?.onNotesResponse(t.notes)
                        mvpView?.hideProgressView()
                    }
                }))

    }
}