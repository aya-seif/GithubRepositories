package com.example.githubrepoviewer.di

import android.content.Context
import androidx.room.Room
import com.example.githubrepoviewer.data.local.RepositoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideRepositoryDatabase(@ApplicationContext context: Context) =
        Room.databaseBuilder(
            context,
            RepositoryDatabase::class.java,
            "repositoryDatabase"
        ).build()

    @Singleton
    @Provides
    fun provideRepositoryDao(repositoryDatabase: RepositoryDatabase) = repositoryDatabase.repositoryDao()

}