package com.example.openaiapplication.domain.repository

import com.example.openaiapplication.data.local.QnA
import kotlinx.coroutines.flow.Flow

interface QnARepository {
    suspend fun askQuestion(question: String): String

    suspend fun getQnAById(id: String): QnA

    suspend fun deleteQnAById(id: String)

    fun getAllQnAs(): Flow<List<QnA>>
}

