package com.example.noteyapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt
import androidx.lifecycle.ViewModelProvider
import com.example.noteyapp.repository.NotesRepository
import com.example.noteyapp.roomdb.Note
import com.example.noteyapp.roomdb.NotesDB
import com.example.noteyapp.screens.DisplayNotesList
import com.example.noteyapp.ui.theme.NoteyAppTheme
import com.example.noteyapp.viewmodel.NoteViewModel
import com.example.noteyapp.viewmodel.NoteViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        // Room db
        val database = NotesDB.getInstance(applicationContext)

        // Repository
        val repository = NotesRepository(database.notesDao)

        // viewModelFactory
        val viewModelFactory = NoteViewModelFactory(repository)

        // viewModel
        val noteViewModel = ViewModelProvider(
            this,
            viewModelFactory
        )[NoteViewModel::class.java]

//
//        val note1 = Note(
//            0,
//            "This is demo notes",
//            "My sona is very cute and beautiful girl.",
//            "#f59597".toColorInt()
//        )
//
//        // inserting notes into a database.
//        noteViewModel.insert(note1)



        setContent {
            NoteyAppTheme {

                // display all records in room database
                val notes by noteViewModel.allNotes.observeAsState(emptyList())


                DisplayNotesList(notes, noteViewModel)
            }
        }
    }
}

