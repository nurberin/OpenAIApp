package com.example.openaiapplication.di

import android.content.Context
import androidx.room.Room
import com.example.openaiapplication.data.local.QnADao
import com.example.openaiapplication.data.local.QnADatabase
import com.example.openaiapplication.data.remote.QnAApi
import com.example.openaiapplication.data.repository.QnARepositoryImpl
import com.example.openaiapplication.domain.repository.QnARepository
import com.example.openaiapplication.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQnADatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, QnADatabase::class.java, Constants.DATABASE_NAME)

    @Provides
    @Singleton
    fun provideQnADao(qnADatabase: QnADatabase) = qnADatabase.qnaDao()

    @Provides
    @Singleton
    fun provideQnAApi(): QnAApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient()).build()
            .create(QnAApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRepository(dao: QnADao, api: QnAApi): QnARepository = QnARepositoryImpl(dao, api)
}