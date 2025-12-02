package com.example.noteyapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteyapp.repository.NotesRepository
import com.example.noteyapp.roomdb.Note
import kotlinx.coroutines.launch


class NoteViewModel(private val repository: NotesRepository) : ViewModel() {


    val allNotes: LiveData<List<Note>> = repository.allNotes


    fun insert(note: Note) {
        viewModelScope.launch {
            repository.insertNote(note)
        }
    }


}