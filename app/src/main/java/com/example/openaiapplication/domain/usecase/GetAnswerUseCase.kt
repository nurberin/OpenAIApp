package com.example.openaiapplication.domain.usecase

import com.example.openaiapplication.domain.repository.QnARepository
import com.example.openaiapplication.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetAnswerUseCase @Inject constructor(
    private val repository: QnARepository
) {

    operator fun invoke(question: String): Flow<Resource<String>> = flow {
        try {
            emit(Resource.Loading<String>())
            val response = repository.askQuestion(question)
            emit(Resource.Success<String>(response))
        } catch (e: HttpException) {
            emit(Resource.Error<String>(e.localizedMessage ?: "An unknown error occured"))
        } catch (e: IOException) {
            emit(Resource.Error<String>("Could not reach the server"))
        }

    }
}