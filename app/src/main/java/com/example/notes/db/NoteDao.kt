package com.example.notes.db

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.notes.model.NoteModel

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNote(note: NoteModel)

    @Delete
    suspend fun deleteNote(note: NoteModel)

    @Update
    suspend fun updateNote(note: NoteModel)

    @Query("SELECT * FROM NoteModel ORDER BY id DESC")
    fun getAllNote() : LiveData<List<NoteModel>>

    @Query("SELECT * FROM NoteModel WHERE title LIKE :query ORDER BY id DESC")
    fun searchNote(query : String) : LiveData<List<NoteModel>>

}