package com.example.openaiapplication.domain.usecase

import com.example.openaiapplication.domain.repository.QnARepository
import javax.inject.Inject

class DeleteQnAByIdUseCase @Inject constructor(
    private val repository: QnARepository
) {
   suspend operator fun invoke(id:String) = repository.deleteQnAById(id)
}