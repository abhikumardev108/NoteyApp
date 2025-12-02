package com.example.noteyapp.roomdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.concurrent.Volatile


@Database(entities = [Note::class], version = 1)
abstract class NotesDB : RoomDatabase() {


    // creating here, singleton companion instance of db.
    // volatile -> prevents any race condition in multithreading.


    abstract val notesDao: NoteDao


    companion object {


        @Volatile
        private var INSTANCE: NotesDB? = null   // at staring, database is empty..


        fun getInstance(context: Context): NotesDB {

            // synchronized -->  this ensures that,only one thread can execute the block at the code that is inside the synchronized, at the point of time.
            synchronized(this) {

                var instance = INSTANCE
                if (instance == null) {

                    // creating the db object
                    instance = Room.databaseBuilder(
                        context = context.applicationContext,
                        klass = NotesDB::class.java,
                        name = "notes_db"
                    ).build()
                }
                INSTANCE = instance
                return instance
            }
        }

    }
}