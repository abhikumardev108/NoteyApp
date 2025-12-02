package com.example.noteyapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.noteyapp.repository.NotesRepository


class NoteViewModelFactory(private val repository: NotesRepository): ViewModelProvider.Factory {

    // if our viewmodel requires additional parameters such as repository or context,
    // in that case, we need to create viewmodelFactory for that to instantiate.

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(NoteViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository) as T
        }
        // You must handle the case where the ViewModel is not found
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}