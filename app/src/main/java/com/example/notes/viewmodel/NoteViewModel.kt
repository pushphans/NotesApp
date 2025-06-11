package com.example.notes.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notes.model.NoteModel
import com.example.notes.repository.NoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(private val repo : NoteRepository) : ViewModel() {

    fun insertNote(note : NoteModel){
        viewModelScope.launch(Dispatchers.IO){
            repo.insertNote(note)
        }
    }

    fun deleteNote(note : NoteModel){
        viewModelScope.launch(Dispatchers.IO){
            repo.deleteNote(note)
        }
    }

    fun updateNote(note : NoteModel){
        viewModelScope.launch(Dispatchers.IO){
            repo.updateNote(note)
        }
    }

    fun getAllNote() : LiveData<List<NoteModel>>{
        return repo.getAllNote()
    }

    fun searchNote(query : String) : LiveData<List<NoteModel>>{
        return repo.searchNote("%$query%")
    }

}