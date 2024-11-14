package com.example.noteapp2

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
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
import com.example.noteapp2.ui.theme.NoteApp2Theme

class MainScreen(val noteViewModel: NoteViewModel) : Screen {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val selectedIndex = remember { mutableStateOf(0) }
        val showAddDialog = remember { mutableStateOf(false) }
        val navigator = LocalNavigator.currentOrThrow

        NoteApp2Theme {
            Scaffold(
                floatingActionButton = {
                    FloatingActionButton(
                        onClick = {
                            showAddDialog.value = true
                        },
                        containerColor = Color(0xFFffb3a8),
                        contentColor = Color.White
                    ) {
                        Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                    }
                },
                topBar = {
                    TopAppBar(
                        actions = {
                            IconButton(onClick = {  }) {
                                Icon(imageVector = Icons.Default.Notifications,contentDescription = null)
                            }
                        },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF9178b9)),
                        title = {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Spacer(modifier = Modifier.height(11.dp))
                                Text(
                                    text = " My Notes",
                                    fontSize = 23.sp,
                                    color = Color(0xFFffffff),
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(bottom = 4.dp)
                                )
                            }
                        },
                        modifier = Modifier.height(52.dp)
                    )
                },
                bottomBar = {
                    BottomAppBar(containerColor = Color(0xFFcac5da)) {
                        IconButton(modifier = Modifier.weight(1f), onClick = { selectedIndex.value = 0 }) {
                            Icon(
                                imageVector = Icons.Filled.Home,
                                contentDescription = null,
                                tint = if (selectedIndex.value == 0) Color(0xFFe78e8e) else Color(0xFF888888)
                            )
                        }
                        IconButton(modifier = Modifier.weight(1f), onClick = { selectedIndex.value = 1 }) {
                            Icon(
                                imageVector = Icons.Default.Favorite,
                                contentDescription = null,
                                tint = if (selectedIndex.value == 1) Color(0xFFe78e8e) else Color(0xFF888888)
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxSize()
            ) { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .padding(top = 8.dp)
                ) {
                    if (showAddDialog.value) {
                        AddNoteDialog(
                            onSaveClick = { title, description ->
                                noteViewModel.insertNote(Note(title = title, description = description, isFavorite = false))
                                showAddDialog.value = false
                            },
                            onDismissRequest = {
                                showAddDialog.value = false
                            }
                        )
                    }
                    when (selectedIndex.value) {
                        0 -> HomeScreenContent(noteViewModel = noteViewModel)
                        1 -> FavoriteScreenContent(noteViewModel = noteViewModel)
                    }
                }
            }
        }
    }
}

