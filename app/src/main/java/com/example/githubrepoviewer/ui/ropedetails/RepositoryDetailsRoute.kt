package com.example.githubrepoviewer.ui.ropedetails

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val ROUTE =
    "RepositoryDetailsScreen"

fun NavGraphBuilder.repositoryDetailsRoute() {
    composable(
        route = "$ROUTE/{${RepositoryDetailsArgs.NAME}}/{${RepositoryDetailsArgs.OWNER}}",
        arguments = listOf(
            navArgument(RepositoryDetailsArgs.NAME) { type = NavType.StringType },
            navArgument(RepositoryDetailsArgs.OWNER) { type = NavType.StringType },
        )
    )
    {
        RepositoryDetailsScreen()
    }
}

fun NavController.toRepositoryDetails(name: String, owner: String) {
    navigate("$ROUTE/$name/$owner")
}

class RepositoryDetailsArgs(savedStateHandle: SavedStateHandle) {

    val name: String = checkNotNull(savedStateHandle[NAME])
    val owner: String = checkNotNull(savedStateHandle[OWNER])

    companion object {
        const val NAME = "name"
        const val OWNER = "owner"
    }
}
