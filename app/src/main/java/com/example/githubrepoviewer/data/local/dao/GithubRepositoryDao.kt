package com.example.githubrepoviewer.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.githubrepoviewer.data.local.entities.RepositoryEntity

@Dao
interface GithubRepositoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGithubRepos(repository: List<RepositoryEntity>)

    @Query("SELECT * FROM repository ORDER BY id ASC LIMIT :limit OFFSET :offset")
    suspend fun getRepositoriesPaged(limit: Int, offset: Int): List<RepositoryEntity>

    @Query("DELETE FROM repository")
    suspend fun deleteAllRepositories()

    @Update
    suspend fun updateRepository(repositoryEntity: RepositoryEntity)

    @Query("SELECT * FROM repository")
    suspend fun getRepositories(): List<RepositoryEntity>

}