package com.example.githubrepoviewer.ui.ropedetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.githubrepoviewer.R
import com.example.githubrepoviewer.ui.LocalNavController
import com.example.githubrepoviewer.ui.issues.toIssues
import com.example.githubrepoviewer.ui.theme.Green
import com.example.githubrepoviewer.ui.theme.Typography

@Composable
fun RepositoryDetailsScreen(
    viewModel: RepositoryDetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    val navController = LocalNavController.current

    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(alignment = Alignment.Center)
                )
            }
        }

        state.error != null -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = state.error ?: "",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.align(alignment = Alignment.Center)
                )
            }

        }

        else -> {
            RepositoryDetailsScreenContent(state) { name, owner ->
                navController.toIssues(name, owner)
            }
        }
    }

    RepositoryDetailsScreenContent(state) { name, owner ->
        navController.toIssues(name, owner)
    }
}

@Composable
fun RepositoryDetailsScreenContent(
    state: RepositoryDetailsScreenUiState,
    onIssueBtnClick: (String, String) -> Unit
) {
    val repositoryDetailsUiState = state.repositoryDetails

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(24.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = stringResource(R.string.back))
            }

            Text(
                text = stringResource(R.string.repository_details),
                style = MaterialTheme.typography.titleLarge.copy(Green),
                modifier = Modifier
                    .padding(start = 8.dp)
            )

        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(R.string.repo_name),
                style = Typography.titleMedium.copy(color = Color.Gray)
            )
            Text(
                text = repositoryDetailsUiState?.name ?: "",
                style = Typography.titleMedium.copy(color = Color.Black),
                modifier = Modifier.padding(start = 4.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(R.string.owner),
                style = Typography.titleMedium.copy(color = Color.Gray),
            )
            Text(
                text = repositoryDetailsUiState?.owner ?: "",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            )
        }

        Text(
            text = stringResource(R.string.description),
            style = Typography.titleMedium.copy(color = Color.Gray)
        )
        Text(
            text = repositoryDetailsUiState?.description ?: "",
            style = Typography.titleMedium.copy(color = Color.Black),
            modifier = Modifier.padding(top = 4.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )



        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DetailsItem("Stars", repositoryDetailsUiState?.stars.toString()?: "")
            DetailsItem("Forks", repositoryDetailsUiState?.forks.toString())
            DetailsItem("Issues", repositoryDetailsUiState?.issues.toString())
            DetailsItem("Language", repositoryDetailsUiState?.language.toString())
        }

        Button(
            onClick = {
                onIssueBtnClick(
                    repositoryDetailsUiState?.name ?: "",
                    repositoryDetailsUiState?.owner ?: ""
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Text(text = "Issues")
        }
    }
}

@Composable
private fun DetailsItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.secondary
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium
        )
    }
}
