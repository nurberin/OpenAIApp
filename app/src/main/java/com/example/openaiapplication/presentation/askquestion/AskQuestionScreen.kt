package com.example.openaiapplication.presentation.askquestion

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.openaiapplication.presentation.components.TextBubble
import com.example.openaiapplication.util.Screen

@Composable
fun AskQuestionScreen(
    navController: NavController,
    viewModel: AskQuestionViewModel = hiltViewModel()
) {

    val context = LocalContext.current
    val focus = LocalFocusManager.current

    LaunchedEffect(key1 = viewModel.event.value) {
        val value = viewModel.event.value
        if (value == "An error occured") {
            Toast.makeText(context, value, Toast.LENGTH_LONG).show()
            navController.navigateUp()
        } else if (value != "Loading" && value != "") {
            navController.navigate(Screen.QnaScreen.route + "/id=${value}")
        }
    }

    Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        TextBubble(fromUser = false, text = "Hello, how can I help you?")

        if (viewModel.event.value == "Loading") {
            TextBubble(fromUser = true, text = viewModel.question.value)
            Spacer(modifier = Modifier.height(16.dp))
            CircularProgressIndicator(color = Color.Black)
        }

        if (viewModel.event.value == "") {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    androidx.compose.material.TextField(
                        value = viewModel.question.value,
                        onValueChange = viewModel::onQuestionChanged,
                        Modifier
                            .fillMaxWidth(0.9f)
                            .padding(2.dp),
                        colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.White),
                        label = {
                            Text(text = "Explain me... What is...")
                        }
                    )
                    Icon(imageVector = Icons.Default.Send, contentDescription = "Send",
                        modifier = Modifier.clickable(enabled = viewModel.event.value != "Loading" && viewModel.question.value.isNotBlank()) {
                            viewModel.onAsk()
                            focus.clearFocus()
                        })
                }
            }
        }
    }
}