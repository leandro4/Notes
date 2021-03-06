package com.leandrogon.notes.model.responses

import com.leandrogon.notes.model.Note
import com.squareup.moshi.Json

data class NotesListResponse(@Json(name = "data") var notes: List<Note>)