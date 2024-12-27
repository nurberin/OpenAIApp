package com.example.openaiapplication.presentation.qna

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.openaiapplication.presentation.components.TextBubble
import com.example.openaiapplication.util.Screen

@Composable
fun QnaScreen(navController: NavController, id: String, viewModel: QnAViewModel = hiltViewModel()) {

    LaunchedEffect(key1 = id) {
        viewModel.submitId(id)
    }

    LazyColumn(Modifier.fillMaxSize()) {
        viewModel.qna.value?.let {
            item {
                TextBubble(fromUser = false, text = "Hello, how can I help you?")
                TextBubble(fromUser = true, text = it.question)
                TextBubble(fromUser = false, text = it.answer)
            }
        }
    }

    BackHandler {
        navController.popBackStack()
        navController.navigate(Screen.QnaListScreen.route)
    }
}