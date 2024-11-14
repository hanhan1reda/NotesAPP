package com.example.noteapp2

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.example.noteapp2.data.db.Note

class NoteDetailsScreen(val note: Note, val noteViewModel: NoteViewModel) : Screen {
    @Composable
    override fun Content() {
        val title = remember { mutableStateOf(note.title) }
        val description = remember { mutableStateOf(note.description) }
        val navigator = LocalNavigator.currentOrThrow

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                OutlinedTextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    placeholder = {
                        Text(
                            text = "Add Title",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4A3F71)
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    placeholder = {
                        Text(
                            text = "Add Description",
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4A3F71)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    shape = RoundedCornerShape(12.dp)
                )

                Spacer(modifier = Modifier.height(20.dp))


                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Button(
                        onClick = {
                            noteViewModel.updateNote(note.copy(title = title.value, description = description.value))
                            navigator.pop()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF7c62a5)
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 8.dp),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(text = "Update", fontSize = 16.sp)
                    }

                    Button(
                        onClick = {
                            noteViewModel.deleteNote(note)
                            navigator.pop()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFda889e)
                        ),
                        modifier = Modifier
                            .weight(1f)
                            .padding(start = 8.dp),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Text(text = "Delete", fontSize = 16.sp)
                    }
                }
            }
        }
    }
}
