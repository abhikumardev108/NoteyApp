package com.example.noteyapp.roomdb

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "notes_table")
data class Note(

    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val color: Int


    // we have to use here color is of type Int, not the Color, because room doesn't supports complex types like Color and all.
)
