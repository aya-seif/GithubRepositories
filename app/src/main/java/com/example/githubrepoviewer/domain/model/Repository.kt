package com.example.githubrepoviewer.domain.model

data class Repository(
    val id: Int,
    val name: String,
    val description: String,
    val owner: String,
    val stars: Int,
)
