package com.example.githubrepoviewer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.githubrepoviewer.data.local.dao.GithubRepositoryDao
import com.example.githubrepoviewer.data.local.entities.RepositoryEntity


@Database(entities = [RepositoryEntity::class], version = 1, exportSchema = false)
abstract class RepositoryDatabase : RoomDatabase() {
    abstract fun repositoryDao(): GithubRepositoryDao
}