package com.example.openaiapplication.presentation.askquestion

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openaiapplication.domain.usecase.GetAnswerUseCase
import com.example.openaiapplication.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AskQuestionViewModel @Inject constructor(private val getAnswerUseCase: GetAnswerUseCase) :
    ViewModel() {

    private val _question = mutableStateOf("")
    val question: State<String> = _question


    private val _event = mutableStateOf("")
    val event: State<String> = _event


    fun onQuestionChanged(question:String){
        _question.value = question
    }

    fun onAsk(){
        viewModelScope.launch {
            getAnswerUseCase(question.value).onEach {
                when(it){
                    is Resource.Error -> {
                        _event.value = "An error occured"
                    }
                    is Resource.Loading -> {
                        _event.value = "Loading"
                    }
                    is Resource.Success -> {
                        _event.value = it.data ?: "An error occured"
                    }
                }
            }.launchIn(viewModelScope)
        }
    }
}