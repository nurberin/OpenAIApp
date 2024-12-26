package com.example.openaiapplication.data.repository

import com.example.openaiapplication.data.local.QnA
import com.example.openaiapplication.data.local.QnADao
import com.example.openaiapplication.data.remote.QnAApi
import com.example.openaiapplication.domain.repository.QnARepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class QnARepositoryImpl @Inject constructor(private val dao: QnADao, private val api: QnAApi) :
    QnARepository {
    override suspend fun askQuestion(question: String): String {
        val answer = api.getAnswer(question)
        val qna = QnA(question, answer)
        dao.insertQna(qna)
        return qna.id
    }

    override suspend fun getQnAById(id: String): QnA {
        return dao.getQnAById(id) ?: QnA("", "")
    }

    override suspend fun deleteQnAById(id: String) {
        return dao.deleteQnAById(id)
    }

    override fun getAllQnAs(): Flow<List<QnA>> {
        return dao.getAllQnAs()
    }
}