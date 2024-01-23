package com.example.githubrepoviewer.di

import com.example.githubrepoviewer.data.remote.GitHubRepoDataRepositoryImpl
import com.example.githubrepoviewer.domain.repositories.GitHubRepoDataRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindGitHubRepoDataRepository(repositoryImpl: GitHubRepoDataRepositoryImpl): GitHubRepoDataRepository

}