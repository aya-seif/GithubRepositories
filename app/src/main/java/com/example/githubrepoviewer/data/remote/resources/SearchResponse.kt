package com.example.githubrepoviewer.data.remote.resources

data class SearchResponse(
    val incomplete_results: Boolean,
    val items: List<RepositoriesResource>,
    val total_count: Int
)
