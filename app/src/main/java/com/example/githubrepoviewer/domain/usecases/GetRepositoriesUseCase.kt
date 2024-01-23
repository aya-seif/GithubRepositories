package com.example.githubrepoviewer.domain.usecases

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.githubrepoviewer.data.RepositoriesPagingSource
import kotlinx.coroutines.GlobalScope
import javax.inject.Inject

class GetRepositoriesUseCase @Inject constructor(
    private val pagingSource: RepositoriesPagingSource
) {

    operator fun invoke() = Pager(
        config = PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            initialLoadSize = 20
        ),
        pagingSourceFactory = { pagingSource }
    ).flow

}