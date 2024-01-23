package com.example.githubrepoviewer.ui.issues

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.items
import com.example.githubrepoviewer.R
import com.example.githubrepoviewer.ui.LocalNavController
import com.example.githubrepoviewer.domain.model.Issue
import com.example.githubrepoviewer.ui.theme.CardBackgroundColor
import com.example.githubrepoviewer.ui.theme.Green
import com.example.githubrepoviewer.ui.theme.Orange

@Composable
fun IssuesScreen(
    viewModel: IssuesViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()
    val navController = LocalNavController.current

    IssuesContentScreen(
        issues = state,
        onBackClick = {

        }
    )
}

@Composable
fun IssuesContentScreen(issues: List<Issue>, onBackClick: () -> Unit) {
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
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }

            Text(
                text = stringResource(R.string.issues),
                style = MaterialTheme.typography.titleLarge.copy(Green),
                modifier = Modifier
                    .padding(start = 8.dp)
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(top = 16.dp)
        ) {
            items(issues) { issue ->
                IssueCard(issue = issue)
            }
        }
    }
}

@Composable
fun IssueCard(issue: Issue) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(color = CardBackgroundColor, shape = RoundedCornerShape(16.dp))
            .clickable {}
            .padding(16.dp)
    ) {
        InfoRow(stringResource(R.string.title), issue.title)
        InfoRow(stringResource(R.string.state), issue.state)
        InfoRow(stringResource(R.string.date), issue.date)
        InfoRow(stringResource(R.string.author), issue.author)
        InfoRow(stringResource(R.string.number), issue.number.toString())
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium.copy(color = Color.Gray),
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 8.dp)
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.titleMedium.copy(color = Green),
                fontWeight = FontWeight.Bold,
            )
        }
    }
}







