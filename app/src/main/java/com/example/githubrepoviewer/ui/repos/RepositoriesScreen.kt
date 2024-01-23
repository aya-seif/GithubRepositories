package com.example.githubrepoviewer.ui.repos

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import com.example.githubrepoviewer.ui.theme.CardBackgroundColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.githubrepoviewer.ui.LocalNavController
import com.example.githubrepoviewer.R
import com.example.githubrepoviewer.domain.model.Repository
import com.example.githubrepoviewer.ui.ropedetails.toRepositoryDetails
import com.example.githubrepoviewer.ui.theme.Green
import com.example.githubrepoviewer.ui.theme.Orange
import com.example.githubrepoviewer.ui.theme.Typography

@Composable
fun RepositoriesScreen(
    viewModel: RepositoriesViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsState()
    val navController = LocalNavController.current

    when {
        state.isLoading -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                CircularProgressIndicator(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center),
                    color = Green
                )
            }
        }

        !state.error.isNullOrBlank() -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
            ) {
                Text(
                    text = stringResource(R.string.repositories),
                    style = Typography.titleLarge.copy(Green),
                    modifier = Modifier.padding(start = 16.dp, top = 16.dp)
                )
                Text(
                    text = state.error ?: "Not found",
                    style = Typography.titleLarge.copy(Green),
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                )
            }
        }

        else -> {
            RepositoriesScreenContent(state = state, onRepoClick = { repoName, owner ->
                navController.toRepositoryDetails(repoName, owner)
            })
        }
    }
}

@Composable
fun RepositoriesScreenContent(state: RepositoriesUiState, onRepoClick: (String, String) -> Unit) {
    val repos = state.repos?.collectAsLazyPagingItems()
    Column {
        Text(
            text = stringResource(R.string.repositories),
            style = Typography.titleLarge.copy(Green),
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 16.dp),
        ) {
            repos?.let {
                items(it) { repo ->
                    repo?.let {
                        RepositoryCard(
                            repository = repo,
                            onRepoClick = onRepoClick,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun RepositoryCard(
    repository: Repository,
    onRepoClick: (String, String) -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .background(CardBackgroundColor, RoundedCornerShape(12.dp))
            .padding(16.dp)
            .clickable { onRepoClick(repository.name , repository.owner) }
    ) {

        Text(
            text = repository.name,
            style = Typography.titleLarge.copy(Green)
        )

        Row(
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.owner),
                    style = Typography.titleMedium.copy(color = Color.Gray)
                )
                Text(
                    text = repository.owner,
                    style = Typography.titleMedium.copy(color = Green),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(R.string.stars),
                    style = Typography.titleMedium.copy(color = Color.Gray)
                )
                Text(
                    text = "${repository.stars}",
                    style = Typography.titleMedium.copy(color = Orange),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.description),
            style = Typography.titleMedium.copy(color = Color.Gray)
        )
        Text(
            text = repository.description,
            style = Typography.titleMedium.copy(color = Color.White),
            modifier = Modifier.padding(top = 4.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }

}

