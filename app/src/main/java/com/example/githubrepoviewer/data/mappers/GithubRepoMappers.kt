package com.example.githubrepoviewer.data.mappers

import com.example.githubrepoviewer.data.local.entities.RepositoryEntity
import com.example.githubrepoviewer.data.remote.resources.IssueResource
import com.example.githubrepoviewer.data.remote.resources.RepositoriesResource
import com.example.githubrepoviewer.data.remote.resources.RepositoryDetailsResponse
import com.example.githubrepoviewer.domain.model.Issue
import com.example.githubrepoviewer.domain.model.RepositoryDetails
import com.example.githubrepoviewer.domain.model.Repository

//fun List<RepositoriesResource>.toListRepository(): List<Repository> {
//    return this.map {
//        Repository(
//            id = it.id ?: 0,
//            name = it.name ?: "",
//            description = it.description ?: "",
//            owner = it.owner?.login ?: "",
//            stars = 0
//        )
//    }
//}

fun List<RepositoriesResource>.toListRepositoryEntity(): List<RepositoryEntity> {
    return this.map {
        RepositoryEntity(
            id = it.id ?: 0,
            name = it.name ?: "",
            description = it.description ?: "",
            owner = it.owner?.login ?: "",
            stars = 0
        )
    }
}


fun Repository.toRepositoryEntity(): RepositoryEntity {
    return RepositoryEntity(
        id = this.id,
        name = this.name,
        description = this.description,
        owner = this.owner,
        stars = this.stars
    )
}

fun List<RepositoryEntity>.toListRepository(): List<Repository> {
    return this.map {
        Repository(
            id = it.id,
            name = it.name,
            description = it.description,
            owner = it.owner,
            stars = it.stars
        )
    }
}

fun RepositoryDetailsResponse.toRepositoryDetails(): RepositoryDetails {
    return RepositoryDetails(
        id = this.id ?: 0,
        name = this.name ?: "",
        description = this.description ?: "",
        owner = this.owner?.login ?: "",
        stars = this.stargazersCount ?: 0,
        forks = this.forksCount ?: 0,
        language = this.language ?: "",
        watchers = this.watchersCount ?: 0,
        openIssues = this.openIssuesCount ?: 0
    )
}

fun List<IssueResource>.toIssuesList(): List<Issue> {
    return this.map {
        Issue(
            title = it.title ?: "",
            author = it.user?.login ?: "",
            date = it.createdAt ?: "",
            state = it.state ?: "",
            number = it.number ?: 0
        )
    }
}