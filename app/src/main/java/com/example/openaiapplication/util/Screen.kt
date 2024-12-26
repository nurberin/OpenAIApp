package com.example.openaiapplication.util

sealed class Screen(val route: String) {
    object QnaListScreen : Screen("qna_list_screen")
    object AskQuestionScreen : Screen("ask_question_screen")
    object QnaScreen : Screen("qna_screen")
}