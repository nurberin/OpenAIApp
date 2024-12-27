package com.example.openaiapplication.presentation.qnalist

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QuestionAnswer
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.openaiapplication.util.Screen

@Composable
fun QnAListScreen(navController: NavController, viewModel: QnAListViewModel = hiltViewModel()) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AskQuestionScreen.route)
            }, backgroundColor = Color.Black) {
                Icon(
                    imageVector = Icons.Default.QuestionAnswer,
                    contentDescription = "Ask a question",
                    tint = Color.White
                )
            }
        }
    ) {
        LazyColumn(Modifier.padding(it)) {
            if (viewModel.qnas.value.isNotEmpty()) {
                items(viewModel.qnas.value) {
                    QnAListItem(qnA = it, onClick = {
                        navController.navigate(Screen.QnaScreen.route + "/id=${it.id}")
                    },
                        onDelete = {
                            viewModel.onDeleteQnA(it.id)
                        }
                    )
                }
            } else {
                item {
                    Text(
                        text = "Hello Im your android assistant. How can I help you? \n" +
                                "You can ask me any kotlin or android question",
                        style = MaterialTheme.typography.h6, textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}