package com.leandrogon.notes.api

import com.leandrogon.notes.model.Note
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface NotesApi {

    @GET(UtilUrl.NOTES)
    fun getNotes(@Body note: Note) : Observable<List<Note>>

    @PUT(UtilUrl.NOTE)
    fun updateNote(@Path("noteId") noteId: String, @Body note: Note) : Single<Note>

    @POST(UtilUrl.NOTES)
    fun createNote(@Body note: Note) : Single<Note>

    @DELETE(UtilUrl.NOTE)
    fun deleteNote(@Path("noteId") noteId: String) : Single<Note>
}