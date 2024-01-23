package com.example.githubrepoviewer.domain.repositories

import com.example.githubrepoviewer.domain.model.Issue
import com.example.githubrepoviewer.domain.model.RepositoryDetails
import com.example.githubrepoviewer.domain.model.Repository

interface GitHubRepoDataRepository {

    suspend fun getRepositories(perPage: Int, pageOffset: Int): List<Repository>

    suspend fun getRepositoryDetails(name: String, owner: String): RepositoryDetails

    suspend fun getIssues(name: String, owner: String): List<Issue>

    suspend fun searchRepositories(query: String): List<Repository>

}