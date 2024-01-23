package com.example.githubrepoviewer.ui.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val ROUTE = "SearchScreen"

fun NavGraphBuilder.searchScreenRoute() {

    composable(
        route = ROUTE
    ) {
        SearchScreen()
    }
}

fun NavController.navigateToSearchScreen(){
    navigate(ROUTE)
}