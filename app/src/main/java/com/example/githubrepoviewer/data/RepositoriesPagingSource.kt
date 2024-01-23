package com.example.githubrepoviewer.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.githubrepoviewer.domain.repositories.GitHubRepoDataRepository
import com.example.githubrepoviewer.domain.model.Repository
import javax.inject.Inject

@OptIn(androidx.paging.ExperimentalPagingApi::class)
class RepositoriesPagingSource @Inject constructor(
    private val githubReposDataRepository: GitHubRepoDataRepository
) : PagingSource<Int, Repository>() {

    override fun getRefreshKey(state: PagingState<Int, Repository>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repository> {
        val page = params.key ?: 0
        return try {
            val response =
                githubReposDataRepository.getRepositories(params.loadSize, page * params.loadSize)
            LoadResult.Page(
                data = response,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (response.isEmpty()) null else page + 1
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}