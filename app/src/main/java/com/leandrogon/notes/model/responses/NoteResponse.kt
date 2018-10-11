package com.leandrogon.notes.model.responses

import com.leandrogon.notes.model.Note
import com.squareup.moshi.Json

data class NoteResponse(@Json(name = "data") var note: Note)