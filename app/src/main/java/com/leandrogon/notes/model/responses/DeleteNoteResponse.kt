package com.leandrogon.notes.model.responses

import com.squareup.moshi.Json

data class DeleteNoteResponse(@Json(name = "data") val  data: Boolean)