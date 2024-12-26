package com.example.openaiapplication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.openaiapplication.presentation.askquestion.AskQuestionScreen
import com.example.openaiapplication.presentation.qna.QnaScreen
import com.example.openaiapplication.presentation.qnalist.QnAListScreen
import com.example.openaiapplication.util.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Screen.QnaListScreen.route) {

        composable(Screen.QnaListScreen.route) {
            QnAListScreen(navController)
        }

        composable(Screen.AskQuestionScreen.route) {
            AskQuestionScreen(navController)
        }

        composable(Screen.QnaScreen.route + "/id={id}") { navBackStackEntry ->
            QnaScreen(navController, navBackStackEntry.arguments?.getString("id")!!)
        }

    }
}
