package com.example.githubrepoviewer.domain.model

data class RepositoryDetails(
    val id: Int,
    val name: String,
    val description: String,
    val owner: String,
    val stars: Int,
    val forks: Int,
    val language: String,
    val watchers: Int,
    val openIssues: Int,
)
