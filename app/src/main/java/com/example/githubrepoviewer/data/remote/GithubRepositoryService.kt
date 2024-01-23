package com.example.githubrepoviewer.data.remote

import com.example.githubrepoviewer.data.remote.resources.IssueResource
import com.example.githubrepoviewer.data.remote.resources.RepositoriesResource
import com.example.githubrepoviewer.data.remote.resources.RepositoryDetailsResponse
import com.example.githubrepoviewer.data.remote.resources.SearchResponse
import com.example.githubrepoviewer.data.utils.OWNER
import com.example.githubrepoviewer.data.utils.REPOS_ENDPOINT
import com.example.githubrepoviewer.data.utils.REPO_DETAILS_ENDPOINT
import com.example.githubrepoviewer.data.utils.REPO_ISSUES_ENDPOINT
import com.example.githubrepoviewer.data.utils.REPO_NAME
import com.example.githubrepoviewer.data.utils.SEARCH
import com.example.githubrepoviewer.data.utils.SEARCH_ENDPOINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubRepositoryService {

    @GET(REPOS_ENDPOINT)
    suspend fun getRepositories(): Response<List<RepositoriesResource>>

    @GET(REPO_DETAILS_ENDPOINT)
    suspend fun getRepositoryDetails(
        @Path(OWNER) owner: String,
        @Path(REPO_NAME) name: String
    ): Response<RepositoryDetailsResponse>

    @GET(REPO_ISSUES_ENDPOINT)
    suspend fun getRepositoryIssues(
        @Path(OWNER) owner: String,
        @Path(REPO_NAME) name: String
    ): Response<List<IssueResource>>

    @GET(SEARCH_ENDPOINT)
    suspend fun searchForRepo(@Query(SEARCH) query: String): Response<SearchResponse>
}