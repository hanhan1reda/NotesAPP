package com.example.noteapp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.Navigator
import com.example.noteapp2.ui.theme.NoteApp2Theme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val noteViewModel = NoteViewModel(application)

        setContent {
            Navigator(screen = MainScreen(noteViewModel))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddNoteDialog(onSaveClick: (String, String) -> Unit, onDismissRequest: () -> Unit) {
    val title = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        icon = {
            Icon(
                Icons.Default.Close,
                contentDescription = null,
                tint = Color(0xFFe78e8e),
                modifier = Modifier
                    .padding(8.dp)
                    .clickable { onDismissRequest() }
            )
        },
        confirmButton = {
            Button(
                onClick = { onSaveClick(title.value, description.value) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFe3dbfa))
            ) {
                Text(text = "Save Note", color = Color(0xFF5b4a61), fontSize = 16.sp)
            }
        },
        title = {
            Text(
                text = "Add New Note",
                fontSize = 20.sp,
                color = Color(0xFFe78e8e),
                modifier = Modifier.padding(bottom = 8.dp)
            )
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = title.value,
                    onValueChange = { title.value = it },
                    placeholder = { Text(text = "Add Title", color = Color(0xFF888888)) },
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFe78e8e),
                        unfocusedBorderColor = Color(0xFF888888)
                    )
                )
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedTextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    placeholder = { Text(text = "Add Description", color = Color(0xFF888888)) },
                    modifier = Modifier.height(120.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color(0xFFe78e8e),
                        unfocusedBorderColor = Color(0xFF888888)
                    )
                )
            }
        },
        containerColor = Color(0xFFf7f0fa)
    )
}

@Preview(showBackground = true)
@Composable
fun AddNoteDialogPreview() {
    NoteApp2Theme {
        AddNoteDialog(onSaveClick = { _, _ -> }, onDismissRequest = {})
    }
}
