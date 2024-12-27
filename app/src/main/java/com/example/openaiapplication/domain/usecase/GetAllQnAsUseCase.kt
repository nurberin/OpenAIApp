package com.example.openaiapplication.domain.usecase

import com.example.openaiapplication.domain.repository.QnARepository
import javax.inject.Inject

class GetAllQnAsUseCase @Inject constructor(
    private val repository: QnARepository
) {
    operator fun invoke() = repository.getAllQnAs()
}