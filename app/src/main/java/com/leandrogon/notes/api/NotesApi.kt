package com.leandrogon.notes.api

import com.leandrogon.notes.model.Note
import com.leandrogon.notes.model.responses.DeleteNoteResponse
import com.leandrogon.notes.model.responses.NoteResponse
import com.leandrogon.notes.model.responses.NotesListResponse
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.*

interface NotesApi {

    @GET(UtilUrl.NOTES)
    fun getNotes() : Observable<NotesListResponse>

    @PUT(UtilUrl.NOTE)
    fun updateNote(@Path("noteId") noteId: String, @Body note: Note) : Observable<NoteResponse>

    @POST(UtilUrl.NOTES)
    fun createNote(@Body note: Note) : Observable<NoteResponse>

    @DELETE(UtilUrl.NOTE)
    fun deleteNote(@Path("noteId") noteId: String) : Observable<DeleteNoteResponse>
}