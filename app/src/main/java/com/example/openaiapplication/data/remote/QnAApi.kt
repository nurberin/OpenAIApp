package com.example.openaiapplication.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface QnAApi {

    @GET("qna")
    suspend fun getAnswer(
        @Query("q") question: String
    ): String
}