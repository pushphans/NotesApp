package com.example.notes.repository

import androidx.lifecycle.LiveData
import com.example.notes.db.NoteDatabase
import com.example.notes.model.NoteModel

class NoteRepository(private val database: NoteDatabase) {

    suspend fun insertNote(note: NoteModel) = database.getDao().insertNote(note)

    suspend fun deleteNote(note: NoteModel) = database.getDao().deleteNote(note)

    suspend fun updateNote(note: NoteModel) = database.getDao().updateNote(note)

    fun getAllNote(): LiveData<List<NoteModel>> {
        return database.getDao().getAllNote()
    }

    fun searchNote(query : String) : LiveData<List<NoteModel>>{
        return database.getDao().searchNote(query)
    }
}