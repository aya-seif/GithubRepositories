package com.example.githubrepoviewer.domain.usecases

import com.example.githubrepoviewer.domain.repositories.GitHubRepoDataRepository
import javax.inject.Inject

class GetRepositoryDetailsUseCase @Inject constructor(
    private val repository: GitHubRepoDataRepository
){

    suspend operator fun invoke(owner: String, repoName: String) =
        repository.getRepositoryDetails(owner, repoName)

}