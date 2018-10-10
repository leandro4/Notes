package com.leandrogon.notes.api

import com.leandrogon.notes.model.Note
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

    fun getNotes(): Single<List<Note>> {
        return notesApi.getNotes()
    }

    fun updateNote(note: Note): Single<Note> {
        return notesApi.updateNote(note.id!!, note)
    }

    fun createNote(note: Note): Single<Note> {
        return notesApi.createNote(note)
    }

    fun deleteNote(noteId: String): Single<Note> {
        return notesApi.deleteNote(noteId)
    }
}