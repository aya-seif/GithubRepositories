package com.example.githubrepoviewer.ui.issues

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.githubrepoviewer.ui.issues.IssuesArgs.Companion.NAME_KEY
import com.example.githubrepoviewer.ui.issues.IssuesArgs.Companion.OWNER_KEY

private const val ROUTE = "IssuesScreen"

fun NavGraphBuilder.issuesScreenRoute(){
    composable(
        route ="$ROUTE/{${NAME_KEY}}/{${OWNER_KEY}}",
        arguments = listOf(
            navArgument(NAME_KEY) { type = NavType.StringType },
            navArgument(OWNER_KEY) { type = NavType.StringType },
        )
    ) {
        IssuesScreen()
    }
}

fun NavController.toIssues(name: String, owner: String) {
    navigate("$ROUTE/$name/$owner")
}

class IssuesArgs(
    private val savedStateHandle: androidx.lifecycle.SavedStateHandle
) {
    val name: String
        get() = savedStateHandle[NAME_KEY] ?: ""

    val owner: String
        get() = savedStateHandle[OWNER_KEY] ?: ""

    companion object{
        const val NAME_KEY = "name"
        const val OWNER_KEY = "owner"
    }
}


