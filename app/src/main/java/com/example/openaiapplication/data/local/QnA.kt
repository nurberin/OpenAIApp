package com.example.openaiapplication.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "qnas")
data class QnA(
    val question: String,
    val answer: String,
    @PrimaryKey
    val id: String = UUID.randomUUID().toString()
)
