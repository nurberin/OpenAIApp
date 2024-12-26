package com.example.openaiapplication.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QnADao {
    @Insert
    suspend fun insertQna(qnQ: QnA)

    @Query("DELETE FROM qnas WHERE id = :id")
    suspend fun deleteQnAById(id: String)

    @Query("SELECT * FROM qnas WHERE id = :id")
    suspend fun getQnAById(id: String): QnA?

    @Query("SELECT * FROM qnas")
    fun getAllQnAs(): Flow<List<QnA>>
}