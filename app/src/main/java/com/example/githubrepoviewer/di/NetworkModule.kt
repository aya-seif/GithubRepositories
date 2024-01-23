package com.example.githubrepoviewer.di

import com.example.githubrepoviewer.data.remote.GithubRepositoryService
import com.example.githubrepoviewer.domain.usecases.GetRepositoriesUseCase
import com.example.githubrepoviewer.domain.usecases.GetRepositoryDetailsUseCase
import com.example.githubrepoviewer.domain.repositories.GitHubRepoDataRepository
import com.example.githubrepoviewer.data.RepositoriesPagingSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory,
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com")
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .build()
    }

    @Singleton
    @Provides
    fun provideGsonFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRepositoryService(retrofit: Retrofit): GithubRepositoryService {
        return retrofit.create(GithubRepositoryService::class.java)
    }

    @Singleton
    @Provides
    fun provideRepositoriesPagingSource(gitHubRepoDataRepository: GitHubRepoDataRepository): RepositoriesPagingSource {
        return RepositoriesPagingSource(gitHubRepoDataRepository)
    }

    @Singleton
    @Provides
    fun provideGetRepositoriesUseCase(repositoriesPagingSource: RepositoriesPagingSource): GetRepositoriesUseCase {
        return GetRepositoriesUseCase(repositoriesPagingSource)
    }

    @Singleton
    @Provides
    fun provideGetRepositoryDetailsUseCase(gitHubRepoDataRepository: GitHubRepoDataRepository): GetRepositoryDetailsUseCase {
        return GetRepositoryDetailsUseCase(gitHubRepoDataRepository)
    }

}