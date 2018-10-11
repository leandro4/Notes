package com.leandrogon.notes.mvp.presenters

import com.leandrogon.notes.api.CustomDisposableObserver
import com.leandrogon.notes.api.NotesService
import com.leandrogon.notes.model.Note
import com.leandrogon.notes.model.responses.NoteResponse
import com.leandrogon.notes.mvp.views.NoteDetailView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NoteDetailPresenter: BasePresenter<NoteDetailView>() {

    fun createNote(note: Note) {
        mvpView?.showProgressView()

        compositeSubscription?.add(NotesService.createNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : CustomDisposableObserver<NoteResponse>() {
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

                    override fun onNext(t: NoteResponse) {
                        mvpView?.onNoteCreated(t.note)
                        mvpView?.hideProgressView()
                    }
                })
        )
    }

    fun updateNote(note: Note) {
        mvpView?.showProgressView()

        compositeSubscription?.add(NotesService.updateNote(note)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : CustomDisposableObserver<NoteResponse>() {
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

                    override fun onNext(t: NoteResponse) {
                        mvpView?.onNoteUpdated(note)
                        mvpView?.hideProgressView()
                    }
                })
        )
    }
}