package com.example.githubrepoviewer.ui.ropedetails

import com.example.githubrepoviewer.domain.model.RepositoryDetails

data class RepositoryDetailsScreenUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val repositoryDetails: RepositoryDetailsUiState? = null
)


data class RepositoryDetailsUiState(
    val name: String,
    val description: String,
    val owner: String,
    val stars: Int,
    val forks: Int,
    val issues: Int,
    val language: String,
)

fun RepositoryDetails.toUiState() = RepositoryDetailsUiState(
    name = name,
    description = description,
    owner = owner,
    stars = stars,
    forks = forks,
    issues = openIssues,
    language = language,
)
