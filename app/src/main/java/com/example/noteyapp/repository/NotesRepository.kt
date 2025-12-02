package com.example.noteyapp.repository

import androidx.lifecycle.LiveData
import com.example.noteyapp.roomdb.Note
import com.example.noteyapp.roomdb.NoteDao


class NotesRepository(private val noteDao: NoteDao) {

    val allNotes: LiveData<List<Note>> = noteDao.getAllData()


    suspend fun insertNote(note: Note) {
        return noteDao.insert(note)
    }




}