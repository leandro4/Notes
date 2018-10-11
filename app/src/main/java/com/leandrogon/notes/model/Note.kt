package com.leandrogon.notes.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Note (var id: String?, var title: String?, var content: String?): Parcelable