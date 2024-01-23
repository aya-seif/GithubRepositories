package com.example.githubrepoviewer.ui.repos

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "RepositoriesScreen"

fun NavGraphBuilder.repositoriesScreenRoute(){
    composable(
        route = ROUTE
    ) {
        RepositoriesScreen()
    }
}