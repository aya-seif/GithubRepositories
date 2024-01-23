package com.example.githubrepoviewer.ui.repos

import androidx.paging.PagingData
import com.example.githubrepoviewer.domain.model.Repository
import kotlinx.coroutines.flow.Flow

data class RepositoriesUiState(
    var isLoading: Boolean = true,
    var repos: Flow<PagingData<Repository>>? = null,
    var error: String? = null
)
