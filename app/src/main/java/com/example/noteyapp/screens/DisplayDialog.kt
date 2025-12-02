package com.example.noteyapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.noteyapp.roomdb.Note
import com.example.noteyapp.viewmodel.NoteViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayDialog(
    noteViewModel: NoteViewModel,
    showDialog: Boolean,
    onDismiss: () -> Unit
) {

    val context = LocalContext.current

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var selectedColor by remember { mutableStateOf(Color.Blue) }


    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,

            title = { Text(text = "Enter Note") },

            text = {
                Column() {
                    TextField(
                        value = title,
                        onValueChange = {
                            title = it
                        },
                        label = {
                            Text(text = "Note Title")
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    TextField(
                        value = description,
                        onValueChange = {
                            description = it
                        },
                        label = {
                            Text(text = "Note Description")
                        }
                    )


                    Spacer(modifier = Modifier.height(16.dp))

                    // color picker compose here..
                    ColorPicker(
                        selectedColor = selectedColor,
                        onColorSelected = {
                            selectedColor = it
                        }
                    )

                }
            },

            confirmButton = {
                Button(onClick = {

                    if(title.isNotBlank() && description.isNotBlank()) {

                        val note = Note(
                            0,
                            title,
                            description,
                            selectedColor.toArgb()    // toArgb --> converts color object to an integer.
                        )

                        // insert note into DB
                        noteViewModel.insert(note)


                        title = ""
                        description = ""

                        onDismiss()

                    } else {
                        Toast.makeText(context, "enter a valid data.!!", Toast.LENGTH_SHORT).show()
//                        onDismiss()
                    }

                }){
                    Text("save")
                }
            },


            dismissButton = {
                Button(onClick = onDismiss) {
                    Text(text = "cancel")
                }
            }

        )
    }
}