package com.example.githubrepoviewer.domain.model

data class Issue(
    val title: String = "",
    val state: String = "",
    val date: String = "",
    val author: String = "",
    val number: Int = 0
)
