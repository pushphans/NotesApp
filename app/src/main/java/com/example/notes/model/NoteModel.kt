package com.example.notes.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class NoteModel(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val content : String,
) : Parcelable
