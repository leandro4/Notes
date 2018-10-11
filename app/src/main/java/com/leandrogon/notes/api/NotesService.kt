package com.leandrogon.notes.api

import com.leandrogon.notes.model.Note
import com.leandrogon.notes.model.responses.DeleteNoteResponse
import com.leandrogon.notes.model.responses.NoteResponse
import com.leandrogon.notes.model.responses.NotesListResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

object NotesService {

    var notesApi: NotesApi

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(UtilUrl.BASE_URL)
                .client(createOkHttpClientInterceptor())
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

        notesApi = retrofit.create(NotesApi::class.java)
    }

    private fun createOkHttpClientInterceptor(): OkHttpClient {
        val okHttpClient = OkHttpClient().newBuilder()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        okHttpClient.addInterceptor(interceptor)
        return okHttpClient.build()
    }

    fun getNotes(): Observable<NotesListResponse> {
        return notesApi.getNotes()
    }

    fun updateNote(note: Note): Observable<NoteResponse> {
        return notesApi.updateNote(note.id!!, note)
    }

    fun createNote(note: Note): Observable<NoteResponse> {
        return notesApi.createNote(note)
    }

    fun deleteNote(noteId: String): Observable<DeleteNoteResponse> {
        return notesApi.deleteNote(noteId)
    }
}