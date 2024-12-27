package com.example.openaiapplication.presentation.qnalist

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openaiapplication.data.local.QnA
import com.example.openaiapplication.domain.usecase.DeleteQnAByIdUseCase
import com.example.openaiapplication.domain.usecase.GetAllQnAsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QnAListViewModel @Inject constructor(
    private val getAllQnAsUseCase: GetAllQnAsUseCase,
    private val deleteQnAByIdUseCase: DeleteQnAByIdUseCase
) : ViewModel() {

    private val _qnas = mutableStateOf<List<QnA>>(emptyList())
    val qnas: State<List<QnA>> = _qnas

    init {
        viewModelScope.launch {
            getAllQnAsUseCase().collect {
                _qnas.value = it
            }
        }
    }

    fun onDeleteQnA(id:String){
        viewModelScope.launch {
            deleteQnAByIdUseCase(id)
        }
    }
}