package com.example.noteyapp.roomdb

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface NoteDao {

    // here we are defining methods for various db operations.


    @Insert
    suspend fun insert(note: Note)


    @Query("SELECT * FROM notes_table")  // query -> it executes a custom query, for complex query
    fun getAllData() : LiveData<List<Note>>



}