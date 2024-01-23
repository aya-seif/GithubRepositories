package com.example.githubrepoviewer.data.remote

import android.util.Log
import com.example.githubrepoviewer.data.local.dao.GithubRepositoryDao
import com.example.githubrepoviewer.data.mappers.toIssuesList
import com.example.githubrepoviewer.data.mappers.toListRepository
import com.example.githubrepoviewer.data.mappers.toListRepositoryEntity
import com.example.githubrepoviewer.data.mappers.toRepositoryDetails
import com.example.githubrepoviewer.data.remote.resources.RepositoriesResource
import com.example.githubrepoviewer.domain.model.Issue
import com.example.githubrepoviewer.domain.model.RepositoryDetails
import com.example.githubrepoviewer.domain.NetworkException
import com.example.githubrepoviewer.domain.repositories.GitHubRepoDataRepository
import com.example.githubrepoviewer.domain.model.Repository
import retrofit2.Response
import javax.inject.Inject

class GitHubRepoDataRepositoryImpl @Inject constructor(
    private val githubRepositoryService: GithubRepositoryService,
    private val githubRepositoryDao: GithubRepositoryDao,
) : GitHubRepoDataRepository {

    private var isFetching = false

    override suspend fun getRepositories(perPage: Int, pageOffset: Int): List<Repository> {
        if (isFetching) {
            return getReposFromLocal(perPage, pageOffset)
        }

        isFetching = true
        val remoteRepositoryResource = tryToExecute { githubRepositoryService.getRepositories() }
        githubRepositoryDao.deleteAllRepositories()
        saveReposToLocal(remoteRepositoryResource)

        return getReposFromLocal(perPage, pageOffset)
    }

    override suspend fun getRepositoryDetails(name: String, owner: String): RepositoryDetails =
        tryToExecute {
            githubRepositoryService.getRepositoryDetails(name, owner)
        }.toRepositoryDetails()

    override suspend fun getIssues(name: String, owner: String): List<Issue> =
        tryToExecute { githubRepositoryService.getRepositoryIssues(name, owner) }.toIssuesList()

    override suspend fun searchRepositories(query: String): List<Repository> {
        return emptyList()
    }

    private suspend fun saveReposToLocal(repositories: List<RepositoriesResource>) =
        githubRepositoryDao.insertGithubRepos(repositories.toListRepositoryEntity())

    private suspend fun getReposFromLocal(perPage: Int, pageOffset: Int): List<Repository> =
        githubRepositoryDao.getRepositoriesPaged(limit = perPage, offset = pageOffset)
            .toListRepository()

    private suspend fun <T> tryToExecute(func: suspend () -> Response<T>): T {
        return handleResponse(func())
    }

    private fun <T> handleResponse(response: Response<T>): T {
        Log.d("TAG", "handleResponse: ${response.code()} ")
        if (response.isSuccessful) {
            return response.body() ?: throw NetworkException.NotFoundException
        }
        throw when (response.code()) {
            HTTP_NOT_FOUND -> NetworkException.NotFoundException
            HTTP_API_KEY_EXPIRED -> NetworkException.ApiKeyExpiredException
            HTTP_UNAUTHORIZED -> NetworkException.UnAuthorizedException
            else -> NetworkException.UnknownException
        }
    }


    private companion object {
        const val HTTP_NOT_FOUND = 404
        const val HTTP_API_KEY_EXPIRED = 402
        const val HTTP_UNAUTHORIZED = 401
    }

}


