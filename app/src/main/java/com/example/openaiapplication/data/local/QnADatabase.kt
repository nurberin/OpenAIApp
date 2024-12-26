package com.example.openaiapplication.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [QnA::class], version = 1)
abstract class QnADatabase : RoomDatabase() {

    abstract fun qnaDao(): QnADao
}