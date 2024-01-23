package com.example.githubrepoviewer.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.githubrepoviewer.ui.issues.issuesScreenRoute
import com.example.githubrepoviewer.ui.repos.repositoriesScreenRoute
import com.example.githubrepoviewer.ui.ropedetails.repositoryDetailsRoute
import com.example.githubrepoviewer.ui.search.searchScreenRoute

private const val START_DESTINATION = "RepositoriesScreen"
val LocalNavController = compositionLocalOf<NavHostController> { error("NO CONTROLLER EXIST") }

@Composable
fun AppNavGraph(
    navController: NavHostController
) {

    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = START_DESTINATION) {
            repositoriesScreenRoute()
            repositoryDetailsRoute()
            issuesScreenRoute()
            searchScreenRoute()
        }
    }
}
