package com.example.openaiapplication.presentation.qna

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openaiapplication.data.local.QnA
import com.example.openaiapplication.domain.usecase.GetQnAUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QnAViewModel @Inject constructor(private val getQnAUseCase: GetQnAUseCase) : ViewModel() {

    private val _qna = mutableStateOf<QnA?>(null)
    val qna: State<QnA?> = _qna

    fun submitId(id:String){
        viewModelScope.launch {
            _qna.value = getQnAUseCase(id)
        }
    }

}